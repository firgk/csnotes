




## Rest风格

users get

users/1 get

users post add

users put update

users/1 delete


```java

@RestController
@RequestMapping("/books")
public class TestController {
    @Autowired
    private BookService bookService;
    @PostMapping
    public Boolean save(@RequestBody Book book){
        return bookService.save(book);
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return bookService.delete(id);
    }
    @PutMapping
    public Boolean update(@RequestBody Book book){
        return bookService.modify(book);
    }
    @GetMapping
    public List<Book> getAll(){
        return bookService.list();
    }
    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id){
        return bookService.getById(id);
    }
    @GetMapping("{currentPage}/{pageSize}")
    public IPage getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return bookService.getPage(currentPage,pageSize);
    }
}

```
serviceImpl 层
```java
@Override
private BookService bookService;
public IPage<Book>getPage(int currentPage,int pageSize){
    IPage page=new Page(currentPage,pageSize);
    bookDao.selectPage(page,null);
    return page;
}
```












## 手动注入
```java
public TestController(BookService bookService){
    this.bookService=bookService;
}
```

## 注解
```java
@RequestMapping(value = "/update",method = RequestMethod.POST)
@ResponseBody
public String update(@RequestBody User user){
    System.out.println("user save...");
    return "user save...";
}

public String getByid(@RequestParam Integer id)

@RequestParam 用于接收url地址传参或者表单传参
@RequestBody 用于接收json数据
@PathVariable 用于接收路径参数，使用｛参数名称｝，描述路径




@RestController= @Controller+ @ResponseBody

@PostMapping=@RequestMapping(method=RequestMethod.Post)
@DeleteMapping=@RequestMapping(method=RequestMethod.Post)
@PutMapping=@RequestMapping(method=RequestMethod.Post)
@GetMapping=@RequestMapping(method=RequestMethod.Post)


可以全都用@Comnet


@service 在serviceImpl中可以使用
在service中什么都不用写

```


## 配置文件
yml 主流格式
springio
springboot 
learn
Reference Doc.
Application Properties

格式 优先级别
properties
yml
yaml
相同的话按照优先级来，不同的话，全都保留

没有提示
在项目结构中修改

## yaml 数据格式
![](https://www.bilibili.com/video/BV15b4y1a7yG?p=21&vd_source=a9dde2385c9a6de7816fb999b5b76c10)





## 整合数据库

## juint 单元测试



不用写main就可以运行

```
@Test
@Before 在所有测试方法之前执行
@After 在所有测试方法之后执行
```

Assert.assertEquals(40,result); //40 is your predict result is use your code to get



```java
测试类上标注 
@SpringbootTest
class Test(){
@Atuowried
注入你要测试的对象
private BookDao bookDao;
测试
@Test
void contextTest(){
	bookDao.save();
}
}
```
@SpringbootTest(//) 里面可以标记引导类是哪一个 



## 注解开发
```java
@Mapper
public interface BookDao{
  @Select("select * from tbl_book where id = #{id}")
  public Book getById(Integer id);
......
}
```


## 注解

框架=注解+反射+设计模式
可以生成javadoc
/**后可以自动生成

注解可以替代配置文件

```java
/**
 * @author lgh
 * @version 1.0
 */
public class Person {
    /**
     *eat方法,实现了,,,功能
     * @param num1 就餐人数
     * @param num2 点了几个菜
     */
    public void eat(int num1,int num2){

    }

    /**
     *
     * @param age 年龄
     * @throws Exception 年龄过大的时候
     */
    public void sleep(int age) throws Exception {
        if(age>100){
            throw new Exception();
        }
    }
}

```

### jdk自带的三个注解

    @override 
    @Deprecated 已经弃用
    @SuppressWarning 抑制编译器警告

    ex:
    @SuppressWarning("unused")


## 反射


## durid

末尾最后加一行
```yml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ssm_db
    name: root
    password: root
    type: com.alibaba.durid.pol.DruidDataSource

```


第二种
```yml
spring:
  datasource:
      druid:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://localhost:3306/ssm_db
        username: root  # 此处写错导致登录失败
        password: root
```


## lombok
```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
</dependency>
```
springboot中收录了,我们无需指定版本


```java
@Data
public class Book{
  private Integer id;
  private String type;
  private String name;
}
```
@Data = @Getter +@Setter +......toString  hashCode equal
但是不包括有构造函数,提供了构造注解 @AllArgsContructor @NoargsContructor

### 启动类注解
@Slf4j




## mybatis plus
手动导入坐标

bookDao.selectList(null); //null位置是querywrapper,条件构造器 是个类似通配符的东西


配置
```yml
mabatis-plus:
  global-config:
   db-config:
    table_prefix: tb1_
    id-type: auto
  configuration:
    log-impl: org.apach.ibatis.logging.stdout.StdOutImpl # 调试日志

```


Dao 层/ Mapper层
```java
@Mapper
public interface BookDao extends BaseMapper<Book>{

}
```

test

```java
@SpringBootTest
public class BookDaoTest{
  @Atutowired
  private BookDao bookDao;
  @Test
  void testSave(){
    Book book = new Book();
    book.setName("测试数据");
    book.setType("测试数据");
    BookDao.insert(book);
  }
}
```


### 条件查询

```java
void testGetPage(){
  QueryWrapper<Book> qw=new QueryWrapper<>();
  qw.like("name","Spring");
  bookDao.selectList(qw);
}
```
解决硬编码

推荐
```java
void testGetPage(){
  lambdaQueryWrapper<Book> lqw=new lambdaQueryWrapper<Book>();
  lqw.like(Book::getName,"Spring");
  bookDao.selectList(lqw);
}
```





### 分页


在config中添加一个类
```java
@Configuration
public class MPConfig{
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor(){
    MybatisPlusInterceptor mybatisPlusInterceptor=new MybatisPlusInterceptor();
    mybatisPlusInterceptor.add(new PaginationInnerInterceptor());
    return mybatisPlusInterceptor;
  }
}
```



```java
void testGetPage(){
  IPage page=new Page(2,5);
  bookDao.selectPage(page,null);
  page.get..... //可以获得一系列参数
}
```



### 快速开发service层
```java
public interface IbookService extends IService<Book>{ //比普通的多一个继承

}

public class IbookServiceImpl extends ServiceImpl<BookDao,Book>  implements IbookService{ //比普通的多一个继承

}

IService<Book>
ServiceImpl<BookDao,Book>

```

需要自己添加功能可以重载,或者追加,添加一个新的方法




## 数据优化处理
R对象
开发中，数据要发送给前端，如果返回值为null，是值还是无效呢，所以我们需要一个标记
@Data
```java
public class R{
  private Boolean flag;
  private Object data;
  public R(){
  }
  public R(Boolean flag){
    this.flag=flag;
  }
  public R(Boolean flag,Object data){
    this.flag=flag;
    this.data=data;
  }
}
```

Controller
```java
@RestController
@RequestMapping("/books")
public class TestController {
    @Autowired
    private BookService bookService;
    @PostMapping
    public Boolean save(@RequestBody Book book){
        return new R(bookService.save(book));
    }
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return new R(bookService.delete(book));
    }
    @PutMapping
    public Boolean update(@RequestBody Book book){
        return new R(bookService.update(book));
    }
    @GetMapping
    public List<Book> getAll(){
        return new R(true,bookService.list());
    }
    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id){
        return new R(true,bookService.getById(id));
    }
    @GetMapping("{currentPage}/{pageSize}")
    public IPage getPage(@PathVariable int currentPage,@PathVariable int pageSize){
        return new R(true,bookService.getPage(currentPage,pageSize));
    }
}

