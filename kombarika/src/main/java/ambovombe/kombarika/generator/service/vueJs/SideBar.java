package ambovombe.kombarika.generator.service.vueJs;
import java.io.File;

import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Misc;

public class SideBar {
    public static void generateSideBar(String[] tables)
            throws Exception {
        String template = FileUtility.readOneFile(Misc.getViewJsTemplateLocation().concat(File.separator).concat("SideBar.template"));
        String viewContent = template.replace("#column#", getAllColumn(tables));

        String fileName = "SideBar.vue";
        String path = "./Vue";
        FileUtility.generateFile(path, fileName, viewContent);
    }
    
    public static String getAllColumn(String[] tables) {
        StringBuilder Builder = new StringBuilder();
        for (String table : tables) {
            Builder.append("<li>\n\t\t\t")
                    .append("<router-link to='/").append(ObjectUtility.formatToCamelCase(table)).append("' class='link'>\n\t\t\t")
                    .append(ObjectUtility.formatToCamelCase(table)).append("\n\t\t")
                    .append("</router-link>\n\t\t")
                    .append("</li>\n\n\t\t");
        }
        return Builder.toString();
    }
}
