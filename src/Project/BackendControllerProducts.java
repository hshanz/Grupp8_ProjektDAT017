package Project;

import se.chalmers.cse.dat216.project.*;


public class BackendControllerProducts {
    IMatDataHandler iM;
    Customer customer;

    public BackendControllerProducts() {
        iM = IMatDataHandler.getInstance();
        customer = iM.getCustomer();

    }

    public void setCustomer(String customer) {

        this.customer.setFirstName(customer);
    }

    public String getCustomer() {
        return customer.getFirstName();
    }
}
