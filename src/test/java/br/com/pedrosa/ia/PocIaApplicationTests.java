package br.com.pedrosa.ia;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class PocIaApplicationTests {

    @Test
    void contextLoads() {
        var modules = ApplicationModules.of(PocIaApplication.class);

        for (var m : modules) {
            System.out.println("module " + m.getName() + " : " + m.getBasePackage());
        }

        modules.verify();

        new Documenter(modules)
                .writeDocumentation()
                .writeIndividualModulesAsPlantUml();
    }

}
