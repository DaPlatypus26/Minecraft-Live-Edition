package org.live_edition;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.live_edition.components.Frame;
import org.live_edition.world.World;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    public static String name;
    public static String version;

    public static Frame frame;

    public static World world;

    public static void main(String[] args) throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model;
        if(new File("pom.xml").exists()) {
            model = reader.read(new FileReader("pom.xml"));
        } else {
            model = reader.read(new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream("/META-INF/maven/org.apache.maven/maven-model/pom.xml"))));
        }

        name = model.getArtifactId().replaceAll("-", " ");
        version = model.getVersion();

        world = new World(50, 50, 25);
        world.generateLevel();

        frame = new Frame();
    }
}