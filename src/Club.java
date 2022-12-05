import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Club
{
    Exerciser exerciser = new Exerciser();
    Competitor competitor = new Competitor();
    Scanner scan = new Scanner(System.in);
    ArrayList<Member> memberList = new ArrayList<>(); //All members
    ArrayList<Competitor> coachList = new ArrayList<>(); //Only competitors
    File fileName = new File("data/members.txt");
    PrintStream toFile = new PrintStream(new FileOutputStream(fileName, true));
    Scanner fromFile = new Scanner(fileName);

    private boolean appStart = true;

    public Club() throws FileNotFoundException
    {}

    public void runApp() throws FileNotFoundException
    {
        loadMembers();
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
                    coachMenu();
                    break;
            }
        } // End of while(appStart)
    } // End of runApp

    public void administrationMenu() throws FileNotFoundException
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
                        memberStatusString = "active";
                    } else if (memberStatusInput == 2)
                    {
                        memberStatusString = "passive";
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
                    coachList.add((Competitor) memberList.get(memberList.size()-1));
                }
                saveMembers();
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
                saveMembers();
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

    public void coachMenu() throws FileNotFoundException
    {
        System.out.println("Register results[1], Show top five[2]");
        int cAdmin = scan.nextInt();
        switch (cAdmin)
        {
            case 1: // Register results
                int compCounter = 0;
                for (int i = 0; i < memberList.size(); i++)
                {
                    if (memberList.get(i) instanceof Competitor)
                    {
                        System.out.println(compCounter+1 + ".");
                        System.out.println(coachList.get(compCounter).printCompInfo());
                        compCounter++;
                    }
                }
                System.out.println("Which member would you like to edit? [1-" + coachList.size() +"]");
                int editComp = scan.nextInt()-1;
                coachList.get(editComp).printCompInfo();
                System.out.println("What would you like to edit? Training results[1] or competitive results[2]");
                int editResult = scan.nextInt();
                System.out.println("New discipline[1] or Edit an existing result[2]");
                int addResult = scan.nextInt();
                String tempDisc;
                String tempTime;
                String tempDate;

                if (editResult == 1)
                {
                    if (addResult == 1)
                    {
                        scan.nextLine();
                        System.out.println("Please enter discipline");
                        tempDisc = scan.nextLine();
                        System.out.println("Please enter time");
                        tempTime = scan.nextLine();
                        System.out.println("Please enter date");
                        tempDate = scan.nextLine();

                        coachList.get(editComp).trainingResultsList.add(new TrainingResult(tempDisc, tempTime, tempDate));
                        saveMembers();
                    }
                    if (addResult == 2)
                    {

                    }
                }
                if (editResult == 2)
                {
                    if(addResult == 1)
                    {

                    }
                    if(addResult == 2)
                    {

                    }
                }



                break;
            case 2: // Show top five
                break;
        }
    }

    public void loadMembers() throws FileNotFoundException
    {
        fromFile = new Scanner(fileName);
        int memberCounter = 0;
        String tempName;
        String fullName;
        int tempAge;
        String tempStatus;
        String tempType;
        boolean tempPaid;
        int loadCounter = 0;

        while(fromFile.hasNext())
        {
            fullName = "";
            while(!fromFile.hasNextInt())
            {
                tempName = fromFile.next();
                fullName = fullName.concat(tempName + " ");
            }

            tempAge = fromFile.nextInt();
            tempStatus = fromFile.next();
            tempType = fromFile.next();
            tempPaid = fromFile.nextBoolean();

            if (tempType.equals("competitor"))
            {
                memberList.add(new Competitor(fullName, tempAge, tempStatus, tempType, tempPaid));
                coachList.add((Competitor) memberList.get(memberCounter));
                //Can add disciplines here.
            }
            if (tempType.equals("casual"))
            {
                memberList.add(new Exerciser(fullName, tempAge, tempStatus, tempType, tempPaid));
            }
            memberCounter++;
        }
        fromFile.close();
    }

    public void saveMembers() throws FileNotFoundException
    {
        toFile = new PrintStream(new FileOutputStream(fileName, false));
        String fullName;
        int tempAge;
        String tempStatus;
        String tempType;
        boolean tempPaid;
        String fullInfo;
        int competitorCounter = 0;

        for (int i = 0; i < memberList.size(); i++)
        {
            fullName = memberList.get(i).getName();
            tempAge = memberList.get(i).getAge();
            tempStatus = memberList.get(i).getMemberStatus();
            tempType = memberList.get(i).getMemberType();
            tempPaid = memberList.get(i).getPaid();
            fullInfo = fullName + " " + tempAge + " " + tempStatus + " " + tempType + " " + tempPaid;

            if (tempType.equals("competitor"))
            {
                String tempstr = "";
                for(int j = 0; j < coachList.get(competitorCounter).trainingResultsList.size(); j++)
                {
                  tempstr = coachList.get(competitorCounter).trainingResultsList.get(j).toString();
                  fullInfo = fullInfo.concat(" " + tempstr);
                  //TODO tilføj compinfo til saveMembers
                }
                competitorCounter++;
            }

            toFile.println(fullInfo);



        }
    }
    //TODO accountingMenu()
    //TODO coachMenu()

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