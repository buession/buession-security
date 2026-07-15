# buession-security-captcha 参考手册


本文档用于说明 `com.buession.security.captcha.core.Parameter` 和官方参数的对应关系。


### 阿里云

|  参数名        | 官方参数名   | 默认值        | 说明           |
|  ----         | ----        | ----         | ----           |
|  sessionId    | SessionId   | sessionId    | 会话 ID        |
|  sig          | Sig         | sig          | 签名串         |
|  token        | Token       | token        | 请求唯一标识    |
|  scene        | Scene       | scene        | 场景标识        |


### 极验

第三代

|  参数名        | 官方参数名   | 默认值        | 说明                                        |
|  ----         | ----        | ----         | ----                                        |
|  challenge    | challenge   | challenge    | 流水号                                       |
|  seccode      | seccode     | seccode      | 核心校验数据                                  |
|  validate     | validate    | validate     | 核心校验数据                                  |
|  userId       | user_id     | user_id      | user_id作为终端用户的唯一标识，确定用户的唯一性   |
|  clientType   | client_type | client_type  | 客户端类型                                    |

第四代

|  参数名           | 官方参数名          | 默认值            | 说明            |
|  ----            | ----               | ----             | ----           |
|  lotNumber       | lot_number         | lot_number       | 验证流水号       |
|  captchaOutput   | captcha_output     | captcha_output   | 验证输出信息     |
|  passToken       | pass_token         | pass_token       | 验证通过标识     |
|  genTime         | gen_time           | gen_time         | 验证通过时间戳   |


### 腾讯云

|  参数名        | 官方参数名   | 默认值        | 说明                       |
|  ----         | ----        | ----         | ----                       |
|  randStr      | Randstr     | Randstr      | 客户端验证回调的随机串        |
|  ticket       | Ticket      | Ticket       | 票据                        |