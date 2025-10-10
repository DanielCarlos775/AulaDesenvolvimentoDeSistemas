package com.daniel_carlos.desenvolv_sist.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Usuario {
    private int id;
    private String nome;
    private int cpf;
    private LocalDate dataNascimento;
    private String email;
    private String senha;

    // Construtores



    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCpf() { return cpf; }
    public void setCpf(int cpf) {this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) {

            // Validar se a data não é antes de 1900
        if (dataNascimento.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new IllegalArgumentException("Data de nascimento não pode ser antes de 1900");
        }

        // Validar se não é no futuro
        if (dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser no futuro");
        }

        this.dataNascimento = dataNascimento;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
