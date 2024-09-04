package com.AI_Posts.Service;

import com.AI_Posts.Entity.PostEntity;
import com.AI_Posts.Entity.TagEntity;
import com.AI_Posts.Entity.UserEntity;
import com.AI_Posts.Repository.PostRepository;
import com.AI_Posts.Repository.TagRepository;
import com.AI_Posts.Repository.UserRepository;
import com.AI_Posts.Service.AI.GeminiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostServiceTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TagRepository tagRepository;

    @Autowired
    private GeminiService geminiService;

    @Autowired
    private PostService postService;

    @Test
    void saveTest() {
        // Cria um objeto PostEntity para ser salvo
        PostEntity post = new PostEntity();
        post.setUuid(UUID.randomUUID());
        post.setConteudo("hoje esta um belo dia");

        // Cria um objeto UserEntity e TagEntity para simulação
        UserEntity user = new UserEntity();
        user.setUuid(UUID.randomUUID());

        TagEntity tag = new TagEntity();
        tag.setUuid(UUID.randomUUID());

        post.setUser(user);
        post.setTags(List.of(tag));

        post.setValido(geminiService.validadeAI(post.getConteudo()));

        // Simula o comportamento do UserRepository
        when(userRepository.findById(user.getUuid())).thenReturn(Optional.of(user));

        // Simula o comportamento do TagRepository
        when(tagRepository.findAllById(List.of(tag.getUuid()))).thenReturn(List.of(tag));

        // Simula o comportamento do PostRepository
        when(postRepository.save(post)).thenReturn(post);

        // Chama o método save do serviço
        PostEntity retorno = postService.save(post);

        // Verifica se o post foi salvo corretamente
        assertEquals(post.getUuid(), retorno.getUuid());
        assertEquals(post.getConteudo(), retorno.getConteudo());
        assertEquals(user.getUuid(), retorno.getUser().getUuid());
        assertTrue(retorno.isValido());
    }
}
