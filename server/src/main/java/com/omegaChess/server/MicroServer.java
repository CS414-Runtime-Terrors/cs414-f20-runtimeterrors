package com.omegaChess.server;

import org.slf4j.Logger;
import org.slf4j.Logger;

class MicroServer {

  private final Logger log = LoggerFactory.getLogger(MicroServer.class);

  // list of user profiles
  public ArrayList<UserProfile> profiles = new ArrayList<>();

  // registration system
  RegistrationSystem registrationSystem = new RegistrationSystem(this);


}
