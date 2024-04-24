# 第3章 Spring Boot数据访问

## 3.1Spring Boot数据访问概述

在开发中，通常会涉及到对数据库的数据进行操作，Spring Boot在简化项目开发以及实现自动化配置的基础上，对关系型数据库和非关系型数据库的访问操作都提供了非常好的整合支持。

Spring Boot默认采用整合SpringData的方式统一处理数据访问层，通过添加大量自动配置，引入各种数据访问模板xxxTemplate以及统一的Repository接口，从而达到简化数据访问层的操作。

**Spring Boot提供的常见数据库依赖启动器**

![](E:\java框架\SpringBoot课件\imgs\chap03\1.png)

说明：Spring Boot没有提供Mybatis场景依赖，MyBatis团队自己适配了Spring Boot，提供mybatis-spring-boot-starter依赖启动器实现数据库访问操作。

## 3.2Spring Boot整合Mybatis

### 1.数据准备

```
# 创建数据库
CREATE DATABASE springbootdata;
# 选择使用数据库
USE springbootdata;
# 创建表t_article并插入相关数据
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(200) DEFAULT NULL COMMENT '文章标题',
  `content` longtext COMMENT '文章内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO `t_article` VALUES ('1', 'Spring Boot基础入门', '从入门到精通讲解...');
INSERT INTO `t_article` VALUES ('2', 'Spring Cloud基础入门', '从入门到精通讲解...');
# 创建表t_comment并插入相关数据
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `content` longtext COMMENT '评论内容',
  `author` varchar(200) DEFAULT NULL COMMENT '评论作者',
  `a_id` int(20) DEFAULT NULL COMMENT '关联的文章id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT INTO `t_comment` VALUES ('1', '很全、很详细', '狂奔的蜗牛', '1');
INSERT INTO `t_comment` VALUES ('2', '赞一个', 'tom', '1');
INSERT INTO `t_comment` VALUES ('3', '很详细', 'kitty', '1');
INSERT INTO `t_comment` VALUES ('4', '很好，非常详细', '张三', '1');
INSERT INTO `t_comment` VALUES ('5', '很不错', '张杨', '2');
```

### 2.创建项目，引入相应的启动器

选择SQL模块中的MySQL和MyBatis依赖

![](E:\java框架\SpringBoot课件\imgs\chap03\2.png)

注意：mybatis-spring-boot-starter依赖如果有错误，可以考虑改版本号修正，mysql依赖要与自己的数据库版本对应

```
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.3</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>
```

### 3.在项目添加domain包，编写实体类Comment和Article

```
public class Comment {
    private Integer id;
    private String content;
    private String author;
    private Integer aId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", aId=" + aId +
                '}';
    }
}
```

```
public class Article {
    private Integer id;
    private String title;
    private String content;
    private List<Comment> commentList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", commentList=" + commentList +
                '}';
    }
}
```

### 4.在全局配置文件中进行数据库连接配置

```
#mysql
spring.datasource.url=jdbc:mysql://localhost:3306/springbootdata?serverTimezone=UTC&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
```

### 5.Spring Boot1.X版本默认使用的是tomcat.jdbc数据源，Spring Boot2.X版本默认使用hikari数据源，若使用其他数据源，还需进行额外配置。

例：使用阿里巴巴Druid数据源

**在pom.xml添加依赖**

```
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
```

**上述配置的Druid数据源启动器内部已经初始化化了一些参数，如果在开发过程中，需要对这些第三方Druid的运行参数进行重新设置，必须在application.properties配置文件中进行默认参数覆盖。**

```
#添加并配置第三方数据库Druid
spring.datasource.type = com.alibaba.druid.pool.DruidDataSource
spring.datasource.initialSize=20
spring.datasource.minIdle=10
spring.datasource.maxActive=100
```

**编写自定义配置类，将配置文件中的属性注入到Druid数据源中**

在项目添加config包，编写自定义配置类DataSourceConfig

```
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDruid(){
        return new DruidDataSource();
    }
}
```

### 6.使用注解方式整合MyBatis

（1）创建Mapper接口文件

在项目添加mapper包，在包下创建一个对数据库表t_comment操作的接口CommentMapper

```
@Mapper
public interface CommentMapper {
    @Select("SELECT * FROM t_comment WHERE id =#{id}")
    public Comment findById(Integer id);

    @Insert("INSERT INTO t_comment(content,author,a_id) " + 
            "values (#{content},#{author},#{aId})")
    public int insertComment(Comment comment);

    @Update("UPDATE t_comment SET content=#{content} WHERE id=#{id}")
    public int updateComment(Comment comment);

    @Delete("DELETE FROM t_comment WHERE id=#{id}")
    public int deleteComment(Integer id);
}
```

