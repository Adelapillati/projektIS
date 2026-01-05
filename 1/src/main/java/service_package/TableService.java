package service_package;

import dao_package.TableDao;
import pacakge_modelet.Table;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TableService {

    private final TableDao tableDao;

    public TableService(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    public Table createTable(Long zoneId, Boolean status, Integer seats) throws SQLException {
        if (zoneId == null || zoneId <= 0) throw new IllegalArgumentException("zone_id duhet > 0");
        if (status == null) throw new IllegalArgumentException("status nuk mund të jetë null");
        if (seats == null || seats <= 0) throw new IllegalArgumentException("seats duhet > 0");

        Table t = new Table(0L, zoneId, status, seats);
        return tableDao.create(t);
    }

    public Optional<Table> getTableById(Long tableId) throws SQLException {
        if (tableId == null || tableId <= 0) throw new IllegalArgumentException("table_id duhet > 0");
        return tableDao.findById(tableId);
    }

    public List<Table> getAllTables() throws SQLException {
        return tableDao.findAll();
    }

    public List<Table> getTablesByZoneId(Long zoneId) throws SQLException {
        if (zoneId == null || zoneId <= 0) throw new IllegalArgumentException("zone_id duhet > 0");
        return tableDao.findByZoneId(zoneId);
    }

    public Table updateTable(Long tableId, Long zoneId, Boolean status, Integer seats) throws SQLException {
        if (tableId == null || tableId <= 0) throw new IllegalArgumentException("table_id duhet > 0");

        Table t = tableDao.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Table me id " + tableId + " nuk u gjet"));

        if (zoneId == null || zoneId <= 0) throw new IllegalArgumentException("zone_id duhet > 0");
        if (status == null) throw new IllegalArgumentException("status nuk mund të jetë null");
        if (seats == null || seats <= 0) throw new IllegalArgumentException("seats duhet > 0");

        t.setZone_id(zoneId);
        t.setStatus(status);
        t.setSeats(seats);

        return tableDao.update(t);
    }

    public void deleteTable(Long tableId) throws SQLException {
        if (tableId == null || tableId <= 0) throw new IllegalArgumentException("table_id duhet > 0");
        tableDao.delete(tableId);
    }
}