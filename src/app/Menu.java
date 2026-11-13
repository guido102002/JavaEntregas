package app;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Repartidor;
import servicio.DomiService;
import servicio.DomiService.Cotizacion;

public class Menu {
    private static final Locale LOCALE = Locale.forLanguageTag("es-CO");

    private Scanner scanner = new Scanner(System.in);
    private DomiService domiService = new DomiService();

    private void opcionCambiarModoLluvia() {
        domiService.cambiarModoLluvia();
    }

    private void printModoLluvia() {
        if (domiService.isModoLluvia()) {
            System.out.println("\n🌧️ MODO LLUVIA ACTIVADO");
        } else {
            System.out.println("\n☀️ MODO LLUVIA DESACTIVADO");
        }
    }

    public void iniciar() {
        int opcion = -1;

        do {
            printModoLluvia();
            System.out.println("\n══════════════════════════════════════════════");
            System.out.println("                 DOMI 🛵                   ");
            System.out.println("══════════════════════════════════════════════");
            System.out.println(" CLIENTES ");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar cliente");
            System.out.println("3. Editar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("═══════════════════════════════════════════════");
            System.out.println(" REPARTIDORES ");
            System.out.println("5. Registrar repartidor");
            System.out.println("6. Listar repartidores");
            System.out.println("7. Cambiar disponibilidad");
            System.out.println("═══════════════════════════════════════════════");
            System.out.println(" PEDIDOS ");
            System.out.println("8. Crear pedido (cotiza y asigna)");
            System.out.println("9. Entregar pedido");
            System.out.println("10. Cancelar pedido");
            System.out.println("11. Listar pedidos");
            System.out.println("═══════════════════════════════════════════════");
            System.out.println(" OPERACIÓN ");
            System.out.println("12. Activar / desactivar lluvia");
            System.out.println("13. Cierre del día");
            System.out.println("0. Salir ");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                switch (opcion) {
                    case 1:
                        opcionRegistrarCliente();
                        break;
                    case 2:
                        opcionListarClientes();
                        break;
                    case 3:
                        opcionEditarCliente();
                        break;
                    case 4:
                        opcionEliminarCliente();
                        break;
                    case 5:
                        opcionRegistrarRepartidor();
                        break;
                    case 6:
                        opcionListarRepartidores();
                        break;
                    case 7:
                        opcionCambiarDisponibilidad();
                        break;
                    case 8:
                        opcionCrearPedido();
                        break;
                    case 9:
                        opcionEntregarPedido();
                        break;
                    case 10:
                        opcionCancelarPedido();
                        break;
                    case 11:
                        opcionListarPedidos();
                        break;
                    case 12:
                        opcionCambiarModoLluvia();
                        break;
                    case 13:
                        opcionCierreDelDia();
                        break;
                    case 0:
                        System.out.println("\nSaliendo del sistema... ¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("\nOpción no válida, intenta de nuevo.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("\nError: Debes ingresar un número válido.");
                opcion = -1;
            } catch (IllegalArgumentException e) {
                System.out.println("\nError: " + e.getMessage());
            }
        } while (opcion != 0);

        scanner.close();
    }

    // CLIENTES

    private void opcionRegistrarCliente() {
        System.out.println("\n--- REGISTRAR NUEVO CLIENTE ---");
        System.out.print("Ingrese la cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese barrio: ");
        String barrio = scanner.nextLine();
        System.out.print("Ingrese dirección: ");
        String direccion = scanner.nextLine();

        domiService.registrarCliente(cedula, nombre, telefono, barrio, direccion);
        System.out.println("Cliente registrado con éxito.");
    }

    private void opcionListarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        List<Cliente> clientes = domiService.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente c : clientes) {
            System.out.println(c.getCedula() + " | " + c.getNombre() + " | Tel: " + c.getTelefono()
                    + " | " + c.getBarrio() + " | " + c.getDireccion());
        }
    }

