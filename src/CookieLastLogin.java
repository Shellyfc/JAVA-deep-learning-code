import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieLastLogin")
public class CookieLastLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();


        Cookie[] cookies = req.getCookies();

        String cookieValue = null;
        for ( int i = 0; cookies != null && i < cookies.length; i++){
            if ( cookies[i].getName().equals("time")){
                printWriter.write("您上次登录的时间是： ");
                cookieValue = cookies[i].getValue();
                printWriter.write(cookieValue);

                cookies[i].setValue(simpleDateFormat.format(new Date()));
                resp.addCookie(cookies[i]);

                break;
            }
        }

        if(cookieValue == null ){
            Cookie cookie = new Cookie("time", simpleDateFormat.format(new Date()));
            cookie.setMaxAge(20000);
            resp.addCookie(cookie);
            printWriter.write("您是第一次登录");
        }


    }
}
