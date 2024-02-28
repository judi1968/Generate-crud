package ambovombe.kombarika;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.service.vueJs.SideBar;
import ambovombe.kombarika.generator.service.vueJs.VueJs;
import ambovombe.kombarika.generator.service.vueJs.Router;

import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;
/**
 *
 *  @author Judi
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws SQLException
     */
     
    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator();
        String pathBack = "./result_BackEnd";
        String pathFront = "./result_FrontEnd";
        String framework = "java:spring-boot";
        String packageName = "com.judiproject.generate";
        String entity = "entity";
        String controller = "controller";
        String repository = "repository";
        String view = "view";
        int portSpring = 4090;
        String host = "localhost";
        String protocolUrl = "http";
        String url = "http://localhost:8080";
        String springUtil = "springUtil";
        try{
            // String[] tables = {"district","region"};
            // DbConnection dbConnection = codeGenerator.getDbConnection();
            // String str = dbConnection.getListConnection().get(dbConnection.getInUseConnection()).getDatabaseType().getForeignKeyQuery();
            // str = str.replace("?", "commune");
            // System.out.println(str);
            // HashMap<String, String> foreignKeys = DbService.getForeignKeys(dbConnection, "commune");
            // for (Map.Entry<String, String> set : foreignKeys.entrySet()) {
            //     System.out.println(set.getKey() + " " + set.getValue());
            // }
            String[] tables = DbService.getAllTablesArrays(codeGenerator.getDbConnection());
            // for(String table: tables)
            //     System.out.println(table);
            codeGenerator.generateAll(pathBack,pathFront, packageName, entity, controller, repository, view, portSpring,host,protocolUrl, tables, framework,springUtil);
            VueJs.generateAllViews(tables, url, codeGenerator.getDbConnection());
            SideBar.generateSideBar(tables);
            Router.generateRouter(tables);
            // codeGenerator.generateEntity(path, "car", packageName+".entity", framework);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            codeGenerator.getDbConnection().close();
        }
    }
}
