








## 安装

```
yum install -y docker

但是安装的不是新
systemctl start docker
systemctl enable docker

or

一键安装脚本
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
```


Centos7以上,某些老旧版本不支持,建议7.6or7.8




## 配置国内加速

```
vim /etc/docker/daemon.json
添加镜像加速的配置文件
...
重新加载配置文件
systemctl daemon-reload
重启docker服务
systemctl restart docker.service
```




## 基本命令

![](http://leaweihou.site:1003/photobed/docker01.png)

container id 可以只写一部分,可以当作names 使用
docker inspect test01
docker network inspect 
docker volume inspcet 

### docker对于镜像操作

```
docker --help
docker run --help

docker images 查看本地docker镜像
docker rmi 删除镜像
docker run

    参数
    -d 后台运行
    --name  起名字
    -p 端口映射
        第一个为实体机的ip，第二个为虚拟机的ip
    -e 环境配置，每个镜像不同
        在hub.docker.com可以搜索
    镜像名字:版本
        没有指定版本时候，默认为latest，最新版本
    -v 数据卷名：容器内目录 挂载数据卷
        挂载到docker的默认路径
    或者
    -v 本地目录 : 容器内目录 
        挂载到自定义的目录
    --network heima 
        指定自定义的网络
    --link可以通过容器名互相通信，容器间共享环境变量。
```


### 对于容器中

```
docker pull
docker rm 删除容器

docoker start
docker stop

docekr ps 镜像运行状态
docker logs日志
    docker logs -f nginx
        follow 持续查看


docker exit
docker exec 
    docker exec  -it nginx bash
        it的意思是iteractive terminal 可交互的终端
        bash 是交互的方式

    docekr exec -it mysql bash
    docker exec -it mysql mysql -u root -p
    可以直接执行命令

```


## 例子


###  docker配置mysql


```bash
docker run -d \
  --name mysql \
  -p 3306:3306 \
  -e TZ=Asia/Shanghai \
  -e MYSQL_ROOT_PASSWORD=123456 \
  mysql


docker run -d \
    -p 3306:3306 \
    --name mysql-stable \
    --privileged=true \
    --restart=always \
    -e MYSQL_ROOT_PASSWORD=qweQWE123. \
    mysql
```

> 安装的是不支持外网访问的，需要自己配置


### docker 配置nginx

docker hub中搜索nginx
修改代码
执行

EX：
```sh
docker run -d --name nginx_docs -v /root/nginx_docs:/usr/share/nginx/html -v /root/nginx_docs_conf/nginx:/etc/nginx/  -p 4000:80 nginx
```


## 数据卷

映射容器和本地机
在/var/lib/docker/volumes/html/_data 目录下
是固定的

docker volume ls 查看已经创建的卷
docker volume inspect 查看卷的详细信息
docker volume rm
docker volume create test01

1. 绝对路径
    -v 本地目录 : 容器内目录 
        挂载到自定义的目录
2. 别名
    -v 数据卷名：容器内目录 挂载数据卷
        挂载到docker的默认路径




## DockerFile

镜像分层结构

```
FROM	    指定基础镜像	FROM centos:6
ENV	        设置环境变量，可在后面指令使用	ENV key value
COPY	    拷贝本地文件到镜像的指定目录	COPY ./jre11.tar.gz /tmp
RUN	        执行Linux的shell命令，一般是安装过程的命令	RUN tar -zxvf /tmp/jre11.tar.gz&& EXPORTS path=/tmp/jre11:$path
EXPOSE	    指定容器运行时监听的端口，是给镜像使用者看的	EXPOSE 8080
ENTRYPOINT	镜像中应用的启动命令，容器运行时调用	ENTRYPOINT java -jar xx.jar
```

一个nodejs的

```
FROM node:20.10.0
RUN mkdir -p /app
WORKDIR /app
COPY ./todo_web_vue .
EXPOSE 8080
RUN chmod -Rf 777 /app
CMD ["./start.sh"]
```

a.sh

```sh
node server.js &
npm run serve
```

```yml
# 指定基础镜像
FROM ubuntu:16.04
# 配置环境变量，JDK的安装目录、容器内时区
ENV JAVA_DIR=/usr/local
# 拷贝jdk和java项目的包
COPY ./jdk8.tar.gz $JAVA_DIR/
COPY ./docker-demo.jar /tmp/app.jar
# 安装JDK
RUN cd $JAVA_DIR \ && tar -xf ./jdk8.tar.gz \ && mv ./jdk1.8.0_144 ./java8
# 配置环境变量
ENV JAVA_HOME=$JAVA_DIR/java8
ENV PATH=$PATH:$JAVA_HOME/bin
# 入口，java项目的启动命令
ENTRYPOINT ["java", "-jar", "/app.jar"]
```


将其中的一些集成了
更改了基础镜像


```yml
# 基础镜像
FROM openjdk:11.0-jre-buster
# 拷贝jar包
COPY docker-demo.jar /app.jar
# 入口
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### 构建


docker build -t todo_web_vue .
docker build -f ./Dockerfile -t todo_web_vue .

`docker run --name testdockerfiile2 -p 90:8080 app2`


```sh
从dockerfile构建
docker build 
    会生成镜像

保存到本地、从本地加载
docker save
docker load

上传到镜像仓库、从镜像仓库拉取
docker pull
docker push
```
当前目录中的dockerfile，镜像名称



## 自定义网络 实现不容容器内部之间互联

可以使用docker 的网桥,但是只能用ip,部署项目的时候不推荐,建议自定义网桥,不同项目之前不互通


在不同容器之间通信

```yml
docker network create test01
docker network create -d bridge test01 # 显式地指定了网络驱动为桥接模式
docker network connect test01
docker network ls
docker network inspect test01
docker network rm test01
docker network prune # 删除所有没有用到的
```

加入自定义网络额容器可以,通过容器名相互访问


运行多个容器在指定网络之中
1. 启动容器时,明确指定容器使用哪个网路
--network heima 
指定自定义的网络


2. 启动后加入到某个网络
docker network connect test01

 


## dockerCompose

用于一次部署多个容器




## docker servivce

docker service ls
docker service ps
docker service rm vacs_hgdb
docker service logs -f vacs_hgdb






## docker Stack

Docker Stack 是建立在Docker Service之上的一种更高级的抽象。

// 部署到 vacs组织 上
docker stack deploy -c Deploy.yml vacs

docker stack ls
docker stack ps vacs


[](https://www.runoob.com/docker/docker-tutorial.html)








## docker 国内访问不了

从另一台服务器下载
docker save -o image.tar redis/redis-stack-server
拷贝
scp image.tar 114.116.43.154:/root/dockerimages/
加载
docker load -i image.tar


