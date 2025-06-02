// Exceção personalizada criada
// Lançada quando o sistema tentar buscar uma entidade e não encontra
// Ex: Robô excluído
public class EntidadeNaoEncontradaException extends Exception {
    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}