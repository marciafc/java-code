# Digytal Code - Programação, Pesquisa e Educação
[www.digytal.com.br](http://www.digytal.com.br) 

[(11) 95894-0362](https://api.whatsapp.com/send?phone=5511958940362)


#### Colaboradores
- [Gleyson Sampaio](https://github.com/glysns)

#### Requisitos
- Noções de uso/compilação na ferramenta Jasper Reports
- Ter um arquivo .jasper


#### Finalidade
- Gerar relatórios no formato pdf em disco

## Jasper Reports Generator
Projeto com a finalidade de facilitar a geração de arquivos .pdf com base em relatórios confeccionados pela ferramenta Jasper Reports

### Estrutura do Projeto
Dividimos as classes em pacotes de acordo com suas responsabilidades.
- Model: onde definimos os modelos ou seja as classes dos objetos que usamos no sistema
- Component: pacote que contém o componente de geração de relatórios
- Util: pacote para organizar as classes utilitárias

### Classes Utilitárias

| Classe  | Descrição |
| ------------- | ------------- |
| code.jasper.reports.component.JasperReportsGenerator  | Classe responsável pela engine de geração de documentos .pdf
| code.jasper.reports.model.Integrante  | Classe que representa uma estrutura de dados para ser impressa como uma lista detalhe no relatório
| code.jasper.reports.util.FileUtil  | Classe auxiliar para disponibilizar recursos relacionados a diretórios e arquivos para geração dos .pdfs
| code.jasper.reports.util.ReportFormat  | Enum auxiliar caso queira atuar com outro formato de relatório


### Teste

Dentro da pasta `src/resources` temos uma pasta chamada `reports` contendo dois arquivos.
1. IntegrantesRpts.jrxml  : Arquivo com modelo de edição de um relatório com Jasper.
2. IntegrantesRpts.jasper : Arquivo compilado para geração de arquivos .pdf pela classe `JasperReportsGenerator`




###### #java #jasper_reports #relatorio







