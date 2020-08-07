package ru.job4j.tracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlTracker implements Store {
    private Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    /**
     * Добавляем заявку в базу данных.
     *
     * @param item заявка.
     * @return заявка.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement st = cn.prepareStatement("insert into items (name) values (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.executeUpdate();
            try (ResultSet id = st.getGeneratedKeys()) {
                if (id.next()) {
                    item.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Редактируем заявку.
     *
     * @param id   id заявки
     * @param item новая заявка
     * @return успех или провал.
     */
    @Override
    public boolean replace(Integer id, Item item) {
        int rsl = 0;
        try (PreparedStatement st = cn.prepareStatement("update items set name=? where id = ?")) {
            st.setString(1, item.getName());
            st.setInt(2, id);
            rsl = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl != 0;
    }

    /**
     * Удаляем заявку.
     *
     * @param id id заявки.
     * @return успех или провал.
     */
    @Override
    public boolean delete(Integer id) {
        int rsl = 0;
        try (PreparedStatement st = cn.prepareStatement("delete from items where id = ?")) {
            st.setInt(1, id);
            rsl = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl != 0;
    }

    /**
     * Получаем список всех заявок.
     *
     * @return список заявок.
     */
    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            ResultSet rsl = st.executeQuery("select * from items");
            while (rsl.next()) {
                Item item = new Item(rsl.getString("name"));
                item.setId(rsl.getInt("id"));
                result.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Находим заявки с одинаковыми именами.
     *
     * @param key имя.
     * @return список одинаковых заявок.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (PreparedStatement st = cn.prepareStatement("select * from items where name = ?")) {
            st.setString(1, key);
            ResultSet rsl = st.executeQuery();
            while (rsl.next()) {
                Item item = new Item(rsl.getString("name"));
                item.setId(rsl.getInt("id"));
                result.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Находим заявку по Id.
     *
     * @param id id.
     * @return заявка.
     */
    @Override
    public Item findById(Integer id) {
        Item rsl = null;
        try (PreparedStatement st = cn.prepareStatement("select name from items where id = ?")) {
            st.setInt(1, id);
            ResultSet it = st.executeQuery();
            while (it.next()) {
                rsl = new Item(it.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public void clearTableItems() {
        try (Statement st = cn.createStatement()) {
             st.executeQuery("DELETE FROM items");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
