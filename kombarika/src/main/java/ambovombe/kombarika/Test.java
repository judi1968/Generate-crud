package ambovombe.kombarika;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import ambovombe.kombarika.generator.service.vueJs.VueJs;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.service.DbService;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author Judi
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws SQLException
     */

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator();
        String path = "./";
        String framework = "java:spring-boot";
        String packageName = "com.package.generate";
        String entity = "entity";
        String controller = "controller";
        String repository = "repository";
        String view = "view";
        String url = "http://localhost:8080";
        try {
            String[] tables = DbService.getAllTablesArrays(codeGenerator.getDbConnection());
            codeGenerator.generateAll(path, packageName, entity, controller, repository, view, url, tables, framework);
            VueJs.generateAllViews(tables, url, codeGenerator.getDbConnection());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            codeGenerator.getDbConnection().close();
        }
    }
}
