package finalproject;
import java.util.TimerTask;

public class ProRunTimerTask extends TimerTask{
    
    private Statement st;
    
    public ProRunTimerTask(Statement st){
        this.st = st;
    }
    
    @Override
    public void run() {
        st.run();
    }
    
    
}
