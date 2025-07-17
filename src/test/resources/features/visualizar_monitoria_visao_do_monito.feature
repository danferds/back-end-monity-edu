# language: pt

Funcionalidade: Visualizar Monitoria (Visão do Monitor)

  Como um monitor,
    eu quero visualizar os detalhes completos de uma das minhas monitorias cadastradas,
    para que eu possa conferir todas as informações e me preparar para a sessão.

  Cenário: Visualização completa de uma monitoria com materiais complementares
    Dado que o monitor "Daniel Fernades" está autenticado no sistema
    E possui uma monitoria cadastrada com ID "MON123"
    Quando ele solicita os detalhes da monitoria "MON123"
    Então o sistema deve exibir:
      | Título          | Listas Encadeadas                          |
      | Data            | 2025-07-28                                 |
      | Horário Início  | 14:00                                      |
      | Horário Fim     | 15:30                                      |
      | Link            | https://meet.google.com/listas-encadeadas |
      | Matéria         | Estrutura de Dados                         |
      | Tópico          | Listas Simples e Duplamente Encadeadas    |
      | Descrição       | Aula com demonstrações de implementação   |
      | Status          | PENDENTE                                   |
    E o sistema deve exibir os materiais complementares associados

  Cenário: Visualização de monitoria que não possui materiais anexados
    Dado que a monitora "Ana Paula" está autenticada no sistema
    E possui uma monitoria cadastrada com ID "MON124" sem anexos
    Quando ela solicita os detalhes da monitoria "MON124"
    Então o sistema deve exibir todos os dados da monitoria
    E o sistema deve informar que não há materiais complementares associados

  Cenário: Monitor solicita visualização de monitoria inexistente
    Dado que o monitor "Léo Jardim" está autenticado
    Quando ele tenta visualizar a monitoria com ID "MON999"
    Então o sistema deve informar que a monitoria não foi encontrada
    E deve encerrar o processo de visualização