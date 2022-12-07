import java.util.ArrayList;

public class Accounting
{
    public ArrayList<Member> paymentList = new ArrayList<>();
    public void accountingMenu()
    {
        System.out.println("What would you like to do?\nUpdate payment for a member[1] or See members in arrears[2]");
        int accInput = Club.scan.nextInt();
        switch (accInput)
        {
            case 1:
                System.out.println("Enter full name of the member to search for");
                Club.scan.nextLine();
                String nameInput = Club.scan.nextLine();
                nameInput = nameInput.trim();
                searchMembers(nameInput);



                break;
            case 2:

                break;
        }

    }
    public void updateOnePayment(){}

    public void updatePaymentList(){}

    public void printPaymentList(){}

    public void searchMembers(String nameSearch)
    {
        System.out.println("List of members with the name '"+nameSearch+"'");
        for(int i = 0; i<Club.memberList.size(); i++)
        {
            if(nameSearch.equalsIgnoreCase(Club.memberList.get(i).getName()))
            {
                System.out.println((i+1)+Club.memberList.get(i).toString());
            }
        }
    }
}
