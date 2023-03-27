package Exceptions;

public class PlayerAlreadyInLobby extends Throwable {
    public PlayerAlreadyInLobby(String name) {
        super(name + " is already in the lobby");
    }
}