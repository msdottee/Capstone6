package co.grandcircus.Capstone6.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            String url = httpServletRequest.getRequestURI();

            if (!url.equals("/") && !url.equals("/login") && !url.equals("/login-submit") &&
                    !url.equals("/sign-up") && !url.equals("/signup-submit")) {

                HttpSession session = httpServletRequest.getSession(false);

                if (session == null || session.getAttribute("user") == null) {
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }
}
