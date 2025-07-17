# language: pt

Funcionalidade: Editar Perfil de Monitor

  Como um monitor autenticado,
    eu quero poder editar meu e-mail e senha,
    para que eu possa manter minhas informações de acesso seguras e atualizadas.

  Cenário: Monitor edita suas informações com sucesso
    Dado que a monitora "Mariana" está autenticada e deseja editar seu perfil.
    Quando ela solicita a alteração de seus dados, mudando sua senha para "mari1234".
    Então o sistema deve atualizar seu perfil com a nova senha.
    E o sistema deve confirmar a conclusão da atualização.

  Cenário: Monitor tenta alterar o e-mail para um que já está em uso
    Dado que a monitora "Mariana" está autenticada e deseja editar seu perfil.
    E já existe outra conta no sistema com o e-mail "ricardo@gmail.com".
    Quando "Mariana" tenta alterar seu próprio e-mail para "ricardo@gmail.com".
    Então o sistema não deve permitir a alteração.
    E o sistema deve informar que o e-mail informado já está cadastrado.

  Cenário: Monitor tenta alterar a senha para uma inválida
    Dado que a monitora "Mariana" está autenticada e deseja editar seu perfil.
    Quando ela tenta alterar sua senha para uma nova com menos de 6 caracteres, como "abc".
    Então o sistema deve rejeitar a solicitação.
    E o sistema deve informar que a senha não atende aos requisitos de validação.


  Cenário: Monitor desiste de editar o perfil
    Dado que a monitora "Mariana" está autenticada e deseja editar seu perfil.
    Quando ela decide cancelar a operação antes de confirmar qualquer alteração.
    Então o sistema deve encerrar o processo de edição.