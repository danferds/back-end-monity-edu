# language: pt

Funcionalidade: Buscar Monitorias

  Como um aluno,
    eu quero buscar e filtrar monitorias disponíveis por palavra-chave, data ou matéria,
    para que eu possa encontrar o apoio acadêmico que preciso.

Cenário: Busca por palavra-chave no título, tópico ou descrição
    Dado que a aluna "Mariana Souza" está autenticada
    E existem monitorias cadastradas com os seguintes dados:
      | ID       | Título                     | Tópico                  | Descrição                           |
      | MON201   | Vetores e Matrizes         | Vetores em Java         | Aula prática com exemplos           |
      | MON202   | Introdução a Grafos        | Grafos direcionados     | Estrutura de grafos orientados      |
      | MON203   | Recursão e Backtracking    | Recursão                | Exemplos práticos de recursividade  |
    Quando ela realiza uma busca digitando a palavra-chave "grafos"
    Então o sistema deve retornar apenas a monitoria "Introdução a Grafos"

  Cenário: Busca com filtros por matéria e tópico
    Dado que a aluna "Mariana Souza" está autenticada
    E deseja buscar monitorias de "Matemática" sobre "Funções do 2º grau"
    Quando ela realiza a busca utilizando esses filtros
    Então o sistema deve retornar apenas as monitorias com essa matéria e esse tópico
    E todas com status "PENDENTE"

  Cenário: Nenhuma monitoria corresponde à busca realizada
    Dado que o aluno "Lucas Ribeiro" está autenticado
    E realiza uma busca utilizando a palavra-chave "quântica" e/ou aplica o filtro "física"
    E não existem monitorias correspondentes no sistema
    Quando ele realiza a busca
    Então o sistema deve informar que nenhuma monitoria foi encontrada
    E encerrar o processo de busca sem erros

  Cenário: Aluno limpa os filtros e a palavra-chave após realizar uma busca
    Dado que a aluna "Mariana Souza" está autenticada
    E realizou uma busca aplicando o filtro de matéria "Matemática" e digitando a palavra-chave "logaritmos"
    E o sistema retornou um conjunto limitado de monitorias
    Quando ela limpa todos os filtros e a palavra-chave
    Então o sistema deve retornar a lista completa de monitorias com status "PENDENTE"
    E deve remover todos os critérios anteriores da busca