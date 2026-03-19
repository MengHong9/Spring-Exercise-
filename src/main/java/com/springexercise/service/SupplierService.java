package com.springexercise.service;

import java.util.List;

import com.springexercise.dto.supplier.SupplierDto;
import com.springexercise.entity.Supplier;
import com.springexercise.exception.DuplicateException;
import com.springexercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springexercise.common.response.Response;
import com.springexercise.dto.supplier.SupplierResponseDto;
import com.springexercise.mapper.SupplierMapper;
import com.springexercise.repository.SupplierRepository;

@Service
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;


    public List<SupplierResponseDto> getSuppliers(){
        List<Supplier> suppliers= supplierRepository.findAll();

        return supplierMapper.toDtoList(suppliers);
    }


    public void addSupplier(SupplierDto dto){
        if (supplierRepository.existsByEmail(dto.getEmail())){
            throw new DuplicateException("email already exists");
        }
        if (supplierRepository.existsByName(dto.getName())){
            throw new DuplicateException("name already exists");
        }

        Supplier supplier = supplierMapper.toEntity(dto);
        supplierRepository.save(supplier);

    }

    public void updateSupplier(Long id , SupplierDto dto){
        Supplier entity = supplierRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("id not found"));

        supplierMapper.putSupplier(entity , dto);
        supplierRepository.save(entity);
    }

    public void deleteSupplier(Long id){
        Supplier entity = supplierRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("id not found"));

        supplierRepository.delete(entity);
    }


    public SupplierResponseDto getSupplierById(Long id){
        Supplier entity = supplierRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("id not found : "+id));
        return supplierMapper.toDto(entity);
    }
}
