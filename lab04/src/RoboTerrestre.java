import java.util.ArrayList;
import java.util.Random;

public class RoboTerrestre extends Robo implements Sensoreavel, Comunicavel, Autonomo, AlertaRapido, Manobrador {

    private CentralComunicacao centralComunicacao;

    public RoboTerrestre(String id, int x, int y) {
        super(id, x, y, 0); // z = 0 para robôs terrestres
    }

    public void configurarComunicacao(CentralComunicacao central) {
        this.centralComunicacao = central;
    }

    protected void usarSensores(Ambiente ambiente) {
        System.out.println("Sensores acionados para o robô " + id);
    }

    // Movimento básico
    public void mover(int dx, int dy, int dh) {
        this.x += dx;
        this.y += dy;
        System.out.println("RoboTerrestre '" + id + "' movido para (" + x + "," + y + ")");
    }

    // Tarefa específica
    @Override
    public void executarTarefa() {
        if (!estaLigado()) {
            System.out.println("Robô terrestre " + id + " está desligado e não pode executar tarefas.");
            return;
        }

        System.out.println("Executando tarefa completa do RoboTerrestre...");

        // Acionar sensores
        try {
            acionarSensores();
        } catch (RoboDesligadoException e) {
            System.out.println("Erro nos sensores: " + e.getMessage());
        }

        // Ação autônoma
        try {
            decidirProximaAcao(null);
        } catch (RoboDesligadoException e) {
            System.out.println("Erro na ação autônoma: " + e.getMessage());
        }

        // Alerta
        try {
            dispararAlerta("Monitoramento terrestre concluído.", new ArrayList<>(), centralComunicacao);
        } catch (Exception e) {
            System.out.println("Erro no alerta: " + e.getMessage());
        }

        // Manobra
        try {
            realizarManobra("curva de reconhecimento");
        } catch (RoboDesligadoException e) {
            System.out.println("Erro na manobra: " + e.getMessage());
        }
    }

    // Sensoreavel
    @Override
    public void acionarSensores() {
        if (!estaLigado()) throw new RoboDesligadoException("Robô está desligado!");
        usarSensores(null); // ou ambiente real, se necessário
    }

    // Comunicavel
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
        if (!estaLigado()) throw new RoboDesligadoException("Robô está desligado!");
        destinatario.receberMensagem("De " + id + ": " + mensagem);
        if (centralComunicacao != null) {
            centralComunicacao.registrarMensagem(id, mensagem);
        }
    }

    @Override
    public void receberMensagem(String mensagem) {
        if (!estaLigado()) throw new RoboDesligadoException("Robô está desligado!");
        System.out.println("Mensagem recebida por " + id + ": " + mensagem);
    }

    // Autonomo
    @Override
    public void decidirProximaAcao(Ambiente ambiente) throws RoboDesligadoException {
        if (!estaLigado()) throw new RoboDesligadoException("Robô está desligado!");

        Random rand = new Random();
        int dx = rand.nextInt(3) - 1;
        int dy = rand.nextInt(3) - 1;

        mover(dx, dy, 0);
        System.out.println("RoboTerrestre " + id + " decidiu mover-se para (" + x + "," + y + ")");
    }

    // AlertaRapido
    @Override
    public void dispararAlerta(String mensagem, ArrayList<Entidade> entidades, CentralComunicacao central)
            throws RoboDesligadoException, ErroComunicacaoException {

        if (!estaLigado()) {
            throw new RoboDesligadoException("Robô terrestre " + id + " está desligado e não pode disparar alerta.");
        }

        CentralComunicacao usada = (central != null) ? central : this.centralComunicacao;
        String alerta = "[ALERTA TERRESTRE] " + mensagem;
        System.out.println("RoboTerrestre '" + id + "' enviando: " + alerta);

        for (Entidade e : entidades) {
            if (e instanceof Comunicavel && e != this) {
                Comunicavel destino = (Comunicavel) e;
                if (usada != null) {
                    usada.registrarMensagem(this.id, alerta);
                }
                destino.receberMensagem(alerta);
            }
        }
    }

    // Manobrador
    @Override
    public void realizarManobra(String contexto) {
        if (!estaLigado()) throw new RoboDesligadoException("Robô está desligado!");
        System.out.println("RoboTerrestre " + id + " realizou uma manobra de " + contexto + "!");
    }
}