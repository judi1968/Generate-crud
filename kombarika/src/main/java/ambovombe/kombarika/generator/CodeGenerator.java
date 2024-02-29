/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ambovombe.kombarika.generator;

import ambovombe.kombarika.configuration.main.ApplicationProprietiesDetails;
import ambovombe.kombarika.configuration.main.LanguageDetails;
import ambovombe.kombarika.configuration.main.MainSpringDetails;
import ambovombe.kombarika.configuration.main.TypeProperties;
import ambovombe.kombarika.configuration.main.ViewDetails;
import ambovombe.kombarika.configuration.main.ViewVueJsDetails;
import ambovombe.kombarika.configuration.mapping.*;
import ambovombe.kombarika.database.DbConnection;
import ambovombe.kombarika.database.DbProperties;
import ambovombe.kombarika.generator.parser.FileUtility;
import ambovombe.kombarika.generator.service.DbService;
import ambovombe.kombarika.generator.service.GeneratorService;
import ambovombe.kombarika.generator.service.controller.Controller;
import ambovombe.kombarika.generator.service.controller.ControllerRest;
import ambovombe.kombarika.generator.service.entity.Entity;
import ambovombe.kombarika.generator.service.repository.Repository;
import ambovombe.kombarika.generator.service.springUtils.ApplicationProprieties;
import ambovombe.kombarika.generator.service.springUtils.MainSpring;
import ambovombe.kombarika.generator.service.view.View;
import ambovombe.kombarika.generator.service.view.ViewVueJs;
import ambovombe.kombarika.generator.utils.ObjectUtility;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.List;

/**
 * @author Mamisoa
 * @author rakharrs
 */
@Getter @Setter
public class CodeGenerator {
    DbConnection dbConnection;
    LanguageDetails languageDetails;
    TypeProperties typeProperties;
    ViewDetails viewDetails;
    ViewVueJsDetails viewVueJsDetails;
    MainSpringDetails mainSpringDetails;
    ApplicationProprietiesDetails applicationProprietiesDetails;


    public CodeGenerator() throws Exception {
        this.dbConnection = new DbConnection();
        this.dbConnection.init();
        this.languageDetails = new LanguageDetails();
        this.languageDetails.init();
        this.typeProperties = new TypeProperties();
        this.typeProperties.init();
        this.viewDetails = new ViewDetails();
        this.viewDetails.init();
        this.viewVueJsDetails = new ViewVueJsDetails();
        this.viewVueJsDetails.init();
        this.mainSpringDetails = new MainSpringDetails();
        this.mainSpringDetails.init();
        this.applicationProprietiesDetails = new ApplicationProprietiesDetails();
        this.applicationProprietiesDetails.init();
    }

