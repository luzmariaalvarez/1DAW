
import java.util.ArrayList;
import java.util.Scanner;

public class TiendaZombie {

    static ArrayList<String> nombres = new ArrayList<>();
    static ArrayList<Double> precios = new ArrayList<>();
    static ArrayList<Integer> stocks = new ArrayList<>();

    static final double DESCUENTO = 0.9;
    static final double LIMITE_DESCUENTO = 50.0;

    public static void main(String[] args) {

        inicializarDatos();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEnteroSeguro(sc);

            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> comprarProducto(sc);
                case 3 -> añadirProducto(sc);
                case 4 -> buscarProducto(sc);
                case 5 -> mostrarInforme();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    static void inicializarDatos() {
        nombres.add("Camiseta");
        precios.add(12.99);
        stocks.add(10);

        nombres.add("Sudadera");
        precios.add(29.99);
        stocks.add(5);

        nombres.add("Gorra");
        precios.add(9.99);
        stocks.add(20);
    }

    static void mostrarMenu() {
        System.out.println("""
                \n--- ZOMBIE SHOP ---
                1. Ver productos
                2. Comprar producto
                3. Añadir producto
                4. Buscar producto
                5. Informe de inventario
                0. Salir
                """);
    }

    static void listarProductos() {
        for (int i = 0; i < nombres.size(); i++) {
            System.out.println((i + 1) + ". " + nombres.get(i)
                    + " - " + precios.get(i) + "€ - stock: " + stocks.get(i));
        }
    }

    static void comprarProducto(Scanner sc) {
        System.out.print("Número de producto: ");
        int indice = leerEnteroSeguro(sc) - 1;

        if (indice >= 0 && indice < nombres.size()) {
            System.out.print("Cantidad: ");
            int cantidad = leerEnteroSeguro(sc);

            if (cantidad > 0 && stocks.get(indice) >= cantidad) {
                stocks.set(indice, stocks.get(indice) - cantidad);
                double total = calcularTotal(precios.get(indice), cantidad);
                System.out.println("Total a pagar: " + total + "€");
            } else {
                System.out.println("Stock insuficiente o cantidad inválida");
            }
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    static void añadirProducto(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio: ");
        double precio = leerDoubleSeguro(sc);

        System.out.print("Stock: ");
        int stock = leerEnteroSeguro(sc);

        nombres.add(nombre);
        precios.add(precio);
        stocks.add(stock);

        System.out.println("Producto añadido correctamente");
    }

    static void buscarProducto(Scanner sc) {
        System.out.print("Texto a buscar: ");
        String texto = sc.nextLine().toLowerCase();
        boolean encontrado = false;

        for (int i = 0; i < nombres.size(); i++) {
            if (nombres.get(i).toLowerCase().contains(texto)) {
                System.out.println("Encontrado: " + nombres.get(i)
                        + " - " + precios.get(i) + "€ - stock: " + stocks.get(i));
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron coincidencias");
        }
    }

    static void mostrarInforme() {
        double valorTotal = 0;
        int totalUnidades = 0;

        for (int i = 0; i < nombres.size(); i++) {
            valorTotal += precios.get(i) * stocks.get(i);
            totalUnidades += stocks.get(i);
        }

        System.out.println("Valor total del inventario: " + valorTotal + "€");
        System.out.println("Unidades totales: " + totalUnidades);
    }

    static double calcularTotal(double precio, int cantidad) {
        double total = precio * cantidad;
        if (total > LIMITE_DESCUENTO) {
            total *= DESCUENTO;
        }
        return total;
    }

    static int leerEnteroSeguro(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Introduce un número válido: ");
            sc.next();
        }
        int valor = sc.nextInt();
        sc.nextLine();
        return valor;
    }

    static double leerDoubleSeguro(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.print("Introduce un número válido: ");
            sc.next();
        }
        double valor = sc.nextDouble();
        sc.nextLine();
        return valor;
    }
}
