package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        return new MyView("/WEB-INF/views/new-form.jsp"); //인라인 단축키 ctrl + alt + n
        //인라인 전 코드
        //MyView myView = new MyView("/WEB-INF/views/new-form.jsp");
        //return myView;

        //지저분한 로직 없이 경로만 넣으면 되니까 깔끔해짐!!
    }
}
