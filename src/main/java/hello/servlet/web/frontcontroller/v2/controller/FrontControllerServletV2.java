package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//같은 단어 동시 선택 단축키 alt + j

@WebServlet(name="frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
// v2/* << 이 경로에 어떤 하위 url이 들어와도 일단 이 서블릿이 무조건 호출되는 것
// 하위에 있든 뭐든지 일단 얘가 호출되는 것
public class FrontControllerServletV2 extends HttpServlet {

    //맵핑 정보 만들기
    //key: url! 어떤 url이 호출되면 ControllerV2를 꺼내서 호출해!
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        //지정한 url로 접속하면 해당 컨트롤러 실행
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    } //key-value로 컨트롤러 넣는 작업

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //변수 자동 정의 : Ctrl + Alt + V
        String requestURI = request.getRequestURI();
        //url 들어오면 여기서 값을 받을 수 있음
        //그래서 controller.Map 부분과 똑같겠죠...?

        //그래서 requestURI로 MAP에서 꺼내면 아래의 controller가 찾아지는 것
        ControllerV2 controller = controllerMap.get(requestURI);
        //만약 memebrs/new-form url이 요청이 됐다면, new MemberFormControllerV2() 이 객체의 인스턴스가 반환됨
        //아래의 Map 코드들도 마찬가지!!
        //우리가 이걸 인터페이스로 꺼내게 되면 이 코드를 일관성 있게 사용할 수 있음

        //다형성에 의해서 인터페이스로 받을 수 있는 것!
        //ControllerV2 controller = MemberListControllerV2() 요런 식임!
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

//코드 최종 정리!
//urlPatterns = "/front-controller/v2/*" : /front-controller/v2를 포함한 하위 모든 요청은 이 서블릿에서 받아들인다!
//controllerMap : key == 매핑 URL, value == 호출될 컨트롤러
//service() : 먼저 requestURI를 조회해서 실제 호출할 컨트롤러를 controllerMap에서 찾음
//만약 없다면 404(SC_NOT_FOUND) 상태 코드 반환!!
//컨트롤러 찾고 controller.process(request, response); 호출해서 해당 컨트롤러 실행

//자꾸 jsp 오류 뜨는 이유... community에서는 jsp 지원 안 된다고 함!
//그래서 실행 안 된다고... 하는데... 커뮤니티 버전이라 그런 건지는 모르겠음