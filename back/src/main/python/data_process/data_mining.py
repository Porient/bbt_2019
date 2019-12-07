#%%
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
    db = pymysql.connect('localhost','root','123456','bbt')
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
#%%
#统计信息
def getStatisticInfo(product_id):
    db = pymysql.connect('localhost','root','123456','bbt')
    cursor = db.cursor()
    statistic_info = {}
    #被收藏次数
    sql = 'SELECT count(*) AS collect_num FROM collect WHERE product_id=%s and product_type=0 GROUP BY product_id'%(product_id)
    cursor.execute(sql)
    result = cursor.fetchone()
    if result == None:
        statistic_info['collect_num'] = 0
    else:
        collect_num = result[0]
        statistic_info['collect_num'] = collect_num

    #被点赞次数
    sql = 'SELECT like_num FROM product_like WHERE product_id=%s and product_type=0'%(product_id)
    cursor.execute(sql)
    result = cursor.fetchone()
    if result == None:
        statistic_info['like_num'] = 0
    else:
        like_num = result[0]
        statistic_info['like_num'] = like_num

    #被浏览次数
    sql = 'SELECT count(*) AS browse_num FROM record WHERE product_id=%s and product_type=0 GROUP BY product_id'%(product_id)
    cursor.execute(sql)
    result = cursor.fetchone()
    if result == None:
        statistic_info['browse_num'] = 0
    else:
        browse_num = result[0]
        statistic_info['browse_num'] = browse_num

    #被评论次数
    sql = 'SELECT count(*) AS review_num FROM `comment` WHERE product_id=%s and product_type=0 GROUP BY product_id'%(product_id)
    cursor.execute(sql)
    result = cursor.fetchone()
    if result == None:
        statistic_info['review_num'] = 0
    else:
        review_num = result[0]
        statistic_info['review_num'] = review_num

    #热度走势 = 某一时间段内浏览次数+点赞次数+评论次数占比图
    daily = {}
    #每日浏览次数
    sql = 'SELECT browse_time, count(*) AS daily_browse_num FROM record WHERE product_id=%s and product_type=0 GROUP BY browse_time'%(product_id)
    cursor.execute(sql)
    results = cursor.fetchall()
    if results != None:
        for result in results:
            date = result[0].strftime('%Y-%m-%d')
            daily_browse_num = result[1]
            if date not in daily:
                daily[date]={}
            daily[date]['browse_num'] = daily_browse_num
    #每日点赞次数
    sql = 'SELECT like_time, count(*) AS daily_like_num FROM user_like WHERE product_id=%s and product_type=0 GROUP BY like_time'%(product_id)
    cursor.execute(sql)
    results = cursor.fetchall()
    if results != None:
        for result in results:
            date = result[0].strftime('%Y-%m-%d')
            daily_like_num = result[1]
            if date not in daily:
                daily[date]={}
            daily[date]['like_num'] = daily_like_num
    #每日评论次数
    sql = 'SELECT date, count(*) AS daily_review_num FROM comment WHERE product_id=%s and product_type=0 GROUP BY date'%(product_id)
    cursor.execute(sql)
    results = cursor.fetchall()
    if results != None:
        for result in results:
            date = result[0].strftime('%Y-%m-%d')
            daily_review_num = result[1]
            if date not in daily:
                daily[date]={}
            daily[date]['review_num'] = daily_review_num
    #补全每个日期的三个key
    for date in daily.keys():
        if 'browse_num' not in daily[date]:
            daily[date]['browse_num'] = 0
        if 'like_num' not in daily[date]:
            daily[date]['like_num'] = 0
        if 'review_num' not in daily[date]:
            daily[date]['review_num'] = 0
    statistic_info['daily'] = daily

    #评论词云图/词频图
    sql = 'SELECT content FROM comment WHERE product_id=%s and product_type=0'%(product_id)
    cursor.execute(sql)
    comment_list = cursor.fetchall()
    if comment_list == None:
        statistic_info['comment_wordcloud_path'] = ''
    else:
        wordcloud_path = comment_cloud.createCommentWordcloud(product_id,comment_list)
        statistic_info['comment_wordcloud_path'] = wordcloud_path

    db.close()
    return statistic_info
# %%
#维度比较信息
def getCompareInfo(product_id):
    basic_info = getBasicInfo(product_id)
    if basic_info == "产品id不存在":
        return "产品id不存在"

    db = pymysql.connect('localhost','root','123456','bbt')
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
#评论信息
def getCommentInfo(product_id):
    db = pymysql.connect('localhost','root','123456','bbt')
    cursor = db.cursor()
    comment_info = []
    sql = 'SELECT comment_id,user_id,content,like_num FROM comment WHERE product_id=%s AND product_type=0'%(product_id)
    print(sql)
    cursor.execute(sql)
    results = cursor.fetchall()
    if results != None:
        for row in results:
            comment_id = row[0]
            user_id = row[1]
            content = row[2]
            like_num = row[3]

            #个人信息
            sql = 'SELECT nick_name,profession FROM user WHERE user_id=%s'%(user_id)
            cursor.execute(sql)
            user = cursor.fetchone()
            nick_name = user[0]
            profession = user[1]

            comment_object = {}
            comment_object['comment_id'] = comment_id
            comment_object['content'] = content
            comment_object['like_num'] = like_num
            comment_object['user'] = {}
            comment_object['user']['id'] = user_id
            comment_object['user']['nick_name'] = nick_name
            comment_object['user']['profession'] = profession

            comment_info.append(comment_object)

    db.close()
    return comment_info


