window.ydoc_plugin_search_json = {
  "快速上手": [
    {
      "title": "快速入门",
      "content": "TIP\n\n官方指南假设您已了解\"JAVA\"方面的相关知识，并且对安全相关的知识有一定认知。\n\nBuession Security 是一款安全方面的框架，实现了当前流行的行为验证码、加解密、数据脱敏、集成 pac4j 和 shiro、集成 spring security，提供浏览器相关的安全响应头。",
      "url": "/docs/quickstart.html",
      "children": [
        {
          "title": "下一步可做什么？",
          "url": "/docs/quickstart.html#下一步可做什么？",
          "content": "下一步可做什么？您对 Buession Security 大致了解后，您接下来可以做以下事情：了解兼容性：了解 Buession Security 的兼容性\n安装：安装/引用 Buession Security\n使用：开始使用 Buession Security 功能\n"
        }
      ]
    },
    {
      "title": "框架介绍",
      "content": "",
      "url": "/docs/intro.html",
      "children": [
        {
          "title": "Buession Security 框架是什么？",
          "url": "/docs/intro.html#buession-security-框架是什么？",
          "content": "Buession Security 框架是什么？基于 Pac4j、Shiro 二次封装，集成 Spring Security，提供极验、阿里云、腾讯云行为验证码 SDK，封装了数据脱敏和多种密码加密方式工具类库的一款安全框架。数据加密封装封装了 MD5、SHA1、SHA256、SHA512、HmacSHA1、HmacSHA256、HmacSHA512 等散列算法\n封装了 AES、DES 等对称加密算法\n封装了 Base64、原 Discuz 论坛中的加解密算法\n封装了基于以上部分算法的密码生成器，未来将会一一实现\n未来将会提供更多的常用加密算法，如：RSA 等等... ...行为验证码封装了阿里云、极验、腾讯云行为验证码，实现标准的 SDK。您只需，更改一下 CaptchaValidator、CaptchaClient 实现类的初始化，即可快速完成行为验证码的更换（当然您还需要修改前端 WEB 代码）未来，我们将会接入更多的行为验证码... ...数据脱敏为保证返回给前端的数据的安全性，我们提供了数据脱敏处理的工具类整合三方安全框架对 pac4j、io.buji:buji-pac4j 进行了整合\n整合了 apache shiro，并实现了基于 redis 的 session、cache 的缓存；并提供了对 velocity 的支持，未来会支持更多的模板引擎\n整合了 spring security，以及对 spring security 默认 Configurer 的修改，支持 servlet 和 webflux\n提供了 XSS 过滤器 XssFilter，支持 servlet 和 webflux\n"
        }
      ]
    },
    {
      "title": "开源协议",
      "content": "                             Apache License                       Version 2.0, January 2004\n                    http://www.apache.org/licenses/\nTERMS AND CONDITIONS FOR USE, REPRODUCTION, AND DISTRIBUTION\nDefinitions.\n\"License\" shall mean the terms and conditions for use, reproduction,\nand distribution as defined by Sections 1 through 9 of this document.\n\"Licensor\" shall mean the copyright owner or entity authorized by\nthe copyright owner that is granting the License.\n\"Legal Entity\" shall mean the union of the acting entity and all\nother entities that control, are controlled by, or are under common\ncontrol with that entity. For the purposes of this definition,\n\"control\" means (i) the power, direct or indirect, to cause the\ndirection or management of such entity, whether by contract or\notherwise, or (ii) ownership of fifty percent (50%) or more of the\noutstanding shares, or (iii) beneficial ownership of such entity.\n\"You\" (or \"Your\") shall mean an individual or Legal Entity\nexercising permissions granted by this License.\n\"Source\" form shall mean the preferred form for making modifications,\nincluding but not limited to software source code, documentation\nsource, and configuration files.\n\"Object\" form shall mean any form resulting from mechanical\ntransformation or translation of a Source form, including but\nnot limited to compiled object code, generated documentation,\nand conversions to other media types.\n\"Work\" shall mean the work of authorship, whether in Source or\nObject form, made available under the License, as indicated by a\ncopyright notice that is included in or attached to the work\n(an example is provided in the Appendix below).\n\"Derivative Works\" shall mean any work, whether in Source or Object\nform, that is based on (or derived from) the Work and for which the\neditorial revisions, annotations, elaborations, or other modifications\nrepresent, as a whole, an original work of authorship. For the purposes\nof this License, Derivative Works shall not include works that remain\nseparable from, or merely link (or bind by name) to the interfaces of,\nthe Work and Derivative Works thereof.\n\"Contribution\" shall mean any work of authorship, including\nthe original version of the Work and any modifications or additions\nto that Work or Derivative Works thereof, that is intentionally\nsubmitted to Licensor for inclusion in the Work by the copyright owner\nor by an individual or Legal Entity authorized to submit on behalf of\nthe copyright owner. For the purposes of this definition, \"submitted\"\nmeans any form of electronic, verbal, or written communication sent\nto the Licensor or its representatives, including but not limited to\ncommunication on electronic mailing lists, source code control systems,\nand issue tracking systems that are managed by, or on behalf of, the\nLicensor for the purpose of discussing and improving the Work, but\nexcluding communication that is conspicuously marked or otherwise\ndesignated in writing by the copyright owner as \"Not a Contribution.\"\n\"Contributor\" shall mean Licensor and any individual or Legal Entity\non behalf of whom a Contribution has been received by Licensor and\nsubsequently incorporated within the Work.\n\n\nGrant of Copyright License. Subject to the terms and conditions of\nthis License, each Contributor hereby grants to You a perpetual,\nworldwide, non-exclusive, no-charge, royalty-free, irrevocable\ncopyright license to reproduce, prepare Derivative Works of,\npublicly display, publicly perform, sublicense, and distribute the\nWork and such Derivative Works in Source or Object form.\n\n\nGrant of Patent License. Subject to the terms and conditions of\nthis License, each Contributor hereby grants to You a perpetual,\nworldwide, non-exclusive, no-charge, royalty-free, irrevocable\n(except as stated in this section) patent license to make, have made,\nuse, offer to sell, sell, import, and otherwise transfer the Work,\nwhere such license applies only to those patent claims licensable\nby such Contributor that are necessarily infringed by their\nContribution(s) alone or by combination of their Contribution(s)\nwith the Work to which such Contribution(s) was submitted. If You\ninstitute patent litigation against any entity (including a\ncross-claim or counterclaim in a lawsuit) alleging that the Work\nor a Contribution incorporated within the Work constitutes direct\nor contributory patent infringement, then any patent licenses\ngranted to You under this License for that Work shall terminate\nas of the date such litigation is filed.\n\n\nRedistribution. You may reproduce and distribute copies of the\nWork or Derivative Works thereof in any medium, with or without\nmodifications, and in Source or Object form, provided that You\nmeet the following conditions:\n(a) You must give any other recipients of the Work or\nDerivative Works a copy of this License; and\n(b) You must cause any modified files to carry prominent notices\nstating that You changed the files; and\n(c) You must retain, in the Source form of any Derivative Works\nthat You distribute, all copyright, patent, trademark, and\nattribution notices from the Source form of the Work,\nexcluding those notices that do not pertain to any part of\nthe Derivative Works; and\n(d) If the Work includes a \"NOTICE\" text file as part of its\ndistribution, then any Derivative Works that You distribute must\ninclude a readable copy of the attribution notices contained\nwithin such NOTICE file, excluding those notices that do not\npertain to any part of the Derivative Works, in at least one\nof the following places: within a NOTICE text file distributed\nas part of the Derivative Works; within the Source form or\ndocumentation, if provided along with the Derivative Works; or,\nwithin a display generated by the Derivative Works, if and\nwherever such third-party notices normally appear. The contents\nof the NOTICE file are for informational purposes only and\ndo not modify the License. You may add Your own attribution\nnotices within Derivative Works that You distribute, alongside\nor as an addendum to the NOTICE text from the Work, provided\nthat such additional attribution notices cannot be construed\nas modifying the License.\nYou may add Your own copyright statement to Your modifications and\nmay provide additional or different license terms and conditions\nfor use, reproduction, or distribution of Your modifications, or\nfor any such Derivative Works as a whole, provided Your use,\nreproduction, and distribution of the Work otherwise complies with\nthe conditions stated in this License.\n\n\nSubmission of Contributions. Unless You explicitly state otherwise,\nany Contribution intentionally submitted for inclusion in the Work\nby You to the Licensor shall be under the terms and conditions of\nthis License, without any additional terms or conditions.\nNotwithstanding the above, nothing herein shall supersede or modify\nthe terms of any separate license agreement you may have executed\nwith Licensor regarding such Contributions.\n\n\nTrademarks. This License does not grant permission to use the trade\nnames, trademarks, service marks, or product names of the Licensor,\nexcept as required for reasonable and customary use in describing the\norigin of the Work and reproducing the content of the NOTICE file.\n\n\nDisclaimer of Warranty. Unless required by applicable law or\nagreed to in writing, Licensor provides the Work (and each\nContributor provides its Contributions) on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or\nimplied, including, without limitation, any warranties or conditions\nof TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A\nPARTICULAR PURPOSE. You are solely responsible for determining the\nappropriateness of using or redistributing the Work and assume any\nrisks associated with Your exercise of permissions under this License.\n\n\nLimitation of Liability. In no event and under no legal theory,\nwhether in tort (including negligence), contract, or otherwise,\nunless required by applicable law (such as deliberate and grossly\nnegligent acts) or agreed to in writing, shall any Contributor be\nliable to You for damages, including any direct, indirect, special,\nincidental, or consequential damages of any character arising as a\nresult of this License or out of the use or inability to use the\nWork (including but not limited to damages for loss of goodwill,\nwork stoppage, computer failure or malfunction, or any and all\nother commercial damages or losses), even if such Contributor\nhas been advised of the possibility of such damages.\n\n\nAccepting Warranty or Additional Liability. While redistributing\nthe Work or Derivative Works thereof, You may choose to offer,\nand charge a fee for, acceptance of support, warranty, indemnity,\nor other liability obligations and/or rights consistent with this\nLicense. However, in accepting such obligations, You may act only\non Your own behalf and on Your sole responsibility, not on behalf\nof any other Contributor, and only if You agree to indemnify,\ndefend, and hold each Contributor harmless for any liability\nincurred by, or claims asserted against, such Contributor by reason\nof your accepting any such warranty or additional liability.\n\nEND OF TERMS AND CONDITIONSAPPENDIX: How to apply the Apache License to your work.  To apply the Apache License to your work, attach the following  boilerplate notice, with the fields enclosed by brackets \"[]\"\n  replaced with your own identifying information. (Don't include\n  the brackets!)  The text should be enclosed in the appropriate\n  comment syntax for the file format. We also recommend that a\n  file or class name and description of purpose be included on the\n  same \"printed page\" as the copyright notice for easier\n  identification within third-party archives.\nCopyright [yyyy] [name of copyright owner]Licensed under the Apache License, Version 2.0 (the \"License\");you may not use this file except in compliance with the License.\nYou may obtain a copy of the License at   http://www.apache.org/licenses/LICENSE-2.0Unless required by applicable law or agreed to in writing, softwaredistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.",
      "url": "/docs/license.html",
      "children": []
    },
    {
      "title": "模块说明",
      "content": "",
      "url": "/docs/module.html",
      "children": [
        {
          "title": "buession-security-core",
          "url": "/docs/module.html#buession-security-core",
          "content": "buession-security-core安全核心类库，安全相关实体类、枚举定义，数据脱敏工具。\n"
        },
        {
          "title": "buession-security-captcha",
          "url": "/docs/module.html#buession-security-captcha",
          "content": "buession-security-captcha极验、阿里云、腾讯云行为验证 SDK\n"
        },
        {
          "title": "buession-security-mcrypt",
          "url": "/docs/module.html#buession-security-mcrypt",
          "content": "buession-security-mcrypt数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等。\n"
        },
        {
          "title": "buession-security-pac4j",
          "url": "/docs/module.html#buession-security-pac4j",
          "content": "buession-security-pac4j对 pac4j 二次封装，集成了 pac4j 和 buji-pac4j\n"
        },
        {
          "title": "buession-security-shiro",
          "url": "/docs/module.html#buession-security-shiro",
          "content": "buession-security-shiroapache shiro 二次封装\n增加 redis 对 session 和 cache 的管理\n"
        },
        {
          "title": "buession-security-spring",
          "url": "/docs/module.html#buession-security-spring",
          "content": "buession-security-spring集成 spring security 框架\n"
        },
        {
          "title": "buession-security-web",
          "url": "/docs/module.html#buession-security-web",
          "content": "buession-security-webweb 安全相关的功能封装，支持 servlet 和 reactive\nHttp 安全构建器，HttpSecurityBuilder\nXSS filter\n"
        }
      ]
    },
    {
      "title": "版本说明",
      "content": "该项目基于 GNU 版风格定义项目版本，即：主版本号.子版本号.修正版本号。管理策略主版本号，发生变更时，不保证所有的 API 对上一个版本兼容，但保障大部分能兼容；主版本变更，可能涉及类、接口、枚举、方法的删除，或者包名的变更\n子版本号，发生变更时，完全兼容上一个版本，主要会增加一些小的功能或API，底层逻辑的调整调优\n修正版本号，主要用于修复 BUG、优化性能、安全漏洞修复，不会新增、变更、删除已有 API\n三方包兼容性说明当引用的三方包，我们保证尽大可能兼容。但对于 springframework、springboot、springcloud、springsecurity、springdata 等 spring 家族组件，以及 servlet 兼容对应的主版本。",
      "url": "/docs/version.html",
      "children": []
    },
    {
      "title": "安装及使用",
      "content": "",
      "url": "/docs/installation.html",
      "children": [
        {
          "title": "Maven 中央仓库搜索",
          "url": "/docs/installation.html#maven-中央仓库搜索",
          "content": "Maven 中央仓库搜索https://mvnrepository.com/search?q=com.buession.security\nhttps://search.maven.org/search?q=g:com.buession.security\n"
        },
        {
          "title": "手动编译",
          "url": "/docs/installation.html#手动编译",
          "content": "手动编译git clone https://github.com/buession/buession-securitycd buession-security/buession-security-parent && mvn clean install\n"
        },
        {
          "title": "Maven",
          "url": "/docs/installation.html#maven",
          "content": "Maven    com.buession.security\n    buession-security-xxx\n    x.x.x\n\n"
        },
        {
          "title": "Gradle",
          "url": "/docs/installation.html#gradle",
          "content": "Gradlecompile group: 'com.buession.security', name: 'buession-security-xxx', version: 'x.x.x'其中，artifactId 中的 xxx 表示对应的子模块；version 中的 x.x.x 代表版本号，根据需要使用特定版本，建议使用 maven 仓库中已构建好的最新版本的包。"
        }
      ]
    },
    {
      "title": "环境要求",
      "content": "JDKJDK 8+构建工具\n\n构建工具\n版本\n\n\n\n\nMaven\n3.5+\n\n\nGradle\n6.x+，推荐 6.3 及以上版本\n\n\nServlet 容器支持 servlet 3.1+，推荐使用 servlet 4.0 及以上版本。",
      "url": "/docs/requirement.html",
      "children": []
    }
  ],
  "参考手册": [
    {
      "title": "参考手册简介",
      "content": "Buession Security 是一款安全方面的框架，实现了当前流行的行为验证、加密、数据脱敏、集成 pac4j 和 shiro、集成 spring security，提供浏览器相关的安全响应头。更多介绍开源查阅框架介绍。本章节将想您讲解，如何使用 Buession Security，为您提供 Java 应用的最佳实践。",
      "url": "/manual/index.html",
      "children": []
    },
    {
      "title": "参考指南",
      "content": "本文档包含了完整的 Buession Security 的参考文档。\n\n版本\n手册\n\n\n\n\n3.0.x\nAPI 手册\n\n\n2.3.x\nAPI 手册\n\n\n2.2.x\nAPI 手册\n\n\n2.1.x\nAPI 手册\n\n\n2.0.x\nAPI 手册\n\n\n",
      "url": "/manual/overview.html",
      "children": []
    },
    {
      "title": "API 参考手册",
      "content": "Buession Security API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-security-core\n使用帮助\nAPI 手册\n\n\nbuession-security-captcha\n使用帮助\nAPI 手册\n\n\nbuession-security-crypto\n使用帮助\nAPI 手册\n\n\nbuession-security-mcrypt\n使用帮助\nAPI 手册\n\n\nbuession-security-pac4j\n使用帮助\nAPI 手册\n\n\nbuession-security-shiro\n使用帮助\nAPI 手册\n\n\nbuession-security-spring\n使用帮助\nAPI 手册\n\n\nbuession-security-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/3.0/index.html",
      "children": []
    },
    {
      "title": "buession-security-core 参考手册",
      "content": "该类库为核心包，目前仅实现了 SameSite 枚举的定义和数据脱敏工具 Desensitization。",
      "url": "/manual/3.0/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/core/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-core\n    x.x.x\n\n数据脱敏：import com.buession.security.core.Desensitization;\nString str = Desensitization.encode(\"13800138000\", 3); // 1380***8000\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "随着互联网的发展，对应用的安全要求越来越高，在安全的前提下，也需要更加注重用户体验。行为式验证码的诞生，避免了用户去读懂扭曲的图片文字，且行为式验证码背景图片采用多种图像加密技术，采用多种字体，且添加了很多随机效果，能有效防止 OCR 文字识别和暴力破解。buession-security-captcha 目前集成了极验行为验证第三代和第四代、阿里云验证码、腾讯云验证码，屏蔽了各行为验证厂商的调用细节。后续会根据实际情况，接入更多厂商的行为验证码，欢迎各位大神可以提供其它厂商的 key 用于开发测试。",
      "url": "/manual/3.0/captcha/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/captcha/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-captcha\n    x.x.x\n\n我们通过实现接口 com.buession.security.captcha.core.RequestData 定义不同厂商行为验证码需要的请求参数。AliYunRequestData：阿里云验证码请求数据\nGeetestV3RequestData：极验第三代行为验证码请求数据\nGeetestV4RequestData：极验第四代行为验证码请求数据\nTencentRequestData：腾讯云验证码请求数据\n"
        },
        {
          "title": "阿里云",
          "url": "/manual/3.0/captcha/index.html#阿里云",
          "content": "阿里云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.aliyun.AliYunCaptchaClient;\nimport com.buession.security.captcha.aliyun.AliYunRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new AliYunCaptchaClient(\"Your accessKeyId\", \"Your accessKeySecret\", \"Your appKey\", httpClient);\n\nRequestData request = new AliYunRequestData();\nrequest.setToken(\"token\");\nrequest.setSig(\"sig\");\nrequest.setSessionId(\"session id\");\nrequest.setScene(\"ecene\");\nrequest.setRemoteIp(\"User client ip\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "极验",
          "url": "/manual/3.0/captcha/index.html#极验",
          "content": "极验import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.geetest.GeetestCaptchaClient;\nimport com.buession.security.captcha.geetest.api.v4.GeetestV4RequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new GeetestCaptchaClient(\"Your appId\", \"Your secretKey\", \"version\", httpClient);\n\nRequestData request = new GeetestV4RequestData();\nrequest.setLotNumber(\"lot number\");\nrequest.setCaptchaOutput(\"captcha Output\");\nrequest.setPassToken(\"pass token\");\nrequest.setGenTime(\"gen time\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/3.0/captcha/index.html#腾讯云",
          "content": "腾讯云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.tencent.TencentCaptchaClient;\nimport com.buession.security.captcha.tencent.TencentRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new TencentCaptchaClient(\"Your secretId\", \"Your secretKey\", httpClient);\n\nRequestData request = new TencentRequestData();\nrequest.setRandstr(\"rand str\");\nrequest.setTicket(\"ticket\");\nrequest.setUserIp(\"User client ip\");\ncaptchaClient.validate(request);\n当然，在您的应用中您可不必这么麻烦的使用，我们已经为您封装好了前端提交参数到 RequestData 的转换，您可不必这么麻烦的一个一个的去设置参数值。在您的 controller 中您可以这么用。import com.buession.lang.Status;import com.buession.web.mvc.Response;\nimport com.buession.security.captcha.CaptchaClient;\nimport com.buession.security.captcha.aliyun.AliyunParameter;\nimport com.buession.security.captcha.validator.servlet.ServletAliYunCaptchaValidator;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\n@RequestMapping(path = \"/captcha\")\npublic class CaptchamentController {\n\n    @Autowired\n    private CaptchaClient captchaClient;\n\n    @RequestMapping(path = \"/validate\", method = RequestMethod.GET)\n    public Status validate(HttpServletRequest request){\n        ServletAliYunCaptchaValidator captchaValidator = new ServletAliYunCaptchaValidator(captchaClient, new AliyunParameter());\n        return captchaValidator.validate(request);\n    }\n\n}\n以上是基于 servlet 的一个简单实例，buession-security-captcha 基于上述模式也可以用于 webflux 环境。CaptchaValidator 的每个最终实现，均通过构造函数设置 com.buession.security.captcha.CaptchaClient 和 com.buession.security.captcha.core.Parameter。通过 com.buession.security.captcha.core.Parameter 的实现配置，用户提交的参数名称，也就是说，您可以自定义行为验证码前端提交到后端的参数名称，每一个 com.buession.security.captcha.core.Parameter 均设置了默认值。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/captcha/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "本文档用于说明 com.buession.security.captcha.core.Parameter 和官方参数的对应关系。",
      "url": "/manual/3.0/captcha/parameter.html",
      "children": [
        {
          "title": "阿里云",
          "url": "/manual/3.0/captcha/parameter.html#阿里云",
          "content": "阿里云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nsessionId\nSessionId\nsessionId\n会话 ID\n\n\nsig\nSig\nsig\n签名串\n\n\ntoken\nToken\ntoken\n请求唯一标识\n\n\nscene\nScene\nscene\n场景标识\n\n\n"
        },
        {
          "title": "极验",
          "url": "/manual/3.0/captcha/parameter.html#极验",
          "content": "极验第三代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nchallenge\nchallenge\nchallenge\n流水号\n\n\nseccode\nseccode\nseccode\n核心校验数据\n\n\nvalidate\nvalidate\nvalidate\n核心校验数据\n\n\nuserId\nuser_id\nuser_id\nuser_id作为终端用户的唯一标识，确定用户的唯一性\n\n\nclientType\nclient_type\nclient_type\n客户端类型\n\n\n第四代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nlotNumber\nlot_number\nlot_number\n验证流水号\n\n\ncaptchaOutput\ncaptcha_output\ncaptcha_output\n验证输出信息\n\n\npassToken\npass_token\npass_token\n验证通过标识\n\n\ngenTime\ngen_time\ngen_time\n验证通过时间戳\n\n\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/3.0/captcha/parameter.html#腾讯云",
          "content": "腾讯云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nrandStr\nRandstr\nRandstr\n客户端验证回调的随机串\n\n\nticket\nTicket\nTicket\n票据\n\n\n"
        }
      ]
    },
    {
      "title": "buession-security-crypto 参考手册",
      "content": "数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等接口。",
      "url": "/manual/3.0/crypto/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/crypto/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-crypto\n    x.x.x\n\n随着互联网的发展，对应用的安全要求越来越高，需要通过各种不同的加密算法，对铭感数据加密，包括可逆的（如：手机号码、身份证号码）和不可逆的（如：密码）。buession-security-crypto 基于此背景封装了大量的加解密、散列/哈希等算法，尚未囊括市面上主流的加密算法，会在后续的版本中继续添加。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/crypto/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "对 pac4j 二次封装，集成了 pac4j 和 buji-pac4j。",
      "url": "/manual/3.0/pac4j/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/pac4j/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-pac4j\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/pac4j/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "",
      "url": "/manual/3.0/pac4j/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/3.0/pac4j/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了获取当前登录用户信息的 API。"
        },
        {
          "title": "注解",
          "url": "/manual/3.0/pac4j/annotation.html#注解-注解",
          "content": "注解\n\n注解\n作用域\n说明\n\n\n\n\n@Principal\n方法参数\n获取当前登录用户信息，并可以以任何实体类、Map 对象的形式返回\n\n\n获取当前登录用户@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n    @RequestMapping(path = \"/principal1\")\n    @ResponseBody\n    public User principal1(@Principal User user, ServerHttpResponse response){\n        return user;\n    }\n\n    @RequestMapping(path = \"/principal2\")\n    @ResponseBody\n    public Map principal2(@Principal Map user, ServerHttpResponse response){\n        return user;\n    }\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "",
      "url": "/manual/3.0/pac4j/ajaxrequestresolver.html",
      "children": [
        {
          "title": "AjaxRequestResolver",
          "url": "/manual/3.0/pac4j/ajaxrequestresolver.html#ajaxrequestresolver",
          "content": "AjaxRequestResolverpac4j 原生的 AJAX 请求解析器 AjaxRequestResolver 的实现 DefaultAjaxRequestResolver，以 XML 的形式响应 pac4j 重定向。\n    \n\n此种，场景增加了响应数据的大小和前端 Ajax 解析的成本和难度。为此，我们扩展了 JsonAjaxRequestResolver 和 TextAjaxRequestResolver 以 JSON 和文本的形式响应重定向地址，以简化前端 JavaScript 的解析成本和难度。{\"redirect\": {\"url\": \"redirect_url\"}}redirect_url"
        }
      ]
    },
    {
      "title": "buession-security-shiro 参考手册",
      "content": "apache shiro 二次封装，增加 redis 对 session 和 cache 的管理。",
      "url": "/manual/3.0/shiro/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/shiro/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-shiro\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/shiro/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-spring 参考手册",
      "content": "集成 spring security 框架。",
      "url": "/manual/3.0/spring/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/spring/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-spring\n    x.x.x\n\n该模块无功能，仅仅整合把 spring security 的依赖整合进来了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/spring/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-web 参考手册",
      "content": "web 安全相关的功能封装，支持 servlet 和 reactive，增加 XSS 过滤器。",
      "url": "/manual/3.0/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/3.0/web/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-web\n    x.x.x\n\n您可以通过 ServletWebSecurityConfigurerAdapterConfiguration、ReactiveWebSecurityConfigurerAdapterConfiguration 来控制 HTTP 安全相关响应头，是 spring security 默认参配置的修改。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/3.0/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "API 参考手册",
      "content": "Buession Security API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-security-core\n使用帮助\nAPI 手册\n\n\nbuession-security-captcha\n使用帮助\nAPI 手册\n\n\nbuession-security-crypto\n使用帮助\nAPI 手册\n\n\nbuession-security-mcrypt\n使用帮助\nAPI 手册\n\n\nbuession-security-pac4j\n使用帮助\nAPI 手册\n\n\nbuession-security-shiro\n使用帮助\nAPI 手册\n\n\nbuession-security-spring\n使用帮助\nAPI 手册\n\n\nbuession-security-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/2.3/index.html",
      "children": []
    },
    {
      "title": "buession-security-core 参考手册",
      "content": "该类库为核心包，目前仅实现了 SameSite 枚举的定义和数据脱敏工具 Desensitization。",
      "url": "/manual/2.3/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/core/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-core\n    x.x.x\n\n数据脱敏：import com.buession.security.core.Desensitization;\nString str = Desensitization.encode(\"13800138000\", 3); // 1380***8000\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "随着互联网的发展，对应用的安全要求越来越高，在安全的前提下，也需要更加注重用户体验。行为式验证码的诞生，避免了用户去读懂扭曲的图片文字，且行为式验证码背景图片采用多种图像加密技术，采用多种字体，且添加了很多随机效果，能有效防止 OCR 文字识别和暴力破解。buession-security-captcha 目前集成了极验行为验证第三代和第四代、阿里云验证码、腾讯云验证码，屏蔽了各行为验证厂商的调用细节。后续会根据实际情况，接入更多厂商的行为验证码，欢迎各位大神可以提供其它厂商的 key 用于开发测试。",
      "url": "/manual/2.3/captcha/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/captcha/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-captcha\n    x.x.x\n\n我们通过实现接口 com.buession.security.captcha.core.RequestData 定义不同厂商行为验证码需要的请求参数。AliYunRequestData：阿里云验证码请求数据\nGeetestV3RequestData：极验第三代行为验证码请求数据\nGeetestV4RequestData：极验第四代行为验证码请求数据\nTencentRequestData：腾讯云验证码请求数据\n"
        },
        {
          "title": "阿里云",
          "url": "/manual/2.3/captcha/index.html#阿里云",
          "content": "阿里云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.aliyun.AliYunCaptchaClient;\nimport com.buession.security.captcha.aliyun.AliYunRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new AliYunCaptchaClient(\"Your accessKeyId\", \"Your accessKeySecret\", \"Your appKey\", httpClient);\n\nRequestData request = new AliYunRequestData();\nrequest.setToken(\"token\");\nrequest.setSig(\"sig\");\nrequest.setSessionId(\"session id\");\nrequest.setScene(\"ecene\");\nrequest.setRemoteIp(\"User client ip\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "极验",
          "url": "/manual/2.3/captcha/index.html#极验",
          "content": "极验import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.geetest.GeetestCaptchaClient;\nimport com.buession.security.captcha.geetest.api.v4.GeetestV4RequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new GeetestCaptchaClient(\"Your appId\", \"Your secretKey\", \"version\", httpClient);\n\nRequestData request = new GeetestV4RequestData();\nrequest.setLotNumber(\"lot number\");\nrequest.setCaptchaOutput(\"captcha Output\");\nrequest.setPassToken(\"pass token\");\nrequest.setGenTime(\"gen time\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/2.3/captcha/index.html#腾讯云",
          "content": "腾讯云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.tencent.TencentCaptchaClient;\nimport com.buession.security.captcha.tencent.TencentRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new TencentCaptchaClient(\"Your secretId\", \"Your secretKey\", httpClient);\n\nRequestData request = new TencentRequestData();\nrequest.setRandstr(\"rand str\");\nrequest.setTicket(\"ticket\");\nrequest.setUserIp(\"User client ip\");\ncaptchaClient.validate(request);\n当然，在您的应用中您可不必这么麻烦的使用，我们已经为您封装好了前端提交参数到 RequestData 的转换，您可不必这么麻烦的一个一个的去设置参数值。在您的 controller 中您可以这么用。import com.buession.lang.Status;import com.buession.web.mvc.Response;\nimport com.buession.security.captcha.CaptchaClient;\nimport com.buession.security.captcha.aliyun.AliyunParameter;\nimport com.buession.security.captcha.validator.servlet.ServletAliYunCaptchaValidator;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\n@RequestMapping(path = \"/captcha\")\npublic class CaptchamentController {\n\n    @Autowired\n    private CaptchaClient captchaClient;\n\n    @RequestMapping(path = \"/validate\", method = RequestMethod.GET)\n    public Status validate(HttpServletRequest request){\n        ServletAliYunCaptchaValidator captchaValidator = new ServletAliYunCaptchaValidator(captchaClient, new AliyunParameter());\n        return captchaValidator.validate(request);\n    }\n\n}\n以上是基于 servlet 的一个简单实例，buession-security-captcha 基于上述模式也可以用于 webflux 环境。CaptchaValidator 的每个最终实现，均通过构造函数设置 com.buession.security.captcha.CaptchaClient 和 com.buession.security.captcha.core.Parameter。通过 com.buession.security.captcha.core.Parameter 的实现配置，用户提交的参数名称，也就是说，您可以自定义行为验证码前端提交到后端的参数名称，每一个 com.buession.security.captcha.core.Parameter 均设置了默认值。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/captcha/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "本文档用于说明 com.buession.security.captcha.core.Parameter 和官方参数的对应关系。",
      "url": "/manual/2.3/captcha/parameter.html",
      "children": [
        {
          "title": "阿里云",
          "url": "/manual/2.3/captcha/parameter.html#阿里云",
          "content": "阿里云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nsessionId\nSessionId\nsessionId\n会话 ID\n\n\nsig\nSig\nsig\n签名串\n\n\ntoken\nToken\ntoken\n请求唯一标识\n\n\nscene\nScene\nscene\n场景标识\n\n\n"
        },
        {
          "title": "极验",
          "url": "/manual/2.3/captcha/parameter.html#极验",
          "content": "极验第三代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nchallenge\nchallenge\nchallenge\n流水号\n\n\nseccode\nseccode\nseccode\n核心校验数据\n\n\nvalidate\nvalidate\nvalidate\n核心校验数据\n\n\nuserId\nuser_id\nuser_id\nuser_id作为终端用户的唯一标识，确定用户的唯一性\n\n\nclientType\nclient_type\nclient_type\n客户端类型\n\n\n第四代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nlotNumber\nlot_number\nlot_number\n验证流水号\n\n\ncaptchaOutput\ncaptcha_output\ncaptcha_output\n验证输出信息\n\n\npassToken\npass_token\npass_token\n验证通过标识\n\n\ngenTime\ngen_time\ngen_time\n验证通过时间戳\n\n\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/2.3/captcha/parameter.html#腾讯云",
          "content": "腾讯云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nrandStr\nRandstr\nRandstr\n客户端验证回调的随机串\n\n\nticket\nTicket\nTicket\n票据\n\n\n"
        }
      ]
    },
    {
      "title": "buession-security-crypto 参考手册",
      "content": "数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等接口。",
      "url": "/manual/2.3/crypto/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/crypto/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-crypto\n    x.x.x\n\n随着互联网的发展，对应用的安全要求越来越高，需要通过各种不同的加密算法，对铭感数据加密，包括可逆的（如：手机号码、身份证号码）和不可逆的（如：密码）。buession-security-crypto 基于此背景封装了大量的加解密、散列/哈希等算法，尚未囊括市面上主流的加密算法，会在后续的版本中继续添加。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/crypto/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-mcrypt 参考手册",
      "content": "数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等。",
      "url": "/manual/2.3/mcrypt/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/mcrypt/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-mcrypt\n    x.x.x\n\n随着互联网的发展，对应用的安全要求越来越高，需要通过各种不同的加密算法，对铭感数据加密，包括可逆的（如：手机号码、身份证号码）和不可逆的（如：密码）。buession-security-mcrypt 基于此背景封装了大量的加解密、散列/哈希等算法，尚未囊括市面上主流的加密算法，会在后续的版本中继续添加。encode：加密，对任意对象进行加密，如果参数为 char[]、byte[] 时，将会 new String 创建一个 String 对象，其它对象会调用 toString() 方法转换为字符串后，再进行加密\ndecode：解密，对 CharSequence 进行解密\nimport com.buession.security.mcrypt.Sha1Mcrypt;\nSha1Mcrypt mcrypt = new Sha1Mcrypt();\nmcrypt.encode(\"Abc\");\nimport com.buession.security.mcrypt.HmacSha512Mcrypt;\nHmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt();\nmcrypt.encode(new Integer(100));\n您可以指定加密 key。import com.buession.security.mcrypt.Sha1Mcrypt;\nSha1Mcrypt mcrypt = new Sha1Mcrypt(\"UTF-8\", \"key\");\nmcrypt.encode(\"Abc\");\nimport com.buession.security.mcrypt.HmacSha512Mcrypt;\nHmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt(\"UTF-8\", \"key\");\nmcrypt.encode(new Integer(100));\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/mcrypt/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "对 pac4j 二次封装，集成了 pac4j 和 buji-pac4j。",
      "url": "/manual/2.3/pac4j/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/pac4j/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-pac4j\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/pac4j/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "",
      "url": "/manual/2.3/pac4j/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/2.3/pac4j/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了获取当前登录用户信息的 API。"
        },
        {
          "title": "注解",
          "url": "/manual/2.3/pac4j/annotation.html#注解-注解",
          "content": "注解\n\n注解\n作用域\n说明\n\n\n\n\n@Principal\n方法参数\n获取当前登录用户信息，并可以以任何实体类、Map 对象的形式返回\n\n\n获取当前登录用户@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n    @RequestMapping(path = \"/principal1\")\n    @ResponseBody\n    public User principal1(@Principal User user, ServerHttpResponse response){\n        return user;\n    }\n\n    @RequestMapping(path = \"/principal2\")\n    @ResponseBody\n    public Map principal2(@Principal Map user, ServerHttpResponse response){\n        return user;\n    }\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "",
      "url": "/manual/2.3/pac4j/ajaxrequestresolver.html",
      "children": [
        {
          "title": "AjaxRequestResolver",
          "url": "/manual/2.3/pac4j/ajaxrequestresolver.html#ajaxrequestresolver",
          "content": "AjaxRequestResolverpac4j 原生的 AJAX 请求解析器 AjaxRequestResolver 的实现 DefaultAjaxRequestResolver，以 XML 的形式响应 pac4j 重定向。\n    \n\n此种，场景增加了响应数据的大小和前端 Ajax 解析的成本和难度。为此，我们扩展了 JsonAjaxRequestResolver 和 TextAjaxRequestResolver 以 JSON 和文本的形式响应重定向地址，以简化前端 JavaScript 的解析成本和难度。{\"redirect\": {\"url\": \"redirect_url\"}}redirect_url"
        }
      ]
    },
    {
      "title": "buession-security-shiro 参考手册",
      "content": "apache shiro 二次封装，增加 redis 对 session 和 cache 的管理。",
      "url": "/manual/2.3/shiro/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/shiro/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-shiro\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/shiro/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-spring 参考手册",
      "content": "集成 spring security 框架。",
      "url": "/manual/2.3/spring/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/spring/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-spring\n    x.x.x\n\n该模块无功能，仅仅整合把 spring security 的依赖整合进来了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/spring/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-web 参考手册",
      "content": "web 安全相关的功能封装，支持 servlet 和 reactive，增加 XSS 过滤器。",
      "url": "/manual/2.3/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.3/web/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-web\n    x.x.x\n\n您可以通过 ServletWebSecurityConfigurerAdapterConfiguration、ReactiveWebSecurityConfigurerAdapterConfiguration 来控制 HTTP 安全相关响应头，是 spring security 默认参配置的修改。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.3/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "API 参考手册",
      "content": "Buession Security API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-security-core\n使用帮助\nAPI 手册\n\n\nbuession-security-captcha\n使用帮助\nAPI 手册\n\n\nbuession-security-mcrypt\n使用帮助\nAPI 手册\n\n\nbuession-security-pac4j\n使用帮助\nAPI 手册\n\n\nbuession-security-shiro\n使用帮助\nAPI 手册\n\n\nbuession-security-spring\n使用帮助\nAPI 手册\n\n\nbuession-security-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/2.2/index.html",
      "children": []
    },
    {
      "title": "buession-security-core 参考手册",
      "content": "该类库为核心包，目前仅实现了 SameSite 枚举的定义和数据脱敏工具 Desensitization。",
      "url": "/manual/2.2/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/core/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-core\n    x.x.x\n\n数据脱敏：import com.buession.security.core.Desensitization;\nString str = Desensitization.encode(\"13800138000\", 3); // 1380***8000\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "随着互联网的发展，对应用的安全要求越来越高，在安全的前提下，也需要更加注重用户体验。行为式验证码的诞生，避免了用户去读懂扭曲的图片文字，且行为式验证码背景图片采用多种图像加密技术，采用多种字体，且添加了很多随机效果，能有效防止 OCR 文字识别和暴力破解。buession-security-captcha 目前集成了极验行为验证第三代和第四代、阿里云验证码、腾讯云验证码，屏蔽了各行为验证厂商的调用细节。后续会根据实际情况，接入更多厂商的行为验证码，欢迎各位大神可以提供其它厂商的 key 用于开发测试。",
      "url": "/manual/2.2/captcha/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/captcha/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-captcha\n    x.x.x\n\n我们通过实现接口 com.buession.security.captcha.core.RequestData 定义不同厂商行为验证码需要的请求参数。AliYunRequestData：阿里云验证码请求数据\nGeetestV3RequestData：极验第三代行为验证码请求数据\nGeetestV4RequestData：极验第四代行为验证码请求数据\nTencentRequestData：腾讯云验证码请求数据\n"
        },
        {
          "title": "阿里云",
          "url": "/manual/2.2/captcha/index.html#阿里云",
          "content": "阿里云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.aliyun.AliYunCaptchaClient;\nimport com.buession.security.captcha.aliyun.AliYunRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new AliYunCaptchaClient(\"Your accessKeyId\", \"Your accessKeySecret\", \"Your appKey\", httpClient);\n\nRequestData request = new AliYunRequestData();\nrequest.setToken(\"token\");\nrequest.setSig(\"sig\");\nrequest.setSessionId(\"session id\");\nrequest.setScene(\"ecene\");\nrequest.setRemoteIp(\"User client ip\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "极验",
          "url": "/manual/2.2/captcha/index.html#极验",
          "content": "极验import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.geetest.GeetestCaptchaClient;\nimport com.buession.security.captcha.geetest.api.v4.GeetestV4RequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new GeetestCaptchaClient(\"Your appId\", \"Your secretKey\", \"version\", httpClient);\n\nRequestData request = new GeetestV4RequestData();\nrequest.setLotNumber(\"lot number\");\nrequest.setCaptchaOutput(\"captcha Output\");\nrequest.setPassToken(\"pass token\");\nrequest.setGenTime(\"gen time\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/2.2/captcha/index.html#腾讯云",
          "content": "腾讯云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.tencent.TencentCaptchaClient;\nimport com.buession.security.captcha.tencent.TencentRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new TencentCaptchaClient(\"Your secretId\", \"Your secretKey\", httpClient);\n\nRequestData request = new TencentRequestData();\nrequest.setRandstr(\"rand str\");\nrequest.setTicket(\"ticket\");\nrequest.setUserIp(\"User client ip\");\ncaptchaClient.validate(request);\n当然，在您的应用中您可不必这么麻烦的使用，我们已经为您封装好了前端提交参数到 RequestData 的转换，您可不必这么麻烦的一个一个的去设置参数值。在您的 controller 中您可以这么用。import com.buession.lang.Status;import com.buession.web.mvc.Response;\nimport com.buession.security.captcha.CaptchaClient;\nimport com.buession.security.captcha.aliyun.AliyunParameter;\nimport com.buession.security.captcha.validator.servlet.ServletAliYunCaptchaValidator;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\n@RequestMapping(path = \"/captcha\")\npublic class CaptchamentController {\n\n    @Autowired\n    private CaptchaClient captchaClient;\n\n    @RequestMapping(path = \"/validate\", method = RequestMethod.GET)\n    public Status validate(HttpServletRequest request){\n        ServletAliYunCaptchaValidator captchaValidator = new ServletAliYunCaptchaValidator(captchaClient, new AliyunParameter());\n        return captchaValidator.validate(request);\n    }\n\n}\n以上是基于 servlet 的一个简单实例，buession-security-captcha 基于上述模式也可以用于 webflux 环境。CaptchaValidator 的每个最终实现，均通过构造函数设置 com.buession.security.captcha.CaptchaClient 和 com.buession.security.captcha.core.Parameter。通过 com.buession.security.captcha.core.Parameter 的实现配置，用户提交的参数名称，也就是说，您可以自定义行为验证码前端提交到后端的参数名称，每一个 com.buession.security.captcha.core.Parameter 均设置了默认值。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/captcha/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "本文档用于说明 com.buession.security.captcha.core.Parameter 和官方参数的对应关系。",
      "url": "/manual/2.2/captcha/parameter.html",
      "children": [
        {
          "title": "阿里云",
          "url": "/manual/2.2/captcha/parameter.html#阿里云",
          "content": "阿里云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nsessionId\nSessionId\nsessionId\n会话 ID\n\n\nsig\nSig\nsig\n签名串\n\n\ntoken\nToken\ntoken\n请求唯一标识\n\n\nscene\nScene\nscene\n场景标识\n\n\n"
        },
        {
          "title": "极验",
          "url": "/manual/2.2/captcha/parameter.html#极验",
          "content": "极验第三代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nchallenge\nchallenge\nchallenge\n流水号\n\n\nseccode\nseccode\nseccode\n核心校验数据\n\n\nvalidate\nvalidate\nvalidate\n核心校验数据\n\n\nuserId\nuser_id\nuser_id\nuser_id作为终端用户的唯一标识，确定用户的唯一性\n\n\nclientType\nclient_type\nclient_type\n客户端类型\n\n\n第四代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nlotNumber\nlot_number\nlot_number\n验证流水号\n\n\ncaptchaOutput\ncaptcha_output\ncaptcha_output\n验证输出信息\n\n\npassToken\npass_token\npass_token\n验证通过标识\n\n\ngenTime\ngen_time\ngen_time\n验证通过时间戳\n\n\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/2.2/captcha/parameter.html#腾讯云",
          "content": "腾讯云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nrandStr\nRandstr\nRandstr\n客户端验证回调的随机串\n\n\nticket\nTicket\nTicket\n票据\n\n\n"
        }
      ]
    },
    {
      "title": "buession-security-mcrypt 参考手册",
      "content": "数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等。",
      "url": "/manual/2.2/mcrypt/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/mcrypt/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-mcrypt\n    x.x.x\n\n随着互联网的发展，对应用的安全要求越来越高，需要通过各种不同的加密算法，对铭感数据加密，包括可逆的（如：手机号码、身份证号码）和不可逆的（如：密码）。buession-security-mcrypt 基于此背景封装了大量的加解密、散列/哈希等算法，尚未囊括市面上主流的加密算法，会在后续的版本中继续添加。encode：加密，对任意对象进行加密，如果参数为 char[]、byte[] 时，将会 new String 创建一个 String 对象，其它对象会调用 toString() 方法转换为字符串后，再进行加密\ndecode：解密，对 CharSequence 进行解密\nimport com.buession.security.mcrypt.Sha1Mcrypt;\nSha1Mcrypt mcrypt = new Sha1Mcrypt();\nmcrypt.encode(\"Abc\");\nimport com.buession.security.mcrypt.HmacSha512Mcrypt;\nHmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt();\nmcrypt.encode(new Integer(100));\n您可以指定加密 key。import com.buession.security.mcrypt.Sha1Mcrypt;\nSha1Mcrypt mcrypt = new Sha1Mcrypt(\"UTF-8\", \"key\");\nmcrypt.encode(\"Abc\");\nimport com.buession.security.mcrypt.HmacSha512Mcrypt;\nHmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt(\"UTF-8\", \"key\");\nmcrypt.encode(new Integer(100));\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/mcrypt/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "对 pac4j 二次封装，集成了 pac4j 和 buji-pac4j。",
      "url": "/manual/2.2/pac4j/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/pac4j/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-pac4j\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/pac4j/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "",
      "url": "/manual/2.2/pac4j/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/2.2/pac4j/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了获取当前登录用户信息的 API。"
        },
        {
          "title": "注解",
          "url": "/manual/2.2/pac4j/annotation.html#注解-注解",
          "content": "注解\n\n注解\n作用域\n说明\n\n\n\n\n@Principal\n方法参数\n获取当前登录用户信息，并可以以任何实体类、Map 对象的形式返回\n\n\n获取当前登录用户@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n    @RequestMapping(path = \"/principal1\")\n    @ResponseBody\n    public User principal1(@Principal User user, ServerHttpResponse response){\n        return user;\n    }\n\n    @RequestMapping(path = \"/principal2\")\n    @ResponseBody\n    public Map principal2(@Principal Map user, ServerHttpResponse response){\n        return user;\n    }\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "",
      "url": "/manual/2.2/pac4j/ajaxrequestresolver.html",
      "children": [
        {
          "title": "AjaxRequestResolver",
          "url": "/manual/2.2/pac4j/ajaxrequestresolver.html#ajaxrequestresolver",
          "content": "AjaxRequestResolverpac4j 原生的 AJAX 请求解析器 AjaxRequestResolver 的实现 DefaultAjaxRequestResolver，以 XML 的形式响应 pac4j 重定向。\n    \n\n此种，场景增加了响应数据的大小和前端 Ajax 解析的成本和难度。为此，我们扩展了 JsonAjaxRequestResolver 和 TextAjaxRequestResolver 以 JSON 和文本的形式响应重定向地址，以简化前端 JavaScript 的解析成本和难度。{\"redirect\": {\"url\": \"redirect_url\"}}redirect_url"
        }
      ]
    },
    {
      "title": "buession-security-shiro 参考手册",
      "content": "apache shiro 二次封装，增加 redis 对 session 和 cache 的管理。",
      "url": "/manual/2.2/shiro/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/shiro/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-shiro\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/shiro/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-spring 参考手册",
      "content": "集成 spring security 框架。",
      "url": "/manual/2.2/spring/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/spring/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-spring\n    x.x.x\n\n该模块无功能，仅仅整合把 spring security 的依赖整合进来了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/spring/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-web 参考手册",
      "content": "web 安全相关的功能封装，支持 servlet 和 reactive，增加 XSS 过滤器。",
      "url": "/manual/2.2/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.2/web/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-web\n    x.x.x\n\n您可以通过 ServletWebSecurityConfigurerAdapterConfiguration、ReactiveWebSecurityConfigurerAdapterConfiguration 来控制 HTTP 安全相关响应头，是 spring security 默认参配置的修改。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.2/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "API 参考手册",
      "content": "Buession Security API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-security-core\n使用帮助\nAPI 手册\n\n\nbuession-security-captcha\n使用帮助\nAPI 手册\n\n\nbuession-security-mcrypt\n使用帮助\nAPI 手册\n\n\nbuession-security-pac4j\n使用帮助\nAPI 手册\n\n\nbuession-security-shiro\n使用帮助\nAPI 手册\n\n\nbuession-security-spring\n使用帮助\nAPI 手册\n\n\nbuession-security-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/2.0/index.html",
      "children": []
    },
    {
      "title": "buession-security-core 参考手册",
      "content": "该类库为核心包，目前仅实现了 SameSite 枚举的定义和数据脱敏工具 Desensitization。",
      "url": "/manual/2.0/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/core/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-core\n    x.x.x\n\n数据脱敏：import com.buession.security.core.Desensitization;\nString str = Desensitization.encode(\"13800138000\", 3); // 1380***8000\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "随着互联网的发展，对应用的安全要求越来越高，在安全的前提下，也需要更加注重用户体验。行为式验证码的诞生，避免了用户去读懂扭曲的图片文字，且行为式验证码背景图片采用多种图像加密技术，采用多种字体，且添加了很多随机效果，能有效防止 OCR 文字识别和暴力破解。buession-security-captcha 目前集成了极验行为验证第三代和第四代、阿里云验证码、腾讯云验证码，屏蔽了各行为验证厂商的调用细节。后续会根据实际情况，接入更多厂商的行为验证码，欢迎各位大神可以提供其它厂商的 key 用于开发测试。",
      "url": "/manual/2.0/captcha/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/captcha/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-captcha\n    x.x.x\n\n我们通过实现接口 com.buession.security.captcha.core.RequestData 定义不同厂商行为验证码需要的请求参数。AliYunRequestData：阿里云验证码请求数据\nGeetestV3RequestData：极验第三代行为验证码请求数据\nGeetestV4RequestData：极验第四代行为验证码请求数据\nTencentRequestData：腾讯云验证码请求数据\n"
        },
        {
          "title": "阿里云",
          "url": "/manual/2.0/captcha/index.html#阿里云",
          "content": "阿里云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.aliyun.AliYunCaptchaClient;\nimport com.buession.security.captcha.aliyun.AliYunRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new AliYunCaptchaClient(\"Your accessKeyId\", \"Your accessKeySecret\", \"Your appKey\", httpClient);\n\nRequestData request = new AliYunRequestData();\nrequest.setToken(\"token\");\nrequest.setSig(\"sig\");\nrequest.setSessionId(\"session id\");\nrequest.setScene(\"ecene\");\nrequest.setRemoteIp(\"User client ip\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "极验",
          "url": "/manual/2.0/captcha/index.html#极验",
          "content": "极验import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.geetest.GeetestCaptchaClient;\nimport com.buession.security.captcha.geetest.api.v4.GeetestV4RequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new GeetestCaptchaClient(\"Your appId\", \"Your secretKey\", \"version\", httpClient);\n\nRequestData request = new GeetestV4RequestData();\nrequest.setLotNumber(\"lot number\");\nrequest.setCaptchaOutput(\"captcha Output\");\nrequest.setPassToken(\"pass token\");\nrequest.setGenTime(\"gen time\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/2.0/captcha/index.html#腾讯云",
          "content": "腾讯云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.tencent.TencentCaptchaClient;\nimport com.buession.security.captcha.tencent.TencentRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new TencentCaptchaClient(\"Your secretId\", \"Your secretKey\", httpClient);\n\nRequestData request = new TencentRequestData();\nrequest.setRandstr(\"rand str\");\nrequest.setTicket(\"ticket\");\nrequest.setUserIp(\"User client ip\");\ncaptchaClient.validate(request);\n当然，在您的应用中您可不必这么麻烦的使用，我们已经为您封装好了前端提交参数到 RequestData 的转换，您可不必这么麻烦的一个一个的去设置参数值。在您的 controller 中您可以这么用。import com.buession.lang.Status;import com.buession.web.mvc.Response;\nimport com.buession.security.captcha.CaptchaClient;\nimport com.buession.security.captcha.aliyun.AliyunParameter;\nimport com.buession.security.captcha.validator.servlet.ServletAliYunCaptchaValidator;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\n@RequestMapping(path = \"/captcha\")\npublic class CaptchamentController {\n\n    @Autowired\n    private CaptchaClient captchaClient;\n\n    @RequestMapping(path = \"/validate\", method = RequestMethod.GET)\n    public Status validate(HttpServletRequest request){\n        ServletAliYunCaptchaValidator captchaValidator = new ServletAliYunCaptchaValidator(captchaClient, new AliyunParameter());\n        return captchaValidator.validate(request);\n    }\n\n}\n以上是基于 servlet 的一个简单实例，buession-security-captcha 基于上述模式也可以用于 webflux 环境。CaptchaValidator 的每个最终实现，均通过构造函数设置 com.buession.security.captcha.CaptchaClient 和 com.buession.security.captcha.core.Parameter。通过 com.buession.security.captcha.core.Parameter 的实现配置，用户提交的参数名称，也就是说，您可以自定义行为验证码前端提交到后端的参数名称，每一个 com.buession.security.captcha.core.Parameter 均设置了默认值。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/captcha/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "本文档用于说明 com.buession.security.captcha.core.Parameter 和官方参数的对应关系。",
      "url": "/manual/2.0/captcha/parameter.html",
      "children": [
        {
          "title": "阿里云",
          "url": "/manual/2.0/captcha/parameter.html#阿里云",
          "content": "阿里云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nsessionId\nSessionId\nsessionId\n会话 ID\n\n\nsig\nSig\nsig\n签名串\n\n\ntoken\nToken\ntoken\n请求唯一标识\n\n\nscene\nScene\nscene\n场景标识\n\n\n"
        },
        {
          "title": "极验",
          "url": "/manual/2.0/captcha/parameter.html#极验",
          "content": "极验第三代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nchallenge\nchallenge\nchallenge\n流水号\n\n\nseccode\nseccode\nseccode\n核心校验数据\n\n\nvalidate\nvalidate\nvalidate\n核心校验数据\n\n\nuserId\nuser_id\nuser_id\nuser_id作为终端用户的唯一标识，确定用户的唯一性\n\n\nclientType\nclient_type\nclient_type\n客户端类型\n\n\n第四代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nlotNumber\nlot_number\nlot_number\n验证流水号\n\n\ncaptchaOutput\ncaptcha_output\ncaptcha_output\n验证输出信息\n\n\npassToken\npass_token\npass_token\n验证通过标识\n\n\ngenTime\ngen_time\ngen_time\n验证通过时间戳\n\n\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/2.0/captcha/parameter.html#腾讯云",
          "content": "腾讯云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nrandStr\nRandstr\nRandstr\n客户端验证回调的随机串\n\n\nticket\nTicket\nTicket\n票据\n\n\n"
        }
      ]
    },
    {
      "title": "buession-security-mcrypt 参考手册",
      "content": "数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等。",
      "url": "/manual/2.0/mcrypt/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/mcrypt/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-mcrypt\n    x.x.x\n\n随着互联网的发展，对应用的安全要求越来越高，需要通过各种不同的加密算法，对铭感数据加密，包括可逆的（如：手机号码、身份证号码）和不可逆的（如：密码）。buession-security-mcrypt 基于此背景封装了大量的加解密、散列/哈希等算法，尚未囊括市面上主流的加密算法，会在后续的版本中继续添加。encode：加密，对任意对象进行加密，如果参数为 char[]、byte[] 时，将会 new String 创建一个 String 对象，其它对象会调用 toString() 方法转换为字符串后，再进行加密\ndecode：解密，对 CharSequence 进行解密\nimport com.buession.security.mcrypt.Sha1Mcrypt;\nSha1Mcrypt mcrypt = new Sha1Mcrypt();\nmcrypt.encode(\"Abc\");\nimport com.buession.security.mcrypt.HmacSha512Mcrypt;\nHmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt();\nmcrypt.encode(new Integer(100));\n您可以指定加密 key。import com.buession.security.mcrypt.Sha1Mcrypt;\nSha1Mcrypt mcrypt = new Sha1Mcrypt(\"UTF-8\", \"key\");\nmcrypt.encode(\"Abc\");\nimport com.buession.security.mcrypt.HmacSha512Mcrypt;\nHmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt(\"UTF-8\", \"key\");\nmcrypt.encode(new Integer(100));\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/mcrypt/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "对 pac4j 二次封装，集成了 pac4j 和 buji-pac4j。",
      "url": "/manual/2.0/pac4j/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/pac4j/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-pac4j\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/pac4j/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-shiro 参考手册",
      "content": "apache shiro 二次封装，增加 redis 对 session 和 cache 的管理。",
      "url": "/manual/2.0/shiro/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/shiro/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-shiro\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/shiro/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-spring 参考手册",
      "content": "集成 spring security 框架。",
      "url": "/manual/2.0/spring/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/spring/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-spring\n    x.x.x\n\n该模块无功能，仅仅整合把 spring security 的依赖整合进来了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/spring/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-web 参考手册",
      "content": "web 安全相关的功能封装，支持 servlet 和 reactive，增加 XSS 过滤器。",
      "url": "/manual/2.0/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.0/web/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-web\n    x.x.x\n\n您可以通过 ServletWebSecurityConfigurerAdapterConfiguration、ReactiveWebSecurityConfigurerAdapterConfiguration 来控制 HTTP 安全相关响应头，是 spring security 默认参配置的修改。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.0/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "API 参考手册",
      "content": "Buession Security API 包含以下目录：\n\n模块\n使用帮助\n手册\n\n\n\n\nbuession-security-core\n使用帮助\nAPI 手册\n\n\nbuession-security-captcha\n使用帮助\nAPI 手册\n\n\nbuession-security-mcrypt\n使用帮助\nAPI 手册\n\n\nbuession-security-pac4j\n使用帮助\nAPI 手册\n\n\nbuession-security-shiro\n使用帮助\nAPI 手册\n\n\nbuession-security-spring\n使用帮助\nAPI 手册\n\n\nbuession-security-web\n使用帮助\nAPI 手册\n\n\n",
      "url": "/manual/2.1/index.html",
      "children": []
    },
    {
      "title": "buession-security-core 参考手册",
      "content": "该类库为核心包，目前仅实现了 SameSite 枚举的定义和数据脱敏工具 Desensitization。",
      "url": "/manual/2.1/core/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/core/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-core\n    x.x.x\n\n数据脱敏：import com.buession.security.core.Desensitization;\nString str = Desensitization.encode(\"13800138000\", 3); // 1380***8000\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/core/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "随着互联网的发展，对应用的安全要求越来越高，在安全的前提下，也需要更加注重用户体验。行为式验证码的诞生，避免了用户去读懂扭曲的图片文字，且行为式验证码背景图片采用多种图像加密技术，采用多种字体，且添加了很多随机效果，能有效防止 OCR 文字识别和暴力破解。buession-security-captcha 目前集成了极验行为验证第三代和第四代、阿里云验证码、腾讯云验证码，屏蔽了各行为验证厂商的调用细节。后续会根据实际情况，接入更多厂商的行为验证码，欢迎各位大神可以提供其它厂商的 key 用于开发测试。",
      "url": "/manual/2.1/captcha/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/captcha/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-captcha\n    x.x.x\n\n我们通过实现接口 com.buession.security.captcha.core.RequestData 定义不同厂商行为验证码需要的请求参数。AliYunRequestData：阿里云验证码请求数据\nGeetestV3RequestData：极验第三代行为验证码请求数据\nGeetestV4RequestData：极验第四代行为验证码请求数据\nTencentRequestData：腾讯云验证码请求数据\n"
        },
        {
          "title": "阿里云",
          "url": "/manual/2.1/captcha/index.html#阿里云",
          "content": "阿里云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.aliyun.AliYunCaptchaClient;\nimport com.buession.security.captcha.aliyun.AliYunRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new AliYunCaptchaClient(\"Your accessKeyId\", \"Your accessKeySecret\", \"Your appKey\", httpClient);\n\nRequestData request = new AliYunRequestData();\nrequest.setToken(\"token\");\nrequest.setSig(\"sig\");\nrequest.setSessionId(\"session id\");\nrequest.setScene(\"ecene\");\nrequest.setRemoteIp(\"User client ip\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "极验",
          "url": "/manual/2.1/captcha/index.html#极验",
          "content": "极验import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.geetest.GeetestCaptchaClient;\nimport com.buession.security.captcha.geetest.api.v4.GeetestV4RequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new GeetestCaptchaClient(\"Your appId\", \"Your secretKey\", \"version\", httpClient);\n\nRequestData request = new GeetestV4RequestData();\nrequest.setLotNumber(\"lot number\");\nrequest.setCaptchaOutput(\"captcha Output\");\nrequest.setPassToken(\"pass token\");\nrequest.setGenTime(\"gen time\");\ncaptchaClient.validate(request);\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/2.1/captcha/index.html#腾讯云",
          "content": "腾讯云import com.buession.security.captcha.CaptchaClient;import com.buession.security.captcha.tencent.TencentCaptchaClient;\nimport com.buession.security.captcha.tencent.TencentRequestData;\nimport com.buession.security.captcha.core.RequestData;\nimport com.buession.httpclient.HttpClient;\n\nHttpClient httpClient;\nCaptchaClient captchaClient = new TencentCaptchaClient(\"Your secretId\", \"Your secretKey\", httpClient);\n\nRequestData request = new TencentRequestData();\nrequest.setRandstr(\"rand str\");\nrequest.setTicket(\"ticket\");\nrequest.setUserIp(\"User client ip\");\ncaptchaClient.validate(request);\n当然，在您的应用中您可不必这么麻烦的使用，我们已经为您封装好了前端提交参数到 RequestData 的转换，您可不必这么麻烦的一个一个的去设置参数值。在您的 controller 中您可以这么用。import com.buession.lang.Status;import com.buession.web.mvc.Response;\nimport com.buession.security.captcha.CaptchaClient;\nimport com.buession.security.captcha.aliyun.AliyunParameter;\nimport com.buession.security.captcha.validator.servlet.ServletAliYunCaptchaValidator;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.web.bind.annotation.RequestMapping;\nimport org.springframework.web.bind.annotation.RestController;\n\n@RestController\n@RequestMapping(path = \"/captcha\")\npublic class CaptchamentController {\n\n    @Autowired\n    private CaptchaClient captchaClient;\n\n    @RequestMapping(path = \"/validate\", method = RequestMethod.GET)\n    public Status validate(HttpServletRequest request){\n        ServletAliYunCaptchaValidator captchaValidator = new ServletAliYunCaptchaValidator(captchaClient, new AliyunParameter());\n        return captchaValidator.validate(request);\n    }\n\n}\n以上是基于 servlet 的一个简单实例，buession-security-captcha 基于上述模式也可以用于 webflux 环境。CaptchaValidator 的每个最终实现，均通过构造函数设置 com.buession.security.captcha.CaptchaClient 和 com.buession.security.captcha.core.Parameter。通过 com.buession.security.captcha.core.Parameter 的实现配置，用户提交的参数名称，也就是说，您可以自定义行为验证码前端提交到后端的参数名称，每一个 com.buession.security.captcha.core.Parameter 均设置了默认值。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/captcha/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-captcha 参考手册",
      "content": "本文档用于说明 com.buession.security.captcha.core.Parameter 和官方参数的对应关系。",
      "url": "/manual/2.1/captcha/parameter.html",
      "children": [
        {
          "title": "阿里云",
          "url": "/manual/2.1/captcha/parameter.html#阿里云",
          "content": "阿里云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nsessionId\nSessionId\nsessionId\n会话 ID\n\n\nsig\nSig\nsig\n签名串\n\n\ntoken\nToken\ntoken\n请求唯一标识\n\n\nscene\nScene\nscene\n场景标识\n\n\n"
        },
        {
          "title": "极验",
          "url": "/manual/2.1/captcha/parameter.html#极验",
          "content": "极验第三代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nchallenge\nchallenge\nchallenge\n流水号\n\n\nseccode\nseccode\nseccode\n核心校验数据\n\n\nvalidate\nvalidate\nvalidate\n核心校验数据\n\n\nuserId\nuser_id\nuser_id\nuser_id作为终端用户的唯一标识，确定用户的唯一性\n\n\nclientType\nclient_type\nclient_type\n客户端类型\n\n\n第四代\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nlotNumber\nlot_number\nlot_number\n验证流水号\n\n\ncaptchaOutput\ncaptcha_output\ncaptcha_output\n验证输出信息\n\n\npassToken\npass_token\npass_token\n验证通过标识\n\n\ngenTime\ngen_time\ngen_time\n验证通过时间戳\n\n\n"
        },
        {
          "title": "腾讯云",
          "url": "/manual/2.1/captcha/parameter.html#腾讯云",
          "content": "腾讯云\n\n参数名\n官方参数名\n默认值\n说明\n\n\n\n\nrandStr\nRandstr\nRandstr\n客户端验证回调的随机串\n\n\nticket\nTicket\nTicket\n票据\n\n\n"
        }
      ]
    },
    {
      "title": "buession-security-mcrypt 参考手册",
      "content": "数据加密、解密类库，支持：MD5、SHA1、SHA256、SHA512、BASE64 以及 Discuz 加密算法等等。",
      "url": "/manual/2.1/mcrypt/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/mcrypt/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-mcrypt\n    x.x.x\n\n随着互联网的发展，对应用的安全要求越来越高，需要通过各种不同的加密算法，对铭感数据加密，包括可逆的（如：手机号码、身份证号码）和不可逆的（如：密码）。buession-security-mcrypt 基于此背景封装了大量的加解密、散列/哈希等算法，尚未囊括市面上主流的加密算法，会在后续的版本中继续添加。encode：加密，对任意对象进行加密，如果参数为 char[]、byte[] 时，将会 new String 创建一个 String 对象，其它对象会调用 toString() 方法转换为字符串后，再进行加密\ndecode：解密，对 CharSequence 进行解密\nimport com.buession.security.mcrypt.Sha1Mcrypt;\nSha1Mcrypt mcrypt = new Sha1Mcrypt();\nmcrypt.encode(\"Abc\");\nimport com.buession.security.mcrypt.HmacSha512Mcrypt;\nHmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt();\nmcrypt.encode(new Integer(100));\n您可以指定加密 key。import com.buession.security.mcrypt.Sha1Mcrypt;\nSha1Mcrypt mcrypt = new Sha1Mcrypt(\"UTF-8\", \"key\");\nmcrypt.encode(\"Abc\");\nimport com.buession.security.mcrypt.HmacSha512Mcrypt;\nHmacSha512Mcrypt mcrypt = new HmacSha512Mcrypt(\"UTF-8\", \"key\");\nmcrypt.encode(new Integer(100));\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/mcrypt/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "对 pac4j 二次封装，集成了 pac4j 和 buji-pac4j。",
      "url": "/manual/2.1/pac4j/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/pac4j/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-pac4j\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/pac4j/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "",
      "url": "/manual/2.1/pac4j/annotation.html",
      "children": [
        {
          "title": "注解",
          "url": "/manual/2.1/pac4j/annotation.html#注解",
          "content": "注解我们通过注解的形式封装了获取当前登录用户信息的 API。"
        },
        {
          "title": "注解",
          "url": "/manual/2.1/pac4j/annotation.html#注解-注解",
          "content": "注解\n\n注解\n作用域\n说明\n\n\n\n\n@Principal\n方法参数\n获取当前登录用户信息，并可以以任何实体类、Map 对象的形式返回\n\n\n获取当前登录用户@Controller@RequestMapping(path = \"/test\")\npublic class TestController {\n\n    @RequestMapping(path = \"/principal1\")\n    @ResponseBody\n    public User principal1(@Principal User user, ServerHttpResponse response){\n        return user;\n    }\n\n    @RequestMapping(path = \"/principal2\")\n    @ResponseBody\n    public Map principal2(@Principal Map user, ServerHttpResponse response){\n        return user;\n    }\n\n}\n"
        }
      ]
    },
    {
      "title": "buession-security-pac4j 参考手册",
      "content": "",
      "url": "/manual/2.1/pac4j/ajaxrequestresolver.html",
      "children": [
        {
          "title": "AjaxRequestResolver",
          "url": "/manual/2.1/pac4j/ajaxrequestresolver.html#ajaxrequestresolver",
          "content": "AjaxRequestResolverpac4j 原生的 AJAX 请求解析器 AjaxRequestResolver 的实现 DefaultAjaxRequestResolver，以 XML 的形式响应 pac4j 重定向。\n    \n\n此种，场景增加了响应数据的大小和前端 Ajax 解析的成本和难度。为此，我们扩展了 JsonAjaxRequestResolver 和 TextAjaxRequestResolver 以 JSON 和文本的形式响应重定向地址，以简化前端 JavaScript 的解析成本和难度。{\"redirect\": {\"url\": \"redirect_url\"}}redirect_url"
        }
      ]
    },
    {
      "title": "buession-security-shiro 参考手册",
      "content": "apache shiro 二次封装，增加 redis 对 session 和 cache 的管理。",
      "url": "/manual/2.1/shiro/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/shiro/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-shiro\n    x.x.x\n\n"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/shiro/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-spring 参考手册",
      "content": "集成 spring security 框架。",
      "url": "/manual/2.1/spring/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/spring/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-spring\n    x.x.x\n\n该模块无功能，仅仅整合把 spring security 的依赖整合进来了。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/spring/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    },
    {
      "title": "buession-security-web 参考手册",
      "content": "web 安全相关的功能封装，支持 servlet 和 reactive，增加 XSS 过滤器。",
      "url": "/manual/2.1/web/index.html",
      "children": [
        {
          "title": "安装",
          "url": "/manual/2.1/web/index.html#安装",
          "content": "安装    com.buession.security\n    buession-security-web\n    x.x.x\n\n您可以通过 ServletWebSecurityConfigurerAdapterConfiguration、ReactiveWebSecurityConfigurerAdapterConfiguration 来控制 HTTP 安全相关响应头，是 spring security 默认参配置的修改。"
        },
        {
          "title": "API 参考手册>>",
          "url": "/manual/2.1/web/index.html#api-参考手册>>",
          "content": "API 参考手册>>"
        }
      ]
    }
  ]
}