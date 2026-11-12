import java.util.Scanner;

/**
 * Ejercicio 04: Operadores aritmeticos y matematicos.
 * Integrantes: Cristhian Chunga Pacheco, Robert Vilca Mendoza
 */
public class CalculadoraGeometrica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Cuadrado
        System.out.println("--- CUADRADO ---");
        double lado = leerPositivo(sc, "Lado del cuadrado: ");
        double areaCuadrado = lado * lado;
        double perimetroCuadrado = 4 * lado;
        System.out.printf("Area: %.2f | Perimetro: %.2f%n", areaCuadrado, perimetroCuadrado);

        // 2. Triangulo
        System.out.println("\n--- TRIANGULO ---");
        double base = leerPositivo(sc, "Base: ");
        double altura = leerPositivo(sc, "Altura: ");
        double lado1 = leerPositivo(sc, "Lado 1: ");
        double lado2 = leerPositivo(sc, "Lado 2: ");
        double lado3 = leerPositivo(sc, "Lado 3: ");
        double areaTriangulo = (base * altura) / 2;
        double perimetroTriangulo = lado1 + lado2 + lado3;
        System.out.printf("Area: %.2f | Perimetro: %.2f%n", areaTriangulo, perimetroTriangulo);

        // 3. Cubo
        System.out.println("\n--- CUBO ---");
        double ladoCubo = leerPositivo(sc, "Lado del cubo: ");
        double volumenCubo = ladoCubo * ladoCubo * ladoCubo;
        System.out.printf("Volumen: %.2f%n", volumenCubo);

        // 4. Cilindro
        System.out.println("\n--- CILINDRO ---");
        double radio = leerPositivo(sc, "Radio: ");
        double alturaCilindro = leerPositivo(sc, "Altura: ");
        double volumenCilindro = Math.PI * radio * radio * alturaCilindro;
        System.out.printf("Volumen: %.2f%n", volumenCilindro);

        // 5. Kilometros a metros
        System.out.println("\n--- KILOMETROS A METROS ---");
        double km = leerPositivo(sc, "Kilometros: ");
        double metros = km * 1000;
        System.out.printf("%.2f km = %.2f m%n", km, metros);

        // 6. Horas a minutos y segundos
        System.out.println("\n--- HORAS A MINUTOS Y SEGUNDOS ---");
        double horas = leerPositivo(sc, "Horas: ");
        double minutos = horas * 60;
        double segundos = horas * 3600;
        System.out.printf("%.2f h = %.2f min = %.2f s%n", horas, minutos, segundos);

        sc.close();
    }

    /** Lee un numero decimal mayor que cero. */
    static double leerPositivo(Scanner sc, String mensaje) {
        double valor = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(sc.nextLine().trim());
                if (valor > 0) {
                    valido = true;
                } else {
                    System.out.println("El valor debe ser mayor que cero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
        return valor;
    }
}
