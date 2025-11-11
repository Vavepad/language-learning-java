public class Loader {
    public Loader() {
    }

    static {
        String architecture = System.getProperty("sun.arch.data.model");
        if(architecture.equals("32")) {
            try {
                System.loadLibrary("32bit/liblept168");
            } catch (UnsatisfiedLinkError var4) {
                if(!var4.getMessage().contains("already loaded")) {
                    throw var4;
                }
            }

            try {
                System.loadLibrary("32bit/libtesseract302");
            } catch (UnsatisfiedLinkError var2) {
                if(!var2.getMessage().contains("already loaded")) {
                    throw var2;
                }
            }
        } else if(architecture.equals("64")) {
            try {
                System.loadLibrary("64bit/liblept168");
            } catch (UnsatisfiedLinkError var5) {
                if(!var5.getMessage().contains("already loaded")) {
                    throw var5;
                }
            }

            try {
                System.loadLibrary("64bit/libtesseract302");
            } catch (UnsatisfiedLinkError var3) {
                if(!var3.getMessage().contains("already loaded")) {
                    throw var3;
                }
            }
        } else {
            System.err.println("Unknown Architecture. OCR not supported");
        }

    }
}
