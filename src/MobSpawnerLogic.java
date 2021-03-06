public abstract class MobSpawnerLogic {

    private OMobSpawnerBaseLogic logic;

    public abstract NBTTagCompound getMetaTag();

    public abstract void writeToTag(NBTTagCompound tag);

    public abstract void readFromTag(NBTTagCompound tag);

    public MobSpawnerLogic(OMobSpawnerBaseLogic logic) {
        this.logic = logic;
        this.logic.logic = this;
    }

    public int getX() {
        return logic.b();
    }

    public int getY() {
        return logic.c();
    }

    public int getZ() {
        return logic.d();
    }

    public World getWorld() {
        return logic.a().world;
    }

    public void update() {
        logic.g();
    }

    /**
     * Allows what to spawn to change on-the-fly.
     * <strong>NB:</strong> This only works if a complex entity has not been set.
     * @param spawn The name of the entity to spawn.
     * @see #setSpawnedEntity(BaseEntity)
     * @see #setSpawnedEntity(Item)
     */
    public void setSpawn(String spawn) {
        logic.a(spawn);
        update();
    }

    /**
     * Returns the spawn used.
     *
     * @return
     */
    public String getSpawn() {
        return logic.e();
    }

    /**
     * Allows delay of what to spawn to change on-the-fly.
     * Modification of this is near-useless as delays get randomized after
     * spawn.
     *
     * @param delay
     */
    public void setDelay(int delay) {
        logic.b = delay;
    }

    /**
     * Returns the minimum delay of the spawner.
     * The delay between spawns is picked randomly between this and the max delay.
     *
     * @return
     */
    public int getMinDelay() {
        return logic.g;
    }

    /**
     * Sets the minimum delay of the spawner.
     * The delay between spawns is picked randomly between this and the max delay.
     * Default is 200.
     *
     * @param delay
     */
    public void setMinDelay(int delay) {
        logic.g = delay;
    }

    /**
     * Returns the maximum delay of the spawner.
     * The delay between spawns is picked randomly between this and the min delay.
     *
     * @return
     */
    public int getMaxDelay() {
        return logic.h;
    }

    /**
     * Sets the maximum delay of the spawner.
     * The delay between spawns is picked randomly between this and the min delay.
     * Default is 800.
     *
     * @param delay
     */
    public void setMaxDelay(int delay) {
        logic.h = delay;
    }

    /**
     * Returns the amount of mobs this spawner attempts to spawn.
     *
     * @return
     */
    public int getSpawnCount() {
        return logic.i;
    }

    /**
     * Sets the amount of mobs this spawner attempts to spawn.
     * Default is 4.
     *
     * @param count
     */
    public void setSpawnCount(int count) {
        logic.i = count;
    }

    /**
     * Returns the maximum number of entities this spawner allows nearby in order to continue spawning.
     * Any more entities and this spawner won't spawn mobs.
     *
     * @return
     */
    public int getMaxNearbyEntities() {
        return logic.k;
    }

    /**
     * Sets the maximum number of entities this spawner allows nearby in order to continue spawning.
     * Any more entities and this spawner won't spawn mobs.
     * Default is 6.
     *
     * @param entities
     */
    public void setMaxNearbyEntities(int entities) {
        logic.k = entities;
    }

    /**
     * If there are no players within this distance of the spawner, it won't spawn.
     *
     * @return
     */
    public int getRequiredPlayerRange() {
        return logic.l;
    }

    /**
     * If there are no players within this distance of the spawner, it won't spawn.
     * Default is 16.
     *
     * @param range
     */
    public void setRequiredPlayerRange(int range) {
        logic.l = range;
    }

    /**
     * Returns the maximum distance that this spawner will spawn mobs at.
     *
     * @return
     */
    public int getSpawnRange() {
        return logic.m;
    }

    /**
     * Sets the maximum distance that this spawner will spawn mobs at.
     * Default is 4.
     *
     * @param range
     */
    public void setSpawnRange(int range) {
        logic.m = range;
    }

    /**
     * Sets the entity spawned by this spawner.
     *
     * @param entity The entity this spawner should spawn
     */
    public void setSpawnedEntity(BaseEntity entity) {
        setSpawnedEntity(entity.getEntity());
    }

    /**
     * Sets the entity spawned by this spawner to an item.
     *
     * @param itemEntity The item this spawner should spawn
     */
    public void setSpawnedEntity(Item itemEntity) {
        setSpawnedEntity(new OEntityItem(null, 0, 0, 0, itemEntity.getBaseItem()));
    }

    /**
     * Sets the entity spawned by this spawner.
     *
     * @param entity The entity this spawner should spawn
     */
    public void setSpawnedEntity(OEntity entity) {
        NBTTagCompound tag = new NBTTagCompound();
        entity.b(tag.getBaseTag());
        tag.removeTag("Health");
        tag.removeTag("HurtTime");
        tag.removeTag("DeathTime");
        tag.removeTag("AttackTime");
        tag.removeTag("Age");
        logic.a(new OWeightedRandomMinecart(logic, tag.getBaseTag()));
    }

    public OMobSpawnerBaseLogic getLogic() {
        return logic;
    }
}