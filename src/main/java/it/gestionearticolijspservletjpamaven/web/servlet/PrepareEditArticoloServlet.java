package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;

@WebServlet("/PrepareEditArticoloServlet")
public class PrepareEditArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idArticoloEditParameter = request.getParameter("idArticolo");
		
		try {
			request.setAttribute("edit_articolo_attr", MyServiceFactory.getArticoloServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idArticoloEditParameter)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/articolo/edit.jsp").forward(request, response);
	}	
}
