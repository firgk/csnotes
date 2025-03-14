## map

  
  
  

一个程序

```c++

#include "bits/stdc++.h"

#include <algorithm>

#include <ratio>

using namespace std;

  

map<long long,int> mp;

  

int main(){

    int n;

    cin>>n;

    while (n--) {

        int x=2;

        while(x--){

            long long a;

            cin>>a;

            if(mp.find(a)==mp.end()){

                mp[a]=1;

            }

            else{

                mp[a]=mp.find(a)->second+1;

            }

        }

    }

//  for(auto it:mp){

//      cout<<it.first;

//      cout<<" ";

//      cout<<it.second;

//      cout<<endl;

//  }

    unsigned long maxnum=0;

    unsigned long maxi=0;

    int cont=0;

    for(auto it:mp){

        if(it.second>maxnum){

            maxnum=it.second;

            maxi=it.first;

        }

    }

  

    for(auto it:mp){

        if(maxnum==it.second){

            cont++;

        }

    }

    cout<<maxi<<" "<<maxnum;

    if(cont!=1)

        cout<<" "<<cont;

    return 0;

}

  

```

#include <map>    //stl头文件不带.h

  
  

初始化

map<int,string> list1;

map<int,string> list2 =

    {{1,"java教程"},{2，"c++教程"},{3,"python教程"}}；

map<int,string> list3 =

    {pair<int,string> (1,"java教程"),pair<int,tring> (2,"c++教程")};

  
  
  

添加对象

map<int,int> list1;

list1.insert(pair<int,int> (1,15));

list1.insert({2,13});

//两种方式添加元素

注意：map中key的值是唯一的，如果插入一个已经存在的key值会导致原先值的覆盖，请尽量避免这样。

mp[a]++; 不存在某人0开始

  
  

遍历

//遍历整个list1

for (auto iter = list1.begin(); iter != list1.end(); ++iter) {              

       cout << iter->first << "  " << iter->second << endl;

    }

  

map<int, int>::iterator it;

    for (it = mp.begin(); it != mp.end(); it++) {

        cout <<it->first << " " << it->second ;

    }

  

for(auto it:mp){

        cout <<it->first << " " << it->second ;

    }

  
  
  

删除

list1.erase(1);    //括号内为key值，也就是左值

  
  

修改

类似数组方法修改

  
  

查找

可以直接通过key值查找，如果要通过value值找ksy的话需要借助find函数

map<int,int> list1 =

      {{1,12},{2,15},{3,18},{4,22}};

cout<<list1[1];        //输出结果为12

cout<<list1[12];       //错误，不能直接用value值

cout<<find(12)->first;    //输出1

cout<<find(1)->second;    //输出12

当所查找的关键key出现时，它返回数据所在对象的位置，如果沒有，返回iter与end函数的值相同。 末尾

// find 返回迭代器指向当前查找元素的位置否则返回map::end()位置

  

iter = mapStudent.find("123");

if(iter != mapStudent.end())

       cout<<"Find, the value is"<<iter->second<<endl;

else

   cout<<"Do not Find"<<endl;

  
  

其他

map<string,int> map_1;

map_1.clear();                //清除所有元素

map_1.empty();                //如果为空返回1，负责返回0

map_1.size();                 //返回容器的元素个数  map的大小

map_1.max_size;               //返回容器可以容纳的最大元素

//可以用过迭代器与first，second访问元素

map_1.begin()->first;         //结果为容器的第一个元素的key值

map_1.begin()->second;         //结果为容器的第一个元素的value值

  
  
  
  
  
  

smallpoint

  
  
  

一个错误

C++ ERROR : base operand of ‘->’ has non-pointer type ‘std::pair< int, int>’ 的解释

list<pair<int,int>> cachelist;

unordered_map<int,list<pair<int,int>>::iterator> map;

  

void put(int key, int value) {

    auto it = map.find(key);

    if(it != map.end()){

        touch(it->second);

        it->second->second = value; // ①

    }

    else if(map.size() < cap){

        cachelist.push_front(make_pair(key,value));

        map[key]=cachelist.begin();

    }

    else{

        auto it = cachelist.back();// ②

        map.erase(it->first); // 出错位置~~！！

        cachelist.pop_back();

        cachelist.push_front(make_pair(key,value));

        map[key]=cachelist.begin();

    }

}

