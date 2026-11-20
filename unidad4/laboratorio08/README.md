# Laboratorio 08: Gestión de colecciones de objetos con JavaBeans y ArrayList

**Curso:** Lenguaje de Programación I
**Estudiante:** Edwin Sebastian de Jesus Coaguila Suarez
**Entorno:** Eclipse IDE for Java Developers · **Lenguaje:** Java 17
**Tema integrador:** Sistema de gestión de una biblioteca (aplicación de consola)

---

## 1. Descripción general del problema

Una biblioteca necesita controlar sus libros y los préstamos que realiza. Se desarrolló, de forma progresiva y sobre **un único proyecto Eclipse**, una aplicación de consola que:

1. Registra y lista libros (Ejercicio 1).
2. Administra los libros con operaciones CRUD mediante un menú interactivo (Ejercicio 2).
3. Gestiona préstamos y devoluciones, controlando la disponibilidad de cada libro (Ejercicio 3).

Toda la información se guarda temporalmente en colecciones `ArrayList` durante la ejecución (no hay base de datos).

## 2. Objetivos del laboratorio

- Crear y configurar un proyecto Java en Eclipse IDE.
- Diseñar clases JavaBean con encapsulamiento (atributos privados, constructores, getters y setters).
- Crear y administrar colecciones de objetos con `ArrayList`.
- Implementar operaciones para agregar, listar, buscar, modificar y eliminar objetos.
- Relacionar objetos de distintas clases (`Prestamo` → `Libro`).
- Aplicar validaciones y controlar la disponibilidad de los libros.
- Usar Git para registrar la evolución del proyecto en tres commits.
- Documentar el proceso y las funcionalidades implementadas.

## 3. Requisitos y herramientas utilizadas

| Recurso | Detalle |
|---|---|
| IDE | Eclipse IDE for Java Developers |
| JDK | Java 17 o superior |
| Control de versiones | Git (repositorio en GitHub) |
| Tipo de aplicación | Aplicación Java de consola |
| Estructura de datos | `ArrayList` |
| Modelo de objetos | Clases JavaBean |

## 4. Estructura del proyecto

```text
Laboratorio08/
├── README.md
├── .gitignore
├── evidencias/                      (capturas de pantalla)
└── Laboratorio08Biblioteca/
    ├── .project
    ├── .classpath
    ├── .settings/
    └── src/
        └── pe/edu/laboratorio08/
            ├── Libro.java
            ├── Prestamo.java
            └── Main.java
```

### Cómo abrir el proyecto en Eclipse

1. `File > Import... > General > Existing Projects into Workspace`.
2. En *Select root directory* elegir la carpeta `Laboratorio08Biblioteca`.
3. Finalizar. Ejecutar `Main.java` con `Run As > Java Application`.

## 5. Clases JavaBean

### `Libro`

| Atributo (privado) | Tipo |
|---|---|
| `codigo` | `String` |
| `titulo` | `String` |
| `autor` | `String` |
| `anioPublicacion` | `int` |

Incluye constructor vacío, constructor con todos los atributos, getters, setters y `toString()`.

### `Prestamo`

| Atributo (privado) | Tipo |
|---|---|
| `codigoPrestamo` | `String` |
| `libro` | `Libro` |
| `nombreUsuario` | `String` |
| `fechaPrestamo` | `String` (formato `dd/MM/yyyy`) |
| `devuelto` | `boolean` |

Incluye constructor vacío, constructor completo, getters (`isDevuelto()` para el booleano), setters y `toString()`.
El atributo `libro` es de tipo `Libro` (no `String`), por lo que cada préstamo queda relacionado directamente con el objeto registrado en la biblioteca.

## 6. Colecciones `ArrayList`

En `Main.main()` se declaran dos colecciones, una por cada clase JavaBean:

```java
ArrayList<Libro> libros = new ArrayList<>();
ArrayList<Prestamo> prestamos = new ArrayList<>();
```

- `libros` almacena los libros; se usan `add()`, `remove(posicion)`, `get()`, `size()` e `isEmpty()`.
- `prestamos` almacena los préstamos y sus devoluciones.
- La **disponibilidad** de un libro no se guarda como atributo: se calcula revisando si existe en `prestamos` algún préstamo de ese libro con `devuelto == false`.

## 7. Descripción de cada ejercicio

### Ejercicio 1 · Básico: registro y listado de libros
Se creó el proyecto `Laboratorio08Biblioteca`, el paquete `pe.edu.laboratorio08` y la clase `Libro`. En `Main` se crea un `ArrayList<Libro>`, se agregan tres libros y se recorren con `for-each` mostrando sus datos y el total.

