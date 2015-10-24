package io.github.rob__.timetable;

import java.io.StringReader;
import java.util.List;

public class Lesson {
    String subject;
    String teacher;
    String room;

    int[] timesStart;
    int[] timesEnd;

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getSubject(){
        return this.subject;
    }

    public void setTeacher(String teacher){
        this.teacher = teacher;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public void setRoom(String room){
        this.room = room;
    }

    public String getRoom(){
        return this.room;
    }

    /**
     * @return The times that the lesson starts in a list of int arrays - [hour, minute]
     */
    public int[] getTimeStart(){
        return this.timesStart;
    }

    public void setTimeStart(int hour, int minute){
        timesStart = new int[] {hour, minute};
    }

    /**
     * @return The time that the lesson ends in an int array - [hour, minute]
     */
    public int[] getTimesEnd(){
        return this.timesEnd;
    }

    public void setTimeEnd(int hour, int minute){
        timesEnd = new int[] {hour, minute};
    }

    public int getTimeTotalMinutes(int[] time){
        return (time[0] * 60) + time[1];
    }

    public String convertTimeToString(int[] time){
        return String.format(
                "%s:%s",
                (time[0] < 10 ? "0" : "") + String.valueOf(time[0]),
                (time[1] < 10 ? "0" : "") + String.valueOf(time[1])
        );
    }
}
