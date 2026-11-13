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

        if (StringUtils.isBlank(cedula)) {
            throw new IllegalArgumentException("Oh-uh! La cédula no puede estar vacía. Por favor, verifique.");
        }

        cedula = cedula.trim();

        // Expresion regular para que solo se digiten numeros 0-9
        String regex = "\\d+";

        if (!cedula.matches()) {
            throw new IllegalArgumentException("La cédula solo debe contener números.");
        }

        this.cedula = cedula;

    }

    public void setNombre(String nombre) {

        if (StringUtils.isBlank(nombre)) {
            throw new IllegalArgumentException("Oh-uh! El nombre no puede estar vacío. Por favor, verifique.");
        }

        this.nombre = nombre;

    }

    public void setTelefono(String telefono) {

        if (StringUtils.isBlank(telefono)) {
            throw new IllegalArgumentException("Oh-uh! El teléfono no puede estar vacío. Por favor, verifique.");
        }

        // Expresion regular para reemplazar guioncitos espacios y valdiar que solo hayan digitos
        String regex = telefono.replaceAll("[ -]", "");

        if (regex.length() < 7 || regex.length() > 10) {
            throw new IllegalArgumentException("Oh-uh! El teléfono debe tener entre 7 y 10 dígitos. Por favor, verifique.");
        }

        if (!regex.matches("\\d+")) {
            throw new IllegalArgumentException("Oh-uh! El teléfono solo debe contener números, espacios o guiones. Por favor, verifique.");
        }

        this.telefono = telefono;

    }

    public void setBarrio(String barrio) {

        if (StringUtils.isBlank(barrio)) {
            throw new IllegalArgumentException("Oh-uh! El barrio no puede estar vacío. Por favor, verifique.");
        }

        this.barrio = barrio;
    }

    public void setDireccion(String direccion) {

        if (StringUtils.isBlank(direccion)) {
            throw new IllegalArgumentException("Oh-uh! La direccion no puede estar vacía. Por favor, verifique.");
        }

        this.direccion = direccion;
    }

    // CONSTRUCTOR

    public Cliente(String direccion, String barrio, String telefono, String nombre, String cedula) {

        this.direccion = direccion;
        this.barrio = barrio;
        this.telefono = telefono;
        this.nombre = nombre;
        this.cedula = cedula;

    }
}
