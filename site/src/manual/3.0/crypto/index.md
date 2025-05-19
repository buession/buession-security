# buession-security-crypto 参考手册


数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等接口。


---


### 安装

```xml
<dependency>
    <groupId>com.buession.security</groupId>
    <artifactId>buession-security-crypto</artifactId>
    <version>x.x.x</version>
</dependency>
```


随着互联网的发展，对应用的安全要求越来越高，需要通过各种不同的加密算法，对铭感数据加密，包括可逆的（如：手机号码、身份证号码）和不可逆的（如：密码）。`buession-security-crypto` 基于此背景封装了大量的加解密、散列/哈希等算法，尚未囊括市面上主流的加密算法，会在后续的版本中继续添加。


### [API 参考手册>>](https://javadoc.io/doc/com.buession.security/buession-security-crypto/3.0.0/index.html)