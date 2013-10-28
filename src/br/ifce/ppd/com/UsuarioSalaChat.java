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
