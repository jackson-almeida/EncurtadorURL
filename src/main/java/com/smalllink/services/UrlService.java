package com.smalllink.services;

import com.smalllink.dtos.UrlDto;
import com.smalllink.entity.UrlEntity;
import com.smalllink.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class UrlService {

    @Autowired
    UrlRepository urlRepository;

    public UrlEntity getUrl(String urlHash) {
        return urlRepository.findByUrlHash(urlHash);
    }

    public UrlEntity save(String url) throws NoSuchAlgorithmException {
        UrlEntity urlEntity = new UrlEntity("0000000", url, new Date(), new Date());
        urlEntity.setUrlHash(generateHash(url));

        return urlRepository.save(urlEntity);
    }

    private String generateHash(String url) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(url.getBytes(), 0, url.length());

            byte[] digest = md.digest();
            String hexa = new BigInteger(1, digest).toString(16);

            return hexa;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
