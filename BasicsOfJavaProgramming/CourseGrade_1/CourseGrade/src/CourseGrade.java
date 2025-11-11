import comp102x.IO;

public class CourseGrade
{
    private static final int examWeight = 70; // Percentage weight given to examination
    private static final int labWeight = 20; // Percentage weight given to lab work
    private static final int hwWeight = 10; // Percentage weight given to homework assignment
    private double examScore; // Examination score obtained by student
    private double labScore; // Lab score obtained by student
    private double hwScore; // Homework score obtained by student
    private double finalGrade; // Final grade obtained by student
    private String studentName; // Name of a particular student

    public CourseGrade(String name) {
        studentName = name;
    }

    /**
     * Method getScores obtains all scores for a student
     */
    public void getScores() {
        IO.output("Enter your exam grade: ");
        examScore = IO.inputDouble( );
        IO.output("Enter your lab grade: ");
        labScore = IO.inputDouble( );
        IO.output("Enter your homework grade: ");
        hwScore = IO.inputDouble( );
    }

    /**
     * Compute final grade as the weighted sum of exam, lab and homework scores
     *
     * @param examScore Exam score of student
     * @param labScore Lab score of student
     * @param hwScore Homework score of student
     * @return Weighted sum of examScore, labScore and hwScore in double type
     */
    public double computeGrade(double examScore, double labScore, double hwScore)
    {
        examScore = examScore * (examWeight / 100.0);
        labScore = labScore * (labWeight / 100.0);
        hwScore = hwScore * (hwWeight / 100.0);
        return examScore + labScore + hwScore;
    }

    /**
     * Set the finalGrade by calling computeGrade
     */
    public void setFinalGrade(){
        finalGrade = computeGrade(examScore, labScore, hwScore);
    }

    public void outputResult(){
        IO.outputln("For " + studentName + ": examScore = " + examScore +
                " labScore = " + labScore + " hwScore = " + hwScore +
                " finalGrade = " + finalGrade);
    }

    public static void main(String[] args)
    {
        CourseGrade cg = new CourseGrade("student");
        cg.getScores();

        cg.setFinalGrade();
        cg.outputResult();

    }
}
