package controller;

import java.io.IOException;

import dao.ContatoDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Contato;
import model.ContatoExisteException;
import model.ContatoNaoExisteException;
import model.Usuario;

@WebServlet("/alterar")
public class ServletAlterar extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void erro(HttpServletRequest request, String erro) {
        request.setAttribute("erro", "Erro: " + erro);
        request.setAttribute("nomeatual", Util.decodificar(request.getParameter("nomeatual")));
        request.setAttribute("emailatual", Util.decodificar(request.getParameter("emailatual")));
        request.setAttribute("telefoneatual", Util.decodificar(request.getParameter("telefoneatual")));
        request.setAttribute("novonome", Util.decodificar(request.getParameter("novonome")));
        request.setAttribute("novoemail", Util.decodificar(request.getParameter("novoemail")));
        request.setAttribute("novotelefone", Util.decodificar(request.getParameter("novotelefone")));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("id", Util.decodificar(request.getParameter("id")));
        request.setAttribute("nomeatual", Util.decodificar(request.getParameter("nome")));
        request.setAttribute("emailatual", Util.decodificar(request.getParameter("email")));
        request.setAttribute("telefoneatual", Util.decodificar(request.getParameter("telefone")));

        request.getRequestDispatcher("/WEB-INF/views/alterar.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = null;

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");

        if (!request.getSession().isNew() && usuario != null) {
            try {
                Contato contato = new Contato(
                        Integer.parseInt(request.getParameter("id")),
                        Util.decodificar(request.getParameter("novonome")),
                        Util.decodificar(request.getParameter("novoemail")),
                        Util.decodificar(request.getParameter("novotelefone")));

                new ContatoDAOImpl().alterar(usuario.getId(), contato);

                request.setAttribute("sucesso", "Contato alterado com sucesso.");
                rd = request.getRequestDispatcher("/WEB-INF/view/agenda.jsp");
            } catch (IndexOutOfBoundsException | IllegalArgumentException | ContatoExisteException | ContatoNaoExisteException e) {
                erro(request, e.getMessage());
                rd = request.getRequestDispatcher("/WEB-INF/views/alterar.jsp");
            }
        }

        rd.forward(request, response);
    }
}
