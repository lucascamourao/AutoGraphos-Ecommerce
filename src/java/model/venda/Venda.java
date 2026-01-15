package model.venda;
import java.sql.Timestamp;
import model.usuario.Usuario;
/**
 *
 * @author Guilherme Sousa Lopes & Lucas Cabral Amador Mourão
 * 
 * Classe de entidade que representa uma venda
 */
public class Venda {
    
    private Integer id;
    private Timestamp data_hora;
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDataHora() {
        return data_hora;
    }

    public void setDataHora(Timestamp data_hora) {
        this.data_hora = data_hora;
    }
    
   public Usuario getUsuario() {
       return usuario;
   }

   public void setUsuario(Usuario usuario) {
       this.usuario = usuario;
   }
}
