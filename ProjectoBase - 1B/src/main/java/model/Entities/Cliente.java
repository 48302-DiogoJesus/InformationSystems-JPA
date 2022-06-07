package model.Entities;

import jakarta.persistence.*;
import DataScopes.JPAEntity;

import java.io.Serializable;
import java.lang.String;

// @Embeddable // - Ficava sem memória quando usámos
@Entity
@Table(
    name = "cliente"
)
public class Cliente implements Serializable, JPAEntity<String> {
  @Id
  @Column(
      nullable = false
  )
  private String nif;

  @JoinColumn(name = "referenciador", nullable = false)
  private Cliente referenciador;

  @Column(
      nullable = false
  )
  private String nome;

  @Column(
      nullable = false
  )
  private String morada;

  @Column(
      nullable = false
  )
  private String telefone;

  @Column(
      nullable = false
  )
  private boolean apagado;

  public Cliente() {
  }

  public String getNif() {
    return this.nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public Cliente getReferenciador() {
    return this.referenciador;
  }

  public void setReferenciador(Cliente referenciador) {
    this.referenciador = referenciador;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getMorada() {
    return this.morada;
  }

  public void setMorada(String morada) {
    this.morada = morada;
  }

  public String getTelefone() {
    return this.telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public boolean getApagado() {
    return this.apagado;
  }

  public void setApagado(boolean apagado) {
    this.apagado = apagado;
  }

  @Override
  public String getPK() {
    return getNif();
  }
}
