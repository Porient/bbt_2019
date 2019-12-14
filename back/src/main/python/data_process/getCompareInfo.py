#coding:utf-8
#%%
import sys
import pymysql
import math
import comment_cloud
import  collections
# db = pymysql.connect('localhost','root','123456','bbt')
# cursor = db.cursor()
#只针对phone

#%%
#基本信息
def getBasicInfo(product_id):
    db = pymysql.connect('localhost','root','7887227','bbt')
    cursor = db.cursor()
    basic_info = {}
    sql = "SELECT * FROM phone WHERE product_id=%s"%(product_id)
    cursor.execute(sql)
    result = cursor.fetchone() 
    if result == None:
        return "产品id不存在"

    basic_info['id'] = result[0]
    basic_info['brand'] = result[1]
    basic_info['name'] = result[1]+result[2]

    #performance
    basic_info['performance']={}
    performance = result[3].replace(':',' ')
    performance = performance.split(' ')

    index = performance.index('CPU型号')
    basic_info['performance']['cpu_type'] = performance[index+1]

    index = performance.index('运行内存(GB)')
    basic_info['performance']['running_memory'] = performance[index+1].replace('以下','')
    index = performance.index('操作系统')
    basic_info['performance']['os_type'] = performance[index+1]

    index = performance.index('操作系统版本')
    basic_info['performance']['os_version'] = performance[index]

    #interfaces
    basic_info['interfaces'] = {}
    interfaces = result[4].strip()
    interfaces = interfaces.replace(':',' ')
    interfaces = interfaces.split(' ')
    
    index = interfaces.index('数据传输接口')
    basic_info['interfaces']['data_interface'] = interfaces[index+1]

    index = interfaces.index('耳机接口类型')
    basic_info['interfaces']['earphone_interface'] = interfaces[index+1]

    index = interfaces.index('NFC/NFC模式')
    basic_info['interfaces']['nfc_support'] = interfaces[index+1]

    #front_camera
    basic_info['front_camera'] = {}
    front_camera = result[5].replace(':',' ')
    front_camera = front_camera.split(' ')

    index = front_camera.index('光圈')
    basic_info['front_camera']['circle'] = front_camera[index+1]

    index = front_camera.index('像素(万)')
    basic_info['front_camera']['pixel'] = front_camera[index+1]

    #rear_camera
    basic_info['rear_camera'] = {}
    rear_camera = result[6].replace(':',' ')
    rear_camera = rear_camera.split(' ')

    index = rear_camera.index('光圈')
    basic_info['rear_camera']['circle'] = rear_camera[index+1]

    index = rear_camera.index('像素(万)')
    basic_info['rear_camera']['pixel'] = rear_camera[index+1]

    #photo_features
    basic_info['photo_features'] = result[7].split(';')

    #body
    basic_info['body'] = {}
    body = result[8].replace(':',' ')
    body = body.split(' ')

    index = body.index('机身颜色')
    basic_info['body']['color'] = body[index+1]

    index = body.index('机身长度(mm)')
    basic_info['body']['length'] = body[index+1]

    index = body.index('机身重量(g)')
    basic_info['body']['weight'] = body[index+1]

    index = body.index('机身材质工艺')
    basic_info['body']['handcraft'] = body[index+1]

    index = body.index('机身宽度(mm)')
    basic_info['body']['width'] = body[index+1]

    index = body.index('机身材质分类')
    basic_info['body']['material'] = body[index+1]

    index = body.index('机身厚度(mm)')
    basic_info['body']['thick'] = body[index+1]

    index = body.index('机身存储(GB)')
    basic_info['body']['memory'] = body[index+1]

    #communication
    basic_info['communication'] = {}
    communication = result[9].replace(':',' ')
    communication = communication.split(' ')

    index = communication.index('存储卡')
    basic_info['communication']['memory_card'] = communication[index+1]

    index = communication.index('最大支持SIM卡数量')
    basic_info['communication']['card_num'] = communication[index+1]

    index = communication.index('双卡机类型')
    basic_info['communication']['card_type'] = communication[index+1]

    #endurance
    basic_info['endurance'] = {}
    endurance = result[10].replace(':',' ')
    endurance = endurance.split(' ')

    index = endurance.index('充电器')
    basic_info['endurance']['power'] = endurance[index+1]

    index = endurance.index('电池容量(mAh)')
    basic_info['endurance']['power_capacity'] = endurance[index+1]

    index = endurance.index('充电接口类型')
    basic_info['endurance']['power_type'] = endurance[index+1]

    #screen
    basic_info['screen'] = {}
    screen = result[11].replace(':',' ')
    screen = screen.split(' ')

    index = screen.index('分辨率')
    basic_info['screen']['dpi'] = screen[index+1]

    index = screen.index('屏幕材质类型')
    basic_info['screen']['screen_type'] = screen[index+1]

    index = screen.index('屏幕比例')
    if screen[index+1] == '以官网信息为准':
        basic_info['screen']['propotion'] = screen[index+1]
    else:
        basic_info['screen']['propotion'] = screen[index+1]+':'+screen[index+2]
    
    index = screen.index('主屏幕尺寸')
    basic_info['screen']['screen_size'] = screen[index+1]
    basic_info['screen']['screen_size'] = basic_info['screen']['screen_size'].replace('英寸','')

    #其他
    date = result[12].replace(':',' ')
    date = date.split(' ')
    basic_info['date'] = date[1]

    basic_info['price'] = result[13]
    basic_info['url'] = result[14]
    basic_info['tag1'] = result[15]
    basic_info['tag2'] = result[16]
    basic_info['tag3'] = result[17]
    basic_info['appearance1'] = result[18]
    basic_info['appearance2'] = result[19]
    basic_info['appearance3'] = result[20]

    basic_info['type'] = result[21]
    basic_info['library'] = result[22]
    basic_info['analysis'] = result[23]

    db.close()
    return basic_info

