package ambovombe.kombarika.generator.service.springUtils;

import java.io.File;

import ambovombe.kombarika.configuration.main.ApplicationProprietiesDetails;
import ambovombe.kombarika.database.DbProperties;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationProprieties {
    ApplicationProprietiesDetails applicationProprietiesDetails;
    DbProperties dbProperties;
    public String getCompleteServerPort(int port){
        String res = "";
        res += this.getApplicationProprietiesDetails().getServerPort().replace("#serverPort#", port+"");
        return res;
    }
    public String getCompleteDbUrl(){
        String res = "";
        String[] dataSourceSpliterComma = dbProperties.getDatasource().split(":");
        String dataSourcePortAndDatabase = dataSourceSpliterComma[dataSourceSpliterComma.length-1];
        String port = dataSourcePortAndDatabase.split("/")[0];
        String database = dataSourcePortAndDatabase.split("/")[1];

        res += this.getApplicationProprietiesDetails().getDbUrl()
        .replace("#port#", port)
        .replace("#database#",database);
        return res;
    }
    public String getCompleteDbUsername(){
        String res = "";
        String username = dbProperties.getUsername();
        res += this.getApplicationProprietiesDetails().getDbUserName()
        .replace("#username#", username);
        return res;
    }
    public String getCompleteDbPassword(){
        String res = "";
        String password = dbProperties.getPassword();
        res += this.getApplicationProprietiesDetails().getDbPassword()
        .replace("#password#", password);
        return res;
    }    
 
    public String generateApplicationProprieties(int port) throws Exception{
        String res = "";        
        String tempPath = Misc.getSpringUtilsTemplateLocation().concat(File.separator).concat(this.getApplicationProprietiesDetails().getTemplate());
        String template = FileUtility.readOneFile(tempPath);
        res = template
        .replace("#serverPort#", getCompleteServerPort(port))
        .replace("#dbUrl#", getCompleteDbUrl())
        .replace("#dbUserName#", getCompleteDbUsername())
        .replace("#dbPassword#", getCompleteDbPassword())
        .replace("#dbDriver#", this.getApplicationProprietiesDetails().getDbDriver())
        .replace("#jpa#", this.getApplicationProprietiesDetails().getJpa());
        return res;
    }
}
