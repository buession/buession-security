# buession-security-pac4j 参考手册


## 注解


我们通过注解的形式封装了获取当前登录用户信息的 API。


### 注解

|  注解               | 作用域             | 说明                                                            |
|  ----              | ----              | ----                                                            |
| @Principal         | 方法参数           | 获取当前登录用户信息，并可以以任何实体类、Map 对象的形式返回            |


#### 获取当前登录用户

```java
@Controller
@RequestMapping(path = "/test")
public class TestController {

    @RequestMapping(path = "/principal1")
    @ResponseBody
    public User principal1(@Principal User user, ServerHttpResponse response){
        return user;
    }

    @RequestMapping(path = "/principal2")
    @ResponseBody
    public Map<String, Object> principal2(@Principal Map<String, Object> user, ServerHttpResponse response){
        return user;
    }

}
```