package sample;

import se.chalmers.cse.dat216.project.*;


public class BackendController {
    IMatDataHandler iM;
    Customer customer;

    public BackendController() {
        iM = IMatDataHandler.getInstance();
        customer = iM.getCustomer();

    }
}
