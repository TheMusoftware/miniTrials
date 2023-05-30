package Memberships;

import java.io.IOException;
import java.util.Date;

public class MGold extends BaseMembership{
    public MGold(String name){
        setName(name);
        registirationDate = new Date();
        setRegDate(sdf.format(registirationDate));
        setCanDoAddComment(true);
        setRemainingRequest(0);
        setMemberShip("Gold");
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
