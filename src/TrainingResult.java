//Use this object to create training results to place into ArrayList
public class TrainingResult
{
    private String discipline;
    private String time;
    private String date;

    public TrainingResult(String discipline, String time, String date)
    {
        this.discipline = discipline;
        this.time = time;
        this.date = date;
    }

    public void setDiscipline(String discipline)
    {
        this.discipline = discipline;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDiscipline()
    {
        return discipline;
    }

    public String getTime()
    {
        return time;
    }

    public String getDate()
    {
        return date;
    }

    @Override
    public String toString()
    {
        return discipline + " " + time + " " + date;
    }
}
