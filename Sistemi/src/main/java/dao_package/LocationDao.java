package dao_package;
import models.Location;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LocationDao {
	
	Location create(Location location) throws SQLException;
	Optional<Location> findById(Long location_id) throws SQLException;
	List<Location> findAll() throws SQLException;
	Location update(Location location) throws SQLException;
	void delete(Long location_id) throws SQLException;

}