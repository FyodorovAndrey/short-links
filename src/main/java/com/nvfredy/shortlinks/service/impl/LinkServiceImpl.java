package com.nvfredy.shortlinks.service.impl;

import com.nvfredy.shortlinks.exception.LinkCountException;
import com.nvfredy.shortlinks.exception.LinkExpiredException;
import com.nvfredy.shortlinks.exception.LinkNotFoundException;
import com.nvfredy.shortlinks.model.Link;
import com.nvfredy.shortlinks.repository.LinkRepository;
import com.nvfredy.shortlinks.service.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    public String createShortLink(String longLink) {

        long count = linkRepository.count();

        if (count >= 100) {
            throw new LinkCountException(HttpStatus.TOO_MANY_REQUESTS, "База данных ссылок заполнена, попробуйте выполнить операцию позже.");
        }

        Link link;

        Link existingLink = linkRepository.findByLongLink(longLink);

        if (existingLink != null && !checkExpiredLink(existingLink)) {
            link = existingLink;
        } else {
            link = new Link();
            link.setLongLink(longLink);
            String shortLink = RandomStringUtils.randomAlphanumeric(20);
            link.setShortLink(shortLink);
            link.setCreatedAt(LocalDateTime.now());

            linkRepository.save(link);

            log.info("Создана короткая ссылка: {}, для ссылки: {}.", shortLink, longLink);
        }

        return link.getShortLink();
    }

    public String getLongLink(String shortLink) {

        Link link = linkRepository.findByShortLink(shortLink)
                .orElseThrow(() -> new LinkNotFoundException(HttpStatus.NOT_FOUND, "Полная ссылка не найдена!"));

        if (checkExpiredLink(link)) {
            throw new LinkExpiredException(HttpStatus.GONE, "Ссылка просрочена!");
        }

        return link.getLongLink();
    }

    private boolean checkExpiredLink(Link link) {
        long minutes = link.getCreatedAt().until(LocalDateTime.now(), ChronoUnit.MINUTES);

        if (minutes > 10) {
            linkRepository.delete(link);
            return true;
        } else {
            return false;
        }
    }
}