（2）编写测试方法进行接口方法测试及整合测试

```
   @Autowired
    private CommentMapper commentMapper;
    @Test
    public void selectComment() {
        Comment comment = commentMapper.findById(1);
        System.out.println(comment);
    }
```

### 7.使用配置文件方式整合 MyBatis

**整合步骤**

- 创建Mapper接口文件：@Mapper
- 创建XML映射文件：编写对应的SQL语句
- 在全局文件中配置XML映射文件路径以及实体类别名映射路径
- 编写测试方法进行接口方法测试及整合测试

（1） 创建Mapper接口文件：

```
@Mapper
public interface ArticleMapper {
    public Article selectArticle(Integer id);
    public int updateArticle(Article article);

}
```

（2）创建XML映射文件ArticleMapper.xml

在resources目录下，创建一个文件包mapper，在包下编写映射文件ArticleMapper.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.sdut.demo04.mapper.ArticleMapper">
    <!-- 1、查询文章详细（包括评论信息） -->
    <select id="selectArticle" resultMap="articleWithComment">
        SELECT a.*,c.id c_id,c.content c_content,c.author
        FROM t_article a,t_comment c
        WHERE a.id=c.a_id AND a.id = #{id}
    </select>
    <resultMap id="articleWithComment" type="Article">
        <id property="id" column="id" />
        <result property="title" column="title" />
        <result property="content" column="content" />
        <collection property="commentList" ofType="Comment">
            <id property="id" column="c_id" />
            <result property="content" column="c_content" />
            <result property="author" column="author" />
        </collection>
    </resultMap>

    <!-- 2、根据文章id更新文章信息 -->
    <update id="updateArticle" parameterType="Article" >
        UPDATE t_article
        <set>
            <if test="title !=null and title !=''">
                title=#{title},
            </if>
            <if test="content !=null and content !=''">
                content=#{content}
            </if>
        </set>
        WHERE id=#{id}
    </update>
