# Laboratório 4 - Simulador de Robôs 

## Informações do Projeto
- **IDE**: Visual Studio Code  
- **Java**: OpenJDK 17

## Descrição
Este projeto simula um ambiente tridimensional onde robôs inteligentes interagem, se movem e executam tarefas. Os robôs possuem comportamentos distintos como sensores, comunicação e manobras, organizados por interfaces, herança e polimorfismo. O ambiente é visualizado em 2D (XY), e o sistema trata erros de execução com exceções personalizadas.

## Principais Mudanças Neste Laboratório
- Refatoração das classes existentes para implementar **interfaces funcionais**.
- Adição de novas **interfaces**: Autonomo, Manobrador, AlertaRapido.
- Implementação de **herança múltipla via interfaces**.
- Adoção de **exceções personalizadas** para controle de fluxo: RoboDesligadoException, ColisaoException, ErroComunicacaoException, ForaDosLimitesException, AcaoNaoPermitidaException, EntidadeNaoEncontradaException.
- Criação do **menu interativo** com execução de ações com base em polimorfismo.
- Adição da **classe CentralComunicacao** para registrar e exibir mensagens trocadas entre robôs.
- Melhoria da visualização do ambiente com **representação 2D em console**, ignorando a coordenada Z.
- Robôs **não aparecem no mapa** quando estão desligados.

## Interfaces Criadas e Onde Foram Implementadas

| Interface       | Descrição                                             | Classes que implementam          |
|----------------|--------------------------------------------------------|----------------------------------|
| Entidade      | Contrato mínimo de uma entidade do ambiente           | Robo, Obstaculo              |
| Sensoreavel   | Ações de sensores                                     | RoboAereo, RoboTerrestre     |
| Comunicavel   | Enviar e receber mensagens                            | RoboAereo, RoboTerrestre     |
| Autonomo      | Define decisões automáticas baseadas no ambiente      | RoboAereo, RoboTerrestre     |
| AlertaRapido  | Disparo de alertas para outros robôs                  | RoboAereo, RoboTerrestre     |
| Manobrador    | Executa manobras específicas                          | RoboAereo, RoboTerrestre     |

---

## Exceções Personalizadas Implementadas

| Exceção                      | Onde é lançada                                                                 |
|-----------------------------|----------------------------------------------------------------------------------|
| RoboDesligadoException     | Ao tentar executar uma ação com o robô desligado (acionarSensores, mover)   |
| ColisaoException           | Ao tentar inserir/mover entidade em posição já ocupada (Ambiente)            |
| ErroComunicacaoException  | Problemas na troca de mensagens (enviarMensagem)                              |
| ForaDosLimitesException   | Ao tentar mover entidade fora dos limites do ambiente (Ambiente)             |
| AcaoNaoPermitidaException | Quando uma ação exige interface não implementada (Ambiente.enviarMensagem)   |
| EntidadeNaoEncontradaException | Ao buscar entidade inexistente no ambiente (Ambiente.buscarEntidadePorId) |

## Como compilar e executar

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="main.SimuladorCLI"
```

## Como rodar os testes

```bash
mvn test
```

## Estrutura

- src/ambiente: Ambiente, obstáculos, tipos de entidade
- src/robo: Robôs, especializações, subsistemas
- src/sensores: Sensores
- src/missao: Missões
- src/comunicacao: Logger e exceções
- src/main: CLI
- test/: Testes JUnit 5
- docs/: Diagrama UML (PlantUML)

## Diagrama UML

Veja o arquivo `docs/diagrama.puml` para o diagrama de classes PlantUML.

