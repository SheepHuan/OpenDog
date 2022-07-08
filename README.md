# OpenDog

![logo.png](https://github.com/SheepHuan/OpenDog/blob/main/res/logo.png)

本课程项目基本功能已经完成

前端：https://github.com/Lawlietqwq/openDog-frontend-dev

客户端代码：`client/`，基于C++ Cinder、WebCC以及Json库构建，都是开源库。

后端代码：`website/opendogServer`,基于`Java Spring`构建。

## 项目简介

项目原型是https://perfdog.qq.com/。perdog是腾讯提供的一个闭源的手机APP性能测试工具，它于2021年底开始由免费转为收费，本项目是想要实现一个与它功能类似的应用，包括PC客户端部分，网站及其后台部分。

使用步骤：

- `PC Client`部分，登录个人账户，连接手机，打开手机APP进行使用，客户端上就会实时显示手机性能状态，例如`CPU使用率`、`内存占用`等。该部分主要实现思路是通过`Android`提供的`ADB`调试工具，通过命令行读取手机内部的系统文件，获取手机状态，并在客户端部分进行处理、记录和整理。
- `网站部分`，它提供了基本的注册、登录、查看历史数据等功能，负责记录个人用户所有的历史测试数据，并提供数据可视化。因精力有限🥱，这一部分，未实现`PerfDog`的团队协作测试功能。







## 要求

### 基本编码要求

- 每个人fork一次本仓库。
- 网站部分代码放入`website/`，客户端部分代码放入`client`。
- java部分代码请用**小驼峰命名法**，例如 `myStudentTable`。



### commit 提交要求

```
<type>(<scope>): <subject>
```

我们希望采用的type包括

- feat：新的功能或特征.
- fix：修复bug，可以在footer部分加上 close #bugNo
- update: 更新某个功能或者特征
- docs：针对文档的修改，比如修改Readme也算
- style：针对代码样式的修改，比如添加注释，调整格式等
- test：添加测试用例，或测试相关的内容
- refactor：重构，包括添加共通的类或者方法，重构框架等
- build：跟构建相关内容的修改
- revert：撤销某次的提交

例如：

```bash
docs: 修改README.md文件
fix: 修复登录界面异常
```







## Contributors

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->

<table>
  <tr>
    <td align="center"><a href="https://github.com/SheepHuan"><img src="https://avatars.githubusercontent.com/u/48245110?v=4?s=140" width="140px;" alt=""/><br /><sub><b>Huan Yang (杨欢)</b></sub></a><br /><a title="Code">💻</a> <a  title="Content">🖋</a> <a title="Documentation">📖</a> <a  title="Reviewed Pull Requests">👀</a> <a title="Design">🎨</a> </td>    
  </tr>
   </table>
<table>
   <tr>
    <td align="center"><a href="https://github.com/Lawlietqaq"><img src="https://avatars.githubusercontent.com/u/35335898?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Quan Wan (万全)</b></sub></a><br /><a title="Code">💻</a> </td>
    <td align="center"><a href="https://github.com/SheepHuan"><img src="https://avatars.githubusercontent.com/u/1567563?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Haohang Jiang (蒋昊航)</b></sub></a><br /><a title="Code">💻</a></td>  
    <td align="center"><a href="https://github.com/Xax-Lbj"><img src="https://avatars.githubusercontent.com/u/46435917?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Anxuan Xiong(熊安选)</b></sub></a><br /><a title="Code">💻</a> </td>
    <td align="center"><a href="https://github.com/CoreCXY"><img src="https://avatars.githubusercontent.com/u/92657989?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Xuyang Chen (陈旭洋)</b></sub></a><br /><a title="Code">💻</a> </td>  
    </tr>
</table>


​       



<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!

