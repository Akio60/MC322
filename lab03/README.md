# Laboratório 3 - Simulador de Robôs 🤖

## Informações do Projeto
- **IDE:** Visual Studio Code
- **Java:** OpenJDK 17

## Descrição
Este projeto implementa um simulador de robôs com diferentes tipos de sensores e capacidades, utilizando conceitos de orientação a objetos como herança, polimorfismo e composição.

## Como Executar
1. Navegue até o diretório `lab03/src`
2. Compile os arquivos Java:
   ```bash
   javac -d bin *.java
   ```
3. Execute o programa principal:
   ```bash
   java -cp bin lab03.src.Main
   ```

## Diagrama de Classes
![Diagrama de Classes](diagrama_classes.png)

### Principais Classes e Relacionamentos

#### Hierarquia de Robôs
- `Robo` (abstrata)
  - Atributos: nome (String), x/y (int), altitude (int), sensores (ArrayList<Sensor>)
  - Métodos principais: mover(dx,dy,dh), usarSensores(ambiente)
- `RoboTerrestre` e `RoboAereo` (herdam de Robo)
  - Implementam comportamentos específicos de movimento

#### Sensores e Estratégias
- `Sensor` (abstrata)
  - Atributos: raio (double), robo (Robo)
  - Método abstrato: monitorar(ambiente)
- Tipos específicos: `SensorTerreno`, `SensorNavegacao`, `SensorTatico`
  - Cada um implementa sua própria estratégia de monitoramento

#### Ambiente e Obstáculos
- `Ambiente`
  - Gerencia robôs e obstáculos
  - Verifica limites e colisões
- `Obstaculo`
  - Representa elementos do ambiente
  - Associado a um `TipoObstaculo`

### Padrões de Projeto Utilizados
1. **Strategy**: Implementado nos diferentes tipos de sensores
2. **Template Method**: Na hierarquia de robôs
3. **Composite**: Na relação entre robôs e sensores
4. **Value Object**: Na enumeração de tipos de obstáculos

### Linha de Raciocínio
O diagrama foi construído pensando em maximizar a reutilização de código e flexibilidade:

1. **Robôs e Sensores**: Relação de composição permite que robôs tenham múltiplos sensores
2. **Hierarquia de Sensores**: Permite adicionar novos tipos de sensores facilmente
3. **Ambiente como Controlador**: Centraliza a lógica de verificação de limites e colisões
4. **Obstáculos como Entidades**: Encapsula diferentes tipos de obstáculos com comportamentos específicos

### Principais Decisões de Design
1. Uso de classes abstratas para `Robo` e `Sensor` para garantir implementação de comportamentos essenciais
2. Composição para sensores permite maior flexibilidade que herança
3. Enumeração para tipos de obstáculos facilita manutenção e evita strings mágicas
4. Interface limpa entre componentes permite fácil extensão do sistema
