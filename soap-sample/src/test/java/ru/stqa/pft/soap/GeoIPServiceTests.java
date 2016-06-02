package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by Anna on 02.06.2016.
 */
public class GeoIPServiceTests {

  @Test
  public void testMyIP() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.185.30.189");
    assertEquals(geoIP.getCountryCode(), "RUS");
  }

  @Test
  public void testInvalidIP() {
    GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("93.185.30.xxx");
    assertEquals(geoIP.getCountryCode(), null);
  }
}
