package modelo;

public class Cliente {

    private String cedula;
    private String nombre;
    private String telefono;
    private String barrio;
    private String direccion;

    // GETTERS

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getDireccion() {
        return direccion;
    }

    // SETTERS

    public void setCedula(String cedula) {

        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("Oh-uh! La cédula no puede estar vacía. Por favor, verifique.");
        }

        cedula = cedula.trim();

        // Expresion regular para que solo se digiten numeros 0-9
        String regex = "\\d+";

        if (!cedula.matches(regex)) {
            throw new IllegalArgumentException("Oh-uh! La cédula solo debe contener números.");
        }

        this.cedula = cedula;

    }

    public void setNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Oh-uh! El nombre no puede estar vacío. Por favor, verifique.");
        }

        this.nombre = nombre.trim();
    }

    public void setTelefono(String telefono) {

        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("Oh-uh! El teléfono no puede estar vacío. Por favor, verifique.");
        }

        // Limpiar entrada para validar longitud y contenido numérico
        String cleaned = telefono.replaceAll("[ -]", "");

        if (cleaned.length() < 7 || cleaned.length() > 10) {
            throw new IllegalArgumentException(
                    "Oh-uh! El teléfono debe tener entre 7 y 10 dígitos. Por favor, verifique.");
        }

        if (!cleaned.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "Oh-uh! El teléfono solo debe contener números, espacios o guiones. Por favor, verifique.");
        }

        this.telefono = cleaned; // Se almacena la versión limpia sin espacios ni guiones

    }

    public void setBarrio(String barrio) {

        if (barrio == null || barrio.trim().isEmpty()) {
            throw new IllegalArgumentException("Oh-uh! El barrio no puede estar vacío. Por favor, verifique.");
        }

        this.barrio = barrio.trim();
    }

    public void setDireccion(String direccion) {

        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("Oh-uh! La direccion no puede estar vacía. Por favor, verifique.");
        }

        this.direccion = direccion.trim();
    }

    // CONSTRUCTOR

    public Cliente(String cedula, String nombre, String telefono, String barrio, String direccion) {

        this.setCedula(cedula);
        this.setNombre(nombre);
        this.setTelefono(telefono);
        this.setBarrio(barrio);
        this.setDireccion(direccion);

    }
}