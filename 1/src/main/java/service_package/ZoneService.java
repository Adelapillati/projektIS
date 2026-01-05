package service_package;

import dao_package.ZoneDao;
import pacakge_modelet.Zone;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ZoneService {

    private final ZoneDao zoneDao;

    public ZoneService(ZoneDao zoneDao) {
        this.zoneDao = zoneDao;
    }

    public Zone createZone(Long locationId, String zoneName) throws SQLException {
        if (locationId == null || locationId <= 0) throw new IllegalArgumentException("location_id duhet > 0");
        if (zoneName == null || zoneName.isBlank()) throw new IllegalArgumentException("zone_name nuk mund të jetë bosh");

        Zone z = new Zone(0L, locationId, zoneName);
        return zoneDao.create(z);
    }

    public Optional<Zone> getZoneById(Long zoneId) throws SQLException {
        if (zoneId == null || zoneId <= 0) throw new IllegalArgumentException("zone_id duhet > 0");
        return zoneDao.findById(zoneId);
    }

    public List<Zone> getAllZones() throws SQLException {
        return zoneDao.findAll();
    }

    public List<Zone> getZonesByLocationId(Long locationId) throws SQLException {
        if (locationId == null || locationId <= 0) throw new IllegalArgumentException("location_id duhet > 0");
        return zoneDao.findByLocationId(locationId);
    }

    public Zone updateZone(Long zoneId, Long locationId, String zoneName) throws SQLException {
        if (zoneId == null || zoneId <= 0) throw new IllegalArgumentException("zone_id duhet > 0");

        Zone z = zoneDao.findById(zoneId)
                .orElseThrow(() -> new IllegalArgumentException("Zone me id " + zoneId + " nuk u gjet"));

        if (locationId == null || locationId <= 0) throw new IllegalArgumentException("location_id duhet > 0");
        if (zoneName == null || zoneName.isBlank()) throw new IllegalArgumentException("zone_name nuk mund të jetë bosh");

        z.setLocation_id(locationId);
        z.setZone_name(zoneName);

        return zoneDao.update(z);
    }

    public void deleteZone(Long zoneId) throws SQLException {
        if (zoneId == null || zoneId <= 0) throw new IllegalArgumentException("zone_id duhet > 0");
        zoneDao.delete(zoneId);
    }
}