报错内容：

Line xx: base operand of '->' has non-pointer type ' std::pair <int, int>'

  

分析与解决

首先unordered_map的erase() 函数的参数可以是键值，可以是迭代器，也可以是迭代器区间，那么肯定不是erase()的问题；

然后报错提示告诉我们pair<int,int>不能用->符号，那就奇怪了，位置①我们不是也用了it->second->second吗？①处的it是unordered_map的iterator，it->second是list<pair<int,int>>的iterator，所以it->second->second是pair的第二值，好像没什么不对？？

到这里可能你跟我一样，发现问题了，②处的it并不是list<pair<int,int>>的迭代器，而是cachelist的最后一个元素节点的地址，auto实际上应该是pair<int,int> &，而pair<int,int>是不认识->符号的，所以出错位置的应该把->改成.，即：

map.erase(it.first);

  
  
  

int的最大值等问题

cout<<LONG_LONG_MAX;

INT_MAX 等等

数组最多可以定义多少个99999   5个9 int

  
  
  
  

c++读入多行数据

```c++

#include<bits/stdc++.h>

using namespace std;

//读入数据

  

//不会读取空格

void getter(){

    string a;

    while (cin>>a) {

        cout<<a;

    }

}

  

// 可以读取空格

void getter2()

{

    string tt;

    while(getline(cin,tt)){

        cout<<tt;

    }

}

  

int main(){

    getter();

}

```

  
  
  
  
  
  

#### 一个例题 词频统计

  

题目

7-1 词频统计 (30 分)

  

请编写程序，对一段英文文本，统计其中所有不同单词的个数，以及词频最大的前10%的单词。

所谓“单词”，是指由不超过80个单词字符组成的连续字符串，但长度超过15的单词将只截取保留前15个单词字符。而合法的“单词字符”为大小写字母、数字和下划线，其它字符均认为是单词分隔符。

输入格式:

输入给出一段非空文本，最后以符号#结尾。输入保证存在至少10个不同的单词。

输出格式:

在第一行中输出文本中所有不同单词的个数。注意“单词”不区分英文大小写，例如“PAT”和“pat”被认为是同一个单词。

随后按照词频递减的顺序，按照词频:单词的格式输出词频最大的前10%的单词。若有并列，则按递增字典序输出。

输入样例：

This is a test.

  

The word "this" is the word with the highest frequency.

  

Longlonglonglongword should be cut off, so is considered as the same as longlonglonglonee. But this_8 is different than this, and this, and this...#

this line should be ignored.

输出样例：（注意：虽然单词the也出现了4次，但因为我们只要输出前10%（即23个单词中的前2个）单词，而按照字母序，the排第3位，所以不输出。）

23

5:this

4:is

  

**思路**

题目要求记录词频，那就记录每个词出现的次数

由于‘#’表示结束，那么就要逐字读入进行判断，但这要这么变成一个单词呢？只要把每个读到的字符接在字符串的后面就行了

然后可以构建一个列表，表中有每个单词及其出现次数

  

用到的结构及函数

begin()和end()函数

基本上线性存储类类型都会有的结构（vector，map），其实就是快速定位到第一个（通常下标是0）和最后一个数据。要注意的是他们可以直接进行加减运算

通常在初始化或者函数中用到

  

vector<pair<string ,int>> v(ma.begin(), ma.end())；    //定义一个pair<string ,int>类型的数组，并且数组第一个数据为ma的第一个数据，最后一个数据为ma的最后一个数据

  
  
  
  

sort(v.begin(), v.end(), cmp);    //将v从v 的第一个到最后一个数据根据自定义的比较函数cmp进行分类。sort的自定义比较函数之后会说

//来自C++Reference的样例

int myints[] = {32,71,12,45,26,80,53,33};

  std::vector<int> myvector (myints, myints+8);               // 32 71 12 45 26 80 53 33 （对前八个数排序）

  

  std::sort (myvector.begin(), myvector.begin()+4);           //(12 32 45 71)26 80 53 33 （对前四个数排序）

pair（对组）

pair是C++定义的模板类型，可以同时存储两个类型的数据，其实可以用结构体实现的呢

  

pair<string, int> a    //定义

a.firts="Hello World";    //对第一个数据进行操作

