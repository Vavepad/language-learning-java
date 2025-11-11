package comp102x.project.task;

import comp102x.project.model.Target;
import java.util.Random;

public class TargetUpdater {
    public TargetUpdater() {
    }

    public void updateTarget(Target[] targets, int level) {
        Random random = new Random();
        int numberOfSwaps = 0;
        if(level == 1) {
            numberOfSwaps = 0;
        } else if(level == 2) {
            numberOfSwaps = level * 2;
        } else if(level == 3) {
            numberOfSwaps = 10;
        }

        for(int i = 0; i < numberOfSwaps; ++i) {
            int index1 = random.nextInt(targets.length);
            int index2 = random.nextInt(targets.length);
            if(targets[index1].isHit() && !targets[index2].isHit() || !targets[index1].isHit() && targets[index2].isHit()) {
                int tempx = targets[index1].getX();
                int tempy = targets[index1].getY();
                int tempz = targets[index1].getZ();
                targets[index1].setX(targets[index2].getX());
                targets[index1].setY(targets[index2].getY());
                targets[index1].setZ(targets[index2].getZ());
                targets[index2].setX(tempx);
                targets[index2].setY(tempy);
                targets[index2].setZ(tempz);
            }
        }

        numberOfSwaps = 0;
    }
}
