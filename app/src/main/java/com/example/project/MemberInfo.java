package com.example.project;

import android.widget.EditText;

public class MemberInfo {

    private String name;
    private String phone;
    private String birth;
    private String hakbun;

    public MemberInfo(String name, String phone, String birth, String hakbun)
    {
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.hakbun = hakbun;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name ;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone=phone ;
    }
    public String getBirth(){
        return this.birth;
    }
    public void setBirth(String birth){
        this.birth=birth ;
    }
    public String getHakbun(){
        return this.hakbun;
    }
    public void setHakbun(String hakbun){
        this.hakbun=hakbun ;
    }
}
