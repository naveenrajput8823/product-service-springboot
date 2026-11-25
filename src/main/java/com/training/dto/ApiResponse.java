package com.training.dto;

public record ApiResponse <T>(boolean status,T data,String message){
	
}