```

## 所有错误的处理
RestControllerAdvice 处理异常的类
ExceptionHandler 消息拦截器，拦截所有的消息
```java
@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler()
    public R doOtherException(Exception ex){
        ex.printStackTrace(); //发送给开发人员，防止吃掉错误
        return new R(false,null,"系统错误");
    }
}

```


## 前端

### 分页查询

```js
getAll(){
  axios.get("/books"+this.pagination.currentPage+"/"+this.pagination.pageSize).then((res)=>
    this.pagination.total=res.data.data.total;
    this.pagination.currentPage=res.data.data.currentPage;
    this.pagination.pagesize=res.data.data.size;
    this.dataList=res.data.data.records;
  )
}
//切换页码
handleCurrentChange(currentPage){
  this.pagination.currentPage=currentPage;
  this.getAll();
}

```
分页查询会产生一个bug,比如你在第三页,最后一行数据之后,重新加载第三页,第三页是没数据的
所以要加一个判断,第三页没数据的时候,传入第二页的代码.
应该是用前端添加一个判断来完成

### 条件查询

```js
getAll(){
  param="?name"+this.pagination.name;
  param+="&type"+this.pagination.type;
  param+="&description"+thiss.pagination.description;
  axios.get("/books/"+this.pagination.currentPage+"/"+this.pagination.pageSize+param).then((res)=>{
    this.dataList=res.data.data.records;
  })
}
```
controller 接收参数
直接传入Book book就可以接收
```java
@GetMapping("{currentPage}/{pageSize}")
public R getAll(@PathVariable int currentPage,@PathVariable int pageSize,Book book){
  System.out.println("参数====>"+book);
  IPage<Book> pageBook = bookService.getPage(currentPage,pageSize);
  return new R(null!=pageBook,pageBook);
}
```
service
```java
public interface IBookService extends IService<Book>{
  IPage<Book> getPage(Integer currentPage,Integer pageSize,Book querybook);
}
```
serviceimpl
```java
public class BookServiceImpl extends ServiceImpl<BookDao,Book> implements IBookService{
  public IPage<Book> getPage(Integer currentPage,Integer pageSize,Book querybook){
    Ipage page=new Page(currentPage,pageSize);
    LamdaQueryWrapper<Book> lqw=new LamdaQueryWrapper<Book>();
    lqw.like(Strings.isNotEmpty(queryBok.getName()),Book::getName,queryBook.getName());
    lqw.like(Strings.isNotEmpty(queryBok.getType()),Book::getType,queryBook.getType());
    lqw.like(Strings.isNotEmpty(queryBok.getDescription()),Book::getDescription,queryBook.getDescription());
    return bookDao.selectPage(page,lqw);
  }
}
```
## 打包

jar支持命令行启动需要依赖maven插件支持
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.springframework.boot<groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
  </plugins>
</build>
```

# 杂
mybatisplus依赖中整合了mybatis





































