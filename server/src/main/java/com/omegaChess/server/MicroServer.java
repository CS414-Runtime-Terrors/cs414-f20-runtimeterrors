package com.omegaChess.server;

//import com.csc14.runtimeterrors.game.UserProfile;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.ArrayList;

class MicroServer {

  // fields
 // private final Logger log = LoggerFactory.getLogger(MicroServer.class);
  private ArrayList<UserProfile> profiles = new ArrayList<>();
  private RegistrationSystem registrationSystem = new RegistrationSystem();

  // methods

  public ArrayList<UserProfile> getProfiles() {
    return profiles;
  }

  public RegistrationSystem getRegistrationSystem() {
    return registrationSystem;
  }



}
