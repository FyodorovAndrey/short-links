package com.nvfredy.shortlinks.controller;

import com.nvfredy.shortlinks.service.impl.LinkServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class LinkController {

    private final LinkServiceImpl linkService;

    @ApiOperation(value = "Endpoint для формирования короткой ссылки.")
    @GetMapping("/create-link/**")
    public ResponseEntity<String> createShortLink(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String longLink = url.split("/create-link/")[1];

        return ResponseEntity.ok(linkService.createShortLink(longLink));
    }

    @ApiOperation(value = "Endpoint для получения полной ссылки на основе краткой.")
    @GetMapping("/get-long-link/{shortLink}")
    public ResponseEntity<String> getLongLink(@PathVariable String shortLink) {

        return ResponseEntity.ok(linkService.getLongLink(shortLink));
    }

    @ApiOperation(value = "Endpoint, через который будет осуществляться переход по короткой ссылке в браузере.")
    @GetMapping("/{shortLink}")
    public RedirectView redirectToRealLink(@PathVariable String shortLink) {
        String realLink = linkService.getLongLink(shortLink);

        return new RedirectView(realLink);
    }
}
