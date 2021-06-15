package modele.entityManager;

import java.sql.ResultSet;
import java.util.List;

public interface EntityManager<T> {
	
	public T processLine(ResultSet rs);
	public List<T> getAll();
	public T getOneById(int id);
	public void deleteOneById(int id);
	public void updateOne(T entity);
	public void addOne(T entity);
	
}
