package com.example.ilijaangeleski.phonebook.model;

import java.io.Serializable;

public class User implements Serializable {

    private String gender;
    private String email;
    private String dob;
    private String registered;
    private String phone;
    private String cell;
    private String nat;

    private Name name;
    private Location location;
    private Picture picture;
    private Login login;
    private ID id;


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    @Override
    public String toString() {
        return "User{" +
                "gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", registered='" + registered + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", nat='" + nat + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", picture=" + picture +
                ", login=" + login +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!gender.equals(user.gender)) return false;
        if (!email.equals(user.email)) return false;
        if (dob != null ? !dob.equals(user.dob) : user.dob != null) return false;
        if (registered != null ? !registered.equals(user.registered) : user.registered != null)
            return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (!name.equals(user.name)) return false;
        return id != null ? id.equals(user.id) : user.id == null;

    }

    @Override
    public int hashCode() {
        int result = gender.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (registered != null ? registered.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
