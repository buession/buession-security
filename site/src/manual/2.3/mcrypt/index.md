# buession-security-mcrypt 参考手册


数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等。


---


### 安装

```xml
<dependency>
    <groupId>com.buession.security</groupId>
    <artifactId>buession-security-mcrypt</artifactId>
    <version>x.x.x</version>
</dependency>
```


随着互联网的发展，对应用的安全要求越来越高，需要通过各种不同的加密算法，对铭感数据加密，包括可逆的（如：手机号码、身份证号码）和不可逆的（如：密码）。`buession-security-mcrypt` 基于此背景封装了大量的加解密、散列/哈希等算法，尚未囊括市面上主流的加密算法，会在后续的版本中继续添加。


* encode：加密，对任意对象进行加密，如果参数为 `char[]`、`byte[]` 时，将会 `new String` 创建一个 `String` 对象，其它对象会调用 `toString()` 方法转换为字符串后，再进行加密
* decode：解密，对 `CharSequence` 进行解密


```java
import com.buession.security.mcrypt.Sha1Mcrypt;

Sha1Mcrypt mcrypt = new Sha1Mcrypt();
mcrypt.encode("Abc");
```

```java
import com.buession.security.mcrypt.HmacSha512Mcrypt;

HmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt();
mcrypt.encode(new Integer(100));
```

您可以指定加密 key。


```java
import com.buession.security.mcrypt.Sha1Mcrypt;

Sha1Mcrypt mcrypt = new Sha1Mcrypt("UTF-8", "key");
mcrypt.encode("Abc");
```

```java
import com.buession.security.mcrypt.HmacSha512Mcrypt;

HmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt("UTF-8", "key");
mcrypt.encode(new Integer(100));
```


### [API 参考手册>>](https://javadoc.io/doc/com.buession.security/buession-security-mcrypt/2.3.0/index.html)