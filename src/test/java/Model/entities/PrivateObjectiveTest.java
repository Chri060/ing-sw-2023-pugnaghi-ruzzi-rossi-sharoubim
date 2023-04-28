package Model.entities;

import org.junit.jupiter.api.Test;
import util.Config;
import util.patterns.privateObj.PrivateObjectivePattern;

class PrivateObjectiveTest {

    @Test
    void constructorTest() {
        Config.initialise(2);

        PrivateObjectivePattern pattern;
        for (int i = 0; i < Config.getAvailablePrivateObjectives(); i++) {
             pattern = Config.getPrivateObjectivePattern(i);
        }
    }
}