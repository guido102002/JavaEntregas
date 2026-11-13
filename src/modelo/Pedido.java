package modelo;

import java.util.Set;

public class Pedido {

    public static final String EN_CAMINO = "EN_CAMINO";
    public static final String ENTREGADO = "ENTREGADO";
    public static final String CANCELADO = "CANCELADO";

    private static final Set<String> ESTADOS_VALIDOS = Set.of(EN_CAMINO, ENTREGADO, CANCELADO);

    private static int contador = 1;

    private int numero;
    private Cliente cliente;
    private String descripcion;
    private boolean grande;
    private double km;
    private Repartidor repartidor;
    private double costo;
    private double tiempo;
    private String estado;

    // CONSTRUCTOR

    public Pedido(Cliente cliente, String descripcion, boolean grande, double km,
                  Repartidor repartidor, double costo, double tiempo) {

        this.setCliente(cliente);
        this.setDescripcion(descripcion);
        this.grande = grande;
        this.setKm(km);
        this.setRepartidor(repartidor);
        this.setCosto(costo);
        this.setTiempo(tiempo);
        this.setEstado(EN_CAMINO);

        // El número se asigna al final: si alguna validación falla, no se gasta un consecutivo
        this.numero = contador++;
    }

    // GETTERS

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isGrande() {
        return grande;
    }

    public double getKm() {
        return km;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public double getCosto() {
        return costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public String getEstado() {
        return estado;
    }

    // SETTERS

    public void setCliente(Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Oh-uh! El pedido debe tener un cliente. Por favor, verifique.");
        }

        this.cliente = cliente;
    }

    public void setDescripcion(String descripcion) {

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("Oh-uh! La descripción no puede estar vacía. Por favor, verifique.");
        }

        this.descripcion = descripcion.trim();
    }

    public void setKm(double km) {

        if (km <= 0) {
            throw new IllegalArgumentException("Oh-uh! Los kilómetros deben ser mayores a cero.");
        }

        this.km = km;
    }

    public void setRepartidor(Repartidor repartidor) {

        if (repartidor == null) {
            throw new IllegalArgumentException("Oh-uh! El pedido debe tener un repartidor asignado.");
        }

        this.repartidor = repartidor;
    }

    public void setCosto(double costo) {

        if (costo < 0) {
            throw new IllegalArgumentException("Oh-uh! El costo no puede ser negativo.");
        }

        this.costo = costo;
    }

    public void setTiempo(double tiempo) {

        if (tiempo < 0) {
            throw new IllegalArgumentException("Oh-uh! El tiempo no puede ser negativo.");
        }

        this.tiempo = tiempo;
    }

    public void setEstado(String estado) {

        if (estado == null || !ESTADOS_VALIDOS.contains(estado.trim().toUpperCase())) {
            throw new IllegalArgumentException("Oh-uh! Estado no válido: " + estado
                    + ". Solo se permite EN_CAMINO, ENTREGADO o CANCELADO.");
        }

        this.estado = estado.trim().toUpperCase();
    }

}