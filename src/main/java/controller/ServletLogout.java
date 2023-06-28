package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class ServletLogout extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(request, response);
//        response.sendRedirect("/WEB-INF/views/index.jsp");
    }
}
