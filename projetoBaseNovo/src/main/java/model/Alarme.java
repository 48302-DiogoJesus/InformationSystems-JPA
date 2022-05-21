package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "alarme"
)
public class Alarme implements Serializable {
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE
  )
  @Column(
      nullable = false
  )
  private int id;

  @Column(
      nullable = false
  )
  private int id_registo;

  @Column(
      nullable = false
  )
  private String id_veiculo;

  public Alarme() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId_registo() {
    return this.id_registo;
  }

  public void setId_registo(int id_registo) {
    this.id_registo = id_registo;
  }

  public String getId_veiculo() {
    return this.id_veiculo;
  }

  public void setId_veiculo(String id_veiculo) {
    this.id_veiculo = id_veiculo;
  }
}
