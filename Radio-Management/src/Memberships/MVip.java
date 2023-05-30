package Memberships;
import java.io.IOException;
import java.util.Date;

public class MVip extends BaseMembership{
    public MVip(String name) {
        setName(name);
        registirationDate = new Date();
        setRegDate(sdf.format(registirationDate));
        setCanDoAddComment(true);
        setRemainingRequest(3);
        setMemberShip("Vip");
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
