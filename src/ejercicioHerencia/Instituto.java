package ejercicioHerencia;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Instituto {
    private String nombreInstituto;
    private List<Profesor> listaProfesores;

    public Instituto(String nombreInstituto, List<Profesor> listaProfesores) {
        this.nombreInstituto = nombreInstituto;
        this.listaProfesores = listaProfesores;
    }
    public boolean addProfesor(Profesor profesor) {
        return listaProfesores.add(profesor);
    }
    public boolean eliminarProfesor(String dni) {
        for (Profesor profesor: listaProfesores) {
            if (profesor.getDni().equals(dni)){
                listaProfesores.remove(profesor);
                return true;
            }
        }
        return false;
    }
    public boolean eliminarProfesor(Profesor profesor) {
        return listaProfesores.remove(profesor);
    }
    public void ampliarContrato (ProfesorInterino profesorInterino, int nuevosMeses){
        for (Profesor profesor: listaProfesores) {
            if (profesor instanceof ProfesorInterino){  //comprobamos que es un profesor interino
                ProfesorInterino pfi = (ProfesorInterino) profesor;
                if (pfi.equals(profesorInterino)) { //localizamos al profesor con ese dni
                    int nuevoContrato = nuevosMeses + pfi.getMesesContrato();
                    if (nuevoContrato > 12)
                        nuevoContrato = 12; //máximo un año
                    pfi.setMesesContrato(nuevoContrato);
                }
            }
        }
    }
    public void visualizarDatosProfesores(){ //alineación izquierda(-) usando cuatro columnas

        for (Profesor profesor: listaProfesores) {
            System.out.printf("%-15s%-15s%-15s%-15.2f%-15s%n", profesor.getApellido1(), profesor.getApellido2(),
                    profesor.getNombrePersona(),profesor.getSueldoBruto(), profesor.getMateriaImpartida());
        }
    }
    public Map<String, Double> listaSueldoProfesores(double retencion) {
        Map<String, Double> lista = new HashMap<>();
        for (Profesor profesor: listaProfesores) {
            lista.put(profesor.getDni(), profesor.getSueldoNeto(retencion));
        }
        return lista;
    }
    public void guardarDatos() {
        String nombreFichero = "FICHEROS/profesores_backup.csv";
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(nombreFichero)))) {
            for (Profesor profesor: listaProfesores) {
                out.println(profesor);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("FICHEROS/profesores.csv"));
        String nombreInstituto = in.nextLine();
        List<Profesor> lista = new ArrayList<>();
        //Aubert;Bord;Marlon;75628864p;6/13/1989;1200.0;SISTEMAS INFORMATICOS;1/18/2004
        //Lydon;Harlan;Ryson;84888199D;8/18/1986;1808.4;SISTEMAS INFORMATICOS;3
        while (in.hasNextLine()) {
            String linea = in.nextLine();
            String[] tokens = linea.split(";");
            String nombreProfesor = tokens[0];
            String apellido1Profesor = tokens[1];
            String apellido2Profesor = tokens[2];
            String dniProfesor = tokens[3];
            String[] datosFecha1 = tokens[4].split("/");
            int dia = Integer.parseInt(datosFecha1[1]);
            int mes = Integer.parseInt(datosFecha1[0]);
            int anno = Integer.parseInt(datosFecha1[2]);
            LocalDate fechaNacimiento = LocalDate.of(anno, mes, dia);
            double sueldoBruto = Double.parseDouble(tokens[5]);
            Materia materiaImpartida = null;
            switch (tokens[6]) {
                case "SISTEMAS INFORMATICOS":
                    materiaImpartida = Materia.SISTEMAS_INFORMATICOS;
                    break;
                case "BASE DE DATOS":
                    materiaImpartida = Materia.BASE_DE_DATOS;
                    break;
                case "EMPRESAS":
                    materiaImpartida = Materia.EMPRESAS;
                    break;
                case "LENGUAJES DE MARCAS":
                    materiaImpartida = Materia.LENGUAJES_DE_MARCAS;
                    break;
                case "ENTORNOS DE PROGRAMACION":
                    materiaImpartida = Materia.ENTORNOS_DE_PROGRAMACION;
                    break;
                case "PROGRAMACION":
                    materiaImpartida = Materia.PROGRAMACION;
                    break;
                default:
            }
       //     int contador1 = 0, contador2 = 0;
            if (tokens[7].matches("[\\d]+")) {
                lista.add(new ProfesorInterino(nombreProfesor, apellido1Profesor, apellido2Profesor, dniProfesor,
                        fechaNacimiento, sueldoBruto, materiaImpartida, Integer.parseInt(tokens[7])));
         //       System.out.println("profesor interino: " + contador1++);
            }
            else {
                String[] datosFecha2 = tokens[4].split("/");
                int diaI = Integer.parseInt(datosFecha2[1]);
                int mesI = Integer.parseInt(datosFecha2[0]);
                int annoI = Integer.parseInt(datosFecha2[2]);
                LocalDate fechaIncorporacion = LocalDate.of(anno, mes, dia);
                lista.add(new ProfesorTitular(nombreProfesor, apellido1Profesor, apellido2Profesor, dniProfesor,
                        fechaNacimiento, sueldoBruto, materiaImpartida, fechaIncorporacion));
            }
        }
        Collections.sort(lista);
        Instituto instituto = new Instituto(nombreInstituto, lista);
        Map<String, Double> listadoSueldo = instituto.listaSueldoProfesores(15.5);
        Set<String> dnis = listadoSueldo.keySet();
        for (String dni: dnis) {
            System.out.printf("DNI %s: %.2f€%n", dni, listadoSueldo.get(dni));
        }
        Profesor profesor = new Profesor(null, null, null,
                "48520480W", null, 0, null);
        instituto.eliminarProfesor(profesor);
        instituto.visualizarDatosProfesores();
//Richardo       Spratley       Mag            SISTEMAS_INFORMATICOS
        instituto.guardarDatos();
    }

}
