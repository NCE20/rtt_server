package com.example.rtt_server.repository;

import com.example.rtt_server.domain.Link;

public interface LinkRepository {
    Link findById(int linkId);
}
