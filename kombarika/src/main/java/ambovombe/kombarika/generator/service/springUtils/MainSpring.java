package ambovombe.kombarika.generator.service.springUtils;

import java.io.File;

import ambovombe.kombarika.configuration.main.MainSpringDetails;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainSpring {
    MainSpringDetails mainSpringDetails;

 
    public String generateMainSpring(String packageName) throws Exception{
        String res = "";        
        String tempPath = Misc.getSpringUtilsTemplateLocation().concat(File.separator).concat(this.getMainSpringDetails().getTemplate());
        String template = FileUtility.readOneFile(tempPath);
        res = template.replace("#package#", "package ".concat(packageName))
        .replace("#codeBase#", this.getMainSpringDetails().getCodeBase());
        
        return res;
    }
}
