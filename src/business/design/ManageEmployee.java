package business.design;

import business.entity.Employee;

public interface ManageEmployee extends IGenericDesign<Employee,Integer>  {

    void deleteEmployee();
    void searchEmployeeByName();
}
