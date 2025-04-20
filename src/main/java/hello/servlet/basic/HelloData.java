package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {

    private String username;
    private int age;

//    lombok이 자동으로 아래의 코드 생성해 줌
//    getter and setter 단축키 alt + Insert
//public String getUsername() {
//    return username;
//}
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    public int getAge() {
//        return age;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }
}
