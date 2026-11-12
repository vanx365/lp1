import java.util.Scanner;

/**
 * Ejercicio 03: Entrada de datos y calculo de remuneraciones.
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class CalculoSueldo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre del trabajador: ");
        String nombre = sc.nextLine();
        double horas = leerDouble(sc, "Horas trabajadas: ");
        double pagoHora = leerDouble(sc, "Pago por hora: ");
        double bonificacion = leerDouble(sc, "Bonificacion adicional: ");

        double sueldoBasico = horas * pagoHora;
        double sueldoBruto = sueldoBasico + bonificacion;
        double descuento = sueldoBruto * 0.10;
        double sueldoNeto = sueldoBruto - descuento;

        System.out.println();
        System.out.println("========= BOLETA DE PAGO =========");
        System.out.println("Trabajador: " + nombre);
        System.out.printf("Sueldo basico: S/ %.2f%n", sueldoBasico);
        System.out.printf("Bonificacion:  S/ %.2f%n", bonificacion);
        System.out.printf("Sueldo bruto:  S/ %.2f%n", sueldoBruto);
        System.out.printf("Descuento 10%%: S/ %.2f%n", descuento);
        System.out.printf("Sueldo neto:   S/ %.2f%n", sueldoNeto);

        sc.close();
    }

    /** Lee un numero decimal no negativo. */
    static double leerDouble(Scanner sc, String mensaje) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(sc.nextLine().trim());
                if (valor >= 0) {
                    valido = true;
                } else {
                    System.out.println("El valor no puede ser negativo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
        return valor;
    }
}
