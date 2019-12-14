#%%
import sys
import pymysql
import math
from abc import ABC, abstractmethod
#import contentbased_module
#import deeplearningbased_module

#%%
class DAL:
    def __init__(self, username, password, basename, host='localhost'):
        self.host = host
        self.username = username
        self.password = password
        self.basename = basename
        self.db = None
    
    def getDB(self):
        if self.db == None:
            self.connectDB()
        return self.db

    def connectDB(self):
        self.db = pymysql.connect(self.host, self.username, self.password, self.basename, charset='utf8')
        #print("DB connect sucessfully")

    def closeDB(self):
        if self.db.open == True:
            self.db.close()
            self.db = None
        #print("DB close sucessfully")


    def selectUserById(self, id):
        userInfo = {}
        #select userInfo by id
        db = self.getDB()
        cursor = db.cursor()
        sql = "SELECT * FROM user WHERE user_id = %s"%(id)
        try:
            #执行sql语句
            cursor.execute(sql)
            #获取所有记录列表
            result = cursor.fetchone()

            userInfo['user_id'] = result[0]
            userInfo['nick_name'] = result[1]
            userInfo['phone'] = result[3]
            userInfo['user_email'] = result[4]
            userInfo['age'] = result[5]
            userInfo['profession'] = result[6]
            userInfo['tag1'] = result[7]
            userInfo['tag2'] = result[8]
            userInfo['tag3'] = result[9]
                
            #print("userInfo:",userInfo)
        except:
            print("Error: unable to fetch userinfo by id")

        #关闭数据库连接
        self.closeDB()

        return userInfo
    
    def getProductDetailBySqlRow(self, row, product_type):
        #将产品信息封装成对象,且进行数据清洗
        product = {}
        if product_type == "computer":
            #数据清洗
            product['id'] = row[0]
            print(id)
            product_name = row[1].replace('系列:','')
            product_name = product_name.replace('型号:','')
            product['name'] = product_name
            product['con_performance'] = row[2]
            product['game_performance'] = row[3]
            product['interfaces'] = row[4]
            product['storage'] = row[5]
            product['sound'] = row[6]
            product['camera'] = row[7]
            product['specification'] = row[8]
            product['peripheral'] = row[9]
            product['internet'] = row[10]
            product['screen'] = row[11]
            product['endurance'] = row[12]
            product['other'] = row[13]
            product['price'] = row[14]
            product['url'] = row[15]
            product['tag1'] = row[16]
            product['tag2'] = row[17]
            product['tag3'] = row[18]
            product['appearance1'] = row[19]
            product['appearance2'] = row[20]
            product['appearance3'] = row[21]
            product['type'] = row[22]
            product['library'] = row[23]
            product['analysis'] = row[24]
        
        if product_type == "phone":
            product['id'] = row[0]
            product['brand'] = row[1]
            product['name'] = row[1]+row[2]

            #performance
            product['performance']={}
            performance = row[3].replace(':',' ')
            performance = performance.split(' ')

            index = performance.index('CPU型号')
            product['performance']['cpu_type'] = performance[index+1]

            index = performance.index('运行内存(GB)')
            product['performance']['running_memory'] = performance[index+1].replace('以下','')

            index = performance.index('操作系统')
            product['performance']['os_type'] = performance[index+1]

            index = performance.index('操作系统版本')
            product['performance']['os_version'] = performance[index]

            #interfaces
            product['interfaces'] = {}
            interfaces = row[4].strip()
            interfaces = interfaces.replace(':',' ')
            interfaces = interfaces.split(' ')
            
            index = interfaces.index('数据传输接口')
            product['interfaces']['data_interface'] = interfaces[index+1]

            index = interfaces.index('耳机接口类型')
            product['interfaces']['earphone_interface'] = interfaces[index+1]

            index = interfaces.index('NFC/NFC模式')
            product['interfaces']['nfc_support'] = interfaces[index+1]

            #front_camera
            product['front_camera'] = {}
            front_camera = row[5].replace(':',' ')
            front_camera = front_camera.split(' ')

            index = front_camera.index('光圈')
            product['front_camera']['circle'] = front_camera[index+1]

            index = front_camera.index('像素(万)')
            product['front_camera']['pixel'] = front_camera[index+1]

            #rear_camera
            product['rear_camera'] = {}
            rear_camera = row[6].replace(':',' ')
            rear_camera = rear_camera.split(' ')

            index = rear_camera.index('光圈')
            product['rear_camera']['circle'] = rear_camera[index+1]

            index = rear_camera.index('像素(万)')
            product['rear_camera']['pixel'] = rear_camera[index+1]

            #photo_features
            product['photo_features'] = row[7].split(';')

            #body
            product['body'] = {}
            body = row[8].replace(':',' ')
            body = body.split(' ')

            index = body.index('机身颜色')
            product['body']['color'] = body[index+1]

            index = body.index('机身长度(mm)')
            product['body']['length'] = body[index+1]

            index = body.index('机身重量(g)')
            product['body']['weight'] = body[index+1]

            index = body.index('机身材质工艺')
            product['body']['handcraft'] = body[index+1]

            index = body.index('机身宽度(mm)')
            product['body']['width'] = body[index+1]

            index = body.index('机身材质分类')
            product['body']['material'] = body[index+1]

            index = body.index('机身厚度(mm)')
            product['body']['thick'] = body[index+1]

            index = body.index('机身存储(GB)')
            product['body']['memory'] = body[index+1]

            #communication
            product['communication'] = {}
            communication = row[9].replace(':',' ')
            communication = communication.split(' ')

            index = communication.index('存储卡')
            product['communication']['memory_card'] = communication[index+1]

            index = communication.index('最大支持SIM卡数量')
            product['communication']['card_num'] = communication[index+1]

            index = communication.index('双卡机类型')
            product['communication']['card_type'] = communication[index+1]

            #endurance
            product['endurance'] = {}
            endurance = row[10].replace(':',' ')
            endurance = endurance.split(' ')

            index = endurance.index('充电器')
            product['endurance']['power'] = endurance[index+1]

            index = endurance.index('电池容量(mAh)')
            product['endurance']['power_capacity'] = endurance[index+1]

            index = endurance.index('充电接口类型')
            product['endurance']['power_type'] = endurance[index+1]

            #screen
            product['screen'] = {}
            screen = row[11].replace(':',' ')
            screen = screen.split(' ')

            index = screen.index('分辨率')
            product['screen']['dpi'] = screen[index+1]

            index = screen.index('屏幕材质类型')
            product['screen']['screen_type'] = screen[index+1]

            index = screen.index('屏幕比例')
            if screen[index+1] == '以官网信息为准':
                product['screen']['propotion'] = screen[index+1]
            else:
                product['screen']['propotion'] = screen[index+1]+':'+screen[index+2]
            
            index = screen.index('主屏幕尺寸')
            product['screen']['screen_size'] = screen[index+1]
            product['screen']['screen_size'] = product['screen']['screen_size'].replace('英寸','')

            #其他
            date = row[12].replace(':',' ')
            date = date.split(' ')
            product['date'] = date[1]

            product['price'] = row[13]
            product['url'] = row[14]
            product['tag1'] = row[15]
            product['tag2'] = row[16]
            product['tag3'] = row[17]
            product['appearance1'] = row[18]
            product['appearance2'] = row[19]
            product['appearance3'] = row[20]

            product['type'] = row[21]
            product['library'] = row[22]
            product['analysis'] = row[23]



        #统计信息
        product['statistic']={}

        return product
    
    def selectBrowseListById(self, id):
        browseList = []
        #select browseList by id
        db = self.getDB()
        cursor = db.cursor()
        sql = "SELECT product_id,product_type FROM record WHERE user_id = %s"%(id)
        # try:
        #执行sql语句
        cursor.execute(sql)
        #获取所有记录列表
        results = cursor.fetchall()
        for row in results:
            product_id = row[0]
            product_type = row[1]
            
            if product_type == '0':
                #computer
                product_type = "computer"
            else:
                #phone
                product_type = "phone"

            sql = "SELECT * FROM %s WHERE product_id  = %s"%(product_type,product_id)
            cursor.execute(sql)
            result = cursor.fetchone()
            product = self.getProductDetailBySqlRow(result, product_type)
            browseList.append(product)
        #print("browseList:",browseList)

        # except:
        #     print("Error: unable to fetch browseList by user_id")

        #关闭数据库连接
        self.closeDB()

        return browseList
    
    def selectCollectionListByID(self, id):
        collectionList = []
        #select collectionList by id
        db = self.getDB()
        cursor = db.cursor()
        sql = "SELECT product_id,product_type FROM collect WHERE user_id = %s"%(id)
        try:
            #执行sql语句
            cursor.execute(sql)
            #获取所有记录列表
            results = cursor.fetchall()
            for row in results:
                product_id = row[0]
                product_type = row[1]
                
                if product_type == '0':
                    #computer
                    product_type = "phone"
                else:
                    #phone
                    product_type = "computer"

                sql = "SELECT * FROM %s WHERE product_id  = %s"%(product_type,product_id)
                cursor.execute(sql)
                result = cursor.fetchone()
                product = self.getProductDetailBySqlRow(result, product_type)
                collectionList.append(product)
            #print("collectionList:",collectionList)

        except:
            print("Error: unable to fetch collectionList by user_id")

        #关闭数据库连接
        self.closeDB()

        return collectionList

    def selectFavoriteListById(self, id):
        favoriteList = []
        #select favoriteList by id
        db = self.getDB()
        cursor = db.cursor()
        sql = "SELECT product_id,product_type FROM user_like WHERE user_id = %s"%(id)
        try:
            #执行sql语句
            cursor.execute(sql)
            #获取所有记录列表
            results = cursor.fetchall()
            for row in results:
                product_id = row[0]
                product_type = row[1]
                
                if product_type == '0':
                    #computer
                    product_type = "phone"
                else:
                    #phone
                    product_type = "computer"

                sql = "SELECT * FROM %s WHERE product_id  = %s"%(product_type,product_id)
                cursor.execute(sql)
                result = cursor.fetchone()
                product = self.getProductDetailBySqlRow(result, product_type)
                favoriteList.append(product)
            #print("favoriteList:",collectionList)

        except:
            print("Error: unable to fetch favoriteList by user_id")

        #关闭数据库连接
        self.closeDB()
        return favoriteList

    def selectIgnoreListById(self, id):
        ignoreList = []
        #删除
        return ignoreList

    def selectTopNPhone(self, n):
        '''
        API : 获取前top n 的手机列表
        返回形式：
            phonelist:{
                likeList = [],
                collectionList = [],
                browseList = []
            }
        '''
        phonelist = {}
        #select phonelist by number n
        if n<3:
            print('Error: n must more than three')
            return None
        else:

            db = self.getDB()
            cursor = db.cursor()
            likeN = math.floor(n * 0.5)
            browseN = 1
            collectN = n-likeN-browseN

            #点赞数50%
            likeList = []
            sql = "SELECT product_id,like_num FROM product_like WHERE product_type = 0 ORDER BY like_num DESC"
            try:
                #执行sql语句
                cursor.execute(sql)
                #获取点赞数列表
                results = cursor.fetchmany(likeN)
                for row in results:
                    product_id = row[0]
                    like_num = row[1]
                    
                    sql = "SELECT * FROM phone WHERE product_id  = %s"%(product_id)
                    cursor.execute(sql)
                    result = cursor.fetchone()
                    product = self.getProductDetailBySqlRow(result, product_type = 'phone')
                    product['statistic']['like_num'] = like_num

                    likeList.append(product)
                    #print("top n phone:",phonelist)
            except:
                print("Error: unable to fetch top n like phone list")
            
            #收藏数
            collectionList = []
            sql = "SELECT product_id, count(*) AS collect_num FROM collect WHERE product_type=0 GROUP BY product_id ORDER BY collect_num DESC"
            try:
                #执行sql语句
                cursor.execute(sql)
                #获取收藏数列表
                results = cursor.fetchmany(collectN)
                for row in results:
                    product_id = row[0]
                    collect_num = row[1] #计算总和

                    sql = "SELECT * FROM phone WHERE product_id = %s"%(product_id)
                    cursor.execute(sql)
                    result = cursor.fetchone()
                    product = self.getProductDetailBySqlRow(result, product_type = 'phone')
                    product['statistic']['collect_num'] = collect_num

                    collectionList.append(product)
            except:
                print("Error: unable to fetch top n collect phone list")

            #浏览次数
            browseList = []
            sql = "SELECT product_id, count(*) AS browse_num FROM record WHERE product_type=0 GROUP BY product_id ORDER BY browse_num DESC"
            try:
                #执行sql语句
                cursor.execute(sql)
                #获取浏览记录数列表
                result = cursor.fetchone()
                product_id = result[0]
                browse_num = row[1] #计算总和

                sql = "SELECT * FROM phone WHERE product_id = %s"%(product_id)
                cursor.execute(sql)
                result = cursor.fetchone()
                product = self.getProductDetailBySqlRow(result, product_type = 'phone')
                product['statistic']['browse_num'] = browse_num

                browseList.append(product)
            except:
                print("Error: unable to fetch top n browse phone list")


            #关闭数据库
            self.closeDB()

            phonelist['like_list'] = likeList
            phonelist['collection_list'] = collectionList
            phonelist['browse_list'] = browseList

            return phonelist


    def selectTopNComputer(self, n):
        '''
        API : 获取前top n 的电脑列表
        返回形式：
            computerlist:{
                like_list = [],
                collection_list = [],
                browse_list = []
            }
        '''
        computerlist = {}
        #select computerlist by number n
        if n<3:
            print('Error: n must more than three')
            return None
        else:

            db = self.getDB()
            cursor = db.cursor()
            likeN = math.floor(n * 0.5)
            browseN = 1
            collectN = n-likeN-browseN
            #print(likeN,browseN,collectN)
            #点赞数50%
            likeList = []
            sql = "SELECT product_id,like_num FROM product_like WHERE product_type=1 ORDER BY like_num DESC"
            try:
                #执行sql语句
                cursor.execute(sql)
                #获取点赞数列表
                results = cursor.fetchmany(likeN)
                for row in results:
                    product_id = row[0]
                    print('product_id:',product_id)
                    like_num = row[1]
                    sql = "SELECT * FROM computer WHERE product_id  = %s"%(product_id)
                    cursor.execute(sql)
                    result = cursor.fetchone()
                    product = self.getProductDetailBySqlRow(result, product_type = 'computer')
                    product['statistic']['like_num'] = like_num

                    likeList.append(product)
            except:
                print("Error: unable to fetch top n like computer list")
            computerlist['like_list'] = likeList
            #print("computer_like_list",likeList)
            
            #收藏数
            collectionList = []
            sql = "SELECT product_id,count(*) AS collect_num FROM collect WHERE product_type=1 GROUP BY product_id ORDER BY collect_num DESC"
            try:
                #执行sql语句
                cursor.execute(sql)
                #获取收藏数列表
                results = cursor.fetchmany(collectN)
                for row in results:
                    product_id = row[0]
                    collect_num = row[1] #计算总和

                    sql = "SELECT * FROM computer WHERE product_id = %s"%(product_id)
                    cursor.execute(sql)
                    result = cursor.fetchone()
                    product = self.getProductDetailBySqlRow(result, product_type = 'computer')
                    product['statistic']['collect_num'] = collect_num

                    collectionList.append(product)
            except:
                print("Error: unable to fetch top n collect computer list")
            computerlist['collection_list'] = collectionList
            #print("computer_collection_list", collectionList)

            #浏览次数
            browseList = []
            sql = "SELECT product_id, count(*) AS browse_num FROM record WHERE product_type=1 GROUP BY product_id ORDER BY browse_num DESC"
            try:
                #执行sql语句
                cursor.execute(sql)
                #获取浏览记录数列表
                results = cursor.fetchone()
                if results != None:
                    product_id = results[0]
                    browse_num = row[1] #计算总和

                    sql = "SELECT * FROM computer WHERE product_id = %s"%(product_id)
                    cursor.execute(sql)
                    result = cursor.fetchone()
                    product = self.getProductDetailBySqlRow(result, product_type = 'computer')
                    product['statistic']['browse_num'] = browse_num
                    
                    browseList.append(product)
            except:
                print("Error: unable to fetch top n browse computer list")
            computerlist['browse_list'] = browseList

            #关闭数据库
            self.closeDB()

            return computerlist

