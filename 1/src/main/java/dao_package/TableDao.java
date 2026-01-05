package dao_package;
import pacakge_modelet.Table;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TableDao {
	
	Table create(Table table) throws SQLException;
	Optional<Table> findById(Long table_id) throws SQLException;
	List<Table> findAll() throws SQLException;
	List<Table> findByZoneId(Long zone_id) throws SQLException;
	Table update(Table table) throws SQLException;
	void delete(Long table_id) throws SQLException;

}

