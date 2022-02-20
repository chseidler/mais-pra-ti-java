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
        assertAll( () -> assertTrue(Validador.validaTelefone("51999999999"))
                ,() -> assertTrue(Validador.validaTelefone("5199999999"))
        );
    }

    @Test
    void deveRetornarFalseNaValidacaoDoTelefone() {
        assertAll( () -> assertFalse(Validador.validaTelefone("999999999"))
                ,() -> assertFalse(Validador.validaTelefone("99999999"))
                ,() -> assertFalse(Validador.validaTelefone("51 99999-9999"))
                ,() -> assertFalse(Validador.validaTelefone("(51) 99999-9999"))
        );
    }

    @Test
    void deveRetornarTrueNaValidacaoDaData() {
        assertAll( () -> assertTrue(Validador.validaData("01/01/2022"))
                ,() -> assertTrue(Validador.validaData("31/12/2022"))
        );
    }

    @Test
    void deveRetornarFalseNaValidacaoDaData() {
        assertAll( () -> assertFalse(Validador.validaData("01-01-2022"))
                ,() -> assertFalse(Validador.validaData("32/01/2022"))
                ,() -> assertFalse(Validador.validaData("01/13/2022"))
                ,() -> assertFalse(Validador.validaData("01/12/22"))
        );
    }

    @Test
    void deveRetornarTrueNaValidacaoDaNota() {
        assertAll( () -> assertTrue(Validador.validaNota("10"))
                ,() -> assertTrue(Validador.validaNota("9.9"))
                ,() -> assertTrue(Validador.validaNota("0"))
                ,() -> assertTrue(Validador.validaNota("7.5"))
        );
    }

    @Test
    void deveRetornarFalseNaValidacaoDaNota() {
        assertAll( () -> assertFalse(Validador.validaNota("8.99"))
                ,() -> assertFalse(Validador.validaNota("8,9"))
                ,() -> assertFalse(Validador.validaNota("10.5"))
                ,() -> assertFalse(Validador.validaNota("-2"))
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