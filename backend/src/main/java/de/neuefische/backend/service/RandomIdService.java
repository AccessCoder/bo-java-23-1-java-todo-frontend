package de.neuefische.backend.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class RandomIdService {

    public String generateRandomId(){
        return UUID.randomUUID().toString();
    }
}
