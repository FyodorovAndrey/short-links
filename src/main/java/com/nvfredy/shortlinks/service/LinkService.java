package com.nvfredy.shortlinks.service;

import org.springframework.stereotype.Service;

@Service
public interface LinkService {

    String createShortLink(String longLink);

    String getLongLink(String shortLink);

}
