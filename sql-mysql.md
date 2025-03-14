

## interview


### 索引了解吗
索引用来加速查询
可以对 区分度高 常用于where语句的字段添加索引
可以通过 create index ... 来建立索引
也可以用 alter table add index 建立索引
默认会给主键设置一个索引 使用B+Tree结构




### 谈谈你对mysql优化的理解
表的设计优化
使用合适的字段
添加索引
SQL语句优化
  避免使用select*
  避免索引失效
  使用 union all 代替 union : union会多进行一次过滤
  避免再 where 语句中进行表达式操作: 会使索引失效
  join优化 可以使用inner join 就不要使用 left join right join : 内连接会对两个表进行优化 优先把小表放在外边 大表放在里面

一些集群提高性能的方式 比如: 主从复制 读写分离 分库分表






## 分库分表

分库
分库就是把数据库中的不同表放到不同的数据库上
包括垂直分库 水平分库

垂直分库 按照业务划分
用户表 订单表 商品表 拆分为 用户数据库 订单数据库 和商品数据库

水平分库 水平分表 得到的表 放到不同的数据库



分表

垂直分表
取出一个表的部分字段

水平分表
按照id等划分数据






分片算法 解决 数据被水平分片之后,数据究竟应该存放在哪个表
hash分片
范围分片
映射表分片
一致性hash






## 索引
B+树索引
  优化过的B树
B+ Tree 的有序性，所以除了用于查找，还可以用于排序和分组


哈希索引
哈希索引能以 O(1) 时间进行查找，但是失去了有序性：
无法用于排序与分组；
只支持精确查找，无法用于部分查找和范围查找。



索引优化
独立的列
在进行查询时，索引列不能是表达式的一部分，也不能是函数的参数，否则无法使用索引。

多列索引
多列索引，数据库系统可以更快速地筛选出符合条件的行，而不需要分别查找单列索引再进行合并结果。这样可以减少数据库系统需要扫描的数据量，提高查询效率。

索引列的顺序
让选择性最强的索引列放在前面。
索引的选择性是指：不重复的索引值和记录总数的比值。最大值为 1，此时每个记录都有唯一的索引与其对应。选择性越高，每个记录的区分度越高，查询效率也越高。

前缀索引
只索引开始的部分字符
对于 BLOB、TEXT 和 VARCHAR 类型的列

覆盖索引
索引包含所有需要查询的字段的值


索引的使用条件
对于非常小的表、大部分情况下简单的全表扫描比建立索引更高效；
对于中到大型的表，索引就非常有效；



`CREATE INDEX index_name
ON table_name (column1 [ASC|DESC], column2 [ASC|DESC], ...);`







## 引擎

InnoDB
InnoDB是MySQL的默认引擎
事务
行级锁
支持外键
热备份和恢复

myism
no 事务
表级锁
no 外键
不支持备份



## 集群

主从复制
  原理binlog 文件记录了 详细的操作语句
  从节点读取这个文件 


读写分离
  主从服务器负责各自的读和写，极大程度缓解了锁的争用；
  从服务器可以使用 MyISAM，提升查询性能以及节约系统开销；


分片集群


## sql执行顺序
FROM：从数据源中选择需要查询的表，可以是单个表或多个表的组合。
WHERE：对FROM子句中指定的表进行过滤，根据条件筛选出符合条件的行。
GROUP BY：按照指定的列对查询结果进行分组。
HAVING：对GROUP BY子句中分组后的结果进行过滤。
SELECT：选择要查询的列。
DISTINCT：去除重复的行。
ORDER BY：对查询结果进行排序。
LIMIT/OFFSET：限制返回的行数，用于分页查询。

from where
groupby having
select
distinct
orderby
limit



## 登录相关
mysql 远程登录
```
use mysql;
select user,host from user;
update user set host ='%' where host ='root';
flush privileges;


```


## DDL Date Define Language
对数据库操作
对表操作



### 库
```sql
show databases;
create database [if not exists] mydb [charset=utf8];
use mydb1;
drop database [if exists] mydb1;
alter database mydb1 cha
racter set utf8;
```

### 表

功能	SQL

查看当前数据库的所有表名称	show tables;
查看指定某个表的创建语句	show create table 表名；
查看表结构	desc 表名
删除表	drop table 表名


查看建表语句
```
SHOW CREATE TABLE table_name;
```



```sql
create table [if not exists]表名(
字段名1 类型[(宽度)] [约束条件] [comment '字段说明'],
字段名2 类型[(宽度)] [约束条件] [comment '字段说明'],
字段名3 类型[(宽度)] [约束条件] [comment '字段说明']
)[表的一些设置];


use mydb1;
create  table if not exists student(
  sid int,
  name varchar(20),
  gender varchar(20),
  age int,
  birth date, 
  address varchar(20),
  score double
);
```




数值类型

和java类似 名称稍微不同  多了一个 MEDIUMINT

