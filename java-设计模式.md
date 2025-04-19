# 模式

## 单例模式

适用于只用来创建一个对象的 使用单例模式 可以减少资源消耗
比如 UUID 生成器 自己写的某些静态方法等等 


思路是创建一个类 类的属性是它本身 使用static private修饰保证只有一份
类的初始化方法使用 static 修饰 保证可以被外部直接调用而不用创建方法


```java
//懒汉式
public class Singleton {

    private static Singleton singleton=new Singleton();
    private Singleton(){
    }
    public  static Singleton getInstance(){
        return singleton;
    }
}

//饿汉式
public class Singleton {

    private static Singleton singleton;
    private Singleton(){
    }
    public  static Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
        }
    }
    return singleton;
}


//多线程饿汉式
public class Singleton {

    private static Singleton singleton;
    private Singleton(){
    }
    public synchronized static Singleton getInstance(){
        if(singleton==null){
            if(singleton==null){
                singleton=new Singleton();
            }
        }
        return singleton;
    }
}


//优化多线程饿汉式
/**
 * Main
 */
class Main {
    Singleton singleton=Singleton.getInstance();
}

public class Singleton {

    private static Singleton singleton;
    private Singleton(){
    }
    public  static Singleton getInstance(){
        if(singleton==null){
            synchronized(Singleton.class){
                if(singleton==null){
                    singleton=new Singleton();
                }
            }
        }
        return singleton;
    }
}
```


## 简单工厂模式
就像一个工厂 给他不同的参数 他会为你创建不同的对象, 不用我们手动创建了

比如`DateFormat()`类 根据传入参数的不同 从而进行不同的创建方法

手动写一个工厂类 创建对象的时候校验传入的参数 根据参数的不同返回不同的对象
    使用 接口 或者抽象类 来保证调用和返回值的通用性


```java
//使用抽象类的
abstract class Re {
    public void speak() {
    }
}

// 实现类1
class Dog extends Re{
    public void speak() {
        System.out.println("汪汪汪");
    }
}

// 实现类2
class Cat extends Re{
    public void speak() {
        System.out.println("喵喵喵");
    }
}

// 工厂类
class AnimalFactory {
    public Re createAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) {
            return new Dog();
        } else if ("cat".equalsIgnoreCase(type)) {
            return new Cat();
        } else {
            throw new IllegalArgumentException("Invalid animal type");
        }
    }
}

// 使用工厂方法创建对象
public class Main {
    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();
        
        Re dog = factory.createAnimal("dog");
        dog.speak(); // 输出：汪汪汪
        
        Re cat = factory.createAnimal("cat");
        cat.speak(); // 输出：喵喵喵
    }
}


```






## 抽象工厂
一般使用抽象类来实现 抽象类可以提供一些通用的方法和属性 而 接口通常用于定义一组方法的规范
一个抽象父类工厂 多个子类工厂

抽象工厂模式旨在提供一个创建一系列相关或相互依赖对象的接口，而无需指定具体类。
抽象工厂可以避免 简单工厂违法开闭模式的问题



## 装饰着模式
BufferedInputStream
BufferedOutputStream





## 适配器模式
原来的接口 对当前不太试用 但是我们不想修改原来的接口
    用于接口不兼容 或者拓展功能使用
使用装饰者模式会修改原来的接口


```java
// 目标接口
interface Target {
    void request();
}



// 需要适配的类
class Adaptee {
    public void specificRequest() {
        System.out.println("Adaptee's specific request");
    }
}


// 适配器类
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}

public class Main {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        
        target.request();
    }
}

```


## 观察者模式 (发布订阅模式)




















# 原则

## 开闭原则

## 里氏替换
子类可以替换父类
子类的行为 是可以预期的
不建议抛出父类没有的异常 这样使用父类的话还要学习子类 增加代码的复杂度



## 接口隔离
接口尽量细化，同时接口中的方法尽量少



## 依赖倒置








# 其他
高内聚
提高接口、类、模块的处理能力，减少对外的交互

低耦合
类 接口 方法之间的耦合成都要降低 易于维护和拓展

