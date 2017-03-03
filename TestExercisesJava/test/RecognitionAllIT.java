/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import javax.xml.parsers.ParserConfigurationException;
import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xml.sax.SAXException;
import org.hamcrest.*;
import static org.hamcrest.Matchers.greaterThan;

/**
 *
 * @author bepis
 */
@SuppressWarnings("FieldMayBeFinal")
@RunWith(Parameterized.class)
public class RecognitionAllIT {
    
    //////////////// SETUP ////////////////
    
    private File plateFile;
    private String plateExpected;
    private CarSnapshot carSnap;
    
    public RecognitionAllIT(File plateFile, String plateExpected){
        this.plateFile = plateFile;
        this.plateExpected = plateExpected;
    }
    
    @Before
    public void init() throws IOException {
        carSnap = new CarSnapshot(new FileInputStream(plateFile));
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> plateDataCreator() throws FileNotFoundException, IOException {
        
        // Dir path for the actual photos to be tested.
        String snapshotDirPath = "src/test/resources/snapshots";
        // Dir path for a file containing test results to be matched up against.
        // Contains an 'equals' for every plate .jpg file with its expected result.
        String resultsPath = "src/test/resources/results.properties";
        Properties properties;
        try (InputStream resultsStream = new FileInputStream(new File(resultsPath))) {
            properties = new Properties();
            properties.load(resultsStream);
        }
        
        assertThat(properties.size(), greaterThan(0));
        
        File snapshotDir = new File(snapshotDirPath);
        File[] snapshots = snapshotDir.listFiles();
        assertThat(snapshots, notNullValue());
        assertThat(snapshots.length, greaterThan(0));
        
        Collection<Object[]> dataForOneImage = new ArrayList();
        for (File file : snapshots) {
            String name = file.getName();
            String plateExpected = properties.getProperty(name);
            dataForOneImage.add(new Object[]{file, plateExpected});
        }
        
        return dataForOneImage;
        
    }
    
    //////////////// TESTS ////////////////
    
    @Test
    public void testAllPlates() throws ParserConfigurationException, IOException, SAXException{
        
        Intelligence intel = new Intelligence();
        assertThat(carSnap, notNullValue());
        
        assertThat(carSnap, notNullValue());
        assertThat(carSnap.getImage(), notNullValue());
        assertThat(plateExpected, notNullValue());
        
        String numberPlate = intel.recognize(carSnap, false);
        
        assertThat(plateExpected, equalTo(numberPlate));
        
    }
    
    
}
