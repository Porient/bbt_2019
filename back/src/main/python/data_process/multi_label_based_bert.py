#%%
import pymysql
import math
import random
import string
import time
import re
import numpy as np
import csv
import pandas as pd

#%%
db = pymysql.connect('localhost','root','123456','bbt')
cursor = db.cursor()
sql = 'SELECT product_id,brand,product_name,performance,interfaces,front_camera,rear_camera,body,screen,price FROM phone'
cursor.execute(sql)
phones = cursor.fetchall()
train_list = []
valid_list = []
test_list = []
for phone in phones:
    phone = list(phone)
    product_id = phone[0]
    phone[-1] = str(phone[-1])
    product_description = ' '.join(phone[1:])
    temp = []
    temp.append(product_id)
    temp.append(product_description)
    if product_id < 51:
        train_list.append(temp)
    elif product_id < 101:
        valid_list.append(temp)
    else:
        test_list.append(temp)
db.close()
train_set = np.array(train_list)
valid_set = np.array(valid_list)
test_set = np.array(test_list)
print(train_set.shape)
print(valid_set.shape)
print(test_set.shape)

#%%
#高续航，高像素，大屏幕，高性能，高性价比，高便携性
labels = ['high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']
columns = ['product_id','text','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']
train_labels = np.zeros((train_set.shape[0],6))
valid_labels = np.zeros((valid_set.shape[0],6))
print(train_labels.shape)
print(valid_labels.shape)
#前50个作为train，生成label，存储到train.csv中，并在数据库中设置tag
for row in range(train_labels.shape[0]):
    #随机生成三个0-5的index
    index_list = random.sample(range(6),3)
    for index in index_list:
        train_labels[row][index] = 1
#拼接并存储到train.csv
train = np.concatenate((train_set,train_labels),axis=1)
trainFile = open('train.csv','w',newline='')
writer = csv.writer(trainFile)
writer.writerow(columns)
writer.writerows(train)
trainFile.close()

#50-100作为valid，生成label，存储到valid.csv中，并在数据库中设置tag
for row in range(valid_labels.shape[0]):
    #随机生成三个0-5的index
    index_list = random.sample(range(6),3)
    for index in index_list:
        valid_labels[row][index] = 1
#凭借并存储到valid.csv
valid = np.concatenate((valid_set,valid_labels),axis=1)
validFile = open('valid.csv','w',newline='')
writer = csv.writer(validFile)
writer.writerow(columns)
writer.writerows(valid)
validFile.close()

#将test_set存储到test.csv
testFile = open('test.csv','w',newline='')
writer = csv.writer(testFile)
writer.writerow(['product_id','text'])
writer.writerows(test_set)
testFile.close()

#%%
#这部分在colab运行
# !git clone https://github.com/NVIDIA/apex.git
# %cd apex
# !pip install -v --no-cache-dir ./
# %cd ..
import torch
import apex
from fastai.text import *
import datetime
run_start_time = datetime.datetime.today().strftime('%Y-%m-%d_%H-%M-%S')

LOG_PATH=Path('drive/Colab Notebooks/logs/')  
MODEL_PATH=Path('drive/Colab Notebooks/models/') 

if not LOG_PATH.exists():
  LOG_PATH.mkdir()
import logging

args = {
    "run_text": "my_test",
    "max_seq_length": 512,
    "do_lower_case": True,
    "train_batch_size": 4,
    "learning_rate": 6e-5,
    "num_train_epochs": 12.0,
    "warmup_proportion": 0.002,
    "local_rank": -1,
    "gradient_accumulation_steps": 1,
    "fp16": True,
    "loss_scale": 128
}

logfile = str(LOG_PATH/'log-{}-{}.txt'.format(run_start_time, args["run_text"]))

logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(name)s -   %(message)s',
    datefmt='%m/%d/%Y %H:%M:%S',
    handlers=[
        logging.FileHandler(logfile),
        logging.StreamHandler(sys.stdout)
    ])

logger = logging.getLogger()

device = torch.device('cuda')

if torch.cuda.device_count() > 1:
    multi_gpu = True
else:
    multi_gpu = False

# !git clone https://github.com/wshuyi/demo-multi-label-classification-bert.git
# !pip install fast-bert
DATA_PATH = Path('drive/Colab Notebooks/data/')   
LABEL_PATH = Path('drive/Colab Notebooks/labels/')
label_cols = ['high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']
BERT_PRETRAINED_MODEL = "bert-base-uncased"
args = {}
args["do_lower_case"] = True
args["train_batch_size"] = 4
args["learning_rate"] = 6e-5
args["max_seq_length"] = 512
args["fp16"] = True

