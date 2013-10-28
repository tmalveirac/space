package br.ifce.ppd.view;

/**
 * Classe: Aba2.java
 * View Aba do chat
 * @author Tiago Malveira
 * 
 */

import br.ifce.ppd.com.ClienteJavaSpace;

import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;


public class Aba2 extends JPanel{
    

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtEnviar;
    private javax.swing.JTextArea jtaMensagem;
    private javax.swing.JTextField jtfMensagem;
    private javax.swing.JButton jbtFecharAba;
    private String sala;
    private JPanel jPanel1;
    private JButton jbtSairSala;
    private JPanel jPanel2;
    private JScrollPane jScrollPane2;
    private JList jltUsuarios;
    private JPanel jPanel3;
    private javax.swing.JCheckBox jcbPrvativa;
    private ClienteJavaSpace cliente;
    private static DefaultListModel  listModel = new DefaultListModel();
    
    
    public Aba2(String sala, ClienteJavaSpace cliente){
       
        this.cliente=cliente;
        this.sala=sala;
        
        System.err.println("Aba NOVA!!");
        
        jtfMensagem = new javax.swing.JTextField();
        jbtEnviar = new javax.swing.JButton();
        jbtSairSala = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jltUsuarios = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaMensagem = new javax.swing.JTextArea();
        jcbPrvativa = new javax.swing.JCheckBox();
       
        jtaMensagem.setColumns(20);
        jtaMensagem.setRows(5);
        jScrollPane1.setViewportView(jtaMensagem);
        
        jtaMensagem.setCaretPosition(jtaMensagem.getDocument().getLength());
        
        jtaMensagem.setEditable(false);
        
        jltUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        jtfMensagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfMensagemActionPerformed(evt);
            }
        });

        jbtEnviar.setText("Enviar");
        jbtEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtEnviarActionPerformed(evt);
            }
        });

        jbtSairSala.setText("Sair da Sala");
        jbtSairSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtSairSalaActionPerformed(evt);
            }
        });
        
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Usuários", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

       
        jScrollPane2.setViewportView(jltUsuarios);
        
        jcbPrvativa.setText("Privativa");

         javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcbPrvativa)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbPrvativa)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Mensagens", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jtaMensagem.setColumns(20);
        jtaMensagem.setRows(5);
        jScrollPane1.setViewportView(jtaMensagem);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
        setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtSairSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(119, 119, 119))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtEnviar)
                    .addComponent(jbtSairSala))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );    
        
    }
    
    private void jbtSairSalaActionPerformed(java.awt.event.ActionEvent evt) {                                            
        System.err.println("clicou Sair da Sala");
        
        getParent().remove(this);
        Principal.getListaAbas().remove(this);
        cliente.sairDaSala(cliente.getNome(), sala);
        
    }                                           

    private void jtfMensagemActionPerformed(java.awt.event.ActionEvent evt) {                                            
        jbtEnviarActionPerformed(evt);
    }              
    
    
    //Envia mensagem
    private void jbtEnviarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        
        //cliente.getInverterservice().enviarMensagem(cliente.getNome(),loginRemoto, jtfMensagem.getText());
        //jtaMensagem.append(this.cliente.getNome() + " enviou: " + jtfMensagem.getText()+ "\n");
        
        if (jtfMensagem.getText().length()>0){
            
            String msg;
            if (jcbPrvativa.isSelected()){
                msg = "****Nome enviou Privativamente: " + jtfMensagem.getText() + "\n";
                
                if (jltUsuarios.isSelectionEmpty()){
                    JOptionPane.showMessageDialog(null, "Selecione um contato ou"
                            + "desmarque o check Privativo!"
                            ,"Aviso",  JOptionPane.WARNING_MESSAGE);
                    return;
                }
                cliente.escreverMensagem(cliente.getSala(), 
                        cliente.getNome(), jltUsuarios.getSelectedValue().toString(), 
                        jtfMensagem.getText(), Boolean.TRUE);
                
            }
            else{
                msg = "Nome enviou: " + jtfMensagem.getText() + "\n";
                
                //Enviar Mensagem
                cliente.escreverMensagem(cliente.getSala(), 
                        cliente.getNome(), null, 
                        jtfMensagem.getText(), Boolean.FALSE);
            }
            
            //jtaMensagem.append(msg);

            jtfMensagem.setText("");
        }
        else{
            JOptionPane.showMessageDialog(null, "Digite uma mensagem!"
                    ,"Aviso",  JOptionPane.WARNING_MESSAGE);
        }
  
    }                                         
     
    public JTextArea getJtaMensagem() {
        return jtaMensagem;
    }

    public String getSala() {
        return sala;
    }   

    public JList getJltUsuarios() {
        return jltUsuarios;
    }

    public void setJltUsuarios(JList jltUsuarios) {
        this.jltUsuarios = jltUsuarios;
    }
    
    
     /**
    * Insere um nome na lista de login do Chat
    *             
    * @param nome   nome a ser inserido na lista do chat
    * @return       void
    */
    public void insereListaChat(Vector<String> lista){
        //listModel.removeAllElements();
        DefaultListModel list = new DefaultListModel();
        for (String s : lista){
            if (idNomeListaChat(s) == -1) {
                list.addElement(s);
                jltUsuarios.setModel(list);
            }
        }     
    }  
    
    /**
    * Identifica o id de um nome na lista do chat
    *             
    * @param    nome   nome a ser buscado na lista de login do chat
    * @return   void   indice i do nome da lista, se existir. -1, caso contrário 
    */
    public static int idNomeListaChat(String nome){
        try {
            for (int i=0; i<listModel.getSize();i++){
                if (listModel.get(i).toString().equals(nome)){
                    return i;
                }
            }
        }
        catch (Exception e){
            System.err.println("EXCEÇão size list " + listModel.size());
        }
        
        
        return -1;
    }
    
}
















