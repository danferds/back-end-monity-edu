# language: pt

Funcionalidade: Emitir Certificado
  Como um monitor,
    eu quero gerar um certificado validado para cada monitoria que eu realizei,
    para que eu possa comprovar minhas horas de atividade complementar.

  Cenário: Monitor emite certificado de monitoria que ele realizou
    Dado que o monitor "Thiago Rocha" está autenticado no sistema
    E ele cadastrou a monitoria "Física - Leis de Newton"
    E a monitoria está com status "REALIZADA"
    Quando Thiago solicita a emissão do certificado
    Então o sistema deve gerar um certificado contendo:
      | Campo                | Valor Esperado                        |
      | Nome do Monitor      | Thiago Rocha                          |
      | Título da Monitoria  | Física - Leis de Newton               |
      | Matéria              | Física                                |
      | Data                 | 2025-07-15                            |
      | Carga Horária        | 2 horas                               |
      | Nome da Plataforma   | monityEdu                             |
      | Data/Hora de Emissão | (data atual)                          |
      | ID de Validação      | UUID válido gerado automaticamente    |

  Cenário: Monitor tenta emitir certificado para monitoria com status PENDENTE
    Dado que o monitor "Alexandre Silva" está autenticado no sistema
    E ele cadastrou a monitoria "Matemática - Geometria Plana"
    E a monitoria está com status "PENDENTE"
    Quando ele solicita a emissão do certificado
    Então o sistema deve rejeitar a ação
    E informar que o certificado só pode ser emitido para monitorias realizadas