from fast_bert.data_cls import BertDataBunch

databunch = BertDataBunch(DATA_PATH, LABEL_PATH,
                          tokenizer='bert-base-uncased',
                          train_file='train.csv',
                          val_file='valid.csv',
                          label_file='labels.csv',
                          text_col='text',
                          label_col=label_cols,
                          batch_size_per_gpu=4,
                          max_seq_length=512,
                          multi_gpu=multi_gpu,
                          multi_label=True,
                          model_type='bert')

#create learner
from fast_bert.learner_cls import BertLearner
from fast_bert.metrics import *
metrics = [{'name': 'accuracy', 'function': accuracy_multilabel}]

logger.info(args)

OUTPUT_DIR = Path('drive/Colab Notebooks/data/')
learner = BertLearner.from_pretrained_model(
						databunch,
						pretrained_path='bert-base-uncased',
						metrics=metrics,
						device=device,
						logger=logger,
						output_dir=OUTPUT_DIR,
						finetuned_wgts_path=None,
						warmup_steps=500,
						multi_gpu=multi_gpu,
						is_fp16=args['fp16'],
						multi_label=True,
						logging_steps=50)

learner.fit(epochs=8, lr=6e-5, validate=True, schedule_type="warmup_linear")


import pandas as pd
data = pd.read_csv("drive/Colab Notebooks/data/test.csv")
#test = data.loc[0:-1].values
texts = []
for index, item in data.iterrows():
  texts.append(item['text'])
pre_list = learner.predict_batch(texts)
print(len(pre_list))

#处理prediction格式
import csv
labels = ['high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']
# pre_list = [
#     [('high_endurance', 0.020599365234375), ('high_pixel', 0.01107025146484375), ('large_screen', 0.01029205322265625), ('high_performance', 0.019124755859375), ('high_cost_effective', 0.08812530517578125), ('high_portability', 0.02782012939453125)],
#     [('high_endurance', 0.020599365234375), ('high_pixel', 0.03107025146484375), ('large_screen', 0.04029205322265625), ('high_performance', 0.049124755859375), ('high_cost_effective', 0.06812530517578125), ('high_portability', 0.01782012939453125)],
#     [('high_endurance', 0.060599365234375), ('high_pixel', 0.09107025146484375), ('large_screen', 0.09029205322265625), ('high_performance', 0.029124755859375), ('high_cost_effective', 0.05812530517578125), ('high_portability', 0.04782012939453125)],
#     [('high_endurance', 0.080599365234375), ('high_pixel', 0.04107025146484375), ('large_screen', 0.01029205322265625), ('high_performance', 0.039124755859375), ('high_cost_effective', 0.01812530517578125), ('high_portability', 0.06782012939453125)],
# ]
test_labels = []
for product in pre_list:
    #先将元素反向
    temp_list = []
    for item in product:
        temp_list.append(item[::-1])
    sorted_temp_list = sorted(temp_list)
    
    #生成label
    temp_label = [0]*6
    for i in range(3):
        key = sorted_temp_list[i][1]
        index = labels.index(key)
        temp_label[index] = 1
    test_labels.append(temp_label)

prediction = np.array(test_labels)
print(prediction.shape)
#存储到prediction.csv
predictFile = open('drive/Colab Notebooks/prediction.csv','w',newline='')
writer = csv.writer(predictFile)
writer.writerow(labels)
writer.writerows(prediction)
predictFile.close()

#%%
#读取train.csv和valid.csv
train_data = pd.read_csv('train.csv')
train = train_data.loc[:,['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']]
train[['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']] = train[['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']].astype(int)
print(train.shape)
valid_data = pd.read_csv('valid.csv')
valid = valid_data.loc[:,['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']]
valid[['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']] = valid[['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']].astype(int)
print(valid.shape)
#%%
#%%
db = pymysql.connect('localhost','root','123456','bbt')
cursor = db.cursor()
#读取train.csv和test.csv
for index, item in train.iterrows():
    tag_list=[]
    product_id = item['product_id']
    high_endurance = item['high_endurance']
    high_pixel = item['high_pixel']
    large_screen = item['large_screen']
    high_performance = item['high_performance']
    high_cost_effective = item['high_cost_effective']
    high_portability = item['high_portability']
    #print(product_id,high_endurance,high_pixel,large_screen,high_performance,high_cost_effective,high_portability)
    if high_endurance == 1:
        tag_list.append('强续航')
    if high_pixel == 1:
        tag_list.append('高像素')
    if large_screen == 1:
        tag_list.append('大屏幕')
    if high_performance == 1:
        tag_list.append('高性能')
    if high_cost_effective == 1:
        tag_list.append('高性价比')
    if high_portability == 1:
        tag_list.append('便携')
    #print(tag_list)
    sql = "UPDATE phone SET tag1='{}',tag2='{}',tag3='{}' WHERE product_id='{}'".format(tag_list[0],tag_list[1],tag_list[2],product_id)
    cursor.execute(sql)

