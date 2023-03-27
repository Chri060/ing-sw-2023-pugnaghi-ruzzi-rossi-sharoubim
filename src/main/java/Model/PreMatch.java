package Model;

import Exceptions.PlayerAlreadyInLobby;
import java.util.ArrayList;
import java.util.List;

public class PreMatch {
    private List<PreMatchPlayer> players = new ArrayList<>();



    public void addPlayer(String player) throws PlayerAlreadyInLobby {
        if (players.stream().map(PreMatchPlayer::getName).anyMatch(x -> x.equals(player))) {
            throw new PlayerAlreadyInLobby(player);
        }
        else {
            players.add(new PreMatchPlayer(player, players.isEmpty()));
        }
    }

    public void removePlayer(String player) {
        players.remove(player);
    }

    public void togglePlayer(String player) {
        players.stream().filter(x -> x.getName().equals(player)).findAny().ifPresent(x -> x.toggleReady());
    }

    public boolean allReady() {
        return players.stream().map(PreMatchPlayer::isReady).reduce((a, b) -> a && b).get();
    }

    public String getRoomLeader() {
        return players.stream().filter(x -> x.isRoomLeader()).findAny().get().getName();
    }

    public List<String> start() {
        List<String> playerList = new ArrayList<>();
        players.stream().forEach(x -> playerList.add(x.getName()));
        return playerList;
    }

    //test only
    public void print() {
        System.out.println("PreMatch status:");
        players.stream().forEach(System.out :: println);
        System.out.println();
    }
}