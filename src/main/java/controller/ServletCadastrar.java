package controller;

import java.io.IOException;

import dao.UsuarioDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioExisteException;

@WebServlet("/cadastrar")
public class ServletCadastrar extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nome", Util.decodificar(request.getParameter("nome")));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!request.getSession().isNew() && request.getSession().getAttribute("usuario") != null) {
//            response.sendRedirect("/WEB-INF/views/agenda.jsp");
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/agenda.jsp");
    		dispatcher.forward(request, response);
        } else {
//            response.sendRedirect("/WEB-INF/views/cadastrar.jsp");
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cadastrar.jsp");
    		dispatcher.forward(request, response);
        }
    }
    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		if (!request.getSession().isNew() && request.getSession().getAttribute("usuario") != null) {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/agenda.jsp");
//    		dispatcher.forward(request, response);
//        } else {
//        	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
//    		dispatcher.forward(request, response);
//        }
//	
//}
    
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!request.getSession().isNew() && request.getSession().getAttribute("usuario") != null) {
//            response.sendRedirect("/WEB-INF/views/agenda.jsp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/agenda.jsp");
    		dispatcher.forward(request, response);
        } else {
            try {
                Usuario usuario = new Usuario(Util.decodificar(request.getParameter("nome")), Util.decodificar(request.getParameter("senha")));
                new UsuarioDAOImpl().cadastrar(usuario);
                request.setAttribute("sucesso", "Usuário \"" + usuario.getNome() + "\" cadastrado com sucesso.");
            } catch (IllegalArgumentException | IndexOutOfBoundsException | UsuarioExisteException e) {
                erro(request, e.getMessage());
            }
//            request.getRequestDispatcher("/WEB-INF/views/cadastrar.jsp").forward(request, response);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cadastrar.jsp");
    		dispatcher.forward(request, response);
        }
    }
}
