//Use this class to create competition objects to put into Arraylist
public class Competition
{
    private String discipline;
    private String competition;
    private String placement;
    private String time;
    
    public Competition(String discipline, String competition, String placement, String time)
    {
        this.discipline = discipline;
        this.competition = competition;
        this.placement = placement;
        this.time = time;
    }

    @Override //for arraylist
    public String toString()
    {
        return discipline + " " + competition + " " + placement + " " + time;
    }

    public void setCompetition(String competition)
    {
        this.competition = competition;
    }

    public void setDiscipline(String discipline)
    {
        this.discipline = discipline;
    }

    public void setPlacement(String placement)
    {
        this.placement = placement;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}