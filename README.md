# Sistema de Gerenciamento de Estacionamento

## Sobre o Projeto
Este é um sistema robusto de gerenciamento de estacionamento desenvolvido inteiramente em **Java**. O principal objetivo deste projeto é aplicar e demonstrar conceitos aprofundados de **Programação Orientada a Objetos (POO)** na modelagem de um problema do mundo real.

O sistema opera via terminal (CLI) e é dividido em dois módulos principais: **Atendimento** (operações do dia a dia, como entrada e saída de veículos) e **Gestão** (controle de vagas, tarifas, clientes e relatórios).

## Funcionalidades

### Módulo de Atendimento
* **Entrada de Veículos:** Alocação automática em vagas compatíveis com o tipo do veículo (Moto, Automóvel, Utilitário).
* **Saída e Faturamento:** Cálculo automático da tarifa com base no tempo de permanência e na categoria do veículo.
* **Cadastro Rápido:** Identificação de placas (padrão Mercosul) e vínculo imediato a um cliente durante a entrada.

### Módulo de Gestão
* **Controle de Vagas:** Cadastro, exclusão e alteração de estado (Livre, Ocupado, Indisponível) e tipo das vagas.
* **Gestão de Clientes e Veículos:** CRUD completo de clientes (validação de CPF) e edição de dados de veículos associados.
* **Relatórios e Consultas:** Geração de relatórios de veículos no dia, veículos estacionados no momento e buscas por intervalos de data e hora.
* **Configuração de Taxas:** Ajuste dinâmico de valores cobrados por hora e por categoria de veículo.

## Conceitos de POO Aplicados
Este projeto foi estruturado para manter o baixo acoplamento e a alta coesão, utilizando:

* **Encapsulamento Avançado:** Uso intenso de construtores de cópia (ex: `Cliente(Cliente cliente)`) para retornar cópias dos objetos em vez de suas referências originais, protegendo a integridade dos dados internos.
* **Padrão de Criação (Factory Method):** Implementação de métodos estáticos `getInstance()` nas classes de modelo para centralizar e controlar a instanciação de objetos.
* **Associações e Agregações:** Relacionamento bem definido de 1 para N (Um Cliente possui N Veículos) e agregação na classe `Estacionamento` (que une `Veiculo`, `Vaga` e `Data` para formar um registro único).
* **Tipos Enumerados (Enums):** Utilização de Enums (`Tipo`, `EstadoVaga`) para garantir a segurança de tipo e padronizar categorias dentro do sistema.

## Como Executar na Sua Máquina

Para rodar este projeto, você precisará ter o [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado.

1. Clone este repositório:
   ```bash
   git clone https://github.com/AndreGuilherme404/EstacionamentoPOO.git
2. Navegue até a pasta src do projeto
   ```bash
   cd NOME_DO_REPOSITORIO/src
3. Compile os arquivos Java (no Windows/Prompt de Comando):
   ```bash
   javac *.java
4. Execute o sistema:
   ```bash
   java Main
