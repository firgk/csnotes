# 第2章Spring Boot核心配置与注解

## 1.全局配置文件


**SpringBoot使用一个全局的配置文件，配置文件名是固定的；**

**•application.properties**

**•application.yaml**

==注意：如果application.properties中跟application.yaml中都配置了端口号的话，最终生效的是properties中的端口号，意思就是先加载了application.yaml文件（优先级高），然后加载application.properties文件（优先级较yml文件低）。存放路径： src/main/resource目录或者类路径的/config

配置文件的作用：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；

### 1.1 application.properties配置文件

```
server.address=80
server.port=8080
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.config.additional-location=
spring.config.location=
spring.config.name=application
```

[配置属性参考文档](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)

1.使用Spring Initializr创建Spring Boot项目

2.创建一个domain包，并在包下创建两个实体类Pet和Person

```
public class Pet {
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
```

```
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private int id;
    private String name;
    private List hobby;
    private String[] family;
    private Map map;
    private Pet pet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getHobby() {
        return hobby;
    }

    public void setHobby(List hobby) {
        this.hobby = hobby;
    }

    public String[] getFamily() {
        return family;
    }

    public void setFamily(String[] family) {
        this.family = family;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hobby=" + hobby +
                ", family=" + Arrays.toString(family) +
                ", map=" + map +
                ", pet=" + pet +
                '}';
    }
}
```

（1）使用@ConfigurationProperties注解要添加依赖

```
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

（2）@Component将当前属性值的类作为Bean组件放到Spring容器中，除此之外还有@Controller @Service @Repository @Configuration

3.在application.properties文件编写Person类设置配置属性

```
#对实体类对象Person进行属性配置
person.id=1
person.name=tom
person.hobby=play,read,sleep
person.family=father,mother
person.map.k1=v1
person.map.k2=v2
person.pet.type=dog
person.pet.name=kity
```

4.在测试类里编写测试方法

```
  @Autowired
    private Person person;
    @Test
    public void test(){
        System.out.println(person);
    }
```

### 1.2 application.yaml配置文件

YAML文件格式是Spring Boot支持的一种JSON超集文件格式。相较于传统的Properties配置文件，YAML文件以数据为核心，是一种更为直观且容易被电脑识别的数据序列化格式。application.yaml文件的工作原理和application.properties一样。

使用key： value格式，注意要有空格

 value的值为数组和单列集合

```
person:
  hobby:
    - play
    - read
    - sleep 
或
person:
  hobby: [play,read,sleep]
```

value的值为Map集合或对象

```
person:
  map:
    k1: v1
    k2: v2
或
person:
    map: {k1: v1,k2: v2}
```

1.添加application.yaml文件

```
#对实体类对象Person进行属性配置
person:
 id: 3
 name: 张三
 hobby: [sing,read,sleep]
 family: [father,mother]
 map: {k1: v1, k2: v2}
 pet: {type: cat,name: tom}
```

2.运行测试类的方法

## 2.配置文件属性值的注入

###  2.1使用@ConfigurationProperties注入属性

注意：配置文件中的属性要与对应实体类的属性名一致

### 2.2使用@Value注入属性

1.创建Student类

```
@Component
public class Student {
    @Value("${person.id}")
    private int id;
    @Value("${person.name}")
    private String name;
    private List hobby;
    private String[] family;
    private Map map;
    private Pet pet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getHobby() {
        return hobby;
    }

    public void setHobby(List hobby) {
        this.hobby = hobby;
    }

    public String[] getFamily() {
        return family;
    }

    public void setFamily(String[] family) {
        this.family = family;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hobby=" + hobby +
                ", family=" + Arrays.toString(family) +
                ", map=" + map +
                ", pet=" + pet +
                '}';
    }
}
```

2.测试

```
@Autowired
    private Student student;
    @Test
    public void testStudent(){
        System.out.println(student);

    }
```

注意：@Value注入的属性类型只能是基本数据类型

### 2.3两种注解对比分析

![](E:\java框架\SpringBoot课件\imgs\chap02\1.png)

**松散绑定**

@ConfigurationProperties支持松散绑定

```
person.firstName=james
person.first-name=james
person.first_name=james
PERSON.FIRSTNAME=james
```

**JSR303数据验证**

1.导入依赖

```
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
```

2.修改Person类

```
@Validated
public class Person {
...
 @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    ...
    }
