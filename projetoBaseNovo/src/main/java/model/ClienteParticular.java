package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "cliente_particular"
)
public class ClienteParticular implements Serializable {
  @Id
  @Column(
      nullable = false
  )
  private String id_cliente;

  @Column(
      nullable = false
  )
  private String cc;

  public ClienteParticular() {
  }

  public String getId_cliente() {
    return this.id_cliente;
  }

  public void setId_cliente(String id_cliente) {
    this.id_cliente = id_cliente;
  }

  public String getCc() {
    return this.cc;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }
}
