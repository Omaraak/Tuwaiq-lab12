package com.example.lab12.Controller;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/blog")
@RequiredArgsConstructor
public class BolgController {
    private final BlogService blogService;

    @GetMapping("/getMyBlogs")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal User user) throws ApiException, ApiException {
        return ResponseEntity.status(200).body(blogService.getMyBlog(user.getId()));
    }

    @PostMapping("/addBlog")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody Blog blog) throws ApiException {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("Blog added successfully to "+user.getUsername());
    }

    @PutMapping("/updateBlog/{blogID}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @PathVariable int blogID, @RequestBody Blog blog) throws ApiException {
        blogService.updateBlog(user.getId(),blogID, blog);
        return ResponseEntity.status(200).body("Blog updated successfully to "+user.getUsername());
    }

    @DeleteMapping("/deleteBlog/{blogID}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable int blogID) throws ApiException {
        blogService.deleteBlog(user.getId(),blogID);
        return ResponseEntity.status(200).body("Blog deleted successfully to "+user.getUsername());
    }

    @GetMapping("/getBlogById/{blogID}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable int blogID) throws ApiException {
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),blogID));
    }

    @GetMapping("/getBlogByTitle/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user, @PathVariable String title) throws ApiException {
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(),title));
    }
}
