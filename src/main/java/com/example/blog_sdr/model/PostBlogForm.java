package com.example.blog_sdr.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class PostBlogForm {
    private Long id;
    private String stt;
    private List<MultipartFile> img;
    private LocalDateTime postingTime;

    private Category category;
    public PostBlogForm() {
    }

    public PostBlogForm(Long id, String stt, List<MultipartFile> img, LocalDateTime postingTime, Category category) {
        this.id = id;
        this.stt = stt;
        this.img = img;
        this.postingTime = postingTime;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public List<MultipartFile> getImg() {
        return img;
    }

    public void setImg(List<MultipartFile> img) {
        this.img = img;
    }

    public LocalDateTime getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(LocalDateTime postingTime) {
        this.postingTime = postingTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
