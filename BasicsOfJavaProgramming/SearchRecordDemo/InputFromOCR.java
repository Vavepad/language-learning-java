import comp102x.IO;

public class InputFromOCR {
    private static Loader loader = new Loader();// for loading OCR libraries

    public void readFromOCR() {
    // Input from image by performing Optical Character Recognition(OCR)
    String text = IO.inputTextImage();
    IO.outputln(text);

}

    public static void main(String args[])
    {
        InputFromOCR ocr = new InputFromOCR();
        ocr.readFromOCR();
    }
    }