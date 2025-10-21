package com.example.clinic.dto;



public record ApiResponse<T>(boolean success, String message, T data) {}

