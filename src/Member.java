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
        if (age < 18)
        {
            this.ageGroup = "Juniorsvømmer";
        }
        else if (age >= 18 && age < 60)
        {
            this.ageGroup = "Seniorsvømmer";
        }

        if(memberStatus == "Active")
        {
            if (ageGroup == "Juniorsvømmer")
            {
                this.price = 1000;
            }
            else if (ageGroup == "Seniorsvømmer")
            {
                this.price = 1600;
            }
            if (ageGroup == "Seniorsvømmer" && age >= 60)
            {
                this.price = (this.price * 0.75f);
            }
        }
    } //End of constructor

    public boolean getPaid()
    {
        return paid;
    }

    public String getMemberStatus()
    {
        return memberStatus;
    }

    public String printMemberInfo()
    {
        return "\nName: " + name + "\nAge: " + age + "\nMember status: " + memberStatus + "\nPaid: " + paid + "\nLast payment: " + paymentYear + "\nPrice: " + price;
    }
}
