
package br.ifce.ppd.com;

import net.jini.core.entry.Entry;

public class UsuarioChat implements Entry{
    
    public String nome;
    
    public UsuarioChat(){
    }
    
    public UsuarioChat(String nome){
        this.nome=nome;
    }
    
}
