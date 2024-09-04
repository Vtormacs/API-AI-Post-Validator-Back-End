package com.AI_Posts.Service;

import com.AI_Posts.Entity.TagEntity;
import com.AI_Posts.Repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TagServiceTest {

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
    void findAll() {
        List<TagEntity> list = this.tagRepository.findAll();
        assertEquals(2, list.size());
    }
}