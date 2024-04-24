# 第8章Spring Boot消息服务

在实际项目开发中，有时候需要与其他系统进行集成完成相关业务功能，这种情况最原始的做法是程序内部相互调用，除此之外，还可以使用消息服务中间件进行业务处理，使用消息服务中间件处理业务能够提升系统的异步通信和扩展解耦能力。Spring Boot对消息服务管理提供了非常好的支持。

## 8.1消息服务概述

### 1.为什么要使用消息服务

在多数应用尤其是分布式系统中，消息服务是不可或缺的重要部分，它使用起来比较简单，同时解决了不少难题，例如异步处理、应用解耦、流量削锋、分布式事务管理等，使用消息服务可以实现一个高性能、高可用、高扩展的系统。

(1)异步处理

![](E:\java框架\SpringBoot课件\imgs\chap08\1.png)

（2）应用解耦

![](E:\java框架\SpringBoot课件\imgs\chap08\2.png)

（3）流量削峰

服务器处理资源能力有限，出现峰值时很容易造成服务器宕机、用户无法访问的情况

![](E:\java框架\SpringBoot课件\imgs\chap08\3.png)

（4）分布式事务管理

![](E:\java框架\SpringBoot课件\imgs\chap08\4.png)

分布式事务管理流程

- 订单支付成功后，写入消息表
- 定时扫描消息表消息写入到消息队列中
- 库存系统会立即读取到消息队列中的消息进行库存更新，同时添加消息处理状态
- 库存系统向消息队列中写入库存处理结果
- 订单系统会立即读取到消息队列中的库存处理状态。直接删除消息表数据，并写入到历史消息表

### 2.常用消息中间件介绍

(1)ActiveMQ (性能相对较弱)

ActiveMQ 是Apache出品，最流行的，能力强劲的开源消息总线。ActiveMQ 是一个完全支持JMS1.1和J2EE 1.4规范的 JMS Provider实现,尽管JMS规范出台已经是很久的事情了,但是JMS在当今的J2EE应用中间仍然扮演着特殊的地位。

(2)RabbitMQ

RabbitMQ 是一个由 Erlang 语言开发的 AMQP 的开源实现。RabbitMQ轻巧且易于部署在云端。 它支持多种消息传递协议。 RabbitMQ可以部署在分布式和联合配置中，以满足高规模，高可用性需求。RabbitMQ可运行在许多操作系统和云环境中，并为大多数流行语言提供广泛的开发工具。

**AMQP （Advanced MessageQueue）**：高级消息队列协议。它是应用层协议的一个开放标准，为面向消息的中间件设计，基于此协议的客户端与消息中间件可传递消息，并不受产品、开发语言等条件的限制。

RabbitMQ最初广泛应用于金融行业.

(3)Kafka

Kafka是由Apache软件基金会开发的一个开源流处理平台，由Scala和Java编写。Kafka是一种高吞吐量的分布式发布订阅消息系统，它可以处理消费者规模的网站中的所有动作流数据。 这种动作（网页浏览，搜索和其他用户的行动）是在现代网络上的许多社会功能的一个关键因素。 这些数据通常是由于吞吐量的要求而通过处理日志和日志聚合来解决。 对于像Hadoop的一样的日志数据和离线分析系统，但又要求实时处理的限制，这是一个可行的解决方案。Kafka的目的是通过Hadoop的并行加载机制来统一线上和离线的消息处理，也是为了通过集群来提供实时的消息。

Kafka它主要用于处理活跃的流式数据，因此Kafaka在大数据系统中使用较多。

(4)RocketMQ

RocketMQ是阿里开源的消息中间件，目前在Apache孵化，使用纯Java开发，具有高吞吐量、高可用性、适合大规模分布式系统应用的特点。RocketMQ思路起源于Kafka，但并不是简单的复制，它对消息的可靠传输及事务性做了优化，目前在阿里集团被广泛应用于交易、充值、流计算、消息推送、日志流式处理、binglog分发等场景，支撑了阿里多次双十一活动。

## 8.2RabbitMQ 消息中间件

### 1.RabbitMQ 简介

RabbitMQ是基于AMQP协议的轻量级、可靠、可伸缩和可移植的消息代理，Spring使用RabbitMQ通过AMQP协议进行通信，在Spirng Boot中对RabbitMQ进行了集成管理。

![](E:\java框架\SpringBoot课件\imgs\chap08\5.png)

### 2.RabbitMQ 工作模式介绍

(1)Work queues（工作队列模式）

![](E:\java框架\SpringBoot课件\imgs\chap08\6.png)

