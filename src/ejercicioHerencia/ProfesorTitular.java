package ejercicioHerencia;

import java.time.LocalDate;
import java.time.Period;

public class ProfesorTitular extends Profesor{
    private LocalDate fechaIncorporacion;

    public ProfesorTitular(String nombrePersona, String apellido1, String apellido2, String dni, LocalDate fechaNacimiento, double sueldoBruto,
                           Materia materiaImpartida, LocalDate fechaIncorporacion) {
        super(nombrePersona, apellido1, apellido2, dni, fechaNacimiento, sueldoBruto, materiaImpartida);
        this.fechaIncorporacion = fechaIncorporacion;
    }

    public LocalDate getFechaIncorporacion() {
        return fechaIncorporacion;
    }

    public void setFechaIncorporacion(LocalDate fechaIncorporacion) {
        this.fechaIncorporacion = fechaIncorporacion;
    }
    //Annetta;Lennie;Tantum;42562598r;4/24/1996;1766.1;ENTORNOS DE PROGRAMACION;7/15/2007

    @Override
    public String toString() {
        return String.format("%s;%d/%d/%d", super.toString(), fechaIncorporacion.getDayOfMonth(),
                fechaIncorporacion.getMonthValue(), fechaIncorporacion.getYear());
    }

    @Override
    public double getSueldoNeto(double retencion) {
        return super.getSueldoNeto(retencion) + 100 * obtenerNumeroTrienios();
    }

    private int obtenerNumeroTrienios () {
        Period periodo = Period.between(fechaIncorporacion, LocalDate.now());
        return periodo.getYears();
    }
    ////Lydon;Harlan;Ryson;84888199D;8/18/1986;1808.4;SISTEMAS INFORMATICOS;3

}
