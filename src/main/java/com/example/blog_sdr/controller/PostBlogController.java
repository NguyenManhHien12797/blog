package com.example.blog_sdr.controller;

import com.example.blog_sdr.model.Category;
import com.example.blog_sdr.model.PostBlog;
import com.example.blog_sdr.model.PostBlogForm;
import com.example.blog_sdr.service.category.ICategoryService;
import com.example.blog_sdr.service.post_blog.IPostBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class PostBlogController {

    @Value("C:\\Users\\Acer\\OneDrive\\Desktop\\CG\\Project\\Md4\\img\\")
    private String fileUpload;
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IPostBlogService postBlogService;

    @ModelAttribute("category")
    public Iterable<Category>categories(){
        return categoryService.findAll();
    }

//    @GetMapping("/home")
//    public ModelAndView home(@RequestParam("search") Optional<String> search, Pageable pageable){
//        Page<PostBlog> posts;
//        if(search.isPresent()){
//            posts = postBlogService.findAllBySttContaining(search.get(),pageable);
//        }else {
//            posts= postBlogService.findAll(pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("/post/home");
//        modelAndView.addObject("post",posts);
//        modelAndView.addObject("postBlog", new PostBlog());
//        return modelAndView;
//    }

    @GetMapping("/home")
    public ModelAndView home(@RequestParam("search") Optional<String> search, HttpServletRequest request, Pageable pageable){
        Page<PostBlog> posts;
        String s = request.getParameter("show");

        if(search.isPresent()){
            posts = postBlogService.findAllBySttContaining(search.get(),pageable);
        }else {
            posts= postBlogService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/post/home");
        modelAndView.addObject("post",posts);
        modelAndView.addObject("postBlog", new PostBlog());
        if("create".equals(s)){
            modelAndView.addObject("show", "create");
        }else{
            if("edit".equals(s)){
                long id= Long.parseLong(request.getParameter("id"));
                Optional<PostBlog> postBlog=postBlogService.findById(id);
                modelAndView.addObject("show", "edit");
                modelAndView.addObject("postBlog",postBlog.get());
            }
        }

        return modelAndView;
    }


//    @PostMapping("/home")
//    public ModelAndView saveCustomer(@ModelAttribute("postBlog") PostBlog postBlog) {
//
//        postBlogService.save(postBlog);
//        ModelAndView modelAndView = new ModelAndView("/post/home");
//        modelAndView.addObject("postBlog", new PostBlog());
//        modelAndView.addObject("message", "New post created successfully");
//        return modelAndView;
//    }

    @PostMapping("/home")
    public ModelAndView saveCustomer(@ModelAttribute PostBlogForm postBlogForm) {
        List<MultipartFile> multipartFiles = postBlogForm.getImg();
        for(int i=0; i< multipartFiles.size(); i++){
            String fileName = multipartFiles.get(i).getOriginalFilename();
            try{
                FileCopyUtils.copy(postBlogForm.getImg().get(i).getBytes(), new File(fileUpload + fileName));
            }catch (IOException e){
                e.printStackTrace();
            }
            LocalDateTime postingTime = LocalDateTime.now();
            PostBlog postBlog = new PostBlog(postBlogForm.getId(), postBlogForm.getStt(), fileName,postingTime,postBlogForm.getCategory());
            postBlogService.save(postBlog);
        }
        ModelAndView modelAndView = new ModelAndView("/post/home");
        modelAndView.addObject("postBlog", new PostBlog());
        modelAndView.addObject("message", "New post created successfully");
        return modelAndView;
    }


    @PostMapping("/edit-post_blog")
    public ModelAndView updateCustomer(@ModelAttribute("postBlog") PostBlog postBlog, HttpServletRequest request , Pageable pageable) {
        long id= Long.parseLong(request.getParameter("id"));
        postBlogService.update(postBlog.getStt(), postBlog.getImg(), postBlog.getPostingTime(),postBlog.getCategory().getId(),id);
        ModelAndView modelAndView = new ModelAndView("/post/home");
        Page<PostBlog> posts= postBlogService.findAll(pageable);
        modelAndView.addObject("post",posts);
        modelAndView.addObject("postBlog", postBlog);
        modelAndView.addObject("message", "Post updated successfully");
        return modelAndView;
    }

//    @GetMapping("/delete-post_blog/{id}")
//    public String deleteCustomer(@PathVariable Long id) {
//        postBlogService.remove(id);
//        return "redirect:home";
//    }

    @GetMapping("/delete-post_blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id, Pageable pageable) {
        postBlogService.remove(id);
        Page<PostBlog> posts= postBlogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/post/home");
        modelAndView.addObject("post",posts);
        modelAndView.addObject("postBlog", new PostBlog());
        return modelAndView;
    }

}
