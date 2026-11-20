package pe.edu.laboratorio08;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Sistema de gestión de una biblioteca (aplicación de consola).
 * Ejercicio 3: administración de libros (CRUD) y gestión de préstamos.
 */
public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final String LINEA = "====================================";
    private static final int INVALIDO = Integer.MIN_VALUE;
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static void main(String[] args) {
        ArrayList<Libro> libros = new ArrayList<>();
        ArrayList<Prestamo> prestamos = new ArrayList<>();

        precargarLibros(libros);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            System.out.println();

            switch (opcion) {
                case 1 -> registrarLibro(libros);
                case 2 -> listarLibros(libros);
                case 3 -> buscarLibroPorCodigo(libros);
                case 4 -> modificarLibro(libros);
                case 5 -> eliminarLibro(libros, prestamos);
                case 6 -> registrarPrestamo(libros, prestamos);
                case 7 -> listarPrestamos(prestamos);
                case 8 -> registrarDevolucion(prestamos);
                case 9 -> listarLibrosDisponibles(libros, prestamos);
                case 10 -> System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
            System.out.println();
        } while (opcion != 10);

        SC.close();
    }

    // ------------------------------------------------------------------
    // Menú y datos iniciales
    // ------------------------------------------------------------------

    private static void mostrarMenu() {
        System.out.println(LINEA);
        System.out.println("       SISTEMA DE BIBLIOTECA");
        System.out.println(LINEA);
        System.out.println();
        System.out.println("1. Registrar libro");
        System.out.println("2. Listar libros");
        System.out.println("3. Buscar libro");
        System.out.println("4. Modificar libro");
        System.out.println("5. Eliminar libro");
        System.out.println("6. Registrar préstamo");
        System.out.println("7. Listar préstamos");
        System.out.println("8. Registrar devolución");
        System.out.println("9. Listar libros disponibles");
        System.out.println("10. Salir");
        System.out.println();
    }

    private static void precargarLibros(ArrayList<Libro> libros) {
        libros.add(new Libro("L001", "Programación en Java", "Juan Pérez", 2022));
        libros.add(new Libro("L002", "Estructuras de Datos", "María López", 2023));
        libros.add(new Libro("L003", "Programación Orientada a Objetos", "Carlos Torres", 2024));
    }

    // ------------------------------------------------------------------
    // Lectura de datos y validaciones auxiliares
    // ------------------------------------------------------------------

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return SC.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        String texto = leerTexto(mensaje);
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return INVALIDO;
        }
    }

    private static boolean anioValido(int anio) {
        return anio >= 1000 && anio <= LocalDate.now().getYear();
    }

    private static boolean fechaValida(String fecha) {
        try {
            LocalDate.parse(fecha, FORMATO_FECHA);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // ------------------------------------------------------------------
    // Búsquedas sobre las colecciones
    // ------------------------------------------------------------------

    private static Libro buscarLibro(ArrayList<Libro> libros, String codigo) {
        for (Libro libro : libros) {
            if (libro.getCodigo().equalsIgnoreCase(codigo)) {
                return libro;
            }
        }
        return null;
    }

    private static Prestamo buscarPrestamo(ArrayList<Prestamo> prestamos, String codigo) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCodigoPrestamo().equalsIgnoreCase(codigo)) {
                return prestamo;
            }
        }
        return null;
    }

    /** Un libro tiene préstamo activo si existe un préstamo suyo que no ha sido devuelto. */
    private static boolean tienePrestamoActivo(ArrayList<Prestamo> prestamos, Libro libro) {
        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isDevuelto()
                    && prestamo.getLibro() != null
                    && prestamo.getLibro().getCodigo().equalsIgnoreCase(libro.getCodigo())) {
                return true;
            }
        }
        return false;
    }

    // ------------------------------------------------------------------
    // CRUD de libros
    // ------------------------------------------------------------------

    private static void registrarLibro(ArrayList<Libro> libros) {
        String codigo = leerTexto("Ingrese código: ");
        if (codigo.isEmpty()) {
            System.out.println("\nError: el código no puede estar vacío.");
            return;
        }
        if (buscarLibro(libros, codigo) != null) {
            System.out.println("\nError: ya existe un libro registrado con el código " + codigo + ".");
            return;
        }

        String titulo = leerTexto("Ingrese título: ");
        if (titulo.isEmpty()) {
            System.out.println("\nError: el título no puede estar vacío.");
            return;
        }

        String autor = leerTexto("Ingrese autor: ");
        if (autor.isEmpty()) {
            System.out.println("\nError: el autor no puede estar vacío.");
            return;
        }

        int anio = leerEntero("Ingrese año: ");
        if (!anioValido(anio)) {
            System.out.println("\nError: el año debe ser un número entre 1000 y "
                    + LocalDate.now().getYear() + ".");
            return;
        }

        libros.add(new Libro(codigo, titulo, autor, anio));
        System.out.println("\n¡Libro registrado correctamente!");
    }

    private static void listarLibros(ArrayList<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        System.out.println("LISTADO DE LIBROS");
        System.out.println();
        for (Libro libro : libros) {
            System.out.println(libro);
            System.out.println();
        }
        System.out.println("Total de libros registrados: " + libros.size());
    }

    private static void buscarLibroPorCodigo(ArrayList<Libro> libros) {
        String codigo = leerTexto("Ingrese código a buscar: ");
        Libro libro = buscarLibro(libros, codigo);
        System.out.println();
        if (libro == null) {
            System.out.println("El libro con código " + codigo + " no existe.");
        } else {
            System.out.println("LIBRO ENCONTRADO");
            System.out.println(libro);
        }
    }

    private static void modificarLibro(ArrayList<Libro> libros) {
        String codigo = leerTexto("Ingrese código del libro a modificar: ");
        Libro libro = buscarLibro(libros, codigo);
        if (libro == null) {
            System.out.println("\nError: el libro con código " + codigo + " no existe.");
            return;
        }

        System.out.println("\nDatos actuales:");
        System.out.println(libro);
        System.out.println();

        String titulo = leerTexto("Nuevo título: ");
        if (titulo.isEmpty()) {
            System.out.println("\nError: el título no puede estar vacío. No se modificó el libro.");
            return;
        }

        String autor = leerTexto("Nuevo autor: ");
        if (autor.isEmpty()) {
            System.out.println("\nError: el autor no puede estar vacío. No se modificó el libro.");
            return;
        }

        int anio = leerEntero("Nuevo año: ");
        if (!anioValido(anio)) {
            System.out.println("\nError: el año debe ser un número entre 1000 y "
                    + LocalDate.now().getYear() + ". No se modificó el libro.");
            return;
        }

        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setAnioPublicacion(anio);
        System.out.println("\n¡Libro modificado correctamente!");
    }

    private static void eliminarLibro(ArrayList<Libro> libros, ArrayList<Prestamo> prestamos) {
        String codigo = leerTexto("Ingrese código del libro a eliminar: ");

        int posicion = -1;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println("\nError: el libro con código " + codigo + " no existe.");
            return;
        }
        if (tienePrestamoActivo(prestamos, libros.get(posicion))) {
            System.out.println("\nError: el libro tiene un préstamo activo y no puede eliminarse.");
            return;
        }

        libros.remove(posicion);
        System.out.println("\n¡Libro eliminado correctamente!");
    }

    // ------------------------------------------------------------------
    // Préstamos y devoluciones
    // ------------------------------------------------------------------

    private static void registrarPrestamo(ArrayList<Libro> libros, ArrayList<Prestamo> prestamos) {
        System.out.println("REGISTRO DE PRÉSTAMO");
        System.out.println();

        String codigoPrestamo = leerTexto("Código del préstamo: ");
        if (codigoPrestamo.isEmpty()) {
            System.out.println("\nError: el código del préstamo no puede estar vacío.");
            return;
        }
        if (buscarPrestamo(prestamos, codigoPrestamo) != null) {
            System.out.println("\nError: ya existe un préstamo con el código " + codigoPrestamo + ".");
            return;
        }

        String codigoLibro = leerTexto("Código del libro: ");
        Libro libro = buscarLibro(libros, codigoLibro);
        if (libro == null) {
            System.out.println("\nError: el libro con código " + codigoLibro + " no existe.");
            return;
        }
        if (tienePrestamoActivo(prestamos, libro)) {
            System.out.println("\nError: el libro ya se encuentra prestado.");
            return;
        }

        System.out.println("\nLibro encontrado: " + libro.getTitulo());
        System.out.println();

        String usuario = leerTexto("Nombre del usuario: ");
        if (usuario.isEmpty()) {
            System.out.println("\nError: el nombre del usuario no puede estar vacío.");
            return;
        }

        String fecha = leerTexto("Fecha del préstamo (dd/MM/yyyy): ");
        if (!fechaValida(fecha)) {
            System.out.println("\nError: la fecha no es válida. Use el formato dd/MM/yyyy.");
            return;
        }

        prestamos.add(new Prestamo(codigoPrestamo, libro, usuario, fecha, false));
        System.out.println("\n¡Préstamo registrado correctamente!");
    }

    private static void listarPrestamos(ArrayList<Prestamo> prestamos) {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        System.out.println("LISTADO DE PRÉSTAMOS");
        System.out.println();
        for (Prestamo prestamo : prestamos) {
            System.out.println(prestamo);
            System.out.println();
        }
        System.out.println("Total de préstamos registrados: " + prestamos.size());
    }

    private static void registrarDevolucion(ArrayList<Prestamo> prestamos) {
        String codigo = leerTexto("Código del préstamo a devolver: ");
        Prestamo prestamo = buscarPrestamo(prestamos, codigo);
        System.out.println();

        if (prestamo == null) {
            System.out.println("Error: el préstamo con código " + codigo + " no existe.");
            return;
        }
        if (prestamo.isDevuelto()) {
            System.out.println("Error: este préstamo ya fue devuelto anteriormente.");
            return;
        }

        prestamo.setDevuelto(true);
        System.out.println("¡Devolución registrada correctamente!");
        System.out.println();
        System.out.println("El libro vuelve a estar disponible.");
    }

    private static void listarLibrosDisponibles(ArrayList<Libro> libros, ArrayList<Prestamo> prestamos) {
        System.out.println("LIBROS DISPONIBLES");
        System.out.println();

        int disponibles = 0;
        for (Libro libro : libros) {
            if (!tienePrestamoActivo(prestamos, libro)) {
                System.out.println(libro.getCodigo() + " - " + libro.getTitulo());
                disponibles++;
            }
        }
        if (disponibles == 0) {
            System.out.println("No hay libros disponibles.");
        }
    }
}
