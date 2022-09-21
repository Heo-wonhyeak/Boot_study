package home.servlet.web.frontcontroller.v5.adapter;

import home.servlet.web.frontcontroller.ModelView;
import home.servlet.web.frontcontroller.v3.ControllerV3;
import home.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // ControllerV3를 상속받은것이라면 참 그 외는 거짓
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handel(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // supports 메서드로 걸러진 Object 이기때문에 캐스팅 해서 써도 된다!
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
