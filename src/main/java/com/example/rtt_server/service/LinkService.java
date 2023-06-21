package com.example.rtt_server.service;

import com.example.rtt_server.dto.LinkDto;
import com.example.rtt_server.domain.Link;
import com.example.rtt_server.dto.ObjectDto;
import com.example.rtt_server.repository.LinkRepository;

public interface LinkService {
    LinkDto getLinkById(int LinkId);
}
