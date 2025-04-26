package LAB03.src;
import java.util.ArrayList;

import Ambiente;

public abstract class Robo {
    protected final String nome;
    protected int x, y;
    protected int altitude;
    protected final ArrayList<Sensor> sensores = new ArrayList<>();

    public Robo(String nome, int x, int y, int altitude) {
        this.nome = nome;
        this.x = x;
        this.y = y;
        this.altitude = altitude;
    }

    public String getNome() { return nome; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAltitude() { return altitude; }

    public void adicionarSensor(Sensor s) {
        sensores.add(s);
    }

    public void usarSensores(Ambiente ambiente) {
        for (Sensor s : sensores) {
            s.monitorar(ambiente);
        }
    }

    public abstract void mover(int dx, int dy, int dh);
}
