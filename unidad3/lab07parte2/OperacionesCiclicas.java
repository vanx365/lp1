import java.util.Scanner;

/**
 * Ejercicio 06: Estructuras repetitivas (for, while, do-while).
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class OperacionesCiclicas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Del 100 al 1 en orden descendente (for)
        System.out.println("--- Numeros del 100 al 1 ---");
        for (int i = 100; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 2. Multiplos de 3 entre 1 y 100 (while)
        System.out.println("\n--- Multiplos de 3 entre 1 y 100 ---");
        int m = 3;
        while (m <= 100) {
            System.out.print(m + " ");
            m += 3;
        }
        System.out.println();

        // 3. Factorial de un numero (for)
        System.out.println("\n--- Factorial ---");
        int n = leerEntero(sc, "Ingrese un numero (0 a 20): ", 0, 20);
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(n + "! = " + factorial);

        // 4. Suma de pares entre 1 y N (while)
        System.out.println("\n--- Suma de pares entre 1 y N ---");
        int limite = leerEntero(sc, "Ingrese N (mayor o igual a 1): ", 1, Integer.MAX_VALUE);
        long sumaPares = 0;
        int p = 2;
        while (p <= limite) {
            sumaPares += p;
            p += 2;
        }
        System.out.println("Suma de pares entre 1 y " + limite + " = " + sumaPares);

        // 5 y 6. Leer hasta cero y contar positivos y negativos (do-while)
        System.out.println("\n--- Positivos y negativos ---");
        int positivos = 0;
        int negativos = 0;
        int numero;
        do {
            numero = leerEntero(sc, "Ingrese un numero (0 para terminar): ",
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (numero > 0) {
                positivos++;
            } else if (numero < 0) {
                negativos++;
            }
        } while (numero != 0);

        System.out.println();
        System.out.println("Numeros positivos: " + positivos);
        System.out.println("Numeros negativos: " + negativos);

        sc.close();
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
