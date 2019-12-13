#%%
import pymysql
import math
import random
import string
import time
import re
from faker import Faker


db = pymysql.connect('localhost','root','123456','bbt')
cursor = db.cursor()
fake = Faker("zh_CN")
include_computer = 0
phone_num = 222
computer_num =223

#%%
#清空表
table_name=['collect','comment','comment_like','product_like','record','user','user_like']
for i in range(len(table_name)):
    cursor.execute("TRUNCATE TABLE %s"%(table_name[i]))
db.commit()

#%%
def delete_brackets(s):
    #删除半角括号内容
    s = re.sub(u"\\(.*?\\)|\\{.*?}|\\[.*?]", "", s)
    s = re.sub('（.*?）','',s)
    return s

#%%
#数据清洗phone
sql = 'SELECT * FROM phone'
cursor.execute(sql)
phones = cursor.fetchall() #fetchall()
#phones = cursor.fetchmany(2)
#清空表
cursor.execute('TRUNCATE TABLE phone')
db.commit()

#无效数据,continue，不插入
#删除括号内的描述
#提取信息后重组
for phone in phones:
    skips = ['nan','以官网信息为准']

    #brand
    brand = phone[1].strip()
    if brand in skips:
        continue
    if brand == '其他':
        continue
    brand = brand.split(' ',1)[0] #只取第一个空格前
    brand = brand.split('（',1)[0] #如果有中文括号，去掉括号后面的部分
    brand = brand.split('(',1)[0] #如果有英文括号删除括号后面的部分
    #print(brand)

    #product_name
    product_name = phone[2].strip()
    if product_name in skips:
        continue
    if product_name == '苹果':
        continue
    if product_name == 'nova5I':
        continue
    if product_name == '小米mix2':
        continue
    if product_name == '以官网信息为准':
        continue
    if product_name == '其他':
        continue

    product_name = delete_brackets(product_name)
    name_list = product_name.split(' ')
    if len(name_list) < 3:
        #只保留前三部分描述
        product_name = ' '.join(name_list)
    else:
        product_name = ' '.join(name_list[:3])
    #print(product_name)

    #performance
    performance = phone[3].strip()
    if performance in skips:
        continue
    performance = delete_brackets(performance)
    performance = performance.replace(':', ' ')
    perform_list = performance.split(' ')
    if 'CPU型号' in perform_list:
        index = perform_list.index('CPU型号')
        cpu_type = perform_list[index+1]
        if cpu_type == '其他':
            cpu_type = '以官网信息为准'
    else:
        cpu_type = '以官网信息为准'

    # if 'CPU频率' in perform_list:
    #     index = perform_list.index('CPU频率')
    #     cpu_rank = perform_list[index+1]
    #     if cpu_rank == '其他':
    #         cpu_rank = '以官网信息为准'
    # else:
    #     cpu_rank = '以官网信息为准'

    # if 'CPU核数' in perform_list:   #有问题
    #     index = perform_list.index('CPU核数')
    #     cpu_num = perform_list[index+1]
    #     if cpu_num == '其他':
    #         cpu_num = '以官网信息为准'
    # else:
    #     cpu_num = '以官网信息为准'

    if '运行内存' in perform_list:
        index = perform_list.index('运行内存')
        memory = perform_list[index+1]
        memory = memory.replace('GB','')
        memory = memory.replace('以下','')
        if memory == '其他':
            memory = '以官网信息为准'
    else:
        memory = '以官网信息为准'
    
    if '操作系统' in perform_list:
        index = perform_list.index('操作系统')
        ostype = perform_list[index+1]
        if ostype == '其他':
            ostype = '以官网信息为准'
    else:
        ostype = '以官网信息为准'

    if '操作系统版本' in perform_list:
        index = perform_list.index('操作系统版本')
        osdetail = perform_list[index+1:]
        osdetail = ' '.join(osdetail)
        if osdetail == '其他':
            osdetail = '以官网信息为准'
    else:
        osdetail = '以官网信息为准'
    performance = 'CPU型号:%s  运行内存(GB):%s 操作系统:%s 操作系统版本:%s'%(cpu_type,memory,ostype,osdetail)
    # print(performance)

    #interface
    interface = phone[4].strip()
    if interface in skips:
        continue
    interface = interface.replace('；',';')
    interface = interface.replace('(','')
    interface = interface.replace(')','')
    interface = interface.replace('（','')
    interface = interface.replace('）','')
    interface = interface.replace('mm','')
    #print(interface)

    #front_camera
    front_camera = phone[5].strip()
    if front_camera in skips:
        continue
    front_camera = front_camera.replace(':', ' ')
    front_list = front_camera.split(' ')
    if '前摄主摄光圈' in front_list:
        index = front_list.index('前摄主摄光圈')
        circle = front_list[index+1]
        if circle =='其他':
            circle = '以官网信息为准'
    else:
        circle = '以官网信息为准'
    
    if '前摄的主摄像素' in front_list:
        index = front_list.index('前摄的主摄像素')
        pixel = front_list[index+1]
        pixel = pixel.replace('万像素','')
        if pixel == '其他':
            pixel = '以官网信息为准'
    else:
        pixel = '以官网信息为准'
    front_camera = '光圈:%s 像素(万):%s'%(circle,pixel)
    #print(front_camera)

    #rear_camera
    rear_camera = phone[6].strip()
    if rear_camera in skips:
        continue
    rear_camera = rear_camera.replace(':',' ')
    rear_list = rear_camera.split(' ')
    if '后摄主摄光圈' in rear_list:
        index = rear_list.index('后摄主摄光圈')
        circle = rear_list[index+1]
        if circle =='其他':
            circle = '以官网信息为准'
    else:
        circle = '以官网信息为准'
    
    if '后摄的主摄像素' in rear_list:
        index = rear_list.index('后摄的主摄像素')
        pixel = rear_list[index+1]
        pixel = pixel.replace('万像素','')
        if pixel == '其他':
            pixel = '以官网信息为准'
    else:
        pixel = '以官网信息为准'
    rear_camera = '光圈:%s 像素(万):%s'%(circle,pixel)
    #print(rear_camera)

    #photo_features
    photo_features = phone[7].strip()
    if photo_features == 'nan':
        photo_features ='无'
    else:
        photo_features = photo_features.replace('；',';')
    # print(photo_features)

    #body
    body = phone[8].strip()
    if body in skips:
        continue
    body = body.replace('（','(')
    body = body.replace('）',')')
    body = body.replace('。',' ')
    body = body.replace('：',' ')
    body = body.replace(':',' ')
    body = body.replace('约','')
    body = body.replace('；',';')
    body = body.replace('备注',' ')
    body = delete_brackets(body)
    body_list = body.split(' ')
    #print(body_list)
    if '机身颜色' in body_list:
        index = body_list.index('机身颜色')
        color = body_list[index+1]
        if color == '其他':
            color = '以官网信息为准'
    else:
        color = '以官网信息为准'
    
    if '机身长度' in body_list:
        index = body_list.index('机身长度')
        length = body_list[index+1]

        length = length.replace('mm','')
        length = length.replace('毫米','')
        if length == '其他':
            length = '以官网信息为准'
    else:
        length = '以官网信息为准'

    if '机身重量' in body_list:
        index = body_list.index('机身重量')
        weight = body_list[index+1]
        weight = weight.replace('克','')
        weight = weight.replace('g','')
        if weight == '其他':
            weight = '以官网信息为准'
    else:
        weight = '以官网信息为准'

    if '机身材质工艺' in body_list:
        index = body_list.index('机身材质工艺')
        meterial = body_list[index+1]
        if meterial == '其他':
            meterial = '以官网信息为准'
        if meterial == '其它':
            meterial = '以官网信息为准'
    else:
        meterial = '以官网信息为准'
    
    if '机身宽度' in body_list:
        index = body_list.index('机身宽度')
        wide = body_list[index+1]
        wide = wide.replace('mm','')
        wide = wide.replace('毫米','')
        if wide == '其他':
            wide = '以官网信息为准'
    else:
        wide = '以官网信息为准'

    if '机身材质分类' in body_list:
        index = body_list.index('机身材质分类')
        catogory = body_list[index+1]
        if catogory == '其他':
            catogory = '以官网信息为准'
        if catogory == '其它':
            catogory = '以官网信息为准'
    else:
        catogory = '以官网信息为准'
    
    if '机身厚度' in body_list:
        index = body_list.index('机身厚度')
        thick = body_list[index+1]
        thick = thick.replace('mm','')
        thick = thick.replace('毫米','')
        if thick == '其他':
            thick = '以官网信息为准'
    else:
        thick = '以官网信息为准'

    if '机身存储' in body_list:
        index = body_list.index('机身存储')
        memory = body_list[index+1]
        memory = memory.replace('GB','')
        memory = memory.replace('以下','')
        if memory == '其它存储':
            memory = '以官网信息为准'
    else:
        memory = '以官网信息为准'
    
    #print(color,length,weight,meterial,wide,catogory,thick,memory)
    body = '机身颜色:%s 机身长度(mm):%s 机身重量(g):%s 机身材质工艺:%s 机身宽度(mm):%s 机身材质分类:%s 机身厚度(mm):%s 机身存储(GB):%s'%(color,length,weight,meterial,wide,catogory,thick,memory)
    #print(body)

    #communication
    communicaiton = phone[9].strip()
    communicaiton = communicaiton.replace('（','(')
    communicaiton = communicaiton.replace('）',')')
    communicaiton = communicaiton.replace('个','')
    # print(communicaiton)

    #endurance
    endurance = phone[10].strip()
    endurance = endurance.replace('（','(')
    endurance = endurance.replace('）',')')
    endurance = endurance.replace('：',' ')
    endurance = endurance.replace(':',' ')
    endurance = delete_brackets(endurance)
    endurance_list = endurance.split(' ')
    
    if '充电器' in endurance_list:
        index = endurance_list.index('充电器')
        power = endurance_list[index+1]
        if power == "其他":
            power = "以官网信息为准"
    else:
        power = "以官网信息为准"

    if '电池容量' in endurance_list:
        index = endurance_list.index('电池容量')
        capacity = endurance_list[index+1]
        capacity = capacity.replace('mAh','')
        if capacity == "其他":
            capacity = "以官网信息为准"
    else:
        capacity = "以官网信息为准"

    if '充电接口类型' in endurance_list:
        index = endurance_list.index('充电接口类型')
        power_type = endurance_list[index+1]
        if power_type == "其他":
            power_type = "以官网信息为准"
    else:
        power_type = "以官网信息为准"
    
    endurance = '充电器:%s 电池容量(mAh):%s 充电接口类型:%s'%(power,capacity,power_type)
    # print(endurance)

    #screen
    screen = phone[11].strip()
    screen = screen.replace('FHD+ ','')
    screen = screen.replace('HD+ ','')
    screen = delete_brackets(screen)
    screen = screen.replace('：',' ')
    screen = screen.replace(':',' ')
    screen_list = screen.split(' ')
    #print(screen_list)

    if '分辨率' in screen_list:
        index = screen_list.index('分辨率')
        fenbianlv = screen_list[index+1]
        fenbianlv.replace('像素','')
        if fenbianlv == "其他":
            fenbianlv = "以官网信息为准"
    else:
        fenbianlv = "以官网信息为准"

    if '屏幕材质类型' in screen_list:
        index = screen_list.index('屏幕材质类型')
        screen_type = screen_list[index+1]
    else:
        screen_type = "其他"

    if '屏幕比例' in screen_list:
        index = screen_list.index('屏幕比例')
        screen_size1 = screen_list[index+1]
        screen_size2 = screen_list[index+2]
        if screen_size1 == "其它屏幕比例":
            screen_size = "以官网信息为准"
        else:
            screen_size = screen_size1 + ":" + screen_size2
    else:
        screen_size = "以官网信息为准"
    
    if '主屏幕尺寸' in screen_list:
        index = screen_list.index('主屏幕尺寸')
        size = screen_list[index+1]
        size.replace('英寸','')
    else:
        size = "以官网信息为准"
    
    screen = '分辨率:%s 屏幕材质类型:%s 屏幕比例:%s 主屏幕尺寸:%s'%(fenbianlv,screen_type,screen_size,size)
    # print(screen)

    #other
    other = phone[12].strip()
    other = other.replace(':',' ')
    other = other.replace('：',' ')
    date_list = other.split(' ')
    # print(date_list)

    if '上市年份' in date_list:
        index = date_list.index('上市年份')
        year = date_list[index+1]
        year = year.replace('年','')
    else:
        year = "以官网信息为准"
    
    if '上市月份' in date_list:
        index = date_list.index('上市月份')
        month = date_list[index+1]
        month = month.replace('月','')
        if month == '以官网信息为准':
            month = '1'
    
    if year == "以官网信息为准":
        date = year
    else:
        date = year + '.' + month
    
    other = '上市时间:%s'%(date)
    #print(other)

    sql = "INSERT INTO phone (brand,product_name,performance,interfaces,front_camera,rear_camera,photo_features,body,communication,endurance,screen,other,price,url,tag1,tag2,tag3,appearance1,appearance2,appearance3,product_type,library,product_analysis) VALUES ('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}','{10}','{11}','{12}','{13}','{14}','{15}','{16}','{17}','{18}','{19}','{20}','{21}','{22}')".format(brand,product_name,performance,interface,front_camera,rear_camera,photo_features,body,communicaiton,endurance,screen,other,phone[13],phone[14],phone[15],phone[16],phone[17],phone[18],phone[19],phone[20],phone[21],phone[21],phone[22])
    cursor.execute(sql)
    db.commit()
    
