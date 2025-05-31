# language: pt

Funcionalidade: Visualização da Lista de Monitorias Inscritas pelo Aluno

  Como ALUNO,
  Desejo visualizar em meu dashboard a lista de monitorias nas quais estou inscrito,
  Para que eu possa acompanhar minhas aulas agendadas e acessar rapidamente as informações necessárias.

  Contexto: Aluno logado na plataforma
    Dado que o aluno "pedro.costa@email.com" está logado na plataforma
    E o aluno "pedro.costa@email.com" está em seu "Dashboard do Aluno"

  Cenário: Aluno visualiza suas monitorias inscritas com informações chave
    Dado que o aluno "pedro.costa@email.com" está inscrito nas seguintes monitorias:
      | Título Monitoria                        | Data         | Horário Início | Horário Início |Matéria        | Monitor        | Status      | Link Meet Visível |
      | "Revisão de Funções Trigonométricas"    | "10/09/2025" | "15:00"        |  "16:00"       |"Matemática"   | "Ana Lima"     | "Agendada"  | sim               |
      | "Introdução à Termoquímica"             | "12/09/2025" | "10:00"        |  "09:00"       | "Química"     | "Carlos Neves" | "Agendada"  | sim               |
    Quando o aluno visualiza a seção "Monitorias Inscritas"
    Então ele deve ver uma lista contendo 2 monitorias
    E para a monitoria "Revisão de Funções Trigonométricas", ele deve ver os detalhes: "Revisão de Funções Trigonométricas", "10/09/2025", "15:00-16:00", "Matemática", e os botões "Entrar no Meet" e "Visualizar"
    E para a monitoria "Introdução à Termoquímica", ele deve ver os detalhes: "Introdução à Termoquímica", "12/09/2025", "10:00-09:00", "Química", e os botões "Entrar no Meet" e "Visualizar"

  Cenário: Aluno sem monitorias inscritas visualiza lista vazia
    Dado que o aluno "pedro.costa@email.com" não está inscrito em nenhuma monitoria
    Quando o aluno visualiza a seção "Monitorias Inscritas"
    Então ele deve ver uma mensagem indicando "Você não está inscrito em nenhuma monitoria no momento."

  Cenário: Aluno visualiza monitoria cancelada na sua lista de inscritas
    Dado que o aluno "pedro.costa@email.com" estava inscrito na monitoria "ID_MONITORIA_FIS_CANCELADA" de "Física" sobre "Óptica Geométrica" que foi cancelada pelo monitor
    Quando o aluno visualiza a seção "Monitorias Inscritas"
    Então ele deve ver a monitoria "ID_MONITORIA_FIS_CANCELADA" listada com o status "Cancelada"
    E o link do Meet para "ID_MONITORIA_FIS_CANCELADA" não deve estar ativo

  Cenário: Acesso rápido aos detalhes completos da monitoria a partir da lista
    Dado que o aluno "pedro.costa@email.com" está inscrito na monitoria "ID_MONITORIA_BIO_ECO" de "Biologia" sobre "Ecossistemas Brasileiros"
    Quando o aluno visualiza a monitoria "ID_MONITORIA_BIO_ECO" na seção "Minhas Monitorias Inscritas"
    E ele clica no botão "Visualizar" da monitoria "ID_MONITORIA_BIO_ECO"
    Então ele deve ser redirecionado para a página de detalhes completos da monitoria "ID_MONITORIA_BIO_ECO"
    E ele deve poder visualizar todas as informações da monitoria
