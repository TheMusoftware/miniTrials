package Memberships;

import java.io.IOException;
import java.util.Date;

public class MNormal extends BaseMembership {
    public MNormal(String name){
    setName(name);
    registirationDate = new Date();
    setRegDate(sdf.format(registirationDate));
    setCanDoAddComment(false);
    setRemainingRequest(0);
    setMemberShip("Normal");
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                getMenu();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
