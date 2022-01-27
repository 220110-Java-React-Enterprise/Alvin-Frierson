package Utils;

/**
 * CRUD used for the Account and Associate Repos
 * */
public interface DataSourceCRUD<T> {
    //CRUD - create, read, update, delete
    public int create(T t);
    public T read(Integer id);
    public T update(T t);
    public void delete(Integer id);
}
