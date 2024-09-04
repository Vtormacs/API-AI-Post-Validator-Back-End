package com.AI_Posts.Controller;

import com.AI_Posts.Entity.PostEntity;
import com.AI_Posts.Repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostControllerTest {

    @Autowired
    PostController postController;

    @MockBean
    PostRepository postRepository;

    @Test
    void findAll() {

        ResponseEntity<List<PostEntity>> retorno = this.postController.findAll();

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }
}