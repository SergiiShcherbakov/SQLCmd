package ua.com.juja.sergiishcherbakov.sqlcmd.model;

/**
 * Created by Sergii Shcherbakov  on 21.04.2017.
 */
public class Field {
    private final String name;

    final private boolean isPrimaryKey;
    final private boolean isNull;
    final private boolean isUnique;
    final private FieldType fieldType;
    final private int size;

    public Field(String name, FieldType fieldType, boolean isPrimaryKey, boolean isNull, boolean isUnique, int size) {
        this.name = name;
        this.isPrimaryKey = isPrimaryKey;
        this.isNull = isNull;
        this.isUnique = isUnique;
        this.fieldType = fieldType;
        this.size = size;
    }

    private String getName() {
        return name;
    }

    private String GetIsPrimaryKeyInText() {
        return isPrimaryKey ? "PRIMARY KEY" : "";
    }

    private String getIsNullInText() {
        return isNull ? "NULL" : "NOT NULL";
    }

    private String getIsUniqueInText() {
        return isUnique ? "UNIQUE" : "";
    }

    private String getSizeInText() {
        if (fieldType == FieldType.VARCHAR) {
            return String.format( "(%d)", size);
        } else {
            return "";
        }
    }

    public String getSqlField(){
        return getName() + " " +
                fieldType.toString()  + getSizeInText() + " " +
                GetIsPrimaryKeyInText() + " " +
                getIsNullInText() + " " +
                getIsUniqueInText() ;
    }
}