```
类型	大小	范围（有符号）	范围（无符号）	用途
TINYINT	1 byte	(-128，127)	(0，255)	小整数值
SMALLINT	2 bytes	(-32 768，32 767)	(0，65 535)	大整数值
MEDIUMINT	3 bytes	(-8 388 608，8 388 607)	(0，16 777 215)	大整数值
INT或INTEGER	4 bytes	(-2 147 483 648，2 147 483 647)	(0，4 294 967 295)	大整数值
BIGINT	8 bytes	(-9,223,372,036,854,775,808，9 223 372 036 854 775 807)	(0，18 446 744 073 709 551 615)	极大整数值
FLOAT	4 bytes	(-3.402 823 466 E+38，3.402 823 466 351 E+38)	0，(1.175 494 351 E-38，3.402 823 466 E+38)	单精度
浮点数值
DOUBLE	8 bytes	(-1.797 693 134 862 315 7 E+308，1.797 693 134 862 315 7 E+308)	0，(2.225 073 858 507 201 4 E-308，1.797 693 134 862 315 7 E+308)	双精度
浮点数值
DECIMAL		依赖于M和D的值	依赖于M和D的值	小数值
```




字符串类型
BLOB 二进制数据容器
```
类型	大小	用途
CHAR	0-255 bytes	定长字符串
VARCHAR	0-65535 bytes	变长字符串
TINYBLOB	0-255 bytes	不超过 255 个字符的二进制字符串
TINYTEXT	0-255 bytes	短文本字符串
BLOB	0-65 535 bytes	二进制形式的长文本数据
TEXT	0-65 535 bytes	长文本数据
MEDIUMBLOB	0-16 777 215 bytes	二进制形式的中等长度文本数据
MEDIUMTEXT	0-16 777 215 bytes	中等长度文本数据
LONGBLOB	0-4 294 967 295 bytes	二进制形式的极大文本数据
LONGTEXT	0-4 294 967 295 bytes	极大文本数据
```


日期类型
```
类型	大小
( bytes)	范围	格式	用途
DATE	3	1000-01-01/9999-12-31	YYYY-MM-DD	日期值
TIME	3	'-838:59:59'/'838:59:59'	HH:MM:SS	时间值或持续时间
YEAR	1	1901/2155	YYYY	年份值
DATETIME	8	1000-01-01 00:00:00/9999-12-31 23:59:59	YYYY-MM-DD HH:MM:SS	混合日期和时间值
TIMESTAMP	4	1970-01-01 00:00:00/2038
结束时间是第 2147483647 秒，北京时间 2038-1-19 11:14:07，格林尼治时间 2038年1月19日 凌晨 03:14:07	YYYYMMDD HHMMSS	混合日期和时间值，时间戳
```





表结构
添加列
```sql
alter table 表名 add 列 类型(长度) [约束];
#为student表添加一个新的字段为：系别 dept 类型为 varchar(20)
ALTER TABLE student ADD `dept` VARCHAR(20); 
```
修改列名和类型
```sql
alter table 表名 change 旧列名 新列名 类型(长度) 约束; 
#为student表的dept字段更换为department varchar(30)
ALTER TABLE student change `dept` department VARCHAR(30); 
```


修改表删除列
```sql
alter table 表名 drop 列名;
#删除student表中department这列
ALTER TABLE student DROP department;
```


修改表名
```sql
rename table 表名 to 新表名;
#将表student改名成 stu
rename table `student`  to stu;
```


## DML Data Manipulation Language 增删改


插入
```sql
insert into 表 (列名1,列名2,列名3...) values (值1,值2,值3...)； //向表中插入某些
insert into 表 values (值1,值2,值3...)     //向表中插入所有列;
insert into student(sid,name,gender,age,birth,address,score) values(1001,'男',18,'1996-12-23','北京',83.5);
insert into student values(1001,'男',18,'1996-12-23','北京',83.5);
```


修改
```
update 表名 set 字段名=值,字段名=值...;
update 表名 set 字段名=值,字段名=值... where 条件;
-- 将所有学生的地址修改为重庆
update student set address = '重庆’; 
-- 将id为1004的学生的地址修改为北京
update student set address = '北京' where id = 1004
-- 将id为1005的学生的地址修改为北京，成绩修成绩修改为100 
update student set address = '广州',score=100 where id = 1005
```





删除
```sql
delete from 表名 [where 条件];
truncate table  表名 或者 truncate 表名
-- 1.删除sid为1004的学生数据
delete from student where sid  = 1004;
-- 2.删除表所有数据
delete from student;
-- 3.清空表数据
truncate table student;
truncate student;
```



注意：delete和truncate原理不同，delete只删除内容，而truncate类似于drop table ，可以理解为是将整个表删除，然后再创建该表；









## DQL-基本查询

1. 简单查询
2. 运算符操作
3. 排序查询
4. 聚合查询
5. 分组查询
6. 分页查询



### 简单操作

```
select 
  [all|distinct]
  <目标列的表达式1> [别名],
  <目标列的表达式2> [别名]...
from <表名或视图名> [别名],<表名或视图名> [别名]...
[where<条件表达式>]
[group by <列名> 
[having <条件表达式>]]
[order by <列名> [asc|desc]]
[limit <数字或者列表>];
```


简化
select *|列名 from 表 where 条件



ex

```sql
-- 1.查询所有的商品.  
select *  from product;
-- 2.查询商品名和商品价格. 
select pname,price from product;
-- 3.别名查询.使用的关键字是as（as可以省略的）.  
-- 3.1表别名: 
select * from product as p;
-- 3.2列别名：
select pname as pn from product; 
-- 4.去掉重复值.  
select distinct price from product;
-- 5.查询结果是表达式（运算查询）：将所有商品的价格+10元进行显示.
select pname,price+10 from product;

```


