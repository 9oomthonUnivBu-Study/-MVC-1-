package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServelt", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type : application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        //json도 결국 문자니까 형태를 바꿔줘야겠죠??
        //{"username": "kim", "age": 20} 요렇게 바꿔줘야 됨
        //이렇게 바꾸려면 오브젝트 매퍼가 필요!!
        //writeValueAsString() :: 객체를 가지고 값을 써서 문자를 바꿔라
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
