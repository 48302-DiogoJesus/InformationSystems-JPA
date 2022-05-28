package model.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.JPAEntity;

import java.io.Serializable;

@Entity
@Table(
    name = "gps"
)
public class Gps implements Serializable, JPAEntity<Integer> {
  @Id
  @Column(
      nullable = false
  )
  private int id;

  public Gps() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public Integer getPK() {
    return getId();
  }
}
