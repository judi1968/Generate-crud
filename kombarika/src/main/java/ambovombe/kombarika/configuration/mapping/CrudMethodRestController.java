package ambovombe.kombarika.configuration.mapping;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CrudMethodRestController {
    String findAll;
    String findById;
    String delete;
    String update;
    String save;
}
