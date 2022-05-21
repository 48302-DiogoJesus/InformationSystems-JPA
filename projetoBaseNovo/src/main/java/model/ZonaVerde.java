package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.String;

@Entity
@Table(
    name = "zona_verde"
)
public class ZonaVerde implements Serializable {
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
  private String id_veiculo;

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

  public String getId_veiculo() {
    return this.id_veiculo;
  }

  public void setId_veiculo(String id_veiculo) {
    this.id_veiculo = id_veiculo;
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
}
