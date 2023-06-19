package com.example.rtt_server.service;

import com.example.rtt_server.dto.ObjectDto;
import com.example.rtt_server.repository.ObjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ObjectServiceImpl implements ObjectService {
    private final ObjectRepository objectRepository;

    public ObjectServiceImpl(ObjectRepository objectRepository) {
        this.objectRepository = objectRepository;
    }

    @Override
    public ObjectDto getObjectById(int objectId) {
        Object object = objectRepository.findById(objectId);
        ObjectDto objectDto = convertToObjectDto(object);
        return objectDto;
    }

    private ObjectDto convertToObjectDto(Object object) {
        ObjectDto objectDto = new ObjectDto();
        return objectDto;
    }
}
