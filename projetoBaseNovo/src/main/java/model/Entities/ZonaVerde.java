package model.Entities;

import jakarta.persistence.*;
import model.JPAEntity;

import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "zona_verde"
)
public class ZonaVerde implements Serializable, JPAEntity<Integer> {
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE
  )
  @Column(
      nullable = false
  )
  private int id;

  @JoinColumn(name = "id_veiculo", nullable = false)
  @ManyToOne
  private Veiculo id_veiculo;

  @Column(
      nullable = false
  )
  private String longitude;

  @Column(
      nullable = false
  )
  private String latitude;

  @Column(
      nullable = false
  )
  private int raio;

  public ZonaVerde() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLongitude() {
    return this.longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return this.latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public int getRaio() {
    return this.raio;
  }

  public void setRaio(int raio) {
    this.raio = raio;
  }

  public Veiculo getId_veiculo() {
    return id_veiculo;
  }

  public void setId_veiculo(Veiculo id_veiculo) {
    this.id_veiculo = id_veiculo;
  }

  @Override
  public Integer getPK() {
    return getId();
  }
}
