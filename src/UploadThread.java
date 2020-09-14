import com.dropbox.core.v2.DbxClientV2;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadThread extends Thread {


    private DbxClientV2 client;
    private InputStream in;

    public UploadThread(DbxClientV2 client, InputStream in) {
        this.client = client;
        this.in = in;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    Date now = new Date();

    @Override
    public void run() {

        try {

            client.files().uploadBuilder("/" + dateFormat.format(now) + ".png").uploadAndFinish(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
