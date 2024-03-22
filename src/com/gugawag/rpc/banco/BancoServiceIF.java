package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;

    void cadastrarConta(String numero, double saldoInicial) throws RemoteException;
    String pesquisarConta(String numero) throws RemoteException;
    void removerConta(String numero) throws RemoteException;

    List<Conta> listarContas() throws RemoteException;
    
}
