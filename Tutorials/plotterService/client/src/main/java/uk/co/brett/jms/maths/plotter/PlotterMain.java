package uk.co.brett.jms.maths.plotter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

/**
 * A chart that fills in the area between a line of data points and the axes.
 * Good for comparing accumulated totals over time.
 * 
* @see javafx.scene.chart.Chart
 * @see javafx.scene.chart.Axis
 * @see javafx.scene.chart.NumberAxis
 * @related charts/line/LineChart
 * @related charts/scatter/ScatterChart
 */
public class PlotterMain extends Application {
    private static final int MAX_DATA_POINTS = 10;
    private XYChart.Series series = new XYChart.Series();
    //private Series series;
    private int xSeriesData = 0;
    private ConcurrentLinkedQueue<Number> dataQX = new ConcurrentLinkedQueue<Number>();
    private ConcurrentLinkedQueue<Number> dataQY = new ConcurrentLinkedQueue<Number>();
    private ExecutorService executor;
    private AddToQueue addToQueue;
    //private Timeline timeline2;
    private NumberAxis xAxis;

    private void init(Stage primaryStage) {
        xAxis = new NumberAxis(0,MAX_DATA_POINTS,MAX_DATA_POINTS/10);
        xAxis.setForceZeroInRange(true);
        xAxis.setAutoRanging(true);
        
        NumberAxis yAxis = new NumberAxis();
        yAxis.setAutoRanging(true);

        //-- Chart
        final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis) {
            // Override to remove symbols on each data point
            @Override protected void dataItemAdded(Series<Number, Number> series, int itemIndex, Data<Number, Number> item) {}
        };
        sc.setAnimated(false);
        sc.setId("liveAreaChart");
        sc.setTitle("Animated Area Chart");

        //-- Chart Series
        series = new ScatterChart.Series<Number, Number>();
        series.setName("Area Chart Series");
        sc.getData().add(series);
        

        primaryStage.setScene(new Scene(sc));
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();

        //-- Prepare Executor Services
        executor = Executors.newCachedThreadPool();
        addToQueue = new AddToQueue();
        executor.execute(addToQueue);
        //-- Prepare Timeline
        prepareTimeline();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class AddToQueue implements Runnable {
        public void run() {
            try {
                // add a item of random data to queue
                dataQX.add(Math.random());
                dataQY.add(Math.random());
                Thread.sleep(500);
                executor.execute(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(PlotterMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //-- Timeline gets called in the JavaFX Main thread
    private void prepareTimeline() {
        // Every frame to take any data from queue and add to chart
        new AnimationTimer() {
            @Override public void handle(long now) {
                addDataToSeries();
            }
        }.start();
    }

    private void addDataToSeries() {
        for (int i = 0; i < 20; i++) { //-- add 20 numbers to the plot+
            if (dataQX.isEmpty()) break;
            series.getData().add(new XYChart.Data(dataQX.remove(), dataQY.remove()));
        }
        // remove points to keep us at no more than MAX_DATA_POINTS
//        if (series.getData().size() > MAX_DATA_POINTS) {
//            series.getData().remove(0, series.getData().size() - MAX_DATA_POINTS);
//        }
//        // update 
//        xAxis.setLowerBound(xSeriesData-MAX_DATA_POINTS);
//        xAxis.setUpperBound(xSeriesData-1);
    }
}



//
//import java.util.ArrayList;
//import java.util.Random;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.ScatterChart;
//import javafx.scene.chart.XYChart;
//import javafx.stage.Stage;
//
//public class PlotterMain extends Application {
//
//	@Override
//	public void start(Stage stage) throws InterruptedException {
//		stage.setTitle("Scatter Chart Sample");
//		final NumberAxis xAxis = new NumberAxis(0, 10, 1);
//		final NumberAxis yAxis = new NumberAxis(0, 10, 1);
//		final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);
//		xAxis.setLabel("Age (years)");
//		yAxis.setLabel("Returns to date");
//		sc.setTitle("Investment Overview");
//
//		Scene scene = new Scene(sc, 500, 400);
//		stage.setScene(scene);
//		stage.show();
//
//		Random rnd = new Random();
//		ArrayList<Point> data = new ArrayList<>();
//
//		for (int i = 0; i < 10; i++) {
//			XYChart.Series series1 = new XYChart.Series();
//			series1.setName("Equities");
//
//			Thread.sleep(500);
//
//			Point p = new Point();
//			p.x = rnd.nextDouble() * 10d;
//			p.y = rnd.nextDouble() * 10d;
//
//			data.add(p);
//			for (Point point : data) {
//				series1.getData().add(new XYChart.Data(point.x, point.y));
//			}
//			sc.getData().removeAll();
//			sc.getData().add(series1);
//
//			stage.setScene(scene);
//			stage.show();
//			
//		}
//
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	private class Point {
//		double x, y;
//
//	}
//
//}
