#%%
import re
import collections
import numpy as np
import jieba
import wordcloud
from PIL import Image
import matplotlib.pyplot as plt

#%%
import functools
def list_unpack(l):
    return functools.reduce(lambda x,y: x+y, l)

#%%
def createCommentWordcloud(product_id,comment_list):
    comment_list = list_unpack(comment_list)
    string_data = ' '.join(comment_list)
    
    #文本预处理
    pattern = re.compile(u'\t|\n|\.|-|:|;|\)|\(|\?|"')
    string_data = re.sub(pattern,'',string_data)

    #分词
    seg_list_exact = jieba.cut(string_data, cut_all=False)
    object_list = []
    remove_words = [u'的', u'，',u'和', u'是', u'随着', u'对于', u'对',u'等',u'能',u'都',u'。',u' ',u'、',u'中',u'在',u'了',
                u'通常',u'如果',u'我们',u'需要'] #停用词
    for word in seg_list_exact:
        if word not in remove_words:
            object_list.append(word)
    
    #词频统计
    word_counts = collections.Counter(object_list)
    #word_counts_top10 = word_counts.most_common(10)
    
    #词频展示
    #mask = np.array(Image.open('./wordcloud_background.jpg'))
    mask = np.array(Image.open('./wordcloud/wordcloud_background.jpg'))
    wc = wordcloud.WordCloud(
        font_path='C:/Windows/Fonts/msyh.ttc',
        mask = mask,
        #scale= 32,
        max_words = 200,
        max_font_size = 120,
        background_color="white"
    )

    wc.generate_from_frequencies(word_counts)
    image_colors = wordcloud.ImageColorGenerator(mask)
    wc.recolor(color_func=image_colors)
    picture_path = './wordcloud/comment_cloud/%s.png'%(product_id)
    wc.to_file(picture_path)
    return picture_path


#%%d
def createProfessionWordcloud(product_id, word_counts):

    #词频展示
    #mask = np.array(Image.open('./wordcloud_background.jpg'))
    mask = np.array(Image.open('./wordcloud/wordcloud_background.jpg'))
    wc = wordcloud.WordCloud(
        font_path='C:/Windows/Fonts/msyh.ttc',
        mask = mask,
        #scale= 32,
        max_words = 200,
        max_font_size = 120,
        background_color="white"
    )

    wc.generate_from_frequencies(word_counts)
    image_colors = wordcloud.ImageColorGenerator(mask)
    wc.recolor(color_func=image_colors)
    picture_path = './wordcloud/profession_cloud/%s.png'%(product_id)
    wc.to_file(picture_path)
    return picture_path

#%%
# import pymysql
# import math

# db = pymysql.connect('localhost','root','123456','bbt')
# cursor = db.cursor()

# sql = 'SELECT content FROM `comment` WHERE product_id=7 and product_type=0'
# cursor.execute(sql)
# comment_list = cursor.fetchall()
# #print(list_unpack(comment_list))
# #createCommentWordcloud(comment_list)

# db.close()

#%%
# createCommentWordcloud('7',comment_list)

# %%
