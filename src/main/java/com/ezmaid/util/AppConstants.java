package com.ezmaid.util;

public interface AppConstants {
	
	String ADMIN = "Admin";
    String SUPER_ADMIN = "SuperAdmin";
    String CUSTOMER = "Customer";
    String MAID = "Maid";
    
    String TOKEN_TYPE = "JWT";
    String TOKEN_ISSUER = "auth-api";
    String TOKEN_AUDIENCE = "ezmaid";
    
    String TOKEN_HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    
    String VALIDATION_FAILED_DETAIL = "Form fields are invalid, please correct and resubmit!";
}
