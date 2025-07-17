# language: pt

Funcionalidade: Consultar Lista de Monitorias

  Como um monitor,
    eu quero visualizar uma lista de todas as minhas monitorias e poder filtrar por status, data ou tópico,
    para que eu possa gerenciar e organizar minhas atividades de forma eficiente.

  Cenário: Monitor consulta sua lista de monitorias sem filtros
    Dado que o monitor "Vitor Pereira" está autenticado no sistema
    E possui três monitorias cadastradas
    Quando ele solicita a lista de todas as suas monitorias
    Então o sistema deve retornar os dados das três monitorias
    E deve apresentá-las ordenadas da mais recente para a mais antiga

  Cenário: Monitor consulta monitorias filtrando por tópico e status
    Dado que a monitora "Ana Paula" está autenticada no sistema
    E possui cinco monitorias cadastradas
    Quando ela solicita a consulta com os filtros:
      | Tópico | Programação Orientada a Objetos |
      | Status | PENDENTE                        |
    Então o sistema deve retornar apenas as monitorias com esse tópico e status
    E deve apresentá-las da mais recente para a mais antiga

  Cenário: Monitor realiza consulta e nenhum resultado é encontrado
    Dado que o monitor "Coutinho" está autenticado no sistema
    E possui monitorias apenas com status "REALIZADA"
    Quando ele solicita a consulta com o filtro:
      | Status | CANCELADA |
    Então o sistema deve informar que nenhum resultado foi encontrado
