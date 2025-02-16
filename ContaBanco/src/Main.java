import kaique.luan.dev.ContaTerminal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Por favor, digite o seu Nome !");
        String nome = sc.nextLine();

        System.out.println("Por favor, digite o número da Agência !");
        String agencia = sc.nextLine();

        System.out.println("Por favor, digite o número da Conta !");
        int numero = sc.nextInt();

        System.out.println("Por favor, digite o Saldo inicial !");
        double saldo = sc.nextDouble();

        ContaTerminal contaTerminal = new ContaTerminal(numero, agencia, nome, saldo);

        System.out.println(contaTerminal);
    }
}