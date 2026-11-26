package pe.edu.laboratorio08;

/**
 * JavaBean que representa un libro de la biblioteca.
 * Atributos privados, constructor vacío, constructor completo,
 * getters, setters y toString().
 */
public class Libro {

    private String codigo;
    private String titulo;
    private String autor;
    private int anioPublicacion;

    public Libro() {
    }

    public Libro(String codigo, String titulo, String autor, int anioPublicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    @Override
    public String toString() {
        return String.format("Código: %s%nTítulo: %s%nAutor: %s%nAño: %d",
                codigo, titulo, autor, anioPublicacion);
    }
}
