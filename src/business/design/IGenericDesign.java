package business.design;

public interface IGenericDesign <T,E> {
    void addNew() ;
    void displayAllList();
    void update();
    T findById(E id);

}
