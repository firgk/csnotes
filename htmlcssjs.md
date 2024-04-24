# html css js

> 文档 [菜鸟教程](https://www.cainiaojc.com/)  



## html

### 文本格式化标签

```html
<body>
    我是<strong>加粗</strong>的文字
    我是<b>加粗</b>的文字
    我是<em>倾斜</em>的文字
    我是<i>倾斜</i>的文字
    我是<del>删除线</del>
    我是<s>删除线</s>
    我是<ins>下划线</ins>
    我是<u>下划线</u>
</body>
```



### 图片

```html
<img src="img.jpg" width="300" height="200" />
```



### 插入视频

```html
<video src="./nothitdog.mp4" position= "absolute" width="100%" height="100%" controls="controls"></video>
<video src="./nothitdog.mp4" controls="controls"></video>
```




### 超链接

```html
<body>
    <!-- 打开新窗口 -->
    <a href="http://www.qq.com" target="_blank"> 腾讯</a>
    <!-- target 打开窗口的方式  默认的值是 _self 当前窗口打开页面  _blank 新窗口打开页面 -->
    <a href="gongsijianjie.html">公司简介</a>
    <!-- 给元素套上链接 -->
    <a href="http://www.baidu.com"><img src="img.jpg"/></a>
</body>
```


### 锚点定位

```html
<!-- 设置锚点 -->
<h3 id="live">个人生活</h3>
<!-- 跳转到锚点 -->
<a href="#live">个人生活</a><br />
```





### 表格

```html
<!-- 这些属性要写到表格标签table 里面去 -->
<table>
    <tr>
        <th>姓名</th>  <th>性别</th> <th> 年龄 </th>
    </tr>
    <tr>
        <td>刘德华</td>  <td>男</td> <td> 56 </td>
    </tr>
    <tr>
        <td>张学友</td>  <td>男</td> <td> 58 </td>
    </tr>
    <tr>
        <td>郭富城</td>  <td>男</td> <td> 51 </td>
    </tr>
</table>

```

#### 合并单元格

```html
<table width="500" height="249" border="1" cellspacing="10">
    <tr>
        <td></td>
        <td colspan="2"></td>
        
    </tr>
    <tr>
        <td rowspan="2"></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
    </tr>
</table>
```

#### 列表

```html
<ul>
    <li>榴莲</li>
    <li>臭豆腐</li>
    <li>鲱鱼罐头</li>
    <li>
        <p>123</p>
    </li>
</ul>

<ol>
    <li>刘德华 10000</li>
    <li>刘若英 1000</li>
    <li>pink老师 1</li>
</ol>
```

### 表单

```html
<form action="/api" method="POST" name="name">
    ...
</form>
```


#### 注册的表单

![](http://leaweihou.site:1003/photobed/2023_12_21_21_09_22.png)

```html
<form action="xxx.php" method="get">
        <!-- text 文本框 用户可以里面输入任何文字 -->
    用户名: <input type="text" name="username" value="请输入用户名" maxlength="6">   <br> 
    密码: <input type="password" name="pwd" >  <br> 
    <!-- radio 单选按钮  可以实现多选一 -->
    <!-- name 是表单元素名字 这里性别单选按钮必须有相同的名字name 才可以实现多选1 -->
    <!-- 单选按钮和复选框可以设置checked 属性, 当页面打开的时候就可以默认选中这个按钮 -->
    性别: 男 <input type="radio" name="sex" value="男"> 女  <input type="radio" name="sex" value="女" checked="checked"> 人妖   <input type="radio" name="sex" value="人妖">   <br> 
    <!-- checkbox 复选框  可以实现多选 -->
    爱好: 吃饭 <input type="checkbox" name="hobby" value="吃饭"> 睡觉 <input type="checkbox" name="hobby">  打豆豆 <input type="checkbox" name="hobby" checked="checked"> 
    <br> 
    <!-- 点击了提交按钮,可以把 表单域 form 里面的表单元素 里面的值 提交给后台服务器 -->
    <input type="submit" value="免费注册">
    <!-- 重置按钮可以还原表单元素初始的默认状态 -->
    <input type="reset" value="重新填写">
    <!-- 普通按钮 button  后期结合js 搭配使用-->
    <input type="button" value="获取短信验证码"> <br>
    <!-- 文件域 使用场景 上传文件使用的 -->
    上传头像:  <input type="file" >
</form>
```


下拉表单


```html
<form>
籍贯: 
<select>
    <option>山东</option>
    <option>北京</option>
    <option>天津</option>
    <option selected="selected">火星</option>
</select>
</form>
```



### 文本域

```html

<form>
    今日反馈:
    <textarea cols="50" rows="5">pink老师,我知道这个反馈留言是textarea来做的 </textarea>
</form>

```
![](http://leaweihou.site:1003/photobed/2023_12_21_21_11_17.png)




### 页脚
`<p class="footer"> 本文来源：中国天气网 责任编辑：刘京_NO5631</p>`





### 背景图片
```background-image: url(images/logo.png);```






## css


### 标签选择器  类选择器  和id选择器

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>菜鸟教程(runoob.com)</title>
</head>
<style>
    .timer-body{
        p {
            color: #666;
        }
    }
    /* 加框 */
    div{   
        border-style: solid;
        border-color: blue;
    }
</style>
<body>
<h1>计时器</h1>
<div class="timer-body">
    <h1>你好世界</h1>
</div>
</body>
</html>
```
id
```html
<div id="container"></div>

<style>
    #container{
        margin-left: 20px;
        border: solid 2px #94D4C4;
        border-radius: 5px;
        font-family: 楷体;
        width: 200px;
        float:left;
    }
</style>

```

二级调用

```html
<style>
    .box {
    width: 298px;
    height: 415px;
    background-color:#fff;
    /* 让块级的盒子水平居中对齐 */
    margin: 100px auto;
    }
    .box img {
        /* 图片的宽度和父亲一样宽 */
        width: 100%;
    }
</style>
```


多个选择器 共用

```html
<style>
    .red {
        color:  red;
    }
    .font35 {
        font-size: 35px;
    }
</style>
<div class="red font35">刘德华</div>
```

ID的优先级最高，用于特定或者特指的

### 行内特殊

```html
<p>我的母亲有 <span style="color:blue">蓝色</span>的眼睛。</p>
```








###  鼠标经过链接变换背景颜色

```html
    a:hover {
        background-color: #ff6700;
    }
```



## js



### ajax

```html
<script>
    $.ajax({
        url: "/api/changeTableName",
        type: "GET",
        <!-- data: "参数传递", -->
        data: {
            name: newTimerName
        },
        success: function (result, textStatus) {
            alert("成功");
            console.log(result)

        },//请求成功执行success函数
        error: function (xhr, errorMessage, e) {
            alert("失败");
        }//失败执行error函数
    });
</script>
```



