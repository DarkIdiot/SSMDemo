package com.demo.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.demo.validation.ValidationGroup1;
import com.demo.validation.ValidationGroup2;

public class User {
	
    private Integer id;
	@NotNull(message="{user.name.notNull}",groups={ValidationGroup1.class})
	@Size(message="{user.password.size}",max=20,groups={ValidationGroup2.class})
    private String username;
	@Size(min=3,max=5,message="{user.password.size}")
    private String password;

    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", birthday=" + birthday + "]";
	}
    
}