# Digytal Code - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br) 

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)


#### Autores
- [Gleyson Sampaio](https://github.com/glysns)

## Spring Send Email
Projeto com a finalidade de enviar e-mails utilizando Java Spring Boot

### Estrutura do Projeto
Dividimos as classes em pacotes de acordo com suas responsabilidades.
- Model: onde definimos os modelos ou seja as classes dos objetos que usamos no sistema
- Service: onde definimos as regras de negócio para manipulação dos Models
- Util: onde classes utilitárias

### Classes Utilitárias

| Classe  | Descrição |
| ------------- | ------------- |
| code.send.email.model.Mensagem  | Classe que representa uma Mensagem gerada no sistema
| code.send.email.service.SendEmailService  | Classe que recebe a mensagem como parametro e enviar o e-mail através de um serviço SMTP como Gmail
| code.send.email.util.FileReaderUtil  | Classe utilitária para leitura de arquivos caso necessite enviar e-mail em lotes

