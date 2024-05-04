
# git

## 基本


```
git pull
git push
git config -- global user.name ""
git config -- global user.email 
git init
git status
git add 文件名
git add 文件1 文件2
git add .
git commit -m "备注"
```




## 网络操作

```
git push
git pull

```


## 流程

### 基于http协议

```
git init
git remote remove origin
git remote add origin http://......
git add .
git commit -m "upload"
git push
```


### 基于ssh协议

```
ssh-keygen -t rsa 
（需要安装openssh）
公钥 全部内容
粘贴到github的一个特定位置中
完成
后续操作免密

```


## 如果提交了，但是提交之前没拉取，并且有修改
```仍然可以git pull
内容不会覆盖，git会自动帮我们合并```


## 忽略文件
```*.zip
mdir/
!index 不忽略 ，！ 为非
```



# 高级操作

## 分支操作
```
git branch查看分支
git branch 分支名称 创建分支
git checkout 分支名称 切换分支

(master中) git merge 分支名称 合并分支
git branch -d 分支名称 删除名称
修改提交后继续
```



## 版本回退操作

```
git log
git reset --hard 提交编号
```


### 回退之后，再回到之间的版本的时候
```
git reflog
git reset ---hard 提交编号
```



git rm --cashed 文件名

```
git push --set-upstream origin master

git push: 将本地提交推送到远程仓库。
--set-upstream（或者简写为 -u）: 将本地分支与远程分支进行关联，这样在以后的推送操作中，只需要使用 git push 命令，Git 就会自动将本地分支的更改推送到已关联的远程分支上。
origin: 远程仓库的名称，通常是你的代码托管服务（比如GitHub、GitLab等）给你的默认名称。
master: 远程仓库中的目标分支，这里是指将本地分支推送到远程仓库的主分支（通常是指主开发分支）。






git init
git add README.md
git commit -m "first commit"
git remote add origin git@github.com:firgk/test.git
git push -u origin



git push -u origin

这个命令的作用是将当前分支的本地更改推送到远程仓库，并且将当前分支与远程仓库的同名分支进行关联。具体来说：

git push: 将本地提交推送到远程仓库。
-u（或者完整写作 --set-upstream）: 将当前分支与远程仓库的同名分支进行关联。这样，在以后的推送操作中，只需要使用 git push 命令，Git 就会自动将本地分支的更改推送到已关联的远程分支上。
origin: 远程仓库的名称，通常是你的代码托管服务（如GitHub、GitLab等）给你的默认名称。

















git init
git add README.md
git commit -m "first commit"
git remote add origin git@github.com:firgk/test.git
git branch -M main 社么意思
git push -u origin main



git branch -M main 
这个命令的意思是将当前的分支重命名为 main。具体来说：





```







注意： 

## 如果提交了，但是提交之前没拉取，并且有修改


仍然可以git pull
内容不会覆盖，git会自动帮我们合并











