// Exceção personalizada criada
// Lançada quando um robô tenta acessar uma posição fora dos limites definidos
public class ForaDosLimitesException extends Exception {
    public ForaDosLimitesException(String mensagem) {
        super(mensagem);
    }
}