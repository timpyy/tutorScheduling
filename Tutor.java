/**
 * Tim Pornyuenyong
 * Assignment 3
 * 4/13/18
 * tp1288
 */

import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Tutor {
    public static void main(String[] args) {

        String fileLine = "";
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the file path: ");
            String userInput;
            userInput = input.next();

            //Accessing the files
            File inputFile = new File(userInput);

            Scanner fileData = new Scanner(inputFile);
            fileLine = fileData.nextLine();
        }
        catch (FileNotFoundException e) {
            System.out.println("Invalid file path.");
        }

        String input = fileLine;

        ArrayList<String> timeArray = new ArrayList<String>();
        Stack<Character> stack = new Stack();
        ArrayList<TutorSlot> students = new ArrayList<TutorSlot>();


        //Going through the input characters
        TutorSlot student = new TutorSlot();
        for(int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            stack.push(current);
            //If ] is detected (time)
            if (current == ']') {
                char temp;
                stack.pop(); //Pops the closing bracket
                String time = "";
                time = stack.pop() + time;
                time = stack.pop() + time;
                time = stack.pop() + time;
                time = stack.pop() + time;
                temp = stack.pop(); //Pops the opening bracket
                //If opening bracket is missing
                if (temp != '[') {
                    student.reset();
                    timeArray.clear();
                    while(true) {
                        i++;
                        if(i > input.length() - 1) {
                            break;
                        }
                        stack.push(input.charAt(i));
                        if(stack.peek() == '(') {
                            while(!stack.isEmpty()) {
                                stack.pop();
                            }
                            stack.push('(');
                            break;
                        }
                    }

                }
                else {
                    timeArray.add(time);
                }
            }
            //If > is detected (day)
            if (current == '>') {
                char temp2;
                stack.pop(); //Pops the closing bracket
                String day = "";
                day = stack.pop() + day;
                temp2 = stack.pop(); //Pops the opening bracket
                if (temp2 != '<') {
                    student.reset();
                    timeArray.clear();
                    while(true) {
                        i++;
                        if(i > input.length() - 1) {
                            break;
                        }
                        stack.push(input.charAt(i));
                        if(stack.peek() == '(') {
                            while(!stack.isEmpty()) {
                                stack.pop();
                            }
                            stack.push('(');
                            break;
                        }
                    }
                }
                else {
                    student.addTimeDay(timeArray, day);
                    timeArray.clear();
                }
            }
            //Irregularities with () detected
            if(current == '(') {
                student.reset();
                timeArray.clear();
                while(!stack.isEmpty()) {
                    stack.pop();
                }
                stack.push('(');
            }
            //If ) is detected (name)
            if(current == ')') {
                stack.pop(); //Pops the closing bracket
                String name = "";
                boolean validName = true;
                while(true) {
                    if(stack.isEmpty()) {
                        validName = false;
                        break;
                    }
                    char letter = stack.pop();
                    if(letter == '<') {
                        student.reset();
                        timeArray.clear();
                        validName = false;
                        break;
                    }
                    if(letter == '[') {
                        student.reset();
                        timeArray.clear();
                        validName = false;
                        break;
                    }
                    if(letter == '(') {
                        break;
                    }
                    name = letter + name;
                }
                if(validName) {
                    student.addName(name);
                    students.add(student);
                    student = new TutorSlot();
                }
                else {
                    student.reset();
                    timeArray.clear();
                    student = new TutorSlot();
                }
            }
        }
        /**
        //Printing out student info for debugging
        for(int i = 0; i < students.size(); i++) {
            students.get(i).getStudentInfo();
            System.out.println("-----------------------");
        }
         **/

        //Creating the 2D array representing the schedule (Day vs Time)
        String[][] schedule = new String[13][5];
        int row = 13;

        //Initializing the 2D array
        for(int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[i].length; j++) {
                schedule[i][j] = "";
            }
        }

        //Recording the amount of people signed up on each day
        int monAmount = 0, tueAmount = 0, wedAmount = 0, thuAmount = 0, friAmount = 0;

        //Populating the 2D array with data from TutorSlot objects
        for(int i = 0; i < students.size(); i++) {
            ArrayList<String> tempMonTimes, tempTueTimes, tempWedTimes, tempThuTimes, tempFriTimes;
            tempMonTimes = students.get(i).getMondayTimes();
            monAmount += tempMonTimes.size();
            tempTueTimes = students.get(i).getTuesdayTimes();
            tueAmount += tempTueTimes.size();
            tempWedTimes = students.get(i).getWednesdayTimes();
            wedAmount += tempWedTimes.size();
            tempThuTimes = students.get(i).getThursdayTimes();
            thuAmount += tempThuTimes.size();
            tempFriTimes = students.get(i).getFridayTimes();
            friAmount += tempFriTimes.size();

            //Monday
            for(int j = 0; j < tempMonTimes.size(); j++) {
                if(tempMonTimes.get(j).equals("0900")) {
                    schedule[0][0] = schedule[0][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1000")) {
                    schedule[1][0] = schedule[1][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1100")) {
                    schedule[2][0] = schedule[2][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1200")) {
                    schedule[3][0] = schedule[3][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1300")) {
                    schedule[4][0] = schedule[4][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1400")) {
                    schedule[5][0] = schedule[5][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1500")) {
                    schedule[6][0] = schedule[6][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1600")) {
                    schedule[7][0] = schedule[7][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1700")) {
                    schedule[8][0] = schedule[8][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1800")) {
                    schedule[9][0] = schedule[9][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("1900")) {
                    schedule[10][0] = schedule[10][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("2000")) {
                    schedule[11][0] = schedule[11][0] + students.get(i).getNameAbv();
                }
                if(tempMonTimes.get(j).equals("2100")) {
                    schedule[12][0] = schedule[12][0] + students.get(i).getNameAbv();
                }
            }
            //Tuesday
            for(int j = 0; j < tempTueTimes.size(); j++) {
                if(tempTueTimes.get(j).equals("0900")) {
                    schedule[0][1] = schedule[0][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1000")) {
                    schedule[1][1] = schedule[1][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1100")) {
                    schedule[2][1] = schedule[2][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1200")) {
                    schedule[3][1] = schedule[3][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1300")) {
                    schedule[4][1] = schedule[4][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1400")) {
                    schedule[5][1] = schedule[5][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1500")) {
                    schedule[6][1] = schedule[6][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1600")) {
                    schedule[7][1] = schedule[7][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1700")) {
                    schedule[8][1] = schedule[8][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1800")) {
                    schedule[9][1] = schedule[9][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("1900")) {
                    schedule[10][1] = schedule[10][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("2000")) {
                    schedule[11][1] = schedule[11][1] + students.get(i).getNameAbv();
                }
                if(tempTueTimes.get(j).equals("2100")) {
                    schedule[12][1] = schedule[12][1] + students.get(i).getNameAbv();
                }
            }
            //Wednesday
            for(int j = 0; j < tempWedTimes.size(); j++) {
                if(tempWedTimes.get(j).equals("0900")) {
                    schedule[0][2] = schedule[0][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1000")) {
                    schedule[1][2] = schedule[1][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1100")) {
                    schedule[2][2] = schedule[2][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1200")) {
                    schedule[3][2] = schedule[3][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1300")) {
                    schedule[4][2] = schedule[4][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1400")) {
                    schedule[5][2] = schedule[5][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1500")) {
                    schedule[6][2] = schedule[6][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1600")) {
                    schedule[7][2] = schedule[7][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1700")) {
                    schedule[8][2] = schedule[8][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1800")) {
                    schedule[9][2] = schedule[9][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("1900")) {
                    schedule[10][2] = schedule[10][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("2000")) {
                    schedule[11][2] = schedule[11][2] + students.get(i).getNameAbv();
                }
                if(tempWedTimes.get(j).equals("2100")) {
                    schedule[12][2] = schedule[12][2] + students.get(i).getNameAbv();
                }
            }
            //Thursday
            for(int j = 0; j < tempThuTimes.size(); j++) {
                if(tempThuTimes.get(j).equals("0900")) {
                    schedule[0][3] = schedule[0][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1000")) {
                    schedule[1][3] = schedule[1][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1100")) {
                    schedule[2][3] = schedule[2][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1200")) {
                    schedule[3][3] = schedule[3][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1300")) {
                    schedule[4][3] = schedule[4][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1400")) {
                    schedule[5][3] = schedule[5][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1500")) {
                    schedule[6][3] = schedule[6][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1600")) {
                    schedule[7][3] = schedule[7][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1700")) {
                    schedule[8][3] = schedule[8][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1800")) {
                    schedule[9][3] = schedule[9][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("1900")) {
                    schedule[10][3] = schedule[10][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("2000")) {
                    schedule[11][3] = schedule[11][3] + students.get(i).getNameAbv();
                }
                if(tempThuTimes.get(j).equals("2100")) {
                    schedule[12][3] = schedule[12][3] + students.get(i).getNameAbv();
                }
            }
            //Friday
            for(int j = 0; j < tempFriTimes.size(); j++) {
                if(tempFriTimes.get(j).equals("0900")) {
                    schedule[0][4] = schedule[0][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1000")) {
                    schedule[1][4] = schedule[1][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1100")) {
                    schedule[2][4] = schedule[2][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1200")) {
                    schedule[3][4] = schedule[3][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1300")) {
                    schedule[4][4] = schedule[4][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1400")) {
                    schedule[5][4] = schedule[5][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1500")) {
                    schedule[6][4] = schedule[6][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1600")) {
                    schedule[7][4] = schedule[7][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1700")) {
                    schedule[8][4] = schedule[8][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1800")) {
                    schedule[9][4] = schedule[9][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("1900")) {
                    schedule[10][4] = schedule[10][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("2000")) {
                    schedule[11][4] = schedule[11][4] + students.get(i).getNameAbv();
                }
                if(tempFriTimes.get(j).equals("2100")) {
                    schedule[12][4] = schedule[12][4] + students.get(i).getNameAbv();
                }
            }
        }

        //Printing out the 2D Array
        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[i].length; j++) {
                System.out.print("[" + schedule[i][j] + "] ");
            }
            System.out.println();
        }

        //Finding the best time slot
        int bestTimeIndex = 0;
        int bestDayIndex = 0;
        int temp;
        int max = 0;

        for(int i = 0; i < schedule.length; i++) {
            for(int j = 0; j < schedule[i].length; j++) {
                temp = schedule[i][j].length();
                if(temp > max) {
                    max = temp;
                    bestTimeIndex = i;
                    bestDayIndex = j;
                }
            }
        }

        //Printing out best time slot
        String bestTime = "";
        if(bestTimeIndex == 0) {
            bestTime = "0900";
        }
        if(bestTimeIndex == 1) {
            bestTime = "1000";
        }
        if(bestTimeIndex == 2) {
            bestTime = "1100";
        }
        if(bestTimeIndex == 3) {
            bestTime = "1200";
        }
        if(bestTimeIndex == 4) {
            bestTime = "1300";
        }
        if(bestTimeIndex == 5) {
            bestTime = "1400";
        }
        if(bestTimeIndex == 6) {
            bestTime = "1500";
        }
        if(bestTimeIndex == 7) {
            bestTime = "1600";
        }
        if(bestTimeIndex == 8) {
            bestTime = "1700";
        }
        if(bestTimeIndex == 9) {
            bestTime = "1800";
        }
        if(bestTimeIndex == 10) {
            bestTime = "1900";
        }
        if(bestTimeIndex == 11) {
            bestTime = "2000";
        }
        if(bestTimeIndex == 12) {
            bestTime = "2100";
        }

        String bestDay = "";
        if(bestDayIndex == 0) {
            bestDay = "Monday";
        }
        if(bestDayIndex == 1) {
            bestDay = "Tuesday";
        }
        if(bestDayIndex == 2) {
            bestDay = "Wednesday";
        }
        if(bestDayIndex == 3) {
            bestDay = "Thursday";
        }
        if(bestDayIndex == 4) {
            bestDay = "Friday";
        }

        //Calculating day where most students signed up
        int[] studentSignups = new int[5];
        studentSignups[0] = monAmount;
        studentSignups[1] = tueAmount;
        studentSignups[2] = wedAmount;
        studentSignups[3] = thuAmount;
        studentSignups[4] = friAmount;

        int tempmax = 0;
        int maxIndex = 0;
        for(int i = 0; i < studentSignups.length; i++) {
            if(studentSignups[i] > tempmax) {
                tempmax = studentSignups[i];
                maxIndex = i;
            }
        }
        String popularDay = "";
        if(maxIndex == 0) {
            popularDay = "Monday";
        }
        if(maxIndex == 1) {
            popularDay = "Tuesday";
        }
        if(maxIndex == 2) {
            popularDay = "Wednesday";
        }
        if(maxIndex == 3) {
            popularDay = "Thursday";
        }
        if(maxIndex == 4) {
            popularDay = "Friday";
        }

        //Displaying Output
        System.out.println("------------------------");
        System.out.println("The best timeslot is on " + bestDay + " at " + bestTime);
        System.out.println("The most popular day is " + popularDay);
    }
}
