
import Utils.ConnectionManager;
import Utils.ViewManager;
import Views.AccountView;
import Views.LoginView;
import Views.RegisterView;
import Views.WelcomeView;

import java.sql.Connection;

public class Main {


    public static void main(String[] args) {

        ViewManager.getViewManager().registerView(new WelcomeView());
        ViewManager.getViewManager().registerView(new RegisterView());
        ViewManager.getViewManager().registerView(new LoginView());
        ViewManager.getViewManager().registerView(new AccountView());



        try{
            Connection conn = ConnectionManager.getConnection();

            ViewManager.getViewManager().navigate("welcome");
            while(ViewManager.getViewManager().isRunning()){
                ViewManager.getViewManager().render();
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

}


