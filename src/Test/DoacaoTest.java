package Test;

import model.Doacao;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DoacaoTest {

    @Test
    void testConstrutorVazio() {
        Doacao d = new Doacao();

        assertNotNull(d.getDataDoacao());
        assertEquals(0, d.getValor());
        assertEquals(0, d.getQuantidade());
    }

    @Test
    void testConstrutorDoacaoDinheiro() {
        Doacao d = new Doacao(1, "Maria", "Dinheiro", "Ajuda financeira", 150.0);

        assertEquals(1, d.getIdDoador());
        assertEquals("Maria", d.getNomeDoador());
        assertEquals("Dinheiro", d.getTipo());
        assertEquals("Ajuda financeira", d.getDescricao());
        assertEquals(150.0, d.getValor());
        assertNotNull(d.getDataDoacao());
    }

    @Test
    void testConstrutorDoacaoItem() {
        Doacao d = new Doacao(2, "João", "Roupa", "Camisas", 10, 0);

        assertEquals(2, d.getIdDoador());
        assertEquals("João", d.getNomeDoador());
        assertEquals("Roupa", d.getTipo());
        assertEquals("Camisas", d.getDescricao());
        assertEquals(10, d.getQuantidade());
    }

    @Test
    void testSettersEGetters() {
        Doacao d = new Doacao();

        d.setId(100);
        d.setIdDoador(10);
        d.setNomeDoador("Ana");
        d.setTipo("Alimento");
        d.setDescricao("Cestas básicas");
        d.setQuantidade(3);
        d.setValor(200.0);
        LocalDate hoje = LocalDate.now();
        d.setDataDoacao(hoje);
        d.setObservacoes("Entrega rápida");

        assertEquals(100, d.getId());
        assertEquals(10, d.getIdDoador());
        assertEquals("Ana", d.getNomeDoador());
        assertEquals("Alimento", d.getTipo());
        assertEquals("Cestas básicas", d.getDescricao());
        assertEquals(3, d.getQuantidade());
        assertEquals(200.0, d.getValor());
        assertEquals(hoje, d.getDataDoacao());
        assertEquals("Entrega rápida", d.getObservacoes());
    }

    @Test
    void testToString() {
        Doacao d = new Doacao(1, "Carlos", "Brinquedo", "Carrinhos", 5, 0);

        String texto = d.toString();

        assertTrue(texto.contains("Carlos"));
        assertTrue(texto.contains("Brinquedo"));
        assertTrue(texto.contains("5"));
    }
}