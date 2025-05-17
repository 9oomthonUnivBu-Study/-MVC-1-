package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//컨트롤러V1을 구현해야 하기 때문에 implements 하는 것
public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
//springmvc > MvcMemberFormServlet 코드와 viewPath 전부 고대로 쓸 것임
//views > new-form.jsp를 상대경로로 설정한 이유!!
//절대경로로 하면 프로젝트마다 경로 자체가 계속 바뀜... 번거롭자나용?