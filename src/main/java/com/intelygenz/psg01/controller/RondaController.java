package com.intelygenz.psg01.controller;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.intelygenz.psg01.ConnectionFactory;
import com.intelygenz.psg01.dao.RondaMapper;
import com.intelygenz.psg01.model.Ronda;

public class RondaController {
	
	public List<Ronda> getAllWithTareas(){
		SqlSession session = ConnectionFactory.getSession().openSession();
		RondaMapper oRondaMapper = session.getMapper(RondaMapper.class);
		int id = oRondaMapper.getLastId();
		List<Ronda> al_Ronda = oRondaMapper.getByIdWithTareas(id);
		session.close();
		return al_Ronda;
	}
	
	public Ronda getById(int id){
		SqlSession session = ConnectionFactory.getSession().openSession();
		RondaMapper oRondaMapper = session.getMapper(RondaMapper.class);
		Ronda oRonda= oRondaMapper.findById(id);
		session.close();
		return oRonda;
	}
	
	public Ronda getLast(){
		SqlSession session = ConnectionFactory.getSession().openSession();
		RondaMapper oRondaMapper = session.getMapper(RondaMapper.class);
		int id = oRondaMapper.getLastId();
		Ronda oRonda= oRondaMapper.findById(id);
		session.close();
		return oRonda;
	}
	
	
	
	public Ronda getByCodigo(long codigo){
		SqlSession session = ConnectionFactory.getSession().openSession();
		RondaMapper oRondaMapper = session.getMapper(RondaMapper.class);
		Ronda oRonda= oRondaMapper.getByCodigo(codigo);
		session.close();
		return oRonda;
	}
	
	public int saveRonda(long nCodigo,Date nActivacion,Date nProceso,Date nAviso){
		Ronda oRonda = new Ronda();
		int fkIdRonda = -1;
		if(!this.existeRonda(nCodigo)){
    		oRonda.setCodigo(nCodigo);
    		oRonda.setActivacion(nActivacion);
    		oRonda.setProceso(nProceso);
    		oRonda.setAviso(nAviso);
    		fkIdRonda = this.create(oRonda);
    	}
    	else{
    		oRonda = this.getByCodigo(nCodigo);
    		fkIdRonda = oRonda.getId();
    	}
		return fkIdRonda;
	}
	
	private boolean existeRonda(long codigo){
		Ronda oRonda = this.getByCodigo(codigo);  
		return oRonda != null;
	}
	
	private int create(Ronda oRonda){
		
		SqlSession session= ConnectionFactory.getSession().openSession();
		int las_insert_id = -1;
		try{
			RondaMapper oRondaMapper = session.getMapper(RondaMapper.class);
			oRondaMapper.insert(oRonda);
			session.commit();
			las_insert_id = oRondaMapper.getLastId(); 
		}
		catch(Exception ex){
			session.rollback();
		}
		finally{
			session.close();
		}
		return las_insert_id;
	}
}
