package com.fullStackApplication.assignment.Repository;



import com.fullStackApplication.assignment.entity.ExtractedText;
import com.fullStackApplication.assignment.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtractedTextRepository extends JpaRepository<ExtractedText, Long> {
    List<ExtractedText> findByImage(Image image);
}
