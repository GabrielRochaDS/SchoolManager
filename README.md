# **SchoolManager**  

**Sistema Acadêmico para Gestão de Alunos, Professores, Disciplinas e Turmas**  

O **SchoolManager** é um projeto Java que simula um sistema acadêmico completo, permitindo o gerenciamento de alunos, professores, disciplinas, turmas e inscrições. Desenvolvido seguindo boas práticas de arquitetura em camadas, o sistema oferece operações CRUD, validações de regras de negócio e geração de relatórios.  

---

##  Funcionalidades  

✔ **Gerenciamento de Alunos**  
- Cadastro, edição, remoção e listagem de alunos.  
- Consulta de inscrições em turmas.  

✔ **Gerenciamento de Professores**  
- Cadastro, remoção e listagem de professores.  
- Associação de professores a turmas.  

✔ **Gerenciamento de Disciplinas**  
- Cadastro e remoção de disciplinas.  
- Definição de pré-requisitos para matrícula.  

✔ **Gerenciamento de Turmas**  
- Criação de turmas vinculadas a disciplinas e professores.  
- Controle de vagas e horários.  

✔ **Inscrições**  
- Matrícula de alunos em turmas, com validação de pré-requisitos.  
- Cancelamento de matrículas.  

✔ **Relatórios**  
- Listagem de alunos por turma.  
- Estatísticas de aprovação/reprovação.  
- Consulta de pré-requisitos de disciplinas.  

---

## ** Arquitetura e Tecnologias**  

O projeto segue uma **arquitetura em camadas**, organizada da seguinte forma:  

- **`model`**: Classes de entidade (`Aluno`, `Professor`, `Disciplina`, `Turma`, `Inscricao`).  
- **`dao`**: Camada de persistência simulada com `Map` (Data Access Object).  
- **`service`**: Lógica de negócios (validações, regras de inscrição).  
- **`exception`**: Tratamento personalizado de erros (ex: `EntidadeNaoEncontradaException`).  
- **Interface (`Principal`)**: Menus interativos via console.  

### **Tecnologias e Bibliotecas**  
- **Linguagem**: Java (JDK 11+)  
- **Persistência**: Dados em memória (simulados com `HashMap`).  
- **I/O**: Entrada e saída via console (`corejava.Console`).  

