// Interface Manobrador
// Utilizado para manobras complexas com vários movimentos
public interface Manobrador {
    void realizarManobra(String tipo) throws RoboDesligadoException;
}