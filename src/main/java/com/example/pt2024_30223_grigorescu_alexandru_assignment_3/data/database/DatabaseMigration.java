package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.database;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseMigration {
    static private final Set<String> migrationsRun = new HashSet<>();

    private static boolean isSqlFile(File file) {
        return file.getName().contains(".") && file.getName().endsWith("sql");
    }

    private static Set<String> getMigrations() {
        File[] migrationFiles = new File("src/main/resources/sql/migrations").listFiles();

        if (migrationFiles == null) return Collections.emptySet();

        return Stream.of(migrationFiles)
                .filter(file -> !file.isDirectory() && isSqlFile(file))
                .map(File::getName)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static void loadRunMigrations() {
        try (BufferedReader reader = new BufferedReader(new FileReader("run_migrations"))) {
            String line;
            while ((line = reader.readLine()) != null) migrationsRun.add(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void markMigrationAsRun(String migration) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("run_migrations",true));) {
            writer.append(migration).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveMigration(String migration) {
        System.out.println("Completed " + migration);
        markMigrationAsRun(migration);
        migrationsRun.add(migration);
    }

    public static void main(String[] args) {
        loadRunMigrations();

        Set<String> migrations = getMigrations();
        for (String migration: migrations) {
            if (migrationsRun.contains(migration)) continue;

            runMigration(migration);
            saveMigration(migration);
        }
    }

    private static void runMigration(String migration) throws RuntimeException {
        try (Connection connection = DatabaseConnection.connect()) {
            Statement statement = connection.createStatement();

            StringBuilder sql = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/sql/migrations/" + migration))) {
                String line;
                while ((line = reader.readLine()) != null) sql.append(line).append('\n');


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            statement.executeUpdate(sql.toString());

        } catch (IOException | SQLException e) {
            throw new RuntimeException("For migration: " + migration + "\n" + e);
        }

    }
}
