package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.List;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while(opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    System.out.println(banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    System.out.println("Digite o saldo inicial:");
                    double saldoInicial = entrada.nextDouble();
                    banco.cadastrarConta(numero, saldoInicial);
                    System.out.println("Conta cadastrada com sucesso.");
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    System.out.println(banco.pesquisarConta(numero));
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da conta:");
                    String numero = entrada.next();
                    banco.removerConta(numero);
                    System.out.println("Conta removida com sucesso.");
                    break;
                }
                case 6: {
                    listarContas(banco);
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }
    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Pesquisar conta");
        System.out.println("5 - Remover conta");
        System.out.println("6 - Listar contas");
        System.out.println("9 - Sair");
    }

    public static void listarContas(BancoServiceIF banco) throws RemoteException {
        List<Conta> contas = banco.listarContas();
        for (Conta conta : contas) {
            System.out.println("Conta: " + conta.getNumero() + ", Saldo: " + conta.getSaldo());
        }
    }
}
