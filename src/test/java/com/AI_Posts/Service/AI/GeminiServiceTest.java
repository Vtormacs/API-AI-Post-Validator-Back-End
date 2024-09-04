package com.AI_Posts.Service.AI;

import com.AI_Posts.Controller.AI.GeminiController;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeminiServiceTest {

    @Autowired
    GeminiController geminiController;

    @Autowired
    GeminiService geminiService;

    @Test
    void validadeAI() {

        String retorno = this.geminiService.validadeAI("hj o dia esta lindo");

        assertEquals("1", retorno);

        System.out.println(retorno);
    }

    @Test
    void validadeAI2() {

        String retorno = this.geminiService.validadeAI("o mundo e uma merda n presta para nada quero morrer");

        assertEquals("0", retorno);

        System.out.println(retorno);
    }

}