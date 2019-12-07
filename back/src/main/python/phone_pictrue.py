import re
import urllib.request

regex = "<img alt='.*' src='//(.+?\.jpg)' data-url='.*' width='54' height='54'>"
text  = "<img alt='小米（MI） 小米8青春版 移动联通电信全网通4G 双卡双待 镜面渐变 全面屏拍照游戏智能手机 深空灰 (6G RAM +128G ROM)' src='//img11.360buyimg.com/n5/s54x54_jfs/t30862/314/95544168/108655/9d84178f/5be7c834Nd806eb30.jpg' data-url='jfs/t30862/314/95544168/108655/9d84178f/5be7c834Nd806eb30.jpg' data-img='1' width='54' height='54'>"

pattern = re.compile(regex)

if re.match(pattern,text):
    print("success")

list = re.compile(regex).findall(text)
print(list)