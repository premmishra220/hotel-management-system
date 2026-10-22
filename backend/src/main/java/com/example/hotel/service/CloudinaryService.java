package com.example.hotel.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.hotel.exception.OurException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(
            @Value("${cloudinary.cloud-name}") String cloudName,
            @Value("${cloudinary.api-key}") String apiKey,
            @Value("${cloudinary.api-secret}") String apiSecret) {

        this.cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret,
                        "secure", true
                )
        );
    }

    public String saveImage(MultipartFile photo) {

        try {

            Map uploadResult = cloudinary.uploader().upload(
                    photo.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "image"
                    )
            );

            return (String) uploadResult.get("secure_url");

        } catch (IOException e) {

            throw new OurException(
                    "Unable to upload image to Cloudinary: " + e.getMessage()
            );
        }
    }
}