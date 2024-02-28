package ambovombe.kombarika.configuration.main;


import ambovombe.kombarika.configuration.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Judi
 */
@Getter @Setter
public class ApplicationProprietiesDetails extends Configuration{
    String serverPort;
    String dbUrl;
    String dbUserName;
    String dbPassword;
    String dbDriver;
    String jpa;
    String template;

    @Override
    public void init() throws Exception {
        setJsonPath("ApplicationProprietiesDetails.json");
        ApplicationProprietiesDetails v = this.read();
        this.setServerPort(v.getServerPort());       
        this.setDbUrl(v.getDbUrl());   
        this.setDbDriver(v.getDbDriver());       
        this.setDbPassword(v.getDbPassword());       
        this.setDbUserName(v.getDbUserName());       
        this.setJpa(v.getJpa());
        this.setTemplate(v.getTemplate());
    }
}
