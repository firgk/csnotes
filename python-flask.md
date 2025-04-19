







```python

debug 模式
app.run(debug==True)
错误信息会在浏览器中显示
热部署


支持外部访问
—host=0.0.0.0 
—port=8000
@app.route(“/“)
def hello_world():
	return render_template(“index.html”)
	# return “hello_world”

 
@app.route(<int:blog_id>)
Def …(blog_id):
	Return …”is : %s” % blog_id
<blog_id>
Def …(blog_id):
	Return …”is : %s” % blog_id


渲染html
访问成员
传如参数


过滤器
def datetime_format(value,format=“%Y-%d-%m %H:%M”):
	return value.strftime(format)
app.add_template_filter(datetime_formate,”dformat”)
# dformat. is just name
自定意过滤器
dformate 为 名字
可以实现一些简单的操作

Class User:
	def __init__(self,username,email)
		self.username=username
		self.email=email
@app.route(‘/’)
def hello_world():
	user=User(username=“治疗”,email=“qq.com”)
	return user.name



{%if            if%}
{%else if.      %}
{else }
{%endif% }




{{name.value}}                          
                                        
                                        
                                        
..                                      
{% extends "base.html" %}               
....                                    
base.html                               
{%block title%}{%endblock%}             
child1.html                             
{%block title%}..{%endblock%}           
                                        
                                        
pymysql                                 
pip intall pymysql                      
flask-sqlalchemy                   
......falsk........                     
..flask........                         

HOSTNAME=“127.0.0.1”
PORT-=3306
USERNAME=“root”
PASSWORD=“”
DATABASE=“database_learn”
app.config[‘SQLALCHEMY_DATABASE_URI’] = f”mysql+pymysql://{USERNAME}:{PASSWORD}@{HOSTNAME}:{PORT}/{DATABASE}?charset=utf-8”
db=SQLAlchemy(app)
# test can use or not
with db.engine.connect() as conn:
	rs=conn.execute(“select 1”)
	print(reps.fetchone())



ORM
user=User(username=“…”,password=“…”)
db.session.add(user)
db.session.commit()
	return “用户创建成功”


查找


def query_user():
	# get 查找 ；根据主键
	User.query.get(1)
	print(f”{user.id}:{user.username}—-{user.password}”)
	users=User.query.filter_by(username=“…”)
for user in Users:
	print(user.username)
	return “查找成功”

update
@app.route(“/user/update”)
def update_user():
	user=User.query.filter_by(username=“…”).first
#why first ? when got nothing got null not Exception
user.password	=“22222”
db.session.commit()
return “数据修改成功 ”

del
find->del->commit()
user=User.query.get(1)
db.session.delete(user)
db.session.commit()












                                       
上下文出问题
withapp.app_context():                  
        要执行的内容                 


# 涉及到外键
class User(db.Model):
	__tablename__=“user”
	id=db.Column(db.Integer,primary_key=Ture,autoincrement=True)
	userame=db.Column(db.String(100),nullable=False)
password=db.Column(db.String(100),nullable=False)

class Article(db.Model):
	__tablename__=“article”
	】id=db.Column(db.Integer,primary_key=Ture,autoincrement=True)
	titile=db.Column(db.String(100),nullable=False)
content=db.Column(db.String(100),nullable=False)



构建项目
config.py
数据库连接信息
models.py
from exits import db
class UserModel(db.Model):
	__tablename__=“user”
	id=db .Column(db.Inter,primary_key=True,autoincrement=True)
	username=db.Column(db.String(100),nullable=False)

	password=db.Column(db.String(100),nullable=False)
	email=db.Column(db.String(100),nullable=False,unique=True)
join_time=db.Column(db.Datetime,default=datetime.now)

auth.py
from flask import Blueprint
bp=Bludprint(“auth”,__name__,url_prefix=“/auth”)
@bp.route(“/login”)
def login():
	pass



app.py
from exits import db
from models import UserModel
from blueprints.qa import bp as qa_bp
from bludprints.auth import bp as auth_bp
from flask migrate import Migrate
 

app=Flask(__name__)
app.config.from_object(config)
db.init_app(app)
migrate =Migrate(app,db)
app.register_blueprint(qa_bp)
app.register_blueprint(auth_bp)

console
flask db init
flask db migrate
flask db upgrade



 


auth.py
from flask import Blueprint
bp=Bludprint(“auth”,__name__,url_prefix=“/auth”)
@bp.route(“/login”)
def login():
	pass

# update auth.py add
@bp.route(“/register”)
def register():
	return render-template(“register.html”)


# html’s css can’t use
you use render_template ,and it did not support relative path
herf=“./css/a.css”
herf={{url_for(‘css’,filename=‘a.css ’)}}
jingja2


flask-mail
ex qqmail
开启pop3/smtp
config.py
MAIL_SERVER=“smtp.qq.com” 
# if 163 163
MAIL_SER_SSL=True
MAIL_PORT=456
MAIL_USERNAME=“邮箱账号”
MAIL_PASSWORD=“开启SMTP服务时产生的授码”
MAIL_DEFAULT_SENDER+”邮箱账号”






exits.py






















  
循环引用
1->2
2->1

1->2
2->3
3->1
还是循环
可以尝试切断第二个中的一个链接，然后反过来引用，即在用的时候创建







 














exits.py

from flask_sqlalchemy import SQLAlchemy
from flask_mail import Mail

db=SQLAlchemy
mail =Mail()



app.py
from exits import mail
mail.init_app(app)




auth.py
from exits import mail
from flask_mail import message
@bp.route(“/mail/test”)
def mail_test():
message=Message(subject=“邮箱测试”,recipients=[“22@qq.com”],body=“这是一个测试邮件”)
mail.send(message)
return “发送成功”



auth.py
@bp.route(“/captcha/email”)
def get_mail_captcha():
email=request.args.get(“email”)
# import sting random
source= string.digits*4
captcha=random.sample(source,4)
 captcha=“”,join(captcha)
# 调用test中的message方法，发送
email_capthca=EmailCatchaModel(email=email,captcha=captcha)
db.session.add(email_captcha)
db.session.commit()
return jsonify({ “code”:200,”message”:””,”data”:None})




class EmailCatchaModel(db.Model):
	__tablename__=“email_capcha”	id=db .Column(db.Inter,primary_key=True,autoincrement=True)	email=db.Column(db.String(100),nullable=False,unique=True)
captcha=db.Column(db.Stirng(100))



 
加载完成后执行
$(function(){
$(“#captchabtn”).click(function(evvent{
event.precentDefault();
var email=$(“innput”[name]=‘email’).val();
$.ajax({
url:”/auth/capture/email”,
method:”GET”,
success:function(result){
console.log(result);
if(code==200){
alter(“验证码发送成公”);
}
else{
alter(result[‘message’]);
}
}
fail:function(error){
console.log(error); 
}
# route 默认get请求
# @bp.route(“/capatcha/email?emal=xx@qq.com”,method=[‘post’])
})
});
})




```




