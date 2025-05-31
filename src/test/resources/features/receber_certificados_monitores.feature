# language: pt

Funcionalidade: Receber Certificados

  Como MONITOR,
  Desejo receber certificados de horas complementares após realizar monitorias,
  Para que eu possa comprovar minha participação e utilizar essas horas para requisitos acadêmicos.

  Contexto: Monitor logado e com histórico de monitorias
    Dado que o monitor "roberto.dias@email.com" está logado na plataforma
    E a monitoria "ID_MON_FIS_REALIZADA_1" de "Física" sobre "Termodinâmica e suas Leis", realizada por "roberto.dias@email.com" com duração de "2 horas" em "01/10/2025", está com status "Realizada" (apta para certificado automático)
    E a monitoria "ID_MON_FIS_REALIZADA_2" de "Física" sobre "Cinemática Vetorial e Lançamentos", realizada por "roberto.dias@email.com" com duração de "1.5 horas" em "05/10/2025", está com status "Realizada" (apta para certificado automático)
    E o sistema gera certificados automaticamente para monitorias "Realizada e Confirmada"
    E o monitor "roberto.dias@email.com" tem a monitoria "ID_MON_FIS_AGENDADA" de "Física" sobre "Leis de Newton e Aplicações" que está "Agendada"
    E o monitor "roberto.dias@email.com" tem a monitoria "ID_MON_FIS_CANCELADA" de "Física" sobre "Óptica Geométrica: Espelhos e Lentes" que foi "Cancelada"

  Cenário: Monitor acessa com sucesso certificado (gerado automaticamente) para uma monitoria realizada
    Quando o monitor acessa a seção "Certificados"
    E o monitor seleciona a opção de visualizar/baixar o certificado da monitoria "ID_MON_FIS_REALIZADA_1"
    Então um certificado deve estar disponível para visualização/download
    E o certificado deve conter o nome do monitor "roberto.dias@email.com"
    E o certificado deve mencionar a monitoria "Física - Termodinâmica e suas Leis"
    E o certificado deve indicar a data "01/10/2025"
    E o certificado deve registrar a carga horária de "Monitoria com 2 horas de duração"
    E o certificado deve registrar um ID único
    E o certificado deve estar em formato PDF

  Cenário: Monitor não encontra certificado disponível para monitoria que ainda não foi realizada
    Quando o monitor acessa a seção "Certificados"
    Então um certificado para a monitoria "ID_MON_FIS_AGENDADA" ("Física - Leis de Newton e Aplicações") não deve estar listado

  Cenário: Monitor não encontra certificado disponível para monitoria que foi cancelada
    Quando o monitor acessa a seção "Certificados"
    Então um certificado para a monitoria "ID_MON_FIS_CANCELADA" ("Física - Óptica Geométrica: Espelhos e Lentes") não deve estar listado

  Cenário: Monitor visualiza lista de certificados disponíveis na sua seção de certificados
    Quando o monitor acessa a seção "Certificados"
    Então o certificado da monitoria "ID_MON_FIS_REALIZADA_1" ("Física - Termodinâmica e suas Leis") deve estar listado e acessível
    E o certificado da monitoria "ID_MON_FIS_REALIZADA_2" ("Física - Cinemática Vetorial e Lançamentos") deve estar listado e acessível
    Mas nenhum certificado para a monitoria "ID_MON_FIS_AGENDADA" deve estar listado ou acessível
    E nenhum certificado para a monitoria "ID_MON_FIS_CANCELADA" deve estar listado ou acessível

  Cenário: Carga horária no certificado acessado corresponde à duração da monitoria de Física
    Quando o monitor acessa e visualiza o certificado da monitoria "ID_MON_FIS_REALIZADA_2" ("Física - Cinemática Vetorial e Lançamentos") na seção "Certificados"
    Então o certificado deve registrar a carga horária de "Monitoria com 1.5 horas de duração"
