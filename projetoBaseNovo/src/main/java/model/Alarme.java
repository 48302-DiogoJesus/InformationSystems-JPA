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

  @JoinColumn(name = "id_registo", nullable = false)
  @ManyToOne
  private Registo id_registo;

  @JoinColumn(name = "id_veiculo",  nullable = false)
  @ManyToOne
  private Veiculo id_veiculo;

  public Alarme() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Registo getId_registo() {
    return id_registo;
  }

  public void setId_registo(Registo id_registo) {
    this.id_registo = id_registo;
  }

  public Veiculo getId_veiculo() {
    return id_veiculo;
  }

  public void setId_veiculo(Veiculo id_veiculo) {
    this.id_veiculo = id_veiculo;
  }
}