需要指定唯一的消息队列进行消息传递，并且可以有多个消息消费者。在这种模式下，多个消息消费者通过轮询的方式依次接收消息队列中存储的消息，一旦消息被某一个消费者接收，消息队列会将消息移除，而接收并处理消息的消费者必须在消费完一条消息后再准备接收下一条消息。

适用于那些较为繁重，并且可以进行拆分处理的业务，这种情况下可以分派给多个消费者轮流处理业务。

(2)Publish/Subscribe（发布订阅模式）

![](E:\java框架\SpringBoot课件\imgs\chap08\7.png)

在Publish/Subscribe工作模式中，必须先配置一个fanout类型的交换器，不需要指定对应的路由键（Routing key），同时会将消息路由到每一个消息队列上，然后每个消息队列都可以对相同的消息进行接收存储，进而由各自消息队列关联的消费者进行消费。

适用于进行相同业务功能处理的场合

(3)Routing（路由模式）

![](E:\java框架\SpringBoot课件\imgs\chap08\8.png)

在Routing工作模式中，必须先配置一个direct类型的交换器，并指定不同的路由键值（Routing key）将对应的消息从交换器路由到不同的消息队列进行存储，由消费者进行各自消费。

适用于进行不同类型消息分类处理的场合

(4)Topics（通配符模式）

![](E:\java框架\SpringBoot课件\imgs\chap08\9.png)

在Topics工作模式中，必须先配置一个topic类型的交换器，并指定不同的路由键值（Routing key）将对应的消息从交换器路由到不同的消息队列进行存储，然后由消费者进行各自消费。

适用于根据不同需求动态传递处理业务的场合

(5)RPC

![](E:\java框架\SpringBoot课件\imgs\chap08\10.png)

RPC模式是一个回环结构，主要针对分布式架构的消息传递业务，客户端Client先发送消息到消息队列，远程服务端Server获取消息，然后再写入另一个消息队列，向原始客户端Client响应消息处理结果。

适用于远程服务调用的业务处理场合

(6)Headers（使用较少，不进行详细介绍）

## 8.3RabbitMQ 安装以及整合环境搭建

### 1.安装RabbitMQ

(1)在RabbitMQ官网上http://www.rabbitmq.com/install-windows.html
下载,如果是在Windows环境下安装RabbitMQ消息中间件还需要64位的Erlang语言包支持。
(2)RabbitMQ安装包依赖于Erlang语言包的支持，所以要先安装Erlang语言包，再安装RabbitMQ安装包。

(3)RabbitMQ可视化效果展示, RabbitMQ默认提供了两个端口号5672和15672，其中5672用作服务端口号，15672用作可视化管理端口号。在浏览器上访问http://localhost:15672(用户名和密码均为guest)

**注意：**

```
1.安装目录尽量不要放到中文目录和带空格目录下
2.需要配置Erlang语言环境变量，配置方法同Java环境变量配置
```

![](E:\java框架\SpringBoot课件\imgs\chap08\11.png)

![](E:\java框架\SpringBoot课件\imgs\chap08\12.png)

```
3.装完RabbitMQ后尽量重启电脑一次
4.重启后若访问访问 http://localhost:15672/ ，访问失败需要执行以下命令
进入 RabbitMQ 的 sbin 目录，比如 D:\java\RabbitMQ Server\rabbitmq_server-3.7.14\sbin
启用管理功能
rabbitmq-plugins enable rabbitmq_management
启动服务
rabbitmqctl start_app
```

### 2.Spring Boot整合RabbitMQ环境搭建

(1)创建Spring Boot项目，添加Web依赖以及RabbitMQ依赖。

(2)在pplication.properties配置文件中编写需要设置的配置属性。

```
# 配置RabbitMQ消息中间件连接配置
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
#配置RabbitMQ虚拟主机路径/，默认可以省略
spring.rabbitmq.virtual-host=/
```

## 8.4Spring Boot与RabbitMQ整合实现

### 1.Publish/Subscribe（发布订阅模式）

**(1)基于API的方式**

**1)使用AmqpAdmin定制消息发送组件。**

a.在测试类中先引入AmqpAdmin管理类定制Publish/Subscribe工作模式所需的消息组件

