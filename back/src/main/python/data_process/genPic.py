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
    
    for product_id in range(1,223):
        #评论词云图/词频图
        sql = 'SELECT content FROM comment WHERE product_id=%s and product_type=0'%(product_id)
        cursor.execute(sql)
        comment_list = cursor.fetchall()
        comment_cloud.createCommentWordcloud(product_id,comment_list)

    db.close()