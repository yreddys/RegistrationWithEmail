package com.reg.service;

import com.reg.model.LoginRequest;
import com.reg.model.LoginResponse;
import com.reg.model.RegisterRequest;
import com.reg.model.RegisterResponse;

public interface RegistrationService {

RegisterResponse createCustomer(RegisterRequest reg);

LoginResponse login(LoginRequest loginRequest);

RegisterResponse updateCustomer(RegisterRequest reg);

}
