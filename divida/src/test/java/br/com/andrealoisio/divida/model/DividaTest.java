package br.com.andrealoisio.divida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DividaTest {

    private Divida divida;

    @BeforeEach
    public void inicializa() {
        this.divida = new Divida();
    }

    @Test
    public void naoDeveriaAceitarValorDaDividaMenorQue100Reais() {
        try {
            divida.setValor(new BigDecimal(99));
            fail("Deveria ter dado excecao");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Valor da divida deve ser maior que 100 reais", e.getMessage());
        }
    }

    @Test
    public void naoDeveriaAceitarValorDaDividaIguaA100Reais() {
        try {
            divida.setValor(new BigDecimal(100));
            fail("Deveria ter dado excecao");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Valor da divida deve ser maior que 100 reais", e.getMessage());
        }
    }

    @Test
    public void deveriaAceitarValorDaDividaMaiorQue100Reais() {
        BigDecimal valorDivida = new BigDecimal(101);

        try {
            divida.setValor(valorDivida);
            assertEquals(valorDivida, divida.getValor());
        }
        catch (IllegalArgumentException e) {
            fail("Nao deveria ter dado excecao");
        }
    }

    @Test
    public void naoDeveriaAceitarDataDeHojeComoDataLimite() {
        LocalDate hoje = LocalDate.now();

        try {
            divida.setDataLimite(hoje);
            fail("Deveria ter dado excecao");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Data limite precisa ser uma data futura cujo prazo não supere 1 ano", e.getMessage());
        }
    }

    @Test
    public void naoDeveriaAceitarDataLimiteComPrazoMaiorQueUmAnoAPartirDeHoje() {
        LocalDate umAnoEUmDiaAPartirDeHoje = LocalDate.now().plusYears(1).plusDays(1);

        try {
            divida.setDataLimite(umAnoEUmDiaAPartirDeHoje);
            fail("Deveria ter dado excecao");
        }
        catch (IllegalArgumentException e) {
            assertEquals("Data limite precisa ser uma data futura cujo prazo não supere 1 ano", e.getMessage());
        }
    }

    @Test
    public void deveriaAceitarDataDeAmanhaComoDataLimite() {
        LocalDate amanha = LocalDate.now().plusDays(1);

        try {
            divida.setDataLimite(amanha);
            assertEquals(amanha, divida.getDataLimite());
        }
        catch (IllegalArgumentException e) {
            fail("Nao deveria ter dado excecao");
        }
    }

    @Test
    public void deveriaAceitarDataLimiteComPrazoIgualAUmAnoAPartirDeHoje() {
        LocalDate umAnoAPartirDeHoje = LocalDate.now().plusYears(1);

        try {
            divida.setDataLimite(umAnoAPartirDeHoje);
            assertEquals(umAnoAPartirDeHoje, divida.getDataLimite());
        }
        catch (IllegalArgumentException e) {
            fail("Nao deveria ter dado excecao");
        }
    }

    @Test
    public void deveriaTerStatusNaoPagaAoInicializarDivida() {
        this.divida = new Divida();
        assertEquals(StatusDivida.NAO_PAGA, divida.getStatus());
    }

    @Test
    public void deveriaTerStatusPagaAoPagarDivida() {
        divida.pagar();
        assertEquals(StatusDivida.PAGA, divida.getStatus());
    }
}
