package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;
import it.gestionearticolijspservletjpamaven.utility.UtilityArticoloForm;

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String dataArrivoStringParam = request.getParameter("dataArrivo");

		Date dataArrivoParsed = UtilityArticoloForm.parseDateArrivoFromString(dataArrivoStringParam);

		if (!UtilityArticoloForm.validateInput(codiceInputParam, descrizioneInputParam, prezzoInputStringParam,
				dataArrivoStringParam) || dataArrivoParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/articolo/insert.jsp").forward(request, response);
			return;
		}

		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam,
				Integer.parseInt(prezzoInputStringParam), dataArrivoParsed);

		try {
			MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloInstance);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/articolo/insert.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);
	}
}
