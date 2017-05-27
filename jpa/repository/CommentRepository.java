package cn.springlogic.comment.jpa.repository;

import cn.springlogic.comment.jpa.entity.Comment;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by admin on 2017/4/25.
 */
@Configuration
@RepositoryRestResource(path="comment")
public interface CommentRepository extends JpaRepository<Comment,Integer> {




}
