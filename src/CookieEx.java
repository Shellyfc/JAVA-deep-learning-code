import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet("/cookieEx")
public class CookieEx extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String value = "中文的value";

        Cookie cookie = new Cookie("info", URLEncoder.encode(value, "UTF-8"));
        // 存下来的是encoded的半杯，需要decode成中文
        cookie.setMaxAge(1000);
        cookie.setPath("/myServlet/MyServlet");

        resp.addCookie(cookie);

        resp.getWriter().write("发送了一个cookie");

        //decode

        Cookie[] cookies = req.getCookies();
        for( int i = 0; cookies != null && i < cookies.length; i++){
            String name = cookies[i].getName();

            String value2 = URLDecoder.decode(cookies[i].getValue(),"UTF-8");

            resp.getWriter().write(name + " " + value2);
        }
    }
}
