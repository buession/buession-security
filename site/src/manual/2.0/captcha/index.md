# buession-security-captcha 参考手册


随着互联网的发展，对应用的安全要求越来越高，在安全的前提下，也需要更加注重用户体验。行为式验证码的诞生，避免了用户去读懂扭曲的图片文字，且行为式验证码背景图片采用多种图像加密技术，采用多种字体，且添加了很多随机效果，能有效防止 OCR 文字识别和暴力破解。

`buession-security-captcha` 目前集成了极验行为验证第三代和第四代、阿里云验证码、腾讯云验证码，屏蔽了各行为验证厂商的调用细节。后续会根据实际情况，接入更多厂商的行为验证码，欢迎各位大神可以提供其它厂商的 key 用于开发测试。


---


### 安装

```xml
<dependency>
    <groupId>com.buession.security</groupId>
    <artifactId>buession-security-captcha</artifactId>
    <version>x.x.x</version>
</dependency>
```


我们通过实现接口 `com.buession.security.captcha.core.RequestData` 定义不同厂商行为验证码需要的请求参数。

* AliYunRequestData：阿里云验证码请求数据
* GeetestV3RequestData：极验第三代行为验证码请求数据
* GeetestV4RequestData：极验第四代行为验证码请求数据
* TencentRequestData：腾讯云验证码请求数据


### 阿里云

```java
import com.buession.security.captcha.CaptchaClient;
import com.buession.security.captcha.aliyun.AliYunCaptchaClient;
import com.buession.security.captcha.aliyun.AliYunRequestData;
import com.buession.security.captcha.core.RequestData;
import com.buession.httpclient.HttpClient;

HttpClient httpClient;
CaptchaClient captchaClient = new AliYunCaptchaClient("Your accessKeyId", "Your accessKeySecret", "Your appKey", httpClient);

RequestData request = new AliYunRequestData();
request.setToken("token");
request.setSig("sig");
request.setSessionId("session id");
request.setScene("ecene");
request.setRemoteIp("User client ip");
captchaClient.validate(request);
```


### 极验

```java
import com.buession.security.captcha.CaptchaClient;
import com.buession.security.captcha.geetest.GeetestCaptchaClient;
import com.buession.security.captcha.geetest.api.v4.GeetestV4RequestData;
import com.buession.security.captcha.core.RequestData;
import com.buession.httpclient.HttpClient;

HttpClient httpClient;
CaptchaClient captchaClient = new GeetestCaptchaClient("Your appId", "Your secretKey", "version", httpClient);

RequestData request = new GeetestV4RequestData();
request.setLotNumber("lot number");
request.setCaptchaOutput("captcha Output");
request.setPassToken("pass token");
request.setGenTime("gen time");
captchaClient.validate(request);
```


### 腾讯云

```java
import com.buession.security.captcha.CaptchaClient;
import com.buession.security.captcha.tencent.TencentCaptchaClient;
import com.buession.security.captcha.tencent.TencentRequestData;
import com.buession.security.captcha.core.RequestData;
import com.buession.httpclient.HttpClient;

HttpClient httpClient;
CaptchaClient captchaClient = new TencentCaptchaClient("Your secretId", "Your secretKey", httpClient);

RequestData request = new TencentRequestData();
request.setRandstr("rand str");
request.setTicket("ticket");
request.setUserIp("User client ip");
captchaClient.validate(request);
```

当然，在您的应用中您可不必这么麻烦的使用，我们已经为您封装好了前端提交参数到 `RequestData` 的转换，您可不必这么麻烦的一个一个的去设置参数值。

在您的 controller 中您可以这么用。


```java
import com.buession.lang.Status;
import com.buession.web.mvc.Response;
import com.buession.security.captcha.CaptchaClient;
import com.buession.security.captcha.aliyun.AliyunParameter;
import com.buession.security.captcha.validator.servlet.ServletAliYunCaptchaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/captcha")
public class CaptchamentController {

    @Autowired
    private CaptchaClient captchaClient;

    @RequestMapping(path = "/validate", method = RequestMethod.GET)
    public Status validate(HttpServletRequest request){
        ServletAliYunCaptchaValidator captchaValidator = new ServletAliYunCaptchaValidator(captchaClient, new AliyunParameter());
        return captchaValidator.validate(request);
    }

}
```

以上是基于 servlet 的一个简单实例，`buession-security-captcha` 基于上述模式也可以用于 webflux 环境。`CaptchaValidator` 的每个最终实现，均通过构造函数设置 `com.buession.security.captcha.CaptchaClient` 和 `com.buession.security.captcha.core.Parameter`。通过 `com.buession.security.captcha.core.Parameter` 的实现配置，用户提交的参数名称，也就是说，您可以自定义行为验证码前端提交到后端的参数名称，每一个 `com.buession.security.captcha.core.Parameter` 均设置了默认值。


### [API 参考手册>>](https://javadoc.io/doc/com.buession.security/buession-security-captcha/2.0.2/index.html)