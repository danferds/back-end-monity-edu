# language: pt

Funcionalidade: Cadastrar Monitoria

  Como um MONITOR,
  Desejo cadastrar uma monitoria informando o título, data, horário de início e fim, link da reunião (Google Meet), matéria, tópico e descrição,
  Além de poder anexar materiais complementares,
  Para que os alunos possam visualizar as informações da monitoria e se inscreverem.

  Contexto: Monitor logado e na página de cadastro de monitoria
    Dado que o monitor "ana.beatriz@exemplo.com" está logado na plataforma
    E o monitor está na página de "Cadastrar Monitoria"

  Cenário: Cadastro de monitoria bem-sucedido com todos os campos obrigatórios e sem material complementar
    Quando o monitor preenche o campo "Título" com "Entendendo os ecomssitemas brasileiros"
    E o monitor preenche o campo "Data" com "10/06/2025"
    E o monitor preenche o campo "Horário de Início" com "14:00"
    E o monitor preenche o campo "Horário de Fim" com "16:00"
    E o monitor preenche o campo "Link da Reunião" com "https://meet.google.com/abc-defg-hij"
    E o monitor seleciona a "Matéria" como "Biologia"
    E o monitor preenche o campo "Tópico" com "Ecossistemas"
    E o monitor preenche o campo "Descrição" com "Sessão para tirar dúvidas e resolver exercícios Amazônia, o Cerrado, a Caatinga, a Mata Atlântica, o Pantanal e os Pampas."
    E o monitor clica no botão "Cadastrar"
    Então o monitor deve ver uma mensagem de sucesso "Monitoria cadastrada com sucesso!"
    E a monitoria "Entendendo os ecomssitemas brasileiros" deve estar listada como disponível para os alunos visualizarem e se inscreverem.

  Cenário: Cadastro de monitoria bem-sucedido com todos os campos obrigatórios e com material complementar
    Quando o monitor preenche o campo "Título" com "Aprofundamento sobre o Cerrado brasileiro"
    E o monitor preenche o campo "Data" com "12/06/2025"
    E o monitor preenche o campo "Horário de Início" com "10:00"
    E o monitor preenche o campo "Horário de Fim" com "11:30"
    E o monitor preenche o campo "Link da Reunião" com "https://meet.google.com/klm-nopq-rst"
    E o monitor seleciona a "Matéria" como "Biologia"
    E o monitor preenche o campo "Tópico" com "Ecossistemas"
    E o monitor preenche o campo "Descrição" com "Aprofunde seus conhecimentos sobre o Cerrado, um dos biomas brasileiros"
    E o monitor anexa o arquivo "cerrado.pdf" no campo "Anexar arquivos"
    E o monitor clica no botão "Cadastrar"
    Então o monitor deve ver uma mensagem de sucesso "Monitoria cadastrada com sucesso!"
    E a monitoria "Aprofundamento sobre o Cerrado brasileiro" deve estar listada com o material "cerrado.pdf" disponível

  Cenário: Tentativa de cadastro de monitoria sem preencher campos obrigatórios
    Quando o monitor preenche o campo "Título" com "Monitoria de Cálculo I"
    E o monitor preenche o campo "Data" com "15/06/2025"
    # Horário de Início, Fim e Link da Reunião não preenchidos
    E o monitor seleciona a "Matéria" como "Cálculo I"
    E o monitor clica no botão "Cadastrar"
    Então o monitor deve ver uma mensagem de erro indicando "Por favor, preencha todos os campos obrigatórios: Horário de Início, Horário de Fim, Link da Reunião, Tópico, Descrição."
    E o monitor deve permanecer na página de "Cadastrar Monitoria"

  Cenário: Tentativa de cadastro de monitoria com horário de fim anterior ao horário de início
    Quando o monitor preenche o campo "Título" com "Tira-dúvidas sobre a Grécia Antiga"
    E o monitor preenche o campo "Data" com "17/06/2025"
    E o monitor preenche o campo "Horário de Início" com "15:00"
    E o monitor preenche o campo "Horário de Fim" com "14:00" # Horário inválido
    E o monitor preenche o campo "Link da Reunião" com "https://meet.google.com/uvw-xyza-bcd"
    E o monitor seleciona a "Matéria" como "História"
    E o monitor preenche o campo "Tópico" com "História Antiga"
    E o monitor preenche o campo "Descrição" com "Sessão aberta para discussão sobre o impacto da civilização graga."
    E o monitor clica no botão "Cadastrar"
    Então o monitor deve ver uma mensagem de erro "O horário de fim deve ser posterior ao horário de início."
    E o monitor deve permanecer na página de "Cadastrar Monitoria"

  Cenário: Tentativa de cadastro de monitoria com anexo de material complementar de tipo não suportado
    Quando o monitor preenche o campo "Título" com "Monitoria de Estrutura de Dados"
    E o monitor preenche o campo "Data" com "21/06/2025"
    E o monitor preenche o campo "Horário de Início" com "13:00"
    E o monitor preenche o campo "Horário de Fim" com "14:30"
    E o monitor preenche o campo "Link da Reunião" com "https://meet.google.com/efg-hijk-lmn"
    E o monitor seleciona a "Matéria" como "Estrutura de Dados"
    E o monitor preenche o campo "Tópico" com "Pilhas e Filas"
    E o monitor preenche o campo "Descrição" com "Implementação e exemplos práticos."
    E o monitor anexa o arquivo "exercicios.exe" no campo "Materiais Complementares"
    E o monitor clica no botão "Salvar Monitoria"
    Então o monitor deve ver uma mensagem de erro "Tipo de arquivo não suportado para materiais complementares. Utilize PDF, DOCX, PPTX, TXT, JPG, PNG."
    E o monitor deve permanecer na página de "Cadastrar Monitoria"

  Esquema do Cenário: Tentativa de cadastro de monitoria com formato de link de reunião inválido
    Quando o monitor preenche o campo "Título" com "Matrizes"
    E o monitor preenche o campo "Data" com "<data>"
    E o monitor preenche o campo "Horário de Início" com "<horario_inicio>"
    E o monitor preenche o campo "Horário de Fim" com "<horario_fim>"
    E o monitor preenche o campo "Link da Reunião" com "<link_invalido>"
    E o monitor seleciona a "Matéria" como "Matemática"
    E o monitor preenche o campo "Tópico" com "Álgebra Linear"
    E o monitor preenche o campo "Descrição" com "Resolução de problemas."
    E o monitor clica no botão "Cadastrar"
    Então o monitor deve ver uma mensagem de erro "O formato do link da reunião é inválido. Utilize um link válido do Google Meet."
    E o monitor deve permanecer na página de "Cadastrar Monitoria"

    Exemplos:
      | data         | horario_inicio | horario_fim | link_invalido               |
      | "18/06/2025" | "09:00"        | "10:00"     | "htt://meet.google.com/123" |
      | "19/06/2025" | "11:00"        | "12:00"     | "www.google.com"            |
      | "20/06/2025" | "16:00"        | "17:00"     | "apenas_um_texto"           |
