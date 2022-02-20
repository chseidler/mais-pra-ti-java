package main.java.controller;

import main.java.model.*;

import java.util.Scanner;

/**
 * Classe Opcoes, responsavel pelas funcoes a serem executadas pelo programa.
 */
public class Opcoes {
    private static final Scanner sc = new Scanner(System.in);
    private static final Cadastro cadastros = new Cadastro();
    static String nome, telefone, aniversario, confirmacao, notaFinal;
    static double notaFinald;

    /**
     * Metodo Inserir interage com o usuario para cadastrar Aluno/Pessoa.
     * Gerencia as validacoes e armazena dados no ArrayList.
     */
    public static void menuInserir() {
        nome = retornaVariavelValidada("Digite o nome que deseja cadastrar: ", "nome");
        telefone = retornaVariavelValidada("Digite o telefone que deseja cadastrar: ", "telefone");
        aniversario = retornaVariavelValidada("Digite a data de nascimento que deseja cadastrar: ", "aniversario");

        if (desejaInserir("Deseja cadastrar uma nota?")) {
            notaFinal = retornaVariavelValidada("Digite a nota do aluno: ", "nota");
            notaFinald = Double.parseDouble(notaFinal);
            inserirAluno(nome, telefone, aniversario, notaFinald);
        } else {
            inserirPessoa(nome, telefone, aniversario);
        }
    }

    /**
     * Metodo para Visualizar todos os registros cadastrados.
     * @return Retorna falso e texto de retorno se não tiver nenhum registro;
     *                 verdadeiro e realiza um forEach para imprimir todos os registros.
     */
    public static boolean menuVisualizar() {
        if (cadastros.todos().isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return false;
        } else {
            cadastros.todos().forEach(Opcoes::informacoes);
            return true;
        }
    }

    /**
     * Metodo Atualizar interage com o usuario para atualizar o cadastro de Aluno/Pessoa especifico.
     * Gerencia as validacoes e armazena, ou mantem, os dados no ArrayList.
     */
    public static void menuAtualizar() {
        notaFinald = -11;
        if (menuVisualizar()) {
            String id = retornaVariavelValidada("Digite o ID do cadastro que deseja atualizar: ", "id");
            int idAtualizar = Integer.parseInt(id);
            if (idExiste(idAtualizar)) {
                final Pessoa p = cadastros.exibir(idAtualizar);

                System.out.println("Nome atual do cadastro: " + p.getNome());
                if (desejaInserir("Deseja atualizar o nome?")) {
                    nome = retornaVariavelValidada("Digite o novo nome: ", "nome");
                } else {
                    nome = p.getNome();
                }

                System.out.println("Telefone atual do cadastro: " + p.getTelefone());
                if (desejaInserir("Deseja atualizar o telefone?")) {
                    telefone = retornaVariavelValidada("Digite o novo telefone: ", "telefone");
                } else {
                    telefone = p.getTelefone();
                }

                System.out.println("Data de nascimento atual do cadastro: " + p.getDataNascimento());
                if (desejaInserir("Deseja atualizar a data de nascimento?")) {
                    aniversario = retornaVariavelValidada("Digite a nova data de nascimento: ", "aniversario");
                } else {
                    aniversario = p.getDataNascimento();
                }

                if ((p.getTipo().equals("Aluno"))) {
                    System.out.println("Nota atual do cadastro: " + ((Aluno) p).getNotaFinal());
                } else {
                    System.out.println("Nenhuma nota registrada.");
                }
                if (desejaInserir("Deseja atualizar a nota?")) {
                    notaFinal = retornaVariavelValidada("Digite a nova nota: ", "nota");
                    notaFinald = Double.parseDouble(notaFinal);
                } else {
                    notaFinald = p instanceof Aluno ? ((Aluno) p).getNotaFinal() : -11;
                }
                Pessoa atualizado;

                if (notaFinald != -11) {
                    atualizado = new Aluno(nome, telefone, aniversario, notaFinald);
                } else {
                    atualizado = new Pessoa(nome, telefone, aniversario);
                }
                atualizado.setDataCadastrada(p.getDataCadastrada());

                cadastros.atualizar(idAtualizar, atualizado);
                System.out.println("|ID = " + idAtualizar + "| atualizado com sucesso!");
            } else
                System.out.println("Nao foi possivel atualizar, ID nao encontrado.");
        }
    }


    /**
     * Metodo Deletar interage com o usuario para excluir um cadastro de Aluno/Pessoa.
     * Gerencia a validacao conforme ID passado pelo usuario e exclui dado no ArrayList.
     */
    public static void menuDeletar() {
        if (menuVisualizar()) {
            String deletar = retornaVariavelValidada("Digite o ID do cadastro que deseja deletar: ", "id");
            int idDeletar = Integer.parseInt(deletar);
            deletarID(idDeletar);
        }
    }

