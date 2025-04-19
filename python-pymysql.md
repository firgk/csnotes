
pip install pymysql


预先数据库

```sql
-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.30 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 数据导出被取消选择。

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

```




```py


import pymysql


connc=pymysql.Connecwt(
    host='127.0.0.1',
    user='root',
    password='',
    database='school',
    port=3306,
    charset='utf8'
)
cur=connc.cursor()

try:
    sql_select='select * from student;'
    cur.execute(sql_select)
    result=cur.fetchall()
    print(result)


    sql_insert='insert into student values(%s,%s);'
    add_data=[3,789]
    cur.execute(sql_insert,add_data)
    connc.commit()

   # 删除操作
    # sql_delete = 'delete from student where id = %s;'  # 假设删除条件为学生ID
    # delete_data = [2]  # 要删除的学生ID
    # cur.execute(sql_delete, delete_data)
    # connc.commit()


    # 更新操作
    # sql_update = 'update student set name=%s where id=%s;'  # 假设更新操作为更新分数
    # update_data = [900, 2]  # 更新后的分数和对应的学生ID
    # cur.execute(sql_update, update_data)
    # connc.commit()

except Exception as e:
    print(e)
    cur.rollback


finally:
    cur.close()
    connc.close()

```