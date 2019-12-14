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
#统计信息
def getStatisticInfo(product_id):
    db = pymysql.connect('localhost','root','7887227','bbt')
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

#%%

if __name__ == "__main__":
    product_id = sys.argv[1]
    print(getStatisticInfo(product_id))