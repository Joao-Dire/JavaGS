package br.com.fiap.dao;

import br.com.fiap.to.CalculoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalculoDAO extends Repository {

    public ArrayList<CalculoTO> findAll() {
        ArrayList<CalculoTO> calculos = new ArrayList<>();
        String sql = "SELECT * FROM ddd_calculos ORDER BY id";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CalculoTO calculo = new CalculoTO();
                calculo.setId(rs.getInt("id"));
                calculo.setEconomia(rs.getFloat("economia"));
                calculo.setRetorno(rs.getFloat("retorno"));
                calculos.add(calculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return calculos;
    }

    public CalculoTO findById(int id) {
        CalculoTO calculo = null;
        String sql = "SELECT * FROM ddd_calculos WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                calculo = new CalculoTO();
                calculo.setId(rs.getInt("id"));
                calculo.setEconomia(rs.getFloat("economia"));
                calculo.setRetorno(rs.getFloat("retorno"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return calculo;
    }

    public CalculoTO save(CalculoTO calculo) {
        String sql = "INSERT INTO ddd_calculos (economia, retorno) VALUES (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setFloat(1, calculo.getEconomia());
            ps.setFloat(2, calculo.getRetorno());
            if (ps.executeUpdate() > 0) {
                return calculo;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM ddd_calculos WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public CalculoTO update(CalculoTO calculo) {
        String sql = "UPDATE ddd_calculos SET economia = ?, retorno = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setFloat(1, calculo.getEconomia());
            ps.setFloat(2, calculo.getRetorno());
            ps.setInt(3, calculo.getId());
            if (ps.executeUpdate() > 0) {
                return calculo;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
}
