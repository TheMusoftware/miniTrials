package Memberships;

import Datas.CurrentPlaying;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import Manager.*;

public  abstract class BaseMembership implements Runnable{
    Scanner scan = new Scanner(System.in);
    public SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private final String  allSongsPath = "src/datas/AllSongs.txt";
    private String name = null;
    private String regDate = null;
    private String memberShip = null;
    private int remainingRequest = 0;
    private boolean canDoAddComment = false;
    private static boolean requestInQueue = false;
    private boolean  isLiked = false;
    Date registirationDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getRemainingRequest() {
        return remainingRequest;
    }

    protected void setRemainingRequest(int remainingRequest) {
        this.remainingRequest = remainingRequest;
    }

    protected void setCanDoAddComment(boolean canDoAddComment) {
        this.canDoAddComment = canDoAddComment;
    }

    public String getMemberShip() {
        return memberShip;
    }

    protected void setMemberShip(String memberShip) {
        this.memberShip = memberShip;
    }

    private int like() throws IOException {
       int cLike = CurrentPlaying.getTotalLike();
       CurrentPlaying.setTotalLike(++cLike);
       CurrentPlaying.updateAllSongstxt();
        isLiked = true;
        return cLike;
    }
    private int diskLike() throws IOException{
        int cLike = CurrentPlaying.getTotalLike();
        CurrentPlaying.setTotalLike(--cLike);
        CurrentPlaying.updateAllSongstxt();
        isLiked = false;
        return cLike;
    }
    private String addComment() throws IOException {
        scan.nextLine();
            System.out.print("Enter your comment: ");
            String comment = scan.nextLine();
            String songName = CurrentPlaying.getSongName().toLowerCase();
            songName = songName.replace(" ","");
            String filePath = "src/datas/Comments/"+songName+".txt";
            File file = new File(filePath);
            if(!file.exists()) file.createNewFile();
            FileWriter fileWriter = new FileWriter(filePath,true);
            fileWriter.write(name+": "+comment+"\n");
            fileWriter.close();
            return "Your comment successfully added!";

    }
    private int reqSong() throws IOException {
        scan.nextLine();
            System.out.println("Enter your request: ");
            String requested = scan.nextLine();
            for(String songInfo : RadioManager.allSongs){
                String [] infos = songInfo.split("-");
                if(infos[0].toLowerCase().equals(requested)){
                    remainingRequest--;
                    RadioManager.setQueue(infos);
                    requestInQueue = true;
                    return  remainingRequest;
                }
            }
            System.out.println(requested+" the song with the name was not found");

        return remainingRequest;
    }
    protected void getMenu() throws IOException {
        int i = 1;
        String like ="Like";
        String disLike = "Dislike";
        if(!isLiked)System.out.printf("\n %d-%s ",i++,like);
        else System.out.printf("\n %d-%s ",i++,disLike);
        if(canDoAddComment) System.out.printf("\t\t%d-Comments ",i++);
        if(remainingRequest > 0 && !requestInQueue) System.out.printf("\t\t%d-Request a song\n",i++);
        else if(requestInQueue) System.out.println("\nYour request in queue. It will be play soon...");
        System.out.println();
        int selection = scan.nextInt();
        switch (selection){
            case 1:
                if(isLiked) diskLike();
                else  like();
                break;
            case 2:
                addComment();
                break;
            case 3:
                reqSong();
                break;
            default:
                break;
        }
        CurrentPlaying.printSongInfo();
    }

    public static void setRequestInQueue(boolean b) {
        requestInQueue = b;
    }

    @Override
    public String toString(){
        return "Name: " + name+"\nRegistration Date: "+regDate+"\n Membership Status: "+memberShip+"\n";
    }
}
