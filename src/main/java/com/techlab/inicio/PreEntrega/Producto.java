package com.techlab.inicio.PreEntrega;

public class Producto {
    private static int SIGUIENTE_ID = 1;
    private final int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id = SIGUIENTE_ID;
        SIGUIENTE_ID++;
    }


    public void mostrarInfo(){
        System.out.println("--------------------------------------------");
        System.out.println("Id: " + this.id);
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: " + this.precio);
        System.out.println("Stock: " + this.stock);
        System.out.println("--------------------------------------------");
    }


    public boolean contieneNombre(String busqueda) {
        String nombreMinuscula = quitarTildes(this.nombre.toLowerCase());
        String busquedaConCambios = quitarTildes(busqueda.toLowerCase());
        return nombreMinuscula.contains(busquedaConCambios);
    }


    private String quitarTildes(String texto) {
        return texto
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("Á", "a")
                .replace("É", "e")
                .replace("Í", "i")
                .replace("Ó", "o")
                .replace("Ú", "u");
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
