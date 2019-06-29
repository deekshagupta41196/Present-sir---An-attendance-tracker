package com.example.deeksha.presentsir;

/**
 * Created by Deeksha on 7/17/2016.
 */
public class Studentpojo
{
    private String sname;
    private String sid;
    private String present;
    private String total;

    public Studentpojo()
    {
    }

    public Studentpojo(String sname,String sid,String present,String total)
    {
        super();
        this.sname=sname;
        this.sid=sid;
        this.present=present;
        this.total=total;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }


    public String getSname(){
        return this.sname;
    }

    public void setSid(String sid)
    {
        this.sid=sid;
    }
    public String getSid()
    {
        return this.sid;
    }

    public void setPresent(String present)
    {
        this.present=present;
    }
    public String getPresent()
    {
        return this.present;
    }

    public void setTotal(String total)
    {
        this.total=total;
    }
    public String getTotal()
    {
        return this.total;
    }


}
