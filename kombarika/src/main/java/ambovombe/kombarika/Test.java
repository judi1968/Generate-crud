package ambovombe.kombarika;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.CodeGenerator;
import ambovombe.kombarika.generator.service.DbService;

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
        String packageName = "com.project.generate";
        String entity = "entity";
        String controller = "controller";
        String repository = "models";
        String view = "view";
        String url = "http://localhost:8080";
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
            codeGenerator.generateAll(pathBack,pathFront, packageName, entity, controller, repository, view, url, tables, framework);
            
            // codeGenerator.generateEntity(path, "car", packageName+".entity", framework);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            codeGenerator.getDbConnection().close();
        }
    }
}
