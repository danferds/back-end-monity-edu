# language: pt

Funcionalidade: Autenticar Usuários

  Como um usuário cadastrado,
    eu quero fazer login com meu e-mail e senha,
    para que eu possa acessar as funcionalidades restritas da plataforma.

  Cenário: Autenticação com sucesso utilizando credenciais válidas
    Dado que a monitora "Ana Paula" possui uma conta com o e-mail "ana.paula@universidade.edu" e senha "senhaSegura123".
    E ela não está autenticada no momento.
    Quando ela informa o e-mail "ana.paula@universidade.edu" e a senha "senhaSegura123".
    Então o sistema deve autenticar a usuária com sucesso.
    E o sistema deve gerar uma sessão autenticada com um token JWT válido.


  Cenário: Autenticação falha devido a senha incorreta
    Dado que o aluno "Ricardo" possui uma conta com o e-mail "ricardo@gmail.com" e senha "vestibular2025".
    E ele não está autenticado no momento.
    Quando ele informa o e-mail "ricardo.estudos@gmail.com" e a senha "vestibular2024".
    Então o sistema não deve autenticar o usuário.
    E o sistema deve informar que as credenciais são inválidas.

  Cenário: Tentativa de autenticação sem preenchimento de e-mail
    Dado que a aluna "Mariana" possui uma conta com o e-mail "mariana3serie@gmail.com" e senha "12345678".
    E ela não está autenticada no momento.
    Quando ela tenta autenticar-se sem preencher o campo de e-mail, apenas com a senha "12345678".
    Então o sistema deve rejeitar a autenticação.
    E o sistema deve informar que os campos e-mail e senha são obrigatórios.

  Cenário: Usuário tenta acessar funcionalidade sem estar autenticado
    Dado que o aluno "Ricardo" não está autenticado no sistema.
    Quando ele tenta acessar a funcionalidade de inscrição em monitorias.
    Então o sistema deve bloquear o acesso à funcionalidade.
    E o sistema deve exigir que ele se autentique previamente.