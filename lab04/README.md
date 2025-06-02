# Laboratório 4 - Simulador de Robôs 🤖

## Informações do Projeto
- **IDE**: Visual Studio Code  
- **Java**: OpenJDK 17

## Descrição
Este projeto simula um ambiente tridimensional onde robôs inteligentes interagem, se movem e executam tarefas. Os robôs possuem comportamentos distintos como sensores, comunicação e manobras, organizados por interfaces, herança e polimorfismo. O ambiente é visualizado em 2D (XY), e o sistema trata erros de execução com exceções personalizadas.

---

## Principais Mudanças Neste Laboratório

- Refatoração das classes existentes para implementar **interfaces funcionais**.
- Adição de novas **interfaces**: `Autonomo`, `Manobrador`, `AlertaRapido`.
- Implementação de **herança múltipla via interfaces**.
- Adoção de **exceções personalizadas** para controle de fluxo: `RoboDesligadoException`, `ColisaoException`, `ErroComunicacaoException`, `ForaDosLimitesException`, `AcaoNaoPermitidaException`, `EntidadeNaoEncontradaException`.
- Criação do **menu interativo** com execução de ações com base em polimorfismo.
- Adição da **classe CentralComunicacao** para registrar e exibir mensagens trocadas entre robôs.
- Melhoria da visualização do ambiente com **representação 2D em console**, ignorando a coordenada Z.
- Robôs **não aparecem no mapa** quando estão desligados.

---

## Diagrama de Classes UML Atualizado

![Diagrama UML](A_UML_class_diagram_in_the_image_depicts_a_hierarc.png)  
*(Diagrama gerado com base nos arquivos finais enviados)*

---

## Interfaces Criadas e Onde Foram Implementadas

| Interface       | Descrição                                             | Classes que implementam          |
|----------------|--------------------------------------------------------|----------------------------------|
| `Entidade`      | Contrato mínimo de uma entidade do ambiente           | `Robo`, `Obstaculo`              |
| `Sensoreavel`   | Ações de sensores                                     | `RoboAereo`, `RoboTerrestre`     |
| `Comunicavel`   | Enviar e receber mensagens                            | `RoboAereo`, `RoboTerrestre`     |
| `Autonomo`      | Define decisões automáticas baseadas no ambiente      | `RoboAereo`, `RoboTerrestre`     |
| `AlertaRapido`  | Disparo de alertas para outros robôs                  | `RoboAereo`, `RoboTerrestre`     |
| `Manobrador`    | Executa manobras específicas                          | `RoboAereo`, `RoboTerrestre`     |

---

## Exceções Personalizadas Implementadas

| Exceção                      | Onde é lançada                                                                 |
|-----------------------------|----------------------------------------------------------------------------------|
| `RoboDesligadoException`     | Ao tentar executar uma ação com o robô desligado (`acionarSensores`, `mover`)   |
| `ColisaoException`           | Ao tentar inserir/mover entidade em posição já ocupada (`Ambiente`)            |
| `ErroComunicacaoException`  | Problemas na troca de mensagens (`enviarMensagem`)                              |
| `ForaDosLimitesException`   | Ao tentar mover entidade fora dos limites do ambiente (`Ambiente`)             |
| `AcaoNaoPermitidaException` | Quando uma ação exige interface não implementada (`Ambiente.enviarMensagem`)   |
| `EntidadeNaoEncontradaException` | Ao buscar entidade inexistente no ambiente (`Ambiente.buscarEntidadePorId`) |

---

## Como Compilar e Executar

1. **Navegue até a pasta do projeto:**
   ```bash
   cd lab03
   ```

2. **Compile os arquivos Java:**
   ```bash
   javac -d bin src/*.java
   ```

3. **Execute o programa principal:**
   ```bash
   java -cp bin Main
   ```

---

## Observações Finais

- O sistema utiliza **entrada interativa via Scanner** com menu funcional.
- A movimentação do robô é feita em 3 eixos (X, Y, Z), porém a visualização do ambiente é **2D (XY)**.
- As funcionalidades de cada robô são tratadas via **polimorfismo com interfaces**.
