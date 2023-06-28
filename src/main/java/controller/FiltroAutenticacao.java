package controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;


public class FiltroAutenticacao implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;
        if (rq.getSession().isNew() || rq.getSession().getAttribute("usuario") == null) {
            rq.setAttribute("erro", "Sessão expirada, faça o login novamente.");
            rq.getSession().invalidate();
            rq.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(request, response);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
