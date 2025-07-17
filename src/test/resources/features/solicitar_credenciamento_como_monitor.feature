# language: pt

Funcionalidade: Solicitar Credenciamento como Monitor

  Como um monitor com cadastro pendente,
    eu quero submeter minhas informações acadêmicas (instituição, curso, período e e-mail institucional),
    para que meu perfil seja validado e eu seja aprovado para ministrar monitorias.

  Cenário: Monitor envia todas as informações corretamente e é aprovado
    Dado que o monitor "Daniel Fernandes" está autenticado e possui status "PENDENTE".
    Quando ele informa os dados acadêmicos válidos:
      | Instituição de Ensino | Universidade Federal do Ceará |
      | Curso                 | Engenharia de Software         |
      | Período Atual         | 5º                             |
      | E-mail Institucional  | daniel@alu.ufc.br             |
    Então o sistema deve validar os dados fornecidos.
    E o sistema deve aprovar a solicitação de credenciamento.
    E o status do monitor deve ser atualizado para "APROVADO".

  Cenário: E-mail institucional informado não possui domínio acadêmico válido
    Dado que o monitor "Lucas Pereira" está autenticado e possui status "PENDENTE".
    Quando ele informa os dados com o e-mail institucional "lucasp@gmail.com".
    Então o sistema deve rejeitar a solicitação de credenciamento.
    E o sistema deve informar que o e-mail institucional não possui domínio acadêmico válido.

  Cenário: Monitor deixa de preencher o campo "Curso"
    Dado que a monitora "Lucas Levy" está autenticada e possui status "PENDENTE".
    Quando ele preenche os dados de credenciamento e deixa o campo "Curso" em branco.
    Então o sistema deve rejeitar a solicitação.
    E o sistema deve informar que todos os campos são obrigatórios.

  Cenário: Monitor tenta credenciar-se novamente após já estar aprovado
    Dado que o monitor "João Victor" está autenticado e possui status "APROVADO".
    Quando ele tenta acessar a funcionalidade de solicitação de credenciamento.
    Então o sistema deve bloquear a ação.
    E o sistema deve informar que apenas monitores com status "PENDENTE" podem solicitar credenciamento.