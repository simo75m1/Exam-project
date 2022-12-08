import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Club
{
    //Instantiating Lists and Objects. The static objects are used in other files.
    static ArrayList<Member> memberList = new ArrayList<>(); //All members
    static ArrayList<Competitor> coachList = new ArrayList<>(); //Only competitors
    TopFive topFive = new TopFive();
    Accounting accountant = new Accounting();
    static Scanner scan = new Scanner(System.in);
    Scanner fromMemberFile;
    Scanner fromTrainFile;
    Scanner fromCompFile;
    static File fileNameMembers = new File("data/members.txt");
    static File fileNameTraining = new File("data/trainingResults.txt");
    static File fileNameComp = new File("data/competitionResults.txt");
    static PrintStream toMemberFile;
    static PrintStream toTrainFile;
    static PrintStream toCompFile;

    private boolean appStart = true;

    public Club() throws FileNotFoundException
    {}

    public void runApp() throws FileNotFoundException
    {
        loadMembers();
        accountant.updatePaymentList();
        while(appStart)
        {
            System.out.println("\nMain menu\n----------\n");
            System.out.println("Administration[1], Accounting[2], Coaching[3], Exit program[0]");
            int menu = scan.nextInt();
            switch(menu)
            {
                case 1:
                    administrationMenu();
                    break;
                case 2:
                    accountant.accountingMenu();
                    break;
                case 3:
                    coachMenu();
                    break;
                case 0:
                    saveMembers();

                    System.out.println("Closing...");
                    System.out.println("All member data saved!");
                    appStart = false;
            }
        } // End of while(appStart)
    } // End of runApp

    public void administrationMenu() throws FileNotFoundException
    {
        System.out.println("Register new member[1], Edit existing member[2], Show member list[3], Exit menu[0]");
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
                    nameInput = nameInput.trim();
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

                if (reg == 1) //Add casual
                {
                    memberList.add(new Casual(nameInput, ageInput, memberStatusString, "casual",paidBoolean));
                }
                if(reg == 2) //Add competitor
                {
                    memberList.add(new Competitor(nameInput, ageInput, memberStatusString, "competitor", paidBoolean));
                    coachList.add((Competitor) memberList.get(memberList.size()-1));
                }
                saveMembers();
                accountant.updatePaymentList();
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
                    if(editMemberTypeInput == 1 && memberList.get(editMember).getMemberType().equals("competitor"))
                    {
                        String tempName = memberList.get(editMember).getName();
                        int tempAge = memberList.get(editMember).getAge();
                        String tempStatus = memberList.get(editMember).getMemberStatus();
                        String tempType = "casual";
                        boolean tempPaid = memberList.get(editMember).getPaid();
                        memberList.set(editMember, new Casual(tempName, tempAge, tempStatus, tempType, tempPaid));
                    }
                    if(editMemberTypeInput == 2 && memberList.get(editMember).getMemberType().equals("casual"))
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
                accountant.updatePaymentList();
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
                    printMemberList("casual");
                }
                break;
            case 0: //Exit
                break;
        }
    } //End of administrationMenu()

    public void coachMenu() throws FileNotFoundException
    {
        System.out.println("Register results[1], Show top five[2], Exit[0]");
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
                System.out.println("New result[1], Edit existing result[2] or Delete existing result[3]");
                int addResult = scan.nextInt();
                String tempDisc;
                String tempTime;
                String tempDate;
                String tempPlace;
                String tempComp;

                if (editResult == 1) //Editing training results
                {
                    if (addResult == 1) //New result
                    {
                        scan.nextLine();
                        System.out.println("Please enter discipline (ONLY ONE WORD!)");
                        tempDisc = scan.nextLine();
                        System.out.println("Please enter time");
                        tempTime = scan.nextLine();
                        System.out.println("Please enter date");
                        tempDate = scan.nextLine();

                        coachList.get(editComp).trainingResultsList.add(new TrainingResult(tempDisc, tempTime, tempDate));
                        System.out.println("Training result added!");
                    }
                    if (addResult == 2) //Edit existing result
                    {
                        for(int i = 0; i < coachList.get(editComp).trainingResultsList.size(); i++)
                        {
                            System.out.println((i+1)+ ". " + coachList.get(editComp).trainingResultsList.get(i).toString());
                        }
                        System.out.println("What discipline do you want to edit? [1-"+ coachList.get(editComp).trainingResultsList.size() + "]");
                        int discChoice = scan.nextInt()-1;
                        System.out.println("What would you like to edit? Discipline[1], time[2], date[3]");
                        int editSingleDisc = scan.nextInt();
                        if(editSingleDisc == 1) //Edit discipline of training result
                        {
                            System.out.println("Enter the new name for the discipline (ONLY ONE WORD!)");
                            scan.nextLine();
                            String nameInput = scan.nextLine();
                            coachList.get(editComp).trainingResultsList.get(discChoice).setDiscipline(nameInput);
                            System.out.println("Discipline name updated to " + nameInput);
                        }
                        if(editSingleDisc == 2) //Edit time of training result
                        {
                            System.out.println("Enter the new time for the result");
                            scan.nextLine();
                            String timeInput = scan.nextLine();
                            coachList.get(editComp).trainingResultsList.get(discChoice).setTime(timeInput);
                            System.out.println("Time updated to " + timeInput);
                        }
                        if(editSingleDisc == 3) //Edit date of training result
                        {
                            System.out.println("Enter the new date for the result");
                            scan.nextLine();
                            String dateInput = scan.nextLine();
                            coachList.get(editComp).trainingResultsList.get(discChoice).setDate(dateInput);
                            System.out.println("Date updated to " + dateInput);
                        }
                    }
                    if(addResult == 3) //Delete existing result
                    {
                        for(int i = 0; i < coachList.get(editComp).trainingResultsList.size(); i++)
                        {
                            System.out.println((i+1)+ ". " + coachList.get(editComp).trainingResultsList.get(i).toString());
                        }
                        System.out.println("Which result do you want to delete? [1-" + coachList.get(editComp).trainingResultsList.size() + "]");
                        int deleteInput = scan.nextInt()-1;
                        System.out.println(coachList.get(editComp).trainingResultsList.get(deleteInput).toString() + "deleted from training results");
                        coachList.get(editComp).trainingResultsList.remove(deleteInput);
                    }
                    saveMembers();
                }
                if (editResult == 2) //Competitive results
                {
                    if(addResult == 1) //New competitive result
                    {
                        scan.nextLine();
                        System.out.println("Please enter discipline (ONLY ONE WORD!)");
                        tempDisc = scan.nextLine();
                        System.out.println("Please enter time");
                        tempTime = scan.nextLine();
                        System.out.println("Please enter the swimmers placement at the competition");
                        tempPlace = scan.nextLine();
                        System.out.println("Please enter the name of the competition (ONLY ONE WORD!)");
                        tempComp = scan.nextLine();

                        coachList.get(editComp).competitionList.add(new Competition(tempDisc, tempComp, tempPlace, tempTime));
                        System.out.println("Competitive result added!");
                    }
                    if(addResult == 2) //Edit existing comp result
                    {
                        for(int i = 0; i < coachList.get(editComp).competitionList.size(); i++)
                        {
                            System.out.println((i+1)+ ". " + coachList.get(editComp).competitionList.get(i).toString());
                        }
                        System.out.println("What discipline do you want to edit? [1-"+ coachList.get(editComp).competitionList.size() + "]");
                        int discChoice = scan.nextInt()-1;
                        System.out.println("What would you like to edit? Discipline[1], time[2], placement[3], competition[4]");
                        int editSingleDisc = scan.nextInt();
                        if(editSingleDisc == 1) //Edit discipline of existing competitive result
                        {
                            System.out.println("Enter the new name for the discipline. (ONLY ONE WORD!)");
                            scan.nextLine();
                            String nameInput = scan.nextLine();
                            coachList.get(editComp).competitionList.get(discChoice).setDiscipline(nameInput);
                            System.out.println("Discipline name updated to " + nameInput);
                        }
                        if(editSingleDisc == 2) //Edit time for existing competitive result
                        {
                            System.out.println("Enter the new time for the result");
                            scan.nextLine();
                            String timeInput = scan.nextLine();
                            coachList.get(editComp).competitionList.get(discChoice).setTime(timeInput);
                            System.out.println("Time updated to " + timeInput);
                        }
                        if(editSingleDisc == 3) //Edit placement for existing competitive result
                        {
                            System.out.println("Enter the new placement for the result");
                            scan.nextLine();
                            String placementInput = scan.nextLine();
                            coachList.get(editComp).competitionList.get(discChoice).setPlacement(placementInput);
                            System.out.println("Placement updated to " + placementInput);
                        }
                        if(editSingleDisc == 4) //Edit comp name of existing result
                        {
                            System.out.println("Enter the new competition name (ONLY ONE WORD!)");
                            scan.nextLine();
                            String competitionInput = scan.nextLine();
                            coachList.get(editComp).competitionList.get(discChoice).setCompetition(competitionInput);
                            System.out.println("Competition name updated to " + competitionInput);
                        }
                    }
                    if(addResult == 3) //Remove existing competitive result
                    {
                        for(int i = 0; i < coachList.get(editComp).competitionList.size(); i++)
                        {
                            System.out.println((i+1)+ ". " + coachList.get(editComp).competitionList.get(i).toString());
                        }
                        System.out.println("Which result do you want to delete? [1-" + coachList.get(editComp).competitionList.size() + "]");
                        int deleteInput = scan.nextInt()-1;
                        System.out.println(coachList.get(editComp).competitionList.get(deleteInput).toString() + "deleted from competitive results");
                        coachList.get(editComp).competitionList.remove(deleteInput);
                    }
                    saveMembers();
                }
                break;
            case 2: //Show Top Five for a chosen discipline that user searches for
                System.out.println("Which discipline would you like to see a top five for? (Example[crawl,400m])");
                scan.nextLine();
                String discFive = scan.nextLine();
                topFive.printTopFive(discFive);
                break;
            case 0: // exits by breaking
                break;
        }
    } //End of coachMenu

    public void loadMembers() throws FileNotFoundException
    {
        fromMemberFile = new Scanner(fileNameMembers);
        fromTrainFile = new Scanner(fileNameTraining);
        fromCompFile = new Scanner(fileNameComp);

        int memberCounter = 0;
        int competitorCounter = 0;
        String tempName;
        String fullName;
        String trimmedName;
        int tempAge;
        String tempStatus;
        String tempType;
        boolean tempPaid;
        String trainFileString;
        String compFileString;
        Scanner trainFileScan;
        Scanner compFileScan;

        while(fromMemberFile.hasNext())
        {
            fullName = "";
            while(!fromMemberFile.hasNextInt())
            {
                tempName = fromMemberFile.next();
                fullName = fullName.concat(tempName + " ");
            }

            trimmedName = fullName.trim();
            tempAge = fromMemberFile.nextInt();
            tempStatus = fromMemberFile.next();
            tempType = fromMemberFile.next();
            tempPaid = fromMemberFile.nextBoolean();

            if (tempType.equals("competitor"))
            {
                memberList.add(new Competitor(trimmedName, tempAge, tempStatus, tempType, tempPaid));
                coachList.add((Competitor) memberList.get(memberCounter));

                trainFileString = fromTrainFile.nextLine();
                trainFileScan = new Scanner(trainFileString);
                compFileString = fromCompFile.nextLine();
                compFileScan = new Scanner(compFileString);

                while(trainFileScan.hasNext()) //Reads training result file until no more results
                {
                    String var1 = trainFileScan.next();
                    String var2 = trainFileScan.next();
                    String var3 = trainFileScan.next();

                    coachList.get(competitorCounter).trainingResultsList.add(new TrainingResult(var1, var2, var3));
                }
                while(compFileScan.hasNext()) //Reads competitive result file until no more results
                {
                    String var1 = compFileScan.next();
                    String var2 = compFileScan.next();
                    String var3 = compFileScan.next();
                    String var4 = compFileScan.next();
                    coachList.get(competitorCounter).competitionList.add(new Competition(var1, var2, var3, var4));
                }
                competitorCounter++;
            }
            if (tempType.equals("casual")) //Skips a line if it's a casual
            {
                memberList.add(new Casual(trimmedName, tempAge, tempStatus, tempType, tempPaid));
                fromTrainFile.nextLine();
                fromCompFile.nextLine();
            }
            memberCounter++;
        }
        fromMemberFile.close();
        fromTrainFile.close();
        fromCompFile.close();
    } // End of loadMembers

    public static void saveMembers() throws FileNotFoundException
    {
        toMemberFile = new PrintStream(new FileOutputStream(fileNameMembers, false));
        toTrainFile = new PrintStream(new FileOutputStream(fileNameTraining, false));
        toCompFile = new PrintStream(new FileOutputStream(fileNameComp, false));
        String fullName;
        int tempAge;
        String tempStatus;
        String tempType;
        boolean tempPaid;
        String fullInfo;
        String fullDiscInfo = "";
        String fullCompInfo = "";
        int competitorCounter = 0;

        for (int i = 0; i < memberList.size(); i++)
        {
            fullName = memberList.get(i).getName();
            tempAge = memberList.get(i).getAge();
            tempStatus = memberList.get(i).getMemberStatus();
            tempType = memberList.get(i).getMemberType();
            tempPaid = memberList.get(i).getPaid();
            fullInfo = fullName + " " + tempAge + " " + tempStatus + " " + tempType + " " + tempPaid;

            if(tempType.equals("competitor"))
            {
                    String tempStr = "";
                    for (int j = 0; j < coachList.get(competitorCounter).trainingResultsList.size(); j++)
                    {
                        tempStr = coachList.get(competitorCounter).trainingResultsList.get(j).toString();
                        fullDiscInfo = fullDiscInfo.concat(tempStr + " ");
                    }

                    for (int j = 0; j < coachList.get(competitorCounter).competitionList.size(); j++)
                    {
                        tempStr = coachList.get(competitorCounter).competitionList.get(j).toString();
                        fullCompInfo = fullCompInfo.concat(tempStr + " ");
                    }

                competitorCounter++;
            }
            toMemberFile.println(fullInfo); //Prints full member info for all members
            toTrainFile.println(fullDiscInfo); //Prints blank line if member is a casual
            toCompFile.println(fullCompInfo); //Prints blank line if member is a casual
            fullDiscInfo = "";
            fullCompInfo = "";
        }
        toMemberFile.close();
        toTrainFile.close();
        toCompFile.close();
    } //End of saveMembers

    public void printMemberList(String type)
    {
        int index = 1;
        if(type.equals("competitor"))
        {
            for(Member m : memberList)
            {
                if(m instanceof Competitor)
                {
                    System.out.println((index++) + ". " + m);
                }
            }
        }
        if(type.equals("casual"))
        {
            for(Member m : memberList)
            {
                if(m instanceof Casual)
                {
                    System.out.println((index++) + ". " + m);
                }
            }
        }

        if(type.equals("all"))
        {
            for(Member m : memberList)
            {
                System.out.println((index++) + ". " + m);
            }
        }
    } //End of printMemberList

}