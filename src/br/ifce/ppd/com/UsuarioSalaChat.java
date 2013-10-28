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
public class UsuarioSalaChat implements Entry{
    String nome;
    String sala;
    
    public UsuarioSalaChat(){
        
    }
    
    public UsuarioSalaChat(String nome, String sala){
        this.nome=nome;
        this.sala=sala;
    }
            
}
