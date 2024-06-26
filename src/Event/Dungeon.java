package Event;

import CharacterInfo.Enemy;
import CharacterInfo.Player;
import GUI.TextAdventure;
import Game.EnemyGenerator;

import java.util.LinkedList;
import java.util.Queue;

public class Dungeon implements EventInterface {
    public Dungeon(Player p, TextAdventure game){
        this.game = game;
        this.player = p;
    }
    private TextAdventure game;
    private Player player;
    private String sideEnemy;
    private Queue<Enemy> sideEnemyList = new LinkedList<>();
    public Queue<Enemy> getSideEnemyList(){
        return this.sideEnemyList;
    }

    public void addEnemy(Enemy enemy){
        this.sideEnemyList.add(enemy);
    }
    public Enemy seeNextEnemyList(){
        return this.sideEnemyList.peek();
    }
    public Enemy getNextEnemyList(){
        return this.sideEnemyList.poll();
    }
    private void randomGen(Player p){
        Enemy e1 = EnemyGenerator.generateEnemy(p,this.game);

        Enemy e2 = EnemyGenerator.generateEnemy(p,this.game);

        Enemy e3 = EnemyGenerator.generateEnemy(p,this.game);

        this.sideEnemyList.offer(e1);
        this.sideEnemyList.offer(e2);
        this.sideEnemyList.offer(e3);

    }
    public static void trigger(TextAdventure game){
        game.setEvent(new Dungeon(game.getPlayer(),game));
        game.displayMessage("Do you want to step inside?");
        game.removePanel(game.getExplorePanel());
        game.addPanel(game.getEventPanel());
        game.repaint();

    }
    @Override
    public void start(Player p){
        this.randomGen(this.player);
    }
}

