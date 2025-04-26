package lab03.src;

import java.util.ArrayList;

/**
 * Classe abstrata base para todos os tipos de robôs.
 * Implementa o padrão Template Method para movimentação.
 * Gerencia sensores através de composição.
 */
public abstract class Robo {
    // Atributos básicos do robô
    protected final String nome;      // Identificador único do robô
    protected int x, y;              // Posição no plano
    protected int altitude;          // Altura em relação ao solo
    // Lista de sensores - implementa composição (robô possui sensores)
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

    /**
     * Método para adicionar sensores ao robô.
     * Implementa o padrão Composite com os sensores.
     */
    public void adicionarSensor(Sensor s) {
        sensores.add(s);
    }

    /**
     * Aciona todos os sensores instalados no robô.
     * Demonstra uso de polimorfismo com os diferentes tipos de sensores.
     */
    public void usarSensores(Ambiente ambiente) {
        for (Sensor s : sensores) {
            s.monitorar(ambiente);
        }
    }

    /**
     * Método abstrato de movimento.
     * Implementado diferentemente por robôs terrestres e aéreos.
     */
    public abstract void mover(int dx, int dy, int dh);
}
