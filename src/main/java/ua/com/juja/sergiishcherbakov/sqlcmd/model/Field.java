package ua.com.juja.sergiishcherbakov.sqlcmd.model;

/**
 * Created by StrannikFujitsu on 21.04.2017.
 */
public class Field {
    String name;

    boolean isPrimaryKey;
    boolean isNull;
    boolean isUnique;
    FiledType filedType;
    int size;

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
        if( isPrimaryKey) return "PRIMARY KEY";
        else return "";
    }

    public String isInNull() {
         if(isNull)return "NULL";
         else return "NOT NULL";
    }

    public String isUnique() {
        if(isUnique) return "UNIQUE";
        else return "";
    }

    public FiledType getFiledType() {
        return filedType;
    }

    public String getSize() {
        if (filedType == FiledType.VARCHAR) {
        return  "["+ size+"]";
        }
        else return "";
    }

    public String getSqlFild(){
       return getName() + " " +
               filedType.toString()  + getSize()+" " +
               isPrimaryKey() + " " +
               isInNull() + " " +
               isUnique() ;
    }

    /*"(id INTEGER PRIMARY KEY NOT NULL," +
                    " username varchar(225) NOT NULL UNIQUE, " +
                    " PASSWORD varchar(225) NOT NULL)";*/
}
