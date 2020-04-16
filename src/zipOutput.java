import java.io.*;
import java.util.zip.GZIPOutputStream;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/zipOutput")
public class zipOutput extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=UTF-8");

        String ss = "这是一串用来测试压缩的文字。好好学习天天向上，fzy是个沙子，" + "" +
                "我好想玩游戏，但是我打算再学5分钟，今天回家我一定要背诵一下GRE单词了， 不然我肯定得凉";
        //创建GZIPOutputStream对象，给予它ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream  gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);

        //GZIP对数据压缩，GZIP写入的数据是保存在byteArrayOutputStream上的
        gzipOutputStream.write(ss.getBytes());

        //gzipOutputStream有缓冲，把缓冲请了，并顺便关闭流
        gzipOutputStream.close();

        // 将压缩的数据取出来
        byte[] bytes = byteArrayOutputStream.toByteArray();


        //将压缩的数据写给浏览器
        resp.setHeader("Content-Encoding","gzip");
        resp.getOutputStream().write(bytes);

        resp.getWriter().write("original length is " + ss.getBytes().length + "</br>");
        System.out.println("original length is" + ss.getBytes().length);
        System.out.println("zipped length is" + bytes.length);

//        resp.getWriter().write(ss);
    }


}
