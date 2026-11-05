package lab07;
import java.util.Scanner;

public class SistemaRegistro {

    static Scanner teclado = new Scanner(System.in);

    static String[] nombres = new String[20];
    static double[] promedios = new double[20];

    static int cantidad = 0;

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE REGISTRO =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Mostrar estudiantes");
            System.out.println("3. Promedio general");
            System.out.println("4. Cantidad de aprobados");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:
                    if (cantidad < 20) {

                        System.out.print("Nombre: ");
                        nombres[cantidad] = teclado.nextLine();

                        double n1, n2, n3;

                        System.out.print("Nota 1: ");
                        n1 = teclado.nextDouble();

                        System.out.print("Nota 2: ");
                        n2 = teclado.nextDouble();

                        System.out.print("Nota 3: ");
                        n3 = teclado.nextDouble();

                        promedios[cantidad] = (n1 + n2 + n3) / 3;

                        System.out.printf("Promedio: %.2f%n", promedios[cantidad]);

                        if (promedios[cantidad] >= 11) {
                            System.out.println("APROBADO");
                        } else {
                            System.out.println("DESAPROBADO");
                        }

                        cantidad++;

                    } else {
                        System.out.println("Limite de estudiantes alcanzado.");
                    }
                    break;

                case 2:
                    if (cantidad == 0) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        for (int i = 0; i < cantidad; i++) {
                            System.out.println(nombres[i] + " - Promedio: "
                                    + promedios[i]);
                        }
                    }
                    break;

                case 3:
                    if (cantidad == 0) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        double suma = 0;

                        for (int i = 0; i < cantidad; i++) {
                            suma = suma + promedios[i];
                        }

                        System.out.printf("Promedio general: %.2f%n", suma / cantidad);
                    }
                    break;

                case 4:
                    int aprobados = 0;

                    for (int i = 0; i < cantidad; i++) {
                        if (promedios[i] >= 11) {
                            aprobados++;
                        }
                    }

                    System.out.println("Cantidad de aprobados: " + aprobados);
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);

        teclado.close();
    }
}