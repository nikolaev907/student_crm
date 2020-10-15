package utils;

import database.DisciplineDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AverageMarkUtils {
    public double getAverageMark(Map<String, Object> data) {
        List marksId = (ArrayList) data.get("marksId");
        String marksIdStr = (String) marksId.stream().map(Object::toString).collect(Collectors.joining(", "));
        double averageGradeByIdMark = DisciplineDB.getAverageGradeByIdMark(marksIdStr);
        return Math.round(averageGradeByIdMark * 100.0) / 100.0;
    }
}
