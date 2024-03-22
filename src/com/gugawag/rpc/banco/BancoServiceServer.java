package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Conta> contas;

    public BancoServiceServer() throws RemoteException {
        contas = new HashMap<>();
        contas.put("1", new Conta("1", 100.0));
        contas.put("2", new Conta("2", 156.0));
        contas.put("3", new Conta("3", 950.0));
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return contas.get(conta).getSaldo();
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return contas.size();
    }

    @Override
    public void cadastrarConta(String numero, double saldoInicial) throws RemoteException {
        contas.put(numero, new Conta(numero, saldoInicial));
    }

    @Override
    public String pesquisarConta(String numero) throws RemoteException {
        if (contas.containsKey(numero)) {
            return "Conta encontrada. Saldo: " + contas.get(numero).getSaldo();
        } else {
            return "Conta não encontrada.";
        }
    }

    @Override
    public void removerConta(String numero) throws RemoteException {
        contas.remove(numero);
    }

    @Override
    public List<Conta> listarContas() throws RemoteException {
        return new ArrayList<>(contas.values());
    }
}
