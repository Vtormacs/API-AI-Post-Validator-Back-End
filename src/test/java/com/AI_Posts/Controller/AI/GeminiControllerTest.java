package com.AI_Posts.Controller.AI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeminiControllerTest {

    @Autowired
    GeminiController geminiController;

    @Test
    void testStatusDoRetorno() {

        ResponseEntity<String> retorno = this.geminiController.validateInput("hj o dia esta lindo");

        assertEquals(HttpStatus.OK, retorno.getStatusCode());
    }
}