package ambovombe.kombarika.configuration.main;

import java.util.HashMap;

import ambovombe.kombarika.configuration.Configuration;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Judi
 */
@Getter @Setter
public class ViewVueJsDetails extends Configuration{
    String inputInsert;
    String inputUpdate;
    String tableHeader;
    String template;
    String select;
    String option;
    String selectUpdate;
    String optionUpdate;
    String titleVueJs;
    HashMap<String, String> listMapping;

    @Override
    public void init() throws Exception {
        setJsonPath("ViewVueJsDetails.json");
        ViewVueJsDetails v = this.read();
        this.setTitleVueJs(v.getTitleVueJs());
        this.setInputInsert(v.getInputInsert());
        this.setInputUpdate(v.getInputUpdate());
        this.setListMapping(v.getListMapping());
        this.setTableHeader(v.getTableHeader());
        this.setOption(v.getOption());
        this.setSelect(v.getSelect());
        this.setOptionUpdate(v.getOptionUpdate());
        this.setSelectUpdate(v.getSelectUpdate());
        this.setTemplate(v.getTemplate());
    }
}
