# Digytal Code - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br) 

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)

## Spring JPA Audit
Projeto com a finalidade de gerenciar as alterações nas entidades mapeadas com o framework JPA Hibernate.

#### Colaboradores
- [Gleyson Sampaio](https://github.com/glysns)

#### Requisitos
* Domínio no Spring Framework
* Noções de Persistência com JPA e Hibernate

## Finalidade
Gerenciar em uma tabela de log as alterações das entidades de persistência na base de dados, mantendo o histórico das colunas e valores anterior e atual dos registros alterados

### Estrutura do Projeto
Dividimos as classes em pacotes de acordo com suas responsabilidades.
- Infra: configurações e classes relacionadas a engine de log de alterações
- Model: onde definimos as classes entidades persistentes no projeto
- Repository: onde definimos as classes que manipulam as entidades vinculadas a base de dados
- Start: Classes que são inicializadas pelo Spring após inicialização

### Classes Utilitárias

| Classe  | Descrição |
| ------------- | ------------- |
| code.jpa.audit.infra.Auditable  | Interface que representa uma entidade auditável
| code.jpa.audit.infra.AuditConfig  | Classe de configuração para o listener de autditoria de persistência
| code.jpa.audit.infra.AuditListener  | Classe que contém a lógica de autditoria
| code.send.email.SendEmailApplication  | Classe principal padrão Springboot contendo um `bean` de CommandLineRunner.

### Configuração do serviços de E-mail

Toda configuração de credencial para acesso a algum serviço de envio de e-mail fica localizado no arquivo `application.propertis` conforme conteúdo abaixo:

```
spring.mail.host=${MAIL_HOST:smtp.gmail.com}
spring.mail.port=${MAIL_PORT:587}
spring.mail.username=${MAIL_USERNAME:seuemail@gmail.com.br}
spring.mail.password=${EMAIL_PASS:nopass}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.ssl.enable=false
spring.mail.test-connection=true
```

O Spring usa expressões ternária para validar as variáveis de ambiente da aplicação.

Serviço de envio de e-mail do Google, [clique aqui](https://support.google.com/a/answer/6260879?hl=pt-BR) para desativar algumas configurações de segurança.

### Teste

Altere o arquivo `resources/emails.csv` e execute a classe `SendEmailApplication`


![](https://github.com/digytal-code/java-code/blob/main/spring/send-email/src/main/resources/teste.jpg)


###### #java #spring #gmail #email







