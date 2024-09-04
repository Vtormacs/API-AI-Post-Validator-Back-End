package com.AI_Posts.Service.Calculo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculoServiceTest {

    @Autowired
    CalculoService calculoService;

    @Test
    void somar() {

        Double retorno = this.calculoService.somar(2.0,3.0);

        assertEquals(5, retorno);
    }
}