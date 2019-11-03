import pymysql
import pandas as pd

brands =[]
product_names = []
performances = []
interfaces = []
front_cameras = []
rear_cameras = []
photo_features = []
bodys = []
communications= []
endurances = []
screens = []
others = []
urls = []

if __name__ == "__main__":
    df=pd.read_csv('urls.csv',sep=',',usecols=[0])
    urls = df['url']
    df = pd.read_csv("my_data.csv",sep=",",encoding="gbk")
    brands = df["品牌"]
    product_names = df["产品名称"]
    prices = df["价格"]
    performances = df["性能"]
    interfaces = df["接口"]
    front_cameras = df["前摄"]
    rear_cameras = df["后摄"]
    photo_features = df["拍照特点"]
    bodys = df["机身"]
    communications = df["通信"]
    endurances = df["续航"]
    screens = df["屏幕"]
    others = df["其他"]

    df = pd.read_csv("phone_pics.csv",sep=",",encoding="utf-8")
    pic_1 = df["pic_1"]
    pic_2 = df["pic_2"]
    pic_3 = df["pic_3"]

    # 打开数据库连接
    db = pymysql.connect("localhost","root","1234","bbt" )
    # 使用 cursor() 方法创建一个游标对象 cursor
    cursor = db.cursor()
    for i in range(len(brands)):
        # db_sql = "insert into phone(" + "brand," + "product_name," + "performance," + 
        # "interfaces,"  + "front_camera," + "rear_camera," + "photo_features," + "body," + "communication," + 
        # "endurance," + "screen," + "other," + "url," + "price) values(" +  + brands[i]  + "," + performances[i]  + "," 
        # + interfaces[i] + "," + front_cameras[i] + "," + rear_cameras[i] + "," + photo_features[i] + ","+ bodys[i] + "," 
        # + communications[i] + "," + endurances[i] + ","  + screens[i] + "," + others[i] + "," + urls + "," + "'0')"
        # print(db_sql)
        db_sql = "insert into phone(" + "brand,"
        db_sql = db_sql + "product_name," + "performance,"
        db_sql = db_sql + "interfaces,"  + "front_camera," + "rear_camera,"
        db_sql = db_sql  + "photo_features," + "body," + "communication,"
        db_sql = db_sql + "endurance," + "screen," + "other," + "url," 
        db_sql = db_sql + "appearance1," + "appearance2," + "appearance3," + "price) values("
        temp_brand = str(brands[i])
        db_sql = db_sql + '"' + temp_brand + '"' + ","
        temp_name = str(product_names[i])
        db_sql = db_sql + '"' + temp_name + '"' + ","
        db_sql = db_sql + '"' + performances[i] + '"' + ","
        temp_interface = str(interfaces[i])
        db_sql = db_sql + '"' + temp_interface + '"' + ","
        temp_front = str(front_cameras[i])
        db_sql = db_sql + '"' + temp_front + '"' + ","
        temp_rear = str(rear_cameras[i])
        db_sql = db_sql + '"' + temp_rear + '"' + ","
        temp_feature = str(photo_features[i])
        db_sql = db_sql + '"' + temp_feature + '"' + ","
        temp = str(bodys[i])
        db_sql = db_sql + '"' + temp + '"' + ","
        db_sql = db_sql + '"' + communications[i] + '"' + ","
        db_sql = db_sql + '"' + endurances[i] + '"' + ","
        db_sql = db_sql + '"' + screens[i] + '"' + ","
        db_sql = db_sql + '"' + others[i] + '"' + ","
        db_sql = db_sql + '"' + urls[i] + '"' + ","
        temp_pic_1 = str(pic_1[i])
        db_sql = db_sql + '"' + temp_pic_1 + '"' + ","
        temp_pic_2 = str(pic_2[i])
        db_sql = db_sql + '"' + temp_pic_2 + '"' + ","
        temp_pic_3 = str(pic_3[i])
        db_sql = db_sql + '"' + temp_pic_3 + '"' + ","
        temp_price = str(prices[i])
        db_sql = db_sql + '"' + temp_price + '"' + ");"
        print(db_sql)
        cursor.execute(db_sql)
        db.commit()
    db.close()