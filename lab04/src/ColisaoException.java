// Exceção personalizada sugerida pelo enunciado
// Lançada quando há tentativa de ocupação de uma posição já ocupada no mapa
public class ColisaoException extends Exception {
    public ColisaoException(String mensagem) {
        super(mensagem);
    }
}