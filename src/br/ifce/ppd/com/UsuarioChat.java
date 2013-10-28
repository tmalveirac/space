
package br.ifce.ppd.com;

import net.jini.core.entry.Entry;

public class UsuarioChat implements Entry{
    
    public String nome;
    public String online;
    
    public UsuarioChat(){
    }
    
    public UsuarioChat(String nome, String online){
        this.nome=nome;
        this.online=online;
    }
    
}
