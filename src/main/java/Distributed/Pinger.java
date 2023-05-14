package Distributed;

abstract public class Pinger extends Thread{

    private boolean needsToStop;

    public boolean needsToStop() {
        return needsToStop;
    }

    public void stop2() {
        needsToStop = true;
    }

}
