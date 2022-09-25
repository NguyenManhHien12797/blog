package com.example.blog_sdr.service.post_blog;

import com.example.blog_sdr.model.PostBlog;
import com.example.blog_sdr.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IPostBlogService extends IGeneralService<PostBlog> {
    Page<PostBlog> findAll(Pageable pageable);
    Page<PostBlog> findAllBySttContaining(String stt, Pageable pageable);

    void update(String stt,String img,LocalDateTime postingTime,Long category_id,Long id);

}
