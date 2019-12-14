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
#挖掘信息
def getMiningInfo(product_id):
    db = pymysql.connect('localhost','root','7887227','bbt')
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
        profession_counts = collections.Counter(profession_list)  #error
        profession_counts = dict(profession_counts)
        counts = []
        for key,num in profession_counts.items():
            p = {}
            p['name'] = key
            p['num'] = num
            counts.append(p)
        mining_info['profession_counts'] = counts
        #[{name:'医生', num:11}, {name:'老师', num:20}]

        # profession_cloud_path = comment_cloud.createProfessionWordcloud(product_id,profession_counts)
        # mining_info['profession_cloud_path'] = profession_cloud_path


    #关注该商品的用户与我的相似程度：构造用户向量，计算余弦，归一化，总体相似度

    db.close()
    return mining_info
#%%

if __name__ == "__main__":
    product_id = sys.argv[1]
    print(getMiningInfo(product_id))