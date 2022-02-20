package main.java.controller;

/**
 * Classe Validador, responsavel pela validacao simples das entradas de dados do usuario.
 */
public class Validador {

    /**
     * Valida via regex se a String recebida contem apenas letras, nao aceitando numeros e caracteres especiais.
     * @param nome parametro de entrada enviado pelo usuario.
     * @return Este metodo retorna verdadeira se tiver mais que 1 caracter e for apenas letras;
     *                     retorna falso se receber caracteres nao aceitos.
     */
    public static boolean validaNome(String nome) {
        if (nome.matches("^[A-Za-zÀ-ÿ \"'-]+$") && nome.length() > 1) {
            System.out.println("Nome registrado com sucesso!");
            return true;
        }
        System.out.println("Por favor digite apenas letras, numeros nao sao aceitos.");
        return false;
    }

    /**
     * Valida via regex se a String recebida contem apenas numeros, nao aceitando letras e caracteres especiais.
     * @param telefone parametro de entrada enviado pelo usuario.
     * @return Este metodo retorna verdadeira se tiver apenas numeros com tamanho em 10 e 11 digitos;
     *                     retorna falso se receber algo que não seja numeros.
     */
    public static boolean validaTelefone(String telefone) {
        if (telefone.matches("\\d+") && telefone.length() > 9 && telefone.length() < 12) {
            System.out.println("Numero de telefone registrado com sucesso!");
            return true;
        }
        System.out.println("Por favor, digite apenas numeros com DDD. Ex: (51) 999999999 = 51999999999");
        return false;
    }

    /**
     * Valida via regex se a String recebida satisfaz a condicao:
     *      Dia = entre 01 e 31;
     *      Mes = entre 01 e 12;
     *      Ano = entre 0000 e 9999;
     *      Separacao = obrigatoriamente / .
     *      (nao verifica ano bisexto nem limita dia em funcao do mes)
     * @param data parametro de entrada enviado pelo usuario.
     * @return Este metodo retorna verdadeira se receber dados no formato dd/MM/yyyy;
     *                     retorna falso se nao satisfazer a condicao.
     */
    public static boolean validaData(String data) {
        if (data.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")) {
            System.out.println("Data de Nascimento registrada com sucesso!");
            return true;
        }
        System.out.println("Por favor, insira novamente a data no formato \"dd/MM/aaaa\". ex: 01/01/2022");
        return false;
    }

    /**
     * Valida via regex se a String recebida contem apenas numeros, com no maximo uma casa decimal, separada por ".".
     * @param nota parametro de entrada enviado pelo usuario.
     * @return Este metodo retorna verdadeira se tiver apenas numeros entre 0 e 10;
     *                     retorna falso se receber algo que nao seja numeros.
     */
    public static boolean validaNota(String nota) {
        if (nota.matches("^(10)$|(10.0)$|^[0-9](\\.[0-9])?$")) {
            System.out.println("Nota registrada com sucesso!");
            return true;
        }
        System.out.println("Por favor, insira um nota valida entre 0 e 10 com no maximo uma casa decimal.");
        return false;
    }

    /**
     * Valida via regex se a String recebida contem apenas numeros inteiros.
     * @param id parametro de entrada enviado pelo usuario.
     * @return Este metodo retorna verdadeira se tiver apenas numeros;
     *                     retorna falso se receber algo que nao seja numeros.
     */
    public static boolean validaID(String id) {
        if (id.matches("^[0-9]*$") && !id.equals("")) {
            return true;
        }
        System.out.println("Por favor, insira apenas numeros.");
        return false;
    }
}
