# buession-security-web 参考手册


web 安全相关的功能封装，支持 servlet 和 reactive，增加 XSS 过滤器。


---


### 安装

```xml
<dependency>
    <groupId>com.buession.security</groupId>
    <artifactId>buession-security-web</artifactId>
    <version>x.x.x</version>
</dependency>
```

您可以通过 `ServletWebSecurityConfigurerAdapterConfiguration`、`ReactiveWebSecurityConfigurerAdapterConfiguration` 来控制 HTTP 安全相关响应头，是 spring security 默认参配置的修改。


### [API 参考手册>>](https://javadoc.io/doc/com.buession.security/buession-security-web/2.0.2/index.html)