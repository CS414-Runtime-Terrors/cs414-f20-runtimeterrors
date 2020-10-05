package com.omegaChess.server;

import org.slf4j.Logger;
import org.slf4j.Logger;

class MicroServer {

  // fields
  private final Logger log = LoggerFactory.getLogger(MicroServer.class);
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
