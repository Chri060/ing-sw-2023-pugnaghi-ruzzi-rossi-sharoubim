package util;

import Distributed.Messages.NetworkMessage;

public interface Observer<ObservedObject extends Observable<Arg>, Arg extends Object> {


    void update(ObservedObject o, Arg message);
}
