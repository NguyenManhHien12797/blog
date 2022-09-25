package com.example.blog_sdr.repository;

import com.example.blog_sdr.model.PostBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IPostBlogRepository extends PagingAndSortingRepository<PostBlog,Long> {
    Page<PostBlog> findAllBySttContaining(String stt, Pageable pageable);

    @Procedure(value = "Update_Posts")
    void update(@Param("stt") String stt, @Param("WEB-INF/views/img") String img, @Param("postingTime") LocalDateTime postingTime, @Param("category_id") Long category_id, @Param("id") Long id);

}
