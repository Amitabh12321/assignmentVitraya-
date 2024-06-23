package com.fullStackApplication.assignment.service;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class OCRService {

    public String extractText(String base64Image) {
        ITesseract instance = new Tesseract();
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        BufferedImage img;
        try {
            img = ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image", e);
        }

        try {
            return instance.doOCR(img);
        } catch (TesseractException e) {
            throw new RuntimeException("Failed to extract text from image", e);
        }
    }
}

