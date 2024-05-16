package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Model;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.database.DatabaseConnection;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class ModelDAO<T extends Model> {
    protected String tableName () {
        return null;
    }

    protected Class<T> model () {
        return null;
    }

    public ArrayList<T> all() {
        ArrayList<T> results = new ArrayList<>();

        try (Connection connection = DatabaseConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName())
        ) {
            List<Field> annotatedFields = T.getFieldsWithAnnotation(model());
            while (resultSet.next()) results.add(createInstance(annotatedFields, resultSet));

        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

    public T find(int id) {
        try (
                Connection connection = DatabaseConnection.connect();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName() + " WHERE ID = " + id)
        ) {
            List<Field> annotatedFields = T.getFieldsWithAnnotation(model());

            if(!resultSet.next()) return null;

            return createInstance(annotatedFields, resultSet);

        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                 IllegalAccessException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private T createInstance(List<Field> annotatedFields, ResultSet resultSet) throws NoSuchMethodException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T reuslt = model().getDeclaredConstructor().newInstance();

        for (Field field : annotatedFields) {
            field.setAccessible(true);
            Model.Column columnAnnotation = field.getAnnotation(Model.Column.class);
            String columnName = columnAnnotation.name();
            String columnType = columnAnnotation.type();

            switch (columnType) {
                case "String" -> {
                    String value = resultSet.getString(columnName);
                    field.set(reuslt, value);
                }
                case "Int" -> {
                    int value = resultSet.getInt(columnName);
                    field.set(reuslt, value);
                }
                case "Long" -> {
                    long value = resultSet.getLong(columnName);
                    field.set(reuslt, value);
                }
            }
        }

        return reuslt;
    }
}
