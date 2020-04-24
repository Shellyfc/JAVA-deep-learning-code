
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//显示商品详细信息的Servlet
@WebServlet("/ServletB")
public class ServletB extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 2. 根据用户带过来的id，显示商品的详细信息
        String id = request.getParameter("id");
        Book book = DB.getAll().get(id);

        out.write("您要查看的书的详细信息如下：<br/><br/>");
        out.print("书名：" + book.getName() + "<br/>");
        out.print("作者：" + book.getAuthor() + "<br/>");
        out.print("描述：" + book.getDescription() + "<br/>");

        // 3. 构建Cookie，回写给浏览器
        // 根据当前浏览书籍的id和本次request中包含的bookHistory来构建一个新的bookHistory
        String cookieValue = buildCookie(id, request,response);
        response.getWriter().write(cookieValue);
        Cookie cookie = new Cookie("bookHistory", cookieValue);

        cookie.setMaxAge(30 * 24 * 3600);
        cookie.setPath("/myServlet/"); //注意要ServletA和ServletB都能或得cookies
        response.addCookie(cookie);

    }

    private String buildCookie(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // bookHistory=null 1 bookHistory=1
        // bookHistory=3_1_5 1 bookHistory=1_3_5
        // bookHistory=3_2_5 1 bookHistory=1_3_2
        // bookHistory=3_2 1 bookHistory=1_3_2

        String bookHistory = null;
        Cookie[] cookies = request.getCookies();

        // 从浏览器带来的cookie中得到bookHistory，并将其赋值给本地变量bookHistory
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals("bookHistory")) {
                bookHistory = cookies[i].getValue();
            }
        }

        // ①如果bookHistory=null，说明浏览器之前并没有访问过本网站的书籍，所以返回当前浏览的书籍的id
        if (bookHistory == null) {
            response.getWriter().write("奇了怪了");
            return id;
        }

        // bookHistory字符串中包含的是之前浏览书籍的所有id，且以“，”分开
        // 所以可以将其切分成一个数组
        response.getWriter().write(bookHistory);
        String[] srcArray = bookHistory.split(",");
        // 将数组转成List集合
        List<String> srcList = Arrays.asList(srcArray);
        // 查看asList方法的源码，知道该方法返回的其实是一个ArrayList
        // 由于LinkedList集合的增删快，所以可以将ArrayList集合转成LinkedList
        LinkedList<String> list = new LinkedList<>();
        list.addAll(srcList);
        response.getWriter().write(list.toString());

        // ②如果bookHistory中包含当前浏览书籍的id，那么就将当前浏览书籍的id放在最前面
        if (list.contains(id)) {
            list.remove(id);
            list.addFirst(id);
        } else {
            // ③如果bookHistory中包含的书籍id大于等于3，则需要移去最后的id，并将当前浏览书籍的id放在最前面
            if (list.size() >= 3) {
                list.removeLast();
                list.addFirst(id);

            } else {
                // ④bookHistory包含的书籍id小于3，且不含当前浏览书籍的id，则直接将当前浏览书籍的id加入到最前面
                list.addFirst(id);
                response.getWriter().write(list.toString());
            }
        }

        StringBuffer sb = new StringBuffer();
        for (String l : list) {
            response.getWriter().write(l);
            sb.append(l).append(",");
        }

        // 去掉左后一个无用的逗号
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}