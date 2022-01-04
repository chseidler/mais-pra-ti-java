package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe Pessoa que inicializa os atributos nome, telefone e data de nascimento.
 * Atributo tipo definido por padrao;
 * Atributos data cadastrada e data alterado atribuidos automaticamente.
 */
public class Pessoa {
    protected String tipo;
    protected String nome;
    protected String telefone;
    protected String dataNascimento;
    protected LocalDateTime dataCadastrada;
    protected LocalDateTime dataAlterada;
    protected DateTimeFormatter formatacaoData = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Metodo construtor Pessoa.
     * @param nome nome do aluno.
     * @param telefone telefone do aluno.
     * @param dataNascimento data de nascimento do aluno.
     */
    public Pessoa(String nome, String telefone, String dataNascimento) {
        this.tipo = "Pessoa";
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        final LocalDateTime dataAtual = LocalDateTime.now();
        this.dataCadastrada = dataAtual;
        this.dataAlterada = dataAtual;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Metodo para receber a Data Cadastrada.
     * @return Data de Cadastro na formatacao padrao definida.
     */
    public String getDataCadastrada() {
        return dataCadastrada.format(formatacaoData);
    }

    /**
     * Metodo auxiliar para manter a Data Cadastrada quando atualizado cadastro.
     * @param dataCadastrada data em que foi cadastrado.
     */
    public void setDataCadastrada(String dataCadastrada) {
        DateTimeFormatter formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dataCadastrada = LocalDateTime.parse(dataCadastrada, formatada);
    }

    /**
     * Metodo para receber a Data Alterada.
     * @return Data de Alteracao na formatacao padrao definida.
     */
    public String getDataAlterada() {
        return dataAlterada.format(formatacaoData);
    }
}
