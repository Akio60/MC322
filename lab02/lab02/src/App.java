public class App {
    public static void main(String[] args) {
        // Criar ambiente e robôs
        Ambiente ambiente = new Ambiente(100, 100, 50);
        
        RoboTerrestre roboT = new RoboTerrestre("RT1", "Norte", 0, 0, 5);
        RoboAereo roboA = new RoboAereo("RA1", "Leste", 0, 0, 30);
        
        // Adicionar robôs ao ambiente
        ambiente.adicionarRobo(roboT);
        ambiente.adicionarRobo(roboA);
        
        // Testar movimentações
        roboT.mover(3, 4);
        roboT.exibirPosicao();
        
        roboA.subir(20);
        roboA.mover(5, 5);
        roboA.exibirPosicao();
    }
}
