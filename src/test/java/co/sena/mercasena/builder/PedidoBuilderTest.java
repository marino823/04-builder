package co.sena.mercasena.builder;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PedidoBuilderTest {

    @Test
    void construyePedidoCompleto() {
        Pedido p = Pedido.builder()
                .cliente("Laura Gómez")
                .direccion("Calle 10 # 5-20, Bogotá")
                .agregarItem("Café 500 g", 2, new BigDecimal("18000"))
                .agregarItem("Panela 1 kg", 1, new BigDecimal("4500"))
                .notas("Dejar en portería")
                .build();
        assertEquals("Laura Gómez", p.getCliente());
        assertEquals("Calle 10 # 5-20, Bogotá", p.getDireccion());
        assertEquals("Dejar en portería", p.getNotas());
        assertNull(p.getCupon());
        assertEquals(2, p.getItems().size());
        assertEquals(0, new BigDecimal("40500").compareTo(p.total()));
    }

    @Test
    void sinDireccionNoSeConstruye() {
        Pedido.Builder b = Pedido.builder().cliente("Laura").agregarItem("Café", 1, BigDecimal.TEN);
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void sinItemsNoSeConstruye() {
        Pedido.Builder b = Pedido.builder().cliente("Laura").direccion("Calle 1");
        assertThrows(IllegalStateException.class, b::build);
    }

    @Test
    void cantidadCeroNoSePermite() {
        assertThrows(IllegalArgumentException.class,
                () -> Pedido.builder().agregarItem("Café", 0, BigDecimal.TEN));
    }
}
