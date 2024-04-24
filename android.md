# sqllite数据库

> 不支持远程访问

```sql
create
alter
drop
创建 修改 删除

insert
update
delete
插入 修改 删除

select
选择
```
不区分大小写
注释 `-- 这是一个注释`


```
ANALYZE;
or
ANALYZE database_name;
or
ANALYZE database_name.table_name;

```

------------菜鸟教程



# SpringCash

```xml
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-starter-cache</artifactId>  
</dependency>
```


```
@EnableCashing # 启动类注解
@Casheable # 查询缓存
@CashePut # 返回数据放到缓存
@CashEvict # 删除缓存
```



> 用于Controller

```java
@CachePut(cacheNames = "userCash",key="#result.id") // #result.id 取到的是返回值

```


```java
@Cacheable(cacheNames="userCache",key="#id") //key的生成 userCache::10public Result<DishVO> er(@PathVariable Long id){  
    log.info("根据id查询菜品");  
    DishVO dishVO=dishService.getByIdWithFlavor(id);  
    return Result.success(dishVO);  
}
```



```java
@CacheEvict(cacheNames="userCache",key="#id")
or
@CacheEcivt(cacheNames="userCache",allEvtries=true)
```
