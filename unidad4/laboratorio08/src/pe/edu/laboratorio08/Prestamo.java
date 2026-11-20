package pe.edu.laboratorio08;

/**
 * JavaBean que representa el préstamo de un libro.
 * La propiedad "libro" es un objeto de tipo Libro (relación entre clases).
 */
public class Prestamo {

    private String codigoPrestamo;
    private Libro libro;
    private String nombreUsuario;
    private String fechaPrestamo; // formato dd/MM/yyyy
    private boolean devuelto;

    public Prestamo() {
    }

    public Prestamo(String codigoPrestamo, Libro libro, String nombreUsuario,
                    String fechaPrestamo, boolean devuelto) {
        this.codigoPrestamo = codigoPrestamo;
        this.libro = libro;
        this.nombreUsuario = nombreUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.devuelto = devuelto;
    }

    public String getCodigoPrestamo() {
        return codigoPrestamo;
    }

    public void setCodigoPrestamo(String codigoPrestamo) {
        this.codigoPrestamo = codigoPrestamo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    @Override
    public String toString() {
        String datosLibro = (libro == null)
                ? "(sin libro)"
                : libro.getCodigo() + " - " + libro.getTitulo();
        return String.format("Código: %s%nLibro: %s%nUsuario: %s%nFecha: %s%nEstado: %s",
                codigoPrestamo, datosLibro, nombreUsuario, fechaPrestamo,
                devuelto ? "DEVUELTO" : "PENDIENTE");
    }
}
