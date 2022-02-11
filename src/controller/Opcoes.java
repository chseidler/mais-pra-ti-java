package controller;

import model.*;

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

        if (desejaInserirNota()) {
            notaFinal = retornaVariavelValidada("Digite a nota do aluno: ", "nota");
            notaFinald = Double.parseDouble(notaFinal);
            inserirAluno();
        } else {
            inserirPessoa();
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
        boolean validaInt;
        String id;
        notaFinald = -11;
        if (menuVisualizar()) {
            System.out.print("Digite o ID do cadastro que deseja atualizar: ");
            id = sc.nextLine();
            validaInt = Validador.validaID(id);
            if (validaInt) {
                int idAtualizar = Integer.parseInt(id);
                if (idAtualizar >= 0 && idAtualizar < cadastros.todos().size()) {
                    boolean validaN = false, validaT = false, validaA = false, validaNota = false;
                    final Pessoa p = cadastros.exibir(idAtualizar);
                    while (!validaN) {
                        System.out.println("Nome atual do cadastro: " + p.getNome());
                        System.out.print("Digite o novo nome (Pressione ENTER sem digitar nada para manter o atual): ");
                        nome = sc.nextLine();
                        validaN = nome.equals("") || Validador.validaNome(nome);
                    }
                    while (!validaT) {
                        System.out.println("Telefone atual do cadastro: " + p.getTelefone());
                        System.out.print("Digite o novo telefone (Pressione ENTER sem digitar nada para manter o atual): ");
                        telefone = sc.nextLine();
                        validaT = telefone.equals("") || Validador.validaTelefone(telefone);
                    }
                    while (!validaA) {
                        System.out.println("Data de nascimento atual do cadastro: " + p.getDataNascimento());
                        System.out.print("Digite a nova data de nascimento (Pressione ENTER sem digitar nada para manter o atual): ");
                        aniversario = sc.nextLine();
                        validaA = aniversario.equals("") || Validador.validaData(aniversario);
                    }
                    boolean aguardaConfirmacao = true;
                    boolean removerNota = false;
                    boolean temNota = false;
                    do {
                        System.out.println("Deseja atualizar ou inserir uma nota? \n[1] - SIM\n[2] - NAO");
                        System.out.println("[3] - Remover nota (Aluno vira Pessoa)");
                        confirmacao = sc.nextLine();
                        switch (confirmacao) {
                            case "1" -> {
                                while (!validaNota) {
                                    System.out.print("Digite a nota do aluno: ");
                                    notaFinal = sc.nextLine();
                                    validaNota = Validador.validaNota(notaFinal);
                                }
                                notaFinald = Double.parseDouble(notaFinal);
                                temNota = true;
                                aguardaConfirmacao = false;
                            }
                            case "2" -> aguardaConfirmacao = false;
                            case "3" ->  {
                                removerNota = true;
                                aguardaConfirmacao = false;
                            }
                            default -> System.out.println("Favor digite 1 para SIM, 2 para NAO ou 3 para REMOVER NOTA.");
                        }
                    } while (aguardaConfirmacao);

                    String insNome = nome.equals("") ? p.getNome() : nome;
                    String insTelefone = telefone.equals("") ? p.getTelefone() : telefone;
                    String insAniversario = aniversario.equals("") ? p.getDataNascimento() : aniversario;
                    double insNotaFinal;
                    Pessoa atualizado;

                    if ((p instanceof Aluno && !removerNota) || temNota) {
                        insNotaFinal = notaFinald == -11 ? ((Aluno) p).getNotaFinal() : notaFinald;
                        atualizado = new Aluno(insNome, insTelefone, insAniversario, insNotaFinal);
                    } else {
                        atualizado = new Pessoa(insNome, insTelefone, insAniversario);
                    }
                    atualizado.setDataCadastrada(p.getDataCadastrada());

                    cadastros.atualizar(idAtualizar, atualizado);
                    System.out.println("|ID = " + idAtualizar + "| atualizado com sucesso!");
                } else
                    System.out.println("Nao foi possivel atualizar, ID nao encontrado.");
            }
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

    private static void deletarID(int idDeletar) {
        if (idDeletar >= 0 && idDeletar < cadastros.todos().size()) {
            cadastros.deletar(idDeletar);
            System.out.println("ID " + idDeletar + " excluido com sucesso!");
        } else
            System.out.println("Nao foi possivel excluir, ID nao encontrado.");
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

    private static void inserirAluno() {
        cadastros.inserir(new Aluno(nome, telefone, aniversario, notaFinald));
        System.out.println("Aluno " + nome + " cadastrado(a) com sucesso!");
    }

    private static void inserirPessoa() {
        cadastros.inserir(new Pessoa(nome, telefone, aniversario));
        System.out.println("Pessoa " + nome + " cadastrado(a) com sucesso!");
    }

    private static boolean desejaInserirNota() {
        do {
            System.out.println("Deseja cadastrar uma nota? \n[1] - SIM\n[2] - NÃO");
            confirmacao = sc.nextLine();
            switch (confirmacao) {
                case "1": return true;
                case "2": return false;
                default: System.out.println("Favor digite 1 para SIM ou 2 para NAO.");
            }
        } while (true);
    }
}
