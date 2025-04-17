package SecondWeek_Servlet.SecondWeek_Servlet.web;

import SecondWeek_Servlet.SecondWeek_Servlet.domain.MemoryMemberRepository;
import SecondWeek_Servlet.SecondWeek_Servlet.domain.Userdata;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import SecondWeek_Servlet.SecondWeek_Servlet.domain.Member;
import SecondWeek_Servlet.SecondWeek_Servlet.domain.MemberRepository;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ResponseUserData", urlPatterns = "/register")
public class ResponseUserData extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Userdata userdata = objectMapper.readValue(request.getInputStream(), Userdata.class);

        Member member = new Member(userdata.getUsername(), userdata.getAge(), userdata.getEmail(), userdata.getPhone());

        MemoryMemberRepository repository = MemoryMemberRepository.getInstance();
        Member savedMember = repository.save(member);

        String message = savedMember.getUsername() + "님, 가입을 환영합니다!";

        Map<String, String> result = new HashMap<>();
        result.put("message", message);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}