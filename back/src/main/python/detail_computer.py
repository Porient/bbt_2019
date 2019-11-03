import pandas as pd
import numpy as np
import re
import requests
from bs4 import BeautifulSoup
from pandas import Series
from pandas import DataFrame
import urllib.request
import json

#  全局变量
brands = []
musics = []
APIS = []
games = []
memorys =[]
product_bodys = []
product_photos = []
performances = []
screens =[]
networks = []
endurances = []
others = []
peripherals = []
map_urls = []
# prices = []

#  获取待爬取的产品url
def get_urls():
    df=pd.read_csv('urls_computer.csv',sep=',',usecols=[0])
    urls = set()
    for url in df['url']:
        urls.add(url)
    return urls

#  正则匹配
def get_regexs():
    regexs =set()
    regexs.add(".*系列.*")
    regexs.add("型号")
    regexs.add(".*USB.*")
    regexs.add(".*平台.*")
    regexs.add(".*操作系统.*")
    regexs.add(".*键盘.*")
    regexs.add(".*触摸.*")
    regexs.add(".*端口.*")
    regexs.add(".*类型.*")
    regexs.add(".*显示芯片.*")
    regexs.add(".*插槽.*")
    regexs.add(".*局域网.*")
    regexs.add(".*缓存.*")
    regexs.add(".*核心.*")
    regexs.add(".*CPU.*")
    regexs.add(".*硬盘.*")
    regexs.add(".*续航.*")
    regexs.add(".*电.*")
    regexs.add(".*摄像.*")
    regexs.add(".*尺寸.*")
    regexs.add(".*净重.*")
    regexs.add(".*屏幕.*")
    regexs.add("物理分辨率")
    regexs.add(".*显示比例.*")
    regexs.add(".*色域.*")
    regexs.add(".*麦克风.*")
    return regexs

# 字典映射
def get_map_dict():
    map_dict = {}

    map_dict[".*屏幕.*"] = "屏幕"
    map_dict["物理分辨率"] = "屏幕"
    map_dict[".*显示比例.*"] = "屏幕"
    map_dict[".*色域.*"] = "屏幕"
    map_dict[".*尺寸.*"] = "规格"
    map_dict[".*净重.*"] = "规格"
    map_dict[".*摄像.*"] = "摄像头"
    map_dict[".*续航.*"] = "续航"
    map_dict[".*电.*"] = "续航"
    map_dict[".*硬盘.*"] = "存储"
    map_dict[".*CPU.*"] = "常规性能"
    map_dict[".*核心.*"] = "常规性能"
    map_dict[".*缓存.*"] = "常规性能"
    map_dict[".*平台.*"] = "常规性能"
    map_dict[".*操作系统.*"] = "常规性能"
    map_dict[".*显示芯片.*"] = "游戏性能"
    map_dict[".*类型.*"] = "游戏性能"
    map_dict[".*局域网.*"] = "网络"
    map_dict[".*插槽.*"] = "接口"
    map_dict[".*端口.*"] = "接口"
    map_dict[".*USB.*"] = "接口"
    map_dict[".*系列.*"] = "品牌型号"
    map_dict["型号"] = "品牌型号"
    map_dict[".*麦克风.*"] = "音效"
    map_dict[".*键盘.*"] = "外设"
    map_dict[".*触摸.*"] = "外设"

    return map_dict

# 获取价格
def jd_price(url):
    sku = url.split('/')[-1].strip(".html")
    price_url = "https://p.3.cn/prices/mgets?skuIds=J_" + sku
    response = urllib.request.urlopen(price_url)
    content = response.read()
    result = json.loads(content)
    record = result[0]
    #print "price:", record['p']
    return record['p']

def get_detail(urls):
    for url in urls:
        # 目标字段集合
        brand = ""
        music = ""
        API = ""
        game = ""
        memory = ""
        product_body = ""
        product_photo = ""
        performance = ""
        screen = ""
        network = ""
        endurance = ""
        peripheral = ""
        other = ""
        # 保存已出现的字段,避免重复
        contain_tokens = set()

        response = requests.get(url)
        html = response.text
        soup = BeautifulSoup(html,"html.parser")
        divSoup = soup.find("div",attrs={"class","Ptable"})
        dls = divSoup.find_all("dl")

        for dl in dls:
            dts = dl.find_all("dt")
            dds = dl.find_all("dd")
            if len(dts) == len(dds):
                for i in range(len(dts)):
                    f = dts[i].getText()
                    p = dds[i].getText()
                    flag = False
                    if f not in contain_tokens:
                        contain_tokens.add(f)
                        for regex in regexs:
                            if re.match(regex,f):
                                #   print(regex)
                                if map_dict.get(regex) != None:
                                    if map_dict.get(regex) == "接口":
                                        API = API + " " + f + ":" +p
                                    if map_dict.get(regex) == "游戏性能":
                                        game= game + " " + f + ":" + p
                                    if map_dict.get(regex) == "常规性能":
                                        performance= performance + " " + f + ":" + p
                                    if map_dict.get(regex) == "规格":
                                        product_body= product_body + " " + f + ":" + p
                                    if map_dict.get(regex) == "屏幕":
                                        screen= screen + " " + f + ":" + p
                                    if map_dict.get(regex) == "存储":
                                        memory= memory + " " + f + ":" + p
                                    if map_dict.get(regex) == "品牌型号":
                                        brand= brand + " " + f + ":" + p
                                    if map_dict.get(regex) == "音效":
                                        music= music + " " + f + ":" + p
                                    if map_dict.get(regex) == "网络":
                                        network= network + " " + f + ":" + p
                                    if map_dict.get(regex) == "续航":
                                        endurance= endurance + " " + f + ":" + p
                                    if map_dict.get(regex) == "摄像头":
                                        product_photo= product_photo + " " + f + ":" + p
                                    if map_dict.get(regex) == "外设":
                                        peripheral= peripheral + " " + f + ":" + p
                                    flag = True
                                    break
                        if flag == False:
                            other = other + " " + f + ":" + p
        
        brands.append(brand)
        musics.append(music)
        performances.append(performance)
        APIS.append(API)
        memorys.append(memory)
        games.append(game)
        product_photos.append(product_photo)
        product_bodys.append(product_body)
        networks.append(network)
        endurances.append(endurance)
        screens.append(screen)
        peripherals.append(peripheral)
        others.append(other)
        map_urls.append(url)




if __name__ == "__main__":
    urls = get_urls()
    regexs = get_regexs()
    map_dict = get_map_dict()
    # for url in urls:
    #     prices.append(jd_price(url))
    get_detail(urls)
    row_data ={
        "品牌型号" : brands,
        "京东链接" : map_urls,
        "常规性能" : performances,
        "游戏性能" : games,
        "接口" : APIS,
        "存储" : memorys,
        "音效" : musics,
        "摄像头" : product_photos,
        "规格" : product_bodys,
        "外设" : peripherals,
        "网络" : networks,
        "续航" : endurances,
        "屏幕" : screens,
        "其他" : others
    }
    data = DataFrame(row_data)
    data.to_csv("my_computer_data.csv",encoding="gbk")