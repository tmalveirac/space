package br.ifce.ppd.com;

/**
 * Classe: MensagemSalaChat.java
 * Definição da tupla de mensagens
 * @author Tiago Malveira
 * 
 */

import net.jini.core.entry.Entry;

public class MensagemSalaChat implements Entry{
    
    public Long numMensagem;
    public String sala;
    public String mensagem;
    public String origem;
    public String destino;
    public Boolean privativa;
    
    public MensagemSalaChat() {   
    }

    public MensagemSalaChat(Long numMensagem, String sala, String mensagem, String origem, String destino, Boolean privativa) {
        this.numMensagem = numMensagem;
        this.sala = sala;
        this.mensagem=mensagem;
        this.origem = origem;
        this.destino = destino;
        this.privativa = privativa;
    }

    
}
