package com.intelygenz.psg01.dao;

import java.util.List;

import com.intelygenz.psg01.model.Tarea;

public interface TareaMapper extends GenericMapper<Tarea, Long> {
	List<Tarea> findUltimasTareas();
	int getLastId();
	void updatePath(Tarea oTarea);
}
