服务器的一些源docker compose 文件



```
服务名	镜像	端口
nginx	nginx	80
alist	alist	5244
memos	memos	5230
```


halo服务器的一些源docker compose 文件




## memos

docker run -d --name memos -p 5230:5230 -v /root/memos/:/var/opt/memos neosmemo/memos:latest

## mysql


```
mkdir -p /data/mysql/data
chmod 777 /data/mysql/data
cd /data/mysql/data
```


```
docker run -d -p 3306:3306 \
--name mysql --privileged=true \
-v /data/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=XXXXX \
mysql
```












## leanote
1改为git 维护
2有点老 废弃

### leanote1

```
docker pull axboy/leanote
mkdir -p /leanote/{db,files,conf,public/upload}



/leanote/db                # 内置mongodb的数据目录，nodb版无此目录
/leanote/conf      # 笔记的配置文件目录
/leanote/files     # 笔记内上传的图片、文件存放目录
/leanote/public/upload     # 头像上传路径


docker run -d --name leanote \
    -v `pwd`/db:/leanote/db \
    -v `pwd`/conf/:/leanote/conf \
    -v `pwd`/files:/leanote/files \
    -v `pwd`/upload:/leanote/public/upload \
    -p 9000:9000 \
    axboy/leanote

```
位置在 /leanote


### leanote2
```
mkdir -p /leanote2/{db,files,conf,public/upload}

docker run -d --name leanote2 \
    -v `pwd`/db:/leanote/db \
    -v `pwd`/conf/:/leanote/conf \
    -v `pwd`/files:/leanote/files \
    -v `pwd`/upload:/leanote/public/upload \
    -p 9001:9000 \
    axboy/leanote
位置在 /leanote2

```

### 备份和还原

#### 还原db
```
复制db文件夹至容器内
docker cp /root/Leanote_data220717/ leanote:/Leanote_data220717

#进入宿主机
docker exec -it leanote /bin/sh
#还原db
mongorestore -h localhost -d leanote --dir /Leanote_data220717/  --drop
#这块如果报错，例如找不到文件，那是因为Leanote_data220717文件下还有一层leanote
#这个文件夹才是db外层文件夹，所以在复制文件到容器的时候直接复制该文件夹
docker cp /leanote231119/leanote leanote:/leanote231119

```

#### 备份db
```
#进入容器
docker exec -it leanote /bin/sh
#备份db
mongodump -h localhost -d leanote -o /leanote220718
#退出容器，在docker中运行以下命令，将备份copy到宿主机
docker cp leanote:leanote220718 /leanote220718
```




## halo
资源消耗过大 废弃

```
version: "3"
----------
services:
halo:
image: registry.fit2cloud.com/halo/halo:2.20
restart: on-failure:3
depends_on:
halodb:
condition: service_healthy
networks:
halo_network:
volumes:
- ./halo2:/root/.halo2
ports:
- "8090:8090"
healthcheck:
test: ["CMD", "curl", "-f", "http://localhost:8090/actuator/health/readiness"]
interval: 30s
timeout: 5s
retries: 5
start_period: 30s
command:
- --spring.r2dbc.url=r2dbc:pool:mysql://halodb:3306/halo
- --spring.r2dbc.username=root
# MySQL 的密码，请保证与下方 MYSQL_ROOT_PASSWORD 的变量值一致。
- --spring.r2dbc.password=XXXXX
- --spring.sql.init.platform=mysql
# 外部访问地址，请根据实际需要修改
- --halo.external-url=http://106.12.212.156:8090/


halodb:
image: mysql:8.1.0
restart: on-failure:3
ports:
- "3306:3306"
networks:
halo_network:
command: 
- --default-authentication-plugin=caching_sha2_password
- --character-set-server=utf8mb4
- --collation-server=utf8mb4_general_ci
- --explicit_defaults_for_timestamp=true
volumes:
- ./mysql:/var/lib/mysql
- ./mysqlBackup:/data/mysqlBackup
healthcheck:
test: ["CMD", "mysqladmin", "ping", "-h", "127.0.0.1", "--silent"]
interval: 3s
retries: 5
start_period: 30s
environment:
# 请修改此密码，并对应修改上方 Halo 服务的 SPRING_R2DBC_PASSWORD 变量值
- MYSQL_ROOT_PASSWORD=XXXXX
- MYSQL_DATABASE=halo

networks:
halo_network:
```



## alist

```
version: '3.3'
services:
    alist:
        image: 'xhofe/alist:latest'
        container_name: alist
        volumes:
            - '/etc/alist:/opt/alist/data'
        ports:
            - '5244:5244'
        environment:
            - PUID=0
            - PGID=0
            - UMASK=022
        restart: unless-stopped

```
docker exec -it alist mkdir /root/alistdata


## trilium1
对网络依赖太大 已经废弃

### trilium1
```
version: '3'
services:
  trilium-cn:
    image: nriver/trilium-cn
    restart: always
    ports:
      - "8080:8080"
    volumes:
      # 把同文件夹下的 trilium-data 目录映射到容器内
      - ./trilium-data:/root/trilium-data
    environment:
      # 环境变量表示容器内笔记数据的存储路径
      - TRILIUM_DATA_DIR=/root/trilium-data
    # 可选功能: 健康检查。需要启用的话，把下面的注释去掉
    #healthcheck:
    #  test: ["CMD-SHELL", "curl --fail http://localhost:8080/api/health-check | jq -e '.status == "ok"' || exit 1"]
    #  interval: 30s
    #  timeout: 10s
    #  retries: 3

```

Database has been backed up to /root/trilium-data/backup/backup-now.db



### trilium2
 
```
version: '3'
services:
  trilium-cn_2:
    image: nriver/trilium-cn
    restart: always
    ports:
      - "8081:8080"
    volumes:
      # 把同文件夹下的 trilium-data 目录映射到容器内
      - ./trilium-data:/root/trilium-data
    environment:
      # 环境变量表示容器内笔记数据的存储路径
      - TRILIUM_DATA_DIR=/root/trilium-data
```


## 主页
```
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>欢迎访问我的网站</title>
</head>
<body>
<h1>欢迎来到我的网站</h1>
<p>请点击下面的链接访问其他页面：</p>
<ul>
    <li><a href="http://106.12.212.156:8080">csnote</a></li>
    <li><a href="http://106.12.212.156:8081">learn</a></li>
    <li><a href="http://106.12.212.156:8090">blog</a></li>
    <li><a href="http://106.12.212.156:8090/uc/posts">blog后台</a></li>
    <li><a href="http://106.12.212.156:5244">alist</a></li>
</ul>
</body>
</html>
```




## nginx
```
version: '3'
services:
  nginx:
    image: registry.cn-hangzhou.aliyuncs.com/zhengqing/nginx:1.21.1                 # 镜像`nginx:1.21.1`
    container_name: nginx               # 容器名为'nginx'
    restart: unless-stopped                                       # 指定容器退出后的重启策略为始终重启，但是不考虑在Docker守护进程启动时就已经停止了的容器
    volumes:                            # 数据卷挂载路径设置,将本机目录映射到容器目录
      - "./html:/usr/share/nginx/html"
    environment:                        # 设置环境变量,相当于docker run命令中的-e
      TZ: Asia/Shanghai
      LANG: en_US.UTF-8
    ports:                              # 映射端口
      - "80:80"
```






