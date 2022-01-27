package Views;

import Utils.ViewManager;


/**
 * Show the Welcome page of the Bank and allows a user to register and log into their account
 * */
public class WelcomeView extends View{
    public WelcomeView(){
        viewName = "welcome";
        viewManager = ViewManager.getViewManager();
    }

    // override method for the renderView of the View class
    @Override
    public void renderView(){
        System.out.println("Welcome to Hip Pocket National Bank\n" +
                "========================\n" +
                "1. Register\n" +
                "2. Login\n" +
                "========================\n");

        String input = viewManager.getScanner().nextLine();

        switch (input){
            case "1" :
                viewManager.navigate("register");
                break;
            case "2" :
                viewManager.navigate("login");
                break;
            default:
                System.out.println("Try again!");
                break;
        }
    }
}
