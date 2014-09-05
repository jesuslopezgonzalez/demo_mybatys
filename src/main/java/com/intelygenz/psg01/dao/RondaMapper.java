package com.intelygenz.psg01.dao;

import java.util.List;

import com.intelygenz.psg01.model.Ronda;

public interface RondaMapper extends GenericMapper<Ronda, Long> {
	Ronda getByCodigo(long codigo);
	List<Ronda> getByIdWithTareas(int id);
	int getLastId();
}
