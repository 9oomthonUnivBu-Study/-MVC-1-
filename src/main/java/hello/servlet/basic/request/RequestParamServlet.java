package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("all parameter-start");

        request.getParameterNames().asIterator()
                        .forEachRemaining(paraName -> System.out.println(paraName+"="+ request.getParameter(paraName)));

        System.out.println("all parameter-end");
        System.out.println();

        System.out.println("one parameter-start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " +username);
        System.out.println("age = " +age);

        System.out.println("one parameter-end");
        System.out.println();

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] usernames = request.getParameterValues("username");

        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        response.getWriter().write("ok");
    }
}
