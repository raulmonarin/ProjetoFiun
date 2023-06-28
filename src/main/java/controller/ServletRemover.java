package controller;

import java.io.IOException;

import dao.ContatoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Contato;
import model.ContatoNaoExisteException;
import model.Usuario;

@WebServlet("/remover")
public class ServletRemover extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (!request.getSession().isNew() && usuario != null) {
            try {
                Contato contato = new Contato(
                        Integer.parseInt(request.getParameter("id")),
                        Util.decodificar(request.getParameter("nome")),
                        Util.decodificar(request.getParameter("email")),
                        Util.decodificar(request.getParameter("telefone")));
                new ContatoDAOImpl().remover(usuario.getId(), contato);
                request.setAttribute("sucesso", "Contato removido com sucesso.");
            } catch (ContatoNaoExisteException e) {
                request.setAttribute("erro", "Erro: " + e.getMessage());
            }
        }

        request.getRequestDispatcher("/WEB-INF/views/agenda.jsp").forward(request, response);
    }
}
