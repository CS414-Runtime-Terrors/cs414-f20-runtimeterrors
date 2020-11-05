package com.omegaChess.server;

//import com.csc14.runtimeterrors.game.UserProfile;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.File;
import java.util.ArrayList;

public class OCServerData {

  // fields
 // private final Logger log = LoggerFactory.getLogger(MicroServer.class);
  private ArrayList<UserProfile> profiles = new ArrayList<>();
  private ArrayList<Match> matches = new ArrayList<>();
  private ArrayList<GameRecord> archive = new ArrayList<>();

  // methods

  public ArrayList<UserProfile> getProfiles() {
    return profiles;
  }

  public void addMatch(Match match){ matches.add(match); }

  public ArrayList<Match> getMatches() { return matches; }

  public void removeMatch(Match target) {
    for (Match match: getMatches())
      if (match == target) {
        matches.remove(match);
        break;
      }
  }

  public void addToArchive(GameRecord game) {
    archive.add(game);
  }

  public ArrayList<GameRecord> getArchive() { return archive; }

  public boolean createProfile(String nick, String pass, String email) {
    if (!isNicknameTaken(nick) && !isEmailTaken(email)) {
      UserProfile profile = new UserProfile(nick, pass, email);
      getProfiles().add(profile);
      return true;
    }
    return false;
  }

  public Integer getLongestNickname() {
    int max_length = 0;
    for( UserProfile profile : getProfiles()) {
      if( profile.getNickname().length() > max_length)
      {
        max_length = profile.getNickname().length();
      }
    }
    return max_length;
  }

  private boolean isNicknameTaken(String nick) {
    for (UserProfile profile : getProfiles()) {
      if (profile.getNickname().equalsIgnoreCase(nick)) {
        return true;
      }
    }
    return false;
  }

  private boolean isEmailTaken(String email) {
    for (UserProfile profile : getProfiles()) {
      if (profile.getEmailAddress().equalsIgnoreCase(email)) {
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

  public boolean checkPassword(String nick, String pass) {
    for (UserProfile profile : getProfiles()) {
      if (profile.getNickname().equalsIgnoreCase(nick)) {
        if (profile.getPassword().equals(pass)) { // case sensitive
          return true;
        }
      }
    }
    return false;
  }

  public boolean profileExists(String nick) {
    return isNicknameTaken(nick);
  }

  public UserProfile getProfile(String nick) {
    for (UserProfile profile : getProfiles()) {
      if (profile.getNickname().equalsIgnoreCase(nick)) {
        return profile;
      }
    }
    return null;
  }

  public void save() {
    System.out.println("Saving server data...");

    final String rootSaveLocation = "./server-data/";

    createDirectoryIfNonExistent(rootSaveLocation);

    final String profilesSaveLocation = rootSaveLocation + "user-profiles/";
    final String matchesSaveLocation = rootSaveLocation + "matches/";
    final String gameRecordsSaveLocation = rootSaveLocation + "game-records/";

    // save user profiles
    for (UserProfile p : getProfiles()) {
      p.save(profilesSaveLocation);
    }

    // save matches
    for (Match m : getMatches()) {
      m.save(matchesSaveLocation);
    }

    // save game records
    for (GameRecord r : archive) {
      r.save(gameRecordsSaveLocation);
    }
    System.out.println("Saved!");
  }

  public static void createDirectoryIfNonExistent(String location) {
    File directory = new File(location);
    if (!directory.exists()) {
      System.out.println("Creating directory: " + location);
      directory.mkdir();
    }
  }

  public void load() {
    System.out.println("Loading server data...");
    // load user profiles
    // TODO

    // load matches
    // TODO

    // load game records
    // TODO
    System.out.println("Loaded!");
  }

}