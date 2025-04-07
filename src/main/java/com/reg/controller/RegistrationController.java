package com.reg.controller;

import static com.reg.constants.CreateConstants.CREATE_CUST;
import static com.reg.constants.CreateConstants.LOGIN;
import static com.reg.constants.CreateConstants.REGISTER;
import static com.reg.constants.CreateConstants.UPDATE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reg.config.EmailSenderService;
import com.reg.model.LoginRequest;
import com.reg.model.LoginResponse;
import com.reg.model.RegisterRequest;
import com.reg.model.RegisterResponse;
import com.reg.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(REGISTER)
@Slf4j
public class RegistrationController {
	/**
	 * 
	 * @author yreddys
	 * 
	 */
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private EmailSenderService emailSenderService;

	@PostMapping(CREATE_CUST)
	public ResponseEntity<RegisterResponse> createCustomer(@RequestBody RegisterRequest reg) {
		log.info("Received request to create customer: {}", reg);

		RegisterResponse createCustomerRe = registrationService.createCustomer(reg);
		emailSenderService.sendRegistrationEmail(reg.getEmail(), reg.getName());
		log.info("Customer created successfully with ID: {}", createCustomerRe.getRegistrationId());

		return new ResponseEntity<>(createCustomerRe, HttpStatus.CREATED);
	}

	@PostMapping(LOGIN)
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse response = registrationService.login(loginRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping(UPDATE)
	public ResponseEntity<RegisterResponse> updateCustomer(@RequestBody RegisterRequest reg) {
		// log.info("Received request to create customer: {}", reg);

		RegisterResponse updateCustomerRe = registrationService.updateCustomer(reg);

		// log.info("Customer created successfully with ID: {}",
		// createCustomerRe.getRegistrationId());

		return new ResponseEntity<>(updateCustomerRe, HttpStatus.CREATED);
	}

}
