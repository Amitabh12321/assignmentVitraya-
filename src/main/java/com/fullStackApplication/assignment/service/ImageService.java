package com.fullStackApplication.assignment.service;

import com.fullStackApplication.assignment.Repository.ExtractedTextRepository;
import com.fullStackApplication.assignment.Repository.ImageRepository;
import com.fullStackApplication.assignment.entity.ExtractedText;
import com.fullStackApplication.assignment.entity.Image;
import com.fullStackApplication.assignment.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ExtractedTextRepository extractedTextRepository;

    @Autowired
    private OCRService ocrService;

    public Image saveImage(String imageBase64, User user) {
        Image image = new Image();
        image.setImageBase64(imageBase64);
        image.setUser(user);
        return imageRepository.save(image);
    }

    public ExtractedText extractTextFromImage(Image image) {
        String extractedText = ocrService.extractText(image.getImageBase64());
        String boldWords = extractBoldWords(extractedText); // Custom method to find bold words

        ExtractedText text = new ExtractedText();
        text.setText(extractedText);
        text.setBoldWords(boldWords);
        text.setImage(image);
        return extractedTextRepository.save(text);
    }

    private String extractBoldWords(String text) {
        // Implementation to identify bold words
        return text; // Placeholder: Implement bold word extraction logic
    }

    public List<Image> getImagesByUser(User user) {
        return imageRepository.findByUser(user);
    }
}

