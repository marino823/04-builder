package co.sena.mercasena.builder;

import java.math.BigDecimal;

public record ItemPedido(String producto, int cantidad, BigDecimal precioUnitario) {

    public BigDecimal subtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
