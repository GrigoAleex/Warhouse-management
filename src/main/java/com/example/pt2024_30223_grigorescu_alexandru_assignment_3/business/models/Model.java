package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.database.DatabaseConnection;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Model{
    public boolean create() {
        String sql = prepareInsertStatement();

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {

            List<Field> annotatedFields = getFieldsWithAnnotation(this.getClass());
            int parameterIndex = 1;
            for (Field field : annotatedFields) {
                if (field.getName().contentEquals("id")) continue;
                Object value;
                try {
                    value = field.get(this);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
                pstmt.setObject(parameterIndex++, value);
            }


            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update() {
        String sql = prepareUpdateStatement();

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {

            List<Field> annotatedFields = getFieldsWithAnnotation(this.getClass());
            int parameterIndex = 1;
            Object id = -1;
            for (Field field : annotatedFields) {
                try {
                if (field.getName().contentEquals("id")){
                    id = field.get(this);
                    continue;
                }
                    Object value;
                    value = field.get(this);
                    pstmt.setObject(parameterIndex++, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            pstmt.setObject(parameterIndex, id);


            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete() {
        String sql = "DELETE FROM " + this.getClass().getSimpleName().toLowerCase() + "s WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.connect();
                PreparedStatement pstmt = connection.prepareStatement(sql)
        ) {

            List<Field> annotatedFields = getFieldsWithAnnotation(this.getClass());
            Object id = -1;
            for (Field field : annotatedFields) {
                try {
                    if (field.getName().contentEquals("id")){
                        id = field.get(this);
                        break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            pstmt.setObject(1, id);


            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Field> getFieldsWithAnnotation(Class<?> clazz) {
        List<Field> annotatedFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)) {
                annotatedFields.add(field);
            }
        }
        return annotatedFields;
    }

    private String prepareInsertStatement() {
        List<Field> annotatedFields = getFieldsWithAnnotation(this.getClass());
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append(this.getClass().getSimpleName()
                .replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase()).append("s (");
        for (Field field : annotatedFields) {
            field.setAccessible(true); // Make private fields accessible
            if (field.getName().contentEquals("id")) continue;
            Column column = field.getAnnotation(Column.class);
            sqlBuilder.append(column.name()).append(",");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the last comma
        sqlBuilder.append(") VALUES (");
        for (int i = 1; i < annotatedFields.size(); i++) {
            sqlBuilder.append("?,");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the last comma
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

    private String prepareUpdateStatement() {
        List<Field> annotatedFields = getFieldsWithAnnotation(this.getClass());
        StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
        sqlBuilder.append(this.getClass().getSimpleName().toLowerCase()).append("s  SET ");
        for (Field field : annotatedFields) {
            field.setAccessible(true); // Make private fields accessible
            if (field.getName().contentEquals("id")) continue;

            Column column = field.getAnnotation(Column.class);
            sqlBuilder.append(column.name()).append(" = ?,");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the last comma
        sqlBuilder.append(" WHERE id =  ?");

        return sqlBuilder.toString();
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Column {
        String name();
        String type();
    }
}
