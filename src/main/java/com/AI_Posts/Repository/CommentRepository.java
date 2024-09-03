package com.AI_Posts.Repository;

import com.AI_Posts.Entity.CommentEntity;
import com.AI_Posts.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
}
