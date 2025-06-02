// Exceção personalizada sugerida pelo enunciado
// Lançada quando há um erro na comunicação
public class ErroComunicacaoException extends Exception {
    public ErroComunicacaoException(String mensagem) {
        super(mensagem);
    }
}