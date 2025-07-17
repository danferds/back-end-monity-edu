# language: pt

Funcionalidade: Cancelar Inscrição em Monitorias

  Como um aluno,
    eu quero poder cancelar minha inscrição em uma monitoria,
    para que eu tenha flexibilidade para gerenciar minha agenda caso ocorra um imprevisto.


  Cenário: Aluno cancela inscrição em uma monitoria com sucesso
    Dado que o aluno "Marcos Oliveira" está autenticado no sistema
    E está inscrito na monitoria "Física - Movimento Uniforme"
    E a monitoria está com status "PENDENTE"
    Quando Marcos solicita o cancelamento da inscrição
    Então o sistema deve removê-lo da lista de inscritos da monitoria
    E confirmar o cancelamento da inscrição

  Cenário: Aluno desiste do cancelamento da inscrição antes de confirmar
    Dado que o aluno "Marcos Oliveira" está autenticado no sistema
    E está inscrito na monitoria "Matemática - Progressões"
    Quando ele inicia o processo de cancelamento da inscrição
    E decide cancelar a operação antes de confirmar
    Então o sistema deve manter o aluno inscrito na monitoria
    E não deve realizar nenhuma alteração

  Cenário: Aluno tenta cancelar inscrição em monitoria já realizada
    Dado que o aluno "Marcos Oliveira" está autenticado no sistema
    E está inscrito na monitoria "História - Idade Média"
    E a monitoria está com status "REALIZADA"
    Quando Marcos solicita o cancelamento da inscrição
    Então o sistema deve rejeitar o cancelamento
    E informar que não é possível cancelar inscrições em monitorias já realizadas