
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//代表书店首页的Servlet
@WebServlet("/ServletA")
public class ServletA extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 1. 输出网站的所有书籍
        out.write("本网站有如下书籍：<br/>");
        Map<String, Book> map = DB.getAll();
        Set<Map.Entry<String, Book>> set = map.entrySet();
        for (Map.Entry<String, Book> entry : set) {
            Book book = entry.getValue();
            // 这儿的超链接打开方式设为在新的窗口打开
            // 并且需要将每次所浏览书的id给ServletB
            out.print("<a href='/myServlet/ServletB?id=" + book.getId()
                    + "' target='_blank'>" + book.getName() + "</a>");
            out.write("<br/>");
        }

        // 4. 从浏览器带来的Cookie中，得到用户曾经看过的书籍
        out.print("<br/><br/>您曾经浏览过的书籍:<br/>");
        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals("bookHistory")) {
                String[] ids = cookies[i].getValue().split(",");// 得到浏览过所有书籍的id集合{2,3,1}
                for (String id : ids) {
                    Book book = DB.getAll().get(id);
                    out.print(book.getName() + "<br/>");
                }
            }
        }

    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

class DB {

    // 使用LinkedHashMap是为了保证存储和取出的顺序一致
    private static Map<String, Book> map = new LinkedHashMap<String, Book>();

    static {

        map.put("1", new Book("1", "javaweb开发", "老张", "一本好书"));
        map.put("2", new Book("2", "spring开发", "老黎", "一本好书"));
        map.put("3", new Book("3", "hibernate开发", "老佟", "一本好书"));
        map.put("4", new Book("4", "struts开发", "老毕", "一本好书"));
        map.put("5", new Book("5", "ajax开发", "老张", "一本好书"));

    }

    public static Map<String, Book> getAll() {
        return map;
    }

}

