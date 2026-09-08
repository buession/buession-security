# 框架介绍


### Buession Security 框架是什么？
基于 Pac4j、Shiro 二次封装，集成 Spring Security，提供极验、阿里云、腾讯云行为验证码 SDK，封装了数据脱敏和多种密码加密方式工具类库的一款安全框架。


#### 数据加密封装
1. 封装了 MD5、SHA1、SHA256、SHA512、HmacSHA1、HmacSHA256、HmacSHA512 等散列算法
2. 封装了 AES、DES 等对称加密算法
3. 封装了 Base64、原 Discuz 论坛中的加解密算法
4. 封装了基于以上部分算法的密码生成器，未来将会一一实现

未来将会提供更多的常用加密算法，如：RSA 等等... ...


#### 行为验证码
封装了阿里云、极验、腾讯云行为验证码，实现标准的 SDK。
您只需，更改一下 `CaptchaValidator`、`CaptchaClient` 实现类的初始化，即可快速完成行为验证码的更换（当然您还需要修改前端 WEB 代码）

未来，我们将会接入更多的行为验证码... ...


#### 数据脱敏
为保证返回给前端的数据的安全性，我们提供了数据脱敏处理的工具类


#### 整合三方安全框架
1. 对 pac4j、io.buji:buji-pac4j 进行了整合
2. 整合了 apache shiro，并实现了基于 redis 的 session、cache 的缓存；并提供了对 velocity 的支持，未来会支持更多的模板引擎
3. 整合了 spring security，以及对 spring security 默认 Configurer 的修改，支持 servlet 和 webflux
4. 提供了 XSS 过滤器 `XssFilter`，支持 servlet 和 webflux