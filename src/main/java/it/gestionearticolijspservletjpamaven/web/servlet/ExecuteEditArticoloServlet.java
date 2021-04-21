package it.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticolijspservletjpamaven.model.Articolo;
import it.gestionearticolijspservletjpamaven.service.ArticoloService;
import it.gestionearticolijspservletjpamaven.service.MyServiceFactory;
import it.gestionearticolijspservletjpamaven.utility.UtilityArticoloForm;

@WebServlet("/ExecuteEditArticoloServlet")
public class ExecuteEditArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String articoloIdParameter = request.getParameter("idArticoloEdit");
		String codiceArticoloEditParameter = request.getParameter("codiceEdit");
		String descrizioneArticoloEditParameter = request.getParameter("descrizioneEdit");
		String prezzoArticoloEditParameter = request.getParameter("prezzoEdit");
		String dataArticoloEditParameter = request.getParameter("dataArrivoEdit");
		
		Date dataArrivoParsed = UtilityArticoloForm.parseDateArrivoFromString(dataArticoloEditParameter);
		
		Articolo articoloEdit = new Articolo();
		
		articoloEdit.setId(Long.parseLong(articoloIdParameter));
		articoloEdit.setCodice(codiceArticoloEditParameter);
		articoloEdit.setDescrizione(descrizioneArticoloEditParameter);
		if(!prezzoArticoloEditParameter.isEmpty()) {
			articoloEdit.setPrezzo(Integer.parseInt(prezzoArticoloEditParameter));
		}
		articoloEdit.setDataArrivo(dataArrivoParsed);
		
		if (!UtilityArticoloForm.validateInput(codiceArticoloEditParameter, descrizioneArticoloEditParameter,
				prezzoArticoloEditParameter, dataArticoloEditParameter) || dataArrivoParsed == null) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.setAttribute("edit_articolo_attr", articoloEdit);
			request.getRequestDispatcher("/articolo/edit.jsp").forward(request, response);
			return;
		}
		
		try {
			ArticoloService articoloService = MyServiceFactory.getArticoloServiceInstance();
			Articolo articolo = articoloService.caricaSingoloElemento(Long.parseLong(articoloIdParameter));
			
			articolo.setId(articoloEdit.getId());
			articolo.setCodice(articoloEdit.getCodice());
			articolo.setDescrizione(articoloEdit.getDescrizione());
			articolo.setDataArrivo(articoloEdit.getDataArrivo());
			articolo.setPrezzo(articoloEdit.getPrezzo());
			
			articoloService.aggiorna(articolo);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
	}

}