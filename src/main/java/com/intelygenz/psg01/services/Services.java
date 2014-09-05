package com.intelygenz.psg01.services;

	import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
	






	import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
	






	import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
	






	import com.google.gson.Gson;
import com.intelygenz.psg01.Response;
import com.intelygenz.psg01.controller.RondaController;
import com.intelygenz.psg01.controller.TareaController;
import com.intelygenz.psg01.model.Ronda;
import com.intelygenz.psg01.model.Tarea;
	
	@Path("service")
	public class Services {
	 	
		@GET
		@Path("list2")
		@Produces("application/json")
	    public String getRondaWithTareas() {
			
			Gson oGson = new Gson();
	    	String sJson = "";
	    	RondaController oRondaController = new RondaController(); 
	    	List<Ronda> al_RondaWithTareas = new ArrayList<Ronda>();
	    	try{
	    		al_RondaWithTareas = oRondaController.getAllWithTareas();
	    		sJson = oGson.toJson(al_RondaWithTareas).toString();
	    	}
	    	catch(Exception e){
	    		Response oResponse = new Response();
	    		oResponse.setResultado("KO");
	    		oResponse.setMensaje(e.getMessage());
	    		sJson = oGson.toJson(oResponse).toString();
	    	}
	        return sJson;
	    }
		
		
		//Obtener las tareas de la últina ronda
	    @GET
	    @Path("list")
	    @Produces("application/json")
	    public String getUltimasTareas() {
	    	Gson oGson = new Gson();
	    	String sJson = "";
	    	TareaController oTareaController = new TareaController(); 
	    	List<Tarea> al_Tarea = new ArrayList<Tarea>();
	    	try{
	    		al_Tarea = oTareaController.findUltimasTareas();
	    		sJson = oGson.toJson(al_Tarea).toString();
	    	}
	    	catch(Exception e){
	    		Response oResponse = new Response();
	    		oResponse.setResultado("KO");
	    		oResponse.setMensaje(e.getMessage());
	    		sJson = oGson.toJson(oResponse).toString();
	    	}
	        return sJson;
	    }
	    
	    
	    @Resource
	    private ServletContext context;
	    
	    @POST
	    @Path("newtask")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    @Produces("application/json")
	    public String post(
	    					@FormDataParam("codigo") String sCodigo,
	    					
	    					@FormDataParam("activacion") String sActivacion,
	    					@FormDataParam("proceso") String sProceso,
	    					@FormDataParam("aviso") String sAviso,
	    					
	    					@FormDataParam("nombre") String sNombre,
	    					@FormDataParam("file") InputStream file,
	    					@FormDataParam("file") FormDataContentDisposition fileDetail
							) {
 	
	    	
	    	int fkIdRonda = -1;
	    	Response oResponse = new Response();

	    	try{
	    		long nCodigo = Long.parseLong(sCodigo);
	    		Timestamp stamp = new Timestamp(Long.parseLong(sActivacion));
	    		Date dActivacion = new Date(stamp.getTime());
	    		
	    		stamp = new Timestamp(Long.parseLong(sProceso));
	    		Date dProceso = new Date(stamp.getTime());
	    		
	    		stamp = new Timestamp(Long.parseLong(sAviso));
	    		Date dAviso = new Date(stamp.getTime());
		    	
		    	fkIdRonda = new RondaController().saveRonda(nCodigo,dActivacion,dProceso,dAviso);
		    	
		    	if(fkIdRonda == -1){
		    		//Error al almacenar o recuperar la ronda
		    		throw new Exception("Error al almacenar la ronda.");
		    	}
		    	else{
		    		TareaController oTareaController = new TareaController();
		    		Tarea oTarea = new Tarea();
		    		oTarea.setFkIdRonda(fkIdRonda);

		        	oTarea.setNombre(URLDecoder.decode(sNombre, "UTF-8"));

		        	oTarea.setFechaRegistro(new Date());
		        	
		        	//Hay fichero de imagen
		        	if(file!=null && fileDetail.getFileName().length() >0){
		        		oTarea.setNameImage(fileDetail.getFileName().replaceAll(" ", ""));
			        	oTarea.setEstado(true);
		        	}
		        	else{
		        		oTarea.setEstado(false);
		        	}
		        	
		        	int idTarea = oTareaController.create(oTarea);
		        	oTarea.setId(idTarea);
			    	if(oTarea.getEstado()){
			    		oTareaController.saveImageAndPath(oTarea,file);
			    	} 
		    	}
		    	oResponse.setResultado("OK");
		    	oResponse.setMensaje("Tarea registrada correctamente.");
	    	}
	    	catch(Throwable e){
	    		oResponse.setResultado("KO");
		    	oResponse.setMensaje(e.getMessage());
	    	} 
	    	
	    	return new Gson().toJson(oResponse).toString();
 	    }
	}