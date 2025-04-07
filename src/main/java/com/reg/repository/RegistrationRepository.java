package com.reg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reg.entity.Register;

@Repository
public interface RegistrationRepository extends JpaRepository<Register, Integer> {

	 Optional<Register> findByEmail(String email);

}
