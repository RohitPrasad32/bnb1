package com.airbnb.bnb1.controller;

import com.airbnb.bnb1.entity.AppUser;
import com.airbnb.bnb1.entity.Image;
import com.airbnb.bnb1.entity.Property;
import com.airbnb.bnb1.repository.ImageRepository;
import com.airbnb.bnb1.repository.PropertyRepository;
import com.airbnb.bnb1.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/image")
public class ImageController {
    private ImageRepository imageRepository;
    private PropertyRepository propertyRepository;
    private BucketService bucketService;

    public ImageController(ImageRepository imageRepository, PropertyRepository propertyRepository, BucketService bucketService) {
        this.imageRepository = imageRepository;
        this.propertyRepository = propertyRepository;
        this.bucketService = bucketService;
    }

    @PostMapping(path = "/upload/file/{bucketName}/property/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@PathVariable MultipartFile file,
                                        @PathVariable String bucketName,
                                        @PathVariable long propertyId,
                                        @AuthenticationPrincipal AppUser user
                   ) {
      String imageUrl =  bucketService.uploadFile(file, bucketName);
      Property property = propertyRepository.findById(propertyId).get();

      Image img = new Image();
      img.setUrl(imageUrl);
      img.setProperty(property);

      Image saveImage = imageRepository.save(img);
      return  new ResponseEntity<>(saveImage, HttpStatus.OK);
    }
}
