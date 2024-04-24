# 第9章  Spring Boot 任务管理

 开发Web应用时，多数应用都具备任务调度功能。常见的任务包括异步任务、定时任务和发邮件任务。

 我们以数据库报表为例看看任务调度如何帮助改善系统设计。报表可能是错综复杂的，用户可能需要很长时间找到需要的报表数据，此时，我们可以在这个报表应用中添加异步任务减少用户等待时间，从而提高用户体验；除此之外，还可以在报表应用中添加定时任务和邮件任务，以便用户可以安排在任何他们需要的时间定时生成报表，并在Email中发送。

## 9.1异步任务

### 1.无返回值异步任务调用

(1)Spring Boot 项目创建

创建一个Spring Boot项目，选择Web模块中的Web依赖

(2)编写异步调用方法

编写异步调用方法，模拟用户短信验证码发送

创建一个业务实现类MyAsyncService，在该类中模拟编写用户短信验证码发送的方法

```
@Service
public class MyAsyncService {
    /**
     * 模拟无返回值的异步任务处理
     * @throws Exception
     */
    @Async
    public void sendSMS() throws Exception {
        System.out.println("调用短信验证码业务方法...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(5000);
        Long endTime = System.currentTimeMillis();
        System.out.println("短信业务执行完成耗时：" + (endTime - startTime));
    }
}
```

(3)开启基于注解的异步任务支持

使用@EnableAsync开启基于注解的异步方法支持

```
@EnableAsync
@SpringBootApplication
public class Demo09Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo09Application.class, args);
    }

}
```

(4)编写控制层业务调用方法

创建一个异步方法调用的实现类MyAsyncController，在该类中模拟编写用户短信验证码发送的处理方法

```
@RestController
public class MyAsyncController {
    @Autowired
    private MyAsyncService myService;

    @GetMapping("/sendSMS")
    public String sendSMS() throws Exception {
        Long startTime = System.currentTimeMillis();
        myService.sendSMS();
        Long endTime = System.currentTimeMillis();
        System.out.println("主流程耗时： "+(endTime-startTime));
        return "success";
    }
}
```

(5)异步任务效果测试

浏览器上访问http://localhost:8080/sendSMS

![](E:\java框架\SpringBoot课件\imgs\chap09\1.png)

**注意：**上述异步方法是没有返回值的，这样主流程在执行异步方法时不会阻塞，而是继续向下执行主流程程序，直接向页面响应结果，而调用的异步方法会作为一个子线程单独执行，直到异步方法执行完成。

### 2.有返回值异步任务调用

(1)编写异步调用方法

在MyAsyncService业务处理类中，添加两个模拟有返回值的异步任务业务处理方法

```
/**
     * 模拟有返回值的异步任务处理
     * @return
     * @throws Exception
     */
    @Async
    public Future<Integer> processA() throws Exception {
        System.out.println("开始分析并统计业务A数据...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(4000);
        // 模拟定义一个假的统计结果
        int count=123456;
        Long endTime = System.currentTimeMillis();
        System.out.println("业务A数据统计耗时：" + (endTime - startTime));
        return new AsyncResult<Integer>(count);
    }
    @Async
    public Future<Integer> processB() throws Exception {
        System.out.println("开始分析并统计业务B数据...");
        Long startTime = System.currentTimeMillis();
        Thread.sleep(5000);
        // 模拟定义一个假的统计结果
        int count=654321;
        Long endTime = System.currentTimeMillis();
        System.out.println("业务B数据统计耗时：" + (endTime - startTime));
        return new AsyncResult<Integer>(count);
    }
```

(2)编写控制层业务调用方法

在MyAsyncController业务处理类中，编写业务数据分析统计的请求处理方法

```
@GetMapping("/statistics")
    public String statistics() throws Exception {
        Long startTime = System.currentTimeMillis();
        Future<Integer> futureA = myService.processA();
        Future<Integer> futureB = myService.processB();
        int total = futureA.get() + futureB.get();
        System.out.println("异步任务数据统计汇总结果： "+total);
        Long endTime = System.currentTimeMillis();
        System.out.println("主流程耗时： "+(endTime-startTime));
        return "success";
    }
```

