package module1;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

/**
 * HelloWorld An application with two maps side-by-side zoomed in on different
 * locations.
 *
 * @author Arav Tewari
 * @version June 23, 2020
 */
public class HelloWorld extends PApplet
{
	/**
	 * TODO: display second map of home town, zoom in, and change background
	 */
	private static final long serialVersionUID = 1L;

	// local tiles for no internet
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	// change to true if offline
	private static final boolean offline = false;

	UnfoldingMap map1;
	UnfoldingMap map2;

	public void setup()
	{
		// Setup applet window to be 800 x 600
		size(800, 600, P2D);

		// background
		this.background(0, 0, 0);

		// map provider
		AbstractMapProvider provider = new Google.GoogleMapProvider();
		
		int zoomLevel = 10;

		if (offline)
		{
			// provider for offline
			provider = new MBTilesMapProvider(mbTilesString);
			zoomLevel = 3;
		}

		// UnfoldingMap(this, x, y, width, height, map provider)
		map1 = new UnfoldingMap(this, 25, 50, 350, 500, provider);
		map2 = new UnfoldingMap(this, 425, 50, 350, 500, provider);

		// zoom and center in
		map1.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
		map2.zoomAndPanTo(zoomLevel, new Location(37.5f, -122f));

		MapUtils.createDefaultEventDispatcher(this, map1);
		MapUtils.createDefaultEventDispatcher(this, map2);

		//System.out.println("Done!");
	}

	public void draw()
	{
		map1.draw();
		map2.draw();
	}
}
