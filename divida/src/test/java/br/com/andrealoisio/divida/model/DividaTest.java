package br.com.andrealoisio.divida.model;

import br.com.andrealoisio.divida.config.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DividaTest {

    private Divida divida;

    @BeforeEach
    public void inicializa() {
        this.divida = new Divida();
    }

    @Test
    public void naoDeveriaAceitarValorDaDividaMenorQue100Reais() {
        try {
            divida.iniciarValor(new BigDecimal(99));
            fail("Deveria ter dado excecao");
        }
        catch (DividaInvalidaException e) {
            assertEquals(Messages.getMessageForLocale("exception.divida.valor_invalido"), e.getLocalizedMessage());
        }
    }

    @Test
    public void naoDeveriaAceitarValorDaDividaIguaA100Reais() {
        try {
            divida.iniciarValor(new BigDecimal(100));
            fail("Deveria ter dado excecao");
        }
        catch (DividaInvalidaException e) {
            assertEquals(Messages.getMessageForLocale("exception.divida.valor_invalido"), e.getLocalizedMessage());
        }
    }

    @Test
    public void deveriaAceitarValorDaDividaMaiorQue100Reais() {
        BigDecimal valorDivida = new BigDecimal(101);

        try {
            divida.iniciarValor(valorDivida);
            assertEquals(valorDivida, divida.getValor());
        }
        catch (DividaInvalidaException e) {
            fail("Nao deveria ter dado excecao");
        }
    }

    @Test
    public void naoDeveriaAceitarDataDeHojeComoDataLimite() {
        LocalDate hoje = LocalDate.now();

        try {
            divida.iniciarDataLimite(hoje);
            fail("Deveria ter dado excecao");
        }
        catch (DividaInvalidaException e) {
            assertEquals(Messages.getMessageForLocale("exception.divida.data_invalida"), e.getLocalizedMessage());
        }
    }

    @Test
    public void naoDeveriaAceitarDataLimiteComPrazoMaiorQueUmAnoAPartirDeHoje() {
        LocalDate umAnoEUmDiaAPartirDeHoje = LocalDate.now().plusYears(1).plusDays(1);

        try {
            divida.iniciarDataLimite(umAnoEUmDiaAPartirDeHoje);
            fail("Deveria ter dado excecao");
        }
        catch (DividaInvalidaException e) {
            assertEquals(Messages.getMessageForLocale("exception.divida.data_invalida"), e.getLocalizedMessage());
        }
    }

    @Test
    public void deveriaAceitarDataDeAmanhaComoDataLimite() {
        LocalDate amanha = LocalDate.now().plusDays(1);

        try {
            divida.iniciarDataLimite(amanha);
            assertEquals(amanha, divida.getDataLimite());
        }
        catch (DividaInvalidaException e) {
            fail("Nao deveria ter dado excecao");
        }
    }

    @Test
    public void deveriaAceitarDataLimiteComPrazoIgualAUmAnoAPartirDeHoje() {
        LocalDate umAnoAPartirDeHoje = LocalDate.now().plusYears(1);

        try {
            divida.iniciarDataLimite(umAnoAPartirDeHoje);
            assertEquals(umAnoAPartirDeHoje, divida.getDataLimite());
        }
        catch (DividaInvalidaException e) {
            fail("Nao deveria ter dado excecao");
        }
    }

    @Test
    public void naoDeveriaAceitarDividaSemDevedores() {
        try {
            divida.iniciarDevedores(new ArrayList<>());
            fail("Deveria ter dado excecao");
        }
        catch (DividaInvalidaException e) {
            assertEquals(Messages.getMessageForLocale("exception.divida.devedor_vazio"), e.getLocalizedMessage());
        }
    }

    @Test
    public void deveriaAceitarDividaComUmDevedor() {
        List<Devedor> devedores = new ArrayList<>();
        devedores.add(new Devedor());

        try {
            divida.iniciarDevedores(devedores);
            assertEquals(devedores, divida.getDevedores());
        }
        catch (DividaInvalidaException e) {
            fail("Nao deveria ter dado excecao");
        }
    }
    @Test
    public void deveriaTerStatusNaoPagaAoInicializarDivida() {
        BigDecimal valorDivida = new BigDecimal(101);
        LocalDate dataLimite = LocalDate.now().plusDays(1);
        List<Devedor> devedores = new ArrayList<>();
        devedores.add(new Devedor());

        try {
            this.divida = new Divida(valorDivida, dataLimite, devedores);
            assertEquals(StatusDivida.NAO_PAGA, divida.getStatus());
        }
        catch (DividaInvalidaException e) {
            fail("Nao deveria ter dado excecao");
        }
    }

    @Test
    public void deveriaTerStatusPagaAoPagarDivida() {
        divida.pagar();
        assertEquals(StatusDivida.PAGA, divida.getStatus());
    }
}
