package com.gabrielrocha;

import com.gabrielrocha.dao.*;
import com.gabrielrocha.model.*;
import com.gabrielrocha.util.FabricaDeDaos;
import corejava.Console;

import java.io.*;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        PrincipalAluno principalAluno = new PrincipalAluno();
        PrincipalInscricao principalInscricao = new PrincipalInscricao();
        PrincipalTurma principalTurma = new PrincipalTurma();
        PrincipalProfessor principalProfessor = new PrincipalProfessor();
        PrincipalDisciplina principalDisciplina = new PrincipalDisciplina();
        PrincipalRelatorio principalRelatorio = new PrincipalRelatorio();

        recuperarDados();

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Alunos");
            System.out.println("2. Tratar Inscricao");
            System.out.println("3. Tratar Turmas");
            System.out.println("4. Tratar Professores");
            System.out.println("5. Tratar Disciplinas");
            System.out.println("6. Relatorios");
            System.out.println("7. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 7:");

            switch (opcao){
                case 1->{
                    principalAluno.principal();
                }
                case 2->{
                    principalInscricao.principal();
                }
                case 3->{
                    principalTurma.principal();
                }
                case 4->{
                    principalProfessor.principal();
                }
                case 5->{
                    principalDisciplina.principal();
                }
                case 6->{
                    principalRelatorio.principal();
                }
                case 7-> {
                    continua = false;
                    salvarDados();
                }

                default -> System.out.println("Opcao invalida" + '\n');
            }

        }
    }
    private static void recuperarDados() {
        try {
            AlunoDAO alunoDAO = FabricaDeDaos.getDAO(AlunoDAO.class);
            DisciplinaDAO disciplinaDAO = FabricaDeDaos.getDAO(DisciplinaDAO.class);
            InscricaoDAO inscricaoDAO = FabricaDeDaos.getDAO(InscricaoDAO.class);
            ProfessorDAO professorDAO = FabricaDeDaos.getDAO(ProfessorDAO.class);
            TurmaDAO turmaDAO = FabricaDeDaos.getDAO(TurmaDAO.class);




            FileInputStream fis = new FileInputStream(new File("meusObjetos.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            Map<Integer, Aluno> mapDeAlunos = (Map<Integer, Aluno>) ois.readObject();
            alunoDAO.setMap(mapDeAlunos);
            Integer contadorDeAlunos = (Integer) ois.readObject();
            alunoDAO.setContador(contadorDeAlunos);

            Map<Integer, Disciplina> mapDeDisciplina = (Map<Integer, Disciplina>) ois.readObject();
            disciplinaDAO.setMap(mapDeDisciplina);
            Integer contadorDeDisciplina = (Integer) ois.readObject();
            disciplinaDAO.setContador(contadorDeDisciplina);

            Map<Integer, Inscricao> mapDeInscricao = (Map<Integer, Inscricao>) ois.readObject();
            inscricaoDAO.setMap(mapDeInscricao);
            Integer contadorDeInscricao = (Integer) ois.readObject();
            inscricaoDAO.setContador(contadorDeInscricao);

            Map<Integer, Professor> mapDeProfessor = (Map<Integer, Professor>) ois.readObject();
            professorDAO.setMap(mapDeProfessor);
            Integer contadorDeProfessores = (Integer) ois.readObject();
            professorDAO.setContador(contadorDeProfessores);

            Map<Integer, Turma> mapDeTurma = (Map<Integer, Turma>) ois.readObject();
            turmaDAO.setMap(mapDeTurma);
            Integer contadorDeTurmas = (Integer) ois.readObject();
            turmaDAO.setContador(contadorDeTurmas);

        } catch (FileNotFoundException e) {
            System.out.println("O arquivo meusObjetos.txt foi criado.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void salvarDados() {
        AlunoDAO alunoDAO = FabricaDeDaos.getDAO(AlunoDAO.class);
        DisciplinaDAO disciplinaDAO = FabricaDeDaos.getDAO(DisciplinaDAO.class);
        InscricaoDAO inscricaoDAO = FabricaDeDaos.getDAO(InscricaoDAO.class);
        ProfessorDAO professorDAO = FabricaDeDaos.getDAO(ProfessorDAO.class);
        TurmaDAO turmaDAO = FabricaDeDaos.getDAO(TurmaDAO.class);

        Map<Integer, Aluno> mapDeAlunos = alunoDAO.getMap();
        Map<Integer, Disciplina> mapDeDisciplina = disciplinaDAO.getMap();
        Map<Integer, Inscricao> mapDeInscicao = inscricaoDAO.getMap();
        Map<Integer, Professor> mapDeProfessores = professorDAO.getMap();
        Map<Integer, Turma> mapDeTurmas = turmaDAO.getMap();

        Integer contadorDeAlunos = alunoDAO.getContador();
        Integer contadorDeDisciplina = disciplinaDAO.getContador();
        Integer contadorDeInscricao = inscricaoDAO.getContador();
        Integer contadorDeProfessores = professorDAO.getContador();
        Integer contadorDeTurmas = turmaDAO.getContador();

        try {
            FileOutputStream fos = new FileOutputStream(new File("meusObjetos.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapDeAlunos);
            oos.writeObject(contadorDeAlunos);

            oos.writeObject(mapDeDisciplina);
            oos.writeObject(contadorDeDisciplina);

            oos.writeObject(mapDeInscicao);
            oos.writeObject(contadorDeInscricao);

            oos.writeObject(mapDeProfessores);
            oos.writeObject(contadorDeProfessores);

            oos.writeObject(mapDeTurmas);
            oos.writeObject(contadorDeTurmas);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}