**Commit 1:** `Commit 1: Implementar registro y listado básico de libros`

### Ejercicio 2 · Intermedio: administración de libros
Se agregó un menú interactivo con `Scanner` y un ciclo `do-while`, con las opciones: registrar, listar, buscar por código, modificar, eliminar y salir. Se incorporaron las validaciones de datos.

**Commit 2:** `Commit 2: Implementar administración CRUD de libros`

### Ejercicio 3 · Avanzado: gestión de préstamos
Se creó la clase `Prestamo`, la colección `ArrayList<Prestamo>` y las opciones 6 a 9 del menú (registrar préstamo, listar préstamos, registrar devolución y listar libros disponibles). La opción 10 es *Salir*.

**Commit 3:** `Commit 3: Implementar gestión de préstamos y devoluciones`

## 8. Funcionalidades implementadas

| Opción | Funcionalidad |
|---|---|
| 1 | Registrar libro |
| 2 | Listar libros |
| 3 | Buscar libro por código |
| 4 | Modificar libro (título, autor y año; el código es la clave y no cambia) |
| 5 | Eliminar libro |
| 6 | Registrar préstamo |
| 7 | Listar préstamos (estado `PENDIENTE` / `DEVUELTO`) |
| 8 | Registrar devolución |
| 9 | Listar libros disponibles |
| 10 | Salir |

El programa inicia con tres libros precargados (L001, L002 y L003) para facilitar las pruebas.

## 9. Validaciones realizadas

**Libros**
- No se permiten códigos duplicados ni vacíos.
- No se aceptan títulos ni autores vacíos.
- El año debe ser un número entero entre 1000 y el año actual.
- No se puede modificar ni eliminar un libro inexistente.

**Préstamos y devoluciones** (reglas de negocio)
1. No se puede prestar un libro que no exista.
2. No se puede prestar un libro que ya tenga un préstamo activo.
3. No se puede devolver un préstamo inexistente.
4. No se puede devolver dos veces el mismo préstamo.
5. Al registrar una devolución, el libro vuelve a estar disponible.
6. Un libro con préstamo activo no puede eliminarse.
7. No se pueden registrar dos préstamos con el mismo código.
8. El nombre del usuario no puede estar vacío y la fecha debe ser real y tener el formato `dd/MM/yyyy`.

### Pruebas funcionales

| N.º | Prueba | Resultado esperado | Resultado |
|---|---|---|---|
| 1 | Registrar un libro | Libro agregado correctamente | ✅ |
| 2 | Registrar un código duplicado | El sistema rechaza el registro | ✅ |
| 3 | Listar libros | Se muestran todos los libros | ✅ |
| 4 | Buscar un libro existente | Se muestran sus datos | ✅ |
| 5 | Buscar un libro inexistente | Se informa que no existe | ✅ |
| 6 | Modificar un libro | Se actualiza su información | ✅ |
| 7 | Eliminar un libro sin préstamo activo | Se elimina correctamente | ✅ |
| 8 | Registrar un préstamo | Se registra correctamente | ✅ |
| 9 | Prestar un libro ya prestado | El sistema rechaza la operación | ✅ |
| 10 | Registrar una devolución | El libro vuelve a estar disponible | ✅ |
| 11 | Devolver dos veces el mismo préstamo | El sistema rechaza la operación | ✅ |
| 12 | Listar libros disponibles | Solo aparecen los libros disponibles | ✅ |

## 10. Evidencias de ejecución

### Proyecto abierto en Eclipse
![Proyecto en Eclipse](evidencias/proyecto-eclipse.png)

### Clases `Libro.java`, `Prestamo.java` y `Main.java`
![Libro.java](evidencias/libro.png)
    ![alt text](image.png)
![Prestamo.java](evidencias/prestamo.png)

![Main.java](evidencias/main.png)

### Ejercicio 1: Registro y listado de libros
![Registro de libros](evidencias/ejercicio01.png)

### Ejercicio 2: Administración CRUD
![Administración de libros](evidencias/ejercicio02.png)

### Ejercicio 3: Gestión de préstamos
![Gestión de préstamos](evidencias/ejercicio03.png)

### Historial de commits
![Historial Git](evidencias/commits.png)

## 11. Control de versiones

```bash
git log --oneline
```

Deben verse los tres commits obligatorios:

1. `Commit 1: Implementar registro y listado básico de libros`
2. `Commit 2: Implementar administración CRUD de libros`
3. `Commit 3: Implementar gestión de préstamos y devoluciones`