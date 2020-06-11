package ejercicioHerencia;

import java.time.LocalDate;

public class ProfesorInterino extends Profesor {
    private int mesesContrato;

    public ProfesorInterino(String nombrePersona, String apellido1, String apellido2, String dni, LocalDate fechaNacimiento,
                            double sueldoBruto, Materia materiaImpartida, int mesesContrato) {
        super(nombrePersona, apellido1, apellido2, dni, fechaNacimiento, sueldoBruto, materiaImpartida);
        this.mesesContrato = mesesContrato;
    }

    public int getMesesContrato() {
        return mesesContrato;
    }

    public void setMesesContrato(int mesesContrato) {
        this.mesesContrato = mesesContrato;
    }

    @Override
    public String toString() {
        return String.format("%s;%d", super.toString(), mesesContrato);
    }
}
