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

if __name__ == "__main__":
    db = pymysql.connect('localhost','root','7887227','bbt')
    cursor = db.cursor()
    
    for product_id in range(213,223):
        #评论词云图/词频图
        sql = 'SELECT content FROM comment WHERE product_id=%s and product_type=0'%(product_id)
        cursor.execute(sql)
        comment_list = cursor.fetchall()
        comment_cloud.createCommentWordcloud(product_id,comment_list)

        mining_info = {}
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

            profession_cloud_path = comment_cloud.createProfessionWordcloud(product_id,profession_counts)
            mining_info['profession_cloud_path'] = profession_cloud_path

    db.close()

    

# %%
