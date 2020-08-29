package module5;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Implements a visual marker for cities on an earthquake map
 * 
 * @author Arav Tewari
 *
 */

public class CityMarker extends CommonMarker
{

	public static int TRI_SIZE = 5; // The size of the triangle marker

	public CityMarker(Location location)
	{
		super(location);
	}

	public CityMarker(Feature city)
	{
		super(((PointFeature) city).getLocation(), city.getProperties());
		// Cities have properties: "name" (city name), "country" (country name) and "population" (population, in millions)
	}

	/**
	 * Implementation of method to draw marker on the map.
	 */
	public void draw(PGraphics pg, float x, float y)
	{
		// Save previous drawing style
		pg.pushStyle();

		pg.fill(150, 30, 30);
		pg.triangle(x, y - TRI_SIZE, x - TRI_SIZE, y + TRI_SIZE, x + TRI_SIZE, y + TRI_SIZE);

		// Restore previous drawing style
		pg.popStyle();
	}

	// Show city title if selected
	public void showTitle(PGraphics pg, float x, float y)
	{

		String title = getCity() + ", " + getCountry() + " --- Population:" + getPopulation() + " million";
		float width = pg.textWidth(title);
		
		pg.rectMode(PConstants.CORNER);
		pg.fill(255);
		pg.rect(x+15, y-8, width, 20, 5, 5, 5, 5);
		
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.fill(20, 24, 35);
		pg.text(title, x+22, y - 5);
	}

	/*
	 * Local getters for some city properties.
	 */
	public String getCity()
	{
		return getStringProperty("name");
	}

	public String getCountry()
	{
		return getStringProperty("country");
	}

	public float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y)
	{
		
	}
}
