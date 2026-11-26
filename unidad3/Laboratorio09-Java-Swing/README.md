# Laboratorio 09 – Java Swing (Lenguaje de Programación I)

Dos aplicaciones de escritorio con Java Swing, desarrolladas en **Eclipse** y replicadas como proyecto **Java Application de NetBeans**.

| Ejercicio | Clase | Descripción |
|---|---|---|
| 01 | `FormularioEstudiante` | Registro de estudiantes: código, nombres, edad, carrera y género. Botones Registrar / Limpiar / Salir. |
| 02 | `Calculadora` | Suma, resta, multiplicación y división con validaciones. Botones Limpiar / Salir. |

## Integrante
- Edwin Sebastian de Jesus Coaguila Suarez


## Estructura

```text
Laboratorio09-Java-Swing/
├── README.md
├── Eclipse/
│   ├── Laboratorio09_Ejercicio01/src/ejercicio01/FormularioEstudiante.java
│   └── Laboratorio09_Ejercicio02/src/ejercicio02/Calculadora.java
├── NetBeans/
│   ├── Laboratorio09_Ejercicio01/src/laboratorio09_ejercicio01/FormularioEstudiante.java
│   └── Laboratorio09_Ejercicio02/src/laboratorio09_ejercicio02/Calculadora.java
└── Evidencias/
    ├── Ejercicio01/ejercicio01_eclipse.png
    └── Ejercicio02/ejercicio02_eclipse.png
```

## Componentes usados (según el enunciado)

- **Ejercicio 01:** 1 `JFrame`, 3 `JPanel`, 5 `JLabel`, 3 `JTextField`, 2 `JComboBox`, 3 `JButton`.
- **Ejercicio 02:** 1 `JFrame`, 4 `JPanel`, 3 `JLabel`, 3 `JTextField`, 6 `JButton`.
- Eventos con `ActionListener` (lambdas) y `JOptionPane` para mensajes.

## Validaciones

- **Ejercicio 01:** campos obligatorios, selección de carrera y género, edad entera y no negativa.
- **Ejercicio 02:** campos vacíos, valores no numéricos, división entre cero. Admite decimales (acepta `2,5` y `2.5`).
- Ambos piden confirmación al salir (incluida la X de la ventana).

## Cómo ejecutar

**Eclipse:** `File → Import → Existing Projects into Workspace`, elegir la carpeta del ejercicio y `Run As → Java Application`.

**NetBeans:** `File → New Project → Java with Existing Sources`, apuntar a la carpeta `src` del ejercicio y `Run Project`.
Para comparar con el diseñador visual, crear en NetBeans un `JFrame Form` (Palette) con los mismos componentes.

## Comparación Eclipse vs NetBeans

Eclipse exige construir la interfaz por código; NetBeans permite diseñarla arrastrando componentes desde la Palette y genera el método `initComponents()` automáticamente. La lógica (eventos y validaciones) es la misma en ambos.

## Pruebas realizadas

Calculadora con 25 y 7: suma 32.0, resta 18.0, multiplicación 175.0, división 3.5714285714285716; `2,5 × 0.5 = 1.25`.
