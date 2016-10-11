﻿---
layout: post
title:  "SSH-JSON"
categories: SSH
tag: SSH
---

* content
{:toc}

## 导入jar包

``` xml
<dependency>
    <groupId>org.apache.struts</groupId>
    <artifactId>struts2-json-plugin</artifactId>
    <version>2.3.16.3</version>
</dependency>
```

## struts.xml

> package需要继承json-default

``` xml
<package name="default" extends="struts-default">
</package>
<package name="jsonPackage" extends="json-default">
    <action name="queryUserAction" class="com.demo.action.QueryUserAction">
        <result type="json"></result>
    </action>
    <action name="addUserAction" class="com.demo.action.AddUserAction">
        <result type="json"></result>
    </action>
</package>
```

## Action
> Action需要有setter方法接收前端数据，所有get开头的get*方法都能返回json数据，可以在方法前使用@JSON(name="jsonName")设置jsonName。不一定需要execute()方法。下面java代码为查询第first个到first+offset-1个User的Action：

``` java 
@Controller
public class QueryUserAction extends ActionSupport {

    @Resource
    private UserService userService;

    private int first;
    private int offset;

    public void setFirst(int first) {
        this.first = first;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @JSON(name="userList")
    public List getUserList() {
        List<User> userList =  userService.userList(first, offset);
        return userList;
    }

}
```

## 前端JSP

> 注意表单`id`和`name`的区别，js中input取值一定要用`id`。

#### 表单

``` html
<input type="text" id="first">
<input type="text" id="offset">
<button id = "b2">查询</button>
<table id="tab" border="0"></table>
```

#### Ajax

``` js
$(document).ready(function () {
    $("#b2").click(function(){
        $.ajax({
            type:"post",
            url:"queryUserAction",
            data:{first:$("#first").val(), offset:$("#offset").val()},
            dataType:"json",
            success:function (data) {
                $("#tab").html("");
                $.each(data.userList, function (index, field) {
                $("#tab").append("<tr> <td>"+field.userId+"</td><td>"+ field.username+"</td><td>"+field.password+"</td></tr>");
                })
            },
            error:function () {
                alert("Error");
            }
       });
   });
})
```

## HibernageCallback

> 使用HibernateCallback API可以非常灵活的访问数据库，下面例子可以用在分页上。不知道为什么Idea中使用HQL(H-Hibernate)"from User" (User-javaBean) 下面要打红线说不能解析，但是可以正常使用（坑）。

``` java
    //UserDaoImpl
    public List<User> userList(final int first, final int offset) {
        List<User> userList = hibernateTemplate
                .execute(new HibernateCallback<List<User>>() {
                    public List<User> doInHibernate(Session session) throws HibernateException {
                        Query query = session.createQuery("from User");
                        List<User> result = query.setFirstResult(first - 1).setMaxResults(offset).list();
                        return result;
                    }
                });
        return userList;
    }
```