a.second="3";             //对第二个数据进行操作

  
  
  

map

先来看看C++reference的定义

  

/*

Maps are associative containers that store elements formed by a combination of a key value and a mapped value, following a specific order.

In a map, the key values are generally used to sort and uniquely identify the elements, while the mapped values store the content associated to this key.

The types of key and mapped value may differ, and are grouped together in member type value_type, which is a pair type combining both:

*/

typedef pair<const Key, T> value_type;

其实，我们可以把map理解为一个容器，其内部存的是一组键值对，即两个不同类型的数据。

可以理解为“关键字”以及“关键字的值”（没错，和pair很像）

  
  
  
  

vector（容器）

vector可以看成加强版数组，用于储存相同类型的多个数据。这里要介绍他的排序函数sort()

从C++reference中可以找到以下例子

  

 int myints[] = {32,71,12,45,26,80,53,33};

  std::vector<int> myvector (myints, myints+8);               //初始化为 32 71 12 45 26 80 53 33（myints的前八个放入vector）

  

  // using default comparison (operator <):

  std::sort (myvector.begin(), myvector.begin()+4);           //(12 32 45 71)26 80 53 33

  

  // using function as comp

  std::sort (myvector.begin()+4, myvector.end(), myfunction); // 12 32 45 71(26 33 53 80)

  

  // using object as comp

  std::sort (myvector.begin(), myvector.end(), myobject);     //(12 26 32 33 45 53 71 80)

可以看出sort()其实可以自己加函数进行排序，不过如果自己每加的话就默认非降序排列。

那如果我要自己加函数要怎么加呢？

参考：【C++】从最简单的vector中sort用法到自定义比较函数comp后对结构体排序的sort算法

首先自定义比较函数的返回值是bool型的，这里给出一个例子

  

    bool comp(int a,int b){

        return a>b;

    }

  

    sort(v.begin(), v.end(), comp);

比较时sort函数根据comp函数进行判断输的大小，系统默认a<b时返回真，于是从小到大排，而我的comp函数设定为a>b时返回为真，那么最终得到的排序结果也相应的从小到大变成从大到小。其实可以这样理解：排序结束后，a是前面的数，b是后面的数，我们的自定义函数是为了定义a与b的关系

再让我们来看代码中的例子

  

bool cmp(pair<string, int> a, pair <string, int> b) {

    bool result = false;

    if (a.second == b.second&&a.first < b.first) {

        result = true;

    }

    else if (a.second > b.second) {

        result = true;

    }

    return result;

}

  

sort(v.begin(), v.end(), cmp);

那么上述式子就表明，对vector中的第一个数据到最后一个数据排列。

因为vector中的数据类型是pair，根据自定义的比较函数，当pair的second（词语出现次数）相等时，first（单词）小的在前面（若有并列，则按递增字典序输出）。

  
  
  
  
  
  
  

代码

```c++

  

#include <iostream>

#include <string>

#include <vector>

#include <map>

#include <algorithm>

  

using namespace std;

  

bool cmp(pair<string, int> a, pair <string, int> b);

  

int main() {

    char ch;

    string s;   //字符串用于记录一个单词

    map<string, int> ma;    //map记录词频，string代表的单词出现次数为int

    do {

        ch = getchar();

        //当读到的是合法字符（大小写字母、数字下划线）

        if ((ch >= 'a'&&ch <= 'z') || (ch >= 'A'&&ch <= 'Z') || (ch >= '0'&&ch <= '9') || ch == '_') {

            if (s.size() <= 14) {   //当长度为14时再进行一次接入，长度为15就停止接入

                if (ch >= 'A'&&ch <= 'Z') {     //把大写换成小写

                    ch += 32;

                }

                s += ch;    //把单个字符ch接到字符串s后，string中有运算符重载所以加法表示接在后面

            }

        }

        else {      //当不是合法字符就表示这个词读取结束了，出现次数+1

            if (s.size() > 0) {

                ma[s]++;

            }

            s.clear();      //清空字符串以统计下一个单词

        }

        if (ch == '#') {    //读到#退出循环

            break;

        }

    } while (ch != '#');

    vector<pair<string ,int>> v(ma.begin(), ma.end());        //存储pair的一个数组（把vector理解为增强版的数组）

    sort(v.begin(), v.end(), cmp);

    cout << v.size() << endl;

    int cnt = (int)(ma.size()*0.1);

    for (int i = 0; i < cnt; i++) {

        cout << v[i].second << ":" << v[i].first << endl;

    }

    return 0;

}

  

//利用pair数据，每个pair数据都含有一个string数值和int数值

//

bool cmp(pair<string, int> a, pair <string, int> b) {

    bool result = false;

    if (a.second == b.second&&a.first < b.first) {

        result = true;

    }

    else if (a.second > b.second) {

        result = true;

    }

    return result;

}

// 附加:上述优化

bool cmp2(pair<string,int >a,pair<string,int >b){ // 若有并列则按照递增字典序输出

    if(a.second>b.second){

        return true;

    }

    else if(a.second==b.second&&a.first<b.first){

        return true;

    }

    return false;

}

```

  
  
  
  
  

