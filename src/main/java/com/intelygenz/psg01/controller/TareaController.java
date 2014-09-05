package com.intelygenz.psg01.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.intelygenz.psg01.ConnectionFactory;
import com.intelygenz.psg01.dao.TareaMapper;
import com.intelygenz.psg01.model.Ronda;
import com.intelygenz.psg01.model.Tarea;

public class TareaController {
	
	public List<Tarea> findUltimasTareas(){
		SqlSession session = ConnectionFactory.getSession().openSession();
		TareaMapper oTareaMapper = session.getMapper(TareaMapper.class);
		List<Tarea> al_Tarea = oTareaMapper.findUltimasTareas();
		session.close();
		return al_Tarea;
	}
	
	public int create (Tarea oTarea) throws Throwable{
		SqlSession session= ConnectionFactory.getSession().openSession();
		int last_insert_id = -1;
		try{
			TareaMapper oTareaMapper = session.getMapper(TareaMapper.class);
			oTareaMapper.insert(oTarea);
			last_insert_id = oTareaMapper.getLastId();
			session.commit();
		}
		catch(Exception ex){
			session.rollback();
			throw ex;
		}
		finally{
			session.close();
		}
		return last_insert_id;
	}

	
	public void saveImageAndPath(Tarea oTarea, InputStream file) throws Throwable{
		String sAbsolutePath = "E:/inetpub/01_produccion/psg_prosegur/psg01/www/img/";//"C://img/";//
		String sRelativePath = "./img/";
		
		Ronda oRonda = new RondaController().getById(oTarea.getFkIdRonda());
		
		//Carpeta para imagenes de ronda
    	sAbsolutePath += oRonda.getCodigo() + "/";
    	sRelativePath += oRonda.getCodigo() + "/";
    	builPath(sAbsolutePath);
    	
    	//Carpeta de imagenes para la tarea
    	sAbsolutePath += oTarea.getId() + "/";
    	sRelativePath += oTarea.getId() + "/";
    	builPath(sAbsolutePath);
    	
    	sAbsolutePath += oTarea.getNameImage();
    	sRelativePath += oTarea.getNameImage();
    	
    	oTarea.setPath(sRelativePath);
  		this.UpdatePath(oTarea);
    	this.writeToFile(file, sAbsolutePath);
	}
	
	private void UpdatePath(Tarea oTarea){
		SqlSession session= ConnectionFactory.getSession().openSession();
		try{
			TareaMapper oTareaMapper = session.getMapper(TareaMapper.class);
			System.out.println(oTarea.getPath());
			oTareaMapper.updatePath(oTarea);
			session.commit();
		}
		catch(Exception ex){
			System.out.println("ERROR" + ex.getMessage());
			session.rollback();
		}
		finally{
			session.close();
		}
	}
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws Throwable {
		OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
		int read = 0;
		byte[] bytes = new byte[1024];
		
		out = new FileOutputStream(new File(uploadedFileLocation));
		while ((read = uploadedInputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();
	}
	private boolean builPath(String sPath) throws Throwable{
		File f = new File(sPath);
		boolean result = true;
    	if (!f.exists()) {
    		result = f.mkdir();  
        }
    	return result;
	}
}
