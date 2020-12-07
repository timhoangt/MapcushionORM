package com.fdflib.example.model;

import com.fdflib.model.state.CommonState;

public class User extends CommonState {
    public String firstName = "";
    public String lastName = "";
    public String email = "";
    public String password = "";
	public String idType1 = "";
	public String idType2 = "";
	public String addr1 = "";
	public String addr2 = "";
	public String city = "";
	public String state = "";
	public Integer zip;
	public String country = "";
	public Integer id1;
	public Integer id2;
	public String date1Init = "";
	public String date1Exp = "";
	public String date2Init = "";
	public String date2Exp = "";
	public String dateBirth = "";
	public String gender = "";
	public String eyeColor= "";
	public float height;
	public float weight;

    public User() {
        super();
    }
}
