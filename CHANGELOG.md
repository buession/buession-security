 Buession Security Changelog
===========================

## [2.1.0](https://github.com/buession/buession-security/releases/tag/v2.1.0) (2022-07-xx)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.1.0)
- [owasp antisamy](https://github.com/nahsra/antisamy) 版本升级至 1.7.0


### ⭐ 新特性

- **buession-security-pac4j：** 注解 @Principal 支持 webflux 环境


### 🔔 变化

- **buession-security-mcrypt：** 废弃加密类中仅传递字符串形式的编码的构造函数
- **buession-security-pac4j：** 优化注解 @Principal HandlerMethodArgumentResolver，继承 spring 原生 HandlerMethodArgumentResolver 实现抽象类
- **buession-security-web：** 新增 ReferrerPolicy 策略转换器 ReferrerPolicyConverter


### 🐞 Bug 修复

- **buession-security-web：** 修复 HttpSecurity 构建器 ReactiveHttpSecurityBuilder、ServletHttpSecurityBuilder 中 Boolean 类型未判断 null 的 BUG


---


## [2.0.2](https://github.com/buession/buession-security/releases/tag/v2.0.2) (2022-07-28)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.0.2)


### ⭐ 新特性

- **buession-security-mcrypt：** 新增 HMAC 密码生成器


### 🐞 Bug 修复

- **buession-security-captcha：** 修复极验 v4 版本签名加密错误的 BUG


---


## [2.0.1](https://github.com/buession/buession-security/releases/tag/v2.0.1) (2022-07-17)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.0.1)
- [owasp antisamy](https://github.com/nahsra/antisamy) 版本升级至 1.6.8


### 🔔 变化

- **buession-security-web：** 安全配置类中所有基本类型，调整为包装类型 


### 🐞 Bug 修复

- **buession-security-web：** 修复 ReactiveWebSecurityConfigurerAdapterConfiguration 无参数构造函数，为初始化 Configurer 的 BUG


### 漏洞修复

- [owasp antisamy](https://github.com/nahsra/antisamy) 修复 [CVE-2022-29577](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2022-29577)、[CVE-2022-28367](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2022-28367)、[CVE-2021-35043](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2021-35043)、[CVE-2022-23437](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2022-23437)、[CVE-2021-29425](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2021-29425)、[CVE-2022-29546](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2022-29546) 漏洞


---


## [2.0.0](https://github.com/buession/buession-security/releases/tag/v2.0.0) (2022-07-07)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.0.0)


### ⭐ 新特性

- **buession-security-captcha：** 新增极验 V4 版本支持，阿里云、腾讯云行为验证码
- **buession-security-mcrypt：** 新增 HMAC、AES、DES 算法加密
- **buession-security-shiro：** 新增 SameSite 转换为 Shiro Cookie.SameSiteOptions 的转换器 SameSiteToShiroSameSiteOptionsConverter
- **buession-security-web：** 新增浏览器安全配置以及浏览器安全 Http Security 构建器和自动配置类


### 🔔 变化

- **buession-security-pac4j：** 移除 com.buession.security.pac4j.annotation.ProfileUtils，使用 com.buession.security.pac4j.profile.ProfileUtils 替代
- **buession-security-pac4j：** Pac4jWebMvcConfigurerAdapter 增加 OnServletCondition 条件控制
- **buession-security-pac4j：** 废弃实现 pac4j 和 cas、shiro 的集成，使用 io.buji:buji-pac4j 集成
- **buession-security-shiro：** 移除 com.buession.security.shiro.cache.DefaultRedisManager，使用 com.buession.security.shiro.DefaultRedisManager 替代
- **buession-security-shiro：** 移除 com.buession.security.shiro.cache.RedisManager，使用 com.buession.security.shiro.RedisManager 替代
- **buession-security-shiro：** interface 废弃 SessionDAO ，直接实现 shiro SessionDAO
- **buession-security-shiro：** interface 废弃 Cache ，直接实现 shiro Cache
- **buession-security-shiro：** interface 废弃 CacheManager ，直接实现 shiro CacheManager
- **buession-security-shiro：** 优化 Session 管理
- **buession-security-spring：** 移除 Csrf Token 生成器，直接使用 spring security csrf token 生成器


### 🐞 Bug 修复

- **buession-security-shiro：** 修复获取激活状态 Session BUG