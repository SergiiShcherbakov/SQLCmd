package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.Field;
import ua.com.juja.sergiishcherbakov.sqlcmd.model.FieldType;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Sergii Shcherbakov on 23.04.2017.
 */
public class DataBaseManagerWithRealDBTest {
//SQLCmd|postgres|z

    private DatabaseManager dbm;

    @Before
    public void set() throws SQLException, ClassNotFoundException {
        dbm = new JDBCPostgresSQLDatabaseManager();
        dbm.setConnection("SQLCmd", "postgres", "z");
    }

    @Test
    public void Create_Delete_GetTablesName_Test() throws SQLException, ClassNotFoundException {
        // given
        Field field = new Field("id", FieldType.INTEGER, true, true, true, 0  );
        Field field1 = new Field("br", FieldType.VARCHAR, false, true, true, 50  );
        String tableName = "tab"  + Math.abs( new Random().nextInt(100));
        // when
        try {
            dbm.deleteTable(tableName);
        } catch ( SQLException e){
            // do nothing
        }
        dbm.createNewTable(tableName , new Field[] {field, field1});
        List<String> tablesNames = dbm.getTablesNames();
        // then
        assertEquals( tablesNames.contains(tableName), true );
        dbm.deleteTable(tableName);
    }

    @Test
    public void deleteNotExistTable() throws SQLException, ClassNotFoundException {

        // given
        String tableName = "tab"  + Math.abs( new Random().nextInt(10));
        List<String> tablesNames = dbm.getTablesNames();
        while (tablesNames.contains(tableName)){
            tableName += Math.abs( new Random().nextInt(10));
        }
        // when
        try {
            dbm.deleteTable(tableName);
            fail("Expected exception");
        } catch ( SQLException e){
            // do nothing
        }
        tablesNames = dbm.getTablesNames();

        // then
        assertEquals( tablesNames.contains(tableName), false );
    }
}
    // given
    // when
    // then
