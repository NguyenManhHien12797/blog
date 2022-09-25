package com.example.blog_sdr.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class PostBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stt;
    private String img;
    private LocalDateTime postingTime;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public PostBlog() {
    }

    public PostBlog(Long id, String stt, String img, LocalDateTime postingTime, Category category) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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
