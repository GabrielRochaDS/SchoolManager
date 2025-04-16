package com.gabrielrocha;

import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.exception.RemocaoNaoAutorizada;
import com.gabrielrocha.model.Professor;
import com.gabrielrocha.model.Turma;
import com.gabrielrocha.service.ProfessorService;
import corejava.Console;

public class PrincipalProfessor {
    private final ProfessorService professorService = new ProfessorService();

    public void principal(){
        int id;
        String nome;
        String email;
        boolean continua = true;

        while (continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma novo professor");
            System.out.println("2. Remover um professor");
            System.out.println("3. Listar todos os professores");
            System.out.println("4. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
            System.out.println();

            switch (opcao){
                case 1-> {
                    nome = Console.readLine("Informe o nome do professor: ");
                    email = Console.readLine("Informe o email do professor: ");

                    Professor umProfessor = new Professor(nome, email);
                    professorService.incluir(umProfessor);
                }
                case 2-> {
                    id = Console.readInt("Digite o id do Professor a ser removido: ");

                    try {
                        professorService.recuperarPorId(id);
                    }catch (EntidadeNaoEncontradaException e){
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    try {
                        professorService.remover(id);
                    } catch (RemocaoNaoAutorizada e) {
                        System.out.println('\n' + e.getMessage());
                    }

                }
                case 3-> {
                    for(Professor professor: professorService.recuperarTodos()){
                        System.out.println(professor.toString());
                        if(!professor.getTurma().isEmpty()){
                            System.out.println("Turmas do professor:");
                            for(Turma turma: professor.getTurma()){
                                System.out.println(turma.getDisciplina().getNome()+ '\n' + turma.toString() + '\n');
                            }
                        }
                    }
                }
                case 4 ->{
                    continua = false;
                }
                default -> System.out.println("Opcao invalida" + '\n');
            }
        }

    }

}
