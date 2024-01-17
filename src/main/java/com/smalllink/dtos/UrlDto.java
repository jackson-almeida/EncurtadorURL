package com.smalllink.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UrlDto(String urlHash, String longUrl, Date createdDate, Date expiresDate) { }
