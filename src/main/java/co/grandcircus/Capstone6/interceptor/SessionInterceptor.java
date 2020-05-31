package co.grandcircus.Capstone6.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        if (!url.equals("/") && !url.equals("/login") && !url.equals("/login-submit") &&
                !url.equals("/sign-up") && !url.equals("/signup-submit")) {

            HttpSession session = request.getSession(true);

            if (session.getAttribute("user") == null) {
                setLoginFlashMessage(request, response);

                response.sendRedirect(request.getContextPath() + "/");
                return false;
            }
        }

        return true;
    }

    private void setLoginFlashMessage(HttpServletRequest request, HttpServletResponse response) {
        FlashMap flashMap = new FlashMap();
        flashMap.put("message", "They really put the fire under me! I'll have to ask you to log in.");
        FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);

        if (flashMapManager != null) {
            flashMapManager.saveOutputFlashMap(flashMap, request, response);
        }
    }
}