```
 @Autowired
    private AmqpAdmin amqpAdmin;
    /**
     * 使用AmqpAdmin管理员API定制消息组件
     */
    @Test
    public void amqpAdmin() {
        // 1、定义fanout类型的交换器
        amqpAdmin.declareExchange(new FanoutExchange("fanout_exchange"));
        // 2、定义两个默认持久化队列，分别处理email和sms
        amqpAdmin.declareQueue(new Queue("fanout_queue_email"));
        amqpAdmin.declareQueue(new Queue("fanout_queue_sms"));
        // 3、将队列分别与交换器进行绑定
        amqpAdmin.declareBinding(new Binding("fanout_queue_email",Binding.DestinationType.QUEUE,"fanout_exchange","",null));
        amqpAdmin.declareBinding(new Binding("fanout_queue_sms",Binding.DestinationType.QUEUE,"fanout_exchange","",null));
    }
```

b.执行上述单元测试方法amqpAdmin()，验证RabbitMQ消息组件的定制效果，通过RabbitMQ可视化管理页面的Exchanges面板查看效果。

c.在RabbitMQ可视化管理页面的Exchanges面板中新出现了一个名称为fanout_exchange的交换器,且其类型是设置的fanout类型。单击fanout_exchange交换器进入查看。

d.切换到Queues面板页面，查看定制生成的消息队列信息

**2)消息发送者发送消息**

a.消息发送者发送消息，创建一个实体类User

```
public class User {
    private Integer id;
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
```

b.在项目测试类中使用Spring框架提供的RabbitTemplate模板类实现消息发送。

```
 @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 1、Publish/Subscribe工作模式消息发送端
     */
    @Test
    public void psubPublisher() {
        User user=new User();
        user.setId(1);
        user.setUsername("石头");
        rabbitTemplate.convertAndSend("fanout_exchange","",user);
    }
```

c.执行上述消息发送的测试方法psubPublisher()，控制执行效果如图

![](E:\java框架\SpringBoot课件\imgs\chap08\13.png)

转换器只支持Spring、byte[]和Serializable序列化后的消息

**异常的两种解决方式**：

- 将实体类实现JDK自带的Serializable序列化接口
- 定制其他类型的消息转换器

d.定制其他类型的消息转换器

创建一个RabbitMQ消息配置类RabbitMQConfig,并在该配置类中通过@Bean注解自定义了一个Jackson2JsonMessageConverter类型的消息转换器组件，该组件的返回值必须为MessageConverter类型

```
@Configuration
public class RabbitMQConfig {
    /**
     * 定制JSON格式的消息转换器
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
```

执行上述测试方法，执行成功，查看RabbitMQ可视化管理页面Queues面板信息，两个消息队列中各自拥有一条待接收的消息。

单击某个队列的详情页面，查看具体的信息

**3)消息消费者接收消息**

消息消费者接受消息,创建一个针对RabbitMQ消息中间件进行消息接收和处理的业务类RabbitMQService，在该类中编写如下方法

```
@Service
public class RabbitMQService {
        /**
     * Publish/Subscribe工作模式接收，处理邮件业务
     * @param message
     */
    @RabbitListener(queues = "fanout_queue_email")
    public void psubConsumerEmail(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        System.out.println("邮件业务接收到消息： "+s);

    }
    /**
     * Publish/Subscribe工作模式接收，处理短信业务
     * @param message
     */
    @RabbitListener(queues = "fanout_queue_sms")
    public void psubConsumerSms(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        System.out.println("短信业务接收到消息： "+s);
    }

}
```

启动项目来监听并接收消息队列中的消息。程序启动成功后，立即查看控制台打印结果

**(2)基于配置类的方式**

1）打开RabbitMQ消息配置类RabbitMQConfig，定义消息转换器、fanout类型的交换器、不同名称的消息队列以及将不同名称的消息队列与交换器绑定。

```
@Configuration
public class RabbitMQConfig {
    /**
     * 定制JSON格式的消息转换器
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    /**
     * 使用基于配置类的方式定制消息中间件
     * @return
     */
    // 1、定义fanout类型的交换器
    @Bean
    public Exchange fanout_exchange(){
        return ExchangeBuilder.fanoutExchange("fanout_exchange").build();
    }
    // 2、定义两个不同名称的消息队列
    @Bean
    public Queue fanout_queue_email(){
        return new Queue("fanout_queue_email");
    }
    @Bean
    public Queue fanout_queue_sms(){
        return new Queue("fanout_queue_sms");
    }
    // 3、将两个不同名称的消息队列与交换器进行绑定
    @Bean
    public Binding bindingEmail(){
        return BindingBuilder.bind(fanout_queue_email()).to(fanout_exchange()).with("").noargs();
    }
    @Bean
    public Binding bindingSms(){
        return BindingBuilder.bind(fanout_queue_sms()).to(fanout_exchange()).with("").noargs();
    }
}
```

**（3）基于注解的方式**

