
# LAB03 - Simulador de Robos

## IDE e Java
- IDE: IntelliJ IDEA
- Java: OpenJDK 17

## Descrição
Este repositório contém a implementação de um simulador de robôs, onde diferentes tipos de robôs interagem em um ambiente com obstáculos e sensores.

## Estrutura
- `Ambiente`: define espaço, limites, robôs e obstáculos.
- `Obstaculo` + `TipoObstaculo`: implementa obstáculos e enums.
- `Sensor` + subclasses: implementa sensores de altitude e proximidade.
- `Robo`, `RoboTerrestre`, `RoboAereo`: hierarquia de robôs.
- `Main`: instância e menu interativo.

## Como executar
1. Compile todas as classes: `javac *.java`
2. Execute: `java Main`

## Diagrama de Classes
```plantuml
@startuml
class Ambiente {
  - largura: int
  - altura: int
  - robos: List<Robo>
  - obstaculos: List<Obstaculo>
  + adicionarRobo(r: Robo)
  + removerRobo(r: Robo)
  + dentroDosLimites(x:int,y:int,alt:int):boolean
  + detectarColisao(r: Robo): boolean
}

class Obstaculo {
  - posicaoX1,int
  - posicaoY1,int
  - posicaoX2,int
  - posicaoY2,int
  - altura:int
  - tipo:TipoObstaculo
  + bloqueiaPosicao(x:int,y:int):boolean
}

enum TipoObstaculo { PAREDE, ARVORE, PREDIO, BURACO, OUTRO }

abstract class Sensor {
  - raio:double
  - robo:Robo
  + monitorar(ambiente:Ambiente)
}

class SensorAltitude
class SensorProximidade
Sensor <|-- SensorAltitude
Sensor <|-- SensorProximidade

abstract class Robo {
  - nome:String
  - x:int
  - y:int
  - altitude:int
  - sensores: List<Sensor>
  + mover(dx:int,dy:int,dh:int)
  + usarSensores(a:Ambiente)
}

class RoboTerrestre
class RoboAereo
Robo <|-- RoboTerrestre
Robo <|-- RoboAereo

Ambiente "1" *-- "*" Robo
Ambiente "1" *-- "*" Obstaculo
Robo "1" *-- "*" Sensor
@enduml
```

> **Diagrama**: Utilizei herança para diferenciar tipos de robôs, composição para ambiente-robo/obstáculo e sensores, e enums para tipos de obstáculos.
