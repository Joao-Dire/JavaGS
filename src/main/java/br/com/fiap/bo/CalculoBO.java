package br.com.fiap.bo;

import br.com.fiap.dao.CalculoDAO;
import br.com.fiap.to.CalculoTO;

import java.util.ArrayList;

public class CalculoBO {

    private CalculoDAO calculoDAO;

    public CalculoBO() {
        this.calculoDAO = new CalculoDAO();
    }

    public ArrayList<CalculoTO> findAll() {
        return calculoDAO.findAll();
    }

    public CalculoTO findById(int id) {
        return calculoDAO.findById(id);
    }

    public CalculoTO save(CalculoTO calculo) {
        return calculoDAO.save(calculo);
    }

    public boolean delete(int id) {
        return calculoDAO.delete(id);
    }

    public CalculoTO update(CalculoTO calculo) {
        return calculoDAO.update(calculo);
    }
}
