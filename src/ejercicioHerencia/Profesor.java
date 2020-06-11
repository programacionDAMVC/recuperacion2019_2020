package ejercicioHerencia;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Profesor extends Persona implements Comparable<Profesor>
{
    private double sueldoBruto;
    private Materia materiaImpartida;

    public Profesor(String nombrePersona, String apellido1, String apellido2, String dni, LocalDate fechaNacimiento,
                    double sueldoBruto, Materia materiaImpartida) {
        super(nombrePersona, apellido1, apellido2, dni, fechaNacimiento);
        this.sueldoBruto = sueldoBruto;
        this.materiaImpartida = materiaImpartida;
    }

    public double getSueldoBruto() {
        return sueldoBruto;
    }

    public void setSueldoBruto(double sueldoBruto) {
        this.sueldoBruto = sueldoBruto;
    }

    public Materia getMateriaImpartida() {
        return materiaImpartida;
    }

    public void setMateriaImpartida(Materia materiaImpartida) {
        this.materiaImpartida = materiaImpartida;
    }

    public double getSueldoNeto( double retencion) {
        return sueldoBruto - sueldoBruto * retencion / 100;
    }
    ////Lydon;Harlan;Ryson;84888199D;8/18/1986;1808.4;SISTEMAS INFORMATICOS;3

    @Override
    public String toString() {
        return String.format("%s;%.2f;%s", super.toString(), sueldoBruto, materiaImpartida);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDni());
    }

    @Override
    public boolean equals(Object obj) {
        Profesor profesor = (Profesor) obj;
        return this.getDni().equals(profesor.getDni());
    }

    @Override
    public int compareTo(Profesor profesor) {
        return (int) (this.getSueldoBruto() - profesor.getSueldoBruto());
    }
}