### 运算符

```
算术运算符
比较运算符
逻辑运算符
位运算符
```

> 算术运算符

```

+	加法运算
-	减法运算
*	乘法运算
/ 或 DIV 除法运算，返回商
% 或 MOD 求余运算，返回余数

```



> 比较运算符


`=	`等于
`<    和    <=`	小于和小于等于
`>    和    >=`	大于和大于等于
`<=>`安全的等于，两个操作码均为NULL时，其所得值为1；而当一个操作码为NULL时，其所得值为0
`<>` 或`!=	`   不等于
`IS NULL` 或 `ISNULL`	判断一个值是否为 NULL
`IS NOT NULL`	判断一个值是否不为 NULL
`LEAST`	当有两个或多个参数时，返回最小值
`GREATEST`	当有两个或多个参数时，返回最大值
`BETWEEN AND`	判断一个值是否落在两个值之间
`IN`	判断一个值是IN列表中的任意一个值
`NOT IN` 判断一个值不是IN列表中的任意一个值
`LIKE`	通配符匹配
`REGEXP`	正则表达式匹配


> 逻辑运算符

`NOT` 或者 !	逻辑非
`AND` 或者 &&	逻辑与
`OR` 或者 ||	逻辑或
`XOR`	逻辑异或


> 位运算符

`|`按位或
`&`按位与
`^`按位异或
`<<`	按位左移
`>>` 	按位右移
`~` 按位取反，反转所有比特




```sql

> ex 运算符操作-算数运算符


select 6 + 2;
select 6 - 2;
select 6 * 2;
select 6 / 2;
select 6 % 2;
 
-- 将每件商品的价格加10
select name,price + 10 as new_price from product;
-- 将所有商品的价格上调10%
select pname,price * 1.1 as new_price from product;



> ex 运算符操作-条件查询


-- 查询商品名称为“海尔洗衣机”的商品所有信息：
select * from product where pname = '海尔洗衣机';
 
-- 查询价格为800商品
select * from product where price = 800;
 
-- 查询价格不是800的所有商品
select * from product where price != 800;
select * from product where price <> 800;
select * from product where not(price = 800);
 
-- 查询商品价格大于60元的所有商品信息
select * from product where price > 60;
 
 
-- 查询商品价格在200到1000之间所有商品
select * from product where price >= 200 and price <=1000;
select * from product where price between 200 and 1000;




> ex 运算符操作-算数运算符

-- 查询商品价格是200或800的所有商品
select * from product where price = 200 or price = 800;
select * from product where price in (200,800);
 
-- 查询含有‘裤'字的所有商品
select * from product where pname like '%裤%';
 
-- 查询以'海'开头的所有商品
select * from product where pname like '海%';
 
-- 查询第二个字为'蔻'的所有商品
select * from product where pname like '_蔻%';
 
-- 查询category_id为null的商品
select * from product where category_id is null;
 
-- 查询category_id不为null分类的商品
select * from product where category_id is not null;



> ex 运算符操作-算数运算符


-- 使用least求最小值
select least(10, 20, 30); -- 10
select least(10, null , 30); -- null
 
-- 使用greatest求最大值
select greatest(10, 20, 30);
select greatest(10, null, 30); -- null


> 运算符操作-位运算符（了解）


select 3&5; -- 位与
select 3|5; -- 位或
select 3^5; -- 位异或
select 3>>1; -- 位左移
select 3<<1; -- 位右移
select ~3;   -- 位取反


```

### 排序查询 纵向


如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 order by 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。



```sql
select 
 字段名1，字段名2，……
from 表名
order by 字段名1 [asc|desc]，字段名2[asc|desc]……
```

1.`asc`代表升序，`desc`代表降序，如果不写`默认升序`
2.`order by`用于子句中可以支持`单个字段`，`多个字段`，`表达式`，`函数`，`别名`
3.`order by`子句，放在查询语句的最后面。`LIMIT子句除外`


```sql
-- 1.使用价格排序(降序)
select * from product order by price desc;
-- 2.在价格排序(降序)的基础上，以分类排序(降序)
select * from product order by price desc,category_id asc;
-- 3.显示商品的价格(去重复)，并排序(降序)
select distinct price from product order by price desc;

```


### 聚合查询 纵向

之前我们做的查询都是横向查询，它们都是根据条件一行一行的进行判断，而使用聚合函数查询是纵向查询，它是`对一列的值进行计算`，然后返回一个单一的值；另外聚合函数会忽略空值。




聚合函数	作用

```sql
count()	统计指定列不为NULL的记录行数；
sum()	计算指定列的数值和，如果指定列类型不是数值类型，那么计算结果为0
max()	计算指定列的最大值，如果指定列是字符串类型，那么使用字符串排序运算；
min()	计算指定列的最小值，如果指定列是字符串类型，那么使用字符串排序运算；
avg()	计算指定列的平均值，如果指定列类型不是数值类型，那么计算结果为0
```




