package sqlcmd.controller;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.Database.DatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.Database.JDBCPostgresSQLDatabaseManager;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.Field;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.FiledType;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by StrannikFujitsu on 23.04.2017.
 */
public class DataBaceManagerTest {
//SQLCmd|postgres|z
    DatabaseManager dbm;

    @Before
    public void set() throws SQLException, ClassNotFoundException {
        dbm = new JDBCPostgresSQLDatabaseManager();
        dbm.setConnection("SQLCmd", "postgres", "z");
    }

    @Test
    public void Create_Delete_GetTablesName_Test() throws SQLException, ClassNotFoundException {
        // given
        Field field = new Field("id", FiledType.INTEGER, true, true, true, 0  );
        Field field1 = new Field("br", FiledType.VARCHAR, false, true, true, 50  );
        String tableName = "tab"  + Math.abs( new Random().nextInt(100));

        // when
        dbm.deleteTable(tableName );
        dbm.createNewTable(tableName , new Field[] {field, field1});
        List<String> tablesNames = dbm.getTablesNames();

        // then
        assertEquals( tablesNames.contains(tableName), true );
    }

    @Test
    public void deleteNotExistTable() throws SQLException, ClassNotFoundException {

        // given
        String tableName = "tab"  + Math.abs( new Random().nextInt(100));

        // when
        dbm.deleteTable(tableName);
        List<String> tablesNames = dbm.getTablesNames();

        // then
        assertEquals( tablesNames.contains(tableName), false );
    }




    // given

    // when

    // then

}
