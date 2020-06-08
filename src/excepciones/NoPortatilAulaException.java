package excepciones;

public class NoPortatilAulaException extends Exception {
    @Override
    public String getMessage() {
        return "No portátil";
    }
}
/* Un ordenador tiene:
id: es un número pósitivo, empieza por 0, NO PUEDE SER NEGATIVO
ram: es un numéro positivo, distinto de cero, EMPIEZA POR 1 Y MÁXIMO 256
ssd: es un numéro positivo, distinto de cero, MÍNIMO 128 MÁXIMO 5000
pantalla: es un número positivo, distinto de cero, EMPIEZA 10 PULGADAS Y MÁXIMO 32
 */