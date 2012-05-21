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
        HttpSession session = ((HttpServletRequest)request).getSession();

        if (session.isNew()) {
            session.setAttribute("start", System.nanoTime());
        }
        else {
            long start = (Long) session.getAttribute("start");

            if (System.nanoTime() - start > (1000000000*5)) {

                session.invalidate();
                ((HttpServletResponse) response).sendRedirect("ec2-184-73-16-97.compute-1.amazonaws.com");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
