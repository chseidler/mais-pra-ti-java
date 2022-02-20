package test.java.controller;

import main.java.controller.Validador;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    @Test
    void deveRetornarTrueNaValidacaoDoNome() {
       assertAll( () -> assertTrue(Validador.validaNome("Andressa"))
               ,() -> assertTrue(Validador.validaNome("De"))
               ,() -> assertTrue(Validador.validaNome("Christian Seidler"))
               ,() -> assertTrue(Validador.validaNome("Nome Completo"))
               );

    }

    @Test
    void deveRetornarFalseNaValidacaoDoNome() {
        assertAll( () -> assertFalse(Validador.validaNome("A"))
                ,() -> assertFalse(Validador.validaNome("Chr1stian"))
                ,() -> assertFalse(Validador.validaNome("Christian_Seidler"))
                ,() -> assertFalse(Validador.validaNome("Nome&Completo"))
        );

    }

    @Test
    void deveRetornarTrueNaValidacaoDoTelefone() {
        assertAll( () -> assertTrue(Validador.validaNome("Andressa"))
                ,() -> assertTrue(Validador.validaNome("De"))
                ,() -> assertTrue(Validador.validaNome("Christian Seidler"))
                ,() -> assertTrue(Validador.validaNome("Nome Completo"))
        );

    }

    @Test
    void deveRetornarFalseNaValidacaoDoTelefone() {
        assertAll( () -> assertFalse(Validador.validaNome("A"))
                ,() -> assertFalse(Validador.validaNome("Chr1stian"))
                ,() -> assertFalse(Validador.validaNome("Christian_Seidler"))
                ,() -> assertFalse(Validador.validaNome("Nome&Completo"))
        );

    }

//    @Test
//    void validaTelefone() {
//    }
//
//    @Test
//    void validaData() {
//    }
//
//    @Test
//    void validaNota() {
//    }
//
//    @Test
//    void validaID() {
//    }
}