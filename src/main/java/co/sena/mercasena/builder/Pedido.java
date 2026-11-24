package co.sena.mercasena.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Pedido inmutable construido con el patrón Builder. */
public final class Pedido {

    private final String cliente;
    private final String direccion;
    private final List<ItemPedido> items;
    private final String cupon;
    private final String notas;

    private Pedido(Builder b) {
        this.cliente = b.cliente;
        this.direccion = b.direccion;
        this.items = Collections.unmodifiableList(b.items);
        this.cupon = b.cupon;
        this.notas = b.notas;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCliente() {
        return cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public String getCupon() {
        return cupon;
    }

    public String getNotas() {
        return notas;
    }

    public BigDecimal total() {
        return items.stream().map(ItemPedido::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static final class Builder {

        private String cliente;
        private String direccion;
        private final List<ItemPedido> items = new ArrayList<>();
        private String cupon;
        private String notas;

        private Builder() {
        }

        public Builder cliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder direccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder agregarItem(String producto, int cantidad, BigDecimal precioUnitario) {
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que 0");
            }
            items.add(new ItemPedido(producto, cantidad, precioUnitario));
            return this;
        }

        public Builder cupon(String cupon) {
            this.cupon = cupon;
            return this;
        }

        public Builder notas(String notas) {
            this.direccion = notas;
            return this;
        }

        public Pedido build() {
            if (direccion == null || direccion.isBlank()) {
                throw new IllegalStateException("La dirección es obligatoria");
            }
            if (items.isEmpty()) {
                throw new IllegalStateException("El pedido debe tener al menos un ítem");
            }
            return new Pedido(this);
        }
    }
}
