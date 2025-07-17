# language: pt

Funcionalidade: Editar Monitoria

  Como um monitor,
    eu quero editar os detalhes de uma monitoria pendente,
    para que eu possa corrigir informações ou atualizar os alunos sobre qualquer mudança.

  Cenário: Edição completa de monitoria pendente
    Dado que o monitor "Nuno Moreira" está autenticado
    E possui uma monitoria com ID "MON789" em status "PENDENTE"
    Quando ele altera os dados da monitoria:
      | Título         | Estrutura Condicional em Java              |
      | Data           | 2025-08-01                                  |
      | Horário Início | 15:00                                       |
      | Horário Fim    | 16:30                                       |
      | Link           | https://meet.google.com/estrutura-cond     |
      | Matéria        | Lógica de Programação                      |
      | Tópico         | If, Else, Switch                           |
      | Descrição      | Aula sobre estruturas de decisão           |
    E anexa os arquivos:
      | Nome do Arquivo          | Tipo  | Tamanho (MB) |
      | estruturas_decisao.pdf   | PDF   | 3            |
      | exemplos_decisao.docx    | DOCX  | 1            |
    Então o sistema deve validar os dados e os arquivos
    E atualizar a monitoria com os novos valores e anexos

  Cenário: Edição parcial da monitoria sem alteração de anexos
    Dado que a monitora "Ana Paula" está autenticada
    E possui uma monitoria com ID "MON102" com status "PENDENTE"
    Quando ela altera apenas a descrição da monitoria para "Aula reforço com novos exemplos"
    Então o sistema deve validar apenas os dados alterados
    E deve atualizar a monitoria sem alterar os anexos

  Cenário: Substituição dos anexos sem alterar os dados
    Dado que o monitor "João Victor" está autenticado
    E possui uma monitoria com ID "MON301" com status "PENDENTE"
    Quando ele remove os arquivos antigos e anexa:
      | Nome do Arquivo         | Tipo | Tamanho (MB) |
      | novo_material.txt       | TXT  | 0.3          |
      | resumo_grafos.pdf       | PDF  | 2.5          |
    Então o sistema deve validar os novos arquivos
    E atualizar os materiais da monitoria mantendo os dados textuais anteriores

  Cenário: Edição não permitida por status inválido
    Dado que a monitora "Ana Paula" está autenticada
    E possui uma monitoria com ID "MON555" com status "REALIZADA"
    Quando ela tenta editar os dados dessa monitoria
    Então o sistema deve bloquear a operação
    E deve informar que não é possível editar monitorias já realizadas ou canceladas

  Cenário: Falha de validação dos dados textuais
    Dado que o monitor "Pablo Vegetti" está autenticado
    E tenta editar sua monitoria "MON456"
    Quando ele informa um horário de término anterior ao início (Início: 15:00 / Fim: 14:00)
    Então o sistema deve rejeitar a edição
    E informar que o horário de término deve ser posterior ao de início.

  Cenário: Monitor tenta adicionar material com formato não permitido
    Dado que o monitor "Coutinho" está autenticado
    E possui uma monitoria com ID "MON301" em status "PENDENTE"
    Quando ele tenta anexar o arquivo "comando_terminal.sh" com formato SH
    Então o sistema deve rejeitar a edição
    E informar que apenas arquivos nos formatos PDF, DOCX, PPTX, TXT, JPG e PNG são permitidos

  Cenário: Anexo de arquivos ultrapassa limite permitido
    Dado que a monitora "Ana Paula" está autenticada
    E deseja editar a monitoria "MON102"
    Quando ela tenta anexar o arquivo "aulacompleta.mov" com 15MB
    Então o sistema deve rejeitar a edição
    E deve informar que cada arquivo execede o tamanho máximo
