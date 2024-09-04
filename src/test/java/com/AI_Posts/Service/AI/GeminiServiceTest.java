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

        String retorno = this.geminiService.validadeAI("hj o dia esta lindo");

        assertEquals("1", retorno);
    }

    @Test
    void validadeAI2() {

        String retorno = this.geminiService.validadeAI("o mundo e uma merda n presta para nada quero morrer");

        assertEquals("0", retorno);
    }
}