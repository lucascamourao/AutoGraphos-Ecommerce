package model.venda;

/**
 *
 * @author Guilherme Sousa Lopes & Lucas Cabral Amador Mourão
 * 
 * Classe de entidade que representa uma venda
 */
public class Venda {
    
    private Integer id;
    private String data_hora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataHora() {
        return data_hora;
    }

    public void setDataHora(String data_hora) {
        this.data_hora = data_hora;
    }
    
}
