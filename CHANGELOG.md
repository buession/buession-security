 Buession Security Changelog
===========================


## [2.3.2](https://github.com/buession/buession-security/releases/tag/v2.3.2) (2023-xx-xx)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.3.2)
- [owasp antisamy](https://github.com/nahsra/antisamy) 版本升级至 1.7.4


### ⭐ 新特性

- **


### 🔔 变化

- **buession-security-shiro：** 依赖 javax.servlet.jsp-api 更换为 jakarta.servlet.jsp-api


---


## [2.3.1](https://github.com/buession/buession-security/releases/tag/v2.3.1) (2023-11-17)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.3.1)
- [owasp antisamy](https://github.com/nahsra/antisamy) 版本升级至 1.7.3


### ⭐ 新特性

- **buession-security-shiro：** 新增任意权限 jsp tag HasAnyPermissionsTag


### 🔔 变化

- **buession-security-mcrypt：** Base64 编码、解码使用 java 内置 API
- **buession-security-pac4j：** cas client 不再默认引用
- **buession-security-web：** 移除 org.bouncycastle 依赖


---


## [2.3.0](https://github.com/buession/buession-security/releases/tag/v2.3.0) (2023-08-17)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.3.0)
- [owasp antisamy](https://github.com/nahsra/antisamy) 版本升级至 1.7.2


### ⭐ 新特性

- **buession-security-crypto：** 新增该模块，逐步替代 buession-security-mcrypt
- **buession-security-mcrypt：** 新增 SM3、SM4 加密
- **buession-security-mcrypt：** 新增 ShaPasswordGenerator、Sm3PasswordGenerator、Sm4PasswordGenerator 密码生成器


### ⏪ 优化

- 其它优化


---


## [2.2.1](https://github.com/buession/buession-security/releases/tag/v2.2.1) (2022-03-31)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.2.1)


---


## [2.2.0](https://github.com/buession/buession-security/releases/tag/v2.2.0) (2022-03-10)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.2.0)
- [owasp antisamy](https://github.com/nahsra/antisamy) 版本升级至 1.7.1


### ⭐ 新特性

- **buession-security-web：** 新增实验性 jackson HttpMessageConverter 支持 XSS 过滤


### 🔔 变化

- **buession-security-captcha：** AliYunCaptchaClient 构造函数参数 regionId 为 null 或空字符串时，不再抛出异常；而使用默认值


### 🐞 Bug 修复

- **buession-security-core：** 修复 SameSite LAX 值


---


## [2.1.2](https://github.com/buession/buession-security/releases/tag/v2.1.2) (2022-11-13)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.1.2)


### 🐞 Bug 修复

- **buession-security-captcha：** 修复极验 v3 版本生成签名错误的问题


---


## [2.1.1](https://github.com/buession/buession-security/releases/tag/v2.1.1) (2022-08-18)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.1.1)


### 🔔 变化

- **buession-security-pac4j：** ProfileUtils.toMap(CommonProfile profile) 从 CommonProfile 转换为 Map 由首先写入 CommonProfile.getAttributes() 的数据，再也入固有字段的数据转换为先写入固有字段的数据，再写入 CommonProfile.getAttributes() 的数据，该变化影响注解 @Principal 的转换结果
- **buession-security-pac4j：** 废弃注解 @Principal 的 id、realName 属性


### 🐞 Bug 修复

- **buession-security-pac4j：** 修复 JsonAjaxRequestResolver 返回值不是合法 JSON 字符的 BUG


---


## [2.1.0](https://github.com/buession/buession-security/releases/tag/v2.1.0) (2022-08-07)

### 🔨依赖升级

- [依赖库版本升级和安全漏洞修复](https://github.com/buession/buession-parent/releases/tag/v2.1.0)
- [owasp antisamy](https://github.com/nahsra/antisamy) 版本升级至 1.7.0


### ⭐ 新特性

- **buession-security-pac4j：** 注解 @Principal 支持 webflux 环境
- **buession-security-web：** 新增 ReferrerPolicy 策略转换器 ReferrerPolicyConverter


### 🔔 变化

- **buession-security-mcrypt：** 废弃加密类中仅传递字符串形式的编码的构造函数
- **buession-security-pac4j：** 优化注解 @Principal HandlerMethodArgumentResolver，继承 spring 原生 HandlerMethodArgumentResolver 实现抽象类


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