```sql
-- 1 查询商品的总条数
select count(*) from product;
-- 2 查询价格大于200商品的总条数
select count(*) from product where price > 200;
-- 3 查询分类为'c001'的所有商品的总和
select sum(price) from product where category_id = 'c001';
-- 4 查询商品的最大价格
select max(price) from product;
-- 5 查询商品的最小价格
select min(price) from product;
-- 6 查询分类为'c002'所有商品的平均价格
select avg(price) from product where category_id = 'c002';
```


> 聚合查询-NULL值的处理


1、count函数对null值的处理
如果count函数的参数为星号（*），则统计所有记录的个数。而如果参数为某字段，不统计含null值的记录个数。

2、sum和avg函数对null值的处理
这两个函数忽略null值的存在，就好象该条记录不存在一样。

3、max和min函数对null值的处理
max和min两个函数同样忽略null值的存在。



```sql

-- 创建表
create table test_null( 
 c1 varchar(20), 
 c2 int 
);

-- 插入数据
insert into test_null values('aaa',3);
insert into test_null values('bbb',3);
insert into test_null values('ccc',null);
insert into test_null values('ddd',6);
 
-- 测试


select count(*), count(1), count(c2) from test_null;  -- count(*) 和 count(1) 都是用来统计行数的，表示统计表中所有行的数量。
select sum(c2),max(c2),min(c2),avg(c2) from test_null;

```




### 分组查询-group by 纵向


这是一个常见的 SQL 查询语句，用于统计每个产品类别中产品的数量。例如，假设有一个名为"product"的表，包含以下数据：

| product_id | category_id | product_name |
|------------|-------------|--------------|
| 1          | 1           | Product A    |
| 2          | 1           | Product B    |
| 3          | 2           | Product C    |
| 4          | 1           | Product D    |
| 5          | 3           | Product E    |

根据给出的 SQL 查询语句，执行后会返回以下结果：

| category_id | count(*) |
|-------------|----------|
| 1           | 3        |
| 2           | 1        |
| 3           | 1        |

这表示在产品表中，类别ID为1的产品有3个，类别ID为2的产品有1个，类别ID为3的产品有1个。

```sql
select category_id ,count(*) from product group by category_id ;
# *代表所有列

-- 统计各个分类商品的个数,且只显示个数大于4的信息
select category_id ,count(*) from product group by category_id having count(*) > 1;
```




### 分页查询-limit 纵向

分页查询在项目开发中常见，由于数据量很大，显示屏长度有限，因此对数据需要采取分页显示方式。例如数据共有30条，每页显示5条，第一页显示1-5条，第二页显示6-10条


```sql
-- 查询product表的前5条记录 
select * from product limit 5 

-- 从第4条开始显示，显示5条 
select * from product limit 3,5
```





将一张表的数据导入到另一张表中，有两种选择 `INSERT INTO SELECT FROM` 和 `SELECT INTO FROM` 。

### INSERT INTO SELECT语句

```sql
insert into Table2(field1,field2,…) select value1,value2,… from Table1 或者：
insert into Table2 select * from Table1
```

要求目标表Table2必须存在


### SELECT INTO FROM语句


```sql
SELECT vale1, value2 into Table2 from Table1
```

要求目标表Table2不存在，因为在插入时会自动创建表Table2，并将Table1中指定字段数据复制到Table2中。


## DQL-正则表达式

MySQL通过REGEXP关键字支持正则表达式进行字符串匹配。


```
模式	描述
^	匹配输入字符串的开始位置。
$	匹配输入字符串的结束位置。
.	匹配除 "\n" 之外的任何单个字符。
[...]	字符集合。匹配所包含的任意一个字符。例如， '[abc]' 可以匹配 "plain" 中的 'a'。
[^...]	负值字符集合。匹配未包含的任意字符。例如， '[^abc]' 可以匹配 "plain" 中的'p'。
p1|p2|p3	匹配 p1 或 p2 或 p3。例如，'z|food' 能匹配 "z" 或 "food"。'(z|f)ood' 则匹配 "zood" 或 "food"。
*	匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。* 等价于{0,}。
+	匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,}。
{n}	n 是一个非负整数。匹配确定的 n 次。例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o。
{n,m}	m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。
```

