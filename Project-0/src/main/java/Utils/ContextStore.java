package Utils;

import Persistence.AssociateModel;

/**
 * Sets the user created by the AssociateModel to the user
 * @return              returns that user as the current user
 * */
public class ContextStore {
    private static AssociateModel currentUser;

    public static void setCurrentUser(AssociateModel user){
        currentUser = user;
    }

    public static AssociateModel getCurrentUser(){
        return currentUser;
    }
}
