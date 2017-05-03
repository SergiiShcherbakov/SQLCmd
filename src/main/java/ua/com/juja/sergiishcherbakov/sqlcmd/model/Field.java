package ua.com.juja.sergiishcherbakov.sqlcmd.model;

/**
 * Created by Sergii Shcherbakov  on 21.04.2017.
 */
public class Field {
    private String name;

    private boolean isPrimaryKey;
    private boolean isNull;
    private boolean isUnique;
    private FiledType filedType;
    private int size;

    public Field(String name, FiledType filedType, boolean isPrimaryKey, boolean inNull, boolean isUnique,  int size) {
        this.name = name;
        this.isPrimaryKey = isPrimaryKey;
        this.isNull = inNull;
        this.isUnique = isUnique;
        this.filedType = filedType;
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

    public FiledType getFieldType() {
        return filedType;
    }

    public String getSize() {
        if (filedType == FiledType.VARCHAR) {
            return "[" + size + "]";
        } else {
            return "";
        }
    }

    public String getSqlField(){
        return getName() + " " +
                filedType.toString()  + getSize() + " " +
                isPrimaryKey() + " " +
                isInNull() + " " +
                isUnique() ;
    }
}
