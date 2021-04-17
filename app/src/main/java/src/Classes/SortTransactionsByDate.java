package src.Classes;

import java.util.Comparator;

public class SortTransactionsByDate implements Comparator<Transaction> {

    @Override
    public int compare(Transaction t1, Transaction t2) {

        if(t1.getDate().equals(t2.getDate()))
            return SortByTime(t2, t1);
        else
            return SortByDates(t2, t1);
    }

    // sort by year, month and day
    private int SortByDates(Transaction t1, Transaction t2) {
        String[] t1_date = t1.getDate().split("/");
        String[] t2_date = t2.getDate().split("/");
        if(t1_date[2].equals(t2_date[2])) {
            if(t1_date[1].equals(t2_date[1])) {
                // sort by day
                int t1_day = SelectCorrectTime(t1_date, 0);
                int t2_day = SelectCorrectTime(t2_date, 0);
                return t1_day - t2_day;
            } else {
                // sort by month
                int t1_month = SelectCorrectTime(t1_date, 1);
                int t2_month = SelectCorrectTime(t2_date, 1);
                return t1_month - t2_month;
            }
        }
        // sort by year
        return Integer.parseInt(t1_date[2]) - Integer.parseInt(t2_date[2]);
    }

    // sort by hours and minutes
    private int SortByTime(Transaction t1, Transaction t2) {
        int t1_hours, t2_hours, t1_minutes, t2_minutes;
        String[] t1_time = t1.getTime().split(":");
        String[] t2_time = t2.getTime().split(":");
        t1_hours = SelectCorrectTime(t1_time, 0);
        t2_hours = SelectCorrectTime(t2_time, 0);
        t1_minutes = SelectCorrectTime(t1_time, 1);
        t2_minutes = SelectCorrectTime(t2_time, 1);
        if(t1_hours == t2_hours)
            return t1_minutes - t2_minutes;
        else
            return t1_hours - t2_hours;
    }

    // select the correct time - ignore zero's
    // example: time  = 09:04   =>  hours = 9,  minutes = 4
    private int SelectCorrectTime(String[] time, int index) {
        if((time[index]).charAt(0) == '0')
            return  Character.getNumericValue((time[index]).charAt(1));
        else
            return  Integer.parseInt(time[index]);
    }
}
