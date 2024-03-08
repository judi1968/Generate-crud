package ambovombe.kombarika.utils;

import java.io.File;

public class Misc {
    public static String currentLocation(String name) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(name).getPath().replace("%20", " ");
    }

    public static String tabulate(String string){
        string = "\t" + string;
        return string.replace("\n", "\n\t");
    }

    public static String getTemplateLocation(){
        return currentLocation("template");
    }

    public static String getSourceTemplateLocation(){
        return getTemplateLocation() + File.separator + "sourceCode";
    }

    public static String getViewTemplateLocation(){
        return getTemplateLocation() + File.separator + "view";
    }

    public static String getSpringUtilsTemplateLocation(){
        return getTemplateLocation() + File.separator + "springUtils";
    }

    public static String getViewJsTemplateLocation(){
        return getTemplateLocation() + File.separator + "VueJs";
    }

    public static String getConfigLocation(){
        return currentLocation("conf");
    }

    public static String getConnectionConfLocation(){
        String separator = File.separator;
        return getConfigLocation() + separator + "connection";
    }

    public static String getGeneratorConfLocation(){
        String separator = File.separator;
        return getConfigLocation() + separator + "generator";
    }
}
