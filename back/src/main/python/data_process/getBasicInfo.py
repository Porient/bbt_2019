import sys
import pymysql

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

if __name__ == "__main__":
    product_id = sys.argv[1]
    print(getBasicInfo(product_id))