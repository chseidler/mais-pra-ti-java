package model;

/**
 * Classe Aluno que inicializa o atributo nota final
 * e herda da Classe Pessoa os atributos nome, telefone e data de nascimento.
 */
public class Aluno extends Pessoa {
    private final Double notaFinal;

    /**
     * Metodo construtor Aluno.
     * @param nome nome do aluno.
     * @param telefone telefone do aluno.
     * @param dataNascimento data de nascimento do aluno.
     * @param notaFinal nota final a ser cadastrada.
     */
    public Aluno(String nome, String telefone, String dataNascimento, Double notaFinal) {
        super(nome, telefone, dataNascimento);
        this.tipo = "Aluno";
        this.notaFinal = notaFinal;
    }

    /**
     * Metodo getNotaFinal para retornar a nota final.
     * @return nota final do Aluno.
     */
    public Double getNotaFinal() {
        return notaFinal;
    }
}