for index, item in valid.iterrows():
    tag_list=[]
    product_id = item['product_id']
    high_endurance = item['high_endurance']
    high_pixel = item['high_pixel']
    large_screen = item['large_screen']
    high_performance = item['high_performance']
    high_cost_effective = item['high_cost_effective']
    high_portability = item['high_portability']
    #print(product_id,high_endurance,high_pixel,large_screen,high_performance,high_cost_effective,high_portability)
    if high_endurance == 1:
        tag_list.append('强续航')
    if high_pixel == 1:
        tag_list.append('高像素')
    if large_screen == 1:
        tag_list.append('大屏幕')
    if high_performance == 1:
        tag_list.append('高性能')
    if high_cost_effective == 1:
        tag_list.append('高性价比')
    if high_portability == 1:
        tag_list.append('便携')
    #print(tag_list)
    sql = "UPDATE phone SET tag1='{}',tag2='{}',tag3='{}' WHERE product_id='{}'".format(tag_list[0],tag_list[1],tag_list[2],product_id)
    cursor.execute(sql)

db.commit()
db.close()
# %%
#读取test
test_data = pd.read_csv('prediction.csv')
test_head = pd.read_csv('test.csv')
prediction_data = pd.concat([test_head,test_data],axis=1)
print(prediction_data.shape)
prediction = prediction_data.loc[:,['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']]
prediction[['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']] = prediction[['product_id','high_endurance','high_pixel','large_screen','high_performance','high_cost_effective','high_portability']].astype(int)
print(prediction.shape)

#%%
print(prediction.head(10))
#%%
db = pymysql.connect('localhost','root','123456','bbt')
cursor = db.cursor()

for index, item in prediction.iterrows():
    tag_list=[]
    product_id = item['product_id']
    high_endurance = item['high_endurance']
    high_pixel = item['high_pixel']
    large_screen = item['large_screen']
    high_performance = item['high_performance']
    high_cost_effective = item['high_cost_effective']
    high_portability = item['high_portability']
    #print(product_id,high_endurance,high_pixel,large_screen,high_performance,high_cost_effective,high_portability)
    if high_endurance == 1:
        tag_list.append('强续航')
    if high_pixel == 1:
        tag_list.append('高像素')
    if large_screen == 1:
        tag_list.append('大屏幕')
    if high_performance == 1:
        tag_list.append('高性能')
    if high_cost_effective == 1:
        tag_list.append('高性价比')
    if high_portability == 1:
        tag_list.append('便携')
    #print(tag_list)
    sql = "UPDATE phone SET tag1='{}',tag2='{}',tag3='{}' WHERE product_id='{}'".format(tag_list[0],tag_list[1],tag_list[2],product_id)
    cursor.execute(sql)

for index, item in valid.iterrows():
    tag_list=[]
    product_id = item['product_id']
    high_endurance = item['high_endurance']
    high_pixel = item['high_pixel']
    large_screen = item['large_screen']
    high_performance = item['high_performance']
    high_cost_effective = item['high_cost_effective']
    high_portability = item['high_portability']
    #print(product_id,high_endurance,high_pixel,large_screen,high_performance,high_cost_effective,high_portability)
    if high_endurance == 1:
        tag_list.append('强续航')
    if high_pixel == 1:
        tag_list.append('高像素')
    if large_screen == 1:
        tag_list.append('大屏幕')
    if high_performance == 1:
        tag_list.append('高性能')
    if high_cost_effective == 1:
        tag_list.append('高性价比')
    if high_portability == 1:
        tag_list.append('便携')
    #print(tag_list)
    sql = "UPDATE phone SET tag1='{}',tag2='{}',tag3='{}' WHERE product_id='{}'".format(tag_list[0],tag_list[1],tag_list[2],product_id)
    cursor.execute(sql)

db.commit()
db.close()
# %%
import pandas as pd
data = pd.read_csv("test.csv")
texts = []
for product_id, text in test.iterrows:
  texts.append(text)
#pre_list = learner.predict_batch(texts)
print(pre_list)