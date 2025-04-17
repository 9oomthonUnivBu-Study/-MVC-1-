package SecondWeek_Servlet.SecondWeek_Servlet.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    private String username;
    private int age;
    private String email;
    private String phone;

    public Member(String username, int age, String email, String phone) {
        this.username = username;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }
}