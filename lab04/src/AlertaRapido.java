import java.util.ArrayList;

// Interface AlertaRapido
// Responsável por disparar alertas rápidos em situações críticas.
public interface AlertaRapido {
    void dispararAlerta(String mensagem, ArrayList<Entidade> entidades, CentralComunicacao central)
        throws RoboDesligadoException, ErroComunicacaoException;
}