import java.util.ArrayList;

/**
 * Classe que representa o ambiente da simulação.
 * Gerencia robôs e obstáculos através de composição.
 * Responsável por verificar limites e colisões.
 */
public class Ambiente {
    // Dimensões do ambiente
    private final int largura;
    private final int profundidade;
    private final int altura;

    private ArrayList<Entidade> entidades;  // Polimorfismo 
    private TipoEntidade[][][] mapa;        // Mapa tridimensional do ambiente

    public Ambiente(int largura, int profundidade, int altura) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.altura = altura;
        this.entidades = new ArrayList<>();
        this.mapa = new TipoEntidade[largura][profundidade][altura];
        inicializarMapa();
    }

    // Métodos principais
    public void inicializarMapa() {
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < profundidade; y++) {
                for (int z = 0; z < altura; z++) {
                    mapa[x][y][z] = TipoEntidade.VAZIO; // Inicializa o mapa como vazio
                }
            }
        }
    }

    public void adicionarEntidade(Entidade e) throws ColisaoException, ForaDosLimitesException {
        if (!dentroDosLimites(e.getX(), e.getY(), e.getZ())) 
            throw new ForaDosLimitesException("Entidade fora dos limites do ambiente");
        if (estaOcupado(e.getX(), e.getY(), e.getZ()))
            throw new ColisaoException("Posição já ocupada por outra entidade");
        entidades.add(e);                                  // Adiciona a entidade à lista
        mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo(); // Atualiza o mapa
    }

    public void removerEntidade(Entidade e) {
       if (entidades.remove(e)){
           mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO; // Atualiza o mapa
       }
    }

    /**
     * Verifica se uma posição está dentro dos limites do ambiente.
     * Usado para validar movimentações de robôs.
     */

    public boolean dentroDosLimites(int x, int y, int z) {
        return x >= 0 && x < largura && 
            y >= 0 && y < profundidade &&
            z >= 0 && z < altura;
    }

    public boolean estaOcupado(int x, int y, int z) {
        return mapa[x][y][z] != TipoEntidade.VAZIO;
    }

    public void moverEntidade(Entidade e, int novoX, int novoY, int novoZ) throws ColisaoException, ForaDosLimitesException {
        if (!dentroDosLimites(novoX, novoY, novoZ)) 
            throw new ForaDosLimitesException("Nova posição fora dos limites do ambiente");
        
        if (estaOcupado(novoX, novoY, novoZ)) 
            throw new ColisaoException("Nova posição já ocupada por outra entidade");
        
        removerEntidade(e);                // Remove da posição atual
        e.setPosicao(novoX, novoY, novoZ); // Atualiza a posição da entidade
        adicionarEntidade(e);               // Adiciona na nova posição
    }

    public void executarSensores(){
        for (Entidade e : entidades) {
            if (e instanceof Sensoreavel) {
                try {
                    ((Sensoreavel) e).acionarSensores();
                } catch (RoboDesligadoException ex) {
                    System.out.println("Erro ao executar sensores: " + ex.getMessage());
                }
            }
        }
    }

    public void verificarColisoes() throws ColisaoException {
        for (int i=0; i< entidades.size(); i++) {
            Entidade a = entidades.get(i);
            for (int j=i+1; j< entidades.size(); j++) {
                Entidade b = entidades.get(j);
                if (a.getX() == b.getX() &&
                    a.getY() == b.getY() &&
                    a.getZ() == b.getZ()) {
                    throw new ColisaoException("Colisão detectada entre entidades na posição" + 
                    a.getX() + "," + a.getY() + "," + a.getZ());
                }
            }
        }
    }

    public void visualizarAmbiente() {
        System.out.println("== VISÃO DO AMBIENTE (XY) ==");

        final int CELULA_LARGURA = 3;
        String[][] grid = new String[profundidade][largura];
        ArrayList<String> idsVoando = new ArrayList<>();

        // Inicializa o grid com "."
        for (int y = 0; y < profundidade; y++) {
            for (int x = 0; x < largura; x++) {
                grid[y][x] = String.format("%-" + CELULA_LARGURA + "s", ".");
            }
        }

        // Preenche o grid com IDs
        for (Entidade e : entidades) {
            // Ignora robôs desligados
            if (e instanceof Robo r && !r.estaLigado()) continue;

            int x = e.getX() - 1;  // 🟡 Subtrai 1 para alinhar visual com indexação 1-based
            int y = e.getY() - 1;
            int z = e.getZ();

            if (x >= 0 && x < largura && y >= 0 && y < profundidade) {
                String id = (e instanceof Robo r2) ? r2.getId() : String.valueOf(e.getRepresentacao());
                if (id.length() > CELULA_LARGURA - 1) {
                    id = id.substring(0, CELULA_LARGURA - 1);
                }
                grid[y][x] = String.format("%-" + CELULA_LARGURA + "s", id);

                if (e instanceof Robo r3 && z > 0 && !idsVoando.contains(r3.getId())) {
                    idsVoando.add(r3.getId());
                }
            }
        }

        // Imprimir o grid linha por linha
        for (int y = 0; y < profundidade; y++) {
            for (int x = 0; x < largura; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }

        if (!idsVoando.isEmpty()) {
            System.out.println("\nRobôs voando: " + String.join(", ", idsVoando));
        }
    }

    public char getRepresentacao(int x, int y, int z) {
        for (Entidade e : entidades) {
            if (e.getX() == x && e.getY() == y && e.getZ() == z) {
                return e.getRepresentacao();
            }
        }
        return '.';
    }

    public ArrayList<Entidade> getEntidades() {
        return entidades;
    }

    //  Método para uso da exceção de EntidadeNaoEncontradaException
    public Entidade buscarEntidadePorId(String id) throws EntidadeNaoEncontradaException {
    for (Entidade e : entidades) {
        if (e instanceof Robo robo) {
            if (robo.getId().equals(id)) {
                return robo;
            }
        }
    }
    throw new EntidadeNaoEncontradaException("Entidade com ID '" + id + "' não foi encontrada.");
    }

    // Método para uso da exceção de AcaoNaoPermitidaException, ErroComunicacaoException e EntidadeNaoEncontradaException
    public void enviarMensagemEntreEntidades(String idOrigem, String idDestino, String mensagem)
        throws EntidadeNaoEncontradaException, AcaoNaoPermitidaException, ErroComunicacaoException {

    Entidade origem = buscarEntidadePorId(idOrigem);
    Entidade destino = buscarEntidadePorId(idDestino);

    if (!(origem instanceof Comunicavel) || !(destino instanceof Comunicavel)) {
        throw new AcaoNaoPermitidaException("Uma das entidades não é comunicável.");
    }

    try {
        ((Comunicavel) origem).enviarMensagem((Comunicavel) destino, mensagem);
    } catch (Exception e) {
        throw new ErroComunicacaoException("Erro ao enviar mensagem: " + e.getMessage());
    }
    }

    public ArrayList<Obstaculo> getObstaculos() {
        ArrayList<Obstaculo> obs = new ArrayList<>();
        for (Entidade e : entidades) {
            if (e instanceof Obstaculo) {
                obs.add((Obstaculo) e);
            }
        }
        return obs;
    }

    public ArrayList<Robo> getRobos() {
        ArrayList<Robo> robos = new ArrayList<>();
        for (Entidade e : entidades) {
            if (e instanceof Robo r) {
                robos.add(r);
            }
        }
        return robos;
    }
}