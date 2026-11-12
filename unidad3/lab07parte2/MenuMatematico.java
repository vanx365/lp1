import java.util.Scanner;

/**
 * Ejercicio 09: Menu de operaciones matematicas.
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class MenuMatematico {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1:
                    System.out.println("Resultado: " + sumar(leer(sc, "Primer numero: "),
                            leer(sc, "Segundo numero: ")));
                    break;
                case 2:
                    System.out.println("Resultado: " + restar(leer(sc, "Primer numero: "),
                            leer(sc, "Segundo numero: ")));
                    break;
                case 3:
                    System.out.println("Resultado: " + multiplicar(leer(sc, "Primer numero: "),
                            leer(sc, "Segundo numero: ")));
                    break;
                case 4:
                    double dividendo = leer(sc, "Dividendo: ");
                    double divisor = leer(sc, "Divisor: ");
                    if (divisor == 0) {
                        System.out.println("Error: no se puede dividir entre cero.");
                    } else {
                        System.out.println("Resultado: " + dividir(dividendo, divisor));
                    }
                    break;
                case 5:
                    double baseNum = leer(sc, "Base: ");
                    double exponente = leer(sc, "Exponente: ");
                    System.out.println("Resultado: " + potencia(baseNum, exponente));
                    break;
                case 6:
                    double numero = leer(sc, "Numero: ");
                    if (numero < 0) {
                        System.out.println("Error: no existe la raiz cuadrada real de un negativo.");
                    } else {
                        System.out.println("Resultado: " + raizCuadrada(numero));
                    }
                    break;
                case 7:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion invalida. Elija un numero del 1 al 7.");
            }
        } while (opcion != 7);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("==================================");
        System.out.println("       MENU MATEMATICO");
        System.out.println("==================================");
        System.out.println("1. Sumar dos numeros");
        System.out.println("2. Restar dos numeros");
        System.out.println("3. Multiplicar dos numeros");
        System.out.println("4. Dividir dos numeros");
        System.out.println("5. Calcular potencia");
        System.out.println("6. Calcular raiz cuadrada");
        System.out.println("7. Salir");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");
    }

    static double sumar(double a, double b) { return a + b; }

    static double restar(double a, double b) { return a - b; }

    static double multiplicar(double a, double b) { return a * b; }

    static double dividir(double a, double b) { return a / b; }

    static double potencia(double base, double exponente) { return Math.pow(base, exponente); }

    static double raizCuadrada(double n) { return Math.sqrt(n); }

    /** Lee la opcion del menu; devuelve -1 si el texto no es un entero. */
    static int leerOpcion(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static double leer(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }
}
