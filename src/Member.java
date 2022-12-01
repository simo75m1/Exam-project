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

    public void updateGroupAndPrice()
    {
        if (this.age < 18)
        {
            this.ageGroup = "Juniorsvømmer";
        }
        else
        {
            this.ageGroup = "Seniorsvømmer";
        }

        if(memberStatus == "Active")
        {
            if (ageGroup == "Juniorsvømmer")
            {
                this.price = 1000;
            } else if (ageGroup == "Seniorsvømmer")
            {
                this.price = 1600;
            }
            if (ageGroup == "Seniorsvømmer" && age >= 60)
            {
                this.price = (this.price * 0.75f);
            }
        }
        if (memberStatus == "Passive")
        {
            this.price = 500;
        }
    }
    //Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setMemberStatus(String memberStatus)
    {
        this.memberStatus = memberStatus;
    }

    public void setMemberType(String memberType)
    {
        this.memberType = memberType;
    }

    public void setAge(int age)
    {
        this.age = age;
        if (age < 18)
        {
            this.ageGroup = "Juniorsvømmer";
        }
        else
        {
            this.ageGroup = "Seniorsvømmer";
        }
    }

    public void setPaid(boolean paid)
    {
        this.paid = paid;
    }

    public void setPaymentYear(int paymentYear)
    {
        this.paymentYear = paymentYear;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    //Getters
    public String getName()
    {
        return name;
    }

    public String getMemberStatus()
    {
        return memberStatus;
    }

    public String getMemberType()
    {
        return memberType;
    }

    public int getAge()
    {
        return age;
    }

    public boolean getPaid()
    {
        return paid;
    }

    public int getPaymentYear()
    {
        return paymentYear;
    }

    public float getPrice()
    {
        return price;
    }

    public String getAgeGroup()
    {
        return ageGroup;
    }
}