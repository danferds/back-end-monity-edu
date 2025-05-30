# language: pt

Funcionalidade: Visualização, Edição e Cancelamento de Monitoria

  Como um MONITOR,
  Desejo visualizar e editar as informações de uma monitoria cadastrada,
  Como título, data, horário, matéria, tópico, descrição e materiais complementares,
  Além de poder cancelar a monitoria,
  Para que eu mantenha as informações atualizadas e organizadas para os alunos.

  Contexto: Monitor logado e com uma monitoria selecionada
    Dado que o monitor "fernanda.lima@exemplo.com" está logado na plataforma
    E o monitor "fernanda.lima@exemplo.com" cadastrou a monitoria "ID_MONITORIA_1" com os seguintes dados:
      | Campo                    | Valor                                               |
      | Título                   | "Álgebra Linear - O básico"                         |
      | Data                     | "10/07/2025"                                        |
      | Horário de Início        | "14:00"                                             |
      | Horário de Fim           | "15:00"                                             |
      | Link da Reunião          | "https://meet.google.com/xyz-abcd-efg"              |
      | Matéria                  | "Álgebra Linear"                                    |
      | Tópico                   | "Vetores e Espaços Vetoriais"                       |
      | Descrição                | "Introdução aos conceitos básicos de vetores."      |
      | Status                   | "Agendada"                                          |
      | Materiais Complementares | "nenhum"                                            |
    E o monitor navega para a página de detalhes da monitoria "ID_MONITORIA_1"

  Cenário: Monitor visualiza os detalhes completos de sua monitoria
    Quando o monitor visualiza as informações da monitoria "ID_MONITORIA_1"
    Então ele deve ver o título "Álgebra Linear - O básico"
    E ele deve ver a data "10/07/2025" e horário "14:00 - 15:00"
    E ele deve ver a matéria "Álgebra Linear" e o tópico "Vetores e Espaços Vetoriais"
    E ele deve ver a descrição "Introdução aos conceitos básicos de vetores."
    E ele deve ver o link da reunião "https://meet.google.com/xyz-abcd-efg"
    E ele deve ver que não há materiais complementares anexados

  Cenário: Monitor edita com sucesso o título e a descrição da monitoria
    Quando o monitor clica no botão "Editar"
    E o monitor altera o campo "Título" para "Álgebra Linear - Revisão de Vetores"
    E o monitor altera o campo "Descrição" para "Revisão detalhada sobre operações com vetores e suas propriedades."
    E o monitor clica no botão "Salvar Alterações"
    Então o monitor deve ver uma mensagem de sucesso "Monitoria atualizada com sucesso!"
    E ao visualizar os detalhes da monitoria "ID_MONITORIA_1", o título deve ser "Álgebra Linear - Revisão de Vetores"
    E a descrição deve ser "Revisão detalhada sobre operações com vetores e suas propriedades."

  Cenário: Monitor edita com sucesso data e horário válidos da monitoria
    Quando o monitor clica no botão "Editar"
    E o monitor altera o campo "Data" para "12/07/2025"
    E o monitor altera o campo "Horário de Início" para "16:00"
    E o monitor altera o campo "Horário de Fim" para "17:30"
    E o monitor clica no botão "Salvar Alterações"
    Então o monitor deve ver uma mensagem de sucesso "Monitoria atualizada com sucesso!"
    E ao visualizar os detalhes da monitoria "ID_MONITORIA_1", a data deve ser "12/07/2025"
    E o horário deve ser "16:00 - 17:30"

  Cenário: Monitor tenta editar horário da monitoria com fim anterior ao início
    Quando o monitor clica no botão "Editar"
    E o monitor altera o campo "Horário de Início" para "14:00"
    E o monitor altera o campo "Horário de Fim" para "13:00" # Inválido
    E o monitor clica no botão "Salvar Alterações"
    Então o monitor deve ver uma mensagem de erro "O horário de fim deve ser posterior ao horário de início."
    E as alterações de horário não devem ser salvas para a monitoria "ID_MONITORIA_1"

  Cenário: Monitor adiciona um novo material complementar à monitoria
    Dado que a monitoria "ID_MONITORIA_1" não possui materiais complementares
    Quando o monitor clica no botão "Editar"
    E o monitor anexa o arquivo "lista_exercicios_vetores.pdf" em "Materiais Complementares"
    E o monitor clica no botão "Salvar Alterações"
    Então o monitor deve ver uma mensagem de sucesso "Monitoria atualizada com sucesso!"
    E ao visualizar os detalhes da monitoria "ID_MONITORIA_1", o material "lista_exercicios_vetores.pdf" deve estar listado

  Cenário: Monitor remove um material complementar existente da monitoria
    Dado que a monitoria "ID_MONITORIA_1" possui o material complementar "slides_aula1.pdf"
    Quando o monitor clica no botão "Editar"
    E o monitor remove o arquivo "slides_aula1.pdf" de "Materiais Complementares"
    E o monitor clica no botão "Salvar Alterações"
    Então o monitor deve ver uma mensagem de sucesso "Monitoria atualizada com sucesso!"
    E ao visualizar os detalhes da monitoria "ID_MONITORIA_1", o material "slides_aula1.pdf" não deve mais estar listado

  Cenário: Monitor cancela com sucesso uma monitoria agendada
    Dado que a monitoria "ID_MONITORIA_1" está com status "Agendada"
    Quando o monitor clica no botão "Cancelar Monitoria"
    E o monitor confirma a ação de cancelamento
    Então o monitor deve ver uma mensagem de sucesso "Monitoria cancelada com sucesso."
    E o status da monitoria "ID_MONITORIA_1" deve ser atualizado para "Cancelada"
    E a monitoria "ID_MONITORIA_1" não deve mais estar disponível para inscrição de alunos

  Cenário: Monitor tenta editar uma monitoria já realizada
    Dado que a monitoria "ID_MONITORIA_1" possui o status "Realizada"
    Quando o monitor tenta clicar no botão "Editar Monitoria"
    Então o botão "Editar Monitoria" deve estar desabilitado

  Cenário: Monitor tenta cancelar uma monitoria já realizada
    Dado que a monitoria "ID_MONITORIA_1" possui o status "Realizada"
    Quando o monitor tenta clicar no botão "Cancelar Monitoria"
    Então o botão "Cancelar Monitoria" deve estar desabilitado