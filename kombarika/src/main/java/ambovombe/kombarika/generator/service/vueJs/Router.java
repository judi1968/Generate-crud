package ambovombe.kombarika.generator.service.vueJs;

import java.io.File;

import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Misc;

public class Router {
    public static void generateRouter(String[] tables)
            throws Exception {
        String template = FileUtility
                .readOneFile(Misc.getViewJsTemplateLocation().concat(File.separator).concat("Router.template"));
        String viewContent = template.replace("#import#", getImport(tables))
                                     .replace("#route#", getRoute(tables));

        String fileName = "Index.js";
        String path = "./Vue";
        FileUtility.generateFile(path, fileName, viewContent);
    }

    public static String getImport(String[] tables) {
        StringBuilder Builder = new StringBuilder();
        for (String table : tables) {
            String column = ObjectUtility.formatToCamelCase(table);
            Builder.append("import ").append(column).append(
                    " from '../components/#column#.vue'\n".replace("#column#", ObjectUtility.capitalize(column)));
        }
        return Builder.toString();
    }

    public static String getRoute(String[] tables) {
        StringBuilder Builder = new StringBuilder();
        for (String table : tables) {
            String column = ObjectUtility.formatToCamelCase(table);
            Builder.append(
                    "{ \n\t\tpath: '/#column#',\n\t\tname: '#column#',\n\t\tcomponent: #column#\n\t},\n\t"
                            .replace("#column#", column));
        }
        return Builder.toString();
    }
}