# %%
#维度比较信息
def getCompareInfo(product_id):
    basic_info = getBasicInfo(product_id)
    if basic_info == "产品id不存在":
        return "产品id不存在"

    db = pymysql.connect('localhost','root','7887227','bbt')
    cursor = db.cursor()
    compare_info = {}
    #收藏数、点赞数、浏览次数在全部手机中的情况
    sql = 'SELECT count(*) from phone'
    cursor.execute(sql)
    phone_num = cursor.fetchone()
    phone_num = phone_num[0] #手机数

    sql = "SELECT a.product_id,a.collect_num,COUNT(b.collect_num) AS collect_rank \
            FROM (SELECT product_id,COUNT(*) AS collect_num FROM collect WHERE product_type=0 GROUP BY product_id) AS a,(SELECT product_id,COUNT(*) AS collect_num FROM collect WHERE product_type=0 \
            GROUP BY product_id) AS b \
            WHERE a.collect_num <= b.collect_num And a.product_id=%s \
            GROUP BY a.product_id \
            ORDER BY a.product_id"%(product_id)
    cursor.execute(sql)
    collect_rank = cursor.fetchone()
    if collect_rank == None:
        collect_rank = phone_num
    else:
        collect_rank = collect_rank[2]
    compare_info['collect_rank'] = collect_rank
    #print(collect_rank)

    sql = "SELECT a.product_id,a.like_num,COUNT(b.like_num) AS like_rank \
            FROM (SELECT product_id,like_num FROM product_like WHERE product_type=0) AS a,(SELECT product_id,like_num FROM product_like WHERE product_type=0 ) AS b \
            WHERE a.like_num <= b.like_num AND a.product_id=%s \
            GROUP BY a.product_id \
            ORDER BY like_rank"%(product_id)
    cursor.execute(sql)
    like_rank = cursor.fetchone()
    if like_rank == None:
        like_rank = phone_num
    else:
        like_rank = like_rank[2]
    compare_info['like_rank'] = like_rank
    #print(like_rank)

    sql = "SELECT a.product_id,a.browse_num,COUNT(b.browse_num) AS browse_rank \
            FROM (SELECT product_id,COUNT(*) AS browse_num FROM record WHERE product_type=0 GROUP BY product_id) AS a,(SELECT product_id,COUNT(*) AS browse_num FROM record WHERE product_type=0 \
            GROUP BY product_id) AS b \
            WHERE a.browse_num <= b.browse_num AND a.product_id=%s \
            GROUP BY a.product_id \
            ORDER BY browse_rank"%(product_id)
    cursor.execute(sql)
    browse_rank = cursor.fetchone()
    if browse_rank == None:
        browse_rank = phone_num
    else:
        browse_rank = browse_rank[2]
    compare_info['browse_rank'] = browse_rank
    #print(browse_rank)

    #获取基本信息并比较
    sql = 'SELECT performance,body,price from phone'
    cursor.execute(sql)
    info_list = cursor.fetchall()
    
    #运行内存比较
    running_memory = basic_info['performance']['running_memory']
    if running_memory == '以官网信息为准':
        running_memory_rank = phone_num
    else:
        running_memory = int(running_memory)
        running_memory_rank = phone_num
        for row in info_list:
            performance = row[0]
            performance = performance.replace(':',' ')
            performance = performance.split(' ')
            index = performance.index('运行内存(GB)')
            if performance[index+1] == '以官网信息为准':
                running_memory_rank = running_memory_rank - 1
            else:
                rm = int(performance[index+1])
                if rm < running_memory:
                    running_memory_rank = running_memory_rank - 1
    compare_info['running_memory_rank'] = running_memory_rank
    #print(running_memory_rank)

    length = basic_info['body']['length']
    weight = basic_info['body']['weight']
    width = basic_info['body']['width']
    thick = basic_info['body']['thick']
    memory = basic_info['body']['memory']
    length_rank = phone_num
    weight_rank = phone_num
    width_rank = phone_num
    thick_rank = phone_num
    memory_rank = phone_num
    for row in info_list:
        body = row[1]
        body = body.replace(':',' ')
        body = body.replace('备注','')
        body = body.split(' ')
        #机身长度比较
        
        if length == '以官网信息为准':
            length_rank = phone_num
        else:
            length = float(length)
            index = body.index('机身长度(mm)')
            if body[index+1] == '以官网信息为准':
                length_rank = length_rank - 1
            else:
                l = float(body[index+1])
                if l < length:
                    length_rank = length_rank - 1

        #机身重量比较
        
        if weight == '以官网信息为准':
            weight_rank = phone_num
        else:
            weight = float(weight)
            index = body.index('机身重量(g)')
            if body[index+1] == '以官网信息为准':
                weight_rank = weight_rank - 1
            else:
                l = float(body[index+1])
                if l < weight:
                    weight_rank = weight_rank - 1

        #机身宽度比较
        
        if width == '以官网信息为准':
            width_rank = phone_num
        else:
            width = float(width)
            index = body.index('机身宽度(mm)')
            if body[index+1] == '以官网信息为准':
                width_rank = width_rank - 1
            else:
                l = float(body[index+1])
                if l < width:
                    width_rank = width_rank - 1

        #机身厚度比较
        
        if thick == '以官网信息为准':
            thick_rank = phone_num
        else:
            thick = float(thick)
            index = body.index('机身厚度(mm)')
            if body[index+1] == '以官网信息为准':
                thick_rank = thick_rank - 1
            else:
                l = float(body[index+1])
                if l < thick:
                    thick_rank = thick_rank - 1

        #机身存储比较
        
        if memory == '以官网信息为准':
            memory_rank = phone_num
        else:
            memory = float(memory)
            index = body.index('机身存储(GB)')
            if body[index+1] == '以官网信息为准':
                memory_rank = memory_rank - 1
            else:
                l = float(body[index+1])
                if l < memory:
                    memory_rank = memory_rank - 1

    compare_info['length_rank'] = length_rank
    compare_info['weight_rank'] = weight_rank
    compare_info['width_rank'] = width_rank
    compare_info['thick_rank'] = thick_rank
    compare_info['memory_rank'] =memory_rank

    #价格比较
    price = basic_info['price']
    price = int(price)
    price_rank = phone_num
    for row in info_list:
        pc = float(row[2])
        if pc < price:
            price_rank = price_rank - 1
    compare_info['price_rank'] = price_rank

    db.close()
    return compare_info

#%%

if __name__ == "__main__":
    product_id = sys.argv[1]
    print(getCompareInfo(product_id))