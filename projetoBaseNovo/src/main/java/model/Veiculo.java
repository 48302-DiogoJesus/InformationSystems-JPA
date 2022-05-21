package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "veiculo"
)
public class Veiculo implements Serializable {
  @Id
  @Column(
      nullable = false
  )
  private String matricula;

  @Column(
      nullable = false
  )
  private String id_cliente;

  @Column(
      nullable = false
  )
  private int id_gps;

  @Column(
      nullable = true
  )
  private String estado_gps;

  @Column(
      nullable = false
  )
  private String nome_condutor;

  @Column(
      nullable = true
  )
  private String telefone_condutor;

  @Column(
      nullable = false
  )
  private int num_alarmes;

  public Veiculo() {
  }

  public String getMatricula() {
    return this.matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getId_cliente() {
    return this.id_cliente;
  }

  public void setId_cliente(String id_cliente) {
    this.id_cliente = id_cliente;
  }

  public int getId_gps() {
    return this.id_gps;
  }

  public void setId_gps(int id_gps) {
    this.id_gps = id_gps;
  }

  public String getEstado_gps() {
    return this.estado_gps;
  }

  public void setEstado_gps(String estado_gps) {
    this.estado_gps = estado_gps;
  }

  public String getNome_condutor() {
    return this.nome_condutor;
  }

  public void setNome_condutor(String nome_condutor) {
    this.nome_condutor = nome_condutor;
  }

  public String getTelefone_condutor() {
    return this.telefone_condutor;
  }

  public void setTelefone_condutor(String telefone_condutor) {
    this.telefone_condutor = telefone_condutor;
  }

  public int getNum_alarmes() {
    return this.num_alarmes;
  }

  public void setNum_alarmes(int num_alarmes) {
    this.num_alarmes = num_alarmes;
  }
}
