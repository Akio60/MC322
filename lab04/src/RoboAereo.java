import java.util.ArrayList;
import java.util.Random;

public class RoboAereo extends Robo implements Sensoreavel, Comunicavel, Autonomo, AlertaRapido, Manobrador {
    private CentralComunicacao centralComunicacao;
    private final ArrayList<String> mensagensRecebidas = new ArrayList<>();

    public RoboAereo(String id, int x, int y, int z) {
        super(id, x, y, z); // Z representa altitude para o aéreo
    }

    public void configurarComunicacao(CentralComunicacao central) {
        this.centralComunicacao = central;
    }

    protected void usarSensores(Ambiente ambiente) {
    System.out.println("Sensores acionados para o robô " + id);
    }

    public void mover(int dx, int dy, int dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
        System.out.println("Robo Aéreo '" + id + "' movido para (" + x + "," + y + "," + z + ")");
    }

    // Sensoreavel
    @Override
    public void acionarSensores() throws RoboDesligadoException {
        if (!estaLigado()) throw new RoboDesligadoException("Robô " + id + " está desligado.");
        usarSensores(null); // ou passe o ambiente real
    }

    // Comunicavel
    @Override
    public void enviarMensagem(Comunicavel destino, String mensagem) throws RoboDesligadoException {
        if (!estaLigado()) throw new RoboDesligadoException("Robô " + id + " está desligado.");
        if (centralComunicacao == null)
            throw new IllegalStateException("Central de comunicação não configurada para o robô " + id);

        centralComunicacao.registrarMensagem(this.getId(), mensagem);
    }

    @Override
    public void receberMensagem(String mensagem) {
        mensagensRecebidas.add(mensagem);
        System.out.println("Robo Aéreo '" + id + "' recebeu mensagem: " + mensagem);
    }

    public ArrayList<String> getMensagensRecebidas() {
        return mensagensRecebidas;
    }

    // Tarefa
    @Override
    public void executarTarefa() {
        if (!estaLigado()) {
            System.out.println("Robô aéreo " + id + " está desligado e não pode executar tarefas.");
            return;
        }

        System.out.println("RoboAereo '" + id + "' está sobrevoando para monitorar obstáculos.");
        mover(1, 1, 1);
        dispararAlerta("[Atenção] Monitoramento aéreo concluído.",
                    new ArrayList<>(), 

                    centralComunicacao);
    }

    // Autonomo
    @Override
    public void decidirProximaAcao(Ambiente ambiente) throws RoboDesligadoException {
        if (!estaLigado()) throw new RoboDesligadoException("Robô " + id + " está desligado.");
        Random rand = new Random();
        int dx = rand.nextInt(3) - 1;
        int dy = rand.nextInt(3) - 1;
        int dz = rand.nextInt(2); // sobe ou mantém
        mover(dx, dy, dz);
        System.out.println("RoboAereo '" + id + "' se moveu autonomamente para (" + x + "," + y + "," + z + ")");
    }

    // AlertaRapido
    @Override
    public void dispararAlerta(String mensagem, ArrayList<Entidade> entidades, CentralComunicacao central)
            throws RoboDesligadoException, ErroComunicacaoException {
        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô aéreo " + id + " está desligado e não pode disparar alerta.");
        }

        String alerta = "[ALERTA AÉREO] " + mensagem;
        System.out.println("RoboAereo '" + id + "' enviando: " + alerta);

        for (Entidade e : entidades) {
            if (e instanceof Comunicavel c && !c.equals(this)) {
                c.receberMensagem(alerta);
                if (central != null) {
                    central.registrarMensagem(id, alerta);
                }
            }
        }
    }
    // Manobrador
    @Override
    public void realizarManobra(String contexto) {
        if (!estaLigado()) {
            System.out.println("Robô aéreo " + id + " está desligado e não pode realizar manobras.");
            return;
        }
        System.out.println("RoboAereo '" + id + "' realizando manobra aérea de " + contexto + ".");
        mover(1, 2, 2); // exemplo de manobra
    }
}
