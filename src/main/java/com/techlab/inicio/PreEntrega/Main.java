package com.techlab.inicio.PreEntrega;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        listadoProductosExistentes(productos);
        ArrayList<Pedido> pedidos = new ArrayList<>();

        Scanner entrada = new Scanner(System.in);
        int opcionUsuario = 0;


        do {
            try{
                System.out.println("""
              =====================================================
              SISTEMA DE GESTIÓN - TIENDA CAMELIA COSMETICA NATURAL
              =====================================================
              Menu principal
              
                1) Listar productos
                2) Agregar productos
                3) Buscar productos/Actualizar productos
                4) Eliminar producto
                5) Crear un pedido
                6) Listar pedidos
                7) Salir
              
              Elija una opción:
              """);
                opcionUsuario = entrada.nextInt();

                switch (opcionUsuario){
                    case 1 -> listarProductos(productos);
                    case 2 -> agregarProducto(productos);
                    case 3 -> buscarProducto(productos);
                    case 4 -> eliminarProducto(productos);
                    case 5 -> agregarPedido(pedidos, productos);
                    case 6 -> listadoPedidosCreados(pedidos);
                    case 7 -> System.out.println("Hasta pronto!");
                    default -> System.out.println("Opcion incorrecta. Por favor ingrese un número válido");
                }
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Ocurrió un error inesperado");
            }
        }while (opcionUsuario != 7);

    }


    public static void listadoProductosExistentes(ArrayList<Producto> productos) {
        productos.add(new Producto("Shampoo Sólido Cabello Seco", 3000, 70));
        productos.add(new Producto("Shampoo Sólido Cabello Normal", 3000, 70));
        productos.add(new Producto("Shampoo Sólido Cabello Graso", 3000, 70));
        productos.add(new Producto("Acondicionador Sólido", 4500, 100));
        productos.add(new Producto("Jabón Hidratante Vainilla y Karité", 1800, 70));
        productos.add(new Producto("Jabón Exfoliante Café", 1800, 70));
        productos.add(new Producto("Jabón Facial Menta", 1800, 70));
        productos.add(new Producto("Jabón Neutro Avena", 1800, 70));
        productos.add(new Producto("Jabonera Simple Bamboo", 1200, 100));
        productos.add(new Producto("Jabonera Doble Bamboo", 1600, 100));
        productos.add(new Producto("Desodorante Natural", 3500, 100));
        productos.add(new Producto("Pasta Dental Natural", 3800, 100));
    }


    private static void listarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()){
            System.out.println("No hay cargado ningun producto todavia");
            System.out.println("Puede cargar productos desde el Menu: Opcion 1) Agregar Productos");
        }else {
            for (Producto producto : productos){
                producto.mostrarInfo();
            }
        }
    }


    public static void agregarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        boolean seguirAgregando = true;

        while (seguirAgregando) {
            System.out.println("Por favor ingrese el nombre del producto que desea agregar: ");
            String nombre = entrada.nextLine();
            String nombreFormateado = capitalizarNombre(nombre);

            System.out.printf("Por favor ingrese el precio de %s: ", nombreFormateado);
            double precio = entrada.nextDouble();

            System.out.printf("Por favor ingrese el stock de %s: ", nombreFormateado);
            int stock = entrada.nextInt();
            entrada.nextLine();

            Producto producto = new Producto(nombreFormateado, precio, stock);
            productos.add(producto);

            System.out.println("El producto fue cargado exitosamente!");

            System.out.println("¿Desea agregar otro producto?");
            System.out.println("1 - SI");
            System.out.println("2 - NO. Volver al menú principal");
            int opcion = entrada.nextInt();
            entrada.nextLine();

            if (opcion != 1) {
                seguirAgregando = false;
            }
        }
    }


    private static String capitalizarNombre(String texto) {
        String[] palabras = texto.trim().toLowerCase().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                resultado.append(Character.toUpperCase(palabra.charAt(0)))
                        .append(palabra.substring(1))
                        .append(" ");
            }
        }

        return resultado.toString().trim();
    }


    private static void buscarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor ingrese el nombre o palabra clave del producto que desea buscar: ");
        String busqueda = entrada.nextLine();
        ArrayList<Producto> productosEncontrados = new ArrayList<>();

        for (Producto producto : productos) {
            if (producto.contieneNombre(busqueda)) {
                productosEncontrados.add(producto);
            }
        }

        if (productosEncontrados.isEmpty()) {
            System.out.println("No hay ningún producto con ese nombre, intente nuevamente");
        } else {
            for (Producto producto : productosEncontrados) {
                producto.mostrarInfo();

                System.out.println("¿Desea actualizar este producto?");
                System.out.println("1 - SI");
                System.out.println("2 - NO");
                int opcion = entrada.nextInt();
                entrada.nextLine();

                if (opcion == 1) {
                    System.out.println("¿Qué desea actualizar?");
                    System.out.println("1 - Precio");
                    System.out.println("2 - Stock");
                    System.out.println("3 - Ambos");
                    int opcionActualizar = entrada.nextInt();
                    entrada.nextLine();

                    if (opcionActualizar == 1 || opcionActualizar == 3) {
                        System.out.print("Ingrese el nuevo precio: ");
                        double nuevoPrecio = entrada.nextDouble();
                        entrada.nextLine();
                        if (nuevoPrecio >= 0) {
                            producto.setPrecio(nuevoPrecio);
                        } else {
                            System.out.println("Precio inválido. Debe ser mayor o igual a 0");
                        }
                    }

                    if (opcionActualizar == 2 || opcionActualizar == 3) {
                        System.out.print("Ingrese el nuevo stock: ");
                        int nuevoStock = entrada.nextInt();
                        entrada.nextLine();
                        if (nuevoStock >= 0) {
                            producto.setStock(nuevoStock);
                        } else {
                            System.out.println("Stock inválido. Debe ser mayor o igual a 0");
                        }
                    }

                    System.out.println("El producto fue actualizado exitosamente!");
                    producto.mostrarInfo();
                }
            }
            System.out.println("No hay más productos para mostrar");
        }
    }


    private static void eliminarProducto(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Por favor ingrese el ID del producto que desea eliminar(el ID se encuentra en el listado de productos)");
        int idAEliminar = entrada.nextInt();
        boolean encontrado = false;
        for (Producto producto : productos){
            if (producto.getId() == idAEliminar){
                encontrado = true;
                System.out.println("Este es el producto encontrado: ");
                producto.mostrarInfo();
                System.out.println("¿Esta seguro que desea eliminarlo?");
                System.out.println("1 - SI");
                System.out.println("2 - NO");
                int opcionEliminar = entrada.nextInt();
                if (opcionEliminar == 1){
                    System.out.println("El producto fue eliminado exitosamente");
                    productos.remove(producto);
                    break;
                } else {
                    System.out.println("No se ha eliminado ningun producto");
                }
            }
        }

        if (!encontrado){
            throw new RuntimeException("No se encontro ningun producto con el id: " + idAEliminar + ". Intente con otro ID");
        }
    }


    private static void agregarPedido(ArrayList<Pedido> pedidos, ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        Pedido nuevoPedido = new Pedido();

        System.out.println("Ingrese el nombre o palabra clave de los productos que desea buscar para su pedido: ");
        String busqueda = entrada.nextLine();
        ArrayList<Producto> productosEncontrados = new ArrayList<>();

        for (Producto producto : productos) {
            if (producto.contieneNombre(busqueda)) {
                productosEncontrados.add(producto);
            }
        }

        if (productosEncontrados.isEmpty()) {
            System.out.println("No se encontraron productos con ese nombre. Intente nuevamente");
            return;
        }

        System.out.println("Productos encontrados: ");
        for (Producto producto : productosEncontrados) {
            producto.mostrarInfo();
        }

        while (true) {
            System.out.print("Ingrese el ID del producto que desea agregar al pedido (o 0 para finalizar): ");
            int idSeleccionado = entrada.nextInt();
            entrada.nextLine();

            if (idSeleccionado == 0) {
                break;
            }

            Producto productoSeleccionado = null;
            for (Producto producto : productosEncontrados) {
                if (producto.getId() == idSeleccionado) {
                    productoSeleccionado = producto;
                    break;
                }
            }

            if (productoSeleccionado == null) {
                System.out.println("ID no válido. Intente con uno de los que se mostraron");
                continue;
            }

            System.out.print("Ingrese la cantidad que desea agregar: ");
            int cantidad = entrada.nextInt();
            entrada.nextLine();

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que 0");
            } else if (cantidad > productoSeleccionado.getStock()) {
                System.out.println("Stock insuficiente");
            } else {
                productoSeleccionado.setStock(productoSeleccionado.getStock() - cantidad);

                Producto productoParaPedido = new Producto(
                        productoSeleccionado.getNombre(),
                        productoSeleccionado.getPrecio(),
                        cantidad
                );
                nuevoPedido.agregarProductoAPedido(productoParaPedido);
                System.out.println("Producto agregado al pedido");
            }
        }

        if (!nuevoPedido.getProductos().isEmpty()) {
            pedidos.add(nuevoPedido);
            System.out.println("Pedido creado exitosamente!");
            System.out.printf("Total del pedido: $%.2f\n", nuevoPedido.calcularTotal());
        } else {
            System.out.println("No se agregó ningún producto. Pedido cancelado");
        }
    }


    public static void listadoPedidosCreados(ArrayList<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados");
            return;
        }

        System.out.println("Listado de pedidos: ");
        int contador = 1;
        for (Pedido pedido : pedidos) {
            System.out.printf("Pedido #%d - Total: $%.2f\n", contador, pedido.calcularTotal());
            contador++;
        }
    }


}