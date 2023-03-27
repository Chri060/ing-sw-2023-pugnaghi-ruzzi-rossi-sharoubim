package Model;

public class PreMatchPlayer {
    private String name;
    private boolean ready;
    private boolean roomLeader;



    PreMatchPlayer(String name, boolean leader) {
        this.name = name;
        ready = false;
        roomLeader = leader;
    }

    void toggleReady() {
        ready = !ready;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName() + "\t ready: " + ready + "\tRoomleader " + roomLeader;
    }

    public boolean isReady() {
        return ready;
    }

    public boolean isRoomLeader() {
        return roomLeader;
    }
}
