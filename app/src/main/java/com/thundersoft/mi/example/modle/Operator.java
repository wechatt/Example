package com.thundersoft.mi.example.modle;

/**
 * @author TuYong
 * @create 19-11-6
 * @Describe
 */
public class Operator {
    private String name = "" ;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private int index ;
    private int operatorType;

    public Operator(String name ,int operatorType,int index){
        this.name = name;
        this.operatorType = operatorType;
        this.index = index;
    }

    public int getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(int operatorType) {
        this.operatorType = operatorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
