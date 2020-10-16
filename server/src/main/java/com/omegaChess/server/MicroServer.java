package com.omegaChess.server;

import java.util.ArrayList;

class MicroServer {

  // fields
  private ArrayList<UserProfile> profiles = new ArrayList<>();

  // methods

  public ArrayList<UserProfile> getProfiles() {
    return profiles;
  }

  public boolean createProfile(String nick, String pass, String email) {
    if (!isNicknameTaken(nick)) {
      UserProfile profile = new UserProfile(nick, pass, email);
      getProfiles().add(profile);
      return true;
    }
    return false;
  }

  private boolean isNicknameTaken(String nick) {
    for (UserProfile profile : getProfiles()) {
      if (profile.getNickname().equalsIgnoreCase(nick)) {
        return true;
      }
    }
    return false;
  }

  public boolean removeProfile(String nick) {
    for (UserProfile profile : getProfiles()) {
      if (profile.getNickname().equalsIgnoreCase(nick)) {
        getProfiles().remove(profile);
        return true;
      }
    }
    return false;
  }

}
