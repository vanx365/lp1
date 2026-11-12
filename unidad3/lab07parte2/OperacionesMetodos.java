import java.util.Scanner;

/**
 * Ejercicio 07: Metodos y reutilizacion de codigo.
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class OperacionesMetodos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        mostrarMensaje();

        // 1. Area del rectangulo
        double base = leerDouble(sc, "Base del rectangulo: ");
        double altura = leerDouble(sc, "Altura del rectangulo: ");
        System.out.printf("Area del rectangulo: %.2f%n", calcularAreaRectangulo(base, altura));

        // 2. Factorial
        int num = leerEntero(sc, "\nNumero para el factorial (0 a 20): ", 0, 20);
        System.out.println(num + "! = " + calcularFactorial(num));

        // 3. Primo
        int primo = leerEntero(sc, "\nNumero para verificar si es primo: ", 1, Integer.MAX_VALUE);
        if (esPrimo(primo)) {
            System.out.println(primo + " es primo.");
        } else {
            System.out.println(primo + " no es primo.");
        }

        // 4. Promedio
        double n1 = leerDouble(sc, "\nNota 1: ");
        double n2 = leerDouble(sc, "Nota 2: ");
        double n3 = leerDouble(sc, "Nota 3: ");
        System.out.printf("Promedio: %.2f%n", calcularPromedio(n1, n2, n3));

        // 5. Tabla de multiplicar
        int tabla = leerEntero(sc, "\nNumero para la tabla de multiplicar: ",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        mostrarTabla(tabla);

        sc.close();
    }

    /** Imprime un mensaje de bienvenida. */
    static void mostrarMensaje() {
        System.out.println("====================================");
        System.out.println("  Bienvenido al programa de metodos ");
        System.out.println("====================================\n");
    }

    static double calcularAreaRectangulo(double base, double altura) {
        return base * altura;
    }

    static long calcularFactorial(int numero) {
        long resultado = 1;
        for (int i = 2; i <= numero; i++) {
            resultado *= i;
        }
        return resultado;
    }

    static boolean esPrimo(int numero) {
        if (numero < 2) {
            return false;
        }
        for (int i = 2; (long) i * i <= numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

    static double calcularPromedio(double n1, double n2, double n3) {
        return (n1 + n2 + n3) / 3;
    }

    static void mostrarTabla(int numero) {
        System.out.println("Tabla de multiplicar del " + numero + ":");
        for (int i = 1; i <= 12; i++) {
            System.out.println(numero + " x " + i + " = " + (numero * i));
        }
    }

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

    static int leerEntero(Scanner sc, String mensaje, int min, int max) {
        int valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= min && valor <= max) {
                    valido = true;
                } else {
                    System.out.println("Valor fuera de rango.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
        return valor;
    }
}
