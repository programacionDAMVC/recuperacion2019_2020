package modelo;

import excepciones.NoPortatilAulaException;

public class Portatil {
    /*id INTEGER PRIMARY KEY AUTOINCREMENT,
	ram INTEGER NOT NULL,
	ssd INTEGER NOT NULL,
	pantalla INTEGER NOT NULL*/
    private int id;
    private int ram;
    private int ssd;
    private int pantalla;

    public Portatil(int id, int ram, int ssd, int pantalla) throws NoPortatilAulaException {
        if (!Auxiliar.validarOrdenador(id, ram, ssd, pantalla))
            throw new NoPortatilAulaException();
        this.id = id;
        this.ram = ram;
        this.ssd = ssd;
        this.pantalla = pantalla;
    }
    public Portatil( int ram, int ssd, int pantalla) throws NoPortatilAulaException {
        if (!Auxiliar.validarOrdenador(100, ram, ssd, pantalla))
            throw new NoPortatilAulaException();
        this.ram = ram;
        this.ssd = ssd;
        this.pantalla = pantalla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws NoPortatilAulaException {
        if (!Auxiliar.validarID(id))
            throw new NoPortatilAulaException();
        this.id = id;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) throws NoPortatilAulaException {
        if (!Auxiliar.validarRAM(ram))
            throw new NoPortatilAulaException();
        this.ram = ram;
    }

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) throws NoPortatilAulaException {
        if (!Auxiliar.validarSSD(ssd))
            throw new NoPortatilAulaException();
        this.ssd = ssd;
    }

    public int getPantalla() {
        return pantalla;
    }

    public void setPantalla(int pantalla) throws NoPortatilAulaException {
        if (!Auxiliar.validarPulgadas(pantalla))
            throw new NoPortatilAulaException();
        this.pantalla = pantalla;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %d, %d", id, ram, ssd, pantalla);
    }
}
