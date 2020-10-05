package com.omegaChess.server;

class RegistrationSystem {

    public boolean createProfile(String nick, String pass, String email) {
        if (!isNicknameTaken(nick)) {
            UserProfile profile = new UserProfile(nick, pass, email);
            server.getProfiles().add(profile);
            return true;
        }
        return false;
    }

    private boolean isNicknameTaken(String nick) {
        for (UserProfile profile : server.getProfiles()) {
            if (profile.getNickname().equalsIgnoreCase(nick)) {
                return true;
            }
        }
        return false;
    }

}