package module4;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.*;

/**
 * Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author Arav Tewari
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker
{

	public OceanQuakeMarker(PointFeature quake)
	{
		super(quake);

		// setting field in earthquake marker
		isOnLand = false;
	}

	@Override
	public void drawEarthquake(PGraphics pg, float x, float y)
	{
		pg.rectMode(PConstants.CENTER);
		pg.rect(x, y, setMarkerSize(), setMarkerSize());

	}

}
