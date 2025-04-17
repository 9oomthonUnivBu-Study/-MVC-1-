package SecondWeek_Servlet.SecondWeek_Servlet.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Userdata {
    private Long id;
    private String username;
    private int age;
    private String email;
    private String phone;

    public Userdata() {}
}