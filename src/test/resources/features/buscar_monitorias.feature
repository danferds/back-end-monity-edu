# language: pt

Funcionalidade: Busca de Monitorias Inscritas

  Como ALUNO,
  Desejo buscar pelas monitorias em que estou inscrito usando filtros ou palavras-chave,
  Para que eu possa localizar rapidamente uma monitoria específica no meu dashboard.

  Contexto: Aluno logado com monitorias inscritas e na página de suas monitorias
    Dado que o aluno "joana.dias@email.com" está logado na plataforma
    E o aluno "joana.dias@email.com" está em seu "Dashboard do Aluno" visualizando a lista de "Monitorias Inscritas"
    E o aluno "joana.dias@email.com" está inscrito nas seguintes monitorias:
      | ID         | Título                                 | Matéria       | Tópico                         | Monitor          | Status      | Data         |
      | "MON_QUM1" | "Revisão ENEM - Química Orgânica"      | "Química"     | "Funções Orgânicas, Isomeria"  | "Betriz Silva"   | "Agendada"  | "10/10/2025" |
      | "MON_QUM2" | "Revisão ENEM - Química Orgânica"      | "Química"     | "Reações Orgânicas"            | "Betriz Silva"   | "Agendada"  | "10/10/2025" |
      | "MON_LIT1" | "Literatura Brasileira - Modernismo"   | "Literatura"  | "Primeira Fase Modernista"     | "Daniel Alves"   | "Agendada"  | "12/10/2025" |
      | "MON_MAT1" | "Geometria Plana - Áreas e Perímetros" | "Matemática"  | "Cálculo de áreas de figuras"  | "Giovanna Silva" | "Realizada" | "01/10/2025" |
      | "MON_HIST1"| "História do Brasil - Período Colonial"| "História"    | "Ciclos Econômicos Coloniais"  | "Leticia Costa"  | "Agendada"  | "15/10/2025" |
      | "MON_FIS1" | "Física: Ondas e Som"                  | "Física"      | "Reflexão e Refração Sonora"   | "Fernando Alves" | "Cancelada" | "18/10/2025" |
      | "MON_BIO1" | "Biologia Celular - Revisão Geral"     | "Biologia"    | "Organelas e suas funções"     | "Rubens Silva"   | "Agendada"  | "20/10/2025" |

  Cenário: Aluno busca por palavra-chave no título de uma monitoria inscrita
    Quando o aluno insere "Química Orgânica" no campo de busca
    E o aluno aciona a busca
    Então a lista de monitorias inscritas deve exibir apenas a monitoria "MON_QUM1"
    E nenhuma outra monitoria deve ser exibida

  Cenário: Aluno busca por palavra-chave no tópico de uma monitoria inscrita
    Quando o aluno insere "Modernista" no campo de busca
    E o aluno aciona a busca
    Então a lista de monitorias inscritas deve exibir apenas a monitoria "MON_LIT1"

  Cenário: Aluno busca por palavra-chave que não corresponde a nenhuma monitoria inscrita
    Quando o aluno insere "Astronomia" no campo de busca por
    E o aluno aciona a busca
    Então a lista de monitorias inscritas deve exibir uma mensagem "Nenhuma monitoria encontrada."

  Cenário: Aluno busca por palavra-chave resultando em múltiplas monitorias
    Quando o aluno insere "Beatriz Silva" no campo de busca
    E o aluno aciona a busca
    Então a lista de monitorias inscritas deve exibir as monitorias "MON_QUM1" e "MON_QUM2"
    Mas não deve exibir as monitorias "MON_LIT1", "MON_HIST1" e "MON_FIS1"

  Cenário: Aluno busca por palavra-chave de forma case-insensitive
    Quando o aluno insere "história do brasil" no campo de busca por palavra-chave
    E o aluno aciona a busca
    Então a lista de monitorias inscritas deve exibir a monitoria "MON_HIST1"

  Cenário: Aluno filtra monitorias inscritas por Matéria
    Quando o aluno aplica o filtro "Matéria" com o valor "Física"
    Então a lista de monitorias inscritas deve exibir apenas a monitoria "MON_FIS1"

  Cenário: Aluno filtra monitorias inscritas por Status
    Quando o aluno aplica o filtro "Status" com o valor "Agendada"
    Então a lista de monitorias inscritas deve exibir as monitorias "MON_QUM1", "MON_LIT1", "MON_HIST1" e "MON_BIO1"
    Mas não deve exibir as monitorias "MON_MAT1" e "MON_FIS1"

  Cenário: Aluno combina múltiplos filtros (Matéria e Status)
    Quando o aluno aplica o filtro "Matéria" com o valor "Química"
    E o aluno aplica o filtro "Status" com o valor "Agendada"
    Então a lista de monitorias inscritas deve exibir apenas a monitoria "MON_QUM1" e "MON_QUM2"

  Cenário: Aluno aplica filtro que não retorna resultados
    Quando o aluno aplica o filtro "Matéria" com o valor "Geografia"
    Então a lista de monitorias inscritas deve exibir uma mensagem "Nenhuma monitoria encontrada"

  Cenário: Aluno limpa os filtros de busca e visualiza todas as suas monitorias inscritas novamente
    Quando o aluno insere "Química" no campo de busca por palavra-chave e aciona a busca
    Então a lista de monitorias inscritas exibe resultados filtrados
    Quando o aluno clica no botão "Limpar Busca"
    Então a lista de monitorias inscritas deve exibir todas as 7 monitorias originalmente listadas: "MON_QUM1", "MON_QUM2", "MON_LIT1", "MON_MAT1", "MON_HIST1", "MON_FIS1", "MON_BIO1"