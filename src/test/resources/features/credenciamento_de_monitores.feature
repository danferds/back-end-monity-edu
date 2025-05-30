# language: pt

Funcionalidade: Credenciamento de Monitores 

  Como um MONITOR,
  Desejo informar minha instituição de ensino, curso, período, e-mail institucional e enviar o comprovante de vínculo acadêmico,
  A fim de me credenciar na plataforma e aguardar aprovação para atuar como monitor.

  Contexto: Monitor logado e na página de credenciamento
    Dado que o monitor "ana.beatriz@exemplo.com" está logado na plataforma
    E o monitor está na página de "Verificação de Credenciamento"

  Cenário: Credenciamento de monitor bem-sucedido com todos os dados e comprovante válido
    Quando o monitor preenche o campo "Instituição de Ensino" com "Universidade Federal do Ceará"
    E o monitor preenche o campo "Curso" com "Engenharia de Software"
    E o monitor preenche o campo "Período Atual" como "5º Semestre"
    E o monitor preenche o campo "E-mail Institucional" com "ana.beatriz@alu.ufc.br"
    E o monitor anexa o arquivo "comprovante_vinculo.pdf" no campo "Comprovante de Vínculo Acadêmico"
    E o monitor clica no botão "Enviar"
    Então o monitor deve ver uma mensagem de sucesso "Seus dados foram enviados para análise. Aguarde a aprovação."
    E o status do credenciamento do monitor deve ser atualizado para "Aguardando Aprovação"

  Cenário: Tentativa de credenciamento de monitor sem preencher campos obrigatórios
    Quando o monitor preenche o campo "Instituição de Ensino" com "Instituto Federal do Ceará"
    # Curso não preenchido e comprovante não anexado
    E o monitor preenche o campo "Período Atual" como "3º Semestre"
    E o monitor preenche o campo "E-mail Institucional" com "carlos.edu@aluno.ifce.edu.br"
    E o monitor clica no botão "Enviar"
    Então o monitor deve ver uma mensagem de erro indicando "Por favor, preencha todos os campos obrigatórios e anexe o comprovante."
    E o monitor deve permanecer na página de "Verificação de Credenciamento"

  Cenário: Tentativa de credenciamento com formato de e-mail institucional inválido
    Quando o monitor preenche o campo "Instituição de Ensino" com "Universidade Estadual do Ceará"
    E o monitor preenche o campo "Curso" com "História"
    E o monitor preenche o campo "Período Atual" como "7º Semestre"
    E o monitor preenche o campo "E-mail Institucional" com "mariana.costa@gmail.com"
    E o monitor anexa o arquivo "comprovante_vinculo.pdf" no campo "Comprovante de Vínculo Acadêmico"
    E o monitor clica no botão "Enviar"
    Então o monitor deve ver uma mensagem de erro "O e-mail institucional informado não parece ser válido ou não pertence a uma instituição de ensino reconhecida."
    E o monitor deve permanecer na página de "Verificação de Credenciamento"

  Cenário: Tentativa de credenciamento com arquivo de comprovante excedendo o limite de tamanho
    Quando o monitor preenche o campo "Instituição de Ensino" com "Universidade Federal da Bahia"
    E o monitor preenche o campo "Curso" com "Biologia"
    E o monitor preenche o campo "Período Atual" como "8º Semestre"
    E o monitor preenche o campo "E-mail Institucional" com "beatriz.souza@academico.ufba.br"
    E o monitor tenta anexar um arquivo "comprovante_grande.pdf" de 10MB no campo "Comprovante de Vínculo Acadêmico"
    E o monitor clica no botão "Enviar para Aprovação"
    Então o monitor deve ver uma mensagem de erro "O arquivo excede o tamanho máximo permitido de 5MB."
    E o monitor deve permanecer na página de "Verificação de Credenciamento"
    E o campo "Comprovante de Vínculo Acadêmico" deve estar vazio ou indicar o erro no upload

  Esquema do Cenário: Tentativa de credenciamento com tipo de arquivo de comprovante inválido
    Quando o monitor preenche o campo "Instituição de Ensino" com "<instituicao>"
    E o monitor preenche o campo "Curso" com "<curso>"
    E o monitor preenche o "Período Atual" como "<periodo>"
    E o monitor preenche o campo "E-mail Institucional" com "<email_institucional>"
    E o monitor anexa o arquivo "<arquivo_comprovante>" no campo "Comprovante de Vínculo Acadêmico"
    E o monitor clica no botão "Enviar para Aprovação"
    Então o monitor deve ver uma mensagem de erro "Tipo de arquivo inválido. Por favor, envie um PDF, JPG ou PNG."
    E o monitor deve permanecer na página de "Verificação de Credenciamento"

    Exemplos:
      | instituicao                              | curso        | periodo       | email_institucional       | arquivo_comprovante    |
      | "Universidade Federal de Minas Gerais"   | "Geográfia"  | "4º Semestre" | "pedro.gomes@alu.ufmg.br" | "comprovante.docx"     |
      | "Univeridade de São Paulo"               | "Medicina"   | "2º Semestre" | "lais.santos@alu.usp.br"  | "documento.zip"        |
      | "Universidade Federal do Rio de Janeiro" | "Matemática" | "6º Semestre" | "rafael.lima@alu.ufrj.br" | "declaracao.txt"       |
