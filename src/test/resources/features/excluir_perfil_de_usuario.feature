# language: pt

Funcionalidade: Excluir Perfil de Usuário

  Como um usuário autenticado,
    eu quero poder excluir permanentemente minha conta,
    para que eu tenha controle sobre meus dados e minha presença na plataforma.

  Cenário: Usuário solicita a exclusão do próprio perfil com confirmação
    Dado que o aluno "Alexandre" está autenticado no sistema.
    Quando ele solicita a exclusão de sua conta e confirma a ação.
    Então o sistema deve remover permanentemente todos os seus dados.
    E o sistema deve encerrar sua sessão e confirmar a exclusão.

  Cenário: Usuário desiste da exclusão após iniciar o processo
    Dado que a monitora "Ana Paula" está autenticada no sistema.
    Quando ela inicia o processo de exclusão da conta
    E opta por cancelar a operação antes da confirmação
    Então o sistema deve encerrar o processo sem excluir sua conta.
    E o sistema deve manter os dados da usuária inalterados.

  Cenário: Monitor tenta excluir o perfil de outro usuário
    Dado que o monitor "Carlos" está autenticado no sistema.
    Quando ele tenta excluir o perfil do aluno "Ricardo"
    Então o sistema deve bloquear a ação.
    E o sistema deve informar que apenas o titular da conta pode excluí-la.

