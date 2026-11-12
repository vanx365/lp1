import java.util.Scanner;

/**
 * Ejercicio 01: Informacion personal y operaciones basicas.
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class InformacionPersonal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese sus nombres y apellidos: ");
        String nombre = sc.nextLine();

        int edad = leerEdad(sc);

        System.out.print("Ingrese su carrera: ");
        String carrera = sc.nextLine();

        System.out.print("Ingrese su ciclo: ");
        String ciclo = sc.nextLine();

        int edadFutura = edad + 5;

        System.out.println();
        System.out.println("========= DATOS DEL ESTUDIANTE =========");
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad actual: " + edad);
        System.out.println("Carrera: " + carrera);
        System.out.println("Ciclo: " + ciclo);
        System.out.println("Edad dentro de cinco anios: " + edadFutura);

        sc.close();
    }

    /** Lee una edad entera positiva, repitiendo hasta que sea valida. */
    static int leerEdad(Scanner sc) {
        int edad = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print("Ingrese su edad: ");
            String texto = sc.nextLine().trim();
            try {
                edad = Integer.parseInt(texto);
                if (edad > 0) {
                    valido = true;
                } else {
                    System.out.println("La edad debe ser mayor que cero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
        return edad;
    }
}
