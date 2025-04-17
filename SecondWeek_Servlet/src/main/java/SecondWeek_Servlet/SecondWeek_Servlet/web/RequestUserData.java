package SecondWeek_Servlet.SecondWeek_Servlet.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;
import SecondWeek_Servlet.SecondWeek_Servlet.domain.Userdata;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestUserData", urlPatterns = "/register")
public class RequestUserData extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();

        String mesagedata = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        Userdata userdata = objectMapper.readValue(mesagedata, Userdata.class);
    }
}
