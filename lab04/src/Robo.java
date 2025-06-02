import java.util.ArrayList;

/**
 * Classe abstrata base para todos os tipos de robôs.
 * Implementa a interface Entidade e define os atributos e métodos comuns. 
 */
 public abstract class Robo implements Entidade {
    // Atributos básicos do robô
    protected final String id;      // Identificador único do robô
    protected EstadoRobo estado;    // Estado atual do robô (ativo, inativo, etc.)
    protected int x, y, z;          // Posição no ambiente
    // Lista de sensores - implementa composição (robô possui sensores)
    protected final ArrayList<Sensor> sensores = new ArrayList<>();

    public Robo(String id, int x, int y, int z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.estado = EstadoRobo.DESLIGADO; // Estado inicial é desligado
    }

    // Métodos para definição de posição
    public void moverPara(int x, int y, int z) {
        setPosicao(x, y, z);
    }

    // Métodos para alteração e verificação do estado do robô
    public void ligar() {
        estado = EstadoRobo.ATIVO; // Muda o estado para ativo
    }
    public void desligar() {
        estado = EstadoRobo.DESLIGADO; // Muda o estado para desligado
    }

    public boolean estaLigado() {
        return estado == EstadoRobo.ATIVO; // Verifica se o robô está ativo
    }

    public EstadoRobo getEstado(){
        return estado; // Retorna o estado atual do robô
    }

    // Interface Entidade
    @Override public int getX() {return x;} // Retorna a coordenada X do robô
    @Override public int getY() {return y;} // Retorna a coordenada Y 
    @Override public int getZ() {return z;} // Retorna a coordenada Z 
    
    @Override public void setPosicao(int x, int y, int z) {
        this.x = x; this.y = y; this.z = z; // Define a nova posição
    }

    @Override public TipoEntidade getTipo() {
        return TipoEntidade.ROBO; // Retorna o tipo da entidade
    }
    @Override public String getDescricao() {
        return "Robô " + id; // Retorna uma descrição textual do robô
    }
    @Override public char getRepresentacao() {
        return 'R'; // Retorna o caractere que representa o robô visualmente
    }

    public String getId() {
        return id; 
    }

    public void adicionarSensor(Sensor s) {
        sensores.add(s);
    }

    public ArrayList<Sensor> getSensores(){
        return sensores; 
    }

    // Método abstrato para ações específicas dentro de cada tipo de robô
    public abstract void executarTarefa();
 }