#### set

  
  
  

set就是集合，STL的set用二叉树实现，集合中的每个元素只出现一次(参照数学中集合的互斥性)，并且是排好序的(默认按键值升序排列)

  

访问元素的时间复杂度是O ( log ⁡ 2 n )

在c++中，set的头文件是#include<set>

  

set具有迭代器set<int>::iterator i 定义一个迭代器,名为i 可以把迭代器理解为C语言的指针

  
  
  
  

set<int> q;     //以int型为例 默认按键值升序

set<int,greater<int>> p;  //降序排列

int x;

q.insert(x);    //将x插入q中

q.erase(x);     //删除q中的x元素,返回0或1,0表示set中不存在x

q.clear();      //清空q

q.empty();      //判断q是否为空，若是返回1，否则返回0

q.size();       //返回q中元素的个数

q.find(x);      //在q中查找x，**返回x的迭代器**，若x不存在，则返回指向q**尾部**的迭代器即 q.end()

q.lower_bound(x); //返回一个迭代器，**指向第一个**键值**不小于**x的元素

q.upper_bound(x); //返回一个迭代器，指向第一个键值大于x的元素

  

q.rend();         //返回第一个元素的的前一个元素迭代器

q.begin();        //返回指向q中第一个元素的迭代器

  

q.end();         //返回指向q最后一个元素下一个位置的迭代器

q.rbegin();      //返回最后一个元素

set_name.count(element)  返回0或者1

返回值：该函数返回1或0，因为该集合仅包含唯一元素。如果设置的容器中存在该值，则返回1。如果容器中不存在它，则返回0。

举例

  
  

```c++

#include<iostream>

#include<set>

using namespace std;

int main()

{

    set<int> q;   //默认按升序排列

    q.insert(5);

    q.insert(5);

    q.insert(5);

    cout<<"q.size "<<q.size()<<endl;   //输出 1 ,在set插入中相同元素只会存在一个

    q.clear(); //清空set

    cout<<"q.size "<<q.size()<<"\n\n";

    q.insert(4);

    q.insert(4);

    q.insert(3);

    q.insert(3);

    q.insert(2);

    q.insert(1);

    cout<<"lower_bound "<<*q.lower_bound(3)<<endl;  //返回3

    cout<<"upper_bound "<<*q.upper_bound(3)<<"\n\n";  //返回4

    set<int>::iterator i;

    for( i=q.begin();i!=q.end();i++)   //set的遍历

        cout<<*i<<" ";                 //输出1 2 3 4，可见自动按键值排序

    cout<<endl;

    q.erase(4);  //删除q中的 4

    for(i=q.begin();i!=q.end();i++)  //再次遍历set 只输出 1 2 3

        cout<<*i<<" ";  //!

    cout<<"\n\n";

    set<int,greater<int>> p;  //降序排列

    p.insert(1);

    p.insert(2);

    p.insert(3);

    p.insert(4);

    p.insert(5);

    for(i=p.begin();i!=p.end();i++)

        cout<<*i<<" ";

    cout<<endl;

    return 0;

}

  
  

```

  
  

set 中以结构体为元

  

