package modelo;

public class Moto extends Repartidor {

    private static final double TARIFA_BASE = 3000;
    private static final double VALOR_KM = 1000;
    private static final double VELOCIDAD_KMH = 30;
    private static final double DISTANCIA_MAXIMA_KM = 15;

    // CONSTRUCTOR

    public Moto(String codigo, String nombre, boolean disponible) {
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
    public boolean puedeLlevar(double km, boolean esGrande) {
        return !esGrande && km <= DISTANCIA_MAXIMA_KM;
    }

}