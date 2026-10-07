package com.example.demo.service;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeResponse> getAll() {
        return employeeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return toResponse(employee);
    }

    public EmployeeResponse create(EmployeeRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        return toResponse(savedEmployee);
    }

    public EmployeeResponse update(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(employee);
        return toResponse(updatedEmployee);
    }

    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(employee);
    }

    private EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getId(),
                employee.getDepartment().getName()
        );
    }
}