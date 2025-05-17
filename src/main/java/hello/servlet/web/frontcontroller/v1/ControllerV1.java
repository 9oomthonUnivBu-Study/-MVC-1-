package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//꼭!! java class interface 선택해서 생성해야 함
//요청이 오고 맵핑 정보에서 데이터를 찾아 호출할 때, 다형성을 사용하면
//프론트 컨트롤러는 인터페이스에 의존하면서 편하게 호출할 수 있음!!
public interface ControllerV1 {

    //서블릿이랑 똑같은 모양의 인터페이스 만들기
    //각 컨트롤러들은 이 인터페이스를 구현하면 됨!
    //프론트 컨트롤러는 이 인터페이스를 호출해서 구현과 관계없이 로직의 일관성을 가질 수 있음
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
