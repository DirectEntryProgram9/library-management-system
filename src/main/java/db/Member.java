package db;

import java.io.Serializable;

public class Member implements Serializable {
    private String nic;
    private String name;
    private String contact;

    public Member() {
    }

    public Member(String nic, String name, String contact) {
        this.nic = nic;
        this.name = name;
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