```

3.配置文件添加

```
person.email=we@163.com
```

4.运行测试类里的测试方法

**SpEL表达式**

@Value支持SpEL表达式

```
@value("#{5*2}")
private int id;
```

## 3.Spring Boot自定义配置

### 3.1使用@PropertySource加载配置文件

1.添加test.properties

```
#对实体类对象MyProperties进行属性配置
test.id=110
test.name=test
```

2.添加类MyProperties

```
@Configuration
@PropertySource("classpath:test.properties")
@EnableConfigurationProperties(MyProperties.class)
@ConfigurationProperties(prefix = "test")
public class MyProperties {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyProperties{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```

**说明：**

@Configuration注解用于表示当前类是一个自定义配置类

@PropertySource("classpath:test.properties")注解指定自定义配置文件的位置和名称

@EnableConfigurationProperties(MyProperties.class)注解开启对应配置类MyProperties的属性注入功能

3.添加测试方法进行测试

```
    @Autowired
    private MyProperties myProperties;
    @Test
    public void myPropertiesTest() {
        System.out.println(myProperties);
    }
```

### 3.2使用@ImportResource加载XML配置文件

传统Spring项目配置主要基于XML文件，Spring Boot默认不使用XML文件配置项目，且XML配置文件不会加载到Spring容器中，可使用该注解加载配置文件。

1.在项目下新建一个config包，并在该包下新创建一个类MyService，该类中不需要编写任何代码。

```
public class MyService {
}
```

2.在resources目录下新建一个名为beans.xml的XML自定义配置文件，在该配置文件中通过配置向Spring容器中添加MyService类对象。 

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                      http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="myService" class="com.sdut.demo03.config.MyService" />
</beans>
```

3.项目启动类上添加@ImportResource注解来指定XML文件位置。

```
@ImportResource("classpath:beans.xml")
@SpringBootApplication
```

4.在测试类中引入ApplicationContext实体类Bean，并新增一个测试方法进行输出测试。

```
  @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void iocTest() {
        System.out.println(applicationContext.containsBean("myService"));
    }
```

### 3.3使用@Configuration编写自定义配置类

使用@ImportResource加载XML配置文件，实际开发中在特殊情况下才会应用，基于“约定大于配置”思想，更推荐使用配置类的方式来代替XML配置。

1.在现有的项目基础上新建一个类MyConfig，使用@Configuration注解将该类声明一个配置类。

```
@Configuration
public class MyConfig {
    @Bean
    public MyService myService(){
        return new MyService();
    }
}
```

2.在项目启动类上添加的@ImportResource注解注释，执行项目测试类中的测试方法iocTest()

## 4.Profile多环境配置

**为什么需要多环境配置？**

在实际开发中，应用程序通常需要部署到不同的运行环境中，例如开发环境、测试环境、生产环境等。不同的环境可能需要不同的环境配置，针对这种情况，不可能手动变更配置文件来适应不同的开发环境，此时就需要对项目进行多环境配置。

**多环境配置方式**

Profile文件多环境配置

@Profile注解多环境配置

### 4.1使用Profile文件多环境配置

配置文件名application-{profile}.properties

**注：{profile}对应具体的环境标识**

例：

application-dev.properties

application-prod.properties

application-test.properties

**激活指定环境的方式**

1.通过命令行方式激活指定环境的配置文件

```
java -jar XXX.jar --spring.profiles.active=dev
```

2.在全局配置文件设置spring.profiles.active属性激活

```
spring.profiles.active=dev
```

### 4.2使用@Profile注解进行多环境配置

@Profile注解主要作用于类，并通过value属性指定配置环境

1.在项目config包下，创建一个配置数据库接口文件DBConnector

```
public interface DBConnector {
    public void configure();
}
```

2.在项目config包下，创建三个实现了接口DBConnector的类DevDBConnector、TestDBConnector、ProdDBConnector

```
@Configuration
@Profile("dev")
public class DevDBConnector implements DBConnector {
    @Override
    public void configure() {
        System.out.println("数据库配置环境dev");
    }
}
```

```
@Configuration
@Profile("prod")
public class ProdDBConnector implements DBConnector {
    @Override
    public void configure() {
        System.out.println("数据库配置环境prod");
    }
}
```

```
@Configuration
@Profile("test")
public class TestDBConnector implements DBConnector {
    @Override
    public void configure() {
        System.out.println("数据库配置环境test");
    }
}
```

3.在全局配置文件中设置spring.profiles.active属性激活@Profile注解构建的多环境

4.在项目controller包下新建DBController类

```
@RestController
public class DBController {
    @Autowired
    private DBConnector dbConnector;
    @GetMapping("/showDB")
    public void showDB()
    {
        dbConnector.configure();
    }
}
```

5.测试

## 5.随机值设置以及参数间引用

### 5.1随机值设置

**1.随机值设置**

${random.xx}

xx表示需要指定生成的随机数类型和范围

实例代码：

```
my.secret=${random.value} 
my.number=${random.int} 
my.bignumber=${random.long} 
my.uuid=${random.uuid}         
my.number.less.than.ten=${random.int(10)}    
my.number.in.range=${random.int[1024,65536]} 
```

**2.参数间引用**

${xx}

xx表示先前在配置文件中已经配置过的属性名。

实例代码：

```
app.name=MyApp
app.description=${app.name} is a Spring Boot application
```

例子：

（1）在全局配置文件添加测试属性

```
tom.age=${random.int[10,20]}
tom.description=Tom的年龄可能是${tom.age}
```

（2）在测试类添加如下代码

```
    @Value("${tom.description}")
    private String description;
    @Test
    public void placeholderTest() {
        System.out.println(description);
    }
```