    public  void generateEntity(
        String path, 
        String table, 
        String packageName, 
        String lang)
    throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        generateEntityFile(path, table, packageName, language, framework);
    }

    public void generateController(
        String path, 
        String table, 
        String packageName, 
        String repository, 
        String entity, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String controller = buildController(table, packageName, repository, entity, language, framework);
        generateControllerFile(path, table, packageName, language, framework, controller);
    }

    public void generateControllerRest(
        String path, 
        String table, 
        String packageName, 
        String repository, 
        String entity, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String controller = buildControllerRest(table, packageName, repository, entity, language, framework);
        generateControllerRestFile(path, table, packageName, language, framework, controller);
    }

    public void generateRepository(
        String path, 
        String table, 
        String packageName, 
        String entityPackage, 
        String lang
    ) throws Exception{
        String[] splittedLang = lang.split(":");
        String language = splittedLang[0]; String framework = splittedLang[1];
        String repository = buildRepository(table, packageName, entityPackage, language, framework);
        generateRepositoryFile(path, table, packageName, language, framework, repository);
    }


    public void generateView(
        String path, 
        String table,
        String directory, 
        String url
    ) throws Exception{
        String view = buildView(table, url);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        String fileName = GeneratorService.getFileName(table, "html");
        FileUtility.generateFile(path, fileName, view);
    }

    public void generateVueJs(
        String path, 
        String table,
        String directory, 
        String url
    ) throws Exception{
        String view = buildViewVueJs(table, url);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        String fileName = GeneratorService.getFileName(table, "vue");
        FileUtility.generateFile(path, fileName, view);
    }

    // generate main
    public void generateMainSpring(
        String packageName,
        String directory
    ) throws Exception{
        String mainSpring = buildMainSpring(packageName);
        directory= directory+"."+packageName;
        directory = directory.replace(".", File.separator);
        String path = "./";
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        String fileName = GeneratorService.getFileName("TestApplication", "java");
        FileUtility.generateFile(path, fileName, mainSpring);
    }
    public void generateApplicationProprieties(
        int portSpring,
        String directory
    ) throws Exception{
        String applciationProprietiesContenu = buildApplicationProprieties(portSpring);
        String path = "./";
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        String fileName = "application.properties";
        FileUtility.generateFile(path, fileName, applciationProprietiesContenu);
    }

    /**
     * eg : generate -p path -t table1, table2, table3 -package name -l java:spring-boot
     * @author rakharrs
     */
    public String buildEntity(String table, String packageName, String language, String framework) throws Exception {
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        String template = frameworkProperties.getTemplate();
        Entity entity = new Entity();
        entity.setAnnotationProperty(frameworkProperties.getAnnotationProperty());
        entity.setLanguageProperties(languageProperties);
        entity.setTypeMapping(typeMapping);
        entity.setImports(frameworkProperties.getImports());
        return entity.generateEntity(getDbConnection(), template, table, packageName);
    }

    public void generateEntityFile(
        String path, 
        String table, 
        String packageName, 
        String language, 
        String framework
    ) throws Exception{
        String entity = buildEntity(table, packageName, language, framework);
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileName(table, languageProperties.getExtension()), entity);
    }

    public String buildController(String table, String packageName, String repository, String entity, String language, String framework) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        String template = frameworkProperties.getTemplate();
        Controller controller = new Controller();
        controller.setAnnotationProperty(frameworkProperties.getAnnotationProperty());
        controller.setControllerProperty(frameworkProperties.getControllerProperty());
        controller.setCrudMethod(frameworkProperties.getCrudMethod());
        controller.setImports(frameworkProperties.getImports());
        controller.setLanguageProperties(languageProperties);
        return controller.generateController(template, table, packageName, repository, entity);
    }

    public String buildControllerRest(String table, String packageName, String repository, String entity, String language, String framework) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        String template = frameworkProperties.getTemplate();
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        ControllerRest controller = new ControllerRest();
        controller.setTypeMapping(typeMapping);
        controller.setDbConnection(this.dbConnection);
        controller.setAnnotationPropertyControllerRest(frameworkProperties.getAnnotationPropertyControllerRest());
        controller.setControllerRestProperty(frameworkProperties.getControllerRestProperty());
        controller.setCrudMethodRestController(frameworkProperties.getCrudMethodRestController());
        controller.setImportsControllerRest(frameworkProperties.getImportsControllerRest());
        controller.setLanguageProperties(languageProperties);
        return controller.generateControllerRest(template, table, packageName, repository, entity);
    }

    public void generateControllerFile(
        String path,
        String table,
        String packageName,
        String language,
        String framework,
        String content
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileName(table+"Controller", languageProperties.getExtension()), content);
    }
    public void generateControllerRestFile(
        String path,
        String table,
        String packageName,
        String language,
        String framework,
        String content
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileName(table+"RestController", languageProperties.getExtension()), content);
    }

    public String buildRepository(
        String table, 
        String packageName, 
        String entityPackage, 
        String language, 
        String framework
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        FrameworkProperties frameworkProperties = languageProperties.getFrameworks().get(framework);
        TypeMapping typeMapping = getTypeProperties().getListProperties().get(language);
        List<String> primaryKeysType = DbService.getPrimaryKeyType(dbConnection, table);
        Repository repository = new Repository();
        repository.setFrameworkProperties(frameworkProperties);
        repository.setLanguageProperties(languageProperties);
        repository.setTypeMapping(typeMapping);
        return repository.generateRepository(table, packageName, entityPackage, primaryKeysType);
    }

    public void generateRepositoryFile(
        String path,
        String table,
        String packageName,
        String language,
        String framework,
        String content
    ) throws Exception{
        LanguageProperties languageProperties = getLanguageDetails().getLanguages().get(language);
        if(languageProperties.getFrameworks().get(framework).getRepository().equals("")) return;
        String directory = packageName.replace(".", File.separator);
        FileUtility.createDirectory(directory,path);
        path = path + File.separator + directory;
        FileUtility.generateFile(path, GeneratorService.getFileName(table+languageProperties.getFrameworks().get(framework).getRepositoryProperty().getName(), languageProperties.getExtension()), content);
    }

    public String buildView(String table, String url) throws Exception{
        View view = new View();
        view.setViewDetails(this.getViewDetails());
        return view.generateView(table, url, dbConnection);
    }
    public String buildViewVueJs(String table, String url) throws Exception{
        ViewVueJs view = new ViewVueJs();
        view.setViewVueJsDetails(this.getViewVueJsDetails());
        return view.generateViewVueJs(table, url, dbConnection);
    }

    public String buildMainSpring(String packageName) throws Exception{
        MainSpring mainSpring = new MainSpring();
        mainSpring.setMainSpringDetails(this.getMainSpringDetails());
        return mainSpring.generateMainSpring(packageName);
    }

    public String buildApplicationProprieties(int portSpring) throws Exception{
        DbProperties dbProperties = this.getDbConnection().getDbProprieties();
        ApplicationProprieties applicationProprieties = new ApplicationProprieties();
        applicationProprieties.setDbProperties(dbProperties);

        applicationProprieties.setApplicationProprietiesDetails(this.getApplicationProprietiesDetails());
        return applicationProprieties.generateApplicationProprieties(portSpring);
    }
    // public static String getTemplate() throws Exception{
    //     String path = Misc.getSourceTemplateLocation() + File.separator + "Template.code";
    //     return FileUtility.readOneFile(path);
    // }
    
    public void generateAll(
        String pathBack, 
        String pathFront,
        String packageName, 
        String entity, 
        String controller, 
        String repository,
        String view,
        int portSpring,
        String host,
        String protocolUrl,
        String[] tables, 
        String framework,
        String springUtil
    ) throws Exception{
        generateMainSpring(packageName,pathBack);
        generateApplicationProprieties(portSpring,springUtil);  
        String url = protocolUrl+"://"+host+":"+portSpring;
        System.out.println(url);
        for (String table : tables) {
            generateEntity(pathBack, table, packageName + "." + entity, framework);
            generateRepository(pathBack, table, packageName + "." + repository, packageName + "." + entity, framework);
            //generateController(pathBack, table, packageName + "." + controller, packageName + "." + repository, packageName + "." + "entity", framework);  
            generateControllerRest(pathBack, table, packageName + "." + controller, packageName + "." + repository, packageName + "." + "entity", framework);  
            //generateView(pathFront, table, view, url); 
            //generateVueJs(pathFront, table, view, url); 
        }
    }

}
