package Entity;

import TileMap.TileMap;

public class Enemy extends MapObject {
  protected int health;
  protected int maxHealth;
  protected int damage;
  protected boolean dead;

  protected boolean flinching;
  protected long flinchedTime;

  public Enemy(TileMap tm) {
    super(tm);
  }

  public boolean isDead() {
    //        if(health==0){
    //            dead=true;
    //            dx=0;
    //        }
    return dead;
  }

  public int getDamage() {
    return damage;
  }

  public void hitDamage(int damage) {
    if (dead || flinching) return;
    health -= damage;
    if (health < 0) health = 0;
    if (health == 0) dead = true;
    flinching = true;
    flinchedTime = System.nanoTime();
  }

  public void update(Player player) {}
  ;

  public void checkAttackEnemy(Player player) {}
}
