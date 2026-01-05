package service_package;

import dao_package.RoleDao;
import pacakge_modelet.Role;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoleService {

    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Role createRole(String name) throws SQLException {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name nuk mund të jetë bosh");

        Role r = new Role(0L, name);
        return roleDao.create(r);
    }

    public Optional<Role> getRoleById(Long roleId) throws SQLException {
        if (roleId == null || roleId <= 0) throw new IllegalArgumentException("role_id duhet > 0");
        return roleDao.findById(roleId);
    }

    public List<Role> getAllRoles() throws SQLException {
        return roleDao.findAll();
    }

    public Role updateRole(Long roleId, String name) throws SQLException {
        if (roleId == null || roleId <= 0) throw new IllegalArgumentException("role_id duhet > 0");

        Role r = roleDao.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role me id " + roleId + " nuk u gjet"));

        if (name == null || name.isBlank()) throw new IllegalArgumentException("name nuk mund të jetë bosh");

        r.setName(name);
        return roleDao.update(r);
    }

    public void deleteRole(Long roleId) throws SQLException {
        if (roleId == null || roleId <= 0) throw new IllegalArgumentException("role_id duhet > 0");
        roleDao.delete(roleId);
    }
}