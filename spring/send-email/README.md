# Digytal Code - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br) 

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)


#### Colaboradores
- [Gleyson Sampaio](https://github.com/glysns)

#### Requisito
Precisa enviar e-mail da sua aplicação Spring ?

## Spring Send Email
Projeto com a finalidade de enviar e-mails utilizando Java Spring Boot

### Estrutura do Projeto
Dividimos as classes em pacotes de acordo com suas responsabilidades.
- Model: onde definimos os modelos ou seja as classes dos objetos que usamos no sistema
- Service: onde definimos as regras de negócio para manipulação dos Models
- Util: Contém classes utilitárias

### Classes Utilitárias

| Classe  | Descrição |
| ------------- | ------------- |
| code.send.email.model.Mensagem  | Classe que representa uma Mensagem gerada no sistema
| code.send.email.service.SendEmailService  | Classe que recebe a mensagem como parametro e enviar o e-mail através de um serviço SMTP como Gmail
| code.send.email.util.FileReaderUtil  | Classe utilitária para leitura de arquivos caso necessite enviar e-mail em lotes
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







