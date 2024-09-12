package br.com.fiap.parking_meter.parking_meter.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "parking_spots")
public class ParkingSpot {
    @Id
    private String cep;  // CEP como chave primária

    private String logradouro;  // Rua (Logradouro)
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;

    private String location;  // Exemplo: número da vaga ou nome da área
    private int quantity;  // Quantidade de vagas disponíveis

    public ParkingSpot () {}

    public ParkingSpot(String cep, String logradouro, String bairro, String cidade, String estado, String pais, String location, int quantity) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.location = location;
        this.quantity = quantity;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                ", location='" + location + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
