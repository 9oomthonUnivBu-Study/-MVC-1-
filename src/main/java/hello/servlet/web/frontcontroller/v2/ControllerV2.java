package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {

    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
//v1 controller와 코드는 똑같지만! 반환하는 값을 MyView로 설정
//ControllerV1은 void로 반환했기 때문에 컨트롤러가 알아서 다 forward로 이동
//ControllerV2는 MyView를 만들어서 넘기면 되는 식으로 인터페이스 설계함