package com.example.demo.dto;

public record EmployeeResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Long departmentId,
        String departmentName,
        String phone
) {}