(3)异步任务效果测试

在浏览器上访问http://localhost:8080/statistics

![](E:\java框架\SpringBoot课件\imgs\chap09\2.png)

**注意：**上述异步方法是有返回值的，这样主流程在执行异步方法时会有短暂阻塞，需要等待并获取异步方法的返回结果，而调用的两个异步方法会作为两个子线程并行执行，直到异步方法执行完成并返回结果，这样主流程会在最后一个异步方法返回结果后跳出阻塞状态。

## 9.2定时任务

### 1.定时任务介绍

(1)@EnableScheduling

启动定时任务支持，主要作用于项目启动类上。

(2)@Scheduled

主要作用定时任务上

1）fixedDelay属性

该属性是程序启动后每ms执行一次

2）cron属性

这是一个时间表达式，可以通过简单的配置就能完成各种时间的配置，我们通过CRON表达式几乎可以完成任意的时间搭配，它包含了六或七个域：

```
Seconds : 可出现", - * /"四个字符，有效范围为0-59的整数
Minutes : 可出现", - * /"四个字符，有效范围为0-59的整数
Hours : 可出现", - * /"四个字符，有效范围为0-23的整数
DayofMonth : 可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
Month : 可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
DayofWeek : 可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
Year : 可出现", - * /"四个字符，有效范围为1970-2099年
```

