package comp102x.project.task;

import comp102x.project.model.GameRecord;

public class RecordManager {
    public RecordManager() {
    }

    public GameRecord[] updateGameRecords(GameRecord[] oldRecords, GameRecord newRecord) {
        boolean recordExist = false;
        new GameRecord(newRecord.getName(), newRecord.getLevel(), newRecord.getScore());
        int existingWorseRecordIndex = -1;
        int sameLevelCount = 0;
        int sameLevelWorstRecordIndex = -1;
        String newName = newRecord.getName();
        int newLevel = newRecord.getLevel();
        int newScore = newRecord.getScore();

        for(int i = 0; i < oldRecords.length; ++i) {
            String currentName = oldRecords[i].getName();
            int currentLevel = oldRecords[i].getLevel();
            int currentScore = oldRecords[i].getScore();
            if(currentName.equals(newName) && currentLevel == newLevel) {
                recordExist = true;
                if(currentScore < newScore) {
                    existingWorseRecordIndex = i;
                }
            }

            if(currentLevel == newLevel) {
                ++sameLevelCount;
                if(currentScore < newScore) {
                    sameLevelWorstRecordIndex = i;
                }
            }
        }

        if(recordExist) {
            if(existingWorseRecordIndex != -1) {
                oldRecords[existingWorseRecordIndex] = newRecord;
                this.sort(oldRecords);
                return oldRecords;
            } else {
                return oldRecords;
            }
        } else if(sameLevelCount >= 10) {
            if(sameLevelWorstRecordIndex != -1) {
                oldRecords[sameLevelWorstRecordIndex] = newRecord;
                this.sort(oldRecords);
                return oldRecords;
            } else {
                return oldRecords;
            }
        } else {
            GameRecord[] newRecords = new GameRecord[oldRecords.length + 1];

            int i;
            for(i = 0; i < oldRecords.length; ++i) {
                newRecords[i] = oldRecords[i];
            }

            newRecords[i] = newRecord;
            this.sort(newRecords);
            return newRecords;
        }
    }

    private void sort(GameRecord[] records) {
        Util.sort(records);
    }
}
