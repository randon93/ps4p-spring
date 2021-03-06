package com.rsotf.ps4p.model.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rsotf.ps4p.model.dao.IAccesorioDao;
import com.rsotf.ps4p.model.dao.IEdificioDao;
import com.rsotf.ps4p.model.dao.ISalaDao;
import com.rsotf.ps4p.model.entity.AccesorioEntity;
import com.rsotf.ps4p.model.entity.EdificioEntity;
import com.rsotf.ps4p.model.entity.SalaEntity;
import com.rsotf.ps4p.model.service.ISalaService;

@Service
public class SalaServiceImpl implements ISalaService{

	@Autowired
	private ISalaDao salaDao;
	
	@Autowired
	private IAccesorioDao accesorioDao;
	
	@Autowired
	private IEdificioDao edificioDao;
	
	@Override
	@Transactional(readOnly = true)
	public SalaEntity findById(Long id) {		
		return salaDao.findById(id).orElse(null);
	}

	@Override
	public void save(SalaEntity sala) {
		
		salaDao.save(sala);
	}

	@Override
	public void delete(Long id) {
		
		salaDao.deleteById(id);
	}

	@Override
	@Transactional()
	public List<AccesorioEntity> findByNombreLikeIgnoreCase(String name) {
		
		return accesorioDao.findByNombreLikeIgnoreCase(name);
	}

	@Override
	public List<EdificioEntity> findEdificioAll() {		
		return edificioDao.findAll();
	}

	@Override
	public List<SalaEntity> findAll() {
		
		return salaDao.findAll();
	}

	
}
