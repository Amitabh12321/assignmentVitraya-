package com.fullStackApplication.assignment.Repository;


import com.fullStackApplication.assignment.entity.Image;
import com.fullStackApplication.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByUser(User user);
}