打开业务类RabbitMQService，使用@RabbitListener注解及其相关属性定制消息发送组件,在该类中编写处理邮件以及短信任务的方法

```
@Service
public class RabbitMQService {
    /**
     *  **使用基于注解的方式实现消息服务
     * 1.1、Publish/Subscribe工作模式接收，处理邮件业务
     * @param user
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("fanout_queue_email"), exchange =@Exchange(value = "fanout_exchange",type = "fanout")))
    public void psubConsumerEmailAno(User user) {
        System.out.println("邮件业务接收到消息： "+user);
    }
    /**
     * 1.2、Publish/Subscribe工作模式接收，处理短信业务
     * @param user
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("fanout_queue_sms"),exchange =@Exchange(value = "fanout_exchange",type = "fanout")))
    public void psubConsumerSmsAno(User user) {
        System.out.println("短信业务接收到消息： "+user);
    }

}
```

### 2.Routing(路由模式)

**(1)使用基于注解的方式定制消息组件和消息消费者**

打开业务类RabbitMQService，在该类中使用@RabbitListener注解及其相关属性定制Routing路由模式的消息组件，并模拟编写消息消费者接收的方法。

```
@Service
public class RabbitMQService {
    /**
     *  2.1、路由模式消息接收，处理error级别日志信息
     * @param message
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("routing_queue_error"),exchange =@Exchange(value = "routing_exchange",type = "direct"),key = "error_routing_key"))
    public void routingConsumerError(String message) {
        System.out.println("接收到error级别日志消息： "+message);
    }
    /**
     *  2.2、路由模式消息接收，处理info、error、warning级别日志信息
     * @param message
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("routing_queue_all"),exchange =@Exchange(value = "routing_exchange",type = "direct"),key = {"error_routing_key","info_routing_key","warning_routing_key"}))
    public void routingConsumerAll(String message) {
        System.out.println("接收到info、error、warning等级别日志消息： "+message);
    }
}
```

**(2)消息发送者发送消息**

在测试类中使用RabbitTemplate模板类实现Routing路由模式下的消息发送

```
/**
     *	2、Routing工作模式消息发送端
     */
    @Test
    public void routingPublisher() {
        rabbitTemplate.convertAndSend("routing_exchange","error_routing_key","routing send  error message");
    }
```

直接执行上述消息发送的测试方法routingPublisher()，控制台效果如图

![](E:\java框架\SpringBoot课件\imgs\chap08\14.png)

将测试方法routingPublisher()中进行消息发送的参数进行修改，调整发送info级别的日志信息（注意同时修改info_routing_key路由键），再次启动该测试方法，查看控制台执行效果

### 3.Topics(通配符模式)

**(1)使用基于注解的方式定制消息组件和消息消费者**

打开业务类RabbitMQService，在该类中使用@RabbitListener注解及其相关属性定制Topics通配符模式的消息组件，并模拟编写消息消费者接收的方法。

```
@Service
public class RabbitMQService {
    /**
     *  3.1、通配符模式消息接收，进行邮件业务订阅处理
     * @param message
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("topic_queue_email"),exchange =@Exchange(value = "topic_exchange",type = "topic"),key = "info.#.email.#"))
    public void topicConsumerEmail(String message) {
        System.out.println("接收到邮件订阅需求处理消息： "+message);
    }
    /**
     *  3.2、通配符模式消息接收，进行短信业务订阅处理
     * @param message
     */
    @RabbitListener(bindings =@QueueBinding(value =@Queue("topic_queue_sms"),exchange =@Exchange(value = "topic_exchange",type = "topic"),key = "info.#.sms.#"))
    public void topicConsumerSms(String message) {
        System.out.println("接收到短信订阅需求处理消息： "+message);
    }

}
```

**(2)消息发送者发送消息**

在项目测试类中使用RabbitTemplate模板类实现Routing路由模式下的消息发送。

```
/**
     *	3、Topcis工作模式消息发送端
     */
    @Test
    public void topicPublisher() {
        // 1、只发送邮件订阅用户消息
       rabbitTemplate.convertAndSend("topic_exchange","info.email","topics send  email message");
        // 2、只发送短信订阅用户消息
       // rabbitTemplate.convertAndSend("topic_exchange","info.sms","topics send  sms message");
        // 3、发送同时订阅邮件和短信的用户消息
       // rabbitTemplate.convertAndSend("topic_exchange","info.email.sms","topics send  email and sms message");
    }
```

执行测试方法topicPublisher()，先进行邮件订阅用户的消息发送

进行短信订阅用户的消息发送

同时进行邮件和短信订阅用户的消息发送方法
