# language: pt

Funcionalidade: Cadastro de Alunos e Monitores
# Cenários para cadastro de monitores
  Como um MONITOR,
  Desejo me cadastrar na plataforma selecionando a opção "Monitor" e informando meu nome, e-mail e senha,
  Para que eu possa oferecer monitorias e apoiar os alunos em suas dúvidas e no processo de aprendizagem.

  Contexto: Acesso à página de cadastro
    Dado que o usuário está na página de cadastro da plataforma

  Cenário: Cadastro de monitor bem-sucedido com todos os campos preenchidos
    Quando o usuário seleciona a opção de perfil "Monitor"
    E o usuário preenche o campo "Nome" com "Ana Beatriz Santos"
    E o usuário preenche o campo "Email" com "ana.beatriz@exemplo.com"
    E o usuário preenche o campo "Senha" com "Monitoria123"
    E o usuário preenche o campo "Confirmar Senha" com "Monitoria123"
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de sucesso "Cadastro realizado com sucesso! Faça a comprovação de vínculo acadêmico."
    E o usuário deve ser redirecionado para o painel do monitor com status pendente

  Cenário: Tentativa de cadastro de monitor sem preencher campos obrigatórios
    Quando o usuário seleciona a opção de perfil "Monitor"
    E o usuário preenche o campo "Nome" com "Carlos Eduardo Lima"
    # Email e Senha não preenchidos
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de erro indicando "Por favor, preencha todos os campos obrigatórios."
    E o usuário deve permanecer na página de cadastro

  Cenário: Tentativa de cadastro de monitor com e-mail já existente
    Dado que já existe um usuário cadastrado com o e-mail "joao.silva@exemplo.com"
    Quando o usuário seleciona a opção de perfil "Monitor"
    E o usuário preenche o campo "Nome" com "João Silva"
    E o usuário preenche o campo "Email" com "joao.silva@exemplo.com"
    E o usuário preenche o campo "Senha" com "Senha456"
    E o usuário preenche o campo "Confirmar Senha" com "Senha456"
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de erro "Este e-mail já está cadastrado na plataforma."
    E o usuário deve permanecer na página de cadastro

  Cenário: Tentativa de cadastro de monitor com senhas não coincidentes
    Quando o usuário seleciona a opção de perfil "Monitor"
    E o usuário preenche o campo "Nome" com "Mariana Costa"
    E o usuário preenche o campo "Email" com "mariana.costa@exemplo.com"
    E o usuário preenche o campo "Senha" com "Segredo&Monitor"
    E o usuário preenche o campo "Confirmar Senha" com "Segredo&Diferente"
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de erro "As senhas devem ser iguais."
    E o usuário deve permanecer na página de cadastro

  Esquema do Cenário: Tentativa de cadastro de monitor com formato de e-mail inválido
    Quando o usuário seleciona a opção de perfil "Monitor"
    E o usuário preenche o campo "Nome" com "<nome>"
    E o usuário preenche o campo "Email" com "<email_invalido>"
    E o usuário preenche o campo "Senha" com "<senha>"
    E o usuário preenche o campo "Confirmar Senha" com "<confirmar_senha>"
    E o usuário clica no botão "Cadastrar"
    Então o usuário deve ver uma mensagem de erro "Formato de e-mail inválido."
    E o usuário deve permanecer na página de cadastro

    Exemplos:
      | nome             | email_invalido          | senha          | confirmar_senha  |
      | "Rafael Oliveira"| "rafael.oliveira"       | "Monitor#2024" | "Monitor#2024"   |
      | "Beatriz Lima"   | "beatriz@.com.br"       | "LimaBeta@1"   | "LimaBeta@1"     |
      | "Tiago Alves"    | "tiago@exemplo"         | "Alves&Tiago"  | "Alves&Tiago"    |
      | "Sofia Andrade"  | "sofia andrade@web.com" | "AndradeS2"    | "AndradeS2"      |