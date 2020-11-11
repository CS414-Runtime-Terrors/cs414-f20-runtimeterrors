package com.omegaChess.server;

//import com.csc14.runtimeterrors.game.UserProfile;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class OCServerData {

    // storage fields
    private final String rootSaveLocation;
    private final String profilesSaveLocation;
    private final String matchesSaveLocation;
    private final String gameRecordsSaveLocation;

    // private final Logger log = LoggerFactory.getLogger(MicroServer.class);
    private final ArrayList<UserProfile> profiles = new ArrayList<>();
    private final ArrayList<Match> matches = new ArrayList<>();
    private final ArrayList<GameRecord> archive = new ArrayList<>();

    // regular constructor
    public OCServerData() {
        rootSaveLocation = "./server-data/";
        profilesSaveLocation = rootSaveLocation + "user-profiles/";
        matchesSaveLocation = rootSaveLocation + "matches/";
        gameRecordsSaveLocation = rootSaveLocation + "game-records/";
    }

    // test constructor
    public OCServerData(String saveLocation) {
        rootSaveLocation = saveLocation;
        profilesSaveLocation = rootSaveLocation + "user-profiles/";
        matchesSaveLocation = rootSaveLocation + "matches/";
        gameRecordsSaveLocation = rootSaveLocation + "game-records/";
    }

    // methods
    public ArrayList<UserProfile> getProfiles() {
        return profiles;
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

  public Match getMatch(int matchID) {
    for (Match match : matches) {
      if (matchID == match.getMatchID())
        return match;
    }
    return null;
  }

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

    public ArrayList<GameRecord> getArchive() {
        return archive;
    }

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

        deleteRootSaveFolder(); // delete outdated data

        createDirectoryIfNonExistent(rootSaveLocation);

        // save user profile nicknames
        try {
            File saveFile = new File(profilesSaveLocation + "profile-nicknames.txt");

            createDirectoryIfNonExistent(profilesSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (UserProfile p : getProfiles()) {
                saveWriter.write(p.getNickname() + "\n");
            }

            saveWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // save user profiles
        for (UserProfile p : getProfiles()) {
            p.save(profilesSaveLocation);
        }

        // save match directory names
        try {
            File saveFile = new File(matchesSaveLocation + "directory-names.txt");

            createDirectoryIfNonExistent(matchesSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (Match m : getMatches()) {
                saveWriter.write(m.getProfile1() + "-" + m.getProfile2() + "/" + "\n");
            }

            saveWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // save matches
        for (Match m : getMatches()) {
            m.save(matchesSaveLocation);
        }

        // save game record filenames
        try {
            File saveFile = new File(gameRecordsSaveLocation + "filenames.txt");

            createDirectoryIfNonExistent(gameRecordsSaveLocation);

            saveFile.createNewFile();

            FileWriter saveWriter = new FileWriter(saveFile);

            for (GameRecord g : archive) {
                saveWriter.write(g.getID() + "\n");
            }

            saveWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
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
        try {
            File loadFile = new File(profilesSaveLocation + "profile-nicknames.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextNickname = loadReader.nextLine();
                UserProfile temp = new UserProfile();
                temp.load(profilesSaveLocation + nextNickname + "/");
                profiles.add(temp);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + profilesSaveLocation);
        }

        // load matches
        try {
            File loadFile = new File(matchesSaveLocation + "directory-names.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextDirectoryName = loadReader.nextLine();
                Match temp = new Match();
                temp.load(matchesSaveLocation + nextDirectoryName + "/");
                matches.add(temp);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + matchesSaveLocation);
        }

        // load game records
        try {
            File loadFile = new File(gameRecordsSaveLocation + "filenames.txt");
            Scanner loadReader = new Scanner(loadFile);
            // actual loading
            while (loadReader.hasNextLine()) {
                String nextFilename = loadReader.nextLine();
                GameRecord temp = new GameRecord();
                temp.load(gameRecordsSaveLocation + nextFilename + ".txt");
                archive.add(temp);
            }
            loadReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found in " + gameRecordsSaveLocation);
        }

        System.out.println("Done loading!");
    }

    public void deleteRootSaveFolder() {
        deleteFolder(rootSaveLocation);
    }

    private void deleteFolder(String location) {
        File f = new File(location);
        if (f.listFiles() != null) {
            for (File file : Objects.requireNonNull(f.listFiles())) {
                deleteFolder(file.getPath());
            }
        }
        f.delete();
    }

    public boolean rootSaveFolderExists() {
        File directory = new File(rootSaveLocation);
        return directory.exists();
    }

}