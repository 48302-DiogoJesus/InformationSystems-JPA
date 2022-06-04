package model.Entities;

import DataScopes.JPAEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import javax.lang.model.type.NullType;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(
    name = "list_all_alarmes"
)
public class ListAllAlarmes implements Serializable, JPAEntity<NullType> {

    public ListAllAlarmes() {}

    @Id
    @JoinColumn(name = "matricula", nullable = false)
    private Veiculo matricula;

    private String nome_condutor;

    private String latitude;
    private String longitude;

    private Timestamp marca_temporal;

    public Veiculo getMatricula() {
        return matricula;
    }

    public void setMatricula(Veiculo matricula) {
        this.matricula = matricula;
    }

    public String getNome_condutor() {
        return nome_condutor;
    }

    public void setNome_condutor(String nome_condutor) {
        this.nome_condutor = nome_condutor;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Timestamp getMarca_temporal() {
        return marca_temporal;
    }

    public void setMarca_temporal(Timestamp marca_temporal) {
        this.marca_temporal = marca_temporal;
    }

    @Override
    public NullType getPK() {
        return null;
    }
}
