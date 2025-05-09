package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id; //리포지토리에 저장하면 아이디 발급됨 >> 회원 저장소 만듭시다 (domain/member/Repository
    private String username;
    private int age;
    
//    기본 생성자
    public Member(){
        
    }

//    생성자로 username과 age를 가짐
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
