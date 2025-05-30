# language: pt

Funcionalidade: Visualização da Lista de Monitorias pelo Monitor

  Como um MONITOR,
  Desejo visualizar a lista de todas as minhas monitorias cadastradas em um dashboard,
  Para que eu possa gerenciar e acompanhar as monitorias que estou oferecendo.

  Contexto: Monitor logado na plataforma
    Dado que o monitor "carlos.santos@exemplo.com" está logado na plataforma
    E o monitor navega para o "Dashboard de Monitorias"

  Cenário: Monitor visualiza suas monitorias cadastradas com informações chave
    Dado que o monitor "carlos.santos@exemplo.com" possui as seguintes monitorias cadastradas:
      | Título                               | Data         | Horário Início | Horário Fim | Matéria                 | Status      |
      | "Revisão de Cálculo Vetorial"        | "05/06/2025" | "10:00"        | "11:00"     | "Cálculo II"            | "Agendada"  |
      | "Introdução a Estruturas de Dados"   | "07/06/2025" | "14:00"        | "15:30"     | "Programação Orientada" | "Agendada"  |
      | "Tira-dúvidas de Física Ondulatória" | "02/06/2025" | "09:00"        | "10:00"     | "Física Geral"          | "Realizada" |
    Quando o monitor visualiza a lista de  "Monitorias"
    Então ele deve ver uma lista contendo 3 monitorias
    E para a monitoria "Revisão de Cálculo Vetorial", ele deve ver os detalhes: "05/06/2025", "10:00-11:00", "Cálculo II", além dos botões "Entrar no meet e "Visualizar"
    E para a monitoria "Introdução a Estruturas de Dados", ele deve ver os detalhes: "07/06/2025", "14:00-15:30", "Programação Orientada", além dos botões "Entrar no meet e "Visualizar"
    E para a monitoria "Tira-dúvidas de Física Ondulatória", ele deve ver os detalhes: "02/06/2025", "09:00-10:00", "Física Geral", além dos botões "Entrar no meet e "Visualizar"

  Cenário: Monitor sem monitorias cadastradas visualiza lista vazia
    Dado que o monitor "carlos.santos@exemplo.com" não possui nenhuma monitoria cadastrada
    Quando o monitor visualiza a lista de "Monitorias"
    Então ele deve ver uma mensagem indicando "Não há moitorias cadastradas"
