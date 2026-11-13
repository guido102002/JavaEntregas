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

    public DomiService() {
        cargarBarrios();
        cargarRepartidores();
        cargarCliente("1020304050", "Laura Gómez", "3001234567", "Teusaquillo", "Calle 1 # 2-3");
        cargarCliente("1030405060", "Mateo Rincón", "3109876543", "Usaquén", "Calle 1 # 2-3");
    }

    private void cargarBarrios() {
        barriosCobertura.add("Chapinero");
        distanciasBarrios.put("Chapinero", 1.5);

        barriosCobertura.add("Teusaquillo");
        distanciasBarrios.put("Teusaquillo", 3.0);

        barriosCobertura.add("La Candelaria");
        distanciasBarrios.put("La Candelaria", 4.5);

        barriosCobertura.add("Usaquén");
        distanciasBarrios.put("Usaquén", 6.0);

        barriosCobertura.add("Cedritos");
        distanciasBarrios.put("Cedritos", 8.0);

        barriosCobertura.add("Fontibón");
        distanciasBarrios.put("Fontibón", 11.0);

        barriosCobertura.add("Suba");
        distanciasBarrios.put("Suba", 12.0);

        barriosCobertura.add("Kennedy");
        distanciasBarrios.put("Kennedy", 14.0);

        barriosCobertura.add("Bosa");
        distanciasBarrios.put("Bosa", 18.0);
    }

    private void cargarRepartidores(){
        repartidores.add(new Bici("R-01", "Camila Torres", true));
        repartidores.add(new Moto("R-02", "Andrés Mejía", true));
        repartidores.add(new Carro("R-03", "Jorge Pardo", true));

    }

    private void cargarCliente(String cedula, String nombre, String telefono, String barrio, String direccion){
        Cliente cliente = new Cliente(cedula, nombre, telefono, barrio, direccion);
        clientes.put(cliente.getCedula(), cliente);
    }

    //Metodos
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

    public void editarCliente(String cedula, String nuevoTelefono, String nuevoBarrio, String nuevaDireccion) {
        Cliente cliente = clientes.get(cedula.trim());

        if (cliente == null) {
            throw new IllegalArgumentException("No existe un cliente con la cédula " + cedula);
        }

        if (!barriosCobertura.contains(nuevoBarrio)) {
            throw new IllegalArgumentException("Domi no tiene cobertura en el barrio " + nuevoBarrio);
        }

        cliente.setTelefono(nuevoTelefono);
        cliente.setBarrio(nuevoBarrio);
        cliente.setDireccion(nuevaDireccion);
    }
}



