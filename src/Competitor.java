import java.util.ArrayList;

public class Competitor extends Member
{
    private String disc1 = "";
    private String disc2 = "";
    private String disc3 = "";
    private String time1 = "999";
    private String time2 = "999";
    private String time3 = "999";

    public Competitor(String name, int age, String memberStatus, boolean paid, String disc1)
    {
        super(name, age, memberStatus, paid);
        this.disc1 = disc1;
        ArrayList<Competition> competitionInfoList = new ArrayList<>(); // Discipline, competition, placement, time
    }
    public Competitor(String name, int age, String memberStatus, boolean paid, String disc1, String disc2)
    {
        super(name, age, memberStatus, paid);
        this.disc1 = disc1;
        this.disc2 = disc2;
        ArrayList<Competition> competitionInfoList = new ArrayList<>(); // Discipline, competition, placement, time
    }
    public Competitor(String name, int age, String memberStatus, boolean paid, String disc1, String disc2, String disc3)
    {
        super(name, age, memberStatus, paid);
        this.disc1 = disc1;
        this.disc2 = disc2;
        this.disc3 = disc3;
        ArrayList<Competition> competitionInfoList = new ArrayList<>(); // Discipline, competition, placement, time
    }

    public void setTime(int timeInput, String timeChange)
    {
        if(timeInput==1)
        {
            this.time1 = timeChange;
        }
        if(timeInput==2)
        {
            this.time2 = timeChange;
        }
        if(timeInput==3)
        {
            this.time3 = timeChange;
        }
    }

    public boolean checkDiscipline(int discInput)
    {
        if(discInput==1 && disc1 != "")
        {
            return true;
        }
        if(discInput==2 && disc2 != "")
        {
            return true;
        }
        if(discInput==3 && disc3 != "")
        {
            return true;
        }
        return false;
    }

    @Override // for competitor info
    public String printMemberInfo()
    {
        return super.printMemberInfo();
    }


}