#%%
#Test DAL
# dal = DAL("root","7887227","bbt")
#browse_list = dal.selectBrowseListById(1)
#print('browse_list:',browse_list)
# collection_list = dal.selectCollectionListByID(1)
# print('collection_list:',collection_list)
# favorite_list = dal.selectFavoriteListById(1)
# print('favorite_list:',favorite_list)

# phone_list = dal.selectTopNPhone(10)
# like_list = phone_list['like_list']
# collection_list = phone_list['collection_list']
# browse_list = phone_list['browse_list']
# for i in like_list:
#     print(i)
# for i in collection_list:
#     print(i)
# for i in collection_list:
#     print(i)

# computer_list = dal.selectTopNComputer(10)
# like_list = computer_list['like_list']
# collection_list = computer_list['collection_list']
# browse_list = computer_list['browse_list']
# for i in like_list:
#     print(i)
# for i in collection_list:
#     print(i)
# for i in collection_list:
#     print(i)
    


#%%
class Person:
    def __init__(self, id):
        self.id = id
        #查询mysql，获取用户收藏列表/浏览记录列表/态度列表
        dal = DAL("root","7887227","bbt")
        #dal.connectDB()
        self.Info = dal.selectUserById(id)
        self.browseList = dal.selectBrowseListById(id)
        self.collectionList = dal.selectCollectionListByID(id)
        self.favoriteList = dal.selectFavoriteListById(id)
        #dal.closeDB()

    def getUserRank(self):
        
        userRank = 0 #最低级
        #根据信息判断用户信息量级
        return userRank