#%%
#数据清洗computer
sql = 'SELECT * FROM computer'
cursor.execute(sql)
rows = cursor.fetchall() #fetchall()
#rows = cursor.fetchmany(2)
#清空表
cursor.execute('TRUNCATE TABLE computer')
db.commit()

skip = ['nan']
for row in rows:
    #brand
    brand = row[1].strip()
    brand = delete_brackets(brand)
    brand = brand.replace(':',' ')
    brand = brand.replace('：',' ')
    brand = brand.replace("'",'')
    brand_list = brand.split(' ')
    if '系列' not in brand_list:
        continue
    if '型号' not in brand_list:
        continue
    if '其他' in brand_list:
        continue
    if '其它' in brand_list:
        continue

    index1 = brand_list.index('系列')
    index2 = brand_list.index('型号')
    series = ' '.join(brand_list[index1+1:index2])
    series = series.replace('系列','')
    product_name = ' '.join(brand_list[index2+1:])

    brand = "系列:%s 型号:%s"%(series,product_name)
    #print(brand)

    #con_performance
    # con_performance = row[2].strip()
    # con_performance = con_performance.replace(':', ' ')
    # con_performance = con_performance.replace('：',' ')
    # con_performance = con_performance.replace('核心数','核心')
    # con_list = con_performance.split(' ')

    # if '平台' in con_list:
    #     index = con_list.index('平台')
    #     stage = con_list[index+1]
    #     stage = stage.replace('平台','')
    # else:
    #     stage = '以官网信息为主'

    # if '操作系统' in con_list:
    #     index = con_list.index('操作系统')
    #     if con_list[index+1] == '其它':
    #         ostype = '以官网信息为准'
    #     if con_list[index+1] =='其他':
    #         ostype = '以官网信息为准'
    #     ostype = ' '.join(con_list[index+1:index+3])
    # else:
    #     ostype = '以官网信息为准'
    
    # if 'CPU型号' in con_list:
    #     index = con_list.index('CPU型号')
    #     if con_list[index+1] == '其它':
    #         cpu_type = '以官网信息为准'
    #     if con_list[index+1] == '其他':
    #         cpu_type = '以官网信息为准'
    #     cpu_type = con_list[index+1]
    # else:
    #     cpu_type = '以官网信息为准'

    # if '核心' in con_list:
    #     index = con_list.index('核心')
    #     kernel_num = con_list[index+1]
    # else:
    #     kernel_num = '以官网信息为准'

    # con_performance = '平台:%s 操作系统:%s CPU型号:%s 核心数:%s'%(stage,ostype,cpu_type,kernel_num)
    #print(con_performance)

    #storage
    storage = row[5].strip()
    if storage in skip:
        continue
    
    #sound
    sound = row[6].strip()
    if sound in skip:
        continue

    #internet
    internet = row[10].strip()
    if internet in skip:
        continue

    #screen
    screen = row[11].strip()
    if screen in skip:
        continue

    sql = "INSERT INTO computer (brand, con_performance, game_performance,interfaces,storage,sound,camera,specification,peripheral,internet,screen,endurance,other,price,url,tag1,tag2,tag3,appearance1,appearance2,appearance3,product_type,library,product_analysis) VALUES ('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}','{10}','{11}','{12}','{13}','{14}','{15}','{16}','{17}','{18}','{19}','{20}','{21}','{22}','{23}')".format(brand,row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],row[11],row[12],row[13],row[14],row[15],row[16],row[17],row[18],row[19],row[20],row[21],row[22],row[23],row[24])
    cursor.execute(sql)
    db.commit()
    
