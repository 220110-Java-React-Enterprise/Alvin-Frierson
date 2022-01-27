package Views;

import Persistence.AssociateModel;
import Persistence.AssociateRepo;
import Utils.ContextStore;
import Utils.ViewManager;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Create a register view using the view manager
 * */

public class RegisterView extends View{
    public RegisterView(){
        viewName = "register";
        viewManager = ViewManager.getViewManager();
    }

    // static method for email validation
    public static boolean emailValidation(String email){
        String emailPattern ="^[a-zA-Z0-9_#$%&’*+/=?^.-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailPattern);
        Matcher mat = pat.matcher(email);
        return mat.matches();
    }

    // method override for the renderView method in the View class
    @Override
    public void renderView() throws SQLException, IOException {
        AssociateModel newAssociate = AssociateModel.getUserModel();

        System.out.println("Register new user\n==============");
        System.out.println("Enter First Name:");
        newAssociate.setFirstName(viewManager.getScanner().nextLine());

        System.out.println("Enter Last Name; ");
        newAssociate.setLastName(viewManager.getScanner().nextLine());

        System.out.println("Enter Email Address: ");
        String email = viewManager.getScanner().nextLine();

        if(emailValidation(email)){
            newAssociate.setEmail(email);
            System.out.println("Enter new username: ");
            newAssociate.setUsername(viewManager.getScanner().nextLine());

            System.out.println("Enter new password: ");
            newAssociate.setPassword(viewManager.getScanner().nextLine());

            AssociateRepo repo = new AssociateRepo();
            int id_result = repo.create(newAssociate);
            if (id_result < 0){
                System.out.println("Error when entering credentials to database");
                renderView();
            }else {
                newAssociate.setUserId(id_result);
            }
            //ContextStore.setCurrentUser(newAssociate);
            viewManager.navigate("login");

        } else {
            System.out.println("Incorrect email format, try again.");
            renderView();
        }


    }


}
