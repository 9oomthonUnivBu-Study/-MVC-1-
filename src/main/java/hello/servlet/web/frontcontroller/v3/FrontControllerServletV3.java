package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//같은 단어 동시 선택 단축키 alt + j

@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //여기서 pramMap을 넘겨 줘야 함
        //request에서 parameter를 다 꺼내야 함

        //getParameterNames()로 모든 파라미터 다 꺼냄!
        // 키는 paramName, 벨류는 request.getParameter(paramName)
        Map<String, String> paramMap = createParamMap(request); //자세한 로직일 경우 메서드를 뽑아서 레벨을 맞추는 게 좋음! ctrl + alt + m
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); //논리 이름
        //논리 이름을 물리 이름으로 바꿔야 함! 지금은 이름만 얻었잖아요
        //viewResolver : 실제 뷰를 찾아주는 해결자 역할
        //실제 뷰 객체까지 만들어 주는 viewResolver 만들어 주면 됨

        MyView view = viewResolver(viewName);//이 또한 대칭을 맞추기 위해 메서드 뽑기
        //viewName(논리 이름)이 new-form이라 하면 "/WEB-INF/views/new-form.jsp"가 되는 것

        view.render(mv.getModel(), request, response); //모델을 렌더에 같이 넘겨줘야 함
    }

    //viewResolver 메서드
    //뷰의 논리 이름을 실제 물리 이름을 만들어서 MyView를 반환해 줌
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp"); //실제 물리 이름
    }

    //createParamMap 메서드
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

//코드 최종 정리!
//urlPatterns = "/front-controller/v3/*" : /front-controller/v3를 포함한 하위 모든 요청은 이 서블릿에서 받아들인다!
//controllerMap : key == 매핑 URL, value == 호출될 컨트롤러
//service() : 먼저 requestURI를 조회해서 실제 호출할 컨트롤러를 controllerMap에서 찾음
//만약 없다면 404(SC_NOT_FOUND) 상태 코드 반환!!
//컨트롤러 찾고 controller.process(request, response); 호출해서 해당 컨트롤러 실행

//자꾸 jsp 오류 뜨는 이유... community에서는 jsp 지원 안 된다고 함!
//그래서 실행 안 된다고... 하는데... 커뮤니티 버전이라 그런 건지는 모르겠음