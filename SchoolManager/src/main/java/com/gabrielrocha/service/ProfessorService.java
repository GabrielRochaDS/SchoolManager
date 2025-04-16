package com.gabrielrocha.service;

import com.gabrielrocha.dao.ProfessorDAO;
import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.exception.RemocaoNaoAutorizada;
import com.gabrielrocha.model.Professor;
import com.gabrielrocha.util.FabricaDeDaos;

import java.util.List;

public class ProfessorService {
    private final ProfessorDAO professorDAO = FabricaDeDaos.getDAO(ProfessorDAO.class);

    public void incluir(Professor professor){
        professorDAO.incluir(professor);
    }
    public void remover(int id){
        if(!professorDAO.recuperarPorId(id).getTurma().isEmpty()){
            throw new RemocaoNaoAutorizada("Remocao nao autorizada pois o mesmo esta associado a outro elemento");
        }
        else{
            System.out.println("Professor " + id + "Removida" + '\n');
            professorDAO.remover(id);
        }
    }

    public Professor recuperarPorId(int id){
        if(professorDAO.recuperarPorId(id) == null){
            throw new EntidadeNaoEncontradaException("Nao existe professor com esse id");
        }
        else {
            return professorDAO.recuperarPorId(id);
        }
    }

    public List<Professor> recuperarTodos(){
        return professorDAO.recuperarTodos();
    }
}
