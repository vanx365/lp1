import java.util.Scanner;

/**
 * Ejercicio 05: Estructuras condicionales.
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class EvaluacionAcademica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ---- Parte principal: evaluacion academica ----
        System.out.println("===== EVALUACION ACADEMICA =====");
        double n1 = leerNota(sc, "Nota 1: ");
        double n2 = leerNota(sc, "Nota 2: ");
        double n3 = leerNota(sc, "Nota 3: ");
        double promedio = (n1 + n2 + n3) / 3;
        System.out.printf("Promedio: %.2f%n", promedio);
        System.out.println("Condicion: " + clasificar(promedio));

        // ---- Actividad 1: divisible entre 3 y 5 ----
        System.out.println("\n===== DIVISIBILIDAD =====");
        int numero = leerEntero(sc, "Ingrese un numero: ");
        boolean div3 = numero % 3 == 0;
        boolean div5 = numero % 5 == 0;
        if (div3 && div5) {
            System.out.println(numero + " es divisible entre 3 y entre 5.");
        } else if (div3) {
            System.out.println(numero + " es divisible solo entre 3.");
        } else if (div5) {
            System.out.println(numero + " es divisible solo entre 5.");
        } else {
            System.out.println(numero + " no es divisible entre 3 ni entre 5.");
        }

        // ---- Actividad 2: menor de cuatro numeros ----
        System.out.println("\n===== MENOR DE CUATRO NUMEROS =====");
        double a = leerDouble(sc, "Numero 1: ");
        double b = leerDouble(sc, "Numero 2: ");
        double c = leerDouble(sc, "Numero 3: ");
        double d = leerDouble(sc, "Numero 4: ");
        System.out.println("El menor es: " + menorDeCuatro(a, b, c, d));

        // ---- Actividad 3: anio bisiesto ----
        System.out.println("\n===== ANIO BISIESTO =====");
        int anio = leerEntero(sc, "Ingrese un anio: ");
        if (esBisiesto(anio)) {
            System.out.println(anio + " es bisiesto.");
        } else {
            System.out.println(anio + " no es bisiesto.");
        }

        // ---- Actividad 4: descuento por compra ----
        System.out.println("\n===== DESCUENTO DE COMPRA =====");
        double importe = leerDouble(sc, "Importe de la compra (S/): ");
        double porcentaje = porcentajeDescuento(importe);
        double descuento = importe * porcentaje / 100;
        System.out.printf("Descuento aplicado: %.0f%% (S/ %.2f)%n", porcentaje, descuento);
        System.out.printf("Total a pagar: S/ %.2f%n", importe - descuento);

        sc.close();
    }

    static String clasificar(double promedio) {
        if (promedio >= 18) {
            return "Excelente";
        } else if (promedio >= 15) {
            return "Bueno";
        } else if (promedio >= 11) {
            return "Regular";
        } else {
            return "Desaprobado";
        }
    }

    static double menorDeCuatro(double a, double b, double c, double d) {
        double menor = a;
        if (b < menor) menor = b;
        if (c < menor) menor = c;
        if (d < menor) menor = d;
        return menor;
    }

    static boolean esBisiesto(int anio) {
        return (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
    }

    static double porcentajeDescuento(double importe) {
        if (importe >= 500) {
            return 20;
        } else if (importe >= 300) {
            return 15;
        } else if (importe >= 100) {
            return 10;
        } else {
            return 0;
        }
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

    static int leerEntero(Scanner sc, String mensaje) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(sc.nextLine().trim());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
        return valor;
    }

    static double leerDouble(Scanner sc, String mensaje) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(sc.nextLine().trim());
                if (valor < 0 && mensaje.startsWith("Importe")) {
                    System.out.println("El importe no puede ser negativo.");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
        return valor;
    }
}
