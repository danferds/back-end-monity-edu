# language: pt

Funcionalidade: Realizar Monitoria

  Como um monitor,
    eu quero registrar que uma monitoria foi realizada,
    para que o sistema atualize o status e poder gerar certificados.


  Cenário: Realização de monitoria com sucesso
    Dado que o monitor "Daniel Lima" está autenticado
    E possui uma monitoria com ID "MON123" em status "PENDENTE"
    E a data e hora da monitoria já passaram
    Quando ele solicita que a monitoria "MON123" seja marcada como "REALIZADA"
    Então o sistema deve alterar o status da monitoria para "REALIZADA"
    E registrar a data e hora da confirmação

  Cenário: Tentativa de realizar monitoria antes da data/hora prevista
    Dado que a monitora "Ana Paula" está autenticada
    E possui uma monitoria com ID "MON456" agendada para uma data futura
    Quando ela tenta marcar a monitoria como "REALIZADA"
    Então o sistema deve rejeitar a ação
    E informar que a monitoria só pode ser marcada como "REALIZADA" após a data/hora de realização

  Cenário: Tentativa de realizar monitoria  monitoria cancelada
    Dado que o monitor "Fernando Diniz" está autenticado
    E possui uma monitoria com ID "MON789" com status "CANCELADA"
    Quando ele tenta marcar a monitoria como "REALIZADA"
    Então o sistema deve rejeitar a ação
    E informar que monitorias canceladas não podem ser marcadas como realizadas