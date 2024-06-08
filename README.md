### 🎉 项目备注
项目：KCloud-Platform-IoT（阻塞式）  
作者：老寇  
语言：Java  
时间：2022.06.15 ~ 至今  

### 📣 项目介绍
<p align="center"><img src="doc/image/logo.png" width="625" height="205" alt="图标"/></p>
KCloud-Platform-IoT（阻塞式）（老寇IoT云平台）是一个企业级微服务架构的IoT云平台。基于Spring Boot 3.3.0、Spring Cloud 2023.0.2、Spring Cloud Alibaba 2023.0.1.0 最新版本开发的云服务多租户IoT平台。
遵循SpringBoot编程思想，使用阿里COLA应用框架构建，高度模块化和可配置化。具备服务注册&发现、配置中心、灰度路由、服务限流、熔断降级、监控报警、多数据源、高亮搜索、定时任务、分布式链路、分布式缓存、分布式事务、分布式存储、分布式锁等功能，用于快速构建微服务项目。目前支持Shell、Docker等多种部署方式，并且支持GraalVM和虚拟线程。实现RBAC权限、其中包含系统管理、系统监控、数据分析等几大模块。
遵循阿里代码规范，采用RESTFul设计风格及DDD(领域驱动设计)思想，代码简洁、架构清晰，非常适合作为基础框架使用。
<p align="center">
    <a target="_blank" href="https://github.com/KouShenhai/KCloud-Platform-IoT"><img alt="GitHub stars" src="https://img.shields.io/github/stars/KouShenhai/KCloud-Platform-IoT?logo=github"></a>
    <a target="_blank" href="https://github.com/KouShenhai/KCloud-Platform-IoT"><img alt="GitHub forks" src="https://img.shields.io/github/forks/KouShenhai/KCloud-Platform-IoT?logo=github"></a>
    <a target="_blank" href="https://github.com/KouShenhai/KCloud-Platform-IoT"><img alt="GitHub watchers" src="https://img.shields.io/github/watchers/KouShenhai/KCloud-Platform-IoT?logo=github"></a>
    <a target="_blank" href="https://github.com/KouShenhai/KCloud-Platform-IoT"><img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/KouShenhai/KCloud-Platform-IoT"></a>
	<a target="_blank" href="https://gitee.com/laokouyun/KCloud-Platform-IoT/stargazers"><img src="https://gitee.com/laokouyun/KCloud-Platform-IoT/badge/star.svg?theme=dark" alt="Gitee Star"></a>
    <a target="_blank" href="https://gitee.com/laokouyun/KCloud-Platform-IoT"><img src="https://gitee.com/laokouyun/KCloud-Platform-IoT/badge/fork.svg?theme=dark"  alt="Gitee Fork"></a>
    <a target="_blank" href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/static/v1?label=Spring Boot&message=3.3.0&color=green" alt="SpringBoot"></a>
    <a target="_blank" href="https://spring.io/projects/spring-cloud"><img alt="Spring Cloud" src="https://img.shields.io/static/v1?label=Spring Cloud&message=2023.0.2&color=green"></a>
    <a target="_blank" href="https://github.com/alibaba/spring-cloud-alibaba"><img alt="Spring Cloud" src="https://img.shields.io/static/v1?label=Spring Cloud Alibaba&message=2023.0.1.0&color=orange"></a>
    <a target="_blank" href="https://spring.io/projects/spring-authorization-server"><img alt="OAuth 2.1" src="https://img.shields.io/static/v1?label=OAuth 2.1&message=1.3.0&color=blue"></a>
    <a target="_blank" href="https://www.oracle.com/java/technologies/downloads/#java17"><img alt="JDK" src="https://img.shields.io/badge/GraalVM-21.0.1-blue.svg"/></a>
    <a target="_blank" href="https://jq.qq.com/?_wv=1027&k=Ec8T76dR"><img src="https://img.shields.io/badge/Q群-465450496-blue.svg" alt="KCloud-Platform开源交流"></a>
</p>

### 😋 项目背景
- 学习微服务架构设计并实践于生产
- 整合所学技术方便快速搭建项目
- 持续优化代码来提高代码质量
- 复制粘贴代码提高生产效率
- 学习DDD思想并落地实践

