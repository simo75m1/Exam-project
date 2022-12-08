import java.io.FileNotFoundException;
import java.util.Arrays;
//Use this class to sort results of competitive members
public class TopFive
{
    private String[] topFiveList = new String[15];

    public TopFive() throws FileNotFoundException
    {}

    //Prints first 5 results of the sorted Array of disciplines
    public void printTopFive(String discipline)
    {
        Arrays.fill(topFiveList, "N/A");
        sortDisciplines(discipline);
        System.out.println("Top five for "+discipline+":");
        for (int i = 0; i < 5; i++)
        {
            System.out.println((i+1)+". "+topFiveList[i]);
        }
    }

    //Checks all members results, and compares to input from user. Any matches in search is
    //placed inside Array and sorted in ascending order.
    public void sortDisciplines(String discipline)
    {
        String tempStr;
        String tempTime;
        String tempName;
        String tempDate;

        for(int i = 0; i<Club.coachList.size(); i++)
        {
            for(int j = 0; j < Club.coachList.get(i).trainingResultsList.size(); j++) //
            {
                if(discipline.equalsIgnoreCase(Club.coachList.get(i).trainingResultsList.get(j).getDiscipline()))
                {
                    tempTime = Club.coachList.get(i).trainingResultsList.get(j).getTime();
                    tempName = Club.coachList.get(i).getName();
                    tempDate = Club.coachList.get(i).trainingResultsList.get(j).getDate();
                    tempStr = tempTime +", "+ tempName + ", " + tempDate;
                    topFiveList[i] = tempStr;
                }
            }
        }
        Arrays.sort(topFiveList);
    }
}
