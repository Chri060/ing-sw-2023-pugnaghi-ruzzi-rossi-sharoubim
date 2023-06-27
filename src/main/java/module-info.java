module org.example{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires json.simple;

    exports Controller;
    opens Controller;

    exports Distributed;
    opens Distributed;

    exports Distributed.Events;
    opens Distributed.Events;

    exports Distributed.Messages;
    opens Distributed.Messages;

    exports Distributed.Messages.clientMessages;
    opens Distributed.Messages.clientMessages;

    exports Distributed.Messages.serverMessages;
    opens Distributed.Messages.serverMessages;

    exports Distributed.Socket;
    opens Distributed.Socket;

    exports Exceptions;
    opens Exceptions;

    exports Model;
    opens Model;

    exports org.example;
    opens org.example;

    exports util;
    opens util;

    exports util.Iterators;
    opens util.Iterators;

    exports util.patterns.commonObj;
    opens util.patterns.commonObj;

    exports util.patterns.privateObj;
    opens util.patterns.privateObj;

    exports View;
    opens View;

    exports View.GUI;
    opens View.GUI;
}