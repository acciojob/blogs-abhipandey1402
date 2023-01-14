package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image(description,dimensions);

        image.setBlog(blog);

        List<Image> imageList = blog.getImageList();
        if(imageList==null)
        {
            imageList=new ArrayList<>();
        }

        imageList.add(image);

        blog.setImageList(imageList);

        imageRepository.save(image);
        blogRepository.save(blog);

        return image;
    }

    public void deleteImage(Image image){
        imageRepository.delete(image);
    }

    public Image findById(int id) {
        return imageRepository.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        if(screenDimensions.split("X").length==2 || image!=null) {
            String imageLength = "";
            imageLength = image.getDimensions().split("X")[0];

            String screenLength = "";
            screenLength = screenDimensions.split("X")[0];

            int length = Integer.parseInt(screenLength) / Integer.parseInt(imageLength);

            String imagebreadth = "";
            imagebreadth = image.getDimensions().split("X")[1];

            String screenbreadth = "";
            screenbreadth = screenDimensions.split("X")[1];

            int breadth = Integer.parseInt(screenbreadth) / Integer.parseInt(imagebreadth);
            return length * breadth;

        }
        return 0;
    }
}