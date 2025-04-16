package com.gabrielrocha;

import com.gabrielrocha.dao.InscricaoDAO;
import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.exception.RemocaoNaoAutorizada;
import com.gabrielrocha.model.Aluno;
import com.gabrielrocha.model.Inscricao;
import com.gabrielrocha.model.Professor;
import com.gabrielrocha.model.Turma;
import com.gabrielrocha.service.AlunoService;
import com.gabrielrocha.service.InscricaoService;
import com.gabrielrocha.service.TurmaService;
import com.gabrielrocha.util.FabricaDeDaos;
import corejava.Console;

import java.util.List;

public class PrincipalInscricao {
    private final InscricaoService inscricaoService = new InscricaoService();
    private final AlunoService alunoService = new AlunoService();
    private final TurmaService turmaService = new TurmaService();

    public void principal(){
        Inscricao umaInscricao;
        String data;
        int idInscricao;
        int idAluno;
        int idTurma;
        int nota;
        boolean continua = true;

        while (continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma inscricao");
            System.out.println("2. Remover uma inscricao");
            System.out.println("3. Listar todos as inscricoes");
            System.out.println("4. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
            System.out.println();


            //==============//TRATAR NOTA MAIOR QUE 10//==============//
            switch (opcao){
                case 1->{
                    data = Console.readLine("Informe a data da inscricao: ");
                    nota = Console.readInt("Informe a nota do aluno: ");

                    List<Aluno> alunos = alunoService.recuperarTodos();
                    for(Aluno aux: alunos){
                        System.out.println("Aluno: " + aux.getNome()+ "  id: " + aux.getId());
                    }
                    idAluno = Console.readInt('\n' + "Digite o id do aluno a ser inscrito: " + '\n');
                    Aluno aluno = null;
                    try {
                        aluno = alunoService.recuperarPorId(idAluno);
                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    List<Turma> turmas = turmaService.recuperarTodos();
                    for(Turma aux: turmas){
                        System.out.println("Turma id: " + aux.getId() + "  Nome: " + aux.getDisciplina().getNome());
                    }
                    idTurma = Console.readInt("Informe o id da turma a ser inscrita: ");
                    Turma turma = null;
                    try {
                        turma = turmaService.recuperarPorId(idTurma);
                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println('\n' + e.getMessage());
                        break;
                    }


                    umaInscricao = new Inscricao(aluno, turma, data, nota);
                    inscricaoService.incluir(umaInscricao);

                }

                case 2->{
                    idInscricao = Console.readInt("Informe o id da inscricao a ser removida: ");

                    try {
                        inscricaoService.recuperarPorId(idInscricao);
                    }catch (EntidadeNaoEncontradaException e){
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    try {
                        inscricaoService.remover(idInscricao);
                    } catch (RemocaoNaoAutorizada e) {
                        System.out.println('\n' + e.getMessage());
                    }


                }

                case 3->{
                    for(Inscricao inscricao: inscricaoService.recuperarTodos()){
                        System.out.println(inscricao.toString());
                    }
                }
                case 4->{
                    continua = false;
                }
                default -> {
                    System.out.println("Opcao invalida" + '\n');
                }
            }
        }

    }

}