#%%
#挖掘信息
def getMiningInfo(product_id):
    db = pymysql.connect('localhost','root','123456','bbt')
    cursor = db.cursor()
    mining_info = {}
    #用户评论可信度，该用户其余评论的点赞数，说明该用户是专业的
    #找到所有评论该产品的用户id，并计算总被点赞数
    sql = 'SELECT user_id FROM comment WHERE product_id =%s'%(product_id)
    cursor.execute(sql)
    results = cursor.fetchall()
    user_id_list = []
    comment_confidence_list = []
    if results == None:
        mining_info['comment_confidence_list'] = comment_confidence_list
    else:
        #对评论过该产品的每个用户
        for row in results:
            user_id = row[0]
            if user_id not in user_id_list:
                #如果该用户的信息之前没有保存过
                user_id_list.append(user_id)

                #评论被赞总数（置信度）
                sql = "SELECT user_id,COUNT(*) AS liked_time \
                        FROM (SELECT user_id, comment_id FROM comment) as a,(SELECT comment_id,like_time FROM comment_like) as b \
                        WHERE a.comment_id=b.comment_id AND user_id=%s \
                        GROUP BY user_id"%(user_id)
                cursor.execute(sql)
                liked_num = cursor.fetchone()[1]

                #评论次数
                sql = 'SELECT COUNT(*) AS review_num FROM comment WHERE user_id=%s GROUP BY user_id'%(user_id)
                cursor.execute(sql)
                review_num = cursor.fetchone()[0]

                #点赞次数
                sql = 'SELECT COUNT(*) AS like_time FROM comment_like WHERE user_id=%s GROUP BY user_id'%(user_id)
                cursor.execute(sql)
                like_num = cursor.fetchone()[0]

                #收藏次数
                sql = 'SELECT COUNT(*) AS collect_num FROM collect WHERE user_id=%s GROUP BY user_id'%(user_id)
                cursor.execute(sql)
                collect_num = cursor.fetchone()[0]

            comment_confidence = {}
            comment_confidence['user_id'] = user_id
            comment_confidence['liked_num'] = liked_num
            comment_confidence['review_num'] = review_num
            comment_confidence['like_num'] = like_num
            comment_confidence['collect_num'] = collect_num
            comment_confidence_list.append(comment_confidence)

            mining_info['comment_confidence_list'] = comment_confidence_list

    #热度变化：日浏览次数
    # daily_browse = []
    # sql = 'SELECT browse_time,product_id, COUNT(*) AS daily_browse FROM record WHERE product_type=0 AND product_id=%s GROUP BY browse_time,product_id'%(product_id)
    # cursor.execute(sql)
    # timeline = cursor.fetchall()
    # if timeline == None:
    #     mining_info['daily_browse'] = daily_browse
    # else:
    #     for row in timeline:
    #         date = row[0]
    #         daily_browse_num = row[2]

    #         daily_browse_object = {}
    #         daily_browse_object['date'] = date 
    #         daily_browse_object['browse_num'] = daily_browse_num
    #         daily_browse.append(daily_browse_object)
    #     mining_info['daily_browse'] = daily_browse

    #关注该产品用户的职业、年龄分布：收藏、评论、点赞
    profession_list = []
    #收藏*5
    sql = "SELECT a.user_id,a.profession \
            FROM (SELECT user_id, profession FROM user) AS a,(SELECT user_id,product_id FROM collect WHERE product_type=0) as b \
            WHERE a.user_id = b.user_id AND b.product_id=%s \
            GROUP BY a.user_id,b.product_id"%(product_id)
    cursor.execute(sql)
    results = cursor.fetchall()
    collect_profession_list = []
    if results == None:
        mining_info['profession_counts'] = []
        mining_info['profession_cloud_path'] = ''
    else:
        for row in results:
            profession = row[1]
            collect_profession_list.append(profession)
        #点赞*3
        sql = "SELECT a.user_id,a.profession \
                FROM (SELECT user_id, profession FROM user) AS a,(SELECT user_id,product_id FROM user_like WHERE product_type=0) as b \
                WHERE a.user_id = b.user_id AND b.product_id=%s \
                GROUP BY a.user_id,b.product_id"%(product_id)
        cursor.execute(sql)
        results = cursor.fetchall()
        like_profession_list = []
        for row in results:
            profession = row[1]
            like_profession_list.append(profession)
        #浏览*1
        sql = "SELECT a.user_id,a.profession \
                FROM (SELECT user_id, profession FROM user) AS a,(SELECT user_id,product_id FROM record WHERE product_type=0) as b \
                WHERE a.user_id = b.user_id AND b.product_id=%s \
                GROUP BY a.user_id,b.product_id"%(product_id)
        cursor.execute(sql)
        results = cursor.fetchall()
        browse_profession_list = []
        for row in results:
            profession = row[1]
            browse_profession_list.append(profession)
        
        profession_list = collect_profession_list * 5 + like_profession_list * 3 + browse_profession_list
        #统计词频
        profession_counts = collections.Counter(profession_list)
        mining_info['profession_counts'] = profession_counts
        profession_cloud_path = comment_cloud.createProfessionWordcloud(product_id,profession_counts)
        mining_info['profession_cloud_path'] = profession_cloud_path


    #关注该商品的用户与我的相似程度：构造用户向量，计算余弦，归一化，总体相似度

    db.close()
    return mining_info

#%%
