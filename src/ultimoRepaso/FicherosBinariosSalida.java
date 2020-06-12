package ultimoRepaso;

import java.io.*;
import java.util.*;

public class FicherosBinariosSalida {
    private static int TAMANNO = 3;
    public static void main(String[] args) {
        File outFile = new File("FICHEROS/datos.csv");
        Personal[] arrayPersonal = new Personal[TAMANNO];
        List<Personal> listaPersonal = new ArrayList<>();
        Set<Personal> conjuntoPersonal = new HashSet<>();
        Map<String, String> diccionarioPersonal = new HashMap<>();

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFile));
             Scanner sc = new Scanner(System.in);) {
            for (int i = 0; i < TAMANNO; i++) {
                System.out.println("Introduce tu nombre:");
                String nombre = sc.nextLine().trim();
                System.out.println("Introduce tu cargo:");
                String cargo = sc.nextLine().trim();
                arrayPersonal[i] = new Personal(nombre, cargo);
                conjuntoPersonal.add(new Personal(nombre, cargo));
                listaPersonal.add(new Personal(nombre, cargo));
                diccionarioPersonal.put(nombre, cargo);
                out.write(nombre.getBytes());
                out.write(",".getBytes());
                out.write(cargo.getBytes());
                out.write("\n".getBytes());
                out.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listaPersonal.add(new Personal("pepe", "jefe emérito"));
       // arrayPersonal[TAMANNO + 1] = new Personal("pepe", "jefe emérito");
        conjuntoPersonal.add(new Personal("pepe", "jefe emérito"));
        diccionarioPersonal.put("pepe", "jefe emérito");
        Collections.sort(listaPersonal);
        System.out.println("CONJUNTO");
        for (Personal personal: conjuntoPersonal) {
            System.out.println(personal);
        }
        System.out.println(".........................");
        System.out.println("LISTA");
        for (Personal personal: listaPersonal) {
            System.out.println(personal);
        }
        System.out.println(".........................");
        System.out.println("DICCIONARIO");
        Set<String> conjuntoNombres = diccionarioPersonal.keySet();
        for (String nombre: conjuntoNombres ) {
            System.out.printf("%s: %s%n", nombre, diccionarioPersonal.get(nombre));
        }
        System.out.println(".........................");
        System.out.println("ARRAY");
        for (int i = 0; i < TAMANNO; i++) {
            System.out.println(arrayPersonal[i]);
        }
    }
}

class Personal  implements Comparable<Personal>{
    private String nombre;
    private String cargo;

    public Personal(String nombre, String cargo) {
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return String.format("%s,%s", nombre, cargo);
    }

    @Override
    public boolean equals(Object obj) {
        Personal personal = (Personal) obj;
        return this.nombre.equals(personal.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public int compareTo(Personal personal) {
        return this.nombre.compareTo(personal.nombre);
    }
}
