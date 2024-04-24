
# git
## 本地git操作
```
git config -- global user.name ""
git config -- global user.email
git init
git status
git add 文件名
git add 文件1 文件2
git add .
git rm --cashed 文件名
git commit -m "备注"
```

## 版本回退操作

```git log
git reset --hard 提交编号
```


### 回退之后，再回到之间的版本的时候
```git reflog
git reset ---hard 提交编号
```

## 网络操作

```
git push
git pull

获得github授权
.ssh/config
更改url=https://用户名:密码@github.com/用户名/仓库名.git ??? bug
```


## 流程
```
github创建仓库
git clone
.git/config
完成
```


# 基于ssh协议
```
ssh-keygen -t rsa -c 注册邮箱
（需要安装openssh）
找到文件,打开
公钥 全部内容
粘贴到github的一个特定位置中
完成
以后克隆的时候使用ssh克隆
```

## 分支操作
```
git branch查看分支
git branch 分支名称 创建分支
git checkout 分支名称 切换分支

(master中) git merge 分支名称 合并分支
git branch -d 分支名称 删除名称
修改提交后继续
```


## 如果提交了，但是提交之前没拉取，并且有修改
```仍然可以git pull
内容不会覆盖，git会自动帮我们合并```


## 忽略文件
```*.zip
mdir/
!index 不忽略 ，！ 为非
```