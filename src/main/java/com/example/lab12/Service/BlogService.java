package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.BlogRepository;
import com.example.lab12.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List<Blog> getAllBlogs() throws ApiException {
        return blogRepository.findAll();
    }

    public List<Blog> getMyBlog(Integer id) throws ApiException {
        User user = userRepository.findUserById(id);
        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Integer authID, Blog blog) throws ApiException {
        User user = userRepository.findUserById(authID);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer authID,int blogID, Blog blog) throws ApiException {
        User user = userRepository.findUserById(authID);
        Blog oldBlog = blogRepository.findBlogById(blogID);
        if (oldBlog == null || oldBlog.getUser() != user) {
            throw new ApiException("Blog not found");
        }
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer authID, int blogID) throws ApiException {
        User user = userRepository.findUserById(authID);
        Blog oldBlog = blogRepository.findBlogById(blogID);
        if (oldBlog == null || oldBlog.getUser() != user) {
            throw new ApiException("Blog not found");
        }
        blogRepository.delete(oldBlog);
    }

    public Blog getBlogById(Integer authID, int blogID) throws ApiException {
        User user = userRepository.findUserById(authID);
        Blog oldBlog = blogRepository.findBlogById(blogID);
        if (oldBlog == null || oldBlog.getUser() != user) {
            throw new ApiException("Blog not found");
        }
        return oldBlog;
    }

    public List<Blog> getBlogByTitle(Integer authID, String title) throws ApiException {
        List<Blog> blogs = blogRepository.findBlogsByTitleAndUserId(title, authID);
        if (blogs.isEmpty()) {
            throw new ApiException("Blog not found");
        }
        return blogs;
    }
}
