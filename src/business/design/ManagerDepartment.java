package business.design;

import business.entity.Department;

public interface ManagerDepartment extends IGenericDesign<Department,Integer> {

    void deleteDepartment();
}
