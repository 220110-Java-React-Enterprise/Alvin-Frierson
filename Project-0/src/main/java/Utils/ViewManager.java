package Utils;

import Views.View;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


/**
 * Set up for the View manager which stores different views in a CustomArrayList
 * */
public class ViewManager {

    //inits
    private static ViewManager viewManager;
    private boolean running;
    private final Scanner scanner;
    CustomArrayList<View> viewList;
    View nextView;

    private ViewManager() {
        // set up starting views and references
        running = true;
        scanner = new Scanner(System.in);
        viewList = new CustomArrayList<>();
    }

    // Method check to see if viewManager is null and if it is sets the viewManager to a new ViewManager and returns that viewManager
    public static ViewManager getViewManager(){
        if(viewManager == null){
            viewManager = new ViewManager();
        }
        return viewManager;
    }

    // Method takes in a destination string and loops that view through my list of views
    // if the destination view equals a view in the list, it set the next view to the destination
    public void navigate(String destination) {
        for (View view: viewList) {
            if (view.getViewName(view).equals(destination)) {
                nextView = view;
            }
        }
    }

    public void registerView(View view){
        viewList.add(view);
    }

    public void render() throws SQLException, IOException{
        nextView.renderView();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void quit() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
