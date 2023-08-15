package org.live_edition;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.live_edition.components.Frame;
import org.live_edition.world.World;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static String name;
    public static String version;

    public static Frame frame;

    public static World world;

    public static void main(String[] args) throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        name = model.getArtifactId();
        version = model.getVersion();

        world = new World(50, 50, 25);
        world.generateLevel();

        frame = new Frame();
    }
}