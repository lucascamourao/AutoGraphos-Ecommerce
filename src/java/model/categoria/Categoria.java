package model.categoria;

/**
 *
 * @author Leonardo Oliveira Moreira
 * 
 * Classe de entidade que representa uma categoria
 */
public class Categoria {
    
    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String descricao) {
        this.nome = descricao;
    }
    
}
