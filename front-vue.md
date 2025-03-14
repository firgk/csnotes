```vue
<div id="app">{{msg}}</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.7.16/dist/vue.js"></script>

<script>
const app = new Vue({
  el: "#app", // 挂载点
  data: {
    //数据
    msg: "hello 黑马",
  },
});
</script>

<div id="app">{{msg+100}}</div>

<p>{{nickName.toUpperCase()}}</p>
<p>{{age >= 18 ? '成年':'未成年'}}</p>
<p>{{obj.name}}</p>
<p>{{fn()}}</p>

错误用法 1.在插值表达式中使用的数据 必须在data中进行了提供
<p>{{hobby}}</p>
//如果在data中不存在 则会报错 2.支持的是表达式，而非语句，比如：if for ...
<p>{{if}}</p>
3.不能在标签属性中使用 {{}} 插值 (插值表达式只能标签中间使用)
<p title="{{username}}">我是P标签</p>

1 . 什么是响应式 2 . 如何访问和修改data中的数据呢
```

## 指令

```
内容渲染指令（v-html、v-text）
● 条件渲染指令（v-show、v-if、v-else、v-else-if）
● 事件绑定指令（v-on）
● 属性绑定指令 （v-bind）
● 双向绑定指令（v-model）
● 列表渲染指令（v-for）
```

```vue
<div id="app">
  <h2>个人信息</h2>
  // 既然指令是vue提供的特殊的html属性，所以咱们写的时候就当成属性来用即可
  <p v-text="uname">姓名：</p>
  <p v-html="intro">简介：</p>
</div>

<script>
const app = new Vue({
  el: "#app",
  data: {
    uname: "张三",
    intro: "<h2>这是一个<strong>非常优秀</strong>的boy<h2>",
  },
});
</script>
```

## 条件渲染指令

v-show 频繁

v-if 不频繁
v-else 和 v-else-if

```vue
<div id="app">
  <p>性别：♂ 男</p>
  <p>性别：♀ 女</p>
  <hr />
  <p>成绩评定A：奖励电脑一台</p>
  <p>成绩评定B：奖励周末郊游</p>
  <p>成绩评定C：奖励零食礼包</p>
  <p>成绩评定D：惩罚一周不能玩手机</p>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script>
const app = new Vue({
  el: "#app",
  data: {
    gender: 2,
    score: 95,
  },
});
</script>
```

## 事件绑定指令

v-on: 简写为 @

<div id="app">
  <button @click="count--">-</button> <span>{{ count }}</span>
  <button v-on:click="count++">+</button>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script>
  const app = new Vue({ el: "#app", data: { count: 100 } });
</script>

事件处理函数

<div id="app">
  <button>切换显示隐藏</button>
  <h1 v-show="isShow">黑马程序员</h1>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script>
  const app = new Vue({
    el: "#app",
    data: {
      isShow: true,
    },
  });
</script>

XXXXXXXXXXXXXXXXXXXX

<style>
  .box {
    border: 3px solid #000000;
    border-radius: 10px;
    padding: 20px;
    margin: 20px;
    width: 200px;
  }
  h3 {
    margin: 10px 0 20px 0;
  }
  p {
    margin: 20px;
  }
</style>
<div id="app">
  <div class="box">
    <h3>小黑自动售货机</h3>
    <button>可乐5元</button>
    <button>咖啡10元</button>
    <button>牛奶8元</button>
  </div>
  <p>银行卡余额：{{ money }}元</p>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<script>
  const app = new Vue({
    el: "#app",
    data: {
      money: 100,
    },
  });
</script>
