# language: pt

Funcionalidade: Visualizar  Monitoria (Visão do Aluno)

  Como um aluno,
    eu quero visualizar todos os detalhes de uma monitoria, incluindo informações sobre o monitor e sua avaliação média,
    para que eu possa decidir se desejo me inscrever.


  Cenário: Aluno visualiza os detalhes completos de uma monitoria com materiais disponíveis
    Dado que o aluno "João Silva" está autenticado no sistema
    E existe uma monitoria com título "Revisão de Matemática", matéria "Matemática", tópico "Equações de 2º grau"
    E essa monitoria foi cadastrada pelo monitor "Coutinho" com avaliação média 4.5
    E os arquivos "material_teorico.pdf" e "exercicios_equacoes.docx" estão anexados
    Quando Ele solicita visualizar os detalhes da monitoria "Revisão de Matemática"
    Então o sistema deve apresentar os dados completos da monitoria
    E o sistema deve indicar os materiais disponíveis anexados
    E o sistema deve exibir o nome do monitor e sua média de avaliação

  Cenário: Aluno visualiza uma monitoria que não possui materiais complementares anexados
    Dado que a aluna "Carla Fernandes" está autenticada no sistema
    E existe uma monitoria cadastrada com título "Resumo de História Geral", sem arquivos anexados
    Quando Ela solicita a visualização da monitoria "Resumo de História Geral"
    Então o sistema deve retornar os dados da monitoria normalmente
    E o sistema deve indicar que não há materiais complementares disponíveis

  Cenário: Aluno acessa uma monitoria cujo monitor ainda não possui avaliações
    Dado que o aluno "Marcos Santos" está autenticado no sistema
    E existe uma monitoria com título "Química Orgânica Básica" ministrada pela monitora "Ana Lins"
    E "Ana Lins" ainda não recebeu avaliações de alunos
    Quando Marcos visualiza os detalhes da monitoria "Química Orgânica Básica"
    Então o sistema deve exibir os dados completos da monitoria
    E deve indicar que o monitor ainda não possui avaliações

  Cenário: Aluno acessa os detalhes de uma monitoria já realizada que participou
    Dado que a aluna "Bianca Oliveira" está autenticada no sistema
    E ela participou da monitoria "Análise de Texto Dissertativo" com status REALIZADA
    E ela avaliou essa monitoria com nota 4
    Quando Ela visualiza os detalhes da monitoria "Análise de Texto Dissertativo"
    Então o sistema deve apresentar os dados da monitoria
    E informar a nota de avaliação dada por ela para essa monitoria

  Cenário: Aluno tenta acessar uma monitoria que não existe
    Dado que o aluno "Rafael Braga" está autenticado no sistema
    E não existe nenhuma monitoria com título "Física Quântica Avançada"
    Quando Ele solicita visualizar os detalhes da monitoria "Física Quântica Avançada"
    Então o sistema deve informar que a monitoria não foi encontrada