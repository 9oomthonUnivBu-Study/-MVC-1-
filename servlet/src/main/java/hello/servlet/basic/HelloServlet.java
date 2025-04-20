package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    //HTTP 요청을 통해 매핑된 URL이 호출되면 서블릿 컨테이너는 다음 메서드를 실행한다. protected void service(...) {}
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // request는 클라이언트가 보낸 요청 정보를 담고 있고, response는 서버가 클라이언트에게 응답을 보낼 때 사용된다.
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //다음 두 정보는 HTTP 헤더부분에 들어간다.
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // .write()를 하면 HTTP 바디부분에 다음 데이터가 들어간다.
        response.getWriter().write("Hello " + username);
    }
}
