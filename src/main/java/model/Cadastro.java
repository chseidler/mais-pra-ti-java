package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Cadastro, responsavel pela criacao do Array para armezar os dados,
 * alem de definir os metodos para listar, inserir, deletar e exibir os dados da lista.
 */
public class Cadastro {

    /**
     * Inicializa o ArrayList.
     */
    private final List<Pessoa> lista = new ArrayList<>();

    /**
     * Metodo todos para conseguir iterar na lista.
     * @return lista dos itens cadastrados no Array.
     */
    public List<Pessoa> todos() {
        return lista;
    }

    /**
     * Insere no ArrayList o objeto Pessoa ou sua instancia Aluno.
     * @param p dados da Pessoa/Aluno.
     */
    public void inserir(Pessoa p) {
        lista.add(p);
    }

    /**
     * Metodo auxiliar para exibir via ID(index) dados da Pessoa/Aluno
     * @param id recebe o ID (que também é o index).
     * @return o conteudo da lista conforme ID passado.
     */
    public Pessoa exibir(int id) {
        return lista.get(id);
    }

    /**
     * Metodo auxiliar para atualizar uma posição especifica do ArrayList.
     * @param id indice (index) que deseja alterar.
     * @param p novo conteudo (Pessoa/Aluno) a ser modificado.
     */
    public void atualizar(int id, Pessoa p) {
        lista.set(id, p);
    }

    /**
     * Metodo auxiliar para remover uma posicao especifica do ArrayList.
     * @param id indice (index) que deseja remover.
     */
    public void deletar(int id) {
        lista.remove(id);
    }
}
