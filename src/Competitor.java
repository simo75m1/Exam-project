import java.util.ArrayList;

//Subclass of Member. Initializes arraylists for results.
public class Competitor extends Member
{
    ArrayList<Competition> competitionList = new ArrayList<>(); // Discipline, competition, placement, time
    ArrayList<TrainingResult> trainingResultsList = new ArrayList<>(); // Discipline, Time, Date


    public Competitor(String name, int age, String memberStatus, String memberType, boolean paid)
    {
        super(name, age, memberStatus, memberType, paid);
    }


    // for competitor info
    public String printCompInfo()
    {
        return "Name: "+ super.getName() + "\nAgegroup: " + super.getAgeGroup() + "\nTraining result(s): " + trainingResultsList + "\nCompetition info: " + competitionList+"\n";
    }

}