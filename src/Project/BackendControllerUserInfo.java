package Project;

import se.chalmers.cse.dat216.project.*;

public class BackendControllerUserInfo {
    private IMatDataHandler iM;
    private static BackendControllerUserInfo bck;


    public BackendControllerUserInfo() {
        iM = IMatDataHandler.getInstance();
        bck = this;
    }

    public static BackendControllerUserInfo getInstance(){
        return bck;
    }

    public Customer getCustomer(){
        return iM.getCustomer();
    }

    public User getUser(){
        return iM.getUser();
    }

    public CreditCard getCreditCard(){
        return iM.getCreditCard();
    }

    public boolean isCustomerComplete(){
        return iM.isCustomerComplete();
    }
}
