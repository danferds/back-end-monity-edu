# language: pt

Funcionalidade: Inscrição em Monitorias

  Como ALUNO,
  Desejo me inscrever em monitorias disponíveis na plataforma,
  Para que eu possa participar das aulas e acessar o conteúdo disponibilizado pelos monitores.

  Contexto: Aluno logado e monitorias disponíveis
    Dado que o aluno "maria.silva@email.com" está logado na plataforma
    E existe uma monitoria "ID_MONITORIA_MAT_DISP" sobre "Análise Combinatória" de "Matemática" para o dia "10/09/2025" às "14:00" com o monitor "Carlos Andrade", que está disponível e com vagas
    E existe uma monitoria "ID_MONITORIA_QUIM_INSCRITA" sobre "Estequiometria" de "Química" para o dia "12/09/2025" às "10:00" com a monitora "Ana Pereira"
    E existe uma monitoria "ID_MONITORIA_FIS_PASSADA" sobre "Cinemática" de "Física" que ocorreu no dia "01/05/2025" às "15:00" com o monitor "João Antunes"

  Cenário: Aluno se inscreve com sucesso em uma monitoria disponível
    Dado que o aluno "maria.silva@email.com" não está inscrito na monitoria "ID_MONITORIA_MAT_DISP"
    Quando o aluno visualiza os detalhes da monitoria "ID_MONITORIA_MAT_DISP"
    E o aluno clica no botão "Inscrever-se"
    Então ele deve ver uma mensagem de sucesso "Inscrição realizada com sucesso!"
    E o aluno "maria.silva@email.com" deve estar listado como inscrito na monitoria "ID_MONITORIA_MAT_DISP"
    E o link do Meet deve ficar visível para o aluno

  Cenário: Aluno tenta se inscrever em uma monitoria na qual já está inscrito
    Dado que o aluno "maria.silva@email.com" já está inscrito na monitoria "ID_MONITORIA_QUIM_INSCRITA"
    Quando o aluno visualiza os detalhes da monitoria "ID_MONITORIA_QUIM_INSCRITA"
    Então o botão "Inscrever-se" deve estar desabilitado

  Cenário: Aluno tenta se inscrever em uma monitoria que já ocorreu
    Quando o aluno visualiza os detalhes da monitoria "ID_MONITORIA_FIS_PASSADA"
    Então o botão "Inscrever-se" não deve estar visível
