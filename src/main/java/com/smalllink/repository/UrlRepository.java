package com.smalllink.repository;

import com.smalllink.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, String> {
    UrlEntity findByUrlHash(String urlHash);
}
