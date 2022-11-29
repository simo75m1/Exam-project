public class Competition //Use this class to create competition objects to put into Arraylist
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
        return "Discipline: " + discipline + " Competition: " + competition + " Placement: " + placement + " Time: " + time;
    }

}
