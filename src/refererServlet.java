import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/refererServlet")
public class refererServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String referer = req.getHeader("referer");
        System.out.println(referer);
        if(referer != null && referer.contains("localhost")){
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("正常显示");

        } else {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("盗链");
//            resp.sendRedirect("/myServlet/index.jsp");
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doGet(req, resp);
    }
}
