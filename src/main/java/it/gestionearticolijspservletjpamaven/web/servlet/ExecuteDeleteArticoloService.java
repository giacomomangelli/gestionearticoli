package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.ArticoloService;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteArticoloService")
public class ExecuteDeleteArticoloService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String articoloIdParameter = request.getParameter("idArticolo");

		try {
			ArticoloService articoloService = MyServiceFactory.getArticoloServiceInstance();
			Articolo articolo = articoloService.caricaSingoloElemento(Long.parseLong(articoloIdParameter));
			articoloService.rimuovi(articolo);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
	}

}
