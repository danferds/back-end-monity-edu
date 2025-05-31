# language: pt

Funcionalidade: Listar Certificados

  Como MONITOR,
  Desejo visualizar a lista de todos os meus certificados no sistema
  E cada certificado da lista deve exibir as seguintes informações sobre a monitoria: título, matéria e data,
  Para que eu possa acessar, acompanhar e utilizar esses documentos sempre que necessário.

  Contexto: Monitor logado com certificados gerados automaticamente
    Dado que o monitor "roberto.dias@email.com" está logado na plataforma
    E o sistema gerou automaticamente os seguintes certificados para o monitor "roberto.dias@email.com" por monitorias de realizadas e confirmadas:
      | ID Certificado | Título da Monitoria                      | Matéria | Data Monitoria | Carga Horária | Data Emissão Cert. |
      | "CERT_FIS_001" | "Termodinâmica e suas Leis"              | "Física"| "01/10/2025"   | "2 horas"     | "02/10/2025"       |
      | "CERT_FIS_002" | "Cinemática Vetorial e Lançamentos"      | "Física"| "05/10/2025"   | "1.5 horas"   | "06/10/2025"       |
      | "CERT_FIS_003" | "Ondulatória: Fenômenos e Propriedades"  | "Física"| "10/09/2025"   | "1 hora"      | "11/09/2025"       |
    E existe outro monitor "ana.gomes@email.com" com seu próprio certificado "CERT_OUTRO_001" para uma monitoria de "Química" sobre "Cálculos Estequiométricos"
    E o monitor "roberto.dias@email.com" acessa a seção "Certificados"

  Cenário: Monitor visualiza a lista de seus certificados com título, matéria e data da monitoria
    Quando o monitor visualiza a lista de certificados
    Então ele deve ver 3 certificados listados
    E para o certificado "CERT_FIS_001", a lista deve exibir o título da monitoria "Termodinâmica e suas Leis", a matéria "Física", a data da monitoria "01/10/2025" e uma opção para acessar o certificado
    E para o certificado "CERT_FIS_002", a lista deve exibir o título da monitoria "Cinemática Vetorial e Lançamentos", a matéria "Física", a data da monitoria "05/10/2025" e uma opção para acessar o certificado
    E para o certificado "CERT_FIS_003", a lista deve exibir o título da monitoria "Ondulatória: Fenômenos e Propriedades", a matéria "Física", a data da monitoria "10/09/2025" e uma opção para acessar o certificado

  Cenário: Monitor sem certificados disponíveis visualiza lista vazia
    Dado que o monitor "roberto.dias@email.com" não possui nenhum certificado gerado no sistema
    Quando o monitor visualiza a lista de certificados na seção "Certificados"
    Então ele deve ver uma mensagem indicando "Você ainda não possui certificados."

  Cenário: Monitor visualiza apenas seus próprios certificados
    Quando o monitor "roberto.dias@email.com" visualiza a lista de certificados
    Então ele deve ver os certificados com os títulos "Termodinâmica e suas Leis", "Cinemática Vetorial e Lançamentos" e "Ondulatória: Fenômenos e Propriedades"
    Mas ele NÃO deve ver nenhum certificado referente à monitoria de "Química" sobre "Cálculos Estequiométricos" da monitora "ana.gomes@email.com"

  Esquema do Cenário: Monitor filtra seus certificados por período da monitoria
    Quando o monitor aplica um filtro de "Data da Monitoria" entre "<Data Inicial>" e "<Data Final>"
    Então a lista de certificados deve exibir <QuantidadeEsperada> certificado(s)
    E cada certificado exibido deve ser de uma monitoria realizada dentro do período informado e deve apresentar título, matéria e data

    Exemplos:
      | Data Inicial | Data Final   | QuantidadeEsperada | Títulos Esperados                                               |
      | "01/09/2025" | "30/09/2025" | 1                  | "Ondulatória: Fenômenos e Propriedades"                         |
      | "01/10/2025" | "31/10/2025" | 2                  | "Termodinâmica e suas Leis", "Cinemática Vetorial e Lançamentos"|
      | "01/11/2025" | "30/11/2025" | 0                  | Nenhum                                                          |


  Cenário: Monitor clica em um certificado na lista e acessa o documento
    Quando o monitor visualiza a lista de certificados
    E o monitor clica na opção de acessar o certificado referente à monitoria "Termodinâmica e suas Leis" (ID "CERT_FIS_001")
    Então o sistema deve exibir ou iniciar o download do documento do certificado "CERT_FIS_001"
    E o documento deve estar em formato PDF e conter os detalhes corretos da monitoria "Termodinâmica e suas Leis" (matéria "Física", data "01/10/2025", carga horária "2 horas") e do monitor "roberto.dias@email.com"