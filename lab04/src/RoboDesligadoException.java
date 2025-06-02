// Exceção personalizada sugerida pelo enunciado
// Lançada quando um robô tenta executar uma ação sem estar ligado
public class RoboDesligadoException extends Exception {
    public RoboDesligadoException(String mensagem) {
        super(mensagem);
    }
}
