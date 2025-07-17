# language: pt

Funcionalidade: Avaliar Monitor

  Como um aluno,
    eu quero avaliar um monitor com um sistema de estrelas após participar de uma monitoria,
    para que eu possa fornecer feedback sobre a qualidade da aula e ajudar outros alunos.


  Cenário: Aluno avalia com suceso o monitor após participar de uma monitoria
    Dado que o aluno "Gabriel Sousa" está autenticado no sistema
    E ele participou da monitoria "Física - Leis de Newton"
    E a monitoria possui status "REALIZADA"
    E Ele ainda não avaliou essa monitoria
    Quando ele envia uma avaliação com nota 4 para o monitor
    Então o sistema deve registrar a avaliação com sucesso
    E associá-la ao aluno, à monitoria correspondente e ao monitor

  Cenário: Aluno tenta avaliar antes da realização da monitoria
    Dado que o aluno "Bruno Silva" está autenticado
    E está inscrito na monitoria "Matemática - Probabilidade"
    E a monitoria ainda está com status "PENDENTE"
    Quando ele tenta enviar uma avaliação
    Então o sistema deve rejeitar a avaliação
    E informar que apenas monitorias realizadas podem ser avaliadas

  Cenário: Aluno tenta enviar uma segunda avaliação para a mesma monitoria
    Dado que o aluno "Daniel Fernandes" está autenticado
    E ele já avaliou a monitoria "Biologia - Genética"
    Quando ele tenta enviar uma nova avaliação
    Então o sistema deve rejeitar a ação
    E informar que só é possível avaliar uma vez por monitoria


  Cenário: Aluno inicia o processo de avaliação, mas desiste antes de confirmar
    Dado que o aluno "Gilvan Fernandes" está autenticado no sistema
    E ele participou da monitoria "História - Revoluções Industriais"
    E a monitoria está com status "REALIZADA"
    E ele ainda não avaliou essa monitoria
    Quando ele inicia o processo de avaliação
    E decide cancelar a ação antes de enviar a nota
    Então o sistema não deve registrar nenhuma avaliação
    E a monitoria deve permanecer sem avaliação por Gabriel
