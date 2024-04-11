package business.design;

import business.entity.Contract;

public interface ManagerContract extends IGenericDesign<Contract,Integer> {

    void deleteContract();
    void searchContractByName();

}
