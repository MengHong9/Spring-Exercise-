package com.springexercise.mapper;


import com.springexercise.entity.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springexercise.dto.supplier.SupplierDto;
import com.springexercise.dto.supplier.SupplierResponseDto;


@Component
public class SupplierMapper {
    
    public Supplier toEntity(SupplierDto dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.getName());
        supplier.setAddress(dto.getAddress());
        supplier.setPhone(dto.getPhone());
        supplier.setEmail(dto.getEmail());
        supplier.setRating(dto.getRating());
        return supplier;
    }

    public SupplierResponseDto toDto(Supplier supplier) {
        SupplierResponseDto dto = new SupplierResponseDto();
        dto.setId(supplier.getSupplierId());
        dto.setName(supplier.getName());
        dto.setAddress(supplier.getAddress());
        dto.setPhone(supplier.getPhone());
        dto.setEmail(supplier.getEmail());
        dto.setRating(supplier.getRating());
        dto.setCreatedAt(supplier.getCreatedAt());
        dto.setUpdatedAt(supplier.getUpdatedAt());
        return dto;
    }

    public List<SupplierResponseDto> toDtoList(List<Supplier> suppliers) {
        if (suppliers == null || suppliers.isEmpty()) {
            return new ArrayList<>();
        }

        return suppliers.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void putSupplier(Supplier supplier , SupplierDto dto){
        if (supplier == null || dto == null){
            return;
        }

        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setAddress(dto.getAddress());
        supplier.setPhone(dto.getPhone());
        supplier.setRating(dto.getRating());
    }
}
