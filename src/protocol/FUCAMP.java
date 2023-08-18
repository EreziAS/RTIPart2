package protocol;

import db.bean.ActivityBean;

public class FUCAMP {
    private String[] _requestArray;
    private ActivityBean _bean;

    public FUCAMP() {

    }

    public void Processing(String[] request, ActivityBean bean){
        //Split the request into an array delimiter is ";"
        switch (request[2]){
            case "GETALL":
                //Display all the activities
                System.out.println("COMMAND GETALL");
                break;
            case "GETBYID":
                //Display the activity with the given id
                System.out.println("GETBYID");
                break;
            case "BOOK":
                //Add an activity
                System.out.println("BOOK");
                break;
            default:
                System.out.println("Error: Invalid request");
                break;
        }
    }

    public void Display(){
        for (int i = 0; i < _requestArray.length; i++) {
            System.out.println(_requestArray[i]);
        }
    }
}
