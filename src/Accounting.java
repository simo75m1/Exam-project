import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Accounting
{
    public ArrayList<Member> paymentList = new ArrayList<>();
    public void accountingMenu() throws FileNotFoundException
    {
        System.out.println("What would you like to do?\nUpdate payment for a member[1], See members in arrears[2], Print all members[3], Exit[0]");
        int accInput = Club.scan.nextInt();
        switch (accInput)
        {
            case 1:
                System.out.println("Enter full name of the member to search for");
                Club.scan.nextLine();
                String nameInput = Club.scan.nextLine();
                nameInput = nameInput.trim();
                searchMembers(nameInput);
                System.out.println("Please choose the member (number on list) whose payment you want to update");
                int idInput = Club.scan.nextInt() - 1;
                updateOnePayment(idInput);
                Club.saveMembers();
                updatePaymentList();
                break;
            case 2:
                printPaymentList();
                break;
            case 3:
                printMemberList();
                break;
        }

    }
    public void updateOnePayment(int idInput)
    {
        Club.memberList.get(idInput).setPaid();
        System.out.println("Payment updated to "+Club.memberList.get(idInput).getPaid());
        Club.memberList.get(idInput).setPaymentYear();
    }

    public void updatePaymentList()
    {
        for(int i = 0; i<Club.memberList.size(); i++)
        {
            if(!Club.memberList.get(i).getPaid())
            {
                paymentList.add(Club.memberList.get(i));
            }
        }
        int listSize = paymentList.size()-1;
        for(int j = listSize; j>=0; j--)
        {
            if (paymentList.get(j).getPaid())
            {
                paymentList.remove(j);
            }
        }
    }

    public void printPaymentList()
    {
        int index = 1;
        for(Member m : paymentList)
        {
            System.out.println((index++) + ". " + m);
        }
    }

    public void printMemberList()
    {
        int index = 1;
        for(Member m : Club.memberList)
        {
            System.out.println((index++) + ". " + m);
        }
    }

    public void searchMembers(String nameSearch)
    {
        boolean searchConfirmed = false;
        while(!searchConfirmed)
        {
            System.out.println("List of members with the name '"+nameSearch+"'");
            for(int i = 0; i<Club.memberList.size(); i++)
            {
                if(nameSearch.equalsIgnoreCase(Club.memberList.get(i).getName()))
                {
                    System.out.println((i+1)+Club.memberList.get(i).toString());
                    searchConfirmed = true;
                }
            }
            if(!searchConfirmed)
            {
                System.out.println("No results found\nTry again[1] or Show all members[2]");
                int searchChoice = Club.scan.nextInt();

                if (searchChoice == 1)
                {
                    Club.scan.nextLine();
                    System.out.println("Enter full name of the member to search for");
                    nameSearch = Club.scan.nextLine();
                    nameSearch = nameSearch.trim();
                }

                if (searchChoice == 2)
                {
                    int index = 1;
                    for(Member m : Club.memberList)
                    {
                        System.out.println((index++) + ". " + m);
                    }
                    searchConfirmed = true;
                }
            }
        }
    }


}
