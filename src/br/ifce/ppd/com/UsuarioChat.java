/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifce.ppd.com;

import net.jini.core.entry.Entry;

/**
 *
 * @author malveira
 */
public class UsuarioChat implements Entry{
    String nome;
    
    public UsuarioChat(){
    }
    
    public UsuarioChat(String nome){
        this.nome=nome;
    }
}
