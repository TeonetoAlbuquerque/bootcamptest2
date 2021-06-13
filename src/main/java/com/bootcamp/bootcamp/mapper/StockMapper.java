package com.bootcamp.bootcamp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bootcamp.bootcamp.model.Stocker;
import com.bootcamp.bootcamp.model.dto.StockDTO;


@Component
public class StockMapper {

	public Stocker toEntity(StockDTO dto) {
		Stocker stock = new Stocker();
		stock.setId(dto.getId());
		stock.setName(dto.getName());
		stock.setPrice(dto.getPrice());
		stock.setVariation(dto.getVariation());
		stock.setDate(dto.getDate());
		
		return stock;
		
		
	}

	public StockDTO toDto(Stocker stocker) {
		StockDTO dto = new StockDTO();
		dto.setId(stocker.getId());
		dto.setName(stocker.getName());
		dto.setPrice(stocker.getPrice());
		dto.setVariation(stocker.getVariation());
		dto.setDate(stocker.getDate());
		
		return dto;
	}
	
	
	public List<StockDTO> toDto(List<Stocker> listStock){
		
		return listStock.stream().map(this::toDto).collect(Collectors.toList()); 

	} 
	
}
