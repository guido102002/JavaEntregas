package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Repartidor implements Calificable {

    protected String codigo;
    protected String nombre;
    protected boolean disponible;
    protected List<Integer> calificaciones = new ArrayList<>();

    // CONSTRUCTOR

    public Repartidor(String codigo, String nombre, boolean disponible) {
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.disponible = disponible;
    }

    // GETTERS

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public List<Integer> getCalificaciones() {
        return List.copyOf(calificaciones); // UnsupportedOperationException
    }

    // SETTERS

    public void setCodigo(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Oh-uh! El código no puede estar vacío. Por favor, verifique.");
        }

        this.codigo = codigo.trim();
    }

    public void setNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Oh-uh! El nombre no puede estar vacío. Por favor, verifique.");
        }

        this.nombre = nombre.trim();
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Métodos

    @Override
    public void recibirCalificacion(int calificacion) {

        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException("Oh-uh! La calificación debe estar dentro del rango de 1 a 5. Por favor, verifique.");
        }

        calificaciones.add(calificacion);
    }

    @Override
    public double obtenerPromedio() {

        double cont = 0;
        List<Integer> calificaciones = getCalificaciones();

        if (calificaciones.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < calificaciones.size(); i++) {
            cont += calificaciones.get(i);
        }

        double promedio = cont / calificaciones.size();

        return promedio;
    }
}
