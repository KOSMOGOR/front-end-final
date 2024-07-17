package com.example.demo.Services;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Utils {

    public static String getSkill(String title) {
        String[] titleSp = title.split(" ");
        return Arrays.stream(titleSp).filter(path -> !(path.toLowerCase().contains("middle") || path.toLowerCase().contains("junior") || path.toLowerCase().contains("senior"))).findFirst().orElseThrow();
    }

    public static String getLevelString(String title) {
        String[] splitTitle = title.split(" ");
        for (String titlePath : splitTitle) {
            switch (titlePath.toLowerCase()) {
                case "middle":
                    return "middle";
                case "senior":
                    return "senior";
                case "junior":
                    return "junior";
                default:
                    break;
            }
        }
        return "";
    }
    public static int getLevelInt(String levelSting) {
        return switch (levelSting) {
            case "junior" -> 1;
            case "middle" -> 2;
            case "senior" -> 3;
            default -> 0;
        };
    }
}
