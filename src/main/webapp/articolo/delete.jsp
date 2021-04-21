<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.gestionearticolijspservletjpamaven.model.Articolo"%>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Rimuovi elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">

</head>

<body>
	<jsp:include page="../navbar.jsp" />
		
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
	    
		    <% Articolo articoloInPagina = (Articolo)request.getAttribute("visualizza_articolo_attr"); %>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Codice</dt>
				  <dd class="col-sm-9"><%=articoloInPagina.getCodice() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9"><%=articoloInPagina.getDescrizione() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Prezzo:</dt>
				  <dd class="col-sm-9"><%=articoloInPagina.getPrezzo() %></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Data di Arrivo:</dt>
				  <dd class="col-sm-9"><%=articoloInPagina.getDataArrivo()!=null? new SimpleDateFormat("dd/MM/yyyy").format(articoloInPagina.getDataArrivo()):"N.D."  %></dd>
		    	</dl>
		    	
		    </div>
		    <div class='card-footer'>
		    <form method="post" action="ExecuteDeleteArticoloService" novalidate="novalidate">	
		        <a href="ListArticoliServlet" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>	
		        <input type = "hidden" name = "idArticolo" value = "<%= articoloInPagina.getId() %>">
			    <button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-danger float-right">Delete
				</button>
		    </form>    

		    </div>
		    
			
		</div>	
		
		
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>