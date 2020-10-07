package com.omegaChess.server;

class RegistrationSystem {

    private MicroServer server;

    public RegistrationSystem(MicroServer serverReference) {
        server = serverReference;
    }

    public boolean createProfile(String nick, String pass, String email) {
        if (!isNicknameTaken(nick)) {
            UserProfile profile = new UserProfile(nick, pass, email);
            MicroServer server = new MicroServer();
            server.getProfiles().add(profile);
            return true;
        }
        return false;
    }

    private boolean isNicknameTaken(String nick) {
        MicroServer server = new MicroServer();
        for (UserProfile profile : server.getProfiles()) {
            if (profile.getNickname().equalsIgnoreCase(nick)) {
                return true;
            }
        }
        return false;
    }

}