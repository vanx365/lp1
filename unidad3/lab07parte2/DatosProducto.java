/**
 * Ejercicio 02: Variables y tipos de datos.
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class DatosProducto {

    public static void main(String[] args) {
        String nombre = "Teclado mecanico";
        int codigo = 101;
        double precioUnitario = 80.00;
        int cantidad = 10;
        char categoria = 'A';
        boolean disponible = true;

        System.out.println("========= DATOS DEL PRODUCTO =========");
        System.out.println("Nombre: " + nombre);
        System.out.println("Codigo: " + codigo);
        System.out.printf("Precio unitario: S/ %.2f%n", precioUnitario);
        System.out.println("Cantidad disponible: " + cantidad);
        System.out.println("Categoria: " + categoria);
        System.out.println("Disponible: " + disponible);

        double valorInventario = precioUnitario * cantidad;
        System.out.printf("Valor total del inventario: S/ %.2f%n", valorInventario);

        // Incremento del 10 %
        precioUnitario = precioUnitario * 1.10;
        System.out.printf("Nuevo precio (+10%%): S/ %.2f%n", precioUnitario);

        double nuevoValorInventario = precioUnitario * cantidad;
        System.out.printf("Valor total del inventario despues del incremento: S/ %.2f%n",
                nuevoValorInventario);
    }
}
