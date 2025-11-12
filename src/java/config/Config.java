package config;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe que possui as constantes de configuração do projeto
 */
public final class Config {
    
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/smdecommerce";
    public static final String JDBC_USUARIO = "smdecommerce";
    public static final String JDBC_SENHA = "ufc123";
    
    private Config() {
        
    }
    
}
