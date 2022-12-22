package com.sparta.myboard.repository;

import com.sparta.myboard.entity.Post;
import com.sparta.myboard.entity.User;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllPostByOrderByCreatedAtDesc();
    // List<Article> findAllByOrderByCreatedAtDesc();

    //ID, pw에 맞는 Post로 찾아옴
    Optional<Post> findById(Long id);
    Boolean existsByIdAndUser(Long id, User user);
    List<Post> findByCategory(String category);

    @Query(nativeQuery = true, value="select * from post where is_deleted=false order by like_count desc limit 3")
    List<Post> findBestPost();

}
