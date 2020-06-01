package modelo;

public class Portatil {
    /*id INTEGER PRIMARY KEY AUTOINCREMENT,
	ram INTEGER NOT NULL,
	ssd INTEGER NOT NULL,
	pantalla INTEGER NOT NULL*/
    private int id;
    private int ram;
    private int ssd;
    private int pantalla;

    public Portatil(int id, int ram, int ssd, int pantallada) {
        this.id = id;
        this.ram = ram;
        this.ssd = ssd;
        this.pantalla = pantallada;
    }
    public Portatil( int ram, int ssd, int pantallada) {
        this.ram = ram;
        this.ssd = ssd;
        this.pantalla = pantallada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public int getPantalla() {
        return pantalla;
    }

    public void setPantalla(int pantalla) {
        this.pantalla = pantalla;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %d, %d", id, ram, ssd, pantalla);
    }
}
