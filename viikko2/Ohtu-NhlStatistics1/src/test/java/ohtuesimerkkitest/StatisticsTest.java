/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkkitest;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kxkyllon
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }

        @Override
        public int extractInt(String str) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    };

    public StatisticsTest() {
        stats = new Statistics(readerStub);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void testSearch() {
        String nameToSearch = "Gretzky";
        Player found = stats.search(nameToSearch);
        assertEquals("Statistics search by name does not work", nameToSearch, found.getName());
    }
    
    @Test
    public void testSearchNoHockeyPlayer() {
        //String nameToSearch = ""; tämä johtaa erroriin
        String nameToSearch = "Hirvikoski";
        Player found = stats.search(nameToSearch);
        assertEquals("Statistics search with no HockeyPlayerName", null, found);
    }
    
    @Test
    public void testTeam() {
        int expectedListSize = 3;
        String teamToSearch = "EDM";
        List<Player> team = stats.team(teamToSearch);
        assertEquals("Team search returns wrong size list", expectedListSize, team.size());
    }
    
    @Test
    public void testTopScores() {
        int expectedListSize = 3;
        List<Player> topScores = stats.topScorers(2);
        assertEquals("Top Scores returned wrong abount of players", expectedListSize, topScores.size());
    }
}
