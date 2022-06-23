 Buession Security Changelog
===========================


## [2.0.0](https://github.com/buession/buession-security/releases/tag/v2.0.0) (2022-02-21)

### 🔨依赖升级

-


### ⭐ 新特性

- **bbuession-security-captcha：** 新增极验 V4 版本支持，网易、阿里云、腾讯云行为验证码
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