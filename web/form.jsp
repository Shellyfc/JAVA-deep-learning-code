<%--
  Created by IntelliJ IDEA.
  User: chufang
  Date: 4/23/20
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>getFormInfo</title>
</head>
<body>
<form action="HelloForm" method="POST">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="gender" value="男">男
                <input type="radio" name="gender" value="女">女
            </td>
        </tr>
        <tr>
            <td>爱好</td>
            <td>
                <input type="checkbox" name="hobbies" value="游泳">游泳
                <input type="checkbox" name="hobbies" value="跑步">跑步
                <input type="checkbox" name="hobbies" value="飞翔">飞翔
            </td>
        </tr>
        <input type="hidden" name="aaa" value="my name is cf">
        <tr>
            <td>你来自哪里？</td>
            <td>
                <select name="address">
                    <option value="广东">广东</option>
                    <option value="深圳">深圳</option>
                    <option value="北京">北京</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>详细说明:</td>
            <td>
                <textarea cols="30" rows="2" name="textarea"></textarea>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="提交"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>


</form>
</body>
</html>
