package hu.unideb.inf.prt.DietManager.controller;

import hu.unideb.inf.prt.DietManager.calculation.Calculation;
import hu.unideb.inf.prt.DietManager.model.DailyIntakeOfNutrients;
import hu.unideb.inf.prt.DietManager.model.User;
import java.awt.Color;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * @author Kriskó Szabolcs
 *
 */
public class WeeklyStatisticsController {

	private User user;

	public void setUser(User user) {
		this.user = user;
	}


	public Scene setBarChart() {
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Napok");
		yAxis.setLabel("Makronutriensek");

		BarChart statistics = new BarChart<String, Number>(xAxis, yAxis);

		XYChart.Series seriesCarbohydrate = new XYChart.Series();
		XYChart.Series seriesProtein = new XYChart.Series();
		XYChart.Series seriesFat = new XYChart.Series();
                
                
                

		for (DailyIntakeOfNutrients dailyNutrients : user.getWeek()) {
			seriesCarbohydrate.getData()
					.add(new XYChart.Data(dailyNutrients.getDate().toString(), dailyNutrients.getIntakeCarbohydrate()));
			seriesProtein.getData()
					.add(new XYChart.Data(dailyNutrients.getDate().toString(), dailyNutrients.getIntakeProtein()));
			seriesFat.getData()
					.add(new XYChart.Data(dailyNutrients.getDate().toString(), dailyNutrients.getIntakeFat()));
		}

		seriesCarbohydrate.setName("Szénhidrát");
		seriesProtein.setName("Fehérje");
		seriesFat.setName("Zsír");

		statistics.getData().addAll(seriesCarbohydrate, seriesProtein, seriesFat);
                
               
               

		return new Scene(statistics, 600, 400);
	}
}