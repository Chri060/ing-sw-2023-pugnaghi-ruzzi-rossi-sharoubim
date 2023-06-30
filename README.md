# Software engineering project 2023
![image](https://github.com/Chri060/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/blob/main/deliverables/repo%20files/image.jpg) <br>
The 2023 [PoliMi](https://www.polimi.it) software engineering project is based on the game _My Shelfie_ by Cranio Creations. <br>
The rulebook of My Shelfie can be found in [english](https://github.com/Chri060/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/blob/main/deliverables/repo%20files/rulebook_en.pdf) or [italian](https://github.com/Chri060/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/blob/main/deliverables/repo%20files/rulebook_it.pdf). <br>
The requirements of the project can be found [here](https://github.com/Chri060/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/blob/main/deliverables/repo%20files/requirements.pdf). <br>

## Implemented Functionalities
| Functionality                             | Status  |
|:------------------------------------------|:-------:|
| Simplified ruleset                        |   ✅    |
| Complete ruleset                          |   ✅    |
| Socket                                    |   ✅    |
| Remote Method Invocation                  |   ✅    |
| Command Line Interface                    |   ✅    |
| Graphic User Interface                    |   ❌    |
| Disconnection resilience                  |   ✅    |
| In-game chat                              |   ✅    |
| Multiple games                            |   ❌    |
| Persistence                               |   ❌    |

## Tools and Dependencies
| Name                                                                                                                                | Usage                 |
|:----------------------------------------------------------------------------------------------------------------------------------- |:---------------------:|
| [IntelliJ IDEA](https://www.jetbrains.com/idea/)                                                                                    | IDE                   |
| [Astah UML](https://astah.net/products/astah-uml/)                                                                                  | UML                   |
| [Java 19](https://jdk.java.net/19/)                                                                                                 | Java release          |
| [JavaFX](https://www.oracle.com/it/java/technologies/javase/javafx-overview.html)                                                   | GUI                   |
| [Scene Builder](https://www.oracle.com/java/technologies/javase/javafxscenebuilder-info.html)                                       | FXML editor           |
| [Apache Maven Project](https://maven.apache.org/)                                                                                   | Dependency management |

## Deliverables
The diagrams used to build the application can be found [here](https://github.com/Chri060/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/tree/main/deliverables/diagrams). <br>
The test coverage documentation files can be found [here](https://github.com/Chri060/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/tree/main/deliverables/test%20coverage%20screenshots). <br>
All the peer rewiew deliverables can be found [here](https://github.com/Chri060/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/tree/main/deliverables/peer%20review). <br>
Javadocs can be found [here](https://chri060.github.io/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/javadocs/). <br>

## Building and Running
Make sure you have the latest [**Java 19**](https://jdk.java.net/19/) jdk installed before you attempt to run this project.
You can download the latest release from the [here](https://github.com/Chri060/ing-sw-2023-pugnaghi-ruzzi-rossi-sharoubim/tree/main/deliverables/jar%20files).
For the client, sssuming the release package is named `ClientApp.jar` and the server ip is `customIP`, run the following command in the root directory of the project to start the application:
```
java -jar .\ClientApp.jar customIP
```
For the server, sssuming the release package is named `ServerApp.jar`, run the following command in the root directory of the project to start the application:
```
java -jar .\ServerApp.jar
```
NOTE: in the ServerApp folder there are config files. To allow game customization you need to download also the JSON files.

# Authors
* [Kirolos Sharoubim](https://github.com/kirolosharoubim)
* [Christian Rossi](https://github.com/Chri060)
* [Alessandro Pugnaghi](https://github.com/ale657)
* [Gianluca Ruzzi](https://github.com/GianlucaRuzzi)

## License
This project is developed in collaboration with [Politecnico di Milano](https://www.polimi.it/) and [Cranio Creations](https://www.craniocreations.it/)
