public class Main {
    public static void main(String[] args) {
        // Criar ambiente e robôs (definição de parâmetros)
        Ambiente ambiente = new Ambiente(100, 100, 50); // Define o ambiente com dimensões e altura máxima
        RoboTerrestre roboT = new RoboTerrestre("Robô Terrestre", "Norte", 0, 0, 5); 
        RoboAereo roboA = new RoboAereo("Robô Aéreo", "Leste", 0, 0, 30); 
        RoboAereoCaptura roboACaptura = new RoboAereoCaptura("Robô Aéreo de Captura", "Sul", 10, 10, 50, 12); // Robô com câmera
        RoboAereoEntrega roboAEntrega = new RoboAereoEntrega("Robô Aéreo de Entrega", "Oeste", 20, 20, 50, 15); // Robô para entregas
        RoboTerrestreRapido roboTRapido = new RoboTerrestreRapido("Robô Terrestre Rápido", "Norte", 5, 5, 10, 1.5); // Robô com velocidade aumentada
        RoboTerrestreCarga roboTCarga = new RoboTerrestreCarga("Robô Terrestre de Carga", "Leste", 0, 0, 5, 50); // Robô com capacidade de carga
        
        // Adicionar robôs ao ambiente
        ambiente.adicionarRobo(roboT);
        ambiente.adicionarRobo(roboTRapido);
        ambiente.adicionarRobo(roboTCarga);
        ambiente.adicionarRobo(roboA);
        ambiente.adicionarRobo(roboACaptura);
        ambiente.adicionarRobo(roboAEntrega);
        
        // Testar movimentações
        roboT.mover(2, 3); // Movimenta o robô terrestre
        roboT.exibirPosicao(); // Exibe a posição atual do robô terrestre
        
        roboA.subir(30); // Sobe o robô aéreo
        roboA.mover(5, 5); // Movimenta o robô aéreo
        roboA.exibirPosicao(); // Exibe a posição atual do robô aéreo

        roboACaptura.subir(20); // Sobe o robô aéreo de captura
        roboACaptura.capturarImagem(); // Captura uma imagem com o robô aéreo de captura

        roboAEntrega.subir(15); // Sobe o robô aéreo de entrega
        roboAEntrega.entregarPacote(); // Realiza a entrega de um pacote

        roboTRapido.mover(3, 2); // Movimenta o robô terrestre rápido com multiplicador de velocidade
        roboTRapido.exibirPosicao(); // Exibe a posição atual do robô terrestre rápido

        roboTCarga.setCargaAtual(30); // Define a carga atual do robô terrestre de carga
        roboTCarga.mover(2, 2); // Movimenta o robô terrestre de carga
        roboTCarga.exibirPosicao(); // Exibe a posição atual do robô terrestre de carga
    }
}