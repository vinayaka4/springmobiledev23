package edu.northeastern.numadsp_23vinhk;

public class Urlmodel {
    String name,url;

    public Urlmodel(String name, String url){
        if (name==null || url==null || name=="" || url==""){
            throw new IllegalArgumentException();
        }
        this.name=name;
        this.url=url;
    }


}
