package de.neuefische.backend.service;

import java.util.UUID;

public class RandomIdService {

    public String generateRandomId(){
        return UUID.randomUUID().toString();
    }
}
