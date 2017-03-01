package uk.co.brett.jms.maths.plotter.temp;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class XYPlotter extends Application {
	private AddToQueue addToQueue;
	private Series<Number, Number> seriesb;
	private MyTimer timer = new MyTimer();
	private ConcurrentLinkedQueue<Point> data = new ConcurrentLinkedQueue<Point>();
	private ExecutorService executor;
	
	private static final int MAX_POINTS=500;
	private static final int MAX_DELAY=100; //ms
	private static final int MAX_X = 20;
	
	public static void main(String[] args) {
		launch(args);
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
		timer.start();

	}
	
	@SuppressWarnings("unchecked")
	public void init(Stage stage) {
		stage.setTitle("Live Plotter");
		final NumberAxis xAxis = new NumberAxis(0, 20, 1);
		final NumberAxis yAxis = new NumberAxis(-10, 10, 0.1);
		final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);
		xAxis.setLabel("x");
		yAxis.setLabel("y(x)");
		sc.setTitle("Sine Wave with Decay");
		xAxis.setForceZeroInRange(true);
		xAxis.setAutoRanging(true);
		
		xAxis.setLowerBound(0);
	    xAxis.setUpperBound(22);
		
		yAxis.setAutoRanging(true);
		yAxis.setLowerBound(-12);
	    yAxis.setUpperBound(12);
	    
		seriesb = new XYChart.Series<Number, Number>();

		sc.getData().addAll(seriesb);
		Scene scene = new Scene(sc, 500, 400);
		stage.setScene(scene);
		stage.show();
	}

	private double function(double x) {
		return 10 * Math.sin(2*x) * Math.exp(-0.1 * x);
	}

	private void addDataToSeries() {
		for (int i = 0; i < 20; i++) { // -- add 20 numbers to the plot+
			if (data.isEmpty()) {
				break;
			}
			Point p = data.remove();
			seriesb.getData().add(new Data<Number, Number>(p.getX(), p.getY()));
			System.out.println(seriesb.getData().size());
		}

		if (seriesb.getData().size() > MAX_POINTS) {
			timer.stop();
			
		}

	}

	private class AddToQueue implements Runnable {
		public void run() {
			try {
				Point p = new Point();
				p.setX(Math.random() * MAX_X);
				p.setY(function(p.getX()));
				data.add(p);

				Thread.sleep((long)(MAX_DELAY*Math.random()));
				executor.execute(this);
			} catch (InterruptedException ex) {
				Logger.getLogger(WorkingPlotter.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public class MyTimer extends AnimationTimer {

		@Override
		public void handle(long now) {
			addDataToSeries();
		}

	}

	public class Point {
		private double x, y;

		public double getX() {
			return x;
		}

		public void setX(double x) {
			this.x = x;
		}

		public double getY() {
			return y;
		}

		public void setY(double y) {
			this.y = y;
		}

	}

}