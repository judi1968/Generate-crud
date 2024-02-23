package ambovombe.kombarika.generator.service.vueJs;

import java.util.HashMap;
import java.util.List;

import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.utils.ObjectUtility;

public class VueJs {
    public static String generateView(String table, String API, String templatePath, DbConnection dbConnection)
            throws Exception {
        String res = "";
        String template = FileUtility.readOneFile(templatePath);
        List<String> primaryKeys = DbService.getPrimaryKey(dbConnection, table);
        String path = ObjectUtility.formatToCamelCase(table);
        HashMap<String, String> columns = DbService.getDetailsColumn(dbConnection.getConnection(), table);
        HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, table);

        res = template.replace("#table#", table)
                .replace("#API#", API)
                .replace("#path#", path)
                .replace("#column-title#", generateColumnTitle(columns))
                .replace("#column-row#", generateColumnRows(columns))
                .replace("#column-add#", generateColumnAdd(columns))
                .replace("#column-edit#", generateColumnEdit(columns));

        return res;
    }

    private static String generateColumnTitle(HashMap<String, String> columns) {
        StringBuilder titleBuilder = new StringBuilder();
        for (String columnName : columns.keySet()) {
            titleBuilder.append("<th scope=\"col\">").append(columnName).append("</th>\n\t\t\t");
        }
        return titleBuilder.toString();
    }

    private static String generateColumnRows(HashMap<String, String> columns) {
        StringBuilder rowBuilder = new StringBuilder();
        for (String columnName : columns.keySet()) {
            rowBuilder.append("<td>{{ item.").append(ObjectUtility.formatToCamelCase(columnName)).append(" }}</td>\n\t");
        }
        return rowBuilder.toString();
    }

    private static String generateColumnAdd(HashMap<String, String> columns) {
        StringBuilder addBuilder = new StringBuilder();
        for (String columnName : columns.keySet()) {
            addBuilder.append("<div class=\"mb-3\">")
                    .append("<label for=\"").append(ObjectUtility.formatToCamelCase(columnName))
                    .append("\" class=\"form-label\">")
                    .append(columnName).append("</label>")
                    .append("<input type=\"text\" class=\"form-control\" id=\"")
                    .append(ObjectUtility.formatToCamelCase(columnName)).append("\" v-model=\"")
                    .append(ObjectUtility.formatToCamelCase(columnName)).append("\">")
                    .append("</div>\n\t\t");
        }
        return addBuilder.toString();
    }

    private static String generateColumnEdit(HashMap<String, String> columns) {
        StringBuilder editBuilder = new StringBuilder();
        for (String columnName : columns.keySet()) {
            editBuilder.append("<div class=\"mb-3\">")
                    .append("<label for=\"").append(ObjectUtility.formatToCamelCase(columnName))
                    .append("\" class=\"form-label\">")
                    .append(columnName).append("</label>")
                    .append("<input type=\"text\" class=\"form-control\" id=\"")
                    .append(ObjectUtility.formatToCamelCase(columnName)).append("\" v-model=\"selectedItem.")
                    .append(ObjectUtility.formatToCamelCase(columnName)).append("\">")
                    .append("</div>\n\t\t");
        }
        return editBuilder.toString();
    }

    public static void generateAllViews(String[] tables ,String API,DbConnection dbConnection)
            throws Exception {
        for (String table : tables) {
            String viewContent = generateView(table, API,"./Kombarika/src/main/resources/template/VueJs/Page.template", dbConnection);
            String fileName = ObjectUtility.formatToCamelCase(table) + ".vue";
            FileUtility.createDirectory("Vue", "./");
            String path = "./Vue";
            FileUtility.generateFile(path, fileName, viewContent);
        }
    }
}
