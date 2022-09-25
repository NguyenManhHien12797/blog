package com.example.blog_sdr.service.post_blog;


import com.example.blog_sdr.model.PostBlog;
import com.example.blog_sdr.repository.IPostBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostBlogService implements IPostBlogService {

    @Autowired
    private IPostBlogRepository postRepository;

    @Override
    public Iterable<PostBlog> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<PostBlog> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void save(PostBlog post) {
//        Long id= post.getId();
//        String stt= post.getStt();
//        String img= post.getImg();
//        LocalDateTime postingTime =LocalDateTime.now();
//        Category category = post.getCategory();
//        PostBlog postBlog= new PostBlog(id, stt,img,postingTime,category);
        postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Page<PostBlog> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<PostBlog> findAllBySttContaining(String stt, Pageable pageable) {
        return postRepository.findAllBySttContaining(stt,pageable);
    }

    @Override
    public void update(String stt, String img, LocalDateTime postingTime, Long category_id, Long id) {
     LocalDateTime time = LocalDateTime.now();
      postRepository.update(stt,img,time,category_id,id);
    }
}
