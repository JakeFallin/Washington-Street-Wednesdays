package com.jakefallin.wsw;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HoursData {

    public static HashMap<String, List<String>> getData(int i) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> bennys = new ArrayList<String>();
        bennys.add("Monday: 11AM - 12AM");
        bennys.add("Tuesday: 11AM - 12AM");
        bennys.add("Wednesday: 11AM - 12AM");
        bennys.add("Thursday: 11AM - 12AM");
        bennys.add("Friday: 11AM - 12AM");
        bennys.add("Saturday: 11AM - 12AM");
        bennys.add("Sunday: 11AM - 12AM");

        List<String> boardwalk = new ArrayList<String>();
        boardwalk.add("Monday: 11AM - 10PM");
        boardwalk.add("Tuesday: 11AM - 10PM");
        boardwalk.add("Wednesday: 11AM - 10PM");
        boardwalk.add("Thursday: 11AM - 10AM");
        boardwalk.add("Friday: 11AM - 12AM");
        boardwalk.add("Saturday: 11AM - 12AM");
        boardwalk.add("Sunday: 11AM - 10PM");

        List<String> stacks = new ArrayList<String>();
        stacks.add("Monday: 6AM - 10PM");
        stacks.add("Tuesday: 6AM - 10PM");
        stacks.add("Wednesday: 6AM - 10PM");
        stacks.add("Thursday: 6AM - 4AM");
        stacks.add("Friday: 6AM - 4AM");
        stacks.add("Saturday: 6AM - 4AM");
        stacks.add("Sunday: 6AM - 10PM");

        List<String> flatbread = new ArrayList<String>();
        flatbread.add("Monday: 11:30AM - 10PM");
        flatbread.add("Tuesday: 11:30AM - 10PM");
        flatbread.add("Wednesday: 11:30AM - 10PM");
        flatbread.add("Thursday: 11:30AM - 10PM");
        flatbread.add("Friday: 11:30AM - 10PM");
        flatbread.add("Saturday: 11:30AM - 10PM");
        flatbread.add("Sunday: 11:30AM - 10PM");

        List<String> hansel = new ArrayList<String>();
        hansel.add("Monday: 8AM - 10PM");
        hansel.add("Tuesday: 8AM - 10PM");
        hansel.add("Wednesday: 8AM - 10PM");
        hansel.add("Thursday: 8AM - 10PM");
        hansel.add("Friday: 8AM - 3AM");
        hansel.add("Saturday: 8AM - 3AM");
        hansel.add("Sunday: 8AM - 10PM");

        switch(i) {
            case(0):
                expandableListDetail.put("Hours", bennys);
                break;
            case(1):
                expandableListDetail.put("Hours", boardwalk);
                break;
            case(2):
                expandableListDetail.put("Hours", flatbread);
                break;
            case(3):
                expandableListDetail.put("Hours", hansel);
                break;
            case(4):
                expandableListDetail.put("Hours", stacks);
                break;
        }

        return expandableListDetail;
    }
}