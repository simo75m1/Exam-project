import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Club
{
    Exerciser exerciser = new Exerciser();
    Competitor competitor = new Competitor();
    Scanner scan = new Scanner(System.in);
    ArrayList<Member> memberList = new ArrayList<>();

    //TODO LOAD MEMBER LIST FIL loadMembers(); ELLER LIGNENDE
    private boolean appStart = true;

    public void runApp()
    {
        while(appStart)
        {
            System.out.println("\nMain menu\n----------\n");
            System.out.println("Administration[1], Accounting[2], Coaching[3]");
            int menu = scan.nextInt(); // Try catch senere
            switch(menu)
            {
                case 1:
                    administrationMenu();
                    break;
                case 2:

                    break;
                case 3:

                    break;
            }


        } // End of while(appStart)
    } // End of runApp

    public void administrationMenu()
    {
        System.out.println("Register new member[1], Edit existing member[2], Show member list[3]");
        int admin = scan.nextInt();
        switch (admin)
        {
            case 1: // New member
                boolean sentinel = true;
                String nameInput = "dummy";
                int ageInput = 1000;
                String memberStatusString = "N/A";
                boolean paidBoolean = false;
                int reg = -99;

                while(sentinel)
                {
                    System.out.println("Please enter name: ");
                    scan.nextLine();
                    nameInput = scan.nextLine();
                    System.out.println("Please enter age: ");
                    ageInput = scan.nextInt();
                    System.out.println("Please choose member status: Active[1] or Passive[2]");
                    int memberStatusInput = scan.nextInt();
                    memberStatusString = "N/A";
                    if (memberStatusInput == 1)
                    {
                        memberStatusString = "Active";
                    } else if (memberStatusInput == 2)
                    {
                        memberStatusString = "Passive";
                    }

                    System.out.println("Is the subscription paid? Yes[1] or No[2]");
                    int paidInput = scan.nextInt();
                    paidBoolean = false;

                    if (paidInput == 1)
                    {
                        paidBoolean = true;
                    } else if (paidInput == 2)
                    {
                        paidBoolean = false;
                    }

                    System.out.println("What type of member do you want to register? \nCasual[1] or Competitive[2]");
                    reg = scan.nextInt();

                    System.out.println("Name: " + nameInput + "\nAge: " + ageInput + "\nMember status: " +
                            memberStatusString + "\nSubscription paid: " + paidBoolean);
                    System.out.println("Is this input correct?");
                    System.out.println("Yes[1] or No[2]");
                    int correct = scan.nextInt();
                    if(correct == 1)
                    {
                        sentinel = false;
                    }
                }  //end of while(sentinel)

                if (reg == 1)
                {
                    memberList.add(new Exerciser(nameInput, ageInput, memberStatusString, "exerciser",paidBoolean));
                }
                if(reg == 2)
                {
                    memberList.add(new Competitor(nameInput, ageInput, memberStatusString, "competitor", paidBoolean));
                }
                System.out.println("New member created successfully!");
                break;

            case 2: // Edit member
                printMemberList("all");
                System.out.println("Which member would you like to edit? [1-" + memberList.size() + "]");
                int editMember = scan.nextInt()-1;
                System.out.println(memberList.get(editMember));
                System.out.println("What would you like to edit? \nName[1], Age[2], Member Type[3]");
                int editInfo = scan.nextInt();
                if(editInfo == 1) // Edit name
                {
                    System.out.println("Please enter the new name");
                    scan.nextLine();
                    String editNameInput = scan.nextLine();
                    memberList.get(editMember).setName(editNameInput);
                    System.out.println("Name changed to: " + editNameInput);
                }
                if(editInfo == 2) // Edit age
                {
                    System.out.println("Please enter the new age:");
                    int editAgeInput = scan.nextInt();
                    memberList.get(editMember).setAge(editAgeInput);
                    memberList.get(editMember).updateGroupAndPrice();
                    System.out.println("Age changed to: " + editAgeInput);
                }
                if(editInfo == 3) //Edit memberType
                {
                    System.out.println("What would you like to change the member type to?\nCasual[1] or Competitor[2]");
                    int editMemberTypeInput = scan.nextInt();
                    if(editMemberTypeInput == 1 && memberList.get(editMember).getMemberType() == "competitor")
                    {
                        String tempName = memberList.get(editMember).getName();
                        int tempAge = memberList.get(editMember).getAge();
                        String tempStatus = memberList.get(editMember).getMemberStatus();
                        String tempType = "casual";
                        boolean tempPaid = memberList.get(editMember).getPaid();
                        memberList.set(editMember, new Exerciser(tempName, tempAge, tempStatus, tempType, tempPaid));
                    }
                    if(editMemberTypeInput == 2 && memberList.get(editMember).getMemberType() == "casual")
                    {
                        String tempName = memberList.get(editMember).getName();
                        int tempAge = memberList.get(editMember).getAge();
                        String tempStatus = memberList.get(editMember).getMemberStatus();
                        String tempType = "competitor";
                        boolean tempPaid = memberList.get(editMember).getPaid();
                        memberList.set(editMember, new Competitor(tempName, tempAge, tempStatus, tempType, tempPaid));
                    }
                    System.out.println("Member type changed to: " + memberList.get(editMember).getMemberType());
                }
                break;
            case 3: // Print members
                System.out.println("What type of members do you want to look at?\nAll[1], Competitors[2], Casuals[3]");
                scan.nextLine();
                int printChoice = scan.nextInt();
                if(printChoice == 1)
                {
                    printMemberList("all");
                }
                if(printChoice == 2)
                {
                    printMemberList("competitor");
                }
                if(printChoice == 3)
                {
                    printMemberList("exerciser");
                }
                break;
        }
    } //End of administrationMenu()

    //TODO accountingMenu()
    //TODO coachMenu()
    //TODO loadMembers()

    public void printMemberList(String type)
    {
        int index = 1;
        if(type == "competitor")
        {
            for(Member m : memberList)
            {
                if(m instanceof Competitor)
                {
                    System.out.println((index++) + ". " + m);
                }
            }
        }
        if(type == "exerciser")
        {
            for(Member m : memberList)
            {
                if(m instanceof Exerciser)
                {
                    System.out.println((index++) + ". " + m);
                }
            }
        }

        if(type == "all")
        {
            for(Member m : memberList)
            {
                System.out.println((index++) + ". " + m);
            }
        }
    }

}