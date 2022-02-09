package view;

import static controller.Opcoes.*;

import java.util.Scanner;

/**
 * Classe Monitor, responsavel por exibir o menu de opcoes.
 */
public class Monitor {
    // Constantes com a definição da função e chave do menu principal
    public static final String INSERIR = "1";
    public static final String MOSTRAR_TODOS = "2";
    public static final String ATUALIZAR = "3";
    public static final String DELETAR = "4";
    public static final String SAIR = "0";

    /**
     * menuPrincipal aguarda em loop até que o usuario digite a opcao que deseja acessar do menu,
     * caso nao reconheça a entrada do usuario, retorna a ele mesmo.
     */
    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        boolean fecharPrograma = true;
        String opcao;

        do {
            System.out.print("---------------------------\\\\\n"
                    + "[" + INSERIR + "] Cadastrar Aluno/Pessoa  |\n"
                    + "[" + MOSTRAR_TODOS + "] Exibir todos cadastros  |\n"
                    + "[" + ATUALIZAR + "] Atualizar um cadastro   |\n"
                    + "[" + DELETAR + "] Deletar um cadastro     |\n"
                    + "[" + SAIR + "] Sair                    |\n"
                    + "---------------------------//\n");

            System.out.print("Escolha uma opcao: ");
            opcao = sc.nextLine();

            switch (opcao) {
                case INSERIR -> menuInserir();
                case MOSTRAR_TODOS -> menuVisualizar();
                case ATUALIZAR -> menuAtualizar();
                case DELETAR -> menuDeletar();
                case SAIR -> {
                    System.out.println("Programa encerrado!");
                    fecharPrograma = false;
                    sc.close();
                }
                default -> System.out.println("Opcao invalida, tente novamente.\n");
            }
        } while (fecharPrograma);
    }
}