flask 多线程操作

```python
if name == 'main':
	sing_thread = threading.Thread (target=sing, args=("我要唱歌 哈哈哈",))
	dance_thread = threading.Thread (target=dance, kwargs={"msg": "我在跳舞哦 啦啦啦"})
	sing_thread.start()
	dance_thread.start()
```



## flask--URL反转

从视图函数到url的转换

在页面做重定向的时候会使用url反转。

在模板中，也会使用url反转
```python
#encoding:utf-8
from flask import Flask,url_for
 
app = Flask(__name__)
 
 
@app.route('/')
def index():
    print url_for('my_list')
    print url_for('article',id='abc')
    return 'Hello World!'
@app.route('/list/')
def my_list():
    return 'list'
@app.route('/article/<id>/')
def article(id):
    return u'您请求的参数是：%s'%id
 
if __name__ == '__main__':
    app.run(debug=True)
```


打散字典

```python
@app.route('/')
def index():
    context = {
        'username': u'知了课堂',
        'gender': u'男',
        'age': 18
    }
    return render_template('index.html',**context)

if __name__ == '__main__':
    app.run(debug=True)
```


if

```html
<body>
    {% if user %}
    <a href="#">{{ user.username }}</a>
    <a href="#">注销</a>
    {% else %}
    <a href="#">登录</a>
    <a href="#">注册</a>
    {% endif %}
</body>
```



```html
{# {% for k,v in user.items() %}#}
{#     <p>{{ k }}: {{ v }}</p>#}
{# {% endfor %}#}
{##}
{# {% for website in websites %}#}
{#     <p>{{ website }}</p>#}
{# {% endfor %}#}
```








**Flask-Script** 是一个扩展库，它允许通过命令行操作 Flask 应用程序。它提供了运行开发服务器、自定义 Python shell、设置数据库脚本、定时任务等功能，使得脚本和系统分开。

```python
from flask_script import Manager
from shop import app_config

app = app_config('develop')
manage = Manager(app)

@manage.command
def hello():
	print('命令执行成功')

if __name__ == '__main__':
	manage.run()
```


```python
@manage.option('-u', '--username', dest='uname')
@manage.option('-p', '--password', dest='pwd')
def create_user(uname, pwd):
	user = User(uname=uname, pwd=pwd)
	db.session.add(user)
	db.session.commit()
	print('添加用户成功！')
if __name__ == '__main__':
	manage.run()
```


```
在命令行中运行以下命令：
python hello.py hello
python hello.py create_user -u 用户名 -p 密码
```




























































































































































