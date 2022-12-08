//Subclass of member. Used to create casual member objects.
public class Casual extends Member
{
    public Casual(String name, int age, String memberStatus, String memberType, boolean paid)
    {
        super(name, age, memberStatus, memberType, paid);
    }
}