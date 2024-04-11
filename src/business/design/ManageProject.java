package business.design;

import business.entity.Project;

public interface ManageProject extends IGenericDesign<Project,Integer> {

    void updateProjectStatus();
}
