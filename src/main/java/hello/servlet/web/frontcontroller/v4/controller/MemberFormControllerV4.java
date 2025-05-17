package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> paramMao, Map<String, Object> model) {
        return "new-form";
        //v3에선 직접 new ModelView 등으로 만들었는데 v4에선 그럴 필요가 없음!
        //view의 논리 이름만 넣으면 됨
        //모델도 프론트 컨트롤러에서 만들어서 넘겨 줄 것...
    }
}
