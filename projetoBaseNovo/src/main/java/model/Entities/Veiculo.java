package model.Entities;

import jakarta.persistence.*;
import model.JPAEntity;

import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "veiculo"
)
public class Veiculo implements Serializable, JPAEntity<String> {
  @Id
  @Column(
      nullable = false
  )
  private String matricula;

  @JoinColumn(name = "id_cliente", nullable = false)
  @ManyToOne
  private Cliente id_cliente;

  @JoinColumn(name = "id_gps", nullable = false)
  @OneToOne
  private Gps id_gps;

  @JoinColumn(name = "estado_gps", nullable = false)
  @OneToOne
  private EstadosGps estado_gps;

  private String nome_condutor;

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

  public EstadosGps getEstado_gps() {
    return estado_gps;
  }

  public void setEstado_gps(EstadosGps estado_gps) {
    this.estado_gps = estado_gps;
  }

  public Cliente getId_cliente() {
    return id_cliente;
  }

  public void setId_cliente(Cliente id_cliente) {
    this.id_cliente = id_cliente;
  }

  public Gps getId_gps() {
    return id_gps;
  }

  public void setId_gps(Gps id_gps) {
    this.id_gps = id_gps;
  }

  @Override
  public String getPK() {
    return getMatricula();
  }
}