class Strategy(ABC):
    @abstractmethod
    def getTopN(self, person, n):
        pass

class HeatBasedStrategy(Strategy):
    '''
    基于产品热度的策略
    '''
    def getTopN(self, person_id, n):
        #partition = 0.5 #初始比例
        #统计用户浏览记录+点赞列表+收藏列表的产品类型比例
        
        dal = DAL("root","7887227","bbt")
        topNPhoneList = dal.selectTopNPhone(n)
        topNComputerList = dal.selectTopNComputer(n)

        top_n_List = {'top_n_phone_list':topNPhoneList,'top_n_computer_list':topNComputerList}
        return top_n_List

# class ContentBasedStrategy(Strategy):
#     '''
#     基于内容相似度的策略，调用ContentBasedModule实现
#     '''
#     def getTopN(self, person_id, n):
#         #数据预处理成module需要的数据形式

#         input = None
#         topNList = contentbased_module.getTopN(input, n)
#         return topNList

# class DeeplearningBasedStrategy(Strategy):
#     '''
#     基于CNN/Word2Vec的策略，调用deeplearningBasedModule实现
#     '''
#     def getTopN(self ,person_id, n):
#         #数据预处理成module需要的数据形式
#         topNList = deeplearningbased_module.getTopN(person_id, n)
#         return topNList



