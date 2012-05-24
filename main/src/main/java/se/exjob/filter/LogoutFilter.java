package se.exjob.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (session.isNew()) {
            session.setAttribute("start", System.currentTimeMillis());
        }
        else {
            long start = (Long) session.getAttribute("start");
            int secondsBeforeLogout = 60;
            int millisecondsOnOneSecond = 1000;
            if (System.currentTimeMillis() - start > (secondsBeforeLogout * millisecondsOnOneSecond)) {

                session.invalidate();
                resp.sendRedirect("http://ec2-184-73-16-97.compute-1.amazonaws.com");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
