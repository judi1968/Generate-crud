package ambovombe.kombarika.generator.service.controller;

import ambovombe.kombarika.configuration.mapping.LanguageProperties;
import ambovombe.kombarika.configuration.mapping.*;
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
            this.getCrudMethodRestController().getSave().replace("className", table));        
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "create")
                .replace("#type#", this.getControllerRestProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax()
                            .replace("?", this.getControllerRestProperty().getPost()
                                .replace("className", '"'+"/"+table+'"')) + "\n" + function);
    }

    public String update(String table) throws Exception{
        String body = "";
        String args = "";
        args += this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        body += Misc.tabulate(
            this.getCrudMethodRestController().getUpdate().replace("#object#", ObjectUtility.formatToCamelCase(table)));
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "update")
                .replace("#type#", this.getControllerRestProperty().getReturnType().replace("?", ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table))))
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getPut()) + "\n" + function);
    }

    public String delete(String table) throws Exception{
        String body = "";   
        String args = "";
        args += this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getAnnotationArgumentParameterFormData()) + " "
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)) + " "
                + ObjectUtility.formatToCamelCase(table);
        body += Misc.tabulate(this.getCrudMethodRestController().getDelete().replace("#object#", ObjectUtility.formatToCamelCase(table)));
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "delete")
                .replace("#type#", "void")
                .replace("#arg#", args)
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getDelete()) + "\n" + function);
    }

    public String findAll(String table){
        String body = "";
        body += Misc.tabulate(this.getCrudMethodRestController().getFindAll().replace("#object#", ObjectUtility.formatToCamelCase(table)));
        String function =  this.getLanguageProperties().getMethodSyntax()
                .replace("#name#", "findAll")
                .replace("#type#", this.getControllerRestProperty().getReturnType().replace("?", this.getLanguageProperties().getListSyntax().replace("?",ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table)))))
                .replace("#arg#", "")
                .replace("#body#", body);
        return Misc.tabulate(this.getLanguageProperties().getAnnotationSyntax().replace("?", this.getControllerRestProperty().getGet()) + "\n" + function);
    }

    public String findById(String table) throws Exception{
        String res = "akory";
        return res;
    }
    public String getCrudMethodRestControllers(String table) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        String save = save(table);
        String findAll = findAll(table);
        String update = update(table);
        String delete = delete(table);
        String findById = findById(table);
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
                + ObjectUtility.capitalize(ObjectUtility.formatToCamelCase(table).concat("ControllerRest")) + "\n";
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
                .replace("#fields#", " ")
                .replace("#constructors#", getConstructor(table))
                .replace("#methods#", getCrudMethodRestControllers(table))
                .replace("#encapsulation#", "");
        return res;
    }
}
