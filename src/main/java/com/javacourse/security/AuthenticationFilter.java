package com.javacourse.security;

import com.javacourse.shared.WebPage;
import com.javacourse.user.role.Role;
import com.javacourse.utils.WebPageDispatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static com.javacourse.shared.WebPage.*;

@WebFilter(filterName = "AuthenticationFilter",
        urlPatterns = { "/Home/Stats/*",
                        "/WEB_INF/*",
                        "/Test/Delete/*",
                        "/Test/Details/*",
                        "/Test/Create/*",
                        "/Test/HeaderEdit/*",
                        "/Test/DescriptionEdit/*",
                        "/Test/PrivateStatus/*",
                        "/Test/PublicStatus/*",
                        "/Test/CreateTask/*",
                        "/Test/TaskDetails/*",
                        "/Test/AddAnswer/*",
                        "/Test/EditAnswer/*",
                        "/Test/DeleteAnswer/*",
                        "/Home/StatsDetails/*"})
public class AuthenticationFilter implements Filter {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String ROLE_PARAM = "role";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        WebPage page;
        if(isLoggedIn(session)){
            Role role = (Role) session.getAttribute(ROLE_PARAM);
            if(role != Role.ADMIN){
                page  = new WebPage(WebPageBase.ERROR_ACTION)
                        .setDispatchType(DispatchType.REDIRECT);
            } else{
                chain.doFilter(req, resp);
                return;
            }
        }else {
            page = new WebPage(WebPageBase.LOGIN_ACTION)
                    .setDispatchType(DispatchType.REDIRECT);
        }
        new WebPageDispatcher(request, response, page).dispatch();

    }

    public void init(FilterConfig config) throws ServletException {

    }

    boolean isLoggedIn(HttpSession session){
        return (session!=null
                && session.getAttribute(LOGIN_PARAM)!=null
                && session.getAttribute(PASSWORD_PARAM)!=null);
    }

}
