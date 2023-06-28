package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class ServletIndex extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		if (!request.getSession().isNew() && request.getSession().getAttribute("usuario") != null) {
    			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/agenda.jsp");
        		dispatcher.forward(request, response);
            } else {
            	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
        		dispatcher.forward(request, response);
            }
    	
    }
}