#%%
#随机函数
def randstr(num):
    salt = ''.join(random.sample(string.ascii_letters,num))
    return salt
def randdigits(num):
    salt = ''.join(random.sample(string.digits,num))
    return salt
def randtime():
    time1 = (2019,12,1,0,0,0,0,0,0)
    time2 = (2019,12,31,23,59,59,0,0,0)

    start = time.mktime(time1)
    end = time.mktime(time2)

    t = random.randint(start,end)
    date_tuple = time.localtime(t)
    date = time.strftime("%Y-%m-%d",date_tuple)
    return date

def get_computer_name(brand):
    product_name = brand.replace('系列','')
    product_name = product_name.replace(':','')
    product_name = product_name.replace('型号','')
    return product_name

# %%
#user表
professions = ['student','engineer','teacher','doctor','nurse','accountant',
                'actor','actress','astronaut','architect','chef','carpenter']
for i in range(100):
    nick_name = fake.name()
    password = 123456
    phone = fake.phone_number()
    user_email = fake.free_email()
    age = random.randint(10,80)  #随机10-80
    #profession = professions[random.randint(0,11)] #随机0-11
    profession = fake.job()
    tag1 = 'tag1',
    tag2 = 'tag2',
    tag3 = 'tag3',
    used = 'tag4',
    is_ban = random.randint(0,1)#0或1

    sql = "INSERT INTO user (nick_name,password,phone,user_email,age,profession,tag1,tag2,tag3,used,is_ban) VALUES ('%s','%s','%s','%s','%s','%s','tag1','tag2','tag3','test','%s')"%(nick_name,password,phone,user_email,age,profession,is_ban)
    #print(sql)
    cursor.execute(sql)
    db.commit()

