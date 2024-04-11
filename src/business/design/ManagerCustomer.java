package business.design;

import business.entity.Customer;

public interface ManagerCustomer extends IGenericDesign<Customer,Integer>{

    void deleteCustomer();
    void searchCustomerByName();
}
