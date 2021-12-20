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
| controle.acesso.infra.doc.SwaggerConfig  | Classe responsável pela documentação da API  |
| controle.acesso.infra.security.jwt.JWTObject  | Classe que representa um Objeto correspondente a estrutura JWT  |
| controle.acesso.infra.security.jwt.JWTCreator  | Classe responsável por gerar o Token com base no Objeto e ou instanciar o Objeto JWT com base no Token   |

