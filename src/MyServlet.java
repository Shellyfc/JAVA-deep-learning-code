// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
// Extend HttpServlet class

//@WebServlet("/myServlet")
//@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    private String message;

    @Override
    public void init() throws ServletException
    {
        // Do required initialization
        message = "Hello World from MyServlet";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.write("同时完成浏览器和转码表");
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }


    @Override
    public void destroy()
    {
        // do nothing.
    }
}