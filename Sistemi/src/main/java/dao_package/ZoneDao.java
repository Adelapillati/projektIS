package dao_package;
import models.Zone;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ZoneDao {
	
	Zone create(Zone zone) throws SQLException;
	Optional<Zone> findById(Long zone_id)  throws SQLException;
	List<Zone> findAll() throws SQLException;
	List<Zone> findByLocationId(Long location_id) throws SQLException;
	Zone update (Zone zone) throws SQLException;
	void delete(Long zone_id) throws SQLException;

}