#%%
#user_like表
for i in range(1000):
    user_id = random.randint(1,100) #1-100

    #手机或电脑
    product_type = random.randint(0,include_computer) #0或1
    if product_type == 0:
        product_id  = random.randint(1,phone_num) #0:1-225,1:1-223
    else:
        product_id = random.randint(1,computer_num)

    like_time = randtime() #格式：2019-11-22 22:02:22 

    sql = "INSERT INTO user_like (user_id,product_id,product_type,like_time) VALUES ('%s','%s','%s','%s')"%(user_id,product_id,product_type,like_time)
    cursor.execute(sql)
    db.commit()

#%%
#collect表
for i in range(400):
    user_id = random.randint(0,100) #0-100
    product_type = random.randint(0,include_computer) #0或1
    if product_type == 0:
        product_id  = random.randint(1,phone_num) #0:1-225,1:1-223
    else:
        product_id = random.randint(1,computer_num)

    sql = "INSERT INTO collect (user_id,product_id,product_type) VALUES ('%s','%s','%s')"%(user_id,product_id,product_type)
    cursor.execute(sql)
    db.commit()

#%%
#record
for i in range(10000):
    user_id = random.randint(0,100) #0-100
    product_type = random.randint(0,include_computer) #0或1
    if product_type == 0:
        product_id = random.randint(1,phone_num)
        sql = 'SELECT product_name FROM phone WHERE product_id=%d'%(product_id)
    else:
        product_id = random.randint(1,computer_num)
        sql = 'SELECT brand FROM computer WHERE product_id=%d'%(product_id)

    cursor.execute(sql)
    result = cursor.fetchone()
    product_name = result[0] #SELECT
    if product_type == 1:
        #从brand中提取出name
        product_name = get_computer_name(product_name)

    #防止过长
    if len(product_name)>30:
        product_name = product_name[:25]

    product_picture = ' '
    browse_time = randtime() #格式：2019-11-22 22:02:22 

    sql = "INSERT INTO record (user_id,product_id,product_type,product_name,product_picture,browse_time) VALUES ('%s','%s','%s','%s','%s','%s')"%(user_id,product_id,product_type,product_name,product_picture,browse_time)
    try:
        cursor.execute(sql)
        db.commit()
    except:
        print(sql)
        db.close()

