package br.com.santander.gastos.query.listener;

import java.io.IOException;

public interface FilaListener {
    void receiveMessage(String mensagem) throws IOException;
}
