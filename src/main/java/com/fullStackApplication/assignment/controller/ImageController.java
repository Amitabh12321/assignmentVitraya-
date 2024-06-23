package com.fullStackApplication.assignment.controller;

import com.fullStackApplication.assignment.entity.ExtractedText;
import com.fullStackApplication.assignment.entity.Image;
import com.fullStackApplication.assignment.entity.User;
import com.fullStackApplication.assignment.service.ImageService;
import com.fullStackApplication.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestBody ImageUploadRequest request) {
        User user = userService.getCurrentUser();
        Image image = imageService.saveImage(request.getImageBase64(), user);
        ExtractedText extractedText = imageService.extractTextFromImage(image);
        return ResponseEntity.ok(extractedText);
    }

    @GetMapping("/images")
    public ResponseEntity<?> getImages(Principal principal) {
        User user = userService.getCurrentUser();
        List<Image> images = imageService.getImagesByUser(user);
        return ResponseEntity.ok(images);
    }

    public static class ImageUploadRequest {
        private String imageBase64;

        // Getter
        public String getImageBase64() {
            return imageBase64;
        }

        // Setter
        public void setImageBase64(String imageBase64) {
            this.imageBase64 = imageBase64;
        }
    }

}

