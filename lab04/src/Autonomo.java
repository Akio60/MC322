// Interface Autonomo
// Tomam decisões sozinhos com base no ambiente
public interface Autonomo {
    void decidirProximaAcao(Ambiente ambiente) throws RoboDesligadoException;
}