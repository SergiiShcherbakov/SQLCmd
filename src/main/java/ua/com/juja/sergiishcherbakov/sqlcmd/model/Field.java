package ua.com.juja.sergiishcherbakov.sqlcmd.model;

/**
 * Created by Sergii Shcherbakov  on 21.04.2017.
 */
public class Field {
    private String name;

    final private boolean isPrimaryKey;
    final private boolean isNull;
    final private boolean isUnique;
    final private FieldType fieldType;
    final private int size;

    public Field(String name, FieldType fieldType, boolean isPrimaryKey, boolean inNull, boolean isUnique, int size) {
        this.name = name;
        this.isPrimaryKey = isPrimaryKey;
        this.isNull = inNull;
        this.isUnique = isUnique;
        this.fieldType = fieldType;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String isPrimaryKey() {
        if( isPrimaryKey){
            return "PRIMARY KEY";
        } else {
            return "";
        }
    }

    public String isInNull() {
        if (isNull) {
            return "NULL";
        } else {
            return "NOT NULL";
        }
    }

    public String isUnique() {
        if (isUnique) {
            return "UNIQUE";
        } else {
            return "";
        }
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public String getSize() {
        if (fieldType == FieldType.VARCHAR) {
            return "[" + size + "]";
        } else {
            return "";
        }
    }

    public String getSqlField(){
        return getName() + " " +
                fieldType.toString()  + getSize() + " " +
                isPrimaryKey() + " " +
                isInNull() + " " +
                isUnique() ;
    }
}
