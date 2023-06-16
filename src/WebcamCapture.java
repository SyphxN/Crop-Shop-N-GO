import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.opencv.imgcodecs.Imgcodecs;

public class WebcamCapture {

    public static void main(String[] args) {
        // Load the OpenCV native library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Initialize the video capture object
        VideoCapture capture = new VideoCapture();

        // Open the default camera (index 0)
        capture.open(0);

        // Check if the camera is opened
        if (capture.isOpened()) {
            // Set the desired image width and height
            int imageWidth = 640;
            int imageHeight = 480;

            // Set the camera properties
            capture.set(Videoio.CAP_PROP_FRAME_WIDTH, imageWidth);
            capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, imageHeight);

            // Create a matrix to hold the captured frame
            Mat frame = new Mat();

            // Capture frames until the user exits
            while (true) {
                // Read a frame from the camera
                capture.read(frame);

                // Display the captured frame (optional)
                // HighGui.imshow("Webcam", frame);
                // HighGui.waitKey(1);

                // Save the frame as an image file
                String fileName = "captured_image.jpg";
                Imgcodecs.imwrite(fileName, frame);

                System.out.println("Image captured and saved as " + fileName);

                // Exit the loop after capturing a single frame
                break;
            }

            // Release the video capture object
            capture.release();
        } else {
            System.out.println("Failed to open the camera");
        }
    }
}


