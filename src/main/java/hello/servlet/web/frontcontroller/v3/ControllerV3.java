package hello.servlet.web.frontcontroller.v3;

//v2에서도 코드 중복이 있음!! new MyView(viewPath url); 요런 식으로
//따라서 v3에서는 논리 이름을 반환할 것!!
//논리 이름이란 viewPath로 넣어주는 값이 v2에서는 물리적인 경로("/WEB-INF/views/new-form.jsp")를 넣어 준 거라면,
//v3에서는 논리 이름인 new-form만 집어넣어 주는 것!

//v3에서는 모델이랑 뷰가 같이 섞여 있는 모델뷰라는 객체를 반환할 것이다
//v2에서는 불필요한 req, res를 썼는데 이 부분 + 코드 중복 개선 위함!
//v3 구조 : 클라이언트가 http 요청 -> 프론트컨트롤러가 매핑 정보 조회 후 컨트롤러 호출 -> 컨트롤러에서 ModelView 반환
// -> 프론트 컨트롤러가 viewResolver 호출 -> viewResolver가 ModelView 반환
// -> 프론트 컨트롤러가 MyView에 render(model) 호출 -> MyView가 html 응답


//지금까지 컨트롤러에서 서블릿에 종속적인 HttpServletRequest 사용!
//Model도 request.setAttribute() 통해 데이터 저장하고 뷰에 전달
//서블릿 종속성 제거 위해 Model을 직접 만들고, 추가로 View 이름까지 전달하는 객체 만들자!!
import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    //프레임워크에 종속적인 거지 서블릿에 종속적인 게 아님!
    //불필요한 request, response 제거
    ModelView process(Map<String, String> paramMap);
}
