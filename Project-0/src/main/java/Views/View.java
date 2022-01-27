package Views;

import Utils.ViewManager;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Abstract class to set up the view manager
 * @return     returns the particular view name being requested
 * */
public abstract class View {
    protected String viewName;
    protected ViewManager viewManager;

    public String getViewName(View view){
        return viewName;
    }

    public abstract void renderView() throws SQLException, IOException;
}
