import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BestMessages extends Remote {
    String getThreeBestMessagesToString() throws RemoteException;
}
