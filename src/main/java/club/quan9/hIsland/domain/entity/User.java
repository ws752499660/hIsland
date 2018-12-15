package club.quan9.hIsland.domain.entity;

public class User
{
    private String id;
    private String name;
    private char sex;   //m——男；f——女
    private String hobby;
    private long phone;
    private String group;
    private String states;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public char getSex()
    {
        return sex;
    }

    public void setSex(char sex)
    {
        this.sex = sex;
    }

    public String getHobby()
    {
        return hobby;
    }

    public void setHobby(String hobby)
    {
        this.hobby = hobby;
    }

    public long getPhone()
    {
        return phone;
    }

    public void setPhone(long phone)
    {
        this.phone = phone;
    }

    public String getGroup()
    {
        return group;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    public String getStates()
    {
        return states;
    }

    public void setStates(String states)
    {
        this.states = states;
    }
}
