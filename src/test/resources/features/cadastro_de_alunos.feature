# language: pt

Funcionalidade: Cadastro de Alunos e Monitores
# Cenários para cadastro de alunos na plataforma
  Como um ALUNO,
  Desejo me cadastrar na plataforma
  Para que eu possa acessar os recursos disponíveis e participar das monitorias.

  Contexto: Acesso à página de cadastro
    Dado que o usuário está na página de cadastro da plataforma

  Cenário: Cadastro de aluno bem-sucedido com todos os campos preenchidos
    Quando o usuário seleciona a opção de perfil "Aluno"
    E o usuário preenche o campo "Nome" com "João da Silva"
    E o usuário preenche o campo "Email" com "joao.silva@exemplo.com"
    E o usuário preenche o campo "Senha" com "SenhaSegura123"
    E o usuário preenche o campo "Confirmar Senha" com "SenhaSegura123"
    E o usuário seleciona a "Série Escolar" como "1º Ano do Ensino Médio"
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de sucesso "Cadastro realizado com sucesso!"
    E o usuário deve ser redirecionado para o painel do aluno
    E o usuário deve ter acesso aos recursos disponíveis para alunos

  Cenário: Tentativa de cadastro de aluno sem preencher campos obrigatórios
    Quando o usuário seleciona a opção de perfil "Aluno"
    E o usuário preenche o campo "Nome" com "Maria Oliveira"
    E o usuário preenche o campo "Email" com "maria.oliveira@exemplo.com"
    # Senha e Série Escolar não preenchidos
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de erro indicando "Por favor, preencha todos os campos obrigatórios."
    E o usuário deve permanecer na página de cadastro

  Cenário: Tentativa de cadastro de aluno com e-mail já existente
    Dado que já existe um usuário cadastrado com o e-mail "ana.pereira@exemplo.com"
    Quando o usuário seleciona a opção de perfil "Aluno"
    E o usuário preenche o campo "Nome Completo" com "Ana Pereira"
    E o usuário preenche o campo "Email" com "ana.pereira@exemplo.com"
    E o usuário preenche o campo "Senha" com "OutraSenha123"
    E o usuário preenche o campo "Confirmar Senha" com "OutraSenha123"
    E o usuário seleciona a "Série Escolar" como "1º Ano do Ensino Médio"
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de erro "Este e-mail já está cadastrado na plataforma."
    E o usuário deve permanecer na página de cadastro

  Cenário: Tentativa de cadastro de aluno com senhas não coincidentes
    Quando o usuário seleciona a opção de perfil "Aluno"
    E o usuário preenche o campo "Nome" com "Carlos Santos"
    E o usuário preenche o campo "Email" com "carlos.santos@exemplo.com"
    E o usuário preenche o campo "Senha" com "SenhaForte456"
    E o usuário preenche o campo "Confirmar Senha" com "SenhaDiferente789"
    E o usuário seleciona a "Série Escolar" como "2º Ano do Ensino Médio"
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de erro "As senhas devem ser iguais."
    E o usuário deve permanecer na página de cadastro

  Esquema do Cenário: Tentativa de cadastro de aluno com formato de e-mail inválido
    Quando o usuário seleciona a opção de perfil "Aluno"
    E o usuário preenche o campo "Nome" com "<nome>"
    E o usuário preenche o campo "Email" com "<email_invalido>"
    E o usuário preenche o campo "Senha" com "<senha>"
    E o usuário preenche o campo "Confirmar Senha" com "<confirmar_senha>"
    E o usuário seleciona a "Série Escolar" como "<serie_escolar>"
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de erro "Formato de e-mail inválido."
    E o usuário deve permanecer na página de cadastro

    Exemplos:
      | nome            | email_invalido        | senha         | confirmar_senha | serie_escolar   |
      | "Pedro Almeida" | "pedro.almeida"       | "Senha123ABC" | "Senha123ABC"   | "1º Ano"        |
      | "Laura Costa"   | "laura@.com"          | "Costa#2025"  | "Costa#2025"    | "3º Ano"        |
      | "Lucas Souza"   | "lucas@exemplo"       | "SouzaLucas@" | "SouzaLucas@"   | "Não Informada" |
      | "Sofia Lima"    | "sofia limagmail.com" | "LimaSofia$"  | "LimaSofia$"    | "2º Ano"        |