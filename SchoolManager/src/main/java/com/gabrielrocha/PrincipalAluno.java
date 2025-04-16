package com.gabrielrocha;

import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.exception.RemocaoNaoAutorizada;
import com.gabrielrocha.model.Aluno;
import com.gabrielrocha.model.Inscricao;
import com.gabrielrocha.service.AlunoService;
import corejava.Console;

import java.util.List;

public class PrincipalAluno {
    private final AlunoService alunoService = new AlunoService();

    public void principal(){
        Aluno umAluno;
        int idAluno;
        String nome;
        String email;
        boolean continua = true;

        while (continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar um aluno");
            System.out.println("2. Remover um aluno");
            System.out.println("3. Listar todos os alunos");
            System.out.println("4. Listar todas as inscricoes de um aluno");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");
            System.out.println();

            switch (opcao){
                case 1 -> {
                    nome = Console.readLine("Informe o nome do aluno: ");
                    email = Console.readLine("Informe o email do aluno: ");

                    umAluno = new Aluno(nome, email);

                    alunoService.incluir(umAluno);
                }

                case 2 -> {
                    idAluno = Console.readInt("Informe o Id do aluno a ser removido:");

                    try {
                        alunoService.recuperarPorId(idAluno);
                    }catch (EntidadeNaoEncontradaException e){
                        System.out.println('\n' + e.getMessage());
                        break;
                    }


                    try {
                        alunoService.remover(idAluno);
                        System.out.println("Aluno com id " + idAluno + " removido com ssucesso" + '\n');
                    } catch (RemocaoNaoAutorizada e) {
                        System.out.println('\n' + e.getMessage());
                    }


                }

                case 3-> {
                        for(Aluno aluno: alunoService.recuperarTodos()){
                            System.out.println(aluno.toString() + '\n');
                            if(!aluno.getInscricaos().isEmpty()){
                                System.out.println("Inscricoes do aluno:" + '\n');
                                for (Inscricao inscricao: aluno.getInscricaos()){
                                    System.out.println("Disciplina: " + inscricao.getTurma().getDisciplina().getNome() + '\n' + inscricao.toString() );
                                }
                            }
                        }
                }

                case 4->{
                    idAluno = Console.readInt("Informe o Id do aluno para listar as inscricoes do mesmo:" + '\n');

                    try {
                        List<Inscricao> lista = alunoService.recuperarPorId(idAluno).getInscricaos();
                        for(Inscricao inscricao: lista){
                            System.out.println(inscricao.toString());
                        }

                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println('\n' + e.getMessage());
                    }

                }

                case 5->{
                    continua = false;
                }
                default -> {
                    System.out.println('\n' + "Opcao invalida");
                }

            }


        }

    }


}
