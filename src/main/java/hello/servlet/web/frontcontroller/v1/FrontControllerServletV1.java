package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
// v1/* << 이 경로에 어떤 하위 url이 들어와도 일단 이 서블릿이 무조건 호출되는 것
// 하위에 있든 뭐든지 일단 얘가 호출되는 것
public class FrontControllerServletV1 extends HttpServlet {

    //맵핑 정보 만들기
    //key: url! 어떤 url이 호출되면 ControllerV1을 꺼내서 호출해!
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        //지정한 url로 접속하면 해당 컨트롤러 실행
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        //변수 자동 정의 : Ctrl + Alt + V
        String requestURI = request.getRequestURI();
        //url 들어오면 여기서 값을 받을 수 있음
        //그래서 controller.Map 부분과 똑같겠죠...?

        //그래서 requestURI로 MAP에서 꺼내면 아래의 controller가 찾아지는 것
        ControllerV1 controller = controllerMap.get(requestURI);
        //만약 memebrs/new-form url이 요청이 됐다면, new MemberFormControllerV1() 이 객체의 인스턴스가 반환됨
        //아래의 Map 코드들도 마찬가지!!
        //우리가 이걸 인터페이스로 꺼내게 되면 이 코드를 일관성 있게 사용할 수 있음

        //다형성에 의해서 인터페이스로 받을 수 있는 것!
        //ControllerV1 controller = MemberListControllerV1() 요런 식임!
        //부모 - 자식 관계라고 생각하시오 ~~

        if (controller == null) {
            //없을 수도 있으니까 에러 처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response); //인터페이스 호출
        //Override 되어 있는 process가 호출되겠죵?
    }
}
