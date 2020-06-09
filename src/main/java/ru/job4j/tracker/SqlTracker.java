package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    /**
     * Подключаемся к базе данных.
     */
    @Override
    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Добавляем заявку в базу данных.
     *
     * @param item заявка.
     * @return заявка.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement st = cn.prepareStatement("insert into items (name) values (?)")) {
            st.setString(1, item.getName());
            st.executeUpdate();
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
    public boolean replace(String id, Item item) {
        int rsl = 0;
        try (PreparedStatement st = cn.prepareStatement("update items set name=? where id = ?")) {
            st.setString(1, item.getName());
            st.setInt(2, Integer.parseInt(id));
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
    public boolean delete(String id) {
        int rsl = 0;
        try (PreparedStatement st = cn.prepareStatement("delete from items where id = ?")) {
            st.setInt(1, Integer.parseInt(id));
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
                item.setId(rsl.getString("id"));
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
                item.setId(rsl.getString("id"));
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
    public Item findById(String id) {
        Item rsl = null;
        try (PreparedStatement st = cn.prepareStatement("select name from items where id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet it = st.executeQuery();
            while (it.next()) {
                rsl = new Item(it.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}
