package com.gabrielrocha;

import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.model.Disciplina;
import com.gabrielrocha.model.Inscricao;
import com.gabrielrocha.model.Turma;
import com.gabrielrocha.service.*;
import corejava.Console;

import java.util.List;

public class PrincipalRelatorio {
    private final TurmaService turmaService = new TurmaService();
    private final ProfessorService professorService = new ProfessorService();
    private final DisciplinaService disciplinaService = new DisciplinaService();
    private final InscricaoService inscricaoService = new InscricaoService();
    private final AlunoService alunoService = new AlunoService();

    public void principal(){
        int id;
        boolean continua = true;

        while (continua){

            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "Qual relatorio deseja ver?");
            System.out.println('\n' + "1. Para uma determinada disciplina quais são seus pré-requisitos.");
            System.out.println("2.  Para uma determinada turma, a relação de alunos inscritos.");
            System.out.println("3. Para um determinado ano e período, a quantidade de alunos aprovados por disciplina.");
            System.out.println("4. Para um determinado ano e período, o percentual de aprovação de alunos por turma.");
            System.out.println("5. Sair.");


            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
            System.out.println();

            switch (opcao){
                case 1->{
                    List<Disciplina> disciplinas = disciplinaService.recuperarTodos();
                    for(Disciplina disciplina: disciplinas){
                        System.out.println(disciplina.toString() + '\n');
                    }
                    id = Console.readInt('\n' + "Digite o id da disciplina: ");
                    Disciplina disc;
                    try {
                        disc = disciplinaService.recuperarPorId(id);
                        for(Disciplina eRequisito: disc.getRequisitos()){
                            System.out.println(eRequisito.getNome() + '\n');
                        }
                    }catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 2->{
                    List<Turma> turmas = turmaService.recuperarTodos();
                    for(Turma turma: turmas){
                        System.out.println("Disciplina: " + turma.getDisciplina().getNome() + " id: " + turma.getId() );
                    }
                    id = Console.readInt('\n' + "Digite o id da turma: ");

                    try {
                        Turma turma = turmaService.recuperarPorId(id);

                        System.out.println("Nome da disciplina: " + turma.getDisciplina().getNome() + "  Professor: " + turma.getProfessor().getNome() + '\n');
                        System.out.println("Alunos inscritos:" + '\n');
                        for(Inscricao inscricao: turma.getInscricaos()){
                            System.out.println("Nome: " + inscricao.getAluno().getNome() + "  Nota: " + inscricao.getNota() + '\n');
                        }


                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 3 ->{
                    int ano = Console.readInt("Digite o ano: ");
                    int periodo = Console.readInt("Digire o periodo: ");

                    try {
                        List<Disciplina> disciplinas = disciplinaService.recuperarTodos();
                        for(Disciplina disciplina: disciplinas){
                            for(Turma turma: disciplina.getTurmas()){
                                if(turma.getPeriodo() == periodo && turma.getAno() == ano){
                                    int aprovados = 0;
                                    for(Inscricao inscricao: turma.getInscricaos()){
                                        if(inscricao.getNota() >= 6) aprovados++;
                                    }
                                    System.out.println("Disciplina: " + disciplina.getNome() + "  Quantidade de Aprovados: " + aprovados);
                                }
                            }
                        }

                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }


                }
                case 4->{
                    int ano = Console.readInt("Digite o ano: ");
                    int periodo = Console.readInt("Digire o periodo: ");

                    try {
                        List<Turma> turmas = turmaService.recuperarTodos();
                        for(Turma turma: turmas){
                            if(turma.getPeriodo() == periodo && turma.getAno() == ano){
                                float aprovados = 0;
                                float total = 0;
                                for(Inscricao inscricao: turma.getInscricaos()){
                                    if(inscricao.getNota() >= 6) aprovados++;
                                    total++;
                                }
                                System.out.println("Turma: " + turma.toString() + '\n' + "Porcentagem de aprovados: " + aprovados/total*100 + '%');
                            }
                        }

                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }
                }
                case 5-> continua = false;

                default -> System.out.println("Opcao invalida!"+'\n');

            }

        }

    }
}
