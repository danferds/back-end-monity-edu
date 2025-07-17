# language: pt

Funcionalidade: Inscrever em Monitorias

  Como um aluno,
    eu quero poder me inscrever em uma monitoria pendente,
    para que eu possa garantir minha vaga e participar da aula.

  Cenário: Aluno realiza sua inscrição com sucesso
    Dado que o aluno "João Silva" está autenticado no sistema
    E a monitoria "Química - Ligações Iônicas" está com status "PENDENTE"
    E João não está inscrito nesta monitoria
    Quando João solicita a inscrição na monitoria
    Então o sistema deve adicioná-lo à lista de inscritos da monitoria
    E o sistema deve confirmar a inscrição com sucesso

  Cenário: Aluno já inscrito tenta se inscrever novamente na mesma monitoria
    Dado que o aluno "Daniel Fernandes" está autenticado no sistema
    E a monitoria "Matemática - Equações o 2º Grau" está com status "PENDENTE"
    E ele já está inscrito nesta monitoria
    Quando Ele solicita a inscrição na monitoria
    Então o sistema deve rejeitar a inscrição
    E deve informar que já está inscrito nesta monitoria

  Cenário: Aluno tenta se inscrever em monitoria com status REALIZADA
    Dado que o aluno "Paulo" está autenticado no sistema
    E a monitoria "História - Revolução Francesa" está com status "REALIZADA"
    Quando Ele solicita a inscrição na monitoria
    Então o sistema deve rejeitar a inscrição
    E deve informar que a inscrição não é permitida para monitorias já realizadas