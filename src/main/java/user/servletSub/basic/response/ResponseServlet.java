package user.servletSub.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.print.attribute.standard.PresentationDirection;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name ="responseServlet", urlPatterns = "/response-header")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");

        PrintWriter writer = response.getWriter();
        writer.println("환영합니다, " + username + "님!");
    }

}
