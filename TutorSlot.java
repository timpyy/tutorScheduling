/**
 * Tim Pornyuenyong
 * Assignment 3
 * 4/13/18
 * tp1288
 */

import java.util.ArrayList;

public class TutorSlot {

    ArrayList<ArrayList<String>> timeArray = new ArrayList<ArrayList<String>>();
    String name;
    String nameAbv;

    /**
     * No-arg Constructor
     */
    public TutorSlot() {
        name = "";
        nameAbv = "";
    }

    /**
     * Resets the TutorSlot object
     */
    public void reset() {
        name = "";
        nameAbv = "";
        for(int i = 0; i < 4; i++) {
            timeArray.clear();
        }
    }

    /**
     * Adding time/day into the appropriate spot in our 2D ArrayList
     * @param time An ArrayList containing all the times on a particular day
     * @param day one letter String representing day
     */
    public void addTimeDay(ArrayList<String> time, String day) {
        int timeArrayIndex = 0;
        //Initializing the timeArray ArrayList for 5 days
        for(int i = 0; i < 5; i++) {
            timeArray.add(new ArrayList<String>());
        }
        if(day.equals("M")) {
            timeArrayIndex = 0;
        }
        if(day.equals("T")) {
            timeArrayIndex = 1;
        }
        if(day.equals("W")) {
            timeArrayIndex = 2;
        }
        if(day.equals("H")) {
            timeArrayIndex = 3;
        }
        if(day.equals("F")) {
            timeArrayIndex = 4;
        }
        for(int i = 0; i < time.size(); i++) {
            timeArray.get(timeArrayIndex).add(time.get(i));
        }
    }

    /**
     * Setting the name
     * @param input name String
     */
    public void addName(String input) {
        name = input;
        char temp = name.charAt(0);
        nameAbv = "" + temp;
    }

    /**
     * Getting the first character of the name (name abbreviation)
     * @return one letter String
     */
    public String getNameAbv() {
        return nameAbv;
    }

    /**
     * Prints out the slots that a particular student requested. Used for debugging purposes.
     */
    public void getStudentInfo() {
        System.out.println("Name: " + name);
        String mondayTimes = "";
        for(int i = 0; i < timeArray.get(0).size(); i++) {
            mondayTimes = mondayTimes + " " + timeArray.get(0).get(i);
        }
        System.out.println("Monday:" + mondayTimes);
        String tuesdayTimes = "";
        for(int i = 0; i < timeArray.get(1).size(); i++) {
            tuesdayTimes = tuesdayTimes + " " + timeArray.get(1).get(i);
        }
        System.out.println("Tuesday:" + tuesdayTimes);
        String wednesdayTimes = "";
        for(int i = 0; i < timeArray.get(2).size(); i++) {
            wednesdayTimes = wednesdayTimes + " " + timeArray.get(2).get(i);
        }
        System.out.println("Wednesday:" + wednesdayTimes);
        String thursdayTimes = "";
        for(int i = 0; i < timeArray.get(3).size(); i++) {
            thursdayTimes = thursdayTimes + " " + timeArray.get(3).get(i);
        }
        System.out.println("Thursday:" + thursdayTimes);
        String fridayTimes = "";
        for(int i = 0; i < timeArray.get(4).size(); i++) {
            fridayTimes = fridayTimes + " " + timeArray.get(4).get(i);
        }
        System.out.println("Friday:" + fridayTimes);
    }

    /**
     * Retrieves the timeslots on Mondays that a student requested.
     * @return ArrayList of Strings representing the times
     */
    public ArrayList<String> getMondayTimes() {
        return timeArray.get(0);
    }

    /**
     * Retrieves the timeslots on Tuesdays that a student requested.
     * @return ArrayList of Strings representing the times
     */
    public ArrayList<String> getTuesdayTimes() {
        return timeArray.get(1);
    }

    /**
     * Retrieves the timeslots on Wednesdays that a student requested.
     * @return ArrayList of Strings representing the times
     */
    public ArrayList<String> getWednesdayTimes() {
        return timeArray.get(2);
    }

    /**
     * Retrieves the timeslots on Thursdays that a student requested.
     * @return ArrayList of Strings representing the times
     */
    public ArrayList<String> getThursdayTimes() {
        return timeArray.get(3);
    }

    /**
     * Retrieves the timeslots on Fridays that a student requested.
     * @return ArrayList of Strings representing the times
     */
    public ArrayList<String> getFridayTimes() {
        return timeArray.get(4);
    }

}
