package filters;

import utils.CookiesUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF-8");

        Cookie[] cookies = req.getCookies();
        if (CookiesUtils.isContainCookie(cookies, "countPage")) {
            int count = Integer.parseInt(CookiesUtils.getCookieByName("countPage", cookies).getValue());
            count++;
            Cookie cookie = new Cookie("countPage", "" + count);
            resp.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("countPage", "1");
            resp.addCookie(cookie);
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
