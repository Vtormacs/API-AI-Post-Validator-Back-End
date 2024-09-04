package com.AI_Posts.Service.AI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeminiServiceTest {

    @Autowired
    GeminiService geminiService;

    @Test
    void validadeAI() {

        Boolean retorno = this.geminiService.validadeAI("hj o dia esta lindo");

        assertEquals(true, retorno);
    }

    @Test
    void validadeAI2() {

        Boolean retorno = this.geminiService.validadeAI("o mundo e uma merda n presta para nada quero morrer");

        assertEquals(false, retorno);
    }
}