```sql

-- ^ 在字符串开始处进行匹配
SELECT  'abc' REGEXP '^a';
 
-- $ 在字符串末尾开始匹配
SELECT  'abc' REGEXP 'a$';
SELECT  'abc' REGEXP 'c$';

-- . 匹配任意字符
SELECT  'abc' REGEXP '.b';
SELECT  'abc' REGEXP '.c';
SELECT  'abc' REGEXP 'a.';
 
-- [...] 匹配括号内的任意单个字符
SELECT  'abc' REGEXP '[xyz]';
SELECT  'abc' REGEXP '[xaz]';


-- [^...] 注意^符合只有在[]内才是取反的意思，在别的地方都是表示开始处匹配
SELECT  'a' REGEXP '[^abc]';
SELECT  'x' REGEXP '[^abc]';
SELECT  'abc' REGEXP '[^a]';
 
-- a* 匹配0个或多个a,包括空字符串。 可以作为占位符使用.有没有指定字符都可以匹配到数据
 
SELECT 'stab' REGEXP '.ta*b';
SELECT 'stb' REGEXP '.ta*b';
SELECT '' REGEXP 'a*';
 
-- a+  匹配1个或者多个a,但是不包括空字符
SELECT 'stab' REGEXP '.ta+b';
SELECT 'stb' REGEXP '.ta+b';

-- a?  匹配0个或者1个a
SELECT 'stb' REGEXP '.ta?b';
SELECT 'stab' REGEXP '.ta?b';
SELECT 'staab' REGEXP '.ta?b';
 
-- a1|a2  匹配a1或者a2，
SELECT 'a' REGEXP 'a|b';
SELECT 'b' REGEXP 'a|b';
SELECT 'b' REGEXP '^(a|b)';
SELECT 'a' REGEXP '^(a|b)';
SELECT 'c' REGEXP '^(a|b)';
 
-- a{m} 匹配m个a
 
SELECT 'auuuuc' REGEXP 'au{4}c';
SELECT 'auuuuc' REGEXP 'au{3}c';

 -- a{m,n} 匹配m到n个a,包含m和n
 
SELECT 'auuuuc' REGEXP 'au{3,5}c';
SELECT 'auuuuc' REGEXP 'au{4,5}c';
SELECT 'auuuuc' REGEXP 'au{5,10}c';
 
-- (abc) abc作为一个序列匹配，不用括号括起来都是用单个字符去匹配，如果要把多个字符作为一个整体去匹配就需要用到括号，所以括号适合上面的所有情况。
SELECT 'xababy' REGEXP 'x(abab)y';
SELECT 'xababy' REGEXP 'x(ab)*y';
SELECT 'xababy' REGEXP 'x(ab){1,2}y';
```







## mysql 约束

约束英文：`constraint`
约束实际上就是表中数据的限制条件
表在设计的时候加入约束的目的就是为了保证表中的记录完整性和有效性，比如用户表有些列的值（手机号）不能为空，有些列的值（身份证号）不能重复。

主键约束(primary key) PK
非空约束(not null)
唯一性约束(unique)
自增长约束(auto_increment)
默认约束(default)
零填充约束(zerofill)
外键约束(foreign key) FK


### 主键约束(primary key) PK
MySQL主键约束是一个列或者多个列的组合，其值能唯一地标识表中的每一行,方便在RDBMS中尽快的找到某一行。
主键约束相当于 `唯一约束` + `非空约束` 的组合，主键约束列不允许重复，也不允许出现空值。
每个表最多只允许一个主键
主键约束的关键字是 primary key
系统会自动给 mysql创建 唯一索引


> 添加单列主键

两种方式

```sql
create table emp1(
    eid int primay key,
    name VARCHAR(20),
    deptId int,
    salary double
);


create table emp2(
    eid INT,
    name VARCHAR(20),
    deptId INT,
    salary double,
    constraint pk1 primary key(id)
 );
```



> 添加多列联合主键

所谓的联合主键，就是这个主键是由一张表中多个字段组成的。
注意：
   1. 当主键是由多个字段组成时，不能直接在字段名后面声明主键约束。
   2. 一张表只能有一个主键，联合主键也是一个主键


```sql
create table 表名(
   ...
   primary key （字段1，字段2，…,字段n)
);
```


```sql
create table emp3( 
  name varchar(20), 
  deptId int, 
  salary double, 
  primary key(name,deptId) 
);

```


通过后期修改添加主键

```sql
create table 表名(
   ...
);

alter table <表名> add primary key（字段列表);
```


```sql
-- 添加单列主键
create table emp4(
  eid int, 
  name varchar(20), 
  deptId int, 
  salary double, 
);
alter table emp4 add primary key（eid);
```









删除主键

```sql
alter table <数据表名> drop primary key;

-- 删除单列主键 
alter table emp1 drop primary key;
 
-- 删除联合主键 
alter table emp5 drop primary key;
```




### 自增长约束(auto_increment)


```sql
create table t_user1( 
  id int primary key auto_increment, 
  name varchar(20) 
);

```




默认情况下，auto_increment的初始值是 1，每新增一条记录，字段值自动加 1。
一个表中只能有一个字段使用 auto_increment约束，且该字段必须有唯一索引，以避免序号重复（即为主键或主键的一部分）。

auto_increment约束的字段必须具备 NOT NULL 属性。
auto_increment约束的字段只能是整数类型（TINYINT、SMALLINT、INT、BIGINT 等。
auto_increment约束字段的最大值受该字段的数据类型约束，如果达到上限，auto_increment就会失效。


> 指定自增字段初始值


如果第一条记录设置了该字段的初始值，那么新增加的记录就从这个初始值开始自增。例如，如果表中插入的第一条记录的 id 值设置为 5，那么再插入记录时，id 值就会从 5 开始往上增加





```sql
-- 方式1，创建表时指定
create table t_user2 ( 
  id int primary key auto_increment, 
  name varchar(20)
)auto_increment=100;


-- 方式2，创建表之后指定
create table t_user3 ( 
  id int primary key auto_increment, 
  name varchar(20)
);

alter table t_user2 auto_increment=100;


delete和truncate在删除后自增列的变化



delete数据之后自动增长从断点开始
truncate数据之后自动增长从默认起始值开始


```




###  非空约束(not null)

如果用户在添加数据时没有指定值，数据库系统就会报错。