</mapper>
```

（3）配置XML映射文件路径

```
#配置MyBatis的xml配置文件路径
mybatis.mapper-locations=classpath:mapper/*.xml
#配置XML映射文件中指定的实体类别名路径
mybatis.type-aliases-package=com.sdut.demo04.domain
```

（4）编写单元测试对接口进行测试

```
 @Autowired
    private ArticleMapper articleMapper;
    @Test
    public void selectArticle() {
        Article article = articleMapper.selectArticle(1);
        System.out.println(article);
    }
```

**注意：如果编写的Mapper接口过多时，每一个接口文件都需要添加@Mapper注解，为避免这种麻烦，可以直接在Spring Boot项目启动类上添加@MapperScan（“XXXX”）注解。**

## 3.3Spring Boot整合JPA

JPA是Java Persistence API的简称，中文名Java持久层API，是JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。

Sun引入新的JPA ORM规范出于两个原因：其一，简化现有Java EE和Java SE应用开发工作；其二，Sun希望整合ORM技术，实现天下归一。

### 1.Spring Data JPA简介

Spring Data JPA是Spring基于ORM框架、JPA规范的基础上封装的一套JPA应用框架，它提供了增删改查等常用功能，使开发者可以用较少的代码实现数据操作，同时还易于扩展。

（1）编写ORM实体类

在项目domain包下编写ORM实体类，配置好映射关系

```
@Entity(name = "t_comment")
public class Discuss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String author;
    @Column(name = "a_id")
    private Integer aId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", aId=" + aId +
                '}';
    }
}
```

说明：

 @Entity(name = "t_comment")  // 设置ORM实体类，并指定映射的表名

@Id   // 表明映射对应的主键id

 @GeneratedValue(strategy = GenerationType.IDENTITY) // 设置主键自增策略

@Column(name = "a_id")  //指定映射的表字段名

（2）编写Repository接口DiscussRepository

在项目下创建repository包，添加DiscussRepository接口，并编写对应的数据库操作方法

```
public interface DiscussRepository extends JpaRepository<Discuss,Integer> {
    // 1、查询author非空的Discuss评论集合
    public List<Discuss> findByAuthorNotNull();

    // 2、根据文章id分页查询Discuss评论集合
    @Query("SELECT c FROM t_comment c WHERE  c.aId = ?1")
    public List<Discuss> getDiscussPaged(Integer aid,Pageable pageable);

    // 3、使用元素SQL语句，根据文章id分页查询Discuss评论集合
    @Query(value = "SELECT * FROM t_comment  WHERE  a_Id = ?1",nativeQuery = true)
    public List<Discuss> getDiscussPaged2(Integer aid, Pageable pageable);

    //4、 根据评论id修改评论作者author
    @Transactional
    @Modifying
    @Query("UPDATE t_comment c SET c.author = ?1 WHERE  c.id = ?2")
    public int updateDiscuss(String author,Integer id);

    // 5、根据评论id删除评论
    @Transactional
    @Modifying
    @Query("DELETE t_comment c WHERE  c.id = ?1")
    public int deleteDiscuss(Integer id);
}
```

（3）Repository继承关系

![](E:\java框架\SpringBoot课件\imgs\chap03\3.png)



使用Spring Data JPA自定义Repository接口，必须继承XXRepository<T, ID>接口。

（4）使用Spring Data JPA进行数据操作的多种实现方式

- 如果自定义接口继承了JpaRepository接口，则默认包含了一些常用的CRUD方法

- 自定义Repository接口中，可以使用@Query注解配合SQL语句进行数据的查、改、删操作。

- 自定义Repository接口中，可以直接使用方法名关键字进行查询操作。

  ![](E:\java框架\SpringBoot课件\imgs\chap03\4.png)

（5）自定义Repository接口中的@Transactional注解

- 在自定义的Repository接口中，针对数据的变更操作（修改、删除），无论是否使用了@Query注解，都必须在方法上方添加@Transactional注解进行事务管理，否则程序执行就会出现InvalidDataAccessApiUsageException异常。
- 如果在调用Repository接口方法的业务层Service类上已经添加了@Transactional注解进行事务管理，那么Repository接口文件中就可以省略@Transactional注解。

（6）变更操作，要配合使用@Query与Modify注解

在自定义的Repository接口中，使用@Query注解方式执行数据变更操作（修改、删除），除了要使用@Query注解，还必须添加@Modifying注解表示数据变更。

（7）JPA还支持使用Example实例进行复杂条件查询

```
//创建查询条件数据对象
        Customer customer = new Customer();
        customer.setName("zhang");
        customer.setAddress("河南省");
        customer.setRemark("BB");
 
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("address", GenericPropertyMatchers.startsWith()) //地址采用“开始匹配”的方式查询
                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
        
        //创建实例
        Example<Customer> ex = Example.of(customer, matcher); 
        
        //查询
        List<Customer> ls = dao.findAll(ex);
```

### 2.使用Spring Boot 整合 JPA

整合步骤：

（1）在pom文件中添加Spring Data JPA依赖启动器

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
```

（2）编写ORM实体类

```
@Entity(name = "t_comment")
public class Discuss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String author;
    @Column(name = "a_id")
    private Integer aId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", aId=" + aId +
                '}';
    }
}
```

（3）编写Repository接口

```
public interface DiscussRepository extends JpaRepository<Discuss,Integer> {
    // 1、查询author非空的Discuss评论集合
    public List<Discuss> findByAuthorNotNull();

    // 2、根据文章id分页查询Discuss评论集合
    @Query("SELECT c FROM t_comment c WHERE  c.aId = ?1")
    public List<Discuss> getDiscussPaged(Integer aid,Pageable pageable);

    // 3、使用元素SQL语句，根据文章id分页查询Discuss评论集合
    @Query(value = "SELECT * FROM t_comment  WHERE  a_Id = ?1",nativeQuery = true)
    public List<Discuss> getDiscussPaged2(Integer aid, Pageable pageable);

    //4、 根据评论id修改评论作者author
    @Transactional
    @Modifying
    @Query("UPDATE t_comment c SET c.author = ?1 WHERE  c.id = ?2")
    public int updateDiscuss(String author,Integer id);

    // 5、根据评论id删除评论
    @Transactional
    @Modifying
    @Query("DELETE t_comment c WHERE  c.id = ?1")
    public int deleteDiscuss(Integer id);
}
```

（4）编写单元测试进行接口方法测试及整合测试

在测试环境添加JpaTests

```
@Autowired
    private DiscussRepository repository;
    // 1、使用JpaRepository内部方法进行数据操作
    @Test
    public void selectComment() {
        Optional<Discuss> optional = repository.findById(1);
        if(optional.isPresent()){
            System.out.println(optional.get());
        }
        System.out.println();
    }
    // 2、使用方法名关键字进行数据操作
    @Test
    public void selectCommentByKeys() {
        List<Discuss> list = repository.findByAuthorNotNull();
        System.out.println(list);
    }
    // 3、使用@Query注解进行数据操作
    @Test
    public void selectCommentPaged() {
        Pageable pageable = PageRequest.of(0,3);
        List<Discuss> allPaged = repository.getDiscussPaged(1, pageable);
        System.out.println(allPaged);
    }
    //  4、使用Example封装参数进行数据查询操作
    @Test
    public void selectCommentByExample() {
        Discuss discuss =new Discuss();
        discuss.setAuthor("张三");
        Example<Discuss> example = Example.of(discuss);
        List<Discuss> list = repository.findAll(example);
        System.out.println(list);
    }
    @Test
    public void selectCommentByExampleMatcher() {
        Discuss discuss =new Discuss();
        discuss.setAuthor("张");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("author",startsWith());
        Example<Discuss> example = Example.of(discuss, matcher);
        List<Discuss> list = repository.findAll(example);
        System.out.println(list);
    }
```

## 3.4Spring Boot整合Redis

Spring Boot对非关系型数据库页提供了非常好的支持。

### 1.Redis 介绍

Redis 是一个开源（BSD许可）的、内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件，并提供多种语言的API。

**Redis优点**

- 存取速度快：Redis速度非常快，每秒可执行大约110000次的设值操作，或者执行81000次的读取操作。
- 支持丰富的数据类型：Redis支持开发人员常用的大多数数据类型，例如列表、集合、排序集和散列等。
- 操作具有原子性：所有Redis操作都是原子操作，这确保如果两个客户端并发访问，Redis服务器能接收更新后的值。
- 提供多种功能：Redis提供了多种功能特性，可用作非关系型数据库、缓存中间件、消息中间件等。

（1）Redis下载安装

下载Redis安装包

[Releases · tporadowski/redis · GitHub](https://github.com/tporadowski/redis/releases)

（2）Redis服务开启与连接配置

redis-server.exe 

Redis Desktop

### 2.使用Spring Boot整合Redis

**整合步骤：**

（1）在pom文件中添加Spring Data Redis依赖启动器

```
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```

（2）编写实体类

编写实体类Person

```
public class Address {
    @Indexed
    private String city;
    @Indexed
    private String country;

    public Address() {
    }

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
```

```
public class Family {
    @Indexed
    private String type;
    @Indexed
    private String username;

    public Family() {
    }

    public Family(String type, String username) {
        this.type = type;
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Family{" +
                "type='" + type + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
```

```
@RedisHash("persons")  // 指定操作实体类对象在Redis数据库中的存储空间
public class Person {
    @Id        // 标识实体类主键
    private String id;
    @Indexed  // 标识对应属性在Redis数据库中生成二级索引
    private String firstname;
    @Indexed
    private String lastname;
    private Address address;
    private List<Family> familyList;

    public Person() {
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Family> getFamilyList() {
        return familyList;
    }

    public void setFamilyList(List<Family> familyList) {
        this.familyList = familyList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address=" + address +
                ", familyList=" + familyList +
                '}';
    }
}
```

说明：

@RedisHash("persons")  // 指定操作实体类对象在Redis数据库中的存储空间

 @Id        // 标识实体类主键

 @Indexed  // 标识对应属性在Redis数据库中生成二级索引

（3）编写Repository接口

```
public interface PersonRepository extends CrudRepository<Person, String> {
    List<Person> findByLastname(String lastname);

    Page<Person> findPersonByLastname(String lastname, Pageable page);

    List<Person> findByFirstnameAndLastname(String firstname, String lastname);

    List<Person> findByAddress_City(String city);

    List<Person> findByFamilyList_Username(String username);
}
```

（4）在全局配置文件application.properties中添加Redis数据库连接配置

```
# Redis服务器地址
spring.redis.host=127.0.0.1
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
```

（5）编写单元测试进行接口方法测试以及整合测试

```
@SpringBootTest
class RedisTests {
    @Autowired
    private PersonRepository repository;
    @Test
    public void savePerson() {
        Person person =new Person("张","有才");
        Person person2 =new Person("James","Harden");
        // 创建并添加住址信息
        Address address=new Address("北京","China");
        person.setAddress(address);
        // 创建并添加家庭成员
        List<Family> list =new ArrayList<>();
        Family dad =new Family("父亲","张良");
        Family mom =new Family("母亲","李香君");
        list.add(dad);
        list.add(mom);
        person.setFamilyList(list);
        // 向Redis数据库添加数据
        Person save = repository.save(person);
        Person save2 = repository.save(person2);
        System.out.println(save);
        System.out.println(save2);
    }
    @Test
    public void selectPerson() {
        List<Person> list = repository.findByAddress_City("北京");
        System.out.println(list);
    }
    @Test
    public void updatePerson() {
        Person person = repository.findByFirstnameAndLastname("张","有才").get(0);
        person.setLastname("小明");
        Person update = repository.save(person);
        System.out.println(update);
    }
    @Test
    public void deletePerson() {
        Person person = repository.findByFirstnameAndLastname("张","小明").get(0);
        repository.delete(person);
    }


}
```

