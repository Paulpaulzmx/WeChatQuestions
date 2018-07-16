# WeChatQuestions
---
## 一、使用方法
1. 进入微信公众平台。
2. 申请测试号扫码登录，网址 https://open.weixin.qq.com。
3. 登陆后自动获取appID和appsecret，将其填入MenuSetting.java中的声明处(每次获取测试号后都不一样）。
4. 设置token，并相应地填入WxServlet.java的token声明处。
5. 利用natapp内网穿透，将本机ip映射到公网上去，将网址http://th3prw.natappfree.cc/wechat/qa.html
填入MenuSetting.java中的json处。
6. 修改测试号的接口配置信息为http://th3prw.natappfree.cc/wechat/wxservlet
中的网址；其中add和delete是用来操纵菜单栏的。
7. 扫码关注测试号，点击趣味答题，即可在线答题
