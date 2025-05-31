# language: pt

Funcionalidade: Avaliar Monitor

  Como ALUNO,
  Desejo avaliar o monitor por meio de estrelas após a monitoria for realizada,
  Para que eu possa expressar meu nível de satisfação.

  Contexto: Aluno logado e monitorias com diferentes status
    Dado que o aluno "carlos.almeida@email.com" está logado na plataforma
    E existe a monitoria "ID_MONITORIA_HIST_REALIZADA" de "História" sobre "Segunda Guerra Mundial" com a monitora "Beatriz", que foi "Realizada" e na qual o aluno "carlos.almeida@email.com" estava inscrito e participou
    E existe a monitoria "ID_MONITORIA_MAT_AGENDADA" de "Matemática" sobre "Logaritmos" com o monitor "Ricardo", que está "Agendada" e na qual o aluno "carlos.almeida@email.com" está inscrito
    E existe a monitoria "ID_MONITORIA_QUIM_CANCELADA" de "Química" sobre "Reações Inorgânicas" com a monitora "Laura", que foi "Cancelada" e na qual o aluno "carlos.almeida@email.com" estava inscrito
    E o aluno "carlos.almeida@email.com" ainda não avaliou a monitora "Beatriz" pela monitoria "ID_MONITORIA_HIST_REALIZADA"

  Cenário: Aluno avalia com sucesso um monitor após uma monitoria realizada
    Quando o aluno acessa o card avaliação da monitoria "ID_MONITORIA_HIST_REALIZADA"
    E o aluno seleciona "5 estrelas" para avaliar a monitora "Beatriz"
    E o aluno clica no botão "Enviar Avaliação"
    Então ele deve ver uma mensagem de sucesso "Avaliação enviada com sucesso!"
    E a avaliação de "5 estrelas" para a monitora "Beatriz" pela monitoria "ID_MONITORIA_HIST_REALIZADA" deve ser registrada no sistema

  Cenário: Aluno tenta avaliar um monitor de uma monitoria que ainda não foi realizada
    Quando o aluno tenta acessar a opção de avaliar o monitor "Ricardo" pela monitoria "ID_MONITORIA_MAT_AGENDADA"
    Então a opção de avaliar não deve estar disponível

  Cenário: Aluno tenta avaliar um monitor de uma monitoria que foi cancelada
    Quando o aluno tenta acessar a opção de avaliar a monitora "Laura" pela monitoria "ID_MONITORIA_QUIM_CANCELADA"
    Então a opção de avaliar não deve estar disponível

  Cenário: Aluno altera uma avaliação previamente submetida para a mesma monitoria
    Dado que o aluno "carlos.almeida@email.com" já avaliou a monitora "Beatriz" pela monitoria "ID_MONITORIA_HIST_REALIZADA" com "3 estrelas"
    Quando o aluno acessa novamente o card de avaliação da monitoria "ID_MONITORIA_HIST_REALIZADA"
    E o aluno altera a avaliação para "4 estrelas" para a monitora "Beatriz"
     o aluno clica no botão "Enviar Avaliação"
    Então ele deve ver uma mensagem de sucesso "Sua avaliação foi atualizada!"
    E a avaliação de "4 estrelas" para a monitora "Beatriz" pela monitoria "ID_MONITORIA_HIST_REALIZADA" deve substituir a avaliação anterior


  Cenário: Aluno visualiza opção de avaliar apenas para monitorias realizadas e em que estava inscrito
    Dado que o aluno "carlos.almeida@email.com" visualiza sua lista de monitorias
    Então para a monitoria "ID_MONITORIA_HIST_REALIZADA", a opção "Avaliar" deve estar visível e habilitada
    E para a monitoria "ID_MONITORIA_MAT_AGENDADA", a opção "Avaliar" deve estar não visível
    E para a monitoria "ID_MONITORIA_QUIM_CANCELADA", a opção "Avaliar Monitor" deve estar não visível