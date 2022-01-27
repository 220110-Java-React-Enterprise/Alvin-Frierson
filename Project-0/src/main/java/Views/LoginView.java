package Views;

import Persistence.AssociateModel;
import Persistence.AssociateRepo;

import Utils.ContextStore;
import Utils.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

import java.util.Scanner;

public class LoginView extends View{

   public LoginView(){
       viewName = "login";
       viewManager = ViewManager.getViewManager();
   }

   @Override

       public void renderView() throws SQLException, IOException {
           System.out.println("User Login\n===============");
           System.out.println("Enter username:");
           String username =  viewManager.getScanner().nextLine();

           System.out.println("Enter password: ");
           String password =  viewManager.getScanner().nextLine();

           AssociateRepo repo = new AssociateRepo ();
           AssociateModel user = repo.authenticate(username, password);

           if(user == null) {
               System.out.println("\nIncorrect credentials... \n\n\n");
               viewManager.navigate("welcome");
               return;
           }

           ContextStore.setCurrentUser(user);
           viewManager.navigate("account");


       }


}
