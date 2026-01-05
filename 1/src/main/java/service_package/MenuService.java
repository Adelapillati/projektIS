package service_package;

import dao_package.MenuDao;
import pacakge_modelet.Menu;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MenuService {

    private final MenuDao menuDao;

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public Menu createMenu(String menuType) throws SQLException {
        if (menuType == null || menuType.isBlank()) throw new IllegalArgumentException("menu_type nuk mund të jetë bosh");

        Menu m = new Menu(0L, menuType);
        return menuDao.create(m);
    }

    public Optional<Menu> getMenuById(Long menuId) throws SQLException {
        if (menuId == null || menuId <= 0) throw new IllegalArgumentException("menu_id duhet > 0");
        return menuDao.findById(menuId);
    }

    public List<Menu> getAllMenus() throws SQLException {
        return menuDao.findAll();
    }

    public Menu updateMenu(Long menuId, String menuType) throws SQLException {
        if (menuId == null || menuId <= 0) throw new IllegalArgumentException("menu_id duhet > 0");

        Menu m = menuDao.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu me id " + menuId + " nuk u gjet"));

        if (menuType == null || menuType.isBlank()) throw new IllegalArgumentException("menu_type nuk mund të jetë bosh");

        m.setMenu_type(menuType);
        return menuDao.update(m);
    }

    public void deleteMenu(Long menuId) throws SQLException {
        if (menuId == null || menuId <= 0) throw new IllegalArgumentException("menu_id duhet > 0");
        menuDao.delete(menuId);
    }
}