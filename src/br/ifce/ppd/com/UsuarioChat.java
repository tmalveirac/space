package br.ifce.ppd.com;

/**
 * Classe: UsuarioChat.java
 * Definição da tupla de usuários no Chat
 * @author Tiago Malveira
 * 
 */

import net.jini.core.entry.Entry;

public class UsuarioChat implements Entry{
    
    public String nome;
    
    public UsuarioChat(){
    }
    
    public UsuarioChat(String nome){
        this.nome=nome;
    }
    
}
