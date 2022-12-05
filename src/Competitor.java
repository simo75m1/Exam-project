import java.util.ArrayList;

public class Competitor extends Member
{
    ArrayList<Competition> competitionInfoList = new ArrayList<>(); // Discipline, competition, placement, time
    ArrayList<TrainingResult> trainingResultsList = new ArrayList<>(); // Discipline, Time, Date

    public Competitor()
    {
    }

    public Competitor(String name, int age, String memberStatus, String memberType, boolean paid)
    {
        super(name, age, memberStatus, memberType, paid);
    }


    // for competitor info
    public String printCompInfo()
    {
        return "\nName: "+ super.getName() + "\nAgegroup: " + super.getAgeGroup() + "\nTraining result(s): " + trainingResultsList + "\nCompetition info: " + competitionInfoList;
    }

}