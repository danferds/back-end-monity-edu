# language: pt

Funcionalidade: Cadastrar Monitoria

  Como um monitor credenciado,
    eu quero cadastrar uma nova monitoria com todos os detalhes (título, data, horário de início e fim, link da reunião (Google Meet), matéria, tópico e descrição) e anexar materiais,
    para que os alunos possam encontrá-la e se inscrever.

  Cenário: Cadastro de monitoria com sucesso incluindo materiais complementares
    Dado que a monitora "Ana Paula" está autenticada e possui status "APROVADO"
    Quando ela preenche os dados obrigatórios da monitoria:
      | Título           | Introdução a Grafos                       |
      | Data             | 2025-07-26                                |
      | Horário Início   | 09:00                                     |
      | Horário Fim      | 10:30                                     |
      | Link da Reunião  | https://meet.google.com/grafos-aula      |
      | Matéria          | Estruturas de Dados                      |
      | Tópico           | Grafos não direcionados                  |
      | Descrição        | Aula teórica com exemplos práticos       |
    E anexa os seguintes arquivos:
      | Nome do Arquivo      | Tipo | Tamanho (MB) |
      | grafos_teoria.pdf    | PDF  | 4            |
      | lista_exercicios.docx| DOCX | 1            |
    Então o sistema deve validar os arquivos anexados
    E cadastrar a monitoria com status "PENDENTE"
    E associá-la ao monitor "Ana Paula"

  Cenário: Cadastro de monitoria com sucesso sem material complementar
    Dado que o monitor "Pedrosa" está autenticado e possui status "APROVADO"
    Quando ele preenche os seguintes dados obrigatórios da monitoria:
      | Título           | Estrutura de Repetição em Java            |
      | Data             | 2025-07-25                                |
      | Horário Início   | 14:00                                     |
      | Horário Fim      | 15:30                                     |
      | Link da Reunião  | https://meet.google.com/abc-defg-hij     |
      | Matéria          | Lógica de Programação                    |
      | Tópico           | Laços de Repetição                       |
      | Descrição        | Revisão prática com exercícios guiados   |
    Então o sistema deve cadastrar a monitoria com status "PENDENTE"
    E associá-la ao monitor "Pedrosa"

  Cenário: Monitor ainda não aprovado tenta cadastrar monitoria
    Dado que o monitor "Lucas Pereira" está autenticado e possui status "PENDENTE"
    Quando ele tenta acessar a funcionalidade de cadastro de monitoria
    Então o sistema deve bloquear o acesso
    E deve informar que apenas monitores com status "APROVADO" podem cadastrar monitorias

  Cenário: Monitor omite campo obrigatório ao cadastrar monitoria
    Dado que a monitora "Fernanda" está autenticada e possui status "APROVADO"
    Quando ela preenche os dados da monitoria, mas deixa o campo "Tópico" em branco
    Então o sistema deve rejeitar o cadastro
    E deve informar que todos os campos obrigatórios devem ser preenchidos

  Cenário: Monitor anexa arquivo em formato não permitido
    Dado que o monitora "Mirela" está autenticada e possui status "APROVADO"
    Quando ela preenche os dados da monitoria
    E tenta anexar o arquivo "script_instalacao.sh" no formato SH
    Então o sistema deve rejeitar o anexo
    E informar que apenas arquivos nos formatos PDF, DOCX, PPTX, TXT, JPG e PNG são permitidos

  Cenário: Anexo excede limite máximo por arquivo
    Dado que a monitora "Ana Paula" está autenticada e possui status "APROVADO"
    Quando ela tenta anexar o arquivo "video_aula.mov" com 12 MB
    Então o sistema deve rejeitar o anexo
    E deve informar que cada arquivo deve ter no máximo 10 MB

  Cenário: Conjunto de arquivos excede tamanho máximo permitido
    Dado que o monitor "Pedrosa" está autenticado e possui status "APROVADO"
    Quando ele tenta anexar três arquivos totalizando 55 MB
    Então o sistema deve rejeitar o conjunto de anexos
    E informar que o limite total de materiais por monitoria é de 50 MB