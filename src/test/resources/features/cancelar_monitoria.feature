# language: pt

Funcionalidade: Cancelar Monitoria

  Como um monitor,
    eu quero cancelar uma monitoria pendente,
    para que eu possa gerenciar imprevistos e notificar os alunos inscritos.

  Cenário: Cancelamento de monitoria com sucesso
    Dado que o monitor "Roberto Dinamite" está autenticado
    E possui uma monitoria cadastrada com ID "MON789" e status "PENDENTE"
    Quando ele solicita o cancelamento da monitoria "MON789"
    Então o sistema deve alterar o status da monitoria para "CANCELADA"
    E registrar a data e hora do cancelamento

  Cenário: Monitor tenta cancelar uma monitoria com status "REALIZADA"
    Dado que a monitora "Ana Paula" está autenticada
    E possui uma monitoria com ID "MON102" e status "REALIZADA"
    Quando ela tenta cancelar a monitoria "MON102"
    Então o sistema deve rejeitar a solicitação
    E informar que monitorias realizadas não podem ser canceladas

  Cenário: Monitor inicia o cancelamento, mas desiste antes de confirmar
    Dado que a monitora "Ana Paula" está autenticada
    E possui uma monitoria cadastrada com ID "MON333" e status "PENDENTE"
    Quando ela inicia o processo de cancelamento da monitoria "MON333"
    E decide cancelar a operação antes de confirmar a exclusão
    Então o sistema deve manter o status da monitoria como "PENDENTE"
    E encerrar o processo de cancelamento sem alterações