    /**
     * Metodo que recebe o ID, e se existe, deleta.
     * @param idDeletar recebe um int com id a ser deletado.
     */
    private static void deletarID(int idDeletar) {
        if (idExiste(idDeletar)) {
            cadastros.deletar(idDeletar);
            System.out.println("ID " + idDeletar + " excluido com sucesso!");
        } else
            System.out.println("Nao foi possivel excluir, ID nao encontrado.");
    }

    /**
     * Metodo verifica se id recebido existe no cadastro.
     * @param id parametro de entrada enviado pelo usuario.
     * @return esse metodo retorna verdadeiro se existe ID no cadastro
     *                              falso se nao existe.
     */
    private static boolean idExiste(int id) {
        return id >= 0 && id < cadastros.todos().size();
    }

    /**
     * Metodo que organiza a impressao de dados conforme parametro recebido.
     * @param p variavel do Objeto instanciado.
     */
    private static void informacoes(Pessoa p) {
        System.out.print("|ID = " + cadastros.todos().indexOf(p) + "| " + p.getTipo() + " --> " + p.getNome());
        if (p instanceof Aluno)
            System.out.print(" (Nota: " + ((Aluno) p).getNotaFinal() + ")");
        System.out.println("\nTelefone: " + p.getTelefone() + " - Nascimento: " + p.getDataNascimento()
                         + "\nCadastrado em: " + p.getDataCadastrada());
        if (!p.getDataCadastrada().equals(p.getDataAlterada()))
            System.out.println("Alterado em: " + p.getDataAlterada());
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    }

    /**
     * Metodo que aguarda em loop até que entrada do usuario satisfaça condição no validador.
     * @param texto String para imprimir na tela informação desejada.
     * @param tipo String com valor a ser checado no validador.
     * @return String enviada pelo usuario ja validado de acordo com o seu validador.
     */
    private static String retornaVariavelValidada(String texto, String tipo) {
        String retorno = null;
        while (retorno == null) {
            System.out.print(texto);
            String entrada = sc.nextLine();
            switch (tipo) {
                case "nome": if (Validador.validaNome(entrada)) { retorno = entrada; }
                    break;
                case "telefone": if (Validador.validaTelefone(entrada)) { retorno = entrada; }
                    break;
                case "aniversario": if (Validador.validaData(entrada)) { retorno = entrada; }
                    break;
                case "nota": if (Validador.validaNota(entrada)) {  retorno = entrada; }
                    break;
                case "id": if (Validador.validaID(entrada)) {  retorno = entrada; }
                    break;
            }
        }
        return retorno;
    }

    /**
     * Metodo insere Aluno no cadastro.
     * @param nomeInserir Nome a ser inserido.
     * @param telefoneInserir Telefone a ser inserido.
     * @param aniversarioInserir Aniversario a ser inserido.
     * @param notaFinaldInserir Nota Final a ser inserida.
     */
    private static void inserirAluno(String nomeInserir, String telefoneInserir, String aniversarioInserir, double notaFinaldInserir) {
        cadastros.inserir(new Aluno(nomeInserir, telefoneInserir, aniversarioInserir, notaFinaldInserir));
        System.out.println("Aluno " + nomeInserir + " cadastrado(a) com sucesso!");
    }

    /**
     * Metodo insere Pessoa no cadastro.
     * @param nomeInserir Nome a ser inserido.
     * @param telefoneInserir Telefone a ser inserido.
     * @param aniversarioInserir Aniversario a ser inserido.
     */
    private static void inserirPessoa(String nomeInserir, String telefoneInserir, String aniversarioInserir) {
        cadastros.inserir(new Pessoa(nomeInserir, telefoneInserir, aniversarioInserir));
        System.out.println("Pessoa " + nomeInserir + " cadastrado(a) com sucesso!");
    }

    /**
     * Metodo para verificar em loop com usario se deseja ou nao inserir/atualizar variavel.
     * @param texto Recebe texto a ser impresso com o tipo de dado que deseja ou nao inserir.
     * @return Este metodo retorna verdadeira se deseja inserir variavel;
     *                     retorna falso se nao deseja inserir variavel.
     */
    private static boolean desejaInserir(String texto) {
        do {
            System.out.println(texto);
            System.out.println("[1] - SIM\n[2] - NÃO");
            confirmacao = sc.nextLine();
            switch (confirmacao) {
                case "1": return true;
                case "2": return false;
                default: System.out.println("Favor digite 1 para SIM ou 2 para NAO.");
            }
        } while (true);
    }

    /**
     * Metodo para sair do programa.
     * @return falso para encerrar loop do Monitor.
     */
    public static boolean menuSair() {
        System.out.println("Programa encerrado!");
        sc.close();
        return false;
    }
}
