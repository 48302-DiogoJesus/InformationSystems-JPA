package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;

@Entity
@Table(
    name = "registo"
)
public class Registo implements Serializable {
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE
  )
  @Column(
      nullable = false
  )
  private int id;

  @JoinColumn(name = "id_gps", nullable = false)
  @ManyToOne
  private Gps id_gps;

  private String longitude;

  private String latitude;

  private Timestamp marca_temporal;

  public Registo() {
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

  public Timestamp getMarca_temporal() {
    return this.marca_temporal;
  }

  public void setMarca_temporal(Timestamp marca_temporal) {
    this.marca_temporal = marca_temporal;
  }

  public Gps getId_gps() {
    return id_gps;
  }

  public void setId_gps(Gps id_gps) {
    this.id_gps = id_gps;
  }
}
