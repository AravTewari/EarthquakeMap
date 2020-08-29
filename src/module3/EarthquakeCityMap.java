package module3;

import java.util.ArrayList;
import java.util.List;
//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.MapUtils;
//Parsing library
import parsing.ParseFeed;
//Processing library
import processing.core.PApplet;

/**
 * EarthquakeCityMap An application with an interactive map displaying
 * earthquake data.
 * 
 * @author Arav Tewari
 * @version June 24, 2020
 */

public class EarthquakeCityMap extends PApplet
{

	// You can ignore this. It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	public static final float THRESHOLD_MODERATE = 5;
	public static final float THRESHOLD_LIGHT = 4;
	
	private UnfoldingMap map;
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	public void setup()
	{
		size(950, 600, OPENGL);

		if (offline)
		{
			map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
			earthquakesURL = "2.5_week.atom"; //Saved Aug 7, 2015
		}
		else
		{
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Microsoft.HybridProvider());
		}

		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);

		List<Marker> markers = new ArrayList<Marker>();

		// PointFeatures have a getLocation method
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		
		for (PointFeature eq: earthquakes)
		{
			SimplePointMarker currMarker = createMarker(eq);
			markers.add(currMarker);
		}
		map.addMarkers(markers);
	}

	private SimplePointMarker createMarker(PointFeature feature)
	{
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());

		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());
		
		if (mag < THRESHOLD_LIGHT)
		{
			int blue = color(0, 0, 255);
			marker.setRadius(8);
			marker.setColor(blue);
		}
		else if (mag >= THRESHOLD_LIGHT && mag <= THRESHOLD_MODERATE)
		{
			int yellow = color (255, 255, 0);
			marker.setRadius(12);
			marker.setColor(yellow);
		}
		else if (mag >= THRESHOLD_MODERATE)
		{
			int red = color(255, 0, 0);
			marker.setRadius(16);
			marker.setColor(red);
		}
		else
		{
			System.out.println(feature.getProperties());
		}
		
		return marker;
	}

	public void draw()
	{
		background(10);
		map.draw();
		addKey();
	}
	
	private void addKey()
	{
		fill(255);
		rect(15, 50, 150, 250);
		noStroke();

		fill(color(0, 0, 255));
		ellipse(40, 120, 16, 16);
		
		fill(color(255, 255, 0));
		ellipse(40, 180, 20, 20);
		
		fill(color(255, 0, 0));
		ellipse(40, 240, 24, 24);
		
		fill(0, 0, 0);
		textAlign(CENTER);
		text("Earthquake Key", 90, 65);
		
		textAlign(LEFT);
		text("Below 4.0", 60, 125);
		text("4.0+ Magnitude", 60, 185);
		text("5.0+ Magnitude", 60, 245);
		
	}
}
