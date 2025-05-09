package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

//Assertion에 alt + enter 누르면 자동으로 됨
import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

//    테스트 할 대상
    MemberRepository memberRepository = MemberRepository.getInstance();

//    테스트 끝나면 깔끔하게 초기화
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    //저장하는 것
    @Test
    void save(){
        //given (이런 게 주어졌을 때)
        Member member = new Member("hello", 20);

        //when (이런 걸 실행했을 때)
        Member savedMember = memberRepository.save(member);

        //then (결과가 이거여야 해)
        Member findMember = memberRepository.findById(savedMember.getId());
        //Assertions > org.assertj 거 써야 함
        assertThat(findMember).isEqualTo(savedMember);
    }

    //모든 걸 조회하기
    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        //개수 확인
        assertThat(result.size()).isEqualTo(2);
        //result 안에 member1과 member2 객체가 있는가 물어보는 것
        assertThat(result).contains(member1, member2);
    }
}
