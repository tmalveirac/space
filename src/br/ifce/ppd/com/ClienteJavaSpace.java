package br.ifce.ppd.com;

/**
 * Classe: ClienteJavaSpace.java
 * cliente JavaSpace, responsável pela comunicação
 * @author Tiago Malveira
 * 
 */

import br.ifce.ppd.view.Principal;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.jini.core.lease.Lease;
import net.jini.space.JavaSpace;

public class ClienteJavaSpace {
    
    private JavaSpace space;
    private String nome;
    private String sala;
    private Long numProxMensagem;
    
    //Contrutor
    public ClienteJavaSpace(){
        try{
            //Pesquisa serviço JavaSpace
            Lookup finder = new Lookup(JavaSpace.class);
            space = (JavaSpace) finder.getService();

            if (space == null) {
                System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
                System.exit(-1);
            }
        }
        catch(Exception e){
            Principal.encerrar();
            System.err.println("Seviço Fora do Ar!");
            System.exit(-1);
        }
        
    }
    
    /**
    * Escreve uma mensagem no chat
    *             
    * @param    sala        sala do chat
    * @param    origem      remetente
    * @param    destino     destinatário
    * @param    mensagem    mensagem a ser enviada
    * @param    tipoMsg     tipo da Mensagem (Privativa ou não)
    * 
    * @return   void   
    */
    public void escreverMensagem(String sala, String origem, String destino, String mensagem, Boolean tipoMsg){
        try {
            //Buscar número da próxima mensagem
            SalaChat template = new SalaChat(null, sala, null); //retorna 1 sala
            SalaChat res = (SalaChat) space.take(template, null, 1);
            
            Long numProxMsg = res.proxMensagemNum;
            
            res.incrementar();
            
            //De volta para o espaço com o Num atualizado
            space.write(res, null, 10*60*1000);
            
            //Montar Msg
            MensagemSalaChat msg = new MensagemSalaChat(numProxMsg, sala, mensagem, origem, destino, tipoMsg);
            space.write(msg, null, 5*60*1000);
         
        } catch (Exception ex) {
            Principal.encerrar();
            Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
    * Recupera as mensagens do chat
    *             
    * @param    sala            sala do chat
    * @param    numMsgInicial   id inicial da mensagem a ser buscada
    * 
    * @return   void   
    */
    public Vector<String> getMensagens(String sala, Long numMsgInicial){
        Vector<String> resultado =  new Vector<String>();
        Long aux = numMsgInicial;
       
        //Busca mensagem
        try {
            MensagemSalaChat template = new MensagemSalaChat(aux, sala, null, null, null, null);
            MensagemSalaChat msg = (MensagemSalaChat) space.read(template, null, 1);

            while (msg != null){
                if (!msg.privativa){
                    resultado.add(msg.origem + " enviou: " + msg.mensagem + "\n");
                }
                else{
                    if (msg.destino.equals(nome) || msg.origem.equals(nome)){
                        resultado.add("**PRIV** " + msg.origem + " enviou: " + msg.mensagem + "\n");
                    }
                }
                aux++;
                template = new MensagemSalaChat(aux, sala, null, null, null, null);
                msg = (MensagemSalaChat) space.read(template, null, 1);
            }
                
            //Atualiza o cliente com o num da próxima mensagem
            numProxMensagem=aux;
        } catch (Exception ex) {
            Principal.encerrar();
            Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return resultado;
    } 
    
    /**
    * Sai da sala 
    *             
    * @param    nome   nome do usuário
    * @param    sala   sala do chat
    * 
    * @return   void   
    */
    public void sairDaSala(String nome, String sala){
        try {
            UsuarioSalaChat template = new UsuarioSalaChat(null,nome,sala);
            space.take(template, null, 1);
            this.sala=null;
            
            //Verifica se a sala ficou vazia. Caso sim, seta TIME_OUT
            if (getUsuarios(sala).isEmpty()){
                SalaChat template1 = new SalaChat(null, sala, null); //retorna 1 sala
                SalaChat res = (SalaChat) space.take(template1, null, 1);

                space.write(res, null, 10*60*1000); //10 minutos
            }
            
        } catch (Exception ex) {
            Principal.encerrar();
            Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
    * Entra em uma sala
    *             
    * @param    nome            nome do usuário
    * @param    sala            sala do chat
    * 
    * @return   void   
    */
    public void entrarNaSala(String nome, String sala){
        try {
            if (proxNumeroUsuarioSalaDisponivel()!=SpaceChatLimites.SALA_LOTADA){
               
                //Veirifica se a sala está inicialmente vazia. Caso Sim, retira TIME_OUT da Sala
                if (getUsuarios(sala).isEmpty()){
                    SalaChat template1 = new SalaChat(null, sala, null); //retorna 1 sala
                    SalaChat res = (SalaChat) space.take(template1, null, 1);
                
                    space.write(res, null, Lease.FOREVER);
                }
                
                UsuarioSalaChat usrSala = new UsuarioSalaChat(proxNumeroUsuarioSalaDisponivel(),
                    nome,sala);
                space.write(usrSala, null, Lease.FOREVER);
                
                //Atualizar numProxMsg
                SalaChat template = new SalaChat(null, sala, null);
                SalaChat salaChat =  (SalaChat) space.read(template, null, 1);
                
                numProxMensagem = salaChat.proxMensagemNum;
                
            }
            else{
                System.err.println("Sala Lotada");
            }
            
        } catch (Exception ex) {
            Principal.encerrar();
            Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
    * Recupera as próximo número do usuário na sala 
    *             
    * 
    * @return   Long    O número da Sala   
    */
    public Long proxNumeroUsuarioSalaDisponivel(){
        for (int i = 0; i < SpaceChatLimites.NUM_MAX_USER_POR_SALA; i++) {
             UsuarioSalaChat template = new UsuarioSalaChat(new Long(i),null, null);
            try {
                UsuarioSalaChat msg = (UsuarioSalaChat) space.read(template, null, 1);
                if (msg == null){
                    return new Long(i);
                }           
            } catch (Exception ex) {
                Principal.encerrar();
                System.err.println("proxNumeroUsuarioSalaDisponivel");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return new Long(SpaceChatLimites.SALA_LOTADA); //100 indica que não é possível criar salas
    }
    
    /**
    * Recupera as usuários do chat
    *             
    * @param    sala            sala do chat
    * 
    * @return   Vector<String>  lista de usuários   
    */
    public Vector<String> getUsuarios(String sala){
        
        if (sala == null){
            System.err.println("getUsuarios Return null");
            return null;          
        }
        
        Vector<String> listaUsuarios = new Vector<String>();
        
        for (int i = 0; i < SpaceChatLimites.NUM_MAX_USER_POR_SALA; i++) {
            UsuarioSalaChat template = new UsuarioSalaChat(new Long(i),null,sala);
            try {
                UsuarioSalaChat msg = (UsuarioSalaChat) space.read(template, null, 1);
                if (msg != null){
                    listaUsuarios.add(msg.nome);
                }       
            } catch (Exception ex) {
                Principal.encerrar();
                System.err.println("getUsuarios");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaUsuarios;
    }
    
    /**
    * Recupera as salas do chat
    *             
    * 
    * @return   Vector<String>  lista de salas   
    */
    public Vector<String> getSalas(){
        
        Vector<String> listaSalas = new Vector<String>();
        
        //Ler as 10 primeiras salas
        for (int i = 0; i < SpaceChatLimites.NUM_MAX_SALAS; i++) {
             SalaChat template = new SalaChat(new Long(i),null, null);
            try {
                SalaChat msg = (SalaChat) space.read(template, null, 1);
                if (msg != null){
                    listaSalas.add(msg.chatNome);
                }       
            } catch (Exception ex) {
                Principal.encerrar();
                System.err.println("getSalas");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listaSalas;
        
    }
    
    
    /**
    * Cria uma sala no Chat
    *             
    * @param    nome            nome da sala
    * 
    * @return   int             o, se OK. 1, se a sala já existe. 2 se o número máx de sala foi alcançado   
    */
    public int criarSala(String nome){      
        if (!existeSala(nome)){
            if (proxNumeroSalaDisponivel() != SpaceChatLimites.NUM_SALA_LOTADO){
                try {
                    SalaChat sala = new SalaChat(proxNumeroSalaDisponivel(), nome, new Long(0));
                    space.write(sala, null, 10*60*1000);
                } catch (Exception ex) {
                    Principal.encerrar();
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
    
    /**
    * Verifica se existe uma determinada sala
    *             
    * @param    nome            nome da sala do chat
    * 
    * @return   boolean         true, se existe. False, cc.   
    */
    public boolean existeSala(String nome){
        //Ler as 10 primeiras salas
        for (int i = 0; i < SpaceChatLimites.NUM_MAX_SALAS; i++) {
             SalaChat template = new SalaChat(new Long(i),null, null);
            try {
                SalaChat msg = (SalaChat) space.read(template, null, 1);
                if (msg != null && msg.chatNome.equals(nome)){
                    return true;
                }
                
                
            } catch (Exception ex) {
                Principal.encerrar();
                System.err.println("ExisteSala");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return false;
    }
    
    /**
    * Recupera o próximo número de sala Disponível
    *             
    * 
    * @return   Long    número da sala   
    */
    public Long proxNumeroSalaDisponivel(){
        for (int i = 0; i < SpaceChatLimites.NUM_MAX_SALAS; i++) {
             SalaChat template = new SalaChat(new Long(i),null, null);
            try {
                SalaChat msg = (SalaChat) space.read(template, null, 1);
                if (msg == null){
                    return new Long(i);
                }           
            } catch (Exception ex) {
                Principal.encerrar();
                System.err.println("proxNumeroSalaDisponivel");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        return new Long(SpaceChatLimites.NUM_SALA_LOTADO); //100 indica que não é possível criar salas
    }
    
    /**
    * Verifica se existe um determinado usuário no Chat
    *             
    * @param    nome            nome do usuário
    * 
    * @return   boolean         true, se existe. False, cc.   
    */
    public boolean existeUsuario(String nome){
        
       System.err.println("Existe usuario "+nome);
       UsuarioChat template = new UsuarioChat();
       template.nome=nome;
       
        try {
            UsuarioChat usr = (UsuarioChat) space.read(template, null, 1);
            
            if (usr != null){
                return true;
            }
            else{
                return false;
            }
            
        } catch (Exception ex) {
            Principal.encerrar();
            System.err.println("ExisteUsuario");
            Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return false;
       
    }
    
    /**
    * Adiciona um usuário no chat
    *             
    * @param    sala            nome do usuario
    * 
    * @return   boolean         true, se foi add. False, cc.   
    */
    public boolean adicionarUsuario(String nome){
        System.err.println("Add usuario "+nome);
        if (!existeUsuario(nome)){
            UsuarioChat usr = new UsuarioChat();
            usr.nome=nome;
            try {
                space.write(usr, null, Lease.FOREVER);
            } catch (Exception ex) {
                Principal.encerrar();
                System.err.println("AdicionarUsuario");
                Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
    * Remove um usuário do chat
    *             
    * @param    nome            nome do usuário
    * 
    * @return   boolean         true, se foi removido. False, cc.   
    */
    public boolean removerUsuario(String nome){
        System.err.println("removerUsuario "+nome);
        
        UsuarioChat template = new UsuarioChat();
        template.nome=nome;

        try {
             UsuarioChat usr = (UsuarioChat) space.take(template, null, 1);
             
            if (usr != null){
                return true;
            }
            else{
                return false;
            }
         } catch (Exception ex) {
             Principal.encerrar();
             System.err.println("RemoverUsuario");
             Logger.getLogger(ClienteJavaSpace.class.getName()).log(Level.SEVERE, null, ex);
         }

        return false;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Long getNumProxMensagem() {
        return numProxMensagem;
    }

    public void setNumProxMensagem(Long numProxMensagem) {
        this.numProxMensagem = numProxMensagem;
    }

}
