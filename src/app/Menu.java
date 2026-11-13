package app;

import java.util.Scanner;

import servicio.DomiService;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private DomiService domiService = new DomiService();

    public void iniciar() {
        int opcion = -1;

        do {
            System.out.println("\n==========================================");
            System.out.println("      SISTEMA DE GESTIÓN DE DOMICILIOS    ");
            System.out.println("==========================================");
            System.out.print(" CLIENTES ");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar cliente");
            System.out.println("3. Editar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.print(" REPARTIDORES ");
            System.out.println("5. Registrar repartidor");
            System.out.println("6. Listar repartidores");
            System.out.println("7. Cambiar disponibilidad");
            System.out.print(" PEDIDOS ");
            System.out.println("8. Crear pedido (cotiza y asigna)");
            System.out.println("9. Entregar pedido");
            System.out.println("10. Cancelar pedido");
            System.out.println("11. Listar pedidos");
            System.out.print(" OPERACIÓN ");
            System.out.println("12. Activar / desactivar lluvia");
            System.out.println("13. Cierre del día");
            System.out.println("0. Salir ");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
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
                        // Crear pedido (cotiza y asigna)
                        break;
                    case 9:
                        // Entregar pedido
                        break;
                    case 10:
                        // Cancelar pedido
                        break;
                    case 11:
                        // Listar pedidos
                        break;
                    case 12:
                        // Activar / desactivar lluvia
                        break;
                    case 13:
                        // Cierre del día
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
        String dirrecion = scanner.nextLine();
        System.out.println("Cliente registrado con Exito.");
    }

    private void opcionListarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
    }

    private void opcionEditarCliente() {
        System.out.println("\n--- EDITAR CLIENTE ---");
        System.out.println("\n--- SOLO SE PUEDE CAMBIAR EL TELEFONO, BARRIO Y DIRECCIÓN ---");
        System.out.print("Ingrese la cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Ingrese nuevo teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese nuevo barrio: ");
        String barrio = scanner.nextLine();
        System.out.print("Ingrese nueva dirección: ");
        String dirrecion = scanner.nextLine();
        System.out.println("Cliente editado con éxito.");
    }

    private void opcionEliminarCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        System.out.print("Ingrese la cédula: ");
        String cedula = scanner.nextLine();
        System.out.println("Cliente eliminado con éxito.");
    }

    private void opcionRegistrarRepartidor() {
        System.out.println("\n--- REGISTRAR REPARTIDOR ---");
        System.out.print("Ingrese código (ej. R-04): ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese tipo (Bici, Moto, Carro): ");
        String tipo = scanner.nextLine();
        System.out.println("Repartidor registrado con Exito.");
        ;

    }

    private void opcionListarRepartidores() {
        System.out.println("\\n--- LISTA DE REPARTIDORES ---");
    }

    private void opcionCambiarDisponibilidad() {
        System.out.println("\\n--- CAMBIAR DISPONIBILIDAD ---");
        System.out.print("Ingrese el código del repartidor: ");
        String codigo = scanner.nextLine();
        System.out.println("Se cambio la disponibilidad con Exito.");
        ;
    }

}
