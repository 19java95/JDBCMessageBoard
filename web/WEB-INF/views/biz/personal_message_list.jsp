<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>

        <meta charset="UTF-8">
        <title>留言板</title>
        <link rel="stylesheet" href="../../../css/index.css">
        <script type="text/javascript">
            function submitMessageForm(flag) {
                if ('first' == flag) {
                    document.getElementById('page').value = 1;
                } else if ('pre' == flag) {
                    var current = Number(document.getElementById('page').value);
                    if (current > 1) {
                        document.getElementById('page').value = current - 1;
                    }
                } else if ('next' == flag) {
                    var current = Number(document.getElementById('page').value);
                    var last = Number(document.getElementById('last').value);
                    if (current < last) {
                        document.getElementById('page').value = current + 1;
                    }
                } else if ('last' == flag) {
                    var last = Number(document.getElementById('last').value);
                    document.getElementById('page').value = last < 1 ? 1 : last;
                }
                document.getElementById('messageForm').submit();
            }
        </script>
    </head>

    <body>
        <header>
            <div class="container">
                <% if (null != request.getSession().getAttribute("user")) {%>
                    <nav>
                        <a href=/message/list.do>返回留言板</a>
                    </nav>
                    <nav>
                        <a href="/userInfo.do">我的信息</a>
                    </nav>
                <%} else { %>
                    <nav>
                        <a href="/login.do">登录</a>
                        <a href="/regPrompt.do">注册</a>
                    </nav>
                <% } %>
            </div>
        </header>
        <section class="banner">
            <div class="container">
                <div>
                    <h1>慕课网留言板</h1>
                    <p>慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。 </p>
                </div>
            </div>
        </section>
        <section class="main">
            <div class="container">
                <c:forEach items="${pmessages}" var="msg">
                    <div class="alt-item">
                        <div class="alt-head">
                            <div class="alt-info">
                                <span>作者：<a href="">${msg.username}</a></span>
                                <span>时间：<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${msg.createTime}"/></span>
                            </div>
                            <style>
                                .alt-update{float:right;
                                    width:100px;
                                    height:25px;
                                    border:0px solid #000;
                                    background-color: #c4e3f3}
                                #1fatie button{
                                    width:80px;
                                    height:30px;
                                    color:white;
                                    background-color:#428BCA;
                                    border:1px solid #428BCA;
                                    border-left:2px solid white;
                                    border-top:2px solid white;
                                    border-radius:5px;
                                    font-weight:bold;
                                    cursor:pointer;
                                    box-shadow:5px 5px 12px #ccc;
                                }
                            </style>
                            <div class="alt-update" id="fatie">
                                <input type="button" a href="xiugai.do" value="修改">
                                <input type="button" a href="hanchu.do" value="删除">
                            </div>

                        </div>
                        <div class="alt-content">
                            <h3>${msg.title}</h3>
                            <p>${msg.content}</p>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
        <section class="page">
            <div class="container">
                <% if (null != request.getSession().getAttribute("user")) {%>
                    <div id="fatie">
                        <a href="/addMessagePrompt.do"><button>点我留言</button></a>
                    </div>
                <%} else { %>
                    <div id="fatie">
                        请<a href="/login.do"><button>登录</button></a>后留言
                    </div>
                <% } %>
            </div>
        </section>
        <footer>
            copy@慕课网
        </footer>
    </body>
</html>