```c++

#include<iostream>

#include<set>

using namespace std;

struct node{

    int a,b;

    bool operator< (const node W)const

    {

        return a>W.a;  //按a的值升序

    }

}t;

int main()

{

    set<node> q;

    t.a=1;

    t.b=2;

    q.insert(t);

    t.a=4;

    t.b=2;

    q.insert(t);

    t.a=3;

    t.b=5;

    q.insert(t);    

    set<node>::iterator i;

    for(i=q.begin();i!=q.end();i++)

    {

        t=*i;

        cout<<t.a<<" "<<t.b<<endl;

    }

    return 0;

}

  

```

  
  

二维set

```c++

#include<cstdio>

#include<iostream>

#include<cstring>

#include<set>

#include<cmath>

  

#define maxn 100

  

using namespace std;

  
  

int main(){

    set<int> a[maxn];

    int i;

    for(i = 0;i< 2;i++){

        for(int j = 0;j< 5;j++)     a[i].insert(j);    

    }

    for(i = 0;i< 2;i++){

        for(set<int>::iterator it = a[i].begin();it!=a[i].end();it++){

            cout << *it << " ";

        }cout<<"\n";

    }

}

  

```

  
  

#### 一个题

  

点赞狂魔

  

7-3 点赞狂魔

分数 25

作者 陈越

单位 浙江大学

微博上有个“点赞”功能，你可以为你喜欢的博文点个赞表示支持。每篇博文都有一些刻画其特性的标签，而你点赞的博文的类型，也间接刻画了你的特性。然而有这么一种人，他们会通过给自己看到的一切内容点赞来狂刷存在感，这种人就被称为“点赞狂魔”。他们点赞的标签非常分散，无法体现出明显的特性。本题就要求你写个程序，通过统计每个人点赞的不同标签的数量，找出前3名点赞狂魔。

  

输入格式：

输入在第一行给出一个正整数N（≤100），是待统计的用户数。随后N行，每行列出一位用户的点赞标签。格式为“Name K F

1

​

 ⋯F

K

​

 ”，其中Name是不超过8个英文小写字母的非空用户名，1≤K≤1000，F

i

​

 （i=1,⋯,K）是特性标签的编号，我们将所有特性标签从 1 到 10

7

  编号。数字间以空格分隔。

  

输出格式：

统计每个人点赞的不同标签的数量，找出数量最大的前3名，在一行中顺序输出他们的用户名,其间以1个空格分隔,且行末不得有多余空格。如果有并列，则输出标签出现次数平均值最小的那个，题目保证这样的用户没有并列。若不足3人，则用-补齐缺失，例如mike jenny -就表示只有2人。

  

输入样例：

5

bob 11 101 102 103 104 105 106 107 108 108 107 107

peter 8 1 2 3 4 3 2 5 1

chris 12 1 2 3 4 5 6 7 8 9 1 2 3

john 10 8 7 6 5 4 3 2 1 7 5

jack 9 6 7 8 9 10 11 12 13 14

输出样例：

jack chris john

  
  
  

```c++

#include<bits/stdc++.h>

using namespace std;

#define max 1005

struct user

{

    string name;

    int k;

    int a[1005];

    int cnt;

} ;

user u[105];

bool cmp(user a, user b)

{

    if(a.cnt==b.cnt)

    {

        return a.k<b.k;

    }

    return a.cnt>b.cnt;

}

vector<int> q;

void add(int x)

{

    if(q.size()==0)

        q.push_back(x);

    else

    {

        for(int i=0;i<q.size();i++)

        {

            if(x==q[i])return;

        }

        q.push_back(x);

    }

    return;

}

  

int main()

{

    int N;

    cin>>N;

    for(int i=0; i<N; i++)

    {

        cin>>u[i].name>>u[i].k;

        for(int j=0; j<u[i].k; j++)

        {

            cin>>u[i].a[j];

        }

    }

    for(int i=0; i<N; i++)

    {

        q.clear();

        for(int j=0; j<u[i].k; j++)

        {

            add(u[i].a[j]);

        }

        u[i].cnt=q.size();

    }

    sort(u,u+N,cmp);

    switch(N)

    {

        case 0:

                cout<<"- - -"<<endl;

                break;

        case 1:

                cout<<u[0].name<<" - -"<<endl;

                break;

        case 2:

                cout<<u[0].name<<" "<<u[1].name<<" -"<<endl;

                break;

        default:
            cout<<u[0].name<<" "<<u[1].name<<" "<<u[2].name<<endl;
            break;
    }
    return 0;
}

  
  

```