# language: pt

Funcionalidade: Listar Certificados Emitidos
  Como um monitor,
    eu quero visualizar uma lista de todos os meus certificados, com informações chave como título da monitoria e data,
    para que eu possa acessar e gerenciar meus comprovantes de atividade complementar.


  Cenário: Monitor acessa a lista completa de certificados que emitiu
    Dado que o monitor "Beatriz Lima" está autenticada no sistema
    E ela já emitiu certificados para as seguintes monitorias:
      | Título                     | Matéria     | Data       | Carga Horária | Data/Hora de Emissão      |
      | Física - Termodinâmica     | Física      | 2025-07-10 | 2h            | 2025-07-11 14:32           |
      | Biologia - Genética        | Biologia    | 2025-07-12 | 1h30          | 2025-07-13 09:15           |
    Quando Beatriz solicita a listagem de seus certificados emitidos
    Então o sistema deve exibir a lista com os certificados acima
    E cada item deve conter: título, matéria, data, carga horária e data/hora de emissão


  Cenário: Monitor acessa a listagem sem ter emitido nenhum certificado
    Dado que o monitor "Beatriz Lima" está autenticada
    E ainda não emitiu nenhum certificado
    Quando ela solicita a listagem de certificados
    Então o sistema deve informar que não há certificados emitidos


