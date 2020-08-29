package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.*;

/**
 * Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author Arav Tewari
 *
 */
public class LandQuakeMarker extends EarthquakeMarker
{

	public LandQuakeMarker(PointFeature quake)
	{
		super(quake);
		isOnLand = true;
	}

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y)
	{
		pg.ellipseMode(PConstants.CENTER);
		pg.ellipse(x, y, setMarkerSize(), setMarkerSize());

	}

	// Get the country the earthquake is in
	public String getCountry()
	{
		return (String) getProperty("country");
	}

}
