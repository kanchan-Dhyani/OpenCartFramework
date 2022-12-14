package com.qa.opencart.constants;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final int SMALL_DEFAULT_TIME_OUT = 5;
	public static final int MEDIUM_DEFAULT_TIME_OUT = 10;
	public static final int LONG_DEFAULT_TIME_OUT = 20;
	public static final Duration SMALL_DEFAULT_TIME_OUT_DURATION = Duration.ofSeconds(5);
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account/account";
	
	
	public static final String LOGOUT_PAGE_TITLE = "Account Logout";
	
	public static final List<String> EXPECTED_ACCOUNTS_HEADERS_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter") ;
	
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String LOGIN_FAILURE_MESSAGE = "Warning: No match for E-Mail Address and/or Password.";
	
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String LOGIN_NEGTIVE_SHEET_NAME = "login";
	
	
	public static final String CHECKOUT_FAIL_MESSAGE =  "Products marked with *** are not available in the desired quantity or not in stock!";
		    
	
	

}