### 🔗 在线体验（作者剔除前端Vue UI，要么找专业前端对接，要么等作者从零采用React重构，估计要很久）
[老寇IoT云平台在线体验（请点击我，境外服务器网络延迟请稍候）](https://www.laokou.org.cn)

### 🔖 在线文档
[老寇IoT云平台在线文档（请点击我）](https://koushenhai.github.io)

### 😎 开发计划
[开发计划（需要登录）](https://docs.qq.com/sheet/DUGhCdGVZWmVxT0VJ?tab=BB08J2)

### 🎁 项目推荐
[作者的另一个开源IoT项目（响应式）](https://github.com/KouShenhai/KCloud-Platform-Reactive-IoT)

### 💪 版本号
特此说明，与Spring Boot版本保持一致

### 🔎 功能介绍
🚀 无页面展示，作者采用DDD思想重构后端API中（已剔除前端Vue UI，要么自己找专业前端对接，要么等作者从零采用React重构，估计要很久）

### 💡 系统架构
![](doc/image/老寇IoT云平台架构图-阿里巴巴.png)

### ✂ 技术体系
#### 🎯 Spring全家桶及核心技术版本
| 组件                          | 版本            |
|:----------------------------|:--------------|
| Spring Boot                 | 3.3.0         |
| Spring Cloud                | 2023.0.2      |
| Spring Cloud Alibaba        | 2023.0.1.0    |
| Spring Boot Admin           | 3.3.2         |
| Spring Authorization Server | 1.3.0         |
| Mybatis Plus                | 3.5.6         |
| Nacos                       | 2.3.2         |
| Seata                       | 2.0.0         |
| Sentinel                    | 1.8.7         |
| Redis                       | 7.2.5         |
| Elasticsearch               | 8.13.4        |
| RocketMQ                    | 5.2.0         |
| OpenResty                   | 1.21.4.1      |
| Netty                       | 4.1.110.Final |
| Kafka                       | 3.7.0         |
| EMQX                        | 5.7.0         |
| Postgresql                  | 16.3          |
| Flyway                      | 10.13.0       |
| TDengine                    | 3.3.0.3       |
#### 🍺 相关技术
- 配置中心&服务注册&发现：Nacos
- API网关：Spring Cloud Gateway
- 认证授权：Spring Security OAuth2 Authorization Server
- 远程调用：Spring Cloud OpenFeign & OkHttp & HttpClient & WebClient
- 负载均衡：Spring Cloud Loadbalancer & OpenResty
- 服务熔断&降级&限流：Sentinel
- 分库分表：Mybatis Plus
- 分布式事务：Seata & RocketMQ
- 消息队列：RocketMQ & Kafka & MQTT
- 服务监控：Spring Boot Admin & Prometheus
- 高亮搜索：Elasticsearch
- 链路跟踪：SkyWalking
- 任务调度：Power Job
- 日志分析：EFK
- 缓存&分布式锁：Redis & Redisson
- 统计报表：MongoDB
- 对象存储：Amazon S3
- 自动化部署：Docker
- 网络通讯：Netty
- 持续集成&交付：Jenkins
- 持久层框架：Mybatis Plus
- JSON序列化：Jackson
- 对象转换：MapStruct
- 数据库：Postgresql
- 时序数据库：TDengine
- 数据库迁移：Flyway

#### 🌴 项目结构
~~~
├── laokou-common  
        └── laokou-common-log                      --- 日志组件  
        └── laokou-common-core                     --- 核心组件  
        └── laokou-common-cors                     --- 跨域组件  
        └── laokou-common-mqtt                     --- 消息组件  
        └── laokou-common-redis                    --- 缓存组件  
        └── laokou-common-kafka                    --- 消息组件  
        └── laokou-common-log4j2                   --- 日志组件  
        └── laokou-common-mongodb                  --- 报表组件  
        └── laokou-common-rocketmq                 --- 消息组件  
        └── laokou-common-algorithm                --- 算法组件  
        └── laokou-common-prometheus               --- 监控组件  
        └── laokou-common-openapi-doc              --- 文档组件  
        └── laokou-common-rate-limiter             --- 限流组件  
        └── laokou-common-elasticsearch            --- 搜索组件  
        └── laokou-common-bom                      --- 依赖版本库  
        └── laokou-common-i18n                     --- 国际化组件  
        └── laokou-common-sensitive                --- 敏感词组件  
        └── laokou-common-extension                --- 扩展点组件  
        └── laokou-common-lock                     --- 分布式锁组件  
        └── laokou-common-trace                    --- 链路跟踪组件  
        └── laokou-common-nacos                    --- 注册发现组件  
        └── laokou-common-netty                    --- 网络通讯组件  
        └── laokou-common-domain                   --- 领域事件组件  
        └── laokou-common-crypto                   --- 加密解密组件  
        └── laokou-common-secret                   --- 接口验签组件  
        └── laokou-common-security                 --- 认证授权组件  
        └── laokou-common-openfeign                --- 远程调用组件   
        └── laokou-common-data-cache               --- 数据缓存组件  
        └── laokou-common-mybatis-plus             --- 对象映射组件  
        └── laokou-common-seata                    --- 分布式事务组件  
        └── laokou-common-tdengine                 --- 时序数据库组件  
        └── laokou-common-sentinel                 --- 服务限流&熔断降级组件  
├── laokou-cloud  
        └── laokou-gateway                         --- API网关  
        └── laokou-monitor                         --- 服务监控  
        └── laokou-register                        --- 服务治理  
        └── laokou-sentinel                        --- 流量治理  
        └── laokou-seata                           --- 分布式事务  
├── laokou-service  
        └── laokou-iot                             --- 物联网模块         
        └── laokou-auth                            --- 认证授权模块  
        └── laokou-admin                           --- 后台管理模块  
        └── laokou-report                          --- 报表统计模块  
        └── laokou-generator                       --- 模板生成模块  
        └── laokou-modlule  
                └── laokou-api                     --- API模块  
                └── laokou-im                      --- 即时通讯模块  
                └── laokou-logstash                --- 日志收集模块  
~~~

### 👊 性能评测
[请点击我，查看详情](性能测试.md)

### 😛 用户权益（点个Star，拜托啦~🙏）
- 采用Apache2.0开源协议，请保留作者、Copyright信息  
- 采用Apache2.0开源协议，请保留作者、Copyright信息  
- 采用Apache2.0开源协议，请保留作者、Copyright信息  

### 😻 开源协议
KCloud-Platform-IoT 开源软件遵循 [Apache 2.0 协议](https://www.apache.org/licenses/LICENSE-2.0.html) 请务必保留作者、Copyright信息  

### 🔧 参与贡献
请查看 [提交规范（请点击我）](CONTRIBUTING.md)  

### 👀 项目地址
Github 地址：[KCloud-Platform-IoT](https://github.com/KouShenhai/KCloud-Platform-IoT)    
Gtiee 地址：[KCloud-Platform-IoT](https://gitee.com/laokouyun/KCloud-Platform-IoT)  

### 🍚 赞助打赏（用于服务器日常维护）
<div>
    <img src="doc/image/wxzp.jpg" width="201" height="300" alt="暂无图片">
    <img src="doc/image/zfb.jpg" width="201" height="300" alt="暂无图片">
    <img src="doc/image/gzh.jpg" width="201" height="300" alt="暂无图片">
    <img src="doc/image/zsxq.jpg" width="201" height="300" alt="暂无图片">
</div>

### 🙋 技术交流
<div>
    <img src="doc/image/wx.png" width="250" height="300" alt="暂无图片">
    <img src="doc/image/wxq.png" width="250" height="300" alt="暂无图片">
    <img src="doc/image/qqq.png" width="250" height="300" alt="暂无图片">
</div>

### 🐭 鸣谢组织
[Spring社区](https://spring.io)  
[Jetbrains社区](https://www.jetbrains.com/community)  
[阿里巴巴社区](https://github.com/alibaba)  
[人人社区](https://www.renren.io)   
[若依社区](https://www.ruoyi.vip)  
[苞米豆社区](https://baomidou.com)  
[livk-cloud社区](https://gitter.im/livk-cloud/community)  
[laokouyun社区](https://github.com/laokouyun)  

非常感谢 Jetbrains 提供的开源 License    
<a href="https://www.jetbrains.com/community/opensource/?utm_campaign=opensource&utm_content=approved&utm_medium=email&utm_source=newsletter&utm_term=jblogo#support"><img alt="暂无图片" width="100" height="100" src="doc/image/jb_beam.png"/></a>

### 🐼 鸣谢个人
|                                                👤                                                |                                                👤                                                |                                                👤                                                |                                                👤                                                |                                                👤                                                 |                                                👤                                                |
|:------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------------------:|
| <img height='50' width='50' src='https://avatars.githubusercontent.com/u/48756217?s=64&amp;v=4'> | <img height='50' width='50' src='https://avatars.githubusercontent.com/u/26246537?s=64&amp;v=4'> | <img height='50' width='50' src='https://avatars.githubusercontent.com/u/50291874?s=64&amp;v=4'> | <img height='50' width='50' src='https://avatars.githubusercontent.com/u/21030225?s=64&amp;v=4'> | <img height='50' width='50' src='https://avatars.githubusercontent.com/u/127269482?s=64&amp;v=4'> | <img height='50' width='50' src='https://avatars.githubusercontent.com/u/69209385?s=64&amp;v=4'> |
|                           [KouShenhai](https://github.com/KouShenhai)                            |                              [liang99](https://github.com/liang99)                               |                           [livk-cloud](https://github.com/livk-cloud)                            |                          [liukefu2050](https://github.com/liukefu2050)                           |                            [HalfPomelo](https://github.com/HalfPomelo)                            |                                [lixin](https://github.com/lixin)                                 |
| <img height='50' width='50' src='https://avatars.githubusercontent.com/u/2041471?s=64&amp;v=4'>  | <img height='50' width='50' src='https://avatars.githubusercontent.com/u/43296325?s=64&amp;v=4'> | <img height='50' width='50' src='https://avatars.githubusercontent.com/u/89563182?s=64&amp;v=4'> | <img height='50' width='50' src='https://avatars.githubusercontent.com/u/32741993?s=64&v=4'> |
|                               [simman](https://github.com/simman)                                |                             [suhengli](https://github.com/suhengli)                              |                            [gitkakafu](https://github.com/gitkakafu)                             |                                                [LeiZhiMin1](https://github.com/LeiZhiMin1)                                                 |

### ⛳️ 赞助列表（感谢各位大佬的赞助）
<table>
    <tr>
        <th>时间</th>
        <th>网名</th>
        <th>金额/物品</th>
        <th>备注</th>
    </tr>
    <tr>
        <td>2024/04/19</td>
        <td>*保熟</td>
        <td>￥188.00</td>
        <td>越做越好</td>
    </tr>
    <tr>
        <td>2024/03/20</td>
        <td>A细节*</td>
        <td>￥66.00</td>
        <td>无</td>
    </tr>
    <tr>
        <td>2024/03/03</td>
        <td>y*i</td>
        <td>￥58.88</td>
        <td>无</td>
    </tr>
    <tr>
        <td>2024/01/30</td>
        <td>*阳</td>
        <td>￥10</td>
        <td>无</td>
    </tr>
    <tr>
        <td>2023/12/22</td>
        <td>*民</td>
        <td>服务器</td>
        <td>无</td>
    </tr>
    <tr>
        <td>2023/12/08</td>
        <td>*来</td>
        <td>￥10</td>
        <td>希望越来越好，一直坚持下去</td>
    </tr>
    <tr>
        <td>2023/12/08</td>
        <td>*迪</td>
        <td>￥20</td>
        <td>越来越好，继续干下去</td>
    </tr>
    <tr>
        <td>2023/10/07</td>
        <td>何*</td>
        <td>￥399</td>
        <td>作者说：感谢支持</td>
    </tr>
    <tr>
        <td>2023/08/27</td>
        <td>*界</td>
        <td>￥10</td>
        <td>希望越来越好</td>
    </tr>
    <tr>
        <td>2023/06/29</td>
        <td>顺</td>
        <td>￥20</td>
        <td>希望项目一直做下去就好</td>
    </tr>
    <tr>
        <td>2023/03/27</td>
        <td>s*e</td>
        <td>￥10</td>
        <td>无</td>
    </tr>
</table>

### 🚫 免责声明
禁止使用本项目从事一切违法犯罪活动。作者不承担任何法律责任，特此声明

### 🐸 联系作者
博客：[https://kcloud.blog.csdn.net](https://kcloud.blog.csdn.net)    

邮箱：[2413176044@qq.com](https://mail.qq.com)  

QQ：[2413176044]( http://wpa.qq.com/msgrd?v=3&uin=2413176044&Site=gitee&Menu=yes)  

[![加入QQ群](https://img.shields.io/badge/Q群-465450496-blue.svg)](https://jq.qq.com/?_wv=1027&k=Ec8T76dR)

![GitHub Star 趋势](https://starchart.cc/KouShenhai/KCloud-Platform-IoT.svg)
