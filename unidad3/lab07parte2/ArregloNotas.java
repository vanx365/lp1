import java.util.Scanner;

/**
 * Ejercicio 08: Arreglos unidimensionales.
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class ArregloNotas {

    static final int TOTAL = 10;
    static final double NOTA_APROBATORIA = 11;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] notas = new double[TOTAL];

        for (int i = 0; i < TOTAL; i++) {
            notas[i] = leerNota(sc, "Nota del estudiante " + (i + 1) + ": ");
        }

        mostrarNotas(notas);

        double promedio = calcularPromedio(notas);
        System.out.printf("%nPromedio general: %.2f%n", promedio);
        System.out.printf("Nota mayor: %.2f%n", obtenerMayor(notas));
        System.out.printf("Nota menor: %.2f%n", obtenerMenor(notas));

        int aprobados = contarAprobados(notas);
        System.out.println("Estudiantes aprobados: " + aprobados);
        System.out.println("Estudiantes desaprobados: " + (TOTAL - aprobados));
        System.out.println("Estudiantes con nota superior al promedio: "
                + contarSuperioresAlPromedio(notas, promedio));

        sc.close();
    }

    static void mostrarNotas(double[] notas) {
        System.out.println("\n===== NOTAS REGISTRADAS =====");
        for (int i = 0; i < notas.length; i++) {
            System.out.printf("Estudiante %2d: %.2f%n", (i + 1), notas[i]);
        }
    }

    static double calcularPromedio(double[] notas) {
        double suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        return suma / notas.length;
    }

    static double obtenerMayor(double[] notas) {
        double mayor = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > mayor) {
                mayor = notas[i];
            }
        }
        return mayor;
    }

    static double obtenerMenor(double[] notas) {
        double menor = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < menor) {
                menor = notas[i];
            }
        }
        return menor;
    }

    static int contarAprobados(double[] notas) {
        int contador = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] >= NOTA_APROBATORIA) {
                contador++;
            }
        }
        return contador;
    }

    static int contarSuperioresAlPromedio(double[] notas, double promedio) {
        int contador = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] > promedio) {
                contador++;
            }
        }
        return contador;
    }

    /** Lee una nota y valida que este entre 0 y 20. */
    static double leerNota(Scanner sc, String mensaje) {
        double nota = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                nota = Double.parseDouble(sc.nextLine().trim());
                if (nota >= 0 && nota <= 20) {
                    valido = true;
                } else {
                    System.out.println("La nota debe estar entre 0 y 20.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
        return nota;
    }
}
