package servicio;

import modelo.Cliente;
import modelo.Repartidor;
import modelo.Pedido;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class DomiService {
    private Map<String, Cliente> Clientes = new HashMap<>();
    private Set<String> Barrios_Cobertura = new HashSet<>();
    private Map<String, Double> Distancias_Barrios = new HashMap<>();
    private List<Repartidor> Repartidores = new ArrayList<>();
    private List<Pedido> Pedidos = new ArrayList<>();
}
