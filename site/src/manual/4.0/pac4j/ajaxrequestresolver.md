# buession-security-pac4j 参考手册


## AjaxRequestResolver


pac4j 原生的 AJAX 请求解析器 `AjaxRequestResolver` 的实现 `DefaultAjaxRequestResolver`，以 XML 的形式响应 pac4j 重定向。

```xml
<?xml version='1.0' encoding='UTF-8'?>
<partial-response>
    <redirect url="redirect_url"></redirect>
</partial-response>
```

此种，场景增加了响应数据的大小和前端 Ajax 解析的成本和难度。为此，我们扩展了 `JsonAjaxRequestResolver` 和 `TextAjaxRequestResolver` 以 JSON 和文本的形式响应重定向地址，以简化前端 JavaScript 的解析成本和难度。

```json
{"redirect": {"url": "redirect_url"}}
```

```text
redirect_url
```