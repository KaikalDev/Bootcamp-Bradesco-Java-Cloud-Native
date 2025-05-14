package kaique.luan.dev;

import kaique.luan.dev.persistence.migration.MigrationStrategy;

import java.sql.SQLException;

import static kaique.luan.dev.persistence.config.connectionConfig.getConnection;

public class main {

    public static void main(String[] args) throws SQLException {
        try(var connection = getConnection()) {
            new MigrationStrategy(connection).executeMigration();
        }
    }

}
