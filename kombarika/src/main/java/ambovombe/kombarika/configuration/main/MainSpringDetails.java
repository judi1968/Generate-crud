package ambovombe.kombarika.configuration.main;

import java.util.HashMap;

import ambovombe.kombarika.configuration.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Judi
 */
@Getter @Setter
public class MainSpringDetails extends Configuration{
    String codeBase;
    String template;

    @Override
    public void init() throws Exception {
        setJsonPath("MainSpringDetails.json");
        MainSpringDetails v = this.read();
        this.setCodeBase(v.getCodeBase());        
        this.setTemplate(v.getTemplate());
    }
}
