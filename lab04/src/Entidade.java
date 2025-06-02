//Interface Entidade
//Todas as entidades colocadas no ambiente devem implementar essa interface
public interface Entidade {
    int getX();                // retorna a coordenada X da entidade
    int getY();                // retorna a coordenada Y da entidade
    int getZ();                // retorna a coordenada Z da entidade
    TipoEntidade getTipo();    // retorna o tipo da entidade
    String getDescricao();     // retorna a descrição textual da entidade
    char getRepresentacao();   // retorna o caractere que representa a entidade visualmente
    void setPosicao(int x, int y, int z); // define a posição da entidade no ambiente
}