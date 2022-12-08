import java.time.Year;

//Abstract superclass.
public abstract class Member
{
    private int age;
    private String name;
    private String memberStatus;
    private String memberType;
    private String ageGroup;
    private boolean paid;
    private int paymentYear = 0;
    private float price; // defined as a float if the prices changes in the future.

    public Member() {}

    public Member(String name, int age, String memberStatus, String memberType, boolean paid)
    {
        this.name = name;
        this.age = age;
        this.memberStatus = memberStatus;
        this.memberType = memberType;
        this.paid = paid;
        if(paid) //same as paid==true
        {
            paymentYear = Year.now().getValue();
        }
        updateGroupAndPrice();
    } //End of constructor
    public String toString()
    {
        return "\nName: " + name + "\nAge: " + age + "\nMember status: " + memberStatus + "\nMember type: " + memberType +"\nPaid: " + paid + "\nLast payment: " + paymentYear + "\nPrice: " + price + "\n";
    }

    public String printCompInfo()
    {
        return "No disciplines or competitions";
    }

    //Method to update member info given age and member status
    public void updateGroupAndPrice()
    {
        if (this.age < 18)
        {
            this.ageGroup = "junior";
        }
        else
        {
            this.ageGroup = "senior";
        }

        if(memberStatus.equals("active"))
        {
            if (ageGroup.equals("junior"))
            {
                this.price = 1000;
            } else
            {
                this.price = 1600;
            }
            if (ageGroup.equals("senior") && age >= 60)
            {
                this.price = (this.price * 0.75f);
            }
        }
        if (memberStatus.equals("passive"))
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
    }

    public void setPaid()
    {
        this.paid = !this.paid;
    }

    public void setPaymentYear()
    {
        this.paymentYear = Year.now().getValue();
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