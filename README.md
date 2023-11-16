# HumanResource
🥇基于SpringBoot2.6，c3p0，swagger，Vue&Element框架开发的广东海洋大学人力资源管理系统

> 运行方式

1. 前端项目：

* 创建：npm install
* 启动：npm run serve
* 打包：npm run build



# 运行环境

- java:8（jdk1.8）
- mysql:5.6.35



# 核心依赖

> 后端技术栈

| 依赖                 | 版本    |
| -------------------- | ------- |
| Spring Boot          | 2.6.4   |
| mysql-connector-java | 8.0.16  |
| c3p0                 | 0.9.5.5 |
| lombok               | 1.18.22 |
| swagger              | 2.7.0   |
| aliyun-sdk-oss       | 3.1.0   |



# 页面介绍

## 首页

### 登录界面

* 地址：http://localhost:8080/#/login

![img](https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/clip_image020.jpg)

| 用户名  | 密码   | 权限         |
| ------- | ------ | ------------ |
| admin   | 123456 | all          |
| xitong  | 123456 | 系统管理权限 |
| dangan  | 123456 | 档案管理权限 |
| xinchou | 123456 | 薪酬管理权限 |
| putong  | 123456 | 普通管理员   |



### 注册界面

* 地址：http://localhost:8080/#/register

![img](https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/clip_image022.jpg)



### 功能介绍

* 地址：http://localhost:8080/#/welcome

![img](https://cocochimp-markdown-img.oss-cn-beijing.aliyuncs.com/clip_image024.jpg)

* 功能模块介绍：

![img](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904222949.png)

1. **系统管理：**

![image-20230904221737983](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904221738.png)

2. **档案管理：**

![image-20230904221803201](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904221803.png)

3. **薪酬管理：**

![image-20230904221905500](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904221905.png)



## 系统管理

* 作用：对项目进行统一管理

![img](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904223029.png)

### 角色管理

* 地址：http://localhost:8080/#/sys/user

![image-20230904222356187](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904222356.png)

* 功能：对人物对应的角色信息进行修改（角色信息是固定的）



### 机构管理

* 地址：http://localhost:8080/#/sys/organization

![image-20230904223259116](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904223259.png)

* 功能：对“三级机构”进行管理



### 职位管理

* 地址：http://localhost:8080/#/sys/position

![image-20230904223246842](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904223246.png)

* 功能：对各个任务的职称进行统一管理



## 档案管理

* 作用：对档案人物进行管理

![img](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904223041.png)

### 档案登记

* 地址：http://localhost:8080/#/file/add

![image-20230904225025676](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225025.png)

* 功能：对某个人员档案的机构和职位进行统一管理



### 档案复核

* 地址：http://localhost:8080/#/file/check

![image-20230904225124363](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225124.png)

* 功能：超级管理员和档案管理员对登记的档案进行复核



### 档案查询

* 地址：http://localhost:8080/#/file/query

![image-20230904225206213](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225206.png)

* 功能：对已经进行档案复核的档案进行多功能查询



## 薪酬管理

* 作用：对档案人员的薪酬进行统一管理

![img](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904223049.png)

### 薪酬登记

* 地址：http://localhost:8080/#/sal/add

![image-20230904225328144](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225328.png)

* 功能：对档案人员薪酬进行登记管理



### 薪酬复核

* 地址：http://localhost:8080/#/sal/check

![image-20230904225429879](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225429.png)

* 功能：超级管理员和薪酬管理员对薪酬登记后的薪酬信息进行复核



### 薪酬查询

* 地址：http://localhost:8080/#/sal/query

![image-20230904225528060](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225528.png)

* 功能：对通过薪酬复核的人员档案进行查询



### 薪酬发放

* 地址：http://localhost:8080/#/sal/give

![image-20230904225617466](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225617.png)

* 功能：对某个机构的所有审批情况进行管理
* 同意提交后：

![image-20230904225738448](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225738.png)

![image-20230904225728028](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225728.png)

* 结果展示：

![image-20230904225702232](https://cocochimp-img.oss-cn-beijing.aliyuncs.com/23-03/20230904225702.png)



