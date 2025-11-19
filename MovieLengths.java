
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieLengths {
    public static void main(String[] args) throws FileNotFoundException {
        idealMovieLength("/Users/echeng/Desktop/HCSS/CS_Seminar/top_100_movies_full_best_effort.csv");    
    }

    //Question 1: What is the ideal movie length? (Short - less than 90, medium 90-110, or log over 110)
    public static void idealMovieLength(String pathname) throws FileNotFoundException {
        File f = new File(pathname);
        Scanner sc = new Scanner(f);

        //find index of needed values from dataset
        String[] header = sc.nextLine().split(",");
        int len_idx = 9;
        int rate_idx = 7;

        //3 kinds, long, medium, and short
        ArrayList<Double> less = new ArrayList<>();
        ArrayList<Double> med = new ArrayList<>();
        ArrayList<Double> more = new ArrayList<>();

        //seperate movies by length
        while(sc.hasNextLine()) {
            ArrayList<String> line = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));
            if (Integer.parseInt(line.get(len_idx)) < 90) {
                less.add(Double.parseDouble(line.get(rate_idx)));
            } else if ((Integer.parseInt(line.get(len_idx)) >= 90) && (Integer.parseInt(line.get(len_idx)) <= 110)) {
                med.add(Double.parseDouble(line.get(rate_idx)));
            } else  {
                more.add(Double.parseDouble(line.get(rate_idx)));
            }
        }

        //add numbers for average
        double shortAvg = 0;
        for(Double s: less) {
            shortAvg +=s;
        }

        double medAvg = 0;
        for(Double m: med) {
            medAvg +=m;
        }

        double longAvg = 0;
        for(Double l: more) {
            longAvg +=l;
        }
        
        //divide to get average (in case it is empty, make it automatically 0)
        if(less.isEmpty()){
            shortAvg = 0;
        } else if (med.isEmpty()) {
            medAvg = 0;
        } else if (more.isEmpty()) {
            longAvg = 0;
        } else {
            shortAvg = (shortAvg/less.size());
            medAvg = (medAvg/med.size());
            longAvg = (longAvg/more.size());
        }

        //print out
        System.out.println(String.format("Short - Average Rating: %.2f", shortAvg));
        System.out.println(String.format("Med - Average Rating %.2f", medAvg));
        System.out.println(String.format("Long - Average Rating %.2f", longAvg));
        if (longAvg > shortAvg && longAvg > medAvg) {
            System.out.println("Long movies (over 110 mins) are the most successful");
        } else if (medAvg > longAvg && medAvg > shortAvg) {
            System.out.println("Medium movies (between 90 to 110 mins) are the most successful");
        } else if (shortAvg > longAvg && shortAvg > medAvg) {
            System.out.println("Short movies (less than 90 mins) are the most successful");
        }

    }
}
