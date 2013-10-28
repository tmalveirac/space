
package br.ifce.ppd.com;

import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jini.core.entry.Entry;
import net.jini.core.entry.UnusableEntryException;
import net.jini.core.lease.Lease;
import net.jini.core.transaction.TransactionException;
import net.jini.space.JavaSpace;

public class ClienteJavaSpace {
    
    private JavaSpace space;
    private String cliente;
    private String nome;
    
    public ClienteJavaSpace(){
        Lookup finder = new Lookup(JavaSpace.class);
        space = (JavaSpace) finder.getService();
         
        if (space == null) {
                   System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
                   System.exit(-1);
        }
    }
    
    
    public void escreverMensagem(String sala, String origem, String Destino, String msg, boolean tipoMsg){
        
       
    }
    
    public String getMensagens(String sala){
        
        return "";
    } 
    
    
    public Vector<String> getUsuarios(String sala){
        
        Vector<String> listaUsuarios = new Vector<String>();
        
        for (int i = 0; i < 30; i++) {
            UsuarioSalaChat template = new UsuarioSalaChat(null,sala);
            try {
                UsuarioSalaChat msg = (UsuarioSalaChat) space.read(template, null, 1);
                if (msg != null){
                    listaUsuarios.add(msg.nome);
                }       
            } catch (Exception ex) {
                System.err.println("getUsuarios");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listaUsuarios;
    }
    
    public Vector<String> getSalas(){
        
        Vector<String> listaSalas = new Vector<String>();
        
        //Ler as 10 primeiras salas
        for (int i = 0; i < 10; i++) {
             SalaChat template = new SalaChat(new Long(i),null, null);
            try {
                SalaChat msg = (SalaChat) space.read(template, null, 1);
                if (msg != null){
                    listaSalas.add(msg.chatNome);
                }       
            } catch (Exception ex) {
                System.err.println("getSalas");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listaSalas;
        
    }
    
    
    public int criarSala(String nome){      
        if (!existeSala(nome)){
            if (proxNumeroSalaDisponivel() != 100){
                try {
                    SalaChat sala = new SalaChat(proxNumeroSalaDisponivel(), nome, new Long(0));
                    space.write(sala, null, 10*60 * 1000);
                } catch (Exception ex) {
                    System.err.println("CriarSala");
                    Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
                }            
            }
            else{
                return 2; // Limite!
            }
        }
        else {
            return 1; //Já há com mesmo nome
        }
        
        return 0;
    }
    
    
    public boolean existeSala(String nome){
        //Ler as 10 primeiras salas
        for (int i = 0; i < 10; i++) {
             SalaChat template = new SalaChat(new Long(i),null, null);
            try {
                SalaChat msg = (SalaChat) space.read(template, null, 1);
                if (msg != null && msg.chatNome.equals(nome)){
                    return true;
                }
                
                
            } catch (Exception ex) {
                System.err.println("ExisteSala");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
    }
    
    public Long proxNumeroSalaDisponivel(){
        for (int i = 0; i < 10; i++) {
             SalaChat template = new SalaChat(new Long(i),null, null);
            try {
                SalaChat msg = (SalaChat) space.read(template, null, 1);
                if (msg == null){
                    return new Long(i);
                }           
            } catch (Exception ex) {
                System.err.println("proxNumeroSalaDisponivel");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return new Long(100); //100 indica que não é possível criar salas
    }
    
    
    public boolean existeUsuario(String nome){
       UsuarioChat template = new UsuarioChat(nome);
       
       UsuarioChat usr = null;
        try {
            usr = (UsuarioChat) space.read(template, null, new Long(1));
        } catch (Exception ex) {
            Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       if (usr != null){
           return true;
       }
       else{
           return false;
       }
    }
    
    public boolean adicionarUsuario(String nome){
        if (!existeUsuario(nome)){
            UsuarioChat usr = new UsuarioChat(nome);
            try {
                space.write(usr, null, Lease.FOREVER);
            } catch (Exception ex) {
                System.err.println("AdicionarUsuario");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return true;
        }
        else{
            return false;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
