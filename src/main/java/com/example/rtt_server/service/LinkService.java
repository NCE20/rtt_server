package com.example.rtt_server.service;

import com.example.rtt_server.dto.LinkDto;

public interface LinkService {
    LinkDto getLinkById(int linkId);
}
