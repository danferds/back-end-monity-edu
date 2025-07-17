# language: pt

Funcionalidade: Cadastrar de Monitor

  Como um visitante,
    eu quero me cadastrar na plataforma como "Monitor", informando meu nome, e-mail e senha,
    para que eu possa iniciar meu processo de credenciamento e, futuramente, oferecer monitorias.

  Cenário: Cadastro de monitor bem-sucedido
    Dado que um visitante inicia o processo de cadastro como monitor.
    Quando ele fornece o nome "Carlos Lima", o e-mail "carloslima@gmail.com" e a senha "monitor123".
    Então o sistema deve criar uma nova conta de monitor para "Carlos Lima".
    E o status inicial da conta deve ser definido como "PENDENTE" automaticamente.
    E o sistema deve gerar um identificador único (UUID) para o novo monitor.
    E a senha fornecida deve ser armazenada de forma criptografada.
    E  o sistema deve confirmar que o cadastro foi realizado com sucesso.

  Cenário: Tentativa de cadastro com um e-mail que já existe
    Dado que já existe um monitor cadastrado com o e-mail "ricardo.existente@email.com".
    Quando um novo visitante tenta se cadastrar usando o mesmo e-mail "ricardo@gmail.com".
    Então o sistema não deve criar uma nova conta.
    E o sistema deve informar ao visitante que o e-mail fornecido já está em uso.

  Cenário: Tentativa de cadastro com senha inválida
    Dado que um visitante está se cadastrando como monitor.
    Quando ele preenche todos os dados, mas informa uma senha com apenas 4 caracteres, como "1234".
    Então o sistema não deve criar a conta.
    E o sistema deve informar que a senha não atende ao requisito de tamanho mínimo de 6 caracteres.

  Cenário: Tentativa de cadastro com campos obrigatórios não preenchidos
    Dado que um visitante está se cadastrando como monitor.
    Quando ele tenta submeter o cadastro com o e-mail "visitante@gmail.com" e a senha "senhaValida123", mas deixa o campo "Nome Completo" em branco.
    Então o sistema não deve criar a conta.
    E o sistema deve informar que o campo "Nome Completo" é de preenchimento obrigatório.

  Cenário: Tentativa de cadastro com formato de e-mail inválido
    Dado que um visitante está no processo de cadastro de monitor.
    Quando ele fornece um e-mail em um formato inválido, como "mariana.silva.com".
    Então o sistema não deve criar a conta de aluno.
    E o sistema deve informar que o formato do e-mail é inválido