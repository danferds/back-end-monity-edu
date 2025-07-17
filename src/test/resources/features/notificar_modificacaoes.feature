# language: pt

Funcionalidade: Notificar Modificações

  Como um aluno inscrito,
    eu quero ser notificado dentro da plataforma sempre que uma monitoria for alterada ou cancelada,
    para que eu não perca nenhuma atualização importante.

  Cenário: Enviar notificação ao aluno após o cancelamento da monitoria
    Dado que a aluna "Letícia Martins" está inscrita na monitoria "Redação - Argumentação"
    E a monitoria está com status "PENDENTE"
    Quando o monitor cancela a monitoria
    Então o sistema deve atualizar o status da monitoria para "CANCELADA"
    E deve enviar uma notificação para ela informando o cancelamento


  Cenário: Enviar notificação ao aluno após alteração na data e no link da monitoria
    Dado que o aluno "Carlos Henrique" está inscrito na monitoria "Química - Ligações Iônicas"
    E a monitoria está com status "PENDENTE"
    Quando o monitor altera a data da monitoria para "2025-07-25"
    E altera o link da reunião para "https://meet.google.com/novo-link"
    Então o sistema deve enviar uma notificação para ele informando as alterações na data e no link

  Cenário: Monitoria modificada não gera notificação por não ter alunos inscritos
    Dado que a monitoria "Geografia - Geopolítica Mundial" está cadastrada
    E não possui nenhum aluno inscrito
    Quando o monitor altera o horário da reunião
    Então o sistema não deve enviar notificações
    E deve registrar que nenhuma notificação foi enviada