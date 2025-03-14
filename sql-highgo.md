

## 备份和还原

注意 版本需要对应否则会报错
此处使用的是 官网下载的数据库软件 安装目录自带的 pg_dump pg_restore 正常使用 使用 postgresql 携带的可能报错 可能是我安装的版本不对


备份：
pg_dump -h 172.16.9.1 -p 5867 -U sysdba -d vacs -n public --exclude-table-data=hg_t_audit_log -v -Fc > highgo_backup.hgdmp


还原：
pg_restore -h 172.16.9.1 -p 5868 -U sysdba -d vacs -v highgo_backup.hgdmp









