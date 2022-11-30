import java.util.ArrayList;

public class Competitor extends Member
{
    private String disc1 = "";
    private String disc2 = "";
    private String disc3 = "";
    private String time1 = "999";
    private String time2 = "999";
    private String time3 = "999";
    ArrayList<Competition> competitionInfoList = new ArrayList<>(); // Discipline, competition, placement, time
    ArrayList<TrainingResult> trainingResultsList = new ArrayList<>(); // Discipline, Time,

    public Competitor()
    {
    }

    public Competitor(String name, int age, String memberStatus, String memberType, boolean paid)
    {
        super(name, age, memberStatus, paid);
        this.disc1 = disc1;
    }


    @Override // for competitor info
    public String toString()
    {
        return "\nName: "+ super.getName() + "\nAgegroup: " + super.getAgeGroup() + "\nTraining result(s): " + trainingResultsList + "\nCompetition info: " + competitionInfoList;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}