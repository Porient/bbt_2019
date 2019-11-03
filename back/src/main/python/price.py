import urllib.request
import json
import pandas as pd

def jd_price(url):
        sku = url.split('/')[-1].strip(".html")
        price_url = "https://p.3.cn/prices/mgets?skuIds=J_" + sku
        response = urllib.request.urlopen(price_url)
        content = response.read()
        result = json.loads(content)
        record = result[0]
        #print "price:", record['p']
        return record['p']  
if __name__=="__main__":
        df=pd.read_csv('my_computer_data.csv',sep=',',encoding='gbk')
        urls = df['京东链接']
        prices = [] 
        for url in urls:
                prices.append(jd_price(url))
                print(jd_price(url))

        row_data = {
                "价格" : prices
        }

        data = pd.DataFrame(row_data)
        data.to_csv("computer_price.csv")