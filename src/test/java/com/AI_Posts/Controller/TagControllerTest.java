package com.AI_Posts.Controller;

import com.AI_Posts.Entity.TagEntity;
import com.AI_Posts.Repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TagControllerTest {

    @Autowired
    TagController tagController;

    @MockBean
    TagRepository tagRepository;

    UUID uuid;

    @BeforeEach
    void setup() {

        //FindAll
        List<TagEntity> lista = new ArrayList<>();

        TagEntity tag1 = new TagEntity();
        tag1.setUuid(UUID.randomUUID());
        tag1.setNome("Game");

        TagEntity tag2 = new TagEntity();
        tag2.setUuid(UUID.randomUUID());
        tag2.setNome("Show");

        lista.addAll(List.of(tag1, tag2));

        when(tagRepository.findAll()).thenReturn(lista);

        //FindById
        TagEntity tag = new TagEntity(UUID.randomUUID(), "Game", new ArrayList<>());

        this.uuid = tag.getUuid();

        when(tagRepository.findById(uuid)).thenReturn(Optional.of(tag));
    }

    @Test
    void findAllTestStatusENumeroDeItensNaLista() {

        ResponseEntity<List<TagEntity>> retorno = this.tagController.findAll();

        assertEquals(HttpStatus.OK, retorno.getStatusCode());

        assertEquals(2, retorno.getBody().size());
    }


    @Test
    void findByIdTestStatus() {

        ResponseEntity<TagEntity> retorno = this.tagController.findById(uuid);

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }

    @Test
    void findByIdTestId() {

        ResponseEntity<TagEntity> retorno = this.tagController.findById(uuid);

        assertEquals(uuid, retorno.getBody().getUuid());
    }

    @Test
    void findByIdTestStatusIdErrado() {

        UUID idInvalido = UUID.randomUUID();

        when(tagRepository.findById(idInvalido)).thenReturn(Optional.empty());

        ResponseEntity<TagEntity> retorno = this.tagController.findById(idInvalido);

        assertEquals(HttpStatus.BAD_REQUEST, retorno.getStatusCode());
    }
}