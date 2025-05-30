# language: pt

Funcionalidade: Visualização Monitoria pelo Aluno

  Como ALUNO,
  Desejo visualizar os detalhes de uma monitoria, como título, data, horário de início e fim, nome do monitor,
  Avaliação do monitor no formato de estrelas, matéria, tópico, descrição,
  Link do meet caso eu já esteja inscrito na monitoria e materiais anexados,
  Para que eu possa me informar melhor sobre a monitoria e me preparar adequadamente.

  Contexto: Aluno logado e monitoria existente
    Dado que o aluno "joao.aluno@email.com" está logado na plataforma
    E existe uma monitoria "ID_MONITORIA_BIO1" cadastrada com os seguintes dados:
      | Campo                 | Valor                                                           |
      | Título                | "Revisão Completa de Citologia"                                 |
      | Data                  | "15/08/2025"                                                    |
      | Horário de Início     | "10:00"                                                         |
      | Horário de Fim        | "11:30"                                                         |
      | Monitor Responsável   | "Fernanda Lima"                                                 |
      | Avaliação do Monitor  | "4 de 5 estrelas"                                               |
      | Matéria               | "Biologia"                                                      |
      | Tópico                | "Membrana Plasmática, Citoplasma e Núcleo"                      |
      | Descrição             | "Revisão aprofundada dos componentes celulares e suas funções." |
      | Link do Meet          | "https://meet.google.com/bio-celu-lar"                          |
      | Materiais Anexados    | "resumo_citologia.pdf", "slides_aula_celula.pptx"               |
      | Status                | "Agendada"                                                      |
    E o aluno navega para a página de detalhes da monitoria "ID_MONITORIA_BIO1"

  Cenário: Aluno não inscrito visualiza detalhes da monitoria
    Dado que o aluno "joao.aluno@email.com" não está inscrito na monitoria "ID_MONITORIA_BIO1"
    Quando o aluno visualiza as informações da monitoria "ID_MONITORIA_BIO1"
    Então ele deve ver o título "Revisão Completa de Citologia"
    E ele deve ver a data "15/08/2025" e o horário "10:00 - 11:30"
    E ele deve ver o nome do monitor "Fernanda Lima"
    E ele deve ver a avaliação do monitor como "4 de 5 estrelas"
    E ele deve ver a matéria "Biologia" e o tópico "Membrana Plasmática, Citoplasma e Núcleo"
    E ele deve ver a descrição "Revisão aprofundada dos componentes celulares e suas funções."
    E ele deve ver os materiais anexados "resumo_citologia.pdf" e "slides_aula_celula.pptx"
    Mas o link do Meet "https://meet.google.com/bio-celu-lar" não deve estar visível

  Cenário: Aluno inscrito visualiza detalhes da monitoria
    Dado que o aluno "joao.aluno@email.com" está inscrito na monitoria "ID_MONITORIA_BIO1"
    Quando o aluno visualiza as informações da monitoria "ID_MONITORIA_BIO1"
    Então ele deve ver o título "Revisão Completa de Citologia"
    E ele deve ver a data "15/08/2025" e o horário "10:00 - 11:30"
    E ele deve ver o nome do monitor "Fernanda Lima"
    E ele deve ver a avaliação do monitor como "4 de 5 estrelas"
    E ele deve ver a matéria "Biologia" e o tópico "Membrana Plasmática, Citoplasma e Núcleo"
    E ele deve ver a descrição "Revisão aprofundada dos componentes celulares e suas funções."
    E ele deve ver os materiais anexados "resumo_citologia.pdf" e "slides_aula_celula.pptx"
    E o link do Meet "https://meet.google.com/bio-celu-lar" deve estar visível

  Cenário: Aluno visualiza detalhes de uma monitoria sem materiais complementares
    Dado que a monitoria "ID_MONITORIA_HIST1" de "História" sobre "Revolução Francesa" com o monitor "Carlos Santos" não possui materiais anexados
  And o aluno navega para a página de detalhes da monitoria "ID_MONITORIA_HIST1"
    E o aluno "joao.aluno@email.com" NÃO está inscrito na monitoria "ID_MONITORIA_HIST1"
    Quando o aluno visualiza as informações da monitoria "ID_MONITORIA_HIST1"
    Então ele deve ver uma indicação de "Nenhum material complementar disponível"
  Ou a seção de materiais complementares não deve ser exibida

  Cenário: Aluno visualiza detalhes de uma monitoria onde o monitor ainda não possui avaliação
    Dado que a monitoria "ID_MONITORIA_FIS1" de "Física" sobre "Leis de Newton" com o monitor "Mariana Costa" tem avaliação "Não avaliado"
    E o aluno navega para a página de detalhes da monitoria "ID_MONITORIA_FIS1"
    E o aluno "joao.aluno@email.com" NÃO está inscrito na monitoria "ID_MONITORIA_FIS1"
    Quando o aluno visualiza as informações da monitoria "ID_MONITORIA_FIS1"
    Então ele deve ver uma indicação de "Estrelas Vazias"
