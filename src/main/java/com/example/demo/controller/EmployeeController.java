package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // 14.1 GET - all records
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    // 14.2 GET - one record by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    // 14.3 POST - create a record
    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 14.4 PUT - update a record
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Long id, @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.update(id, request));
    }

    // 14.5 DELETE - delete a record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}