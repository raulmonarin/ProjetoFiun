package controller;

import java.io.IOException;
import java.util.ArrayList;

import dao.ContatoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Contato;
import model.Usuario;

@WebServlet("/agenda")
public class ServletAgenda extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (!request.getSession().isNew() && usuario != null) {
            ArrayList<Contato> contatos = new ContatoDAOImpl().carregar(usuario);
            if (!contatos.isEmpty()) {
                request.setAttribute("contatos", contatos);
            } else {
                request.setAttribute("agendavazia", "A agenda ainda não possui contatos.");
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/agenda.jsp").forward(request, response);
    }
}
