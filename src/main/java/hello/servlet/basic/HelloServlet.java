package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name= "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.service(req, resp);
//        실제로 실행이 되는지 봐야 되겠죠??
//        단축키 soutm + enter 하면 클래스명과 메서드명이 나옴
        System.out.println("HelloServlet.service");

//        서블릿 http 요청이 오면 이 WAS가 서블릿 컨테이너가 리퀘스트 리스펀스 객체를 만들어서 서블릿에 던져줌
//        localhost:8080 이 url에서 호출하면 웹 브라우저가 http 요청 메시지를 만듦
//        soutv단축키
        System.out.println("request = " + request);
        System.out.println("response = " + response);

//        단축키 ctrl + alt + V
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//      위에 2개는 컨텐트 타입!! 헤더 정보에 들어가는 것

        response.getWriter().write("hello " + username);

    }
}
