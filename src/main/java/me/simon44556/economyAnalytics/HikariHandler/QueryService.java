package me.simon44556.economyAnalytics.HikariHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class QueryService {
    Hikari handler;

    public QueryService() {
        handler = new Hikari();
    }

    public void execute(String sql) {

        try (Connection conn = handler.getConn()) {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.execute();

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}