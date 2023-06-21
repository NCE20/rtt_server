package com.example.rtt_server.controller;

import com.example.rtt_server.dto.ObjectDto;
import com.example.rtt_server.service.ObjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectController {
    //노드와 관련된 요청을 처리하는 컨트롤러 클래스
    private final ObjectService objectService;

    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping("object/{objectId}")
    public ResponseEntity<ObjectDto> getObject(@PathVariable int objectId) {
        ObjectDto objectDto = objectService.getObjectById(objectId);
        return ResponseEntity.ok(objectDto);
    }
}

