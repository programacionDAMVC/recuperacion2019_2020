package ejercicioHerencia;

import java.time.LocalDate;

public class Persona  {
    /*dni con letra
• Nombre
• Apellido1
• Apellido2
• Fecha de nacimiento.
• Constructor
• Getters y setters
• toString con formato apellido1, apellido2, nombre, dni, fecha_nacimiento*/
    private String nombrePersona;
    private String apellido1;
    private String apellido2;
    private String dni;
    private LocalDate fechaNacimiento;

    public Persona(String nombrePersona, String apellido1, String apellido2, String dni, LocalDate fechaNacimiento) {
        this.nombrePersona = nombrePersona;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    //Lydon;Harlan;Ryson;84888199D;8/18/1986;1808.4;SISTEMAS INFORMATICOS;3

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%d/%d/%d;", nombrePersona, apellido1, apellido2, dni,
                fechaNacimiento.getDayOfMonth(), fechaNacimiento.getMonthValue(), fechaNacimiento.getYear());
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
    }


}
