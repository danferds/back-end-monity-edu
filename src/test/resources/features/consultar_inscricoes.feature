# language: pt

Funcionalidade: Consultar Inscrições

  Como um aluno,
    eu quero visualizar uma lista de todas as monitorias em que estou inscrito (pendentes e realizadas),
    para que eu possa organizar minha agenda de estudos.


  Cenário: Aluno visualiza todas as monitorias em que está inscrito
    Dado que o aluno "Lucas Andrade" está autenticado no sistema
    E está inscrito nas seguintes monitorias:
      | Título                        | Status     | Data       |
      | Química - Ligações Químicas  | PENDENTE   | 2025-07-20 |
      | Biologia - Ecossistemas      | REALIZADA  | 2025-07-10 |
    Quando Lucas solicita a consulta de todas as suas inscrições
    Então o sistema deve retornar as duas monitorias listadas
    E deve categorizá-las como Próximas e Anteriores com base no status e data

  Cenário: Aluno filtra inscrições por monitorias realizadas
    Dado que o aluno "Hugo Moura" está autenticado
    E está inscrito em monitorias com status "REALIZADA" e "PENDENTE"
    Quando ele filtra suas inscrições por status “REALIZADA”
    Então o sistema deve exibir apenas as monitorias com status “REALIZADA”

  Cenário: Aluno filtra monitorias por data e matéria
    Dado que o aluno "Lucas levy" está autenticado
    E está inscrito nas monitorias "Física - Cinemática" em 2025-07-22 e "Matemática - Geometria" em 2025-07-23
    Quando ele aplica os filtros: matéria = "Física" e data = "2025-07-22"
    Então o sistema deve exibir apenas a monitoria "Física - Cinemática"

  Cenário: Aluno sem inscrições tenta consultar sua lista
    Dado que o aluno "Paulo Santos" está autenticado
    E não possui nenhuma inscrição em monitorias
    Quando ele solicita a consulta de suas inscrições
    Então o sistema deve informar que nenhuma inscrição foi encontrada
