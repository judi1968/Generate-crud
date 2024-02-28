package ambovombe.kombarika.generator.service.springUtils;

import java.io.File;

import ambovombe.kombarika.configuration.main.ApplicationProprietiesDetails;
import ambovombe.kombarika.configuration.main.MainSpringDetails;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationProprieties {
    ApplicationProprietiesDetails applicationProprietiesDetails;

    public String getCompleteServerPort(int port){
        String res = "";
        res += this.getApplicationProprietiesDetails().getServerPort().replace("#serverPort#", port+"");
        return res;
    }
    
    
 
    public String generateApplicationProprieties(int port) throws Exception{
        String res = "";        
        String tempPath = Misc.getSpringUtilsTemplateLocation().concat(File.separator).concat(this.getApplicationProprietiesDetails().getTemplate());
        String template = FileUtility.readOneFile(tempPath);
        res = template
        .replace("#serverPort#", getCompleteServerPort(port))
        .replace("#dbUrl#", this.getApplicationProprietiesDetails().getDbUrl())
        .replace("#dbUserName#", this.getApplicationProprietiesDetails().getDbUserName())
        .replace("#dbPassword#", this.getApplicationProprietiesDetails().getDbPassword())
        .replace("#dbDriver#", this.getApplicationProprietiesDetails().getDbDriver())
        .replace("#jpa#", this.getApplicationProprietiesDetails().getJpa());
        return res;
    }
}
