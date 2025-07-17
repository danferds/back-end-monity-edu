# language: pt

Funcionalidade: Cadastrar Aluno

  Como um visitante,
    eu quero me cadastrar na plataforma como "Aluno", informando meu nome, e-mail, senha e série escolar,
    para que eu possa acessar os recursos do sistema e me inscrever em monitorias.

  Cenário: Cadastro de aluno bem-sucedido com dados válidos
    Dado que um visitante inicia o processo de cadastro como aluno.
    Quando ele fornece o nome "Mariana Silva", o e-mail "mariana.silva@gmail.com", a senha "senhaSegura123" e seleciona a série "2º Ano do Ensino Médio".
    Então o sistema deve criar uma nova conta de aluno para "Mariana Silva".
    E o sistema deve gerar um identificador único (UUID) para o novo aluno
    E a senha fornecida deve ser armazenada de forma criptografada.
    E o sistema deve confirmar que o cadastro foi realizado com sucesso.

  Cenário: Tentativa de cadastro com um e-mail que já existe
    Dado que já existe um aluno cadastrado com o e-mail "ricardo@gmail.com".
    Quando um novo visitante tenta se cadastrar usando o mesmo e-mail "ricardo@gmail.com".
    Então o sistema não deve criar uma nova conta.
    E o sistema deve informar ao visitante que o e-mail fornecido já está em uso.

  Cenário: Tentativa de cadastro com senha inválida
    Dado que um visitante está se cadastrando como aluno.
    Quando ele preenche todos os dados, mas informa uma senha com apenas 4 caracteres, como "1234".
    Então o sistema não deve criar a conta.
    E o sistema deve informar que a senha não atende ao requisito de tamanho mínimo de 6 caracteres.

  Cenário: Tentativa de cadastro com campos obrigatórios não preenchidos
    Dado que um visitante está se cadastrando como aluno.
    Quando ele tenta submeter o cadastro com o e-mail "visitante@gmail.com" e a senha "senhaValida123", mas deixa os campo "Nome Completo" e "Série Escolar" em branco.
    Então o sistema não deve criar a conta.
    E o sistema deve informar que o campo "Nome Completo" e "Série Escolar" são de preenchimento obrigatório.

  Cenário: Tentativa de cadastro com formato de e-mail inválido
    Dado que um visitante está no processo de cadastro de aluno.
    Quando ele fornece um e-mail em um formato inválido, como "mariana.silva.com".
    Então o sistema não deve criar a conta de aluno.
    E o sistema deve informar que o formato do e-mail é inválido