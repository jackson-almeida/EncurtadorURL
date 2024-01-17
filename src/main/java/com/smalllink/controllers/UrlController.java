package com.smalllink.controllers;

import com.smalllink.entity.UrlEntity;
import com.smalllink.services.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    @GetMapping("/url/{urlHash}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public ResponseEntity<Void> getUrl(@PathVariable String urlHash) {
        UrlEntity response = urlService.getUrl(urlHash);

        String originalUrl = "http://" + response.getLongUrl();

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", originalUrl)
                .build();
    }

    @PostMapping("/url/{url}")
    public ResponseEntity<UrlEntity> saveUrl(@PathVariable String url) throws NoSuchAlgorithmException {
        UrlEntity response = urlService.save(url);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
