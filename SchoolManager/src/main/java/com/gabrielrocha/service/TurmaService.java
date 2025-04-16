package com.gabrielrocha.service;

import com.gabrielrocha.dao.*;
import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.model.*;
import com.gabrielrocha.util.FabricaDeDaos;

import java.util.List;

public class TurmaService {
    private final TurmaDAO turmaDAO = FabricaDeDaos.getDAO(TurmaDAO.class);
    private final ProfessorDAO professorDAO = FabricaDeDaos.getDAO(ProfessorDAO.class);
    private final DisciplinaDAO disciplinaDAO = FabricaDeDaos.getDAO(DisciplinaDAO.class);
    private final InscricaoDAO inscricaoDAO = FabricaDeDaos.getDAO(InscricaoDAO.class);
    private final AlunoDAO alunoDAO = FabricaDeDaos.getDAO(AlunoDAO.class);

    public void incluir(Turma turma){

        Professor professor = professorDAO.recuperarPorId(turma.getProfessor().getId());
        professor.getTurma().add(turma);

        Disciplina disciplina = disciplinaDAO.recuperarPorId(turma.getDisciplina().getId());
        disciplina.getTurmas().add(turma);

        turmaDAO.incluir(turma);
    }
    public void remover(int id){
        Turma turma = turmaDAO.recuperarPorId(id);
        if(turma == null){
            throw new EntidadeNaoEncontradaException("Nao existe turma com esse id");
        }


        Professor professor = professorDAO.recuperarPorId(turma.getProfessor().getId());
        professor.getTurma().remove(turma);


        Disciplina disciplina = disciplinaDAO.recuperarPorId(turma.getDisciplina().getId());
        disciplina.getTurmas().remove(turma);


        for(Inscricao inscricao: turma.getInscricaos()){
            Aluno aluno = inscricao.getAluno();
            aluno.getInscricaos().remove(inscricao);
            inscricaoDAO.remover(inscricao.getId());
        }

    }

    public Turma recuperarPorId(int id){
        if(turmaDAO.recuperarPorId(id) == null){
            throw new EntidadeNaoEncontradaException("Nao existe turma com esse id");
        }
        else {
            return turmaDAO.recuperarPorId(id);
        }
    }

    public List<Turma> recuperarTodos(){
        return turmaDAO.recuperarTodos();
    }

}
