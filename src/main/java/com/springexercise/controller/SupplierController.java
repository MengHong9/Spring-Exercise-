package com.springexercise.controller;

import com.springexercise.common.response.Response;
import com.springexercise.dto.supplier.SupplierDto;
import com.springexercise.dto.supplier.SupplierResponseDto;
import com.springexercise.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<Response> getData(){
        List<SupplierResponseDto> dtos = supplierService.getSuppliers();

         return ResponseEntity.status(HttpStatus.OK).body(Response.success("200", "success", "successfully retrieved data" , dtos));
    }

    @PostMapping
    public ResponseEntity<Response> addSupplier(@Valid @RequestBody SupplierDto dto){
        supplierService.addSupplier(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(Response.success("201", "success", "successfully added data" ));
    }


    @PutMapping("{id}")
    public ResponseEntity<Response> updateSupplier(@PathVariable Long id , @Valid @RequestBody SupplierDto dto){
        supplierService.updateSupplier(id , dto);

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully updated data"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully deleted data"));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getSupplierById(@PathVariable Long id){
        SupplierResponseDto entity = supplierService.getSupplierById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200", "success", "successfully retrieved data" , entity));
    }
}
