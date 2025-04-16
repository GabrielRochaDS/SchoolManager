package com.gabrielrocha;

import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.exception.RemocaoNaoAutorizada;
import com.gabrielrocha.model.Disciplina;
import com.gabrielrocha.service.DisciplinaService;
import corejava.Console;

import java.util.List;

public class PrincipalDisciplina {
    private final DisciplinaService disciplinaService = new DisciplinaService();

    public void principal(){
        int id;
        int cargaHoraria;
        String nome;
        boolean continua = true;

        while (continua){
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar ums nova disciplina");
            System.out.println("2. Cadastrar um novo pre-requisito");
            System.out.println("3. Remover uma disciplina");
            System.out.println("4. Listar todos as disciplinas");
            System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");
            System.out.println();

            switch (opcao){
                case 1-> {
                    nome = Console.readLine("Informe o nome da disciplina: ");
                    cargaHoraria = Console.readInt("Informe a carga horaria da disciplina: ");

                    Disciplina disciplina = new Disciplina(nome, cargaHoraria);
                    disciplinaService.incluir(disciplina);
                }
                case 2->{
                    List<Disciplina> disciplinas = disciplinaService.recuperarTodos();
                    for(Disciplina disciplina: disciplinas){
                        System.out.println(disciplina.toString() + '\n');
                    }
                    id = Console.readInt('\n' + "Digite o id da disciplina: ");
                    Disciplina disc;
                    try {
                        disc = disciplinaService.recuperarPorId(id);
                    }catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    for(Disciplina disciplina: disciplinas){
                        if(disciplina != disc){
                            System.out.println(disciplina.toString() + '\n');
                        }
                    }
                    id = Console.readInt("Digite o id do pre-requisito dentre os acima: ");
                    try {
                        Disciplina requisito = disciplinaService.recuperarPorId(id);
                        disc.getRequisitos().add(requisito);
                    }catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }

                }

                case 3-> {
                    id = Console.readInt("Digite o id da disciplina a ser removida: ");

                    try {
                        disciplinaService.recuperarPorId(id);
                    }catch (EntidadeNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                        break;
                    }

                    try {
                        disciplinaService.remover(id);
                    } catch (RemocaoNaoAutorizada e) {
                        System.out.println('\n' + e.getMessage());
                    }

                }
                case 4-> {
                    for(Disciplina disciplina: disciplinaService.recuperarTodos()){
                        System.out.println(disciplina.toString());
                        if(!disciplina.getRequisitos().isEmpty()){
                            System.out.println("Requisitos:" + '\b');
                            for(Disciplina requisito: disciplina.getRequisitos()){
                                System.out.println(requisito.getNome() + '\n');
                            }
                        }
                        System.out.println();
                    }
                }
                case 5 ->{
                    continua = false;
                }
                default -> System.out.println("Opcao invalida" + '\n');
            }
        }

    }

}

