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
        this.codigo = codigo;
        this.nombre = nombre;
        this.disponible = disponible;
    }

    public List<Integer> getCalificaciones() {
        return List.copyOf(calificaciones); // UnsupportedOperationException
    }

    // Métodos

    @Override
    public void recibirCalificacaion(int calificacion) {
        if (calificacion < 1 || calificacion > 5) {
            return;
        }
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
