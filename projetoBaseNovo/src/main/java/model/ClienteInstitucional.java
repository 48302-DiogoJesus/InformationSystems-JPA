package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "cliente_institucional"
)
public class ClienteInstitucional implements Serializable {
  @Id
  @Column(
      nullable = false
  )
  private String id_cliente;

  @Column(
      nullable = false
  )
  private String nome_de_contacto;

  public ClienteInstitucional() {
  }

  public String getId_cliente() {
    return this.id_cliente;
  }

  public void setId_cliente(String id_cliente) {
    this.id_cliente = id_cliente;
  }

  public String getNome_de_contacto() {
    return this.nome_de_contacto;
  }

  public void setNome_de_contacto(String nome_de_contacto) {
    this.nome_de_contacto = nome_de_contacto;
  }
}
