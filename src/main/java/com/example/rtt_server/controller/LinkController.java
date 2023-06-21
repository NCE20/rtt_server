package com.example.rtt_server.controller;

import com.example.rtt_server.dto.LinkDto;
import com.example.rtt_server.dto.ObjectDto;
import com.example.rtt_server.service.LinkService;
import com.example.rtt_server.service.ObjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {
    private final LinkService LinkService;

    public LinkController(LinkService LinkService) {
        this.LinkService = LinkService;
    }

    @GetMapping("/links/{LinkId}")
    public ResponseEntity<LinkDto> getLink(@PathVariable int LinkId) {
        LinkDto LinkDto = LinkService.getLinkById(LinkId);
        return ResponseEntity.ok(LinkDto);
    }
}
