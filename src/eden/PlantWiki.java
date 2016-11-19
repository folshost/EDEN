
package eden;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Harry Doyle
 */

public class PlantWiki {
    
    public String displayWikiPage(String plantName) throws IOException{
       String fileName = plantName + "wiki.txt";
       String content = null;
       content = new String(Files.readAllBytes(Paths.get(fileName)));
       return content;
    }
    
    
    
}
