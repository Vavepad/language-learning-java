import comp102x.ColorImage;
import comp102x.Canvas; 

/**
 * The Choice class represents a choice made by the player or the computer.
 * It can be either "rock", "paper", or "scissors".
 */
public class Choice
{
    private int type; //stores the choice type: 0=rock, 1=paper, 2=scissors
    private ColorImage choiceImage; //stores the image to be displayed on the canvas
    
    /**
     * The constructor
     * 
     * @param   type   the choice type to be represented by this Choice object
     */
    public Choice(int type)
    {
        //initialize the "type" instance varialble
        this.type = type;
    }
    
    /**
     * Get a number that represents the choice type
     * 
     * @return  a number that represents the choice type: 0=rock, 1=paper, 2=scissors
     */
    public int getType()
    {
        return type;
    }
    
    /**
     * Compare "this" with anotherChoice
     * 
     * @param   anotherChoice   the choice to be compared
     * @return  either 1, -1, or 0 which indicates the comparison result: 1 means "this" wins anotherChoice; -1 means "this" loses to anotherChoice; 0 means "this" and anotherChoice are the same
     */
    public int compareWith(Choice anotherChoice)
    {        
        if(this.type==anotherChoice.getType()) //draw if the types are the same
        {
            return 0; //draw
        }
        else if(this.type==0 && anotherChoice.getType()==1) //rock vs paper
        {
            return -1; //lose
        }
        else if(this.type==1 && anotherChoice.getType()==2) //paper vs scissors
        {
            return -1; //lose
        }
        else if(this.type==2 && anotherChoice.getType()==0) //scissors vs rock
        {
            return -1; //lose
        }
        else //the remaining cases are all "winning cases", including paper vs rock, scissors vs paper and rock vs scissors
        {
            return 1; //win
        }
        
        /*
         * A shorter solution. Notice that the difference of the type instance variables 
         * of this Choice instace and the anotherChoice Choice instance represents 
         * the comparison result except the following two cases:
         * 
         * 1. this Choice is rock and anotherChoice is scissors. The difference is -2.
         * 2. this Choice is scissors and anotherChoice is rock. The difference is 2.
         * 
         * Therefore, we can handle these two cases separately and return the difference for other cases.
         */
        
        /*
        int difference = this.type - anotherChoice.getType();
        if (difference == -2) return 1;
        else if (difference == 2) return -1;
        else return difference;
        */
    }
    
    /**
     * Draw the choice image (rock/paper/scissors) on the given canvas
     * 
     * @param   canvas      the canvas to draw on
     * @param   x           the x-position of the choice image to be drawn
     * @param   y           the y-position of the choice image to be drawn
     * @param   rotation    the rotation of the choice image to be drawn
     */ 
    public void draw(Canvas canvas, int x, int y, int rotation)
    {
        // initialize the choiceImage variable according to the current type
        // notice that the variable is ALREADY declared above
        
        switch(type)
        {
            case 0:
                choiceImage = new ColorImage("rock.png");
                break;
            case 1:
                choiceImage = new ColorImage("paper.png");
                break;
            case 2:
                choiceImage = new ColorImage("scissors.png");
                break;
        }
        
        
        // you can also use an if-else-if statement to initialize choiceImage
        /*
        if (type == 0) {
            
            choiceImage = new ColorImage("rock.png");
            
        } else if (type == 1) {
            
            choiceImage = new ColorImage("paper.png");
            
        } else {
            
            choiceImage = new ColorImage("scissors.png");
            
        }
        */
            
        
        // set x position
        choiceImage.setX(x);
        
        // set y position
        choiceImage.setY(y);
        
        //rotate it
        choiceImage.setRotation(rotation);

        //show it on the canvas
        canvas.add(choiceImage);
        
        
        // alternative for the four lines of code above
        //choiceImage.setRotation(rotation);
        //canvas.add(choiceImage, x, y);
        
    }
}
