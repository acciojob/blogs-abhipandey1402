package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageRepository imageRepository;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> blogList = blogRepository.findAll();

        return blogList;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        //updating the blog details

        //Updating the userInformation and changing its blogs

        User user = userRepository.findById(userId).get();

        Blog blog = new Blog(title,content);

        blog.setUser(user);

        List<Blog> blogList = user.getBlogList();

        if(blogList==null){
            blogList=new ArrayList<>();
        }

        blogList.add(blog);

        user.setBlogList(blogList);

        userRepository.save(user);
        blogRepository.save(blog);

    }

    public Blog findBlogById(int blogId){
        //find a blog
        return blogRepository.findById(blogId).get();
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it

        Blog blog = blogRepository.findById(blogId).get();

        Image image = imageService.createAndReturn(blog,description,dimensions);

        blogRepository.save(blog);

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository.findById(blogId).get();

        List<Image> imageList = blog.getImageList();

        if(imageList!=null){
            for(Image image : imageList){
                imageRepository.delete(image);
            }
        }
        blogRepository.deleteById(blogId);
    }
}