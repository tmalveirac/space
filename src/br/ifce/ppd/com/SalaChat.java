
package br.ifce.ppd.com;

import net.jini.core.entry.Entry;

public class SalaChat implements Entry{
    
    public Long chatNum;
    public String chatNome;
    public Long proxMensagemNum;
    
    public SalaChat() {   
    }

    public SalaChat(Long chatNum, String chat, Long proxMensagemNum) {
        this.chatNum = chatNum;
        this.chatNome = chat;
        this.proxMensagemNum = proxMensagemNum;
    }
    
    public void incrementar() {
        proxMensagemNum = new Long(
        proxMensagemNum.longValue() + 1);
    }
    
}
