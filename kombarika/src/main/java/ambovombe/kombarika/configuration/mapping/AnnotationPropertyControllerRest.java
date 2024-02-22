package ambovombe.kombarika.configuration.mapping;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter

public class AnnotationPropertyControllerRest {
    String table;
    String autoIncrement;
    String column;
    String entity;
    String controllerRest;
    Constraint constraints;
}
