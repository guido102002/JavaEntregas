package servicio;

import modelo.Cliente;
import modelo.Repartidor;
import modelo.Pedido;
import modelo.Bici;
import modelo.Moto;
import modelo.Carro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class DomiService {
    private Map<String, Cliente> clientes = new HashMap<>();
    private Set<String> barriosCobertura = new HashSet<>();
    private Map<String, Double> distanciasBarrios = new HashMap<>();
    private List<Repartidor> repartidores = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    public static class Cotizacion {
        private final Repartidor repartidor;
        private final double costo;
        private final double tiempo;

        public Cotizacion(Repartidor repartidor, double costo, double tiempo) {
            this.repartidor = repartidor;
            this.costo = costo;
            this.tiempo = tiempo;
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
    }

    public DomiService() {
        cargarBarrios();
        cargarRepartidores();
        cargarCliente("454535445", "Laura ", "354545", "barrio1", "Calle 19");
        cargarCliente("787878787", "Jenssen", "54454545", "barrio2", "Calle 20");
    }

    private void cargarBarrios() {
        barriosCobertura.add("barrio1");
        distanciasBarrios.put("barrio1", 1.5);

        barriosCobertura.add("barrio2");
        distanciasBarrios.put("barrio2", 3.0);

        barriosCobertura.add("barrio3");
        distanciasBarrios.put("barrio3", 4.5);

        barriosCobertura.add("barrio4");
        distanciasBarrios.put("barrio4", 6.0);

        barriosCobertura.add("barrio5");
        distanciasBarrios.put("barrio5", 8.0);

        barriosCobertura.add("barrio6");
        distanciasBarrios.put("barrio6", 11.0);

        barriosCobertura.add("barrio7");
        distanciasBarrios.put("barrio7", 12.0);

        barriosCobertura.add("barrio8");
        distanciasBarrios.put("barrio8", 14.0);

        barriosCobertura.add("barrio9");
        distanciasBarrios.put("barrio9", 18.0);
    }

    private void cargarRepartidores(){
        repartidores.add(new Bici("01", "Laura Garcia", true));
        repartidores.add(new Moto("02", "\n" +"Michalle Muñoz ", true));
        repartidores.add(new Carro("03", "Jenssen Quintero", true));

    }

    private void cargarCliente(String cedula, String nombre, String telefono, String barrio, String direccion){
        Cliente cliente = new Cliente(cedula, nombre, telefono, barrio, direccion);
        clientes.put(cliente.getCedula(), cliente);
    }

    //Metodos - CLIENTES
    public void registrarCliente(String cedula, String nombre, String telefono, String barrio, String direccion) {
        Cliente cliente = new Cliente(cedula, nombre, telefono, barrio, direccion);

        if (clientes.containsKey(cliente.getCedula())) {
            throw new IllegalArgumentException("Ya existe un cliente con la cédula " + cliente.getCedula());
        }

        if (!barriosCobertura.contains(cliente.getBarrio())) {
            throw new IllegalArgumentException("Domi no tiene cobertura en el barrio " + cliente.getBarrio());
        }

        clientes.put(cliente.getCedula(), cliente);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }

    public Cliente buscarCliente(String cedula) {
        Cliente cliente = clientes.get(cedula.trim());

        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con la cédula " + cedula.trim());
        }

        return cliente;
    }

    public double obtenerKm(String barrio) {
        Double km = distanciasBarrios.get(barrio);

        if (km == null) {
            throw new IllegalArgumentException("No hay distancia registrada para el barrio " + barrio);
        }

        return km;
    }

    public void editarCliente(String cedula, String nuevoTelefono, String nuevoBarrio, String nuevaDireccion) {
        Cliente cliente = clientes.get(cedula.trim());

        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con la cédula " + cedula);
        }

        if (!barriosCobertura.contains(nuevoBarrio.trim())) {
            throw new IllegalArgumentException("Domi no tiene cobertura en el barrio " + nuevoBarrio);
        }

        cliente.setTelefono(nuevoTelefono);
        cliente.setBarrio(nuevoBarrio);
        cliente.setDireccion(nuevaDireccion);
    }

    public void eliminarCliente(String cedula) {
        Cliente cliente = clientes.get(cedula.trim());

        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con la cédula " + cedula);
        }

        // no se elimina  cliente con pedidos en curso
        for (Pedido p : pedidos) {
            if (p.getCliente().getCedula().equals(cliente.getCedula())
                    && p.getEstado().equals(Pedido.EN_CAMINO)) {
                throw new IllegalArgumentException(
                        "No se puede eliminar al cliente: tiene el pedido #" + p.getNumero() + " EN_CAMINO.");
            }
        }

        clientes.remove(cliente.getCedula());
    }

    //Metodos repartidores
    public void registrarRepartidor(String codigo, String nombre, String tipo) {
        String codigoLimpio = codigo.trim();

        for (Repartidor r : repartidores) {
            if (r.getCodigo().equalsIgnoreCase(codigoLimpio)) {
                throw new IllegalArgumentException("Ya existe un repartidor con el código " + codigoLimpio);
            }
        }

        Repartidor nuevo;

        switch (tipo.trim().toLowerCase()) {
            case "bici":
                nuevo = new Bici(codigoLimpio, nombre, true);
                break;
            case "moto":
                nuevo = new Moto(codigoLimpio, nombre, true);
                break;
            case "carro":
                nuevo = new Carro(codigoLimpio, nombre, true);
                break;
            default:
                throw new IllegalArgumentException("Tipo de repartidor no válido: " + tipo);
        }

        repartidores.add(nuevo);
    }

    public List<Repartidor> listarRepartidores() {
        return new ArrayList<>(repartidores);
    }

    public boolean cambiarDisponibilidad(String codigo) {
        for (Repartidor r : repartidores) {
            if (r.getCodigo().equalsIgnoreCase(codigo.trim())) {
                r.setDisponible(!r.isDisponible());
                return r.isDisponible();
            }
        }

        throw new IllegalArgumentException("No existe un repartidor con el código " + codigo);
    }

    //Metodos pedidos

    public List<Cotizacion> cotizarPedido(String cedula, boolean esGrande) {
        Cliente cliente = buscarCliente(cedula);
        double km = obtenerKm(cliente.getBarrio());

        List<Cotizacion> cotizaciones = new ArrayList<>();

        for (Repartidor r : repartidores) {
            if (r.isDisponible() && r.puedeLlevar(km, esGrande)) {
                cotizaciones.add(new Cotizacion(r, r.calcularCosto(km), r.calcularTiempo(km)));
            }
        }

        return cotizaciones;
    }

    public Pedido crearPedido(String cedula, String descripcion, boolean esGrande) {
        Cliente cliente = buscarCliente(cedula);
        double km = obtenerKm(cliente.getBarrio());

        List<Cotizacion> cotizaciones = cotizarPedido(cedula, esGrande);

        if (cotizaciones.isEmpty()) {
            throw new IllegalArgumentException(
                    "Ningún repartidor disponible puede llevar este pedido. El pedido no fue creado.");
        }

        //menor costo si empatan, el más rápido
        Cotizacion mejor = cotizaciones.get(0);

        for (int i = 1; i < cotizaciones.size(); i++) {
            Cotizacion actual = cotizaciones.get(i);

            if (actual.getCosto() < mejor.getCosto()
                    || (actual.getCosto() == mejor.getCosto() && actual.getTiempo() < mejor.getTiempo())) {
                mejor = actual;
            }
        }

        Pedido pedido = new Pedido(cliente, descripcion, esGrande, km,
                mejor.getRepartidor(), mejor.getCosto(), mejor.getTiempo());

        mejor.getRepartidor().setDisponible(false);
        pedidos.add(pedido);

        return pedido;
    }

    public Pedido buscarPedido(int numero) {
        for (Pedido p : pedidos) {
            if (p.getNumero() == numero) {
                return p;
            }
        }

        throw new IllegalArgumentException("No existe el pedido #" + numero);
    }

    public Pedido entregarPedido(int numero) {
        Pedido pedido = buscarPedido(numero);

        if (!pedido.getEstado().equals(Pedido.EN_CAMINO)) {
            throw new IllegalArgumentException(
                    "No se puede entregar el pedido #" + numero + ": está " + pedido.getEstado() + ".");
        }

        pedido.setEstado(Pedido.ENTREGADO);
        pedido.getRepartidor().setDisponible(true);

        return pedido;
    }

    public Pedido cancelarPedido(int numero) {
        Pedido pedido = buscarPedido(numero);

        if (!pedido.getEstado().equals(Pedido.EN_CAMINO)) {
            throw new IllegalArgumentException(
                    "No se puede cancelar el pedido #" + numero + ": está " + pedido.getEstado() + ".");
        }

        pedido.setEstado(Pedido.CANCELADO);
        pedido.getRepartidor().setDisponible(true);

        return pedido;
    }

    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }
}