package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        //컨트롤러에서 나머지 다 처리하고 맵에다 request 요청 파라미터 정보를 다 넣어서 넘겨줄 것
        //여기선 단순히 꺼내서 쓰면 됨!
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age")); //문자로 오기 때문에 parseint() 해 줘야 함

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member); //모델 가져와서 member 집어넣어줌
        return mv;


    }
}
