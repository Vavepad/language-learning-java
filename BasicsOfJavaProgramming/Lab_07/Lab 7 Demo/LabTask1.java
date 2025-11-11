import comp102x.ColorImage;

public class LabTask1 {
    public LabTask1() {
    }

    public void changeScreen(ColorImage image, int x, int y, int width, int height, int stripeSize) {
        if(width >= 2 * stripeSize && height >= 2 * stripeSize) {
            this.animateStripe(image, x++, y, width, stripeSize, "toRight");
            --x;
            this.animateStripe(image, x + width - stripeSize, y + stripeSize, height - stripeSize, stripeSize, "toBottom");
            this.animateStripe(image, x++, y + height - stripeSize, width - stripeSize, stripeSize, "toLeft");
            --x;
            this.animateStripe(image, x, y + stripeSize, height - stripeSize * 2, stripeSize, "toTop");
            this.changeScreen(image, x + stripeSize, y + stripeSize, width - 2 * stripeSize, height - 2 * stripeSize, stripeSize);
        } else {
            this.animateStripe(image, x, y, width, height, "toRight");
        }

    }

    private void animateStripe(ColorImage image, int left, int top, int length, int stripeSize, String direction) {
        long delay = (long)Math.pow(10.0D, 5.5D);
        byte var10 = -1;
        switch(direction.hashCode()) {
            case -1166960543:
                if(direction.equals("toRight")) {
                    var10 = 0;
                }
                break;
            case -869110494:
                if(direction.equals("toLeft")) {
                    var10 = 2;
                }
                break;
            case 110519514:
                if(direction.equals("toTop")) {
                    var10 = 3;
                }
                break;
            case 2026802310:
                if(direction.equals("toBottom")) {
                    var10 = 1;
                }
        }

        int i;
        switch(var10) {
            case 0:
                for(i = 0; i < length; ++i) {
                    image.drawRectangle(left + i, top, 1, stripeSize);
                    this.pauseByTicks(delay);
                }

                return;
            case 1:
                for(i = 0; i < length; ++i) {
                    image.drawRectangle(left, top + i, stripeSize, 1);
                    this.pauseByTicks(delay);
                }

                return;
            case 2:
                for(i = length - 1; i >= 0; --i) {
                    image.drawRectangle(left + i, top, 1, stripeSize);
                    this.pauseByTicks(delay);
                }

                return;
            case 3:
                for(i = length - 1; i >= 0; --i) {
                    image.drawRectangle(left, top + i, stripeSize, 1);
                    this.pauseByTicks(delay);
                }

                return;
            default:
                System.err.println("Invalid direction: " + direction);
                System.err.println("Only \"toRight\", \"toBottom\", \"toLeft\", \"toTop\" are allowed!");
        }
    }

    private void pauseByTicks(long ticks) {
        while(ticks != 0L) {
            --ticks;
        }

    }
}