推荐一个在[线Cron表达式](http://cron.qqe2.com/)生成器http://cron.qqe2.com/

几个简单例子

```
"0 0 12 * * ?" 每天中午十二点触发
"0 15 10 ? * *" 每天早上10：15触发
"0 15 10 * * ?" 每天早上10：15触发
"0 15 10 * * ? *" 每天早上10：15触发
"0 15 10 * * ? 2005" 2005年的每天早上10：15触发
"0 * 14 * * ?" 每天从下午2点开始到2点59分每分钟一次触发
"0 0/5 14 * * ?" 每天从下午2点开始到2：55分结束每5分钟一次触发
"0 0/5 14,18 * * ?" 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
"0 0-5 14 * * ?" 每天14:00至14:05每分钟一次触发
"0 10,44 14 ? 3 WED" 三月的每周三的14：10和14：44触发
"0 15 10 ? * MON-FRI" 每个周一、周二、周三、周四、周五的10：15触发
```

3）fixedRate属性

该属性的含义是上一个调用开始后再次调用的延时（不用等待上一次调用完成），这样就会存在重复执行的问题，所以不是建议使用，但数据量如果不大时在配置的间隔时间内可以执行完也是可以使用的。

4）initialDelay属性

该属性的作用是第一次执行延迟时间

### 2.定时任务实现

(1)编写定时任务业务处理方法

新建一个定时任务管理的业务处理类ScheduledTaskService，并在该类中编写对应的定时任务处理方法。使用@Scheduled注解声明了三个定时任务方法，这三个方法定制的执行规则基本相同，都是每隔1分钟重复执行一次定时任务，在使用fixedDelay属性的方法scheduledTaskAfterSleep()中，使用Thread.sleep(10000)模拟该定时任务处理耗时为10秒钟。

```
@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Integer count1 = 1;
    private Integer count2 = 1;
    private Integer count3 = 1;

    @Scheduled(fixedRate = 60000)
    public void scheduledTaskImmediately() {
        System.out.println(String.format("fixedRate第%s次执行，当前时间为：%s", count1++, dateFormat.format(new Date())));
    }

    @Scheduled(fixedDelay = 60000)
    public void scheduledTaskAfterSleep() throws InterruptedException {
        System.out.println(String.format("fixedDelay第%s次执行，当前时间为：%s", count2++, dateFormat.format(new Date())));
        Thread.sleep(10000);
    }

    @Scheduled(cron = "0 * * * * *")
    public void scheduledTaskCron(){
        System.out.println(String.format("cron第%s次执行，当前时间为：%s",count3++, dateFormat.format(new Date())));
    }
}
```

(2)开启基于注解的定时任务支持

在项目启动类上开启基于注解的定时任务支持

```
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class Demo09Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo09Application.class, args);
    }

}

```

(3)定时任务效果测试

![](E:\java框架\SpringBoot课件\imgs\chap09\3.png)

结论：配置@Scheduled注解的fixedRate和fixedDelay属性的定时方法会立即执行一次，配置cron属性的定时方法会在整数分钟时间点首次执行；接着，配置fixedRate和cron属性的方法会每隔1分钟重复执行一次定时任务，而配置fixedDelay属性的方法是在上一次方法执行完成后再相隔1分钟重复执行一次定时任务。

## 9.3邮件任务

### 1.发送纯文本邮件

(1)添加邮件服务的依赖启动器

在pom.xml文件中添加邮件服务的依赖启动器

```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
```

注：
当添加上述依赖后，Spring Boot自动配置的邮件服务会生效，在邮件发送任务时，可以直接使用Spring框架提供的JavaMailSender接口或者它的实现类JavaMailSenderImpl邮件发送。

(2)添加邮件服务配置

在全局配置文件中添加邮件服务配置

```
# 发件人邮服务器相关配置
spring.mail.host=smtp.qq.com
spring.mail.port=587
# 配置个人QQ账户和密码（密码是加密后的授权码）
spring.mail.username=2127269781@qq.com
spring.mail.password=ijdjokspcbnzfbfa
spring.mail.default-encoding=UTF-8
# 邮件服务超时时间配置
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=3000
spring.mail.properties.mail.smtp.writetimeout=5000
```

(3)定制邮件发送服务

新建一个邮件发送任务管理的业务处理类SendEmailService，编写了一个发送纯文本邮件的sendSimpleEmail()方法，在该方法中通过SimpleMailMessage类定制了邮件信息的发件人地址（From）、收件人地址（To）、邮件标题（Subject）和邮件内容（Text），最后使用JavaMailSenderImpl的send()方法实现纯文本邮件发送。

```
@Service
public class SendEmailService {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送纯文本邮件
     * @param to       收件人地址
     * @param subject  邮件标题
     * @param text     邮件内容
     */
    public void sendSimpleEmail(String to,String subject,String text){
        // 定制纯文本邮件信息SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            // 发送邮件
            mailSender.send(message);
            System.out.println("纯文本邮件发送成功");
        } catch (MailException e) {
            System.out.println("纯文本邮件发送失败 "+e.getMessage());
            e.printStackTrace();
        }
    }
}
```

(4)纯文本邮件发送效果测试

先定制了纯文本邮件发送方法所需要的参数（示例中定制了给自己邮箱发送邮件），然后调用业务方法实现了纯文本邮件发送。

```
    @Autowired
    private SendEmailService sendEmailService;
    @Test
    public void sendSimpleMailTest() {
        String to="2127269781@qq.com";
        String subject="【纯文本邮件】标题";
        String text="Spring Boot纯文本邮件发送内容测试.....";
        // 发送简单邮件
        sendEmailService.sendSimpleEmail(to,subject,text);
    }
```

接启动单元测试方法sendSimpleMailTest()

### 2.发送带附件和图片的邮件

(1)定制邮件发送服务

打开之前创建的邮件发送任务的业务处理类SendEmailService，在该类中编写一个发送带附件和图片邮件的业务方法sendComplexEmail() ，该方法需要接收的参数除了基本的发送信息外，还包括静态资源唯一标识、静态资源路径和附件路径。

```
 /**
     * 发送复杂邮件（包括静态资源和附件）
     * @param to           收件人地址
     * @param subject      邮件标题
     * @param text         邮件内容
     * @param filePath     附件地址
     * @param rscId        静态资源唯一标识
     * @param rscPath      静态资源地址
     */
    public void sendComplexEmail(String to,String subject,String text,String filePath,String rscId,String rscPath){
        // 定制复杂邮件信息MimeMessage
        MimeMessage message = mailSender.createMimeMessage();
        try {
            // 使用MimeMessageHelper帮助类，并设置multipart多部件使用为true
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            // 设置邮件静态资源
            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);
            // 设置邮件附件
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            // 发送邮件
            mailSender.send(message);
            System.out.println("复杂邮件发送成功");
        } catch (MessagingException e) {
            System.out.println("复杂邮件发送失败 "+e.getMessage());
            e.printStackTrace();
        }
    }
```

(2)邮件发送效果测试

在项目测试类中添加一个方法调用带附件和图片的复杂邮件发送的方法实现邮件发送效果测试，根据前面定义的复杂邮件发送业务方法定制了各种参数。其中，在定义邮件内容时使用了Html标签编辑邮件内容，并内嵌了一个标识为rscId的图片，并为邮件指定了携带的附件路径。在邮件发送之前，务必保证指定路径下存放有对应的静态资源和附件文件。

```
@Test
    public void sendComplexEmailTest() {
        String to="2127269781@qq.com";
        String subject="【复杂邮件】标题";
        // 定义邮件内容
        StringBuilder text = new StringBuilder();
        text.append("<html><head></head>");
        text.append("<body><h1>祝大家元旦快乐！</h1>");
        // cid为固定写法，rscId指定一个唯一标识
        String rscId = "img001";
        text.append("<img src='cid:" +rscId+"'/></body>");
        text.append("</html>");
        // 指定静态资源文件和附件路径
        String rscPath="F:\\email\\newyear.jpg";
        String filePath="F:\\email\\元旦放假注意事项.docx";
        // 发送复杂邮件
        sendEmailService.sendComplexEmail(to,subject,text.toString(),filePath,rscId,rscPath);
    }
```

### 3.发送模板邮件

(1)添加Thymeleaf模板引擎依赖启动器

在pom.xml文件中添加Thymeleaf模板引擎依赖启动器

```
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

(2)定制模板邮件

定制模板邮件，添加发送用户注册验证码的模板页面

```
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>用户验证码</title>
</head>
<body>
    <div><span th:text="${username}">XXX</span>&nbsp;先生/女士,您好：</div>
    <P style="text-indent: 2em">您的新用户验证码为<span th:text="${code}" style="color: cornflowerblue">123456</span>，请妥善保管。</P>
</body>
</html>
```

(3)定制邮件发送服务

定制邮件发送服务，模拟发送html模板邮件

在业务处理类SendEmailService中编写一个发送Html模板邮件的业务方法，sendTemplateEmail()方法主要用于处理Html内容（包括Thymeleaf邮件模板）的邮件发送，在定制Html模板邮件信息时，、使用了MimeMessageHelper类对邮件信息进行封装处理。

```
/**
     * 发送模板邮件
     * @param to       收件人地址
     * @param subject  邮件标题
     * @param content  邮件内容
     */
    public void sendTemplateEmail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            // 使用MimeMessageHelper帮助类，并设置multipart多部件使用为true
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            // 发送邮件
            mailSender.send(message);
            System.out.println("模板邮件发送成功");
        } catch (MessagingException e) {
            System.out.println("模板邮件发送失败 "+e.getMessage());
            e.printStackTrace();
        }
    }
```

(4)模板邮件发送效果测试

在项目测试类中添加一个方法调用前面编写的Html模板邮件发送方法测试邮件发送效果，先使用@Autowired注解引入了Thymeleaf提供的模板引擎解析器TemplateEngine，然后定制了模板邮件发送所需的参数。

```
 @Test
    public void sendTemplateEmailTest() {
        String to="2127269781@qq.com";
        String subject="【模板邮件】标题";
        // 使用模板邮件定制邮件正文内容
        Context context = new Context();
        context.setVariable("username", "石头");
        context.setVariable("code", "456123");
        // 使用TemplateEngine设置要处理的模板页面
        String emailContent = templateEngine.process("emailTemplate_vercode", context);
        // 发送模板邮件
        sendEmailService.sendTemplateEmail(to,subject,emailContent);
    }
```

