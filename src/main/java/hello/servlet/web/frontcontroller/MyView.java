package hello.servlet.web.frontcontroller;

//컨트롤러에서 뷰로 이동해야 되기 때문에 모든 컨트롤러에 해당 코드에 대한 중복이 있었음 ㅠㅠ
//String viewPath = "/WEB-INF/views/save-result.jsp";
//RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//dispatcher.forward(request, response);

//이 부분을 분리하기 위해!! 해당 코드를 처리하는 '전담 뷰'라는 이름의 객체를 만든 것
//기존에는 컨트롤러가 jsp로 직접 forward!! 이제는 포워드 역할을 안 할 거다
//v2 구조 : 클라이언트 http 요청 -> 프론트 컨트롤러가 매핑 정보 찾아와서 해당 컨트롤러 호출
// -> 컨트롤러가 myview 반환 -> 프론트 컨트롤러가 myview에 render() 호출 -> myview가 jsp forward -> html 응답

// ** 컨트롤러가 MyView를 실행하는 게 아니라, 생성하고 반환만 하는 것!

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    //기존에 jsp로 이동한다 했던 것(실제 view가 렌더링 되도록 동작하는 것)을 렌더링 된다고 표현하겠다~~
    @SneakyThrows
    public void render(HttpServletRequest request, HttpServletResponse response) throws SerialException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    } //view 만드는 행위 자체를 렌더링한다고 표현하신대용~~

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //모델에 있는 데이터를 다 꺼내야 됨
        modelToRequestAttribute(model, request); //메서드 생성해 주기
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        //변수명 key value로 해서 map에 루프를 다 돌린다고 이해하면 됨
        //그다음 request.setAttribute() 통해 key value에 값을 다 담아놓음
        model.forEach((key, value)-> request.setAttribute(key, value));
    }
}
