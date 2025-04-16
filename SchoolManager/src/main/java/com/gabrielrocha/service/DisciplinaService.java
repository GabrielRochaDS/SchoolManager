package com.gabrielrocha.service;

import com.gabrielrocha.dao.DisciplinaDAO;
import com.gabrielrocha.exception.EntidadeNaoEncontradaException;
import com.gabrielrocha.exception.RemocaoNaoAutorizada;
import com.gabrielrocha.model.Disciplina;
import com.gabrielrocha.util.FabricaDeDaos;

import java.util.List;

public class DisciplinaService {
    private final DisciplinaDAO disciplinaDAO = FabricaDeDaos.getDAO(DisciplinaDAO.class);

    public void incluir(Disciplina disciplina){
        disciplinaDAO.incluir(disciplina);
    }

    public void remover(int id){
        if( disciplinaDAO.recuperarPorId(id) != null){
            throw new RemocaoNaoAutorizada("Remocao nao autorizada pois o mesmo esta associado a outro elemento");
        }
        else{
            disciplinaDAO.remover(id);
        }
    }

    public void alterar(Disciplina disciplina){
        disciplinaDAO.alterar(disciplina);
    }

    public Disciplina recuperarPorId(int id){
        if(disciplinaDAO.recuperarPorId(id) == null){
            throw new EntidadeNaoEncontradaException("Nao existe disciplina com esse id");
        }
        else {
            return disciplinaDAO.recuperarPorId(id);
        }
    }

    public List<Disciplina> recuperarTodos(){
        return disciplinaDAO.recuperarTodos();
    }

}
