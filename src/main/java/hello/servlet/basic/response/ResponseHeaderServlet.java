package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        요렇게 하면 http 응답 코드를 넣을 수 있음!!
//        node.js 방식과 유사
//        response.setStatus(200);
//        아니면! http servlet response에 SC_OK라는 게 있음! 상수로 정의되어 있는데, 이렇게 사용하는 게 훨씬 좋다
//        200이라는 값을 직접 적는 것보다 아래의 코드처럼 해 줘야 의미 있는 값으로 쓰임!
//        어떤 의미인지 바로 확인할 수 있기 때문~~

//      [status-line] : HTTP 스펙에서 응답의 첫 번째
        response.setStatus(HttpServletResponse.SC_OK);

//        bad request도 한 번 해 봅시다~~
//         response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

//      [response-headers] : 한글 깨짐 방지 위해 charset=utf-8 추가
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidata");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

//     [Header 편의 메서드]
      content(response);
      cookie(response);
      redirect(response);

        PrintWriter writer = response.getWriter();
//        writer.println("ok");
//        utf-8을 해 줬기 때문에 한글 출력 가능!!
         writer.println("안녕하세요");
    }

//    content 편의 메서드
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2 >> 기본으로 이렇게 보낼 때는 content-length가 꼭 있어야 함
        //Content-Length는 임의로 적은 값이 고정되어 나갈 수도 있지만, 생략하면 자동으로 계산돼서 나감!
        // 원래는 아래와 같이 했지만 이렇게 하지 말고
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 아래와 같이 해도 됨!
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600; :: 이 쿠키는 600초 동안 유효하다
        //쿠키도 response-header에 위와 같은 코드를 넣으면 됨
        //그다음 아래의 코드처럼 세팅하면 되는데! 이거 하나하나 하기 귀찮잖아용?
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        //그래서 쿠키라는 객체가 있다~~
        //setMaxAge에서 respons.addCookie라는 것을 지원해 줌
        //여기에 cookie를 넣어 주면 위의 코드들과 똑같은 효과를 보여줌
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");
        //위의 코드를 돌리면 ~~ Status Code 302 (...) 등의 해당 결과가 출력됨
        
        //근데 저거 넘 불편하잖아용?
        //그냥 sendRedirect 한 줄 넣어 주면 됨
        response.sendRedirect("/basic/hello-form.html");
    }

}
