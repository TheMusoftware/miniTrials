import Manager.RadioManager;
import Memberships.MGold;
import Memberships.MNormal;
import Memberships.MVip;
import Memberships.MVipPlus;

import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException,  InterruptedException {
        RadioManager radioManager = new RadioManager();
        MNormal normal = new MNormal("Sude");
        MGold gold = new MGold("Ahmet");
        MVip vip = new MVip("Mustafa");
        MVipPlus vipPlus = new MVipPlus("irem");
        Thread music = new Thread(radioManager);
        Thread user = new Thread(vipPlus);
        user.setName("User");
        music.setName("Music");
       music.start();
       Thread.sleep(100);
       user.start();
    }


    /*
    Bugs
    the thread that called User stop command does not work because the scanner is running so scanner blocking to interrupt  thread
     */
}