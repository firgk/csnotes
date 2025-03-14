


Git 是一种分布式版本控制系统，每个用户都有完整的版本库，可以在本地进行提交、分支、合并等操作，不需要网络连接。
SVN 是一种集中式版本控制系统，所有文件的历史记录都保存在中央服务器上，用户需要通过网络连接来提交和获取历史记录。

## 基础命令
```
git pull
git push
git config -- global user.name "firgk"
git config -- global user.email "1627368483@qq.com"
git init
git status
git add 文件名
git add 文件1 文件2
git add .
git commit -m ""
```

## 配置流程
基于http协议
```
git init
git config user.name ""
git config user.email ""
git remote add origin http://...... (git remote remove origin)
git add .
git commit -m "upload"
git push
```

基于ssh协议
```
ssh-keygen -t rsa
公钥 全部内容 粘贴到github
后续操作免密
```


# 高级操作
如果提交了，但是提交之前没拉取，并且有修改
```
仍然可以git pull
内容不会覆盖，git会自动帮我们合并
```

忽略文件
```
*.zip
mdir/ # 目录
!index 不忽略 ，！ 为非
```

分支操作
```
git branch # 查看分支
git branch 分支名称 # 创建分支
git checkout 分支名称 # 切换分支
git merge 分支名称(master中)  # 合并分支
git branch -d 分支名称 删除名称
```

版本回退操作
```
git log
git reset --hard 提交编号
```


回退之后，再回到之间的版本
```
git reflog
git reset ---hard 提交编号
```

从暂存区中删除文件
git rm --cashed 文件名

将本地分支与远程分支进行关联
`git push --set-upstream origin master`
git push: 将本地提交推送到远程仓库。
--set-upstream（或者简写为 -u）: 将本地分支与远程分支进行关联，这样在以后的推送操作中，只需要使用 git push 命令，Git 就会自动将本地分支的更改推送到已关联的远程分支上。
origin: 远程仓库的名称，通常是你的代码托管服务（比如GitHub、GitLab等）给你的默认名称。
master: 远程仓库中的目标分支，这里是指将本地分支推送到远程仓库的主分支（通常是指主开发分支）。

重命名分支
`git branch -M main `

重新链接到仓库
```sh
# 本地仓库版本和远程仓库版本一致
git pull origin master # 从远程 master 分支拉取最新的代码到本地，并自动合并到当前所在的分支。
# 不一致
git push -u origin master # 将本地的 master 分支推送到远程仓库的 master 分支
or
git push --set-upstream origin master
```

git如何合并分支
1. 确保当前在接收更改的目标分支上
2. 使用以下命令合并要合并的分支到目标分支：
`git merge <要合并的分支名称>`
Git会尝试自动合并更改。如果自动合并成功，会生成一个新的版本。

git的三种状态
已提交 保存在了本地服务器中
已修改
已暂存 表示对文件的当前版本做了标记,使其包含在下次提交的快照中


