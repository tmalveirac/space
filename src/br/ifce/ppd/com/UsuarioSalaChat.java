package br.ifce.ppd.com;

/**
 * Classe: UsuarioSalaChat.java
 * Definição da tupla de Usuários por sala
 * @author Tiago Malveira
 * 
 */

import net.jini.core.entry.Entry;


public class UsuarioSalaChat implements Entry{
    public Long id;
    public String nome;
    public String sala;
    
    public UsuarioSalaChat(){
        
    }
    
    public UsuarioSalaChat(Long id, String nome, String sala){
        this.id=id;
        this.nome=nome;
        this.sala=sala;
    }
            
}