```sql

方式1：<字段名><数据类型> not null;
方式2：alter table 表名 modify 字段 类型 not null;



-- 方式1，创建表时指定
create table t_user6 ( 
  id int , 
  name varchar(20) not null, 
  address varchar(20) not null 
);




-- 方式2:
create table t_user7 ( 
  id int , 
  name varchar(20) , -- 指定非空约束 
  address varchar(20) -- 指定非空约束 
); 
alter table t_user7 modify name varchar(20) not null; 
alter table t_user7 modify address varchar(20) not null;



> 删除非空约束


-- alter table 表名 modify 字段 类型 
alter table t_user7 modify name varchar(20) ; 
alter table t_user7 modify address varchar(20) ;

```

###  唯一约束(unique)


```sql


方式1：<字段名> <数据类型> unique
方式2： alter table 表名 add constraint 约束名 unique(列);

1
-- 创建表时指定
create table t_user8 ( 
 id int , 
 name varchar(20) , 
 phone_number varchar(20) unique -- 指定唯一约束 
);

2
create table t_user9 ( 
  id int , 
  name varchar(20) , 
  phone_number varchar(20) -- 指定唯一约束 
); 
alter table t_user9 add constraint unique_ph unique(phone_number);




-- alter table <表名> drop index <唯一约束名>;
alter table t_user9 drop index unique_ph;


```





###  默认约束(default)


```sql

方式1： <字段名> <数据类型> default <默认值>;


create table t_user10 ( 
  id int , 
  name varchar(20) , 
  address varchar(20) default ‘北京’ -- 指定默认约束 
);



-- alter table 表名 modify 列名 类型 default 默认值; 

create table t_user11 ( 
  id int , 
  name varchar(20) , 
  address varchar(20)  
);

alter table t_user11 modify address varchar(20) default  ‘北京’;


删除

-- alter table <表名> modify column <字段名> <类型> default null; 

alter table t_user11 modify column address varchar(20) default null;

```

###  零填充约束(zerofill)


1、插入数据时，当该字段的值的长度小于定义的长度时，会在该值的前面补上相应的02、zerofill默认为int(10) 3、当使用zerofill 时，默认会自动加unsigned（无符号）属性，使用unsigned属性后，数值范围是原值的2倍，例如，有符号为-128~+127，无符号为0~256。



```sql

create table t_user12 ( 
  id int zerofill , -- 零填充约束
  name varchar(20)   
);


alter table t_user12 modify id int;
```




## MySQL的多表操作
多表联合查询
子查询
自关联查询






### 外键约束
默认 不允许在外键中出现空值
阿里巴巴代码文档中表示 数据库中不适用外键 而是在逻辑中写 因为外键会影响性能 而且这样分开写还可以解耦

在主表的表名后面指定列名或列名的组合。这个列或列的组合必须是主表的 主键 或 候选键 。

外键中列的数目必须和主表的主键中列的数目相同。
外键中列的数据类型必须和主表主键中对应列的数据类型相同。


MySQL 外键约束（FOREIGN KEY）是表的一个特殊字段，经常与主键约束一起使用。对于两个具有关联关系的表而言，相关联字段中主键所在的表就是主表（父表），外键所在的表就是从表（子表）。

外键用来建立主表与从表的关联关系，为两个表的数据建立连接，约束两个表中数据的一致性和完整性。比如，一个水果摊，只有苹果、桃子、李子、西瓜等 4 种水果，那么，你来到水果摊要买水果就只能选择苹果、桃子、李子和西瓜，其它的水果都是不能购买的。



```sql
create database mydb3; 
use mydb3;
-- 创建部门表
create table if not exists dept(
  deptno varchar(20) primary key ,  -- 部门号
  name varchar(20) -- 部门名字
);

create table if not exists emp(
  eid varchar(20) primary key , -- 员工编号
  ename varchar(20), -- 员工名字
  age int,  -- 员工年龄
  dept_id varchar(20),  -- 员工所属部门
  constraint emp_fk foreign key (dept_id) references dept (deptno) –- 外键约束
);



-- 创建部门表
create table if not exists dept2(
  deptno varchar(20) primary key ,  -- 部门号
  name varchar(20) -- 部门名字
);
-- 创建员工表
create table if not exists emp2(
  eid varchar(20) primary key , -- 员工编号
  ename varchar(20), -- 员工名字
  age int,  -- 员工年龄
  dept_id varchar(20)  -- 员工所属部门
);

-- 创建外键约束
alter table emp2 add constraint dept_id_fk foreign key(dept_id) references dept2 (deptno);





 -- 1、添加主表数据
 -- 注意必须先给主表添加数据
insert into dept values('1001','研发部');
insert into dept values('1002','销售部');
insert into dept values('1003','财务部');
insert into dept values('1004','人事部');

-- 2、添加从表数据
  -- 注意给从表添加数据时，外键列的值不能随便写，必须依赖主表的主键列
insert into emp values('1','乔峰',20, '1001');
insert into emp values('2','段誉',21, '1001');
insert into emp values('3','虚竹',23, '1001');
insert into emp values('4','阿紫',18, '1002');
insert into emp values('5','扫地僧',35, '1002');
insert into emp values('6','李秋水',33, '1003');
insert into emp values('7','鸠摩智',50, '1003'); 
insert into emp values('8','天山童姥',60, '1005');  -- 不可以


-- 3、删除数据
 /*
   注意：
       1：主表的数据被从表依赖时，不能删除，否则可以删除
       2: 从表的数据可以随便删除
 */
delete from dept where deptno = '1001'; -- 不可以删除
delete from dept where deptno = '1004'; -- 可以删除
delete from emp where eid = '7'; -- 可以删除



删除外键约束
alter table <表名> drop foreign key <外键约束名>;
alter table emp2 drop foreign key dept_id_fk;
```



