package uk.co.brett.images;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.zip.GZIPOutputStream;

import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.awt.WorldWindowGLJPanel;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainController implements Initializable {

	@FXML
	ImageView imageHolder;
	
	@FXML
	Pane wwdPane;

	public void initialize(URL location, ResourceBundle resources) {

		File f = new File("/Users/Sam/Desktop/P1000417.jpg");

		System.out.println(f.isFile());

		try {
			byte[] bytes = FileUtils.readFileToByteArray(f);

			Image image = new Image(new ByteArrayInputStream(bytes));

			imageHolder.setImage(image);


			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    public static byte[] compressStringToGZipByteArray(String data, String encoding) throws Exception {
        if (data == null || data.length() == 0) {
            throw new Exception("data");
        }
        if (encoding == null || encoding.length() == 0) {
            throw new Exception("encoding");
        }
        return byteArrayToGZipByteArray(data.getBytes(encoding));
    }
	
	
    public static byte[] byteArrayToGZipByteArray(byte[] data) throws Exception {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            GZIPOutputStream gzip = new GZIPOutputStream(baos);
            gzip.write(data);

            gzip.close();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new Exception(e.getCause());
        }

    }
	
}
