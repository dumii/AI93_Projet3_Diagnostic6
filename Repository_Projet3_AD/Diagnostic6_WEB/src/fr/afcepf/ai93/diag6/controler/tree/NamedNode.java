package fr.afcepf.ai93.diag6.controler.tree;

import java.io.Serializable;

public class NamedNode implements Serializable {
    protected String type;
    protected String name;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    @Override
    public String toString() {
        return this.name;
    }
}