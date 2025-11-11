import java.util.ListResourceBundle;

public class ProgramResource_en_US extends ListResourceBundle {
    static final Object[][] contents = new Object[][]{{"copyright", ""}, {"description", "Description"}, {"back", "Back"}, {"next", "Next"}, {"restart", "Restart"}, {"convert", "Convert"}, {"clear", "Clear"}, {"voiceDescription", "Voice Description"}, {"inputCodeEditable", "Input Code (Editable)"}, {"simplifiedCodeNonEditable", "      Simplified Code (Non-editable)      "}, {"flowChart", "Flow Chart"}, {"errorConvertEmptyProgram", " Error : Convert Empty Program"}, {"error", " Error : "}, {"parsingError", "Parsing Error"}, {"programStarts", "Program Starts"}, {"programStops", "Program Stops"}, {"statements", "Statements "}, {"isExecuting", " is Executing ..."}, {"condition", "Condition "}, {"trueOrFalse", " -- True or False?"}, {"start", "START"}, {"end", "END"}, {"statements_draw", "Statements "}, {"condition_draw", "Condition "}, {"true", "True"}, {"false", "False"}};

    public Object[][] getContents() {
        return contents;
    }

    public ProgramResource_en_US() {
    }
}
