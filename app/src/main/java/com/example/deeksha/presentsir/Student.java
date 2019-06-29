package com.example.deeksha.presentsir;

/**
 * Created by Deeksha on 7/6/2016.
 */
public class Student
{
    private String name;
    private String sid;
    int image;
    boolean box;
    public Student(){

    }

    public Student(String name, String sid, int image, boolean box){
        this.name = name;
        this.sid = sid;
        this.image=image;
        this.box=box;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setSid(String sid){
        this.sid = sid;
    }

    public String getSid(){
        return this.sid;
    }
    public void setImage(int image)
    {
        this.image=image;
    }
    public int getImage()
    {
        return this.image;
    }
    public void setBox(boolean box)
    {
        this.box=box;
    }
    public boolean getBox()
    {
        return this.box;
    }
}


