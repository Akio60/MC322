// Exceção personalizada criada
// Quando robô ou sensor tentar realizar uma ação não permitida naquele estado
public class AcaoNaoPermitidaException extends Exception {
    public AcaoNaoPermitidaException(String mensagem) {
        super(mensagem);
    }
}