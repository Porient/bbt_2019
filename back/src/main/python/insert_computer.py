import pymysql
import pandas as pd

if __name__ == "__main__":
    df = pd.read_csv("my_computer_data.csv",sep=",",encoding = "gbk")
    brands = df["品牌型号"]
    urls = df["京东链接"]
    con_performances = df["常规性能"]
    game_performances = df["游戏性能"]
    interfaces = df["接口"]
    storages = df["存储"]
    sounds = df["音效"]
    cameras = df["摄像头"]
    specifications = df["规格"]
    peripherals = df["外设"]
    internets = df["网络"]
    screens = df["屏幕"]
    endurances = df["续航"]
    others = df["其他"]

    df = pd.read_csv("computer_price.csv",sep=",",encoding="utf-8")
    prices = df["价格"]

    df = pd.read_csv("computer_pics.csv",sep=",",encoding="utf-8")
    pic_1 = df["pic_1"]
    pic_2 = df["pic_2"]
    pic_3 = df["pic_3"]

    db = pymysql.connect("localhost","root","1234","bbt")
    cursor = db.cursor()

    for i in range(len(brands)):
        db_sql = "insert into computer(" + "brand,"
        db_sql = db_sql + "con_performance," + "game_performance,"
        db_sql = db_sql + "interfaces," + "storage,"
        db_sql = db_sql + "sound," + "camera,"
        db_sql = db_sql + "specification," + "peripheral,"
        db_sql = db_sql + "internet," + "screen,"
        db_sql = db_sql + "endurance," + "other,"
        db_sql = db_sql + "price," + "url,"
        db_sql = db_sql + "appearance1," + "appearance2,"
        db_sql = db_sql + "appearance3) values("
        temp_brand = str(brands[i])
        db_sql = db_sql + '"' + temp_brand + '"' + ","
        temp_con_performance = str(con_performances[i])
        db_sql = db_sql + '"' + temp_con_performance + '"' + ","
        temp_game_performance = str(game_performances[i])
        db_sql = db_sql + '"' + temp_game_performance + '"' + ","
        temp_interface = str(interfaces[i])
        db_sql = db_sql + '"' + temp_interface + '"' + ","
        temp_storage = str(storages[i])
        db_sql = db_sql + '"' + temp_storage + '"' + ","
        temp_sound = str(sounds[i])
        db_sql = db_sql + '"' + temp_sound + '"' + ","
        temp_camera = str(cameras[i])
        db_sql = db_sql + '"' + temp_camera + '"' + ","
        temp_specification = str(specifications[i])
        db_sql = db_sql + '"' + temp_specification + '"' + ","
        temp_peripheral = str(peripherals[i])
        db_sql = db_sql + '"' + temp_peripheral + '"' + ","
        temp_internet = str(internets[i])
        db_sql = db_sql + '"' + temp_internet + '"' + ","
        temp_screen =str(screens[i])
        db_sql = db_sql + '"' + temp_screen + '"' + ","
        temp_endurance = str(endurances[i])
        db_sql = db_sql + '"' + temp_endurance + '"' + ","
        temp_other = str(others[i])
        db_sql = db_sql + '"' + temp_other + '"' + ","
        temp_price = str(prices[i])
        db_sql = db_sql + '"' + temp_price + '"' + ","
        db_sql = db_sql + '"' + urls[i] + '"' + ","
        temp_pic_1 = str(pic_1[i])
        db_sql = db_sql + '"' + temp_pic_1 + '"' + ","
        temp_pic_2 = str(pic_2[i])
        db_sql = db_sql + '"' + temp_pic_2 + '"' + ","
        temp_pic_3 = str(pic_3[i])
        db_sql = db_sql + '"' + temp_pic_3 + '"' + ");"
        print(db_sql)
        cursor.execute(db_sql)
        db.commit()
    db.close()


