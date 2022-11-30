import java.time.Year;

public abstract class Member
{
    private int age;
    private String name;
    private String memberStatus;
    private String ageGroup;
    private boolean paid;
    private int paymentYear = 0;
    private float price; // defined as a float if the prices changes in the future.

    public Member(String name, int age, String memberStatus, boolean paid)
    {
        this.name = name;
        this.age = age;
        this.memberStatus = memberStatus;
        this.paid = paid;
        if(paid) //same as paid==true
        {
            paymentYear = Year.now().getValue();
        }
        updateGroupAndPrice();
    } //End of constructor
    public String toString()
    {
        return "\nName: " + name + "\nAge: " + age + "\nMember status: " + memberStatus + "\nMember type: " + memberType +"\nPaid: " + paid + "\nLast payment: " + paymentYear + "\nPrice: " + price;
    }
}
