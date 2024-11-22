package br.com.fiap.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection conexao;
    private String url;
    private String user;
    private String password;
    private String driver;

    private ConnectionFactory(String url, String user, String password, String driver) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.driver = driver;
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            Properties prop = new Properties();
            try (FileInputStream file = new FileInputStream("./src/main/resources/application.properties")) {
                prop.load(file);
                String url = prop.getProperty("datasource.url");
                String user = prop.getProperty("datasource.user");
                String password = prop.getProperty("datasource.password");
                String driver = prop.getProperty("datasource.driver-class-name");

                instance = new ConnectionFactory(url, user, password, driver);
            } catch (FileNotFoundException e) {
                System.out.println("Erro (FileNotFoundException): " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Erro (IOException): " + e.getMessage());
            }
        }
        return instance;
    }

    public Connection getConexao() {
        try {
            if (this.conexao == null || this.conexao.isClosed()) {
                if (this.driver == null || this.driver.isEmpty()) {
                    throw new ClassNotFoundException("Nome da classe do driver nulo ou em branco");
                }
                if (this.url == null || this.url.isEmpty()) {
                    throw new SQLException("URL de conexão nulo ou em branco");
                }
                if (this.user == null || this.user.isEmpty()) {
                    throw new SQLException("Usuário nulo ou em branco");
                }

                Class.forName(this.driver);
                this.conexao = DriverManager.getConnection(this.url, this.user, this.password);
            }
        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Erro (ClassNotFoundException): " + e.getMessage());
        }
        return this.conexao;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}
