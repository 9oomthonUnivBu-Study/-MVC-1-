package hello.servlet.web.frontcontroller.v4;

//v3 컨트롤러는 서블릿 종속성 제거 + 뷰 경로 중복 제거 등으로 잘 설계된 컨트롤러!!
//그런데 실제 컨트롤러 인터페이스를 구현하는 개발자 입장에서 보면, 항상 ModelView 객체를 생성하고 반환해야 하는 부분이 번거로움..
//v4에서는 컨트롤러가 ModelView를 반환하지 않고 viewName만 반환한다는 점이 다름!!

//v4 구조 : 클라이언트가 http 요청 -> 프론트 컨트롤러가 매핑 정보 조회 후 컨트롤러 호출(paramMap, model)
// -> 컨트롤러가 viewName 반환 -> 프론트 컨트롤러가 viewResolver 호출 -> MyView 반환 받음
// -> render(model) 호출 -> MyView가 html 응답

//v1 ~ v4까지의 컨트롤러의 공통된 단점!!
//항상 모양이 정해져 있음...
//v4 말고 v1 모양의 인터페이스로 구현하고 싶다!! 할 때가 있을 거잖아용
//컨트롤러 보면 ControllerV4 이런 식으로 딱 박혀 있음
//인터페이스 자체를 바꿀 수 있는 방법이 없음!! 인터페이스로 제약하는 것의 장점이자 단점
//그래서~~ v5에서는 이 문제를 해결합니당 > 어떤 컨트롤러든 호출할 수 있는 프론트 컨트롤러를 만들 것

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param paramMao
     * @param model
     * @return
     */
    String process(Map<String, String> paramMao, Map<String, Object> model);
}