class RecommendSystem:
    def __init__(self, strategyName):
        self.setStrategy(strategyName)

    def setStrategy(self, strategyName):
        if strategyName == "HeatBased":
            self.strategy = HeatBasedStrategy()
        
        # if strategyName == "ContentBased":
        #     self.strategy = ContentBasedStrategy()
        
        # if strategyName == "DeeplearningBased":
        #     self.strategy = DeeplearningBasedStrategy()

    def getTopN(self, person_id, n):
        #person = Person(person_id)

        #根据策略进行top n 获取
        topNList =self.strategy.getTopN(person_id, n)


        return topNList


#%%
def recommendBoth(person_id, n=5, strategy_name="HeatBased"):
    recommend_system = RecommendSystem(strategy_name)
    topNList = recommend_system.getTopN(person_id,n)
    
    # for key,product_list in topNList:
    #     print(key)
    #     for dimension,dimension_list in product_list:
    #         print(dimension)
    #         for item in dimension_list:
    #             print(item)
    # topNPhoneList = topNList['top_n_phone_list']
    # topNComputerList = topNList['top_n_computer_list']

    # like_list = topNPhoneList['like_list']
    # collection_list = topNPhoneList['collection_list']
    # browse_list = topNPhoneList['browse_list']
    # for i in like_list:
    #     print(i)
    # for i in collection_list:
    #     print(i)
    # for i in browse_list:
    #     print(i)

    # like_list = topNComputerList['like_list']
    # collection_list = topNComputerList['collection_list']
    # browse_list = topNComputerList['browse_list']
    # for i in like_list:
    #     print(i)
    # for i in collection_list:
    #     print(i)
    # for i in browse_list:
    #     print(i)
    
    return topNList

#%%

#%%


#%%

if __name__ == "__main__":
    user_id = sys.argv[1]
    print(recommendBoth(user_id))