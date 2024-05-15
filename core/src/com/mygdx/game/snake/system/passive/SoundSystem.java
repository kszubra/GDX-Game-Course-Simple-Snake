package com.mygdx.game.snake.system.passive;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.snake.assets.AssetDescriptors;

public class SoundSystem extends EntitySystem {

    // == attributes ==
    private final AssetManager assetManager;

    private Sound coinSound;
    private Sound loseSound;

    // == constructors ==
    public SoundSystem(AssetManager assetManager) {
        this.assetManager = assetManager;
        init();
    }

    // == init ==
    private void init() {
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);
    }

    // == public methods ==
    @Override
    public void update(float deltaTime) {
        // not processing
    }

    @Override
    public boolean checkProcessing() {
        return false;
    }

    public void hitCoin() {
        coinSound.play();
    }

    public void lose() {
        loseSound.play();
    }
}
