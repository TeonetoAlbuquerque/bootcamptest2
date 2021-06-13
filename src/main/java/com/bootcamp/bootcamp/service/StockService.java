package com.bootcamp.bootcamp.service;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.bootcamp.exception.BusinessException;
import com.bootcamp.bootcamp.exception.NotFoundException;
import com.bootcamp.bootcamp.mapper.StockMapper;
import com.bootcamp.bootcamp.model.Stocker;
import com.bootcamp.bootcamp.model.dto.StockDTO;
import com.bootcamp.bootcamp.repository.StockRepository;
import com.bootcamp.bootcamp.util.MessageUtils;

@Service
public class StockService {

	@Autowired
	private StockMapper mapper;
	
	@Autowired
	private StockRepository repository;
	
	@Transactional
	public StockDTO save(StockDTO dto) {
		Optional<Stocker> optionalStock = repository.findByNameAndDate(dto.getName(),dto.getDate());
		
		if(optionalStock.isPresent()){
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
		}
		Stocker stocker = mapper.toEntity(dto);
		repository.save(stocker);
		return mapper.toDto(stocker);
		
	}

	@Transactional
	public StockDTO update(@Valid StockDTO dto) {
		Optional<Stocker> optionalStock = repository.findByStockUpdate(dto.getName(),dto.getDate(), dto.getId());
		
			if(optionalStock.isPresent()){
				throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
			}
		
		Stocker stocker = mapper.toEntity(dto);
		repository.save(stocker);
		return mapper.toDto(stocker);
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findAll() {
		List<Stocker> list = repository.findAll();
		return mapper.toDto(list);
		
	}

	@Transactional(readOnly = true)
	public StockDTO  findById(Long id) {
		
		return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
	}

	@Transactional
	public StockDTO delete(Long id) {
		StockDTO dto = this.findById(id);
		repository.deleteById(id);;
		return dto;
				
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findByToday() {
			return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);
		
	}
	
}
