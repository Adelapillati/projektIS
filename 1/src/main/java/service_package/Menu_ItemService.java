package service_package;

import dao_package.Menu_ItemDao;
import pacakge_modelet.Menu_Item;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Menu_ItemService {

    private final Menu_ItemDao menuItemDao;

    public Menu_ItemService(Menu_ItemDao menuItemDao) {
        this.menuItemDao = menuItemDao;
    }

    public Menu_Item createMenuItem(Long menuId, String itemName, Long itemPrice) throws SQLException {
        if (menuId == null || menuId <= 0) throw new IllegalArgumentException("menu_id duhet > 0");
        if (itemName == null || itemName.isBlank()) throw new IllegalArgumentException("item_name nuk mund të jetë bosh");
        if (itemPrice == null || itemPrice < 0) throw new IllegalArgumentException("item_price s’mund të jetë negativ");

        Menu_Item item = new Menu_Item(0L, menuId, itemName, itemPrice);
        return menuItemDao.create(item);
    }

    public Optional<Menu_Item> getMenuItemById(Long itemId) throws SQLException {
        if (itemId == null || itemId <= 0) throw new IllegalArgumentException("item_id duhet > 0");
        return menuItemDao.findById(itemId);
    }

    public List<Menu_Item> getAllMenuItems() throws SQLException {
        return menuItemDao.findAll();
    }

    public Menu_Item updateMenuItem(Long itemId, Long menuId, String itemName, Long itemPrice) throws SQLException {
        if (itemId == null || itemId <= 0) throw new IllegalArgumentException("item_id duhet > 0");

        Menu_Item item = menuItemDao.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Menu_Item me id " + itemId + " nuk u gjet"));

        if (menuId == null || menuId <= 0) throw new IllegalArgumentException("menu_id duhet > 0");
        if (itemName == null || itemName.isBlank()) throw new IllegalArgumentException("item_name nuk mund të jetë bosh");
        if (itemPrice == null || itemPrice < 0) throw new IllegalArgumentException("item_price s’mund të jetë negativ");

        item.setMenu_id(menuId);
        item.setItem_name(itemName);
        item.setItem_price(itemPrice);

        return menuItemDao.update(item);
    }

    public void deleteMenuItem(Long itemId) throws SQLException {
        if (itemId == null || itemId <= 0) throw new IllegalArgumentException("item_id duhet > 0");
        menuItemDao.delete(itemId);
    }
}