package model.Entities;

import jakarta.persistence.*;
import DataScopes.JPAEntity;

import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "cliente_institucional"
)
public class ClienteInstitucional implements Serializable, JPAEntity<Cliente> {
  @Id
  @JoinColumn(name = "id_cliente", nullable = false)
  @OneToOne
  private Cliente id_cliente;

  @Column(
      nullable = false
  )
  private String nome_de_contacto;

  public ClienteInstitucional() {
  }


  public String getNome_de_contacto() {
    return this.nome_de_contacto;
  }

  public void setNome_de_contacto(String nome_de_contacto) {
    this.nome_de_contacto = nome_de_contacto;
  }

  public Cliente getId_cliente() {
    return id_cliente;
  }

  public void setId_cliente(Cliente id_cliente) {
    this.id_cliente = id_cliente;
  }

  @Override
  public Cliente getPK() {
    return getId_cliente();
  }
}
