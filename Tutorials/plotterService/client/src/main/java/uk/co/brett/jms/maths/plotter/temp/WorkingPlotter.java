package uk.co.brett.jms.maths.plotter.temp;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
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
public class WorkingPlotter extends Application {
	private static final int MAX_DATA_POINTS = 20;

	private Series<Number, Number> series;
	private int xSeriesData = 0;
	private ConcurrentLinkedQueue<Number> dataQ = new ConcurrentLinkedQueue<Number>();
	private ExecutorService executor;
	private AddToQueue addToQueue;
	
	private NumberAxis xAxis;
	private MyTimer timer = new MyTimer();

	private void init(Stage primaryStage) {
		xAxis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS / 10);
		xAxis.setForceZeroInRange(true);
		xAxis.setAutoRanging(true);

		NumberAxis yAxis = new NumberAxis();
		yAxis.setAutoRanging(true);

		// -- Chart
		final AreaChart<Number, Number> sc = new AreaChart<Number, Number>(xAxis, yAxis) {
			// Override to remove symbols on each data point
			@Override
			protected void dataItemAdded(Series<Number, Number> series, int itemIndex, Data<Number, Number> item) {
			}
		};
		sc.setAnimated(false);
		sc.setId("liveAreaChart");
		sc.setTitle("Animated Area Chart");

		// -- Chart Series
		series = new AreaChart.Series<Number, Number>();
		series.setName("Area Chart Series");
		sc.getData().add(series);

		primaryStage.setScene(new Scene(sc));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		init(primaryStage);
		primaryStage.show();

		// -- Prepare Executor Services
		executor = Executors.newCachedThreadPool(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setDaemon(true);
				return thread;
			}
		});

		addToQueue = new AddToQueue();
		executor.execute(addToQueue);
		// -- Prepare Timeline
		//prepareTimeline();
		timer.start();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	private class AddToQueue implements Runnable {
		public void run() {
			try {
				// add a item of random data to queue
				dataQ.add(Math.random());
				Thread.sleep(100);
				executor.execute(this);
			} catch (InterruptedException ex) {
				Logger.getLogger(WorkingPlotter.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}


	private void addDataToSeries() {
		for (int i = 0; i < 20; i++) { // -- add 20 numbers to the plot+
			if (dataQ.isEmpty())
				break;
			series.getData().add(new Data<Number, Number>(xSeriesData++, dataQ.remove()));

			System.out.println(series.getData().size());

		}

		if (series.getData().size() > 50) {
			timer.stop();
		}

	}

	public class MyTimer extends AnimationTimer {

		@Override
		public void handle(long now) {
			addDataToSeries();
		}

	}

}
