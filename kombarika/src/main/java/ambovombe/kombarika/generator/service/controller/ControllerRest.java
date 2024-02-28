package ambovombe.kombarika.generator.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.service.GeneratorService;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import ambovombe.kombarika.utils.Misc;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ControllerRest{
    LanguageProperties languageProperties;
    CrudMethodRestController crudMethodRestController;
    ControllerRestProperty controllerRestProperty;
    AnnotationPropertyControllerRest annotationPropertyControllerRest;
    ImportsControllerRest importsControllerRest;
    DbConnection dbConnection;
    TypeMapping typeMapping;

    /**
     * Generate the function that make the insert to the database
     * @param table
     * @param language
     * @param method
     * @param controllerRestProperty
     * @return the string form of the function
     */
    public String save(String table){
        String body = "";
        String args = "";
        args += this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        body += Misc.tabulate(
            this.getCrudMethodRestController().getSave().replace("className", ObjectUtility.formatToCamelCase(table)));        
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "create")
                .replace("#type#", this.getControllerRestProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax()
                            .replace("?", this.getControllerRestProperty().getPost()
                                .replace("className", '"'+"/"+ObjectUtility.formatToCamelCase(table)+'"')) + "\n" + function);
    }
    
    public String getArgPrimaryKey(HashMap<String, String> columns,List<String> primaryKeys){
        String res = "";

        for (Map.Entry<String, String> set : columns.entrySet()) {
            if (primaryKeys.contains(set.getKey())) {
                res +=
                this.getLanguageProperties().getFieldSyntax()
                    .replace("Type", typeMapping.getListMapping().get(set.getValue()).getType())
                    .replace("field", ObjectUtility.formatToCamelCase(set.getKey()));
                break;
            }
            
        }
        return res.replace(";", "");
    }
    public String update(HashMap<String, String> columns,List<String> primaryKeys,String table) throws Exception{
        String body = "";
        String args = "";
        String primaryKeyArgs = getArgPrimaryKey(columns, primaryKeys);
        String primmaryKeyName = primaryKeyArgs.split(" ")[1];
        args += this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table)
                + ","
                + this.getLanguageProperties().getAnnotationSyntax().replace("?",this.getControllerRestProperty().getAnnotationArgumentParameterLink()) + " "+primaryKeyArgs;
        body += Misc.tabulate(
            this.getCrudMethodRestController().getUpdate()
            .replace("className",ObjectUtility.formatToCamelCase(table)))
            .replace("#primaryKeyArgSetter#",ObjectUtility.capitalize(primmaryKeyName))
            .replace("#primaryKeyArg#",ObjectUtility.formatToCamelCase(primmaryKeyName));
            String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "update")
                .replace("#type#", this.getControllerRestProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getPut()
        .replace("classNameWithId", '"'+"/"+ObjectUtility.formatToCamelCase(table)+"/{id}"+'"')) + "\n" + function);
    }

    public String delete(HashMap<String, String> columns,List<String> primaryKeys,String table) throws Exception{
        String body = "";   
        String args = "";
        String primaryKeyArgs = getArgPrimaryKey(columns, primaryKeys);
        String primmaryKeyName = primaryKeyArgs.split(" ")[1];
        args +=  this.getLanguageProperties().getAnnotationSyntax().replace("?",this.getControllerRestProperty().getAnnotationArgumentParameterLink()) + " "+primaryKeyArgs;
        body += Misc.tabulate(
            this.getCrudMethodRestController().getDelete()
                .replace("className", ObjectUtility.formatToCamelCase(table)))
                .replace("#type#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))
                .replace("#primaryKeyArgSetter#",ObjectUtility.capitalize(primmaryKeyName))
                .replace("#primaryKeyArg#",ObjectUtility.formatToCamelCase(primmaryKeyName));
                
                String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "delete")
                .replace("#type#", this.getControllerRestProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getDelete()
        .replace("classNameWithId", '"'+"/"+ObjectUtility.formatToCamelCase(table)+"/{id}"+'"')) + "\n" + function);
    }

    public String findAll(String table){
        String body = "";
        body += Misc.tabulate(this.getCrudMethodRestController().getFindAll()
        .replace("className", ObjectUtility.formatToCamelCase(table))
        .replace("#type#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))));        
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "findAll")
                .replace("#type#", this.getControllerRestProperty().getReturnType().replace("?", this.getLanguageProperties().getListSyntax().replace("?",ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))))
                .replace("#arg#", "")
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getGet()
        .replace("className", '"'+"/"+ObjectUtility.formatToCamelCase(table)+"s"+'"')) + "\n" + function);
    }

    public String findById(HashMap<String, String> columns,List<String> primaryKeys,String table) throws Exception{
        String body = "";   
        String args = "";
        String primaryKeyArgs = getArgPrimaryKey(columns, primaryKeys);
        String primmaryKeyName = primaryKeyArgs.split(" ")[1];

        args += this.getLanguageProperties().getAnnotationSyntax().replace("?",this.getControllerRestProperty().getAnnotationArgumentParameterLink()) + " "+primaryKeyArgs;
        body += Misc.tabulate(
            this.getCrudMethodRestController().getFindById()
                .replace("className", ObjectUtility.formatToCamelCase(table))
                .replace("#primaryKeyArg#",ObjectUtility.formatToCamelCase(primmaryKeyName))
                .replace("#type#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))));
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "findById")
                .replace("#type#", this.getControllerRestProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getFindById()
        .replace("classNameWithId", '"'+"/"+ObjectUtility.formatToCamelCase(table)+"/{id}"+'"')) + "\n" + function);
    }
    public String getCrudMethodRestControllers(String table) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, String> columns = DbService.getColumnNameAndType(dbConnection.getConnection(), table);
        List<String> primaryKeyColumn = DbService.getPrimaryKey(dbConnection, table);
        String save = save(table);
        String findAll = findAll(table);
        String update = update(columns,primaryKeyColumn,table);
        String delete = delete(columns,primaryKeyColumn,table);
        String findById = findById(columns,primaryKeyColumn,table);
        stringBuilder.append(save);
        stringBuilder.append("\n");
        stringBuilder.append(update);
        stringBuilder.append("\n");
        stringBuilder.append(delete);
        stringBuilder.append("\n");
        stringBuilder.append(findAll);
        stringBuilder.append("\n");
        stringBuilder.append(findById);
        return stringBuilder.toString();
    }

    public String getControllerRestField(String table){
        String res = "";
        if(!this.getControllerRestProperty().getField().equals("") && !this.getControllerRestProperty().getAnnotationField().equals("")){
            res += "\t"
                    + this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getAnnotationField()) + "\n"
                    + "\t" + this.getControllerRestProperty().getField().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))) + "\n";
        }else if (!this.getControllerRestProperty().getField().equals("")){
            res += "\t" + this.getControllerRestProperty().getField().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))) + "\n";
        }
        return res;
    }
    

    public  String getControllerRestClass(String table){
        String res = "";
        res += this.getLanguageProperties().getAnnotationSyntax()
                .replace("?", this.getAnnotationPropertyControllerRest().getControllerRest()) + "\n"
                + this.getLanguageProperties().getClassSyntax() + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table).concat("RestController")) + "\n";
        return res;
    }

    public String getControllerRestImport(String repository, String entity, String table) throws Exception{
        String res = "";
        String imp = this.getLanguageProperties().getImportSyntax();
        for(String item : this.getImportsControllerRest().getControllerRest()){
            item = item
            .replace("packageName", repository)
            .replace("className", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))
            .replace("entity", entity);
            res += imp+ " " + item + "" + this.getLanguageProperties().getEndOfInstruction() + "\n";
        }
        return res;
    }

    public String getConstructor(String table) throws Exception{
        String res = "";
        if(!this.getControllerRestProperty().getConstructor().equals("")){
            res = this.getControllerRestProperty().getConstructor()
                .replace("#name#", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)));
        }
        return res;
    }
    
    public String generateControllerRest(String template, String table, String packageName, String repository, String entity) throws Exception {
        String res = template.replace("#package#", GeneratorService.getPackage(this.getLanguageProperties(), packageName))
                .replace("#imports#", getControllerRestImport(repository, entity, table))
                .replace("#class#", getControllerRestClass(table))
                .replace("#open-bracket#", languageProperties.getOpenBracket())
                .replace("#close-bracket#", languageProperties.getCloseBracket())
                .replace("#fields#", getControllerRestField(table))
                .replace("#constructors#", getConstructor(table))
                .replace("#methods#", getCrudMethodRestControllers(table))
                .replace("#encapsulation#", "");
        return res;
    }
}
