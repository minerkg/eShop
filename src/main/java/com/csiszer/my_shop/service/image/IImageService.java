package com.csiszer.my_shop.service.image;

import com.csiszer.my_shop.dto.ImageDto;
import com.csiszer.my_shop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> file, Long productId);
    void updateImage(MultipartFile file, Long productId);



}
