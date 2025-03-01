package com.example.krompirica.Worker.services;

import com.example.krompirica.Worker.WorkerEntity;
import com.example.krompirica.Worker.WorkerService;

import com.example.krompirica.model.WorkerPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


@Service
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {
    private final WorkerService service;
    @Override
    public UserDetails loadUserByUsername(String username){
        return new WorkerPrincipal(service.findByUsername(username));
    }
    private Boolean verifyPassword(String password,byte[] storedHash, byte[] storedSalt) throws NoSuchAlgorithmException {
        if(password.isBlank() || password.isEmpty())throw new IllegalArgumentException(
                "Password cannot be empty."
        );

        if(storedHash.length !=64)throw new IllegalArgumentException(
                "Invalid length of password Hash."
        );

        if(storedSalt.length !=128)throw new IllegalArgumentException(
                "Invalid length of password Salt."
        );

        var md= MessageDigest.getInstance("SHA-512");
        md.update(storedSalt);
        var computedHash=md.digest(password.getBytes(StandardCharsets.UTF_8));
        System.out.println("🔹 Stored Hash: " + Arrays.toString(storedHash));
        System.out.println("🔹 Computed Hash: " + Arrays.toString(computedHash));
        System.out.println("🔹 Stored Salt: " + Arrays.toString(storedSalt));
        return MessageDigest.isEqual(computedHash,storedHash);
    }

    public WorkerEntity authenticate(String username,String password) throws NoSuchAlgorithmException {

        if(username.isEmpty() || password.isEmpty())throw new BadCredentialsException("Unauthorized");
        var worker=service.findByUsername(username);

        if(worker==null)throw new BadCredentialsException("Unauthorized");
        var verified=verifyPassword(password,worker.getStoredHash(), worker.getStoredSalt());
        System.out.println(verified);
        if(!verified)throw new BadCredentialsException("Unauthorized");

        return worker;
    }
}
