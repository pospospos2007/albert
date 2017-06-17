# <a href="http://123.56.164.113:9999/">Albert</a>
my personal website 

# 部署方法（过段时间编写一下详细的部署文档）

首先下载4个服务，不下载也可以正常部署项目，但是一些功能无法使用，https://pan.baidu.com/s/1jH72wDo

1.部署启动 redis3.0.1  windows或者linux版本

2.修改zoo.cfg配置文件，使日志目录和你的环境一致，部署启动 zookeeper-3.4.8  windows或者linux

3.部署启动apache-activemq-5.9.0  windows或者linux

4.删除es目录下的data目录下所有缓存数据，部署启动elasticsearch 2.3.3  windows或者linux，注意yml文件的cluster-name设置为albert-es

5.运行albert.sql文件 (mysql 5.5+) ，在spring-common-xml 修改sql配置

6.jdk1.8，注意lib下的包要引入，项目部署到tomcat8容器启动

7.(附加)近期使用了lombok简化代码，如需在此项目上开发，请在自己的ide上安装lombok，如：eclipse 或myeclipse的话，下载lombok.jar后，当前目录执行java -jar lombok.jar 然后安装提示安装，重启ide即可


# 开发环境和工具
1.SpringMvc

2.Mybatis及其生成工具

3.redis

4.elasticsearch

5.zookeeper

6.activemq

7.bootstrap,canvas,jQuery

8.face++

9.httpClient

10.kinderEdictor

11.mysql 5.5+

12.spring-data-es

13.i18n

14.websocket

15.myeclipse,eclipse,egit,svn1.8,os x 10.12,windows 10,xshell.xftp

16.turing

17.druid连接池

18.twitter api

19.google api

20.shadownsocks,polipo

21.dubbo

22.lombok

23.Guava

