# buession-security-core 参考手册


该类库为核心包，目前仅实现了 `SameSite` 枚举的定义和数据脱敏工具 `Desensitization`。


---


### 安装

```xml
<dependency>
    <groupId>com.buession.security</groupId>
    <artifactId>buession-security-core</artifactId>
    <version>x.x.x</version>
</dependency>
```


数据脱敏：

```java
import com.buession.security.core.Desensitization;

String str = Desensitization.encode("13800138000", 3); // 1380***8000
```


### [API 参考手册>>](https://javadoc.io/doc/com.buession.security/buession-security-core/2.2.0/index.html)