### 多表联合查询
交叉连接查询 (产生笛卡尔积，了解)
  语法：select * from A,B;  

内连接只返回匹配的行，而 外连接会返回匹配的行以及不匹配的行（通过填充 NULL 值），从而保留了数据的完整性。

交集
内连接查询(使用的关键字 inner join  -- inner可以省略)
  隐式内连接（SQL92标准）：select * from A,B where 条件;
  显示内连接（SQL99标准）：select * from A inner join B on 条件;

并
外连接查询(使用的关键字 outer join -- outer可以省略)
  左外连接：left outer join
      select * from A left outer join B on 条件;
  右外连接：right outer join
      select * from A right outer join B on 条件;
  满外连接: full outer join
      select * from A full outer join B on 条件;

子查询
      select的嵌套
表自关联：
      将一张表当成多张表来用




```sql
-- 查询人数大于等于3的部门，并按照人数降序排序
select deptno,count(1) as total_cnt from dept3,emp3 where dept3.deptno = emp3.dept_id group by deptno having total_cnt >= 3 order by total_cnt desc;
 
select deptno,count(1) as total_cnt from dept3 join emp3 on dept3.deptno = emp3.dept_id group by deptno having total_cnt >= 3 order by total_cnt desc;



-- 外连接查询
-- 查询哪些部门有员工，哪些部门没有员工
use mydb3;
select * from dept3 left outer join emp3 on dept3.deptno = emp3.dept_id;
 
-- 查询哪些员工有对应的部门，哪些没有
select * from dept3 right outer join emp3 on dept3.deptno = emp3.dept_id;
 
 
-- 使用union关键字实现左外连接和右外连接的并集
select * from dept3 left outer join emp3 on dept3.deptno = emp3.dept_id
union 
select * from dept3 right outer join emp3 on dept3.deptno = emp3.dept_id;


```



### 子查询

子查询就是指的在一个完整的查询语句之中，嵌套若干个不同功能的小查询，从而一起完成复杂查询的一种编写形式，通俗一点就是包含select嵌套的查询。

子查询可以返回的数据类型一共分为四种：
单行单列：返回的是一个具体列的内容，可以理解为一个单值数据；
单行多列：返回一行数据中多个列的内容；
多行单列：返回多行记录之中同一列的内容，相当于给出了一个操作范围；
多行多列：查询返回的结果是一张临时表


```sql
-- 查询年龄最大的员工信息，显示信息包含员工号、员工名字，员工年龄
select eid,ename,age from emp3 where age = (select max(age) from emp3);
 
 
-- 查询年研发部和销售部的员工信息，包含员工号、员工名字
select eid,ename,t.name from emp3 where dept_id in (select deptno,name from dept3 where name = '研发部' or name = '销售部') ;
 
 
-- 查询研发部20岁以下的员工信息,包括员工号、员工名字，部门名字
select eid,age,ename,name from (select * from dept where name = '研发部 ')t1,(select * from emp3 where age <20)t2

```



> 子查询关键字

1.ALL关键字
2.ANY关键字
3.SOME关键字
4.IN关键字
5.EXISTS关键字



> all

```sql
select …from …where c > all(查询语句)
--等价于：
select ...from ... where c > result1 and c > result2 and c > result3
```

ALL: 与子查询返回的所有值比较为true 则返回true
ALL可以与=、>、>=、<、<=、<>结合是来使用，分别表示等于、大于、大于等于、小于、小于等于、不等于其中的其中的所有数据。
ALL表示指定列中的值必须要大于子查询集的每一个值，即必须要大于子查询集的最大值；如果是小于号即小于子查询集的最小值。同理可以推出其它的比较运算符的情况。




```sql
-- 查询年龄大于‘1003’部门所有年龄的员工信息
select * from emp3 where age > all(select age from emp3 where dept_id = '1003');
-- 查询不属于任何一个部门的员工信息 
select * from emp3 where dept_id != all(select deptno from dept3); 
```

> ANY和SOME





```sql
select …from …where c > any(查询语句)
--等价于：
select ...from ... where c > result1 or c > result2 or c > result3


```

  ANY:与子查询返回的任何值比较为true 则返回true
  ANY可以与=、>、>=、<、<=、<>结合是来使用，分别表示等于、大于、大于等于、小于、小于等于、不等于其中的其中的任何一个数据。
  表示制定列中的值要大于子查询中的任意一个值，即必须要大于子查询集中的最小值。同理可以推出其它的比较运算符的情况。
  SOME和ANY的作用一样，SOME可以理解为ANY的别名

```sql
-- 查询年龄大于‘1003’部门任意一个员工年龄的员工信息
select * from emp3 where age > all(select age from emp3 where dept_id = '1003');

```

> in



```sql
select …from …where c in(查询语句)
--等价于：
select ...from ... where c = result1 or c = result2 or c = result3
```

IN关键字，用于判断某个记录的值，是否在指定的集合中
在IN关键字前边加上not可以将条件反过来



