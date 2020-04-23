import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/downloadPage")
public class downloadPage extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = this.getServletContext().getRealPath("/downloadSuccessfully.png");

        //read
        FileInputStream fileInputStream = new FileInputStream(path);

        String fileName = path.substring(path.lastIndexOf("\\")+1); //很奇怪，改成_还是不太对
        System.out.println(fileName);
        response.setHeader("Content-Disposition","attachment;filename="+fileName);

        int len = 0;
        byte[] bytes = new byte[1024];
        ServletOutputStream servletOutputStream = response.getOutputStream();

        while((len = fileInputStream.read(bytes))>0) {
            servletOutputStream.write(bytes, 0, len);
        }

        servletOutputStream.close();
        fileInputStream.close();
        

    }
}
