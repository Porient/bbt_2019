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
#评论信息
def getCommentInfo(product_id):
    db = pymysql.connect('localhost','root','7887227','bbt')
    cursor = db.cursor()
    comment_info = []
    sql = 'SELECT comment_id,user_id,content,like_num FROM comment WHERE product_id=%s AND product_type=0'%(product_id)
 #   print(sql)
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

if __name__ == "__main__":
    product_id = sys.argv[1]
    print(getCommentInfo(product_id))