package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "cliente_particular"
)
public class ClienteParticular implements Serializable {
  @Id
  @JoinColumn(name = "id_cliente", nullable = false)
  @OneToOne
  private Cliente id_cliente;

  @Column(
      nullable = false
  )
  private String cc;

  public ClienteParticular() {
  }

  public String getCc() {
    return this.cc;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }

  public Cliente getId_cliente() {
    return id_cliente;
  }

  public void setId_cliente(Cliente id_cliente) {
    this.id_cliente = id_cliente;
  }
}
