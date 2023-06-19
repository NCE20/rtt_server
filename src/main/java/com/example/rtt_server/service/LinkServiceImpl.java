package com.example.rtt_server.service;

import com.example.rtt_server.domain.Link;
import com.example.rtt_server.dto.LinkDto;
import com.example.rtt_server.repository.LinkRepository;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;

    public LinkServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public LinkDto getLinkById(int linkId) {
        Link link = linkRepository.findById(linkId);
        LinkDto linkDto = convertToLinkDto(link);
        return linkDto;
    }

    private LinkDto convertToLinkDto(Link link) {
        LinkDto linkDto = new LinkDto();
        return linkDto;
    }
}
