import java.util.InputMismatchException;
import java.util.Scanner;

public class SVG_3012_Docker_Pipeline {

    public static void validateInputs(double speed, double distance) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative: " + speed);
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative: " + distance);
        }
    }


    public static double timeCal(double speed, double distance) {
        validateInputs(speed, distance);
        if (distance == 0 || speed ==0) {
            return 0;
        }
        return distance / speed;
    }


    public static String buildReport(double speed, double distance){
        double time= timeCal(speed,distance);
       return "distance: "+distance+" ,time: "+time+ ", speed: "+speed ;
    }
 // test the functionality of the functions
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Please enter the speed:");
            double speed = sc.nextDouble();
            System.out.println("Please enter the distance:");
            double distance = sc.nextDouble();

            System.out.println(buildReport(speed, distance));
        } catch (InputMismatchException e) {
            System.out.println("Error: please enter numeric values only.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }

    }


}
