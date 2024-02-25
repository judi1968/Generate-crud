package ambovombe.kombarika.generator.service.vueJs;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.parser.FileUtility;

public class SideBar {
    public static void generateSideBar(String[] tables, String API, DbConnection dbConnection)
            throws Exception {
        String template = FileUtility.readOneFile("./Kombarika/src/main/resources/template/VueJs/SideBar.template");
        String viewContent = template.replace("#column#", getAllColumn(tables));

        String fileName = "Side.vue";
        String path = "./Vue";
        FileUtility.generateFile(path, fileName, viewContent);
    }
    
    public static String getAllColumn(String[] tables) {
        StringBuilder Builder = new StringBuilder();
        for (String table : tables) {
            Builder.append("<li>").append(table).append("</li>\n\t\t\t");
        }
        return Builder.toString();
    }
}
