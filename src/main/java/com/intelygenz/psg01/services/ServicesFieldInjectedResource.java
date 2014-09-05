package com.intelygenz.psg01.services;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/")
public class ServicesFieldInjectedResource {
	@FormDataParam("codigo") private String codigo;
	@FormDataParam("codigo") private String nombre;
    @FormDataParam("file") private InputStream file;
    @FormDataParam("file") FormDataContentDisposition fileDetail;

    @POST
    @Path("newtask")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String post() {
    	System.out.println(codigo +  "," + nombre);
        return codigo +  "," + nombre + "," + file + "," + fileDetail;
    }
}