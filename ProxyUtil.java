package com.neusoft.lemis.rest.utils;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyUtil extends Authenticator {
	 private String user;
     private String pass;
     
     public ProxyUtil(String user, String pass) {
         this.user  = user;
         this.pass = pass;
     }

     @Override
     protected PasswordAuthentication getPasswordAuthentication() {
         return  new PasswordAuthentication(user, pass.toCharArray());
     }
}
