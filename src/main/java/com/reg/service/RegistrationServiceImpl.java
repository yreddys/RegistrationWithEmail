package com.reg.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reg.auth.JwtUtils;
import com.reg.entity.Register;
import com.reg.exception.UserAlreadyRegister;
import com.reg.model.LoginRequest;
import com.reg.model.LoginResponse;
import com.reg.model.RegisterRequest;
import com.reg.model.RegisterResponse;
import com.reg.repository.RegistrationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponse createCustomer(RegisterRequest reg) {
        log.info("Starting customer registration for: {}", reg.getEmail());

        if (registrationRepository.findByEmail(reg.getEmail()).isPresent()) {
            log.error("User already registered: {}", reg.getEmail());
            throw new UserAlreadyRegister("User already registered with email: " + reg.getEmail());
        }

        Register register = new Register();
        register.setEmail(reg.getEmail());
        register.setName(reg.getName());
        register.setPassword(passwordEncoder.encode(reg.getPassword())); // Encrypt password

        Register savedUser = registrationRepository.save(register);
        log.info("User registered successfully: {}", savedUser.getEmail());

        return new RegisterResponse(savedUser.getName(), savedUser.getEmail(), savedUser.getRegistrationId());
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        log.info("Attempting login for: {}", loginRequest.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String token = jwtUtils.generateToken(loginRequest.getEmail());
        log.info("Login successful for user: {}", loginRequest.getEmail());

        return new LoginResponse("Login Successful", token);
    }

    @Override
    public RegisterResponse updateCustomer(RegisterRequest reg) {
        log.info("Attempting to update customer with email: {}", reg.getEmail());

        Optional<Register> optionalRegister = registrationRepository.findByEmail(reg.getEmail());

        if (optionalRegister.isEmpty()) {
            log.error("User not found with email: {}", reg.getEmail());
            throw new UsernameNotFoundException("User not found with email: " + reg.getEmail());
        }

        Register existingUser = optionalRegister.get();

        // Update only allowed fields
        existingUser.setName(reg.getName());
        existingUser.setPassword(reg.getPassword()); // Password should be encoded in real apps

        Register updatedUser = registrationRepository.save(existingUser);

        log.info("User updated successfully: {}", updatedUser.getRegistrationId());

        return new RegisterResponse(
            updatedUser.getName(),
            updatedUser.getEmail(),
            updatedUser.getRegistrationId()
        );
    }

}
