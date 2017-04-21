/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise.integration;

import Utils.Mailer;
import exercise.Authenticator;
import exercise.AuthenticatorTest;
import exercise.realdatabase.UserFacadeRealDB;

/**
 *
 * @author Private
 */
public class AuthenticatorIT extends AuthenticatorTest {
    
    @Override
    public Authenticator makeAuthenticator(){
     return new Authenticator(new UserFacadeRealDB("pu_localDB"), new Mailer());
  }
    
}