```sql

-- 查询研发部和销售部的员工信息，包含员工号、员工名字
select eid,ename,t.name from emp3 where dept_id in (select deptno from dept3 where name = '研发部' or name = '销售部') ;
 
```
> exists






该子查询如果“有数据结果”(至少返回一行数据)， 则该EXISTS() 的结果为“true”，外层查询执行
该子查询如果“没有数据结果”（没有任何数据返回），则该EXISTS()的结果为“false”，外层查询不执行
EXISTS后面的子查询不返回任何实际数据，只返回真或假，当返回真时 where条件成立
注意，EXISTS关键字，比IN关键字的运算效率高，因此，在实际开发中，特别是大数据量时，推荐使用EXISTS关键字


```sql

-- 查询公司是否有大于60岁的员工，有则输出
select * from emp3 a where exists(select * from emp3 b where a.age > 60);
 
-- 查询有所属部门的员工信息
select * from emp3 a where exists(select * from dept3 b where a.dept_id = b.deptno);
```




### 自关联查询


MySQL有时在信息查询时需要进行对表自身进行关联查询，即一张表自己和自己关联，一张表当成多张表来用。注意自关联时表必须给表起别名。



```sql

select 字段列表 from 表1 a , 表1 b where 条件;
或者 
select 字段列表 from 表1 a [left] join 表1 b on 条件;





-- 创建表,并建立自关联约束
create table t_sanguo(
    eid int primary key ,
    ename varchar(20),
    manager_id int,
 foreign key (manager_id) references t_sanguo (eid)  -- 添加自关联约束
);


-- 添加数据 
insert into t_sanguo values(1,'刘协',NULL);
insert into t_sanguo values(2,'刘备',1);
insert into t_sanguo values(3,'关羽',2);
insert into t_sanguo values(4,'张飞',2);
insert into t_sanguo values(5,'曹操',1);
insert into t_sanguo values(6,'许褚',5);
insert into t_sanguo values(7,'典韦',5);
insert into t_sanguo values(8,'孙权',1);
insert into t_sanguo values(9,'周瑜',8);
insert into t_sanguo values(10,'鲁肃',8);
 
-- 进行关联查询
-- 1.查询每个三国人物及他的上级信息，如:  关羽  刘备 
select * from t_sanguo a, t_sanguo b where a.manager_id = b.eid;

```















# 优化

## 如何定位慢查询
mysql 配置文件中启用 慢日志 会对慢查询记录
使用一些运维工具,比如 skywalking 可以查看当前接口运行的情况

## 定位到了 如何分析
语句前加入 explain 或 desc 可以获取sql执行的详细信息
字段:
possible_key 当前sql可能用到的索引
key 实际命中的索引
key_len 索引占用的大小
Extra 额外优化的建议
  UsingWhere UsingIndex 使用了索引 需要的数据可以在索引列中找到 不需回表
  UsingIndex 使用了索引 回表了


type 性能好坏
从好到差: NULL system const eq_ref ref range index all
NULL
查询系统中的表
根据主键查询
主键索引 唯一索引 (单个)
索引查询 (多条)
范围查询
索引树-------需要优化
全盘扫描--------需要优化



key key_len 查看是否命中了索引
type 查看性能好坏
Extra 看看是否回表了










索引是mysql 中高效获取数据的 数据结构 
除了数据之外, 数据库还维护着满足特定搜索的数据结构 比如B+树



## 为何使用B+树 而不是 二叉树 红黑树
二叉树可能会存在数据只在一边的情况 退化为O1
数据量达到百万千万级 红黑树层级很多 效率不高

B树每个节点可以有多个分支
B+树在B树上优化 
  非叶子节点只存储指针 磁盘读写代价低
  查询效率B+树更加稳定
  叶子节点之间使用双向链表 便于扫库和区间查询




## 聚集索引 二级索引

聚集索引 数据 (叶子节点的整行数据) 和索引放在了一起 只有一个,必须有
二级索引 数据和索引分开存储 索引结构的叶子节点关联的是对应的主键 多个

如果存在主键 主键为聚集索引
如果不存在主键 将使用第一个唯一索引当作聚集索引





## 覆盖索引

查询的时候使用了索引 返回的时候的所有列 都在索引中 这就叫覆盖索引
如果没有覆盖到 那么会进行第二次查询 就产生了  回表查询

避免使用 select *






## Mysql超大分页查询

例子 select * from tb limit 900000,10
  查询大量数据, 并且还需要排序
可以使用 覆盖索引 和 子查询 进行优化

```
SELECT * 
FROM tb t
WHERE t.id IN (SELECT id FROM tb ORDER BY id LIMIT 900000, 10)
```



## 创建索引

数据量大 10w
常作为查询条件 分组 排序 字段内容区分度高 的字段

内容较长 使用前缀索引
尽量使用联合索引 因为可能会覆盖索引 提高效率
控制索引的数量 索引会影响增删改的效率
如果索引不能存储 NULL, 使用 NOT NULL 约束它 便于提高效率




## 索引失效

违反最做前缀法则
范围查询右边的列
在索引上进行运算
字符串不加单引号
模糊查询


# 事务



## redo log undo log
redo log
使用 先写日志 再进行事务的操作
用来实现事务的持久性,服务器宕机可用继续操作



undo log
用于回滚
和版本控制