    private void opcionEditarCliente() {
        System.out.println("\n--- EDITAR CLIENTE ---");
        System.out.println("--- SOLO SE PUEDE CAMBIAR EL TELÉFONO, BARRIO Y DIRECCIÓN ---");
        System.out.print("Ingrese la cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Ingrese nuevo teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese nuevo barrio: ");
        String barrio = scanner.nextLine();
        System.out.print("Ingrese nueva dirección: ");
        String direccion = scanner.nextLine();

        domiService.editarCliente(cedula, telefono, barrio, direccion);
        System.out.println("Cliente editado con éxito.");
    }

    private void opcionEliminarCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        System.out.print("Ingrese la cédula: ");
        String cedula = scanner.nextLine();

        domiService.eliminarCliente(cedula);
        System.out.println("Cliente eliminado con éxito.");
    }

    // ----------------------- REPARTIDORES -----------------------

    private void opcionRegistrarRepartidor() {
        System.out.println("\n--- REGISTRAR REPARTIDOR ---");
        System.out.print("Ingrese código (ej. R-04): ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese tipo (Bici, Moto, Carro): ");
        String tipo = scanner.nextLine();

        domiService.registrarRepartidor(codigo, nombre, tipo);
        System.out.println("Repartidor registrado con éxito.");
    }

    private void opcionListarRepartidores() {
        System.out.println("\n--- LISTA DE REPARTIDORES ---");
        List<Repartidor> repartidores = domiService.listarRepartidores();

        if (repartidores.isEmpty()) {
            System.out.println("No hay repartidores registrados.");
            return;
        }

        for (Repartidor r : repartidores) {
            System.out.println(r.getCodigo() + " | " + r.getNombre() + " | " + r.getTipo()
                    + " | " + (r.isDisponible() ? "Disponible" : "No disponible")
                    + " | Promedio: " + String.format(LOCALE, "%.1f", r.obtenerPromedio()));
        }
    }

    private void opcionCambiarDisponibilidad() {
        System.out.println("\n--- CAMBIAR DISPONIBILIDAD ---");
        System.out.print("Ingrese el código del repartidor: ");
        String codigo = scanner.nextLine();

        boolean disponible = domiService.cambiarDisponibilidad(codigo);
        System.out.println("Ahora el repartidor está: " + (disponible ? "Disponible" : "No disponible"));
    }

    // -------------------------- PEDIDOS --------------------------

    private void opcionCrearPedido() {
        System.out.println("\n--- CREAR PEDIDO ---");
        System.out.print("Ingrese la cédula del cliente: ");
        String cedula = scanner.nextLine();

        // Se valida que el cliente exista antes de pedir más datos
        Cliente cliente = domiService.buscarCliente(cedula);

        System.out.print("Ingrese la descripción del pedido: ");
        String descripcion = scanner.nextLine();
        boolean esGrande = leerSiNo("¿Es un pedido grande? (s/n): ");

        double km = domiService.obtenerKm(cliente.getBarrio());
        List<Cotizacion> cotizaciones = domiService.cotizarPedido(cedula, esGrande);

        System.out.println("\nCotizando pedido para " + cliente.getNombre() + " · "
                + cliente.getBarrio() + " (" + String.format(LOCALE, "%.1f", km) + " km)");

        for (Cotizacion c : cotizaciones) {
            Repartidor r = c.getRepartidor();
            System.out.println("  " + r.getCodigo() + " " + r.getNombre() + " (" + r.getTipo() + ")  $"
                    + formatearCosto(c.getCosto()) + "  " + formatearTiempo(c.getTiempo()) + " min");
        }

        // Si no hay cotizaciones, el servicio lanza la excepción y el menú la muestra
        Pedido pedido = domiService.crearPedido(cedula, descripcion, esGrande);

        System.out.println("✔ Pedido #" + pedido.getNumero() + " asignado a "
                + pedido.getRepartidor().getNombre() + " (" + pedido.getRepartidor().getTipo()
                + ") · " + pedido.getEstado());
    }

    private void opcionEntregarPedido() {
        System.out.println("\n--- ENTREGAR PEDIDO ---");
        System.out.print("Ingrese el número del pedido: ");
        int numero = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Califique la entrega (1 a 5): ");
        int calificacion = Integer.parseInt(scanner.nextLine().trim());

        Pedido pedido = domiService.entregarPedido(numero, calificacion);
        System.out.println("Pedido #" + pedido.getNumero() + " entregado con éxito.");
    }

    private void opcionCancelarPedido() {
        System.out.println("\n--- CANCELAR PEDIDO ---");
        System.out.print("Ingrese el número del pedido: ");
        int numero = Integer.parseInt(scanner.nextLine().trim());

        Pedido pedido = domiService.cancelarPedido(numero);
        System.out.println("Pedido #" + pedido.getNumero() + " cancelado con éxito.");
    }

    private void opcionListarPedidos() {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        List<Pedido> pedidos = domiService.listarPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.println("#" + p.getNumero()
                    + " | " + p.getCliente().getNombre()
                    + " | " + p.getRepartidor().getNombre() + " (" + p.getRepartidor().getTipo() + ")"
                    + " | $" + formatearCosto(p.getCosto())
                    + " | " + formatearTiempo(p.getTiempo()) + " min"
                    + " | " + p.getEstado());
        }
    }

    // -------------------------- CIERRE DEL DÍA --------------------------

    private void opcionCierreDelDia() {

        System.out.println("\n================ CIERRE DEL DÍA ================");

        // 1. Total de pedidos y cantidad por estado
        List<Pedido> todos = domiService.listarPedidos();
        Map<String, Integer> porEstado = domiService.contarPedidosPorEstado();

        System.out.println("Total de pedidos del día: " + todos.size());
        System.out.println("  - EN_CAMINO : " + porEstado.getOrDefault(Pedido.EN_CAMINO, 0));
        System.out.println("  - ENTREGADO : " + porEstado.getOrDefault(Pedido.ENTREGADO, 0));
        System.out.println("  - CANCELADO : " + porEstado.getOrDefault(Pedido.CANCELADO, 0));

        // 2. Ingresos totales (solo pedidos entregados)
        double ingresosTotales = domiService.calcularIngresosTotales();

        System.out.println("\nIngresos totales (ENTREGADOS): $" + formatearCosto(ingresosTotales));

        System.out.println("\nIngresos por tipo de repartidor:");

        Map<String, Double> porTipo = domiService.calcularIngresosPorTipo();

        if (porTipo.isEmpty()) {
            System.out.println("  (Sin ingresos registrados aún)");
        } else {
            for (Map.Entry<String, Double> entrada : porTipo.entrySet()) {
                System.out.println(
                        "  - " + entrada.getKey() + ": $" + formatearCosto(entrada.getValue()));
            }
        }

        Repartidor estrella = domiService.obtenerRepartidorEstrella();

        System.out.println("\n⭐ Repartidor estrella:");

        if (estrella == null) {
            System.out.println("  Aún no hay repartidores calificados en el turno.");
        } else {
            System.out.println(
                    "  " + estrella.getCodigo()
                            + " " + estrella.getNombre()
                            + " (" + estrella.getTipo() + ") - Promedio: "
                            + String.format(LOCALE, "%.1f", estrella.obtenerPromedio()));
        }
    }

    // ------------------------- AUXILIARES -------------------------

    private boolean leerSiNo(String mensaje) {
        System.out.print(mensaje);
        String respuesta = scanner.nextLine().trim();

        if (respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("si")
                || respuesta.equalsIgnoreCase("sí")) {
            return true;
        }

        if (respuesta.equalsIgnoreCase("n") || respuesta.equalsIgnoreCase("no")) {
            return false;
        }

        throw new IllegalArgumentException("Responde 's' para sí o 'n' para no.");
    }

    private String formatearCosto(double costo) {
        return String.format(LOCALE, "%,.0f", costo);
    }

    private String formatearTiempo(double minutos) {
        return String.format(LOCALE, "%.1f", minutos);
    }
}