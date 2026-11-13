package modelo;

public class Dron extends Repartidor {

    private static final double TARIFA_BASE = 4000;
    private static final double VALOR_KM = 600;
    private static final double VELOCIDAD_KMH = 50;
    private static final double DISTANCIA_MAXIMA_KM = 8;

    public Dron(String codigo, String nombre, boolean disponible) {
        super(codigo, nombre, disponible);
    }

    @Override
    public double calcularCosto(double km) {
        return TARIFA_BASE + (km * VALOR_KM);
    }

    @Override
    public double calcularTiempo(double km) {
        return km / VELOCIDAD_KMH * 60;
    }

    @Override
    public boolean puedeLlevar(double km, boolean esGrande, boolean modoLluvia) {

        if (modoLluvia) {
            return false;
        }

        return km <= DISTANCIA_MAXIMA_KM && !esGrande;
    }
}