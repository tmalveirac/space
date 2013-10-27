package br.ifce.ppd.com;

import net.jini.core.entry.Entry;
public class Message implements Entry {
    public String content;
    public Message() {
    }

   public Message(String content){
	this.content=content;
   }	
}