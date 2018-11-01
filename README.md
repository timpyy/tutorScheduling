# tutorScheduling
A Java program that figures out the best time to hold tutoring sessions according to student input. Students can indicate the day and times they can attend tutoring, and the program will utilize stacks and 2D arrays to figure out the optimal time to hold a tutoring session.

The input data set will be lines of student preferences for tutoring hours that give the Student’s ID, Day and Time period. Special delimiters are included in the data:
( )  are the symbols surrounding student data
< > are the symbols surrounding day of the week data
[ ]  are the symbols surrounding the time period.
For example:
(A Smith <M [0900][1000][1100]> <H [1400] > ) ( S Jones <T [2100][1800]>)
 means A Smith can go to tutoring on Mondays from 9-11, and Thursday 2-3, and S Jones can go on Tuesday from 6-7 and 9-10. Note the period order is  not significant.  There are no implied ranges. In this example the student can go from 9AM to 11AM, yet 9,10, and 11 are all provided. All times will end in 00.
Assume the only tutoring days are M,T,W,H,F  and the hours are from 0900 to 2100, hence the last hour is 2100 (9PM – 10 PM)
