package com.techlab.inicio.PreEntrega;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final List<Producto> productos;

    public Pedido() {
        this.productos = new ArrayList<>();
    }

    public void agregarProductoAPedido(Producto producto){
        this.productos.add(producto);
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : this.productos){
            total += p.getPrecio();
        }
        return total;
    }

    public List<Producto> getProductos() {
        return this.productos;
    }
}