#%%
#comment表
for i in range(1000):
    user_id = random.randint(0,100) #0-100
    product_type = random.randint(0,include_computer) #0或1
    if product_type == 0:
        product_id = random.randint(1,phone_num)
        sql = 'SELECT product_name FROM phone WHERE product_id=%d'%(product_id)
    else:
        product_id = random.randint(1,computer_num)
        sql = 'SELECT brand FROM computer WHERE product_id=%d'%(product_id)

    cursor.execute(sql)
    result = cursor.fetchone()
    product_name = result[0]  #SELECT
    if product_type == 1:
        #从brand中提取出name
        product_name = get_computer_name(product_name)

    #防止过长
    if len(product_name)>30:
        product_name = product_name[:25]

    content = fake.paragraph(nb_sentences=3, variable_nb_sentences=True) #随机一段评论
    like_num = '0'
    date =randtime() #格式：2019-11-22 22:02:22 
    sql = "INSERT INTO comment (user_id,product_id,product_type,product_name,content,like_num,date) VALUES ('%s','%s','%s','%s','%s','%s','%s')"%(user_id,product_id,product_type,product_name,content,like_num,date) 
    try:
        cursor.execute(sql)
        db.commit()
    except:
        print("too long:",product_name)
        db.close()

#%%
#comment_like表
for i in range(5000):
    user_id = random.randint(0,100) #0-100
    comment_id = random.randint(0,1000) #0-1000
    like_time =randtime() #时间
    sql = "INSERT INTO comment_like (user_id,comment_id,like_time) VALUES ('%s','%s','%s')"%(user_id,comment_id,like_time)
    cursor.execute(sql)
    db.commit()

#统计Commentlike，设置Comment的like_num
sql = 'SELECT comment_id,count(*) FROM comment_like GROUP BY comment_id'
cursor.execute(sql)
results = cursor.fetchall()
for row in results:
    sql = 'UPDATE comment SET like_num=%s WHERE comment_id=%s'%(row[1],row[0])
    cursor.execute(sql)
    db.commit()

#%%
#product_like表
#统计user_like
sql = 'SELECT product_id,product_type,count(*) FROM user_like GROUP BY product_id'
cursor.execute(sql)
results = cursor.fetchall()
for row in results:
    product_id = row[0]
    product_type = row[1]
    like_num = row[2]

    sql = "INSERT INTO product_like (product_id,product_type,like_num) VALUES ('%s','%s','%s')"%(product_id,product_type,like_num)
    cursor.execute(sql)
    db.commit()

#%%
db.close()
