package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//같은 단어 동시 선택 단축키 alt + j

@WebServlet(name="frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request, response);
        //render() 및 MyView forward가 안 돼서 인텔리제이에서 알려주는 대로 try-catch 구문으로 바꿔 보고
        //SerialException을 throws 해 보고
        //lombok에서 지원하는 @SneakyThrows 도 해 봤는데
        //회원가입 input창이 아예 안 뜨고 빈 결과값만 뜬다...
    }
} //이제는 process의 결과가 반환!!

//코드 최종 정리!
//urlPatterns = "/front-controller/v2/*" : /front-controller/v2를 포함한 하위 모든 요청은 이 서블릿에서 받아들인다!
//controllerMap : key == 매핑 URL, value == 호출될 컨트롤러
//service() : 먼저 requestURI를 조회해서 실제 호출할 컨트롤러를 controllerMap에서 찾음
//만약 없다면 404(SC_NOT_FOUND) 상태 코드 반환!!
//컨트롤러 찾고 controller.process(request, response); 호출해서 해당 컨트롤러 실행

//자꾸 jsp 오류 뜨는 이유... community에서는 jsp 지원 안 된다고 함!
//그래서 실행 안 된다고... 하는데... 커뮤니티 버전이라 그런 건지는 모르겠음