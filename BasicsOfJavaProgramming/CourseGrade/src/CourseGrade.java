import comp102x.IO;

public class CourseGrade
{
    public static void main(String[] args)
    {
        final int examWeight = 70;    // Percentage weight given to examination
        final int labWeight = 20;     // Percentage weight given to lab work
        final int hwWeight = 10;      // Percentage weight given to homework assignment
        double examScore;       // Examination score obtained by student
        double labScore;        // Lab score obtained by student
        double hwScore;         // Homework score obtained by student
        double finalGrade;      // Final grade obtained by student

        // Ask student to input scores for exam, lab and homework
        IO.output("Enter your exam grade: ");
        examScore = IO.inputDouble( );
        IO.outputln("Your examScore is " + examScore);
        IO.output("Enter your lab grade: ");
        labScore = IO.inputDouble( );
        IO.outputln("Your labScore is " + labScore);
        IO.output("Enter your homework grade: ");
        hwScore = IO.inputDouble( );
        IO.outputln("Your hwScore is " + hwScore);

        // Computer final grade as the weighted sum of exam, lab and homework scores
        examScore = examScore * ((double) examWeight / 100);
        IO.outputln("Your examScore grade is " + examScore);
        labScore = labScore * ((double) labWeight / 100);
        IO.outputln("Your labScore grade is " + labScore);
        hwScore = hwScore * ((double) hwWeight / 100);
        IO.outputln("Your hwScore grade is " + hwScore);
        finalGrade = examScore + labScore + hwScore;

        // Output the final grade
        IO.outputln("Your final grade is " + finalGrade);

    }
}
