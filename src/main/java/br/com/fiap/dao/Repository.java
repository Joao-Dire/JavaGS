package br.com.fiap.dao;

import java.sql.Connection;

public abstract class Repository {
    protected Connection connection;

    public Repository() {
        getConnection();
    }

    public Connection getConnection() {
        try {
            connection = ConnectionFactory.getInstance().getConexao();
            if (connection != null) {
                System.out.println("Conexão estabelecida com sucesso.");
            } else {
                System.out.println("Falha ao estabelecer conexão.");
            }
            return connection;
        } catch (Exception e) {
            System.out.println("Erro ao obter conexão: " + e.getMessage());
        }
        return null;
    }


    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao fechar a conexao: " + e.getMessage());
        }
    }
}