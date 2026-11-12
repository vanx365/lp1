# Práctica N.° 02 – Ejercicios básicos de programación en Java

**Curso:** Lenguaje de Programación 1 – Unidad 3, Laboratorio 07

**Integrantes:**
- Edwin Sebastian de Jesus Coaguila Suarez
- Cristhian Chunga Pacheco
- Robert Vilca Mendoza

## Descripción del proyecto

Conjunto de programas de consola en Java que resuelven los ejercicios 01 al 09 de la guía de práctica: operaciones básicas, variables y tipos de datos, cálculo de remuneraciones, operaciones geométricas, estructuras condicionales y repetitivas, métodos, arreglos y un menú de operaciones matemáticas.

> El **Sistema de gestión de ventas** (trabajo integrador, sección 4 de la guía) no está incluido en esta entrega.

## Requisitos

- JDK 17 o superior (probado con JDK 21).
- Terminal, o un IDE como VS Code, IntelliJ IDEA o NetBeans.

Verificar la instalación:

```
java -version
javac -version
```

## Estructura del repositorio

```
.
├── README.md
├── src/
│   ├── InformacionPersonal.java      (Ejercicio 01)
│   ├── DatosProducto.java            (Ejercicio 02)
│   ├── CalculoSueldo.java            (Ejercicio 03)
│   ├── CalculadoraGeometrica.java    (Ejercicio 04)
│   ├── EvaluacionAcademica.java      (Ejercicio 05)
│   ├── OperacionesCiclicas.java      (Ejercicio 06)
│   ├── OperacionesMetodos.java       (Ejercicio 07)
│   ├── ArregloNotas.java             (Ejercicio 08)
│   └── MenuMatematico.java           (Ejercicio 09)
└── evidencias/                       (salidas de ejecución de cada programa)
```

## Compilación y ejecución

Desde la carpeta raíz del proyecto:

```
mkdir bin
javac -encoding UTF-8 -d bin src/*.java
java -cp bin InformacionPersonal
```

Para ejecutar otro ejercicio, cambiar el nombre de la clase (`DatosProducto`, `CalculoSueldo`, `CalculadoraGeometrica`, `EvaluacionAcademica`, `OperacionesCiclicas`, `OperacionesMetodos`, `ArregloNotas`, `MenuMatematico`).

## Resumen de los ejercicios

| N.° | Archivo | Qué hace |
| --- | ------- | -------- |
| 01 | `InformacionPersonal.java` | Pide datos del estudiante, los muestra y calcula la edad dentro de cinco años. |
| 02 | `DatosProducto.java` | Usa `String`, `int`, `double`, `char` y `boolean`; calcula el valor del inventario y un incremento de precio del 10 %. |
| 03 | `CalculoSueldo.java` | Calcula sueldo básico, bruto, descuento del 10 % y sueldo neto. |
| 04 | `CalculadoraGeometrica.java` | Áreas, perímetros, volúmenes y conversiones de unidades. |
| 05 | `EvaluacionAcademica.java` | Promedio y condición del estudiante, divisibilidad entre 3 y 5, menor de cuatro números, año bisiesto y descuento por compra. |
| 06 | `OperacionesCiclicas.java` | Uso de `for`, `while` y `do-while`: conteo descendente, múltiplos de 3, factorial, suma de pares y conteo de positivos/negativos. |
| 07 | `OperacionesMetodos.java` | Seis métodos reutilizables invocados desde `main()`. |
| 08 | `ArregloNotas.java` | Arreglo de 10 notas con promedio, mayor, menor, aprobados/desaprobados y notas sobre el promedio. |
| 09 | `MenuMatematico.java` | Menú con `switch` y `do-while`; valida división entre cero y raíz de negativos. |

## Evidencias de ejecución

Las salidas completas de cada programa (con los datos ingresados) están en la carpeta `evidencias/`. Se recomienda además adjuntar capturas de pantalla de la terminal al ejecutar cada programa.

Ejemplo (Ejercicio 03):

