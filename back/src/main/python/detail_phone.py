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
product_names = []
APIS = []
back_photos = []
front_photos =[]
product_bodys = []
product_photos = []
performances = []
screens =[]
networks = []
endurances = []
others = []
prices = []
map_urls = []

#  获取待爬取的产品url
def get_urls():
    df=pd.read_csv('urls.csv',sep=',',usecols=[0])
    urls = set()
    for url in df['url']:
        urls.add(url)
    return urls

#  正则匹配
def get_regexs():
    regexs = set()
    regexs.add(".*接口.*")
    regexs.add(".*NFC.*")
    regexs.add(".*后摄.*")
    regexs.add(".*CPU.*")
    regexs.add(".*运行内存.*")
    regexs.add(".*机身存储.*")
    regexs.add(".*操作系统.*")
    regexs.add(".*分辨率.*")
    regexs.add(".*屏幕.*")
    regexs.add(".*前摄.*")
    regexs.add(".*品牌.*")
    regexs.add(".*产品名称.*")
    regexs.add(".*机身.*")
    regexs.add(".*卡.*")
    regexs.add(".*网络.*")
    regexs.add(".*电.*")
    regexs.add(".*拍照特点.*")
    return regexs

# 字典映射
def get_map_dict():
    map_dict = {}

    map_dict[".*接口.*"] = "接口"
    map_dict[".*NFC.*"] = "接口"
    map_dict[".*后摄.*"] = "后摄"
    map_dict[".*CPU.*"] = "性能"
    map_dict[".*运行内存.*"] = "性能"
    map_dict[".*机身存储.*"] = "性能"
    map_dict[".*操作系统.*"] = "性能"
    map_dict[".*分辨率.*"] = "屏幕"
    map_dict[".*屏幕.*"] = "屏幕"
    map_dict[".*前摄.*"] = "前摄"
    map_dict[".*品牌.*"] = "品牌"
    map_dict[".*产品名称.*"] = "产品名称"
    map_dict[".*机身.*"] = "机身"
    map_dict[".*卡.*"] = "通信"
    map_dict[".*网络.*"] = "通信"
    map_dict[".*电.*"] = "续航"
    map_dict[".*拍照特点.*"] = "拍照特点"

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
        product_name = ""
        API = ""
        back_photo = ""
        front_photo = ""
        product_body = ""
        product_photo = ""
        performance = ""
        screen = ""
        network = ""
        endurance = ""
        other = ""
        # 保存已出现的字段,避免重复
        contain_tokens = set()

        response = requests.get(url)
        html = response.text
        soup = BeautifulSoup(html,"html.parser")
        divSoup = soup.find("div",attrs={"class","Ptable"})
        if not divSoup is None:
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
                                    if map_dict.get(regex) == "后摄":
                                        back_photo= back_photo + " " + f + ":" + p
                                    if map_dict.get(regex) == "性能":
                                        performance= performance + " " + f + ":" + p
                                    if map_dict.get(regex) == "机身":
                                        product_body= product_body + " " + f + ":" + p
                                    if map_dict.get(regex) == "屏幕":
                                        screen= screen + " " + f + ":" + p
                                    if map_dict.get(regex) == "前摄":
                                        front_photo= front_photo + " " + f + ":" + p
                                    if map_dict.get(regex) == "品牌":
                                        brand= brand + " " + p
                                    if map_dict.get(regex) == "产品名称":
                                        product_name= product_name + " " + p
                                    if map_dict.get(regex) == "通信":
                                        network= network + " " + f + ":" + p
                                    if map_dict.get(regex) == "续航":
                                        endurance= endurance + " " + f + ":" + p
                                    if map_dict.get(regex) == "拍照特点":
                                        product_photo= product_photo + " " + p
                                    flag = True
                                    break
                        if flag == False:
                            other = other + " " + f + ":" + p
        
        brands.append(brand)
        product_names.append(product_name)
        performances.append(performance)
        APIS.append(API)
        front_photos.append(front_photo)
        back_photos.append(back_photo)
        product_photos.append(product_photo)
        product_bodys.append(product_body)
        networks.append(network)
        endurances.append(endurance)
        screens.append(screen)
        others.append(other)
        map_urls.append(url)




if __name__ == "__main__":
    urls = get_urls()
    regexs = get_regexs()
    map_dict = get_map_dict()
    for url in urls:
        prices.append(jd_price(url))
    get_detail(urls)
    row_data ={
        "品牌" : brands,
        "京东链接" : map_urls,
        "产品名称" : product_names,
        "价格" : prices,
        "性能" : performances,
        "接口" : APIS,
        "前摄" : front_photos,
        "后摄" : back_photos,
        "拍照特点" : product_photos,
        "机身" : product_bodys,
        "通信" : networks,
        "续航" : endurances,
        "屏幕" : screens,
        "其他" : others
    }
    data = DataFrame(row_data)
    data.to_csv("my_data.csv",encoding="gbk")
    
    