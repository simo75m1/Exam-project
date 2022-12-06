import java.io.FileNotFoundException;
import java.util.Arrays;

public class TopFive
{
    static Club club;

    static
    {
        try
        {
            club = new Club();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    //TODO læg det ind som strings med tiden først.
    static String[] topFiveList = new String[15];

    public TopFive() throws FileNotFoundException
    {}

    public static void printTopFive(String discipline)
    {
        sortDisciplines(discipline);
        for (int i = 0; i <= 4; i++)
        {
            System.out.println(topFiveList[i]);
        }
    }

    public static void sortDisciplines(String discipline)
    {
        String tempStr;
        String tempTime;
        String tempName;
        String tempDate;
        for(int i = 0; i<club.coachList.size(); i++)
        {
            for(int j = 0; j < club.coachList.get(i).trainingResultsList.size(); j++)
            {
                if (discipline.equalsIgnoreCase(club.coachList.get(i).trainingResultsList.get(j).getDiscipline()));
                {
                    tempTime = club.coachList.get(i).trainingResultsList.get(j).getTime();
                    tempName = club.coachList.get(i).getName();
                    tempDate = club.coachList.get(i).trainingResultsList.get(j).getDate();
                    tempStr = tempTime +", "+ tempName + ", " + tempDate;
                    topFiveList[j] = tempStr;
                }
            }
        }
        Arrays.sort(topFiveList);
    }
}
