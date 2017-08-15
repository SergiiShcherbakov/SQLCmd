package ua.com.juja.sergiishcherbakov.sqlcmd.model.database;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.juja.sergiishcherbakov.sqlcmd.DBSetupForTests;
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

    private JDBCPostgresSQLDatabaseManager dbManager;


    @BeforeClass
    public static void  setDBAndStructure() throws SQLException, ClassNotFoundException {
        DBSetupForTests.createTestDatabase();
    }
    @AfterClass
    public static void  cleanDBAndStructure() throws SQLException, ClassNotFoundException {
        DBSetupForTests.deleteTestDatabase();
    }
    @Before
    public void set() throws SQLException, ClassNotFoundException {
        dbManager = new JDBCPostgresSQLDatabaseManager();
        dbManager.setConnection(DBSetupForTests.TEST_DB, DBSetupForTests.getDBLogin(), DBSetupForTests.getDBPassword());
    }

    @Test
    public void Create_Delete_GetTablesName_Test() throws SQLException, ClassNotFoundException {
        // given
        Field field = new Field("id", FieldType.INTEGER, true, true, true, 0  );
        Field field1 = new Field("br", FieldType.VARCHAR, false, true, true, 50  );
        String tableName = "tab"  + Math.abs( new Random().nextInt(100));
        // when
        try {
            dbManager.deleteTable(tableName);
        } catch ( RuntimeException e){
            // do nothing
        }
        dbManager.createTable(tableName , new Field[] {field, field1});
        List<String> tablesNames = dbManager.getTablesNames();
        // then
        assertEquals( tablesNames.contains(tableName), true );
        dbManager.deleteTable(tableName);
    }

    @Test
    public void deleteNotExistTable() throws SQLException, ClassNotFoundException {
        // given
        String tableName = "tab"  + Math.abs( new Random().nextInt(10));
        List<String> tablesNames = dbManager.getTablesNames();
        while (tablesNames.contains(tableName)){
            tableName += Math.abs( new Random().nextInt(10));
        }
        // when
        try {
            dbManager.deleteTable(tableName);
            fail("Expected exception");
        } catch ( RuntimeException e){
            // do nothing
        }
        tablesNames = dbManager.getTablesNames();
        // then
        assertEquals( tablesNames.contains(tableName), false );
    }
}

