package app.beans;

import app.model.Point;

import javax.annotation.Resource;
import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;


public class PointsBean implements Serializable {
    private Deque<Point> data;
    private int size;

    @Resource(name = "datasources/oracle")
    private DataSource ds;

    {
        data = new ArrayDeque<>();
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:jboss/datasources/oracle");
        } catch (NamingException e) {
            e.printStackTrace();
//        } catch (SQLException ee) {
//            ee.printStackTrace();
        }
    }

    public void addPoint(Point point) {
        try {
            Point last = data.getFirst();
            if (!last.equals(point)) {  //add if not reload page
                data.addFirst(point);
            }
        } catch (NoSuchElementException e) {
            data.addFirst(point);
        }
//
//        if (ds == null) throw new SQLException("No data source");
//        Connection conn = ds.getConnection();
//        if (conn == null) throw new SQLException("No connection");
//
//        try {
//            conn.setAutoCommit(false);
//            boolean committed = false;
//            try {
//                PreparedStatement newpoint = conn.prepareStatement("INSERT INTO points VALUES (?,?,?,?,?)");
//                newpoint.setString(1, point.getUnique());
//                newpoint.setDouble(2, point.getX());
//                newpoint.setBigDecimal(3, point.getY());
//                newpoint.setDouble(4, point.getR());
//                newpoint.setString(5, point.getResult());
//                newpoint.executeUpdate();
//                conn.commit();
//                committed = true;
//            } finally {
//                if (!committed) conn.rollback();
//            }
//        } finally {
//            conn.close();
//        }
    }

    @Deprecated
    public void updatePoints() throws SQLException {
        data.clear();
        if (ds == null) throw new SQLException("No data source");
        Connection conn = ds.getConnection();
        if (conn == null) throw new SQLException("No connection");
        try {
            Statement usersPoints = conn.createStatement();
            ResultSet result = usersPoints.executeQuery("SELECT id, x, y, r, result  from points");
            while (result.next()) {
                data.add(new Point(
                        result.getDouble("x"),
                        result.getBigDecimal("y"),
                        result.getDouble("r"),
                        result.getString("result"),
                        result.getString("id")
                ));
            }
        } finally {
            conn.close();
        }
    }


    public Deque<Point> getData() {
        return data;
    }

    public void setData(Deque<Point> points) {
        this.data = points;
    }

    public String pointJSON() {
        return '[' + data.parallelStream()
                .map(point -> String.format("{\"x\": %s, \"y\": %s}",
                        point.getX(), point.getY(), point.getR(), point.getResult()))
                .collect(Collectors.joining(", ")) + ']';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
