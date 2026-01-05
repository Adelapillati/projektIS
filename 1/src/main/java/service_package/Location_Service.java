package service_package;

import dao_package.LocationDao;
import pacakge_modelet.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Location_Service {

    private final LocationDao locationDao;

    public Location_Service(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public Location createLocation(String name, String address) throws SQLException {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("location_name nuk mund të jetë bosh");
        if (address == null || address.isBlank()) throw new IllegalArgumentException("location_address nuk mund të jetë bosh");

        Location l = new Location(0L, name, address);
        return locationDao.create(l);
    }

    public Optional<Location> getLocationById(Long locationId) throws SQLException {
        if (locationId == null || locationId <= 0) throw new IllegalArgumentException("location_id duhet > 0");
        return locationDao.findById(locationId);
    }

    public List<Location> getAllLocations() throws SQLException {
        return locationDao.findAll();
    }

    public Location updateLocation(Long locationId, String name, String address) throws SQLException {
        if (locationId == null || locationId <= 0) throw new IllegalArgumentException("location_id duhet > 0");

        Location l = locationDao.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location me id " + locationId + " nuk u gjet"));

        if (name == null || name.isBlank()) throw new IllegalArgumentException("location_name nuk mund të jetë bosh");
        if (address == null || address.isBlank()) throw new IllegalArgumentException("location_address nuk mund të jetë bosh");

        l.setLocation_name(name);
        l.setLocation_adress(address); // vëreni: modeli yt e ka setLocation_adress
        return locationDao.update(l);
    }

    public void deleteLocation(Long locationId) throws SQLException {
        if (locationId == null || locationId <= 0) throw new IllegalArgumentException("location_id duhet > 0");
        locationDao.delete(locationId);
    }
}