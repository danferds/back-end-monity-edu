# language: pt

Funcionalidade: Notificar Modificação em Monitoria:

  Como ALUNO,
  Desejo ser notificado sempre que houver uma modificação em uma monitoria na qual estou inscrito,
  Para que eu esteja sempre atualizado sobre mudanças.

  Contexto: Aluno inscrito em uma monitoria que será modificada
    Dado que o aluno "laura.gomes@email.com" está logado na plataforma
    E o aluno "laura.gomes@email.com" está inscrito na monitoria "ID_MON_FIS_MOD" de "Física" sobre "Eletrodinâmica - Circuitos Simples", agendada para "10/11/2025" das "14:00" às "15:00", com link "meet.google.com/original" e sem materiais.
    E existe outro aluno "marcos.silva@email.com" que não está inscrito na monitoria "ID_MON_FIS_MOD"
    E o aluno "laura.gomes@email.com" também está inscrito na monitoria "ID_MON_QUIM_INTACTA" de "Química" sobre "Funções Inorgânicas", que não sofrerá alterações.

  Cenário: Aluno é notificado quando a data de uma monitoria inscrita é alterada
    Quando o monitor altera a data da monitoria "ID_MON_FIS_MOD" para "12/11/2025"
    Então o aluno "laura.gomes@email.com" deve receber uma notificação
    E a notificação deve informar que a monitoria "Eletrodinâmica - Circuitos Simples" teve sua data alterada para "12/11/2025"

  Cenário: Aluno é notificado quando o horário de uma monitoria inscrita é alterado
    Quando o monitor altera o horário da monitoria "ID_MON_FIS_MOD" para "16:00" às "17:00"
    Então o aluno "laura.gomes@email.com" deve receber uma notificação
    E a notificação deve informar que a monitoria "Eletrodinâmica - Circuitos Simples" teve seu horário alterado para "16:00 - 17:00"

  Cenário: Aluno é notificado quando o link da reunião de uma monitoria inscrita é alterado
    Quando o monitor altera o link da reunião da monitoria "ID_MON_FIS_MOD" para "meet.google.com/novo_link"
    Então o aluno "laura.gomes@email.com" deve receber uma notificação
    E a notificação deve informar que o link da reunião da monitoria "Eletrodinâmica - Circuitos Simples" foi atualizado

  Cenário: Aluno é notificado quando a monitoria inscrita é cancelada
    Quando o monitor cancela a monitoria "ID_MON_FIS_MOD"
    Então o aluno "laura.gomes@email.com" deve receber uma notificação
    E a notificação deve informar que a monitoria "Eletrodinâmica - Circuitos Simples" foi cancelada

  Cenário: Aluno é notificado quando materiais complementares são adicionados a uma monitoria inscrita
    Quando o monitor adiciona o material "lista_exercicios_circuitos.pdf" à monitoria "ID_MON_FIS_MOD"
    Então o aluno "laura.gomes@email.com" deve receber uma notificação
    E a notificação deve informar que novos materiais foram adicionados à monitoria "Eletrodinâmica - Circuitos Simples"

  Cenário: Aluno não inscrito NÃO recebe notificação sobre modificação da monitoria
    Quando o monitor altera o tópico da monitoria "ID_MON_FIS_MOD" para "Análise de Circuitos Complexos"
    Então o aluno "laura.gomes@email.com" deve receber uma notificação sobre a mudança de tópico
    Mas o aluno "marcos.silva@email.com" NÃO deve receber nenhuma notificação referente à monitoria "ID_MON_FIS_MOD"