```
Nombre del trabajador: Maria Lopez
Horas trabajadas: 160
Pago por hora: 12.5
Bonificacion adicional: 200

========= BOLETA DE PAGO =========
Trabajador: Maria Lopez
Sueldo basico: S/ 2000.00
Bonificacion:  S/ 200.00
Sueldo bruto:  S/ 2200.00
Descuento 10%: S/ 220.00
Sueldo neto:   S/ 1980.00
```

## Preguntas de comprensión

**Pregunta 1. ¿Qué diferencia existe entre utilizar variables individuales y arreglos para almacenar información en Java? Presentar un ejemplo de cada uno.**

Una variable individual guarda un solo valor, por lo que si se necesitan muchos datos del mismo tipo hay que declarar una variable por cada uno, lo cual es poco práctico y no permite recorrerlos con un ciclo. Un arreglo guarda varios valores del mismo tipo bajo un solo nombre, accediendo a cada uno por su índice, y puede recorrerse con `for`.

```java
// Variables individuales
double nota1 = 15, nota2 = 12, nota3 = 18;
double promedio = (nota1 + nota2 + nota3) / 3;

// Arreglo
double[] notas = {15, 12, 18};
double suma = 0;
for (int i = 0; i < notas.length; i++) {
    suma += notas[i];
}
double promedio2 = suma / notas.length;
```

**Pregunta 2. ¿Qué es un método en Java? Explicar la diferencia entre un método que utiliza `void` y otro que devuelve un valor mediante `return`.**

Un método es un bloque de código con nombre que realiza una tarea específica y puede invocarse varias veces, lo que evita repetir instrucciones y organiza el programa. Un método `void` ejecuta acciones (por ejemplo, imprimir) pero no devuelve ningún resultado. Un método con tipo de retorno devuelve un valor mediante `return`, que quien lo llama puede guardar o usar en otra operación.

```java
static void mostrarMensaje() {                 // void: solo imprime
    System.out.println("Bienvenido");
}

static double calcularAreaRectangulo(double base, double altura) {
    return base * altura;                      // devuelve un valor
}
```

**Pregunta 3. ¿Qué diferencias existen entre las estructuras condicionales `if-else` y `switch`? ¿En qué situaciones conviene utilizar cada una?**

`if-else` evalúa condiciones booleanas de cualquier tipo: comparaciones, rangos (`promedio >= 15`) y combinaciones con `&&` y `||`. `switch` compara una sola expresión contra valores concretos (`case`), y es más legible cuando hay muchas opciones discretas. Conviene `if-else` para rangos y condiciones compuestas, como clasificar el promedio en Excelente, Bueno, Regular o Desaprobado (ejercicio 05). Conviene `switch` para elegir entre opciones fijas, como el menú del ejercicio 09.

**Pregunta 4. ¿Qué es un arreglo unidimensional en Java y cuáles son sus principales ventajas para almacenar y procesar información?**

Es una estructura de tamaño fijo que almacena elementos del mismo tipo en posiciones consecutivas, identificadas por un índice que empieza en 0 (`double[] notas = new double[10];`). Sus ventajas son: agrupar datos relacionados bajo un único nombre, acceder a cualquier elemento directamente por su índice, recorrer y procesar todos los datos con ciclos (promedio, mayor, menor, conteos) y poder pasar el conjunto completo a un método.

**Pregunta 5. ¿Por qué es importante validar los datos ingresados por el usuario? Presentar dos ejemplos de validaciones aplicadas a los ejercicios desarrollados.**

Porque el usuario puede ingresar valores incorrectos o inesperados (texto en lugar de números, valores fuera de rango, divisiones entre cero) que provocarían errores de ejecución o resultados erróneos. Validar mantiene el programa estable y los resultados confiables. Ejemplos en este proyecto:

1. En `EvaluacionAcademica` y `ArregloNotas`, las notas se leen en un ciclo hasta que estén entre 0 y 20; si no, se muestra un mensaje y se vuelve a pedir.
2. En `MenuMatematico`, la opción 4 comprueba que el divisor no sea cero antes de dividir, y la opción 6 verifica que el número no sea negativo antes de calcular la raíz cuadrada.

Además, todos los programas capturan `NumberFormatException` para rechazar texto